package br.com.alex.ossystem.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alex.ossystem.domain.OrdemServico;
import br.com.alex.ossystem.domain.enums.Prioridade;
import br.com.alex.ossystem.domain.enums.Status;

public class OrdemServicoDTO implements Serializable {

	private static final long serialVersionUID = -6592765415060444011L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;

	private Integer prioridade;

	private String observacao;

	private Integer status;

	@NotNull(message = "O campo TÉCNICO é requerido")
	private Integer tecnico;

	@NotNull(message = "O campo CLIENTE é requerido")
	private Integer cliente;

	public OrdemServicoDTO() {
		super();
	}

	public OrdemServicoDTO(OrdemServico ordemServico) {
		super();
		this.id = ordemServico.getId();
		this.dataAbertura = ordemServico.getDataAbertura();
		this.dataFechamento = ordemServico.getDataFechamento();
		this.prioridade = ordemServico.getPrioridade().getCodigo();
		this.observacao = ordemServico.getObservacao();
		this.status = ordemServico.getStatus().getCodigo();
		this.tecnico = ordemServico.getTecnico().getId();
		this.cliente = ordemServico.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}

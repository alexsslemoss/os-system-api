package br.com.alex.ossystem.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.ossystem.domain.Cliente;
import br.com.alex.ossystem.domain.OrdemServico;
import br.com.alex.ossystem.domain.Tecnico;
import br.com.alex.ossystem.domain.dtos.OrdemServicoDTO;
import br.com.alex.ossystem.domain.enums.Prioridade;
import br.com.alex.ossystem.domain.enums.Status;
import br.com.alex.ossystem.exceptions.DataNotFoundException;
import br.com.alex.ossystem.repositories.ClienteRepository;
import br.com.alex.ossystem.repositories.OrdemServicoRepository;
import br.com.alex.ossystem.repositories.TecnicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServicoDTO findById(Integer id) {
		OrdemServico ordemServico = this.ordemServicoRepository.findById(id).orElseThrow(DataNotFoundException::new);
		OrdemServicoDTO ordemServicoDTO = new OrdemServicoDTO(ordemServico);
		return ordemServicoDTO;
	}

	public List<OrdemServicoDTO> findAll() {
		return this.ordemServicoRepository.findAll().stream().map(OrdemServicoDTO::new).collect(Collectors.toList());
	}

	public OrdemServicoDTO create(OrdemServicoDTO ordemServicoDTO) {
		OrdemServico ordemServico = this.ordemServicoRepository.save(fromDTO(ordemServicoDTO));
		return new OrdemServicoDTO(ordemServico);
	}
	
	private OrdemServico fromDTO(OrdemServicoDTO ordemServicoDTO) {
		Tecnico tecnico = this.tecnicoRepository.findById(ordemServicoDTO.getTecnico()).orElseThrow(() -> new DataNotFoundException("Técnico infomado não cadastrado"));
		Cliente cliente = this.clienteRepository.findById(ordemServicoDTO.getCliente()).orElseThrow(() -> new DataNotFoundException("Cliente infomado não cadastrado"));

		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(ordemServicoDTO.getId() != null ? ordemServicoDTO.getId() : null);
		ordemServico.setPrioridade(ordemServicoDTO.getPrioridade() == null ? Prioridade.BAIXA : ordemServicoDTO.getPrioridade());
		ordemServico.setStatus(ordemServicoDTO.getStatus() == null ? Status.ABERTA : ordemServicoDTO.getStatus());
		ordemServico.setObservacao(ordemServicoDTO.getObservacao());
		ordemServico.setTecnico(tecnico);
		ordemServico.setCliente(cliente);
		
		if (ordemServicoDTO.getStatus() != null && ordemServicoDTO.getStatus().equals(Status.ENCERRADA)) {
			ordemServico.setDataFechamento(LocalDateTime.now());
		}
		
		return ordemServico;
	}

	public OrdemServicoDTO update(Integer id, @Valid OrdemServicoDTO ordemServicoDTO) {
		Optional<OrdemServico> optional = this.ordemServicoRepository.findById(id);
		
		if (optional.isEmpty()) {
			throw new DataNotFoundException();
		}
		ordemServicoDTO.setId(id);
		OrdemServico save = this.ordemServicoRepository.save(fromDTO(ordemServicoDTO));
		return new OrdemServicoDTO(save);
	}

	public void delete(Integer id) {
		OrdemServico ordemServico = this.ordemServicoRepository.findById(id).orElseThrow(DataNotFoundException::new);
		this.ordemServicoRepository.delete(ordemServico);
	}

}

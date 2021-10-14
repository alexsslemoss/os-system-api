package br.com.alex.ossystem.domain.enums;

import java.util.stream.Stream;

public enum Status {

	ABERTA(0, "ABERTA"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADA(2, "ENCERRADA");

	private Integer codigo;

	private String descricao;

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer codigo) {
		return Stream.of(Status.values())
				.filter(s -> s.getCodigo().equals(codigo))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Status inválido, codigo=" + codigo));
	}

}

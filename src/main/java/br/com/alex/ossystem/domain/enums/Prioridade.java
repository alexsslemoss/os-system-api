package br.com.alex.ossystem.domain.enums;

import java.util.stream.Stream;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer codigo;

	private String descricao;

	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		return Stream.of(Prioridade.values())
				.filter(p -> p.getCodigo().equals(codigo))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Prioridade inválida, codigo=" + codigo));
	}

}

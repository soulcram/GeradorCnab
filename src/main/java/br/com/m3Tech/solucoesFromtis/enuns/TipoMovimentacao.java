package br.com.m3Tech.solucoesFromtis.enuns;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum TipoMovimentacao {

	AQUISICAO("Aquisição"), BAIXA("Baixa"), LIQUIDACAO_PARCIAL("Liquidação Parcial"), RECOMPRA("Recompra"),
	RECOMPRA_PARCIAL("Recompra Parcial"), PRORROGACAO("Prorrogação"), REATIVACAO("Reativação");

	private String nome;

	TipoMovimentacao(String nome) {
		this.nome = nome;
	}

	public static TipoMovimentacao parse(String nome) {

		for (TipoMovimentacao item : TipoMovimentacao.values()) {
			if (item.nome.equals(nome)) {
				return item;
			}
		}
		return AQUISICAO;
	}

	public static TipoMovimentacao parseContains(String conteudo) {

		if (conteudo.contains("AQUISICAO")) {
			return AQUISICAO;
		} else if (conteudo.contains("BAIXA")) {
			return BAIXA;
		}else if (conteudo.contains("LIQUIDACAO_PARCIAL")) {
			return LIQUIDACAO_PARCIAL;
		}else if (conteudo.contains("RECOMPRA")) {
			return RECOMPRA;
		}else if (conteudo.contains("RECOMPRA_PARCIAL")) {
			return RECOMPRA_PARCIAL;
		}else if (conteudo.contains("PRORROGACAO")) {
			return PRORROGACAO;
		}else if (conteudo.contains("REATIVACAO")) {
			return REATIVACAO;
		}
		return AQUISICAO;
	}

	public static List<TipoMovimentacao> findAll() {
		List<TipoMovimentacao> retorno = new ArrayList<TipoMovimentacao>();

		for (TipoMovimentacao item : TipoMovimentacao.values()) {
			retorno.add(item);
		}

		return retorno;

	}

}

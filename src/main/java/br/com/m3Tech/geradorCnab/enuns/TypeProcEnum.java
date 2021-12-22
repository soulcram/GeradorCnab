package br.com.m3Tech.geradorCnab.enuns;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum TypeProcEnum {

	PROCEDURE("PROCEDURE", "SP_"), FUNCTION("FUNCTION", ""), VIEW("VIEW", "VW_");

	private String nome;
	private String iniciais;

	TypeProcEnum(String nome, String iniciais) {
		this.nome = nome;
		this.iniciais = iniciais;
	}

	public static TypeProcEnum parse(String nome) {

		for (TypeProcEnum item : TypeProcEnum.values()) {
			if (item.nome.equals(nome)) {
				return item;
			}
		}
		return PROCEDURE;
	}

	public static TypeProcEnum parseContains(String conteudo) {

		if (conteudo.contains("PROCEDURE")) {
			return PROCEDURE;
		} else if (conteudo.contains("FUNCTION")) {
			return FUNCTION;
		} else if (conteudo.contains("VIEW")) {
			return VIEW;
		}
		return PROCEDURE;
	}

	public static List<TypeProcEnum> findAll() {
		List<TypeProcEnum> retorno = new ArrayList<TypeProcEnum>();

		for (TypeProcEnum item : TypeProcEnum.values()) {
			retorno.add(item);
		}

		return retorno;

	}

}

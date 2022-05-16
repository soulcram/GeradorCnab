package br.com.m3Tech.solucoesFromtis.util;

import java.util.Arrays;

import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;

public class LayoutUtils {

	private LayoutUtils() {
	}

	public static boolean coobrigacaoNoHeader(LayoutEnum layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_500_FINAXIS, LayoutEnum.CNAB_600_DAYCOVAL)
				.contains(layoutSelecionado);

	}

	public static boolean exibirChaveNfe(Integer layoutSelecionado) {
		return Arrays
				.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_600_PLANNER_REM03, LayoutEnum.CNAB_600_DAYCOVAL)
				.contains(LayoutEnum.parse(layoutSelecionado));

	}

	public static Boolean exibirIndexadores(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirDataCarencia(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirTaxaJurosIndexador(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirVariacaoCambial(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirRisco(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

}

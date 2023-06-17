package br.com.m3Tech.solucoesFromtis.util;

import java.util.Arrays;

import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;

public class LayoutUtils {

	private LayoutUtils() {
	}

	public static boolean coobrigacaoNoHeader(LayoutEnum layoutSelecionado) {
		return Arrays.asList(
							 LayoutEnum.CNAB_600_DAYCOVAL, 
							 LayoutEnum.CNAB_500_REMESSA_PAULISTA,
							 LayoutEnum.CNAB_500_REMESSA_V2,
							 LayoutEnum.CNAB_450_REMESSA_BRASIL_PLURAL,
							 LayoutEnum.CNAB_500_OT_REMESSA)
				.contains(layoutSelecionado);

	}

	public static boolean exibirChaveNfe(Integer layoutSelecionado) {
		return Arrays
				.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_600_PLANNER_REM03, LayoutEnum.CNAB_600_DAYCOVAL, LayoutEnum.CNAB_444_REMESSA_AUTBANK, LayoutEnum.CNAB_500_VERSAO_FINAXIS)
				.contains(LayoutEnum.parse(layoutSelecionado));

	}
	
	public static boolean exibirValorRetencao(Integer layoutSelecionado) {
		return Arrays
				.asList(LayoutEnum.CNAB_444_REMESSA_BRL)
				.contains(LayoutEnum.parse(layoutSelecionado));

	}
	
	public static boolean exibirDadosConta(Integer layoutSelecionado) {
		return Arrays
				.asList(LayoutEnum.CNAB_444_REMESSA_BRL, LayoutEnum.CNAB_600_DAYCOVAL)
				.contains(LayoutEnum.parse(layoutSelecionado));

	}

	public static Boolean exibirIndexadores(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_500_REMESSA_STONE, LayoutEnum.CNAB_444_REMESSA_AUTBANK).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirDataCarencia(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_444_REMESSA_AUTBANK).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirTaxaJurosIndexador(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_500_REMESSA_STONE, LayoutEnum.CNAB_444_REMESSA_AUTBANK).contains(LayoutEnum.parse(layoutSelecionado));
	}
	
	public static Boolean exibirTaxaSpread(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_500_REMESSA_STONE, LayoutEnum.CNAB_600_DAYCOVAL).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirVariacaoCambial(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirRisco(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_REMESSA_444, LayoutEnum.CNAB_444_REMESSA_AUTBANK).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirQtdeLastros(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_500_REMESSA_FIDD).contains(LayoutEnum.parse(layoutSelecionado));
	}
	
	public static Boolean exibirTipoPagamento(Integer layoutSelecionado) {
		return Arrays.asList(LayoutEnum.CNAB_500_REMESSA_FIDD).contains(LayoutEnum.parse(layoutSelecionado));
	}

	public static Boolean exibirExpecieComum(Integer layoutSelecionado) {
		return !Arrays.asList(LayoutEnum.CNAB_400_REMESSA_FINAXIS, LayoutEnum.CNAB_500_VERSAO_FINAXIS).contains(LayoutEnum.parse(layoutSelecionado));
		 
	}

}

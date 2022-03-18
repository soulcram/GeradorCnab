package br.com.m3Tech.solucoesFromtis.enuns;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum LayoutEnum {
	
	CNAB_REMESSA_444(16,"Remessa_444","R",444),
	CNAB_240_REMESSA(17,"remessa_240","R",240),
	CNAB_500_REMESSA_CREDSYSTEM(24,"cnab500RemessaCredSystem", "R", 500),
//	CNAB_500_REMESSA_FIDD(41,"cnab500RemessaFidd","R",500),
	CNAB_500_REMESSA_CCB(94,"cnab500RemessaCcb","R",500),
	CNAB_600_PLANNER_REM03(43,"cnab600RemessaPlanner03","R",600),
	
	CNAB_400_COBRANCA_BANCO_BRASIL(4,"cobrancaBancoBrasil_400","C",400),
	CNAB_400_COBRANCA_BRADESCO(5,"cobrancaBradesco_400","C",400),
	CNAB_400_COBRANCA_HSBC(6,"cobrancaHsbc_400","C",400),
	CNAB_400_COBRANCA_ITAU(7,"cobrancaItau_400","C",400),
	CNAB_400_COBRANCA_SAFRA(9,"cobrancaSafra_400","C",400),
	CNAB_400_COBRANCA_FIBRA(11,"cobrancaFibra_400","C",400),
	CNAB_400_COBRANCA_BS2(27,"cobrancaBs2_400","C",400),
	CNAB_400_COBRANCA_CITIBANK(28,"cobrancaCitibank_400","C",400),
	CNAB_400_COBRANCA_STARBANK(32,"cobrancaStarBank_400","C",400),
	CNAB_400_COBRANCA_SANTANDER(33,"cobrancaSantander_400","C",400),
	CNAB_400_COBRANCA_MONEYPLUS(38,"cobrancaMoneyPlus_400","C",400),
	CNAB_400_COBRANCA_SOCIEDADE_CREDITO(45,"cobrancaSociedadeCredito_400","C",400),
	CNAB_240_COBRANCA_ABC(46,"cobrancaAbc_240","C",240),
	CNAB_240_COBRANCA_SANTANDER(25,"cobrancaSantander_240","C",240),
	CNAB_240_COBRANCA_SICOOB(26,"cobrancaSiccob_240","C",240),
	CNAB_400_COBRANCA_BANCO_RENDIMENTO(51,"cobrancaBancoRendimento_400","C",400)
	;
	
	private Integer cdLayout;
	private String nmLayout;
	private String tipo;
	private Integer tamLayout;
	
	LayoutEnum(Integer cdLayout, String nmLayout,String tipo, Integer tamLayout){
		this.cdLayout = cdLayout;
		this.nmLayout = nmLayout;
		this.tipo = tipo;
		this.tamLayout = tamLayout;
	}
	
	public static LayoutEnum parse(Integer cdLayout) {
		
		for(LayoutEnum item : LayoutEnum.values()) {
			if(item.cdLayout == cdLayout) {
				return item;
			}
		}
		return CNAB_REMESSA_444;
	}
	
	
	public static List<LayoutEnum> findAllRemessa(){
		List<LayoutEnum> retorno = new ArrayList<LayoutEnum>();
		
		for(LayoutEnum item : LayoutEnum.values()) {
			if("R".equals(item.tipo)) {
				retorno.add(item);
			}
		}
		
		return retorno;
		
	}
	
	public static List<LayoutEnum> findAllCobranca(){
		List<LayoutEnum> retorno = new ArrayList<LayoutEnum>();
		
		for(LayoutEnum item : LayoutEnum.values()) {
			if("C".equals(item.tipo)) {
				retorno.add(item);
			}
		}
		
		return retorno;
		
	}

	public static boolean in(LayoutEnum layoutEnum, Integer ... codigosLayout) {
		
		for(Integer i :  codigosLayout) {
			if(layoutEnum.getCdLayout().equals(i)) {
				return true;
			}
		}
		return false;
	}

	
}

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
	CNAB_600_DAYCOVAL(88,"cnab600RemessaDaycoval", "R",600),
    CNAB_500_REMESSA_DAYMAXX(89, "cnab500RemessaDaymaxx", "R",500),
    CNAB_500_FINAXIS(35,"cnab500RemessaFinaxis", "R", 500),
	
//	CNAB_400_COBRANCA_BANCO_BRASIL(4,"cnab400RetornoCobrancaPadrao","C",400),
	CNAB_400_COBRANCA_BRADESCO(5,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_HSBC(6,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_ITAU(7,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_SAFRA(9,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_FIBRA(11,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_240_COBRANCA_SANTANDER(25,"cnab400RetornoCobrancaPadrao","C",240),
//	CNAB_240_COBRANCA_SICOOB(26,"cnab400RetornoCobrancaPadrao","C",240),
//	CNAB_400_COBRANCA_BS2(27,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_CITIBANK(28,"cnab400RetornoCobrancaPadrao","C",400),
	CNAB_400_COBRANCA_STARBANK(32,"cnab400RetornoCobrancaPadrao","C",400),
	CNAB_400_COBRANCA_SANTANDER(33,"cnab400RetornoCobrancaPadrao","C",400),
	CNAB_400_COBRANCA_MONEYPLUS(38,"cnab400RetornoCobrancaPadrao","C",400),
	CNAB_400_COBRANCA_SOCIEDADE_CREDITO(45,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_240_COBRANCA_ABC(46,"cnab400RetornoCobrancaPadrao","C",240),
//	CNAB_400_COBRANCA_BANCO_RENDIMENTO(51,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_AUTBANK(91,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_ABC(96,"cnab400RetornoCobrancaPadrao","C",400),
//	CNAB_400_COBRANCA_DELBANK(97,"cnab400RetornoCobrancaPadrao","C",400)	
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
	
	public static LayoutEnum findCobrancaByCodBanco(String codBanco){

        switch (codBanco) {
            case "033":
            	return CNAB_400_COBRANCA_SANTANDER;
//            case "353":
//            	return CNAB_240_COBRANCA_SANTANDER;
//            case "001":
//            	return CNAB_400_COBRANCA_BANCO_BRASIL;
            case "237":
            	return CNAB_400_COBRANCA_BRADESCO;
            case "574":
            	return CNAB_400_COBRANCA_STARBANK;
//            case "246":
//            	return CNAB_400_COBRANCA_ABC;
//            case "341":
//            	return CNAB_400_COBRANCA_ITAU;
//            case "399":
//            	return CNAB_400_COBRANCA_HSBC;
//            case "422":
//            	return CNAB_400_COBRANCA_SAFRA;
//            case "218":
//            	return CNAB_400_COBRANCA_BS2;
//            case "745":
//            	return CNAB_400_COBRANCA_CITIBANK;
            case "274":
            	return CNAB_400_COBRANCA_MONEYPLUS;
            case "329":
            	return CNAB_400_COBRANCA_SOCIEDADE_CREDITO;
//            case "435":
//            	return CNAB_400_COBRANCA_DELBANK;
//			case "707":
//				return CNAB_400_COBRANCA_AUTBANK;
//			case "756":
//				return CNAB_240_COBRANCA_SICOOB;
            default:
			  return CNAB_400_COBRANCA_BRADESCO;
        }
		
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

package br.com.m3Tech.solucoesFromtis.enuns;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum LayoutEnum {
	
	CNAB_REMESSA_444(16,"Remessa_444","R",444,37, 94,"ddMMyy"),
	CNAB_240_REMESSA(17,"remessa_240","R",240,37, 94,"ddMMyy"),
	CNAB_500_REMESSA_V2(19, "cnab500RemessaV2", "R",500, 108, 94, "ddMMyy"),
	CNAB_500_OT_REMESSA(20, "cnab500OTRemessa", "R", 500, 138, 94, "ddMMyyyy"),
	CNAB_500_REMESSA_PAULISTA(23, "cnab500RemessaC3", "R",500,37, 94,"ddMMyy"),
	CNAB_500_REMESSA_CREDSYSTEM(24,"cnab500RemessaCredSystem", "R", 500,37, 94,"ddMMyy"),
	CNAB_550_REMESSAV2(28,"cnab550RemessaV2", "R", 500,37, 94,"ddMMyy"),
	CNAB_500_REMESSA_PLURAL_CASHME(35,"cnab500RemessaFinaxis", "R", 500,80, 94,"ddMMyy"),
	CNAB_500_REMESSA_STONE(37,"cnab500RemessaStone","R",500,47, 94,"ddMMyyyy"),
	CNAB_500_REMESSA_FIDD(41,"cnab500RemessaFidd","R",500,37, 94,"ddMMyy"),
	CNAB_600_PLANNER_REM03(43,"cnab600RemessaPlanner03","R",600,35, 94,"ddMMyyyy"),
	CNAB_400_REMESSA_FINAXIS(47, "cnab400RemessaFinaxis", "R", 400, 110, 94,"ddMMyy"),
	CNAB_450_REMESSA_GENIAL(50,"Remessa_450_Genial","R",450,37, 94,"ddMMyy"),
	CNAB_460_REMESSA(51,"Remessa_460","R",460,37, 94,"ddMMyy"),
	CNAB_450_REMESSA_BRASIL_PLURAL(53, "cnab450RemessaBrasilPlural", "R", 450, 37, 94, "ddMMyy"),
	CNAB_444_REMESSA_BRL(55, "cnab444RemessaBRL", "R", 444, 37, 94, "ddMMyy"),
	CNAB_500_VERSAO_FINAXIS(56, "cnab500RemessaFinaxis", "R", 500,  108, 94, "ddMMyy"),
	CNAB_500_REMESSA_BV(58, "cnab500RemessaBV", "R", 500, 108, 110, "ddMMyy"),
	CNAB_550_FINAXIS(60, "cnab550RemessaV3", "R", 550, 108, 110, "ddMMyy"),
	CNAB_460_REMESSA_AUTBANK(69, "cnab460RemessaAutbank", "R",460,108, 94,"ddMMyy"),
	CNAB_550_REMESSAV2_CM(70, "cnab550RemessaV2CM", "R", 500,  108, 94,"ddMMyy"),
    CNAB_500_REMESSA_CM_C3(71, "cnab500RemessaV2CM", "R", 500,  108, 94,"ddMMyy"),
    CNAB_550_REMESSA_COMUM_CM(72, "cnab550RemessaComumCM", "R", 550,  108, 94,"ddMMyy"),
   	CNAB_550_REMESSA_COMUM_CM_V2(73, "cnab550RemessaComumV2CM", "R", 550,  18, 8,"ddMMyy"),
    
	CNAB_600_DAYCOVAL(88,"cnab600RemessaDaycoval", "R",600,47, 94,"ddMMyyyy"),
    CNAB_500_REMESSA_DAYMAXX(89, "cnab500RemessaDaymaxx", "R",500,37, 94,"ddMMyy"),
    CNAB_444_REMESSA_AUTBANK(90, "cnab444RemessaAutbank", "R",444,108, 94,"ddMMyy"),
	CNAB_500_REMESSA_FINAXIS(92, "cnab500RemessaFinaxisC3", "R", 500,  108, 94, "ddMMyy"),
	CNAB_550_REMESSA(93, "cnab550RemessaFinaxis", "R", 550, 108, 110, "ddMMyy"),
	CNAB_500_REMESSA_CCB(94,"cnab500RemessaCcb","R",500,37, 94,"ddMMyy"),
	
	
	CNAB_400_COBRANCA_BANCO_BRASIL(4,"cnab400RetornoCobrancaBancoBrasil","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_BRADESCO(5,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_HSBC(6,"cnab400RetornoCobrancaHsbc","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_ITAU(7,"cnab400RetornoCobrancaItau","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_SAFRA(9,"cnab400RetornoCobrancaSafra","C",400,37, 94,"ddMMyy"),
//	CNAB_400_COBRANCA_FIBRA(11,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
	CNAB_240_COBRANCA_SANTANDER(25,"cnab240CobrancaSantander","C",240,37, 143,"ddMMyy"),
//	CNAB_240_COBRANCA_SICOOB(26,"cnab400RetornoCobrancaPadrao","C",240,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_BS2(27,"cnab400RetornoCobrancaBs2","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_STARBANK(32,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_SANTANDER(33,"cnab400RetornoSantader","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_MONEYPLUS(38,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_SOCIEDADE_CREDITO(45,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
//	CNAB_240_COBRANCA_ABC(46,"cnab400RetornoCobrancaPadrao","C",240,37, 94,"ddMMyy"),
//	CNAB_400_COBRANCA_BANCO_RENDIMENTO(51,"cnab400RetornoCobrancaPadrao","C",400,37, 94,"ddMMyy"),
	CNAB_450_COBRANCA_GENIAL(52,"Cobranca_450_Genial","C",450,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_BV(59, "cnab400CobrancaBV", "C", 400, 110, 400,"ddMMyy"),
	CNAB_400_COBRANCA_ID(61, "cnab400CobrancaId", "C", 400, 37, 94, "ddMMyy"),
    CNAB_240_COBRANCA_ID(62, "cnab240CobrancaId", "C", 240, 108, 110, "ddMMyy"),
	CNAB_400_COBRANCA_AUTBANK(91,"cnab400RetornoCobrancaAutbank","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_CITIBANK(95,"cnab400RetornoCobrancaCitibank","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_ABC(96,"cnab400RetornoCobrancaAbc","C",400,37, 94,"ddMMyy"),
	CNAB_400_COBRANCA_DELBANK(97,"cnab400RetornoCobrancaDelbank","C",400,37, 94,"ddMMyy")	
	;
	
	private Integer cdLayout;
	private String nmLayout;
	private String tipo;
	private Integer tamLayout;
	private Integer seuNumeroIni;
	private Integer dataGravacaoIni;
	private String pattern;
	
	LayoutEnum(Integer cdLayout, String nmLayout,String tipo, Integer tamLayout, Integer seuNumeroIni, 
			Integer dataGravacaoIni, String pattern){
		this.cdLayout = cdLayout;
		this.nmLayout = nmLayout;
		this.tipo = tipo;
		this.tamLayout = tamLayout;
		this.seuNumeroIni = seuNumeroIni;
		this.dataGravacaoIni = dataGravacaoIni;
		this.pattern = pattern;
	}
	
	public static LayoutEnum parse(Integer cdLayout) {
		
		for(LayoutEnum item : LayoutEnum.values()) {
			if(item.cdLayout == cdLayout) {
				return item;
			}
		}
		return null;
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
            case "001":
            	return CNAB_400_COBRANCA_BANCO_BRASIL;
            case "125":
            	return LayoutEnum.CNAB_450_COBRANCA_GENIAL;
            case "237":
            	return CNAB_400_COBRANCA_BRADESCO;
            case "574":
            	return CNAB_400_COBRANCA_STARBANK;
            case "246":
            	return CNAB_400_COBRANCA_ABC;
//            case "341":
//            	return CNAB_400_COBRANCA_ITAU;
            case "399":
            	return CNAB_400_COBRANCA_HSBC;
            case "422":
            	return CNAB_400_COBRANCA_SAFRA;
            case "218":
            	return CNAB_400_COBRANCA_BS2;
            case "745":
            	return CNAB_400_COBRANCA_CITIBANK;
            case "439":
            	return CNAB_400_COBRANCA_ID;
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

package br.com.m3Tech.geradorCnab.enuns;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum LayoutEnum {
	
	CNAB_REMESSA_444(16,"Remessa_444",444);
	
	private Integer cdLayout;
	private String nmLayout;
	private Integer tamLayout;
	
	LayoutEnum(Integer cdLayout, String nmLayout, Integer tamLayout){
		this.cdLayout = cdLayout;
		this.nmLayout = nmLayout;
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
	
	
	public static List<LayoutEnum> findAll(){
		List<LayoutEnum> retorno = new ArrayList<LayoutEnum>();
		
		for(LayoutEnum item : LayoutEnum.values()) {
			retorno.add(item);
		}
		
		return retorno;
		
	}

	
}

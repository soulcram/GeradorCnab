package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundoCobrancaDto {
	
	private Integer idFundo;
	private String codCobranca;
	private String nuBanco;
		
	
}

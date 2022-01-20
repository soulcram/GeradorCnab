package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BancoDto {
	
	private Integer idBanco;
	private String codigoBanco;
	private String nomeBanco;
	@Override
	public String toString() {
		return nomeBanco;
	}
	
	

}

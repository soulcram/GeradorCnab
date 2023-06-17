package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoTesteCustodiaDto {
	
	private String processo;
	private String status;
	private String mensagem;
	
	@Override
	public String toString() {
		return processo;
	}
	
	

}

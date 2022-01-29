package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoTesteDto {
	
	private String processo;
	private String info;
	private String resultado;
	
	@Override
	public String toString() {
		return processo;
	}
	
	

}

package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexadorDto {
	
	private Integer idIndexador;
	private Integer codIndexador;
	private String nomeIndexador;
	
	@Override
	public String toString() {
		return codIndexador + " - " + nomeIndexador;
	}

	
	
}

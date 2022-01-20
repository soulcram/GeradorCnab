package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoDto {
	
	private Integer idMovimento;
	private String cdOcorrencia;
	private String nmMovimento;
	
	@Override
	public String toString() {
		return cdOcorrencia + " - " + nmMovimento;
	}

}

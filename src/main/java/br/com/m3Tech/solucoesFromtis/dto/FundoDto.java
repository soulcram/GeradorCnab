package br.com.m3Tech.solucoesFromtis.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundoDto {
	
	private Integer idFundo;
	private String nomeFundo;
	private Integer layoutAquisicao;
	private LocalDate dataFundo;
	
	@Override
	public String toString() {
		return idFundo + " - " + nomeFundo;
	}

	
	
}

package br.com.m3Tech.geradorCnab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CedenteDto {
	
	private Integer idCedente;
	private String nomeCedente;
	private String docCedente;
	
	@Override
	public String toString() {
		return nomeCedente;
	}

}

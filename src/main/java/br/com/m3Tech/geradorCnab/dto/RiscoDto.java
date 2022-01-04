package br.com.m3Tech.geradorCnab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiscoDto {
	
	private Integer idRisco;
	private String codRisco;
	private String nomeRisco;
	
	@Override
	public String toString() {
		return nomeRisco;
	}

	
	
}

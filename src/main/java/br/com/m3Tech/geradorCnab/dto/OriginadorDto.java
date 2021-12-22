package br.com.m3Tech.geradorCnab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginadorDto {
	
	private Integer idOriginador;
	private String codigoOriginador;
	private String nomeOriginador;
	
	@Override
	public String toString() {
		return codigoOriginador;
	}
	
	

}

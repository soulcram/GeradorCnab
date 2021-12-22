package br.com.m3Tech.geradorCnab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SacadoDto {
	
	private Integer idSacado;
	private String nomeSacado;
	private String docSacado;
	private String endereco;
	private String cep;
	
	@Override
	public String toString() {
		return nomeSacado;
	}
	
	

}

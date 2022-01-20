package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoRecebivelDto {
	
	private Integer idTipoRecebivel;
	private String cdEspecie;
	private String nmTipoRecebivel;
	
	@Override
	public String toString() {
		return cdEspecie + " - " + nmTipoRecebivel;
	}

}

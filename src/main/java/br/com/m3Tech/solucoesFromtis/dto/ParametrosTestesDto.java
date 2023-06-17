package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosTestesDto {

	private FundoDto fundoExistente; 
	private String docCedenteNovo ;
	private String docSacadoNovo ;
	private String docFundoNovo ;
	private Integer idCedenteInserido;
	private Integer idSacadoInserido;
	private Integer idFundoInserido;
	private String cnpjCedenteExistente;
	private String cnpjSacadoExistente;
	private String cnpjFundoExistente;
	
	public ParametrosTestesDto (String docCedenteNovo , String docFundoNovo, String docSacadoNovo) {
		this.docCedenteNovo = docCedenteNovo;
		this.docFundoNovo = docFundoNovo;
		this.docSacadoNovo = docSacadoNovo;
	}


}

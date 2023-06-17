package br.com.m3Tech.solucoesFromtis.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoBaixas;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CnabBaixaOrgaoDto {
	
	private List<DadosArquivoBaixas> titulos;
	private Integer numSeqArquivo;
	
	public CnabBaixaOrgaoDto() {

		this.titulos = new ArrayList<DadosArquivoBaixas>();
	}

}

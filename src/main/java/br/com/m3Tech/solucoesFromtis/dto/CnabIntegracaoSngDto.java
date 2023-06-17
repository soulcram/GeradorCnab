package br.com.m3Tech.solucoesFromtis.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoSng;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CnabIntegracaoSngDto {
	
	private List<DadosArquivoSng> titulos;
	private Integer numSeqArquivo;
	
	public CnabIntegracaoSngDto() {

		this.titulos = new ArrayList<DadosArquivoSng>();
	}

}

package br.com.m3Tech.solucoesFromtis.beanio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cnab240TraillerArquivo {
	
	private String identBanco;
	private String numSeqRegistro;
	private String tipoRegistro;
	
	public Cnab240TraillerArquivo(String numSeqRegistro) {
		this.numSeqRegistro = numSeqRegistro;
	}

}

package br.com.m3Tech.solucoesFromtis.beanio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cnab240TraillerLote {
	
	private String identBanco;
	private String numSeqRegistro;
	private String tipoRegistro;
	
	public Cnab240TraillerLote(String numSeqRegistro) {
		this.numSeqRegistro = numSeqRegistro;
	}

}

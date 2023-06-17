package br.com.m3Tech.solucoesFromtis.beanio;

import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabDetail2L90 {
	
	private String mensagem;
	private String numSeqRegistro;
	
	public CnabDetail2L90(String mensagem,Integer numSeq) {
		
		this.mensagem = mensagem;
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}

}

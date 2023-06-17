package br.com.m3Tech.solucoesFromtis.beanio;

import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabDetail2L47E56 {
	

	private String especie;
	private String notaFiscal;	
	private String chaveNfe;
	private String numSeqRegistro;
	
	

	public CnabDetail2L47E56(TituloDto dto,Integer numSeq) {

		this.notaFiscal = ValorAleatorioUtil.getStringNumeros(11);
		this.especie = dto.getEspecie();
		this.chaveNfe = dto.getChaveNfe() != null ? dto.getChaveNfe() :  "31190600006388319890559240000000311006164587"; 
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
	}		

}

package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabHeaderCobranca {
	
	private String codigoCobranca;
	private String nomeOriginador;
	private String numeroBanco;
	private String nomeBanco;
	private LocalDate dataGravacao;
	private String numSeqRegistro;
	
	public CnabHeaderCobranca(CnabCobrancaDto dto) {
		this.codigoCobranca = dto.getCodigoCobranca();
		this.nomeOriginador = null;
		this.numeroBanco = dto.getBanco().getCodigoBanco();
		this.nomeBanco = dto.getBanco().getNomeBanco();
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqRegistro = "000001";
	}
}

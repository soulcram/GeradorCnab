package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cnab240HeaderArquivo {
	
	private String identBanco;
	private String numSeqRegistro;
	private String codCobranca;
	private String nomeOriginador;
	private String numeroBanco;
	private String nomeBanco;
	private Integer codigoRemessa;
	private LocalDate dataGravacao;
	private String tipoRegistro;
	
	public Cnab240HeaderArquivo(CnabCobrancaDto dto) {
		this.codCobranca = dto.getCodigoCobranca();
		this.nomeOriginador = null;
		this.codigoRemessa = 1;
		this.numeroBanco = dto.getBanco().getCodigoBanco();
		this.nomeBanco = dto.getBanco().getNomeBanco();
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqRegistro = "000001";
	}

}

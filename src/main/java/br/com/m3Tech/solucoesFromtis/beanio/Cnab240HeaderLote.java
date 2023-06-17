package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cnab240HeaderLote {
	
	private String identBanco;
	private String numSeqRegistro;
	private String tipoRegistro;
	private String tipoOperacao;
	private Integer tipoServico;
	private Integer numeroLoteRemessa;
	private String numeroInscricaoEmpresa;
	private LocalDate dataGravacao;
	private String nomeEmpresa;
	
	
	public Cnab240HeaderLote(CnabCobrancaDto dto) {
		this.numeroLoteRemessa = 1;
		this.tipoServico = 1;
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqRegistro = "000002";
	}
}

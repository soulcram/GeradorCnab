package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabHeader {
	
	private String codigoOriginador;
	private String nomeOriginador;
	private String numeroBanco;
	private String nomeBanco;
	private LocalDate dataGravacao;
	private String numSeqArquivo;
	private String numSeqRegistro;
	
	public CnabHeader(CnabDto dto) {
		this.codigoOriginador = dto.getOriginador().getCodigoOriginador();
		this.nomeOriginador = dto.getOriginador().getNomeOriginador();
		this.numeroBanco = dto.getBanco().getCodigoBanco();
		this.nomeBanco = dto.getBanco().getNomeBanco();
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqArquivo = StringUtils.getNumeroComZerosAEsquerda(dto.getNumSeqArquivo(), 7);
		this.numSeqRegistro = "000001";
	}
}

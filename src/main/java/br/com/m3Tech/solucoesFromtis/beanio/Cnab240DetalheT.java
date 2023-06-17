package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cnab240DetalheT {
	
	private String identBanco;
	private String bancoCobranca;
	private String agenciaCobranca;
	private String seuNumero;
	private String idCobranca;
	private String codOcorrencia;
	private LocalDate dataVencimento;
	private BigDecimal valorTitulo;
	private String numSeqRegistro;
	private String tipoRegistro;
	private String codigoSegmentoDetalhe;
	private String nuDocumento;
	

	public Cnab240DetalheT(TituloDto dto,Integer numSeq) {
		
		
		this.seuNumero = dto.getSeuNumero();
		this.idCobranca = dto.getIdCobranca();
		this.codOcorrencia = dto.getMovimento().getCdOcorrencia();
		this.dataVencimento = dto.getDataVencimento();
		this.valorTitulo = dto.getValorTitulo();
		this.bancoCobranca = dto.getNumBanco();
		this.agenciaCobranca = dto.getAgenciaCobranca();
		this.nuDocumento = dto.getNumeroDocumento();
		
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}

}

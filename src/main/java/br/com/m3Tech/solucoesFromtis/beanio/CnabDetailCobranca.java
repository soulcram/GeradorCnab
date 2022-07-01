package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabDetailCobranca {
	
	private String seuNumero;
	private String idCobranca;
	private String numDocumento;
	private String codOcorrencia;
	private LocalDate dataVencimento;
	private LocalDate dataLiquidacao;
	private BigDecimal valorTitulo;
	private BigDecimal valorPago;
	private String bancoCobranca;
	private String agenciaCobranca;
	private BigDecimal valorAbatimento;
	private BigDecimal valorDesconto;
	private BigDecimal valorJuros;
	private BigDecimal valorDespesas;
	private String numSeqRegistro;
	
	
	
	public CnabDetailCobranca(TituloDto dto,Integer numSeq, LayoutEnum layout) {
		
		
		this.seuNumero = dto.getSeuNumero();
		this.idCobranca = dto.getIdCobranca();
		this.numDocumento = dto.getNumeroDocumento(); 
		this.codOcorrencia = dto.getMovimento().getCdOcorrencia();
		this.dataVencimento = dto.getDataVencimento();
		this.dataLiquidacao = dto.getDataLiquidacao();
		this.valorTitulo = dto.getValorTitulo();
		this.valorPago = dto.getValorPago();
		this.bancoCobranca = dto.getNumBanco();
		this.valorAbatimento = dto.getValorAbatimento();
		this.valorDesconto = dto.getValorDesconto();
		this.valorJuros = dto.getValorJuros();
		this.valorDespesas = dto.getValorDespesas();
		
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}
	

}

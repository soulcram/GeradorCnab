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
public class Cnab240DetalheU {
	
	private String identBanco;
	private BigDecimal valorPago;
	private BigDecimal valorAbatimento;
	private BigDecimal valorDesconto;
	private BigDecimal valorJuros;
	private BigDecimal valorDespesasCobranca;
	private LocalDate dataCredito;
	private String numSeqRegistro;
	private String tipoRegistro;
	private String codigoSegmentoDetalhe;


	
	public Cnab240DetalheU(TituloDto dto,Integer numSeq) {
		
		

		this.valorPago = dto.getValorPago() == null ? dto.getValorTitulo() : dto.getValorPago();
		this.dataCredito = dto.getDataLiquidacao();
		this.valorAbatimento = dto.getValorAbatimento();
		this.valorDesconto = dto.getValorDesconto();
		this.valorJuros = dto.getValorJuros();
		this.valorDespesasCobranca = dto.getValorDespesas();	
		
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}
}

package br.com.m3Tech.geradorCnab.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TituloDto {

	private CedenteDto cedente;
	private SacadoDto sacado;
	private String numBanco;
	private LocalDate dataVencimento;
	private LocalDate dataLiquidacao;
	private MovimentoDto movimento;
	private String seuNumero;
	private String coobrigacao;
	private String nossoNumero;
	private String numeroDocumento;
	private String especie;
	private String termoCessao;
	private String chaveNfe;
	private BigDecimal valorPago;
	private BigDecimal valorTitulo;
	private BigDecimal valorAquisicao;
	private BigDecimal valorAbatimento;

	public TituloDto getCopy() {
		return new TituloDto(cedente, 
							 sacado, 
							 numBanco, 
							 dataVencimento, 
							 dataLiquidacao, 
							 movimento, 
							 seuNumero, 
							 coobrigacao, 
							 nossoNumero, 
							 numeroDocumento, 
							 especie, 
							 termoCessao, 
							 chaveNfe, 
							 valorPago, 
							 valorTitulo, 
							 valorAquisicao, 
							 valorAbatimento);
	}

}

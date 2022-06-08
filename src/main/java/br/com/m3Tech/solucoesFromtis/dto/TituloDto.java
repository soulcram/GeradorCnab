package br.com.m3Tech.solucoesFromtis.dto;

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
	private LocalDate dataCarencia;
	private LocalDate dataAquisicao;
	private MovimentoDto movimento;
	private IndexadorDto indexador;
	private RiscoDto risco;
	private String seuNumero;
	private String coobrigacao;
	private String nossoNumero;
	private String numeroDocumento;
	private String especie;
	private String termoCessao;
	private String chaveNfe;
	private String variacaoCambial;
	private String docOrigRecebivel;
	private String nomeOrigRecebivel;
	private BigDecimal valorPago;
	private BigDecimal valorTitulo;
	private BigDecimal valorAquisicao;
	private BigDecimal valorAbatimento;
	private BigDecimal taxaJurosIndexador;
	private BigDecimal taxaJuros;
	private String agenciaCobranca;
	private String idCobranca;
	private BigDecimal  valorDesconto;
	private BigDecimal  valorJuros;
	private BigDecimal  valorDespesas;

	public TituloDto getCopy() {
		return new TituloDto(cedente, 
							 sacado, 
							 numBanco, 
							 dataVencimento, 
							 dataLiquidacao, 
							 dataCarencia,
							 dataAquisicao,
							 movimento, 
							 indexador,
							 risco,
							 seuNumero, 
							 coobrigacao, 
							 nossoNumero, 
							 numeroDocumento, 
							 especie, 
							 termoCessao, 
							 chaveNfe, 
							 variacaoCambial,
							 docOrigRecebivel,
							 nomeOrigRecebivel,
							 valorPago, 
							 valorTitulo, 
							 valorAquisicao, 
							 valorAbatimento,
							 taxaJurosIndexador,
							 taxaJuros,
							 agenciaCobranca,
							 idCobranca,
							 valorDesconto,
							 valorJuros,
							 valorDespesas);
	}

}

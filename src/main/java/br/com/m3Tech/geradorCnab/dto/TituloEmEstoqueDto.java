package br.com.m3Tech.geradorCnab.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TituloEmEstoqueDto {
	
	private FundoDto fundo;
	private OriginadorDto originador;
	private BancoDto banco;
	
	private CedenteDto cedente;
	private SacadoDto sacado;
	private String numBanco;
	private LocalDate dataVencimento;
	private String seuNumero;
	private String coobrigacao;
	private String nossoNumero;
	private String numeroDocumento;
	private String especie;
	private String chaveNfe;
	private BigDecimal valorTitulo;
	private BigDecimal valorAquisicao;

}

package br.com.m3Tech.solucoesFromtis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosOperacaoParaAprovacaoDto {

	
	private String cnpjFundo;
	private String cpfCnpjCedente;
	private String nomeArquivo;
	private BigDecimal reembolso;
	private ContaCorrenteDto contaCorrente;


}

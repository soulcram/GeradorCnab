package br.com.m3Tech.solucoesFromtis.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperacaoAzumi implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String agencia;
	private String conta;
	private String banco;
	private String contaBancariaId;
	private String documento;
	private String empresaFavorecidaId;
	private BigDecimal valor;

	private TokenAzumi token;
}

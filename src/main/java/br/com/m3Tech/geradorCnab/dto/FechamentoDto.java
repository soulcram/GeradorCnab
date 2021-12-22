package br.com.m3Tech.geradorCnab.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FechamentoDto {
	
	private String nome;
	private Long quantidade;
	private BigDecimal valor;
	private BigDecimal subTotal;

}

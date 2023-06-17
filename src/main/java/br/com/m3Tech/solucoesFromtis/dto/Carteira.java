package br.com.m3Tech.solucoesFromtis.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class Carteira implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private LocalDate data;
	private BigDecimal valorPl;

}

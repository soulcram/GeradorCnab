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
public class Operacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Account;
	private BigDecimal Amount;
	private Integer SourceOperationType;
	private String Notes;
	private Receiver Receiver;

}

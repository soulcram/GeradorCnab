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
public class Receiver implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Name;
	private String Document;
	private String BankId;
	private String AgencyId;
	private String AccountNumber;
	private String AccountDigit;

}

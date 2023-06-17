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
public class OperacaoGenial implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Authentication Authentication;
    private Integer BankDestination;
    private Long AgencyDestination;
    private Long AccountDestination;
    private Integer AccountDigit;
    private String CPFCNPJ;
    private String PersonType;
    private String Name;
    private String Observation;
    private String AccountType;
    private BigDecimal Value;
}

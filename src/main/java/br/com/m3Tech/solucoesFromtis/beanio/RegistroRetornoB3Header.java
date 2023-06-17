package br.com.m3Tech.solucoesFromtis.beanio;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("HEADER")
public class RegistroRetornoB3Header {
	private String tipoRegistro;
	private String tipoArquivo;
	private String nomeCliente;
	private LocalDate dataProcessamentoArquivo;
	private String sequencia;
}

package br.com.m3Tech.solucoesFromtis.beanio;

import javax.persistence.DiscriminatorValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("TRAILER")
public class RegistroRetornoB3Trailer {
	private String tipoRegistro;
	private Integer quantidadeRegistros;
	private String sequencia;
}

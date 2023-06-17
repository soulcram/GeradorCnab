package br.com.m3Tech.solucoesFromtis.dto;

import java.io.Serializable;

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
public class TokenAzumi implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String estrutura;
	private String tipo;
	private String token;

}

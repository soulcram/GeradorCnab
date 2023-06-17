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
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String access_token;
	private String token_type;
	private String expires_in;

}

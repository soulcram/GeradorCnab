package br.com.m3Tech.solucoesFromtis.model;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosCadastrosAutomaticos {
	
	private String url;
	private String usuario;
	private String senha;
	private FundoDto fundo;
	private int repeticoes;

}

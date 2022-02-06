package br.com.m3Tech.solucoesFromtis.model;

import br.com.m3Tech.noSql.Crud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Base extends Crud<Base> {

	private String url;
	private String usuario;
	private String senha;
	private Boolean versaoMercado;

	@Override
	public String toString() {
		return url;
	}
}

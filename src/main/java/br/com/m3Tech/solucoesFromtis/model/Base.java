package br.com.m3Tech.solucoesFromtis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Base extends AbstractPersistable<Integer> {

	private String url;
	private String usuario;
	private String senha;
	private Boolean versaoMercado;

	@Override
	public String toString() {
		return url;
	}
}

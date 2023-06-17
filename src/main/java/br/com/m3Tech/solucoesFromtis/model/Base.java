package br.com.m3Tech.solucoesFromtis.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Base extends AbstractPersistable<Integer> {

	private Integer id;
	private String url;
	private String usuario;
	private String senha;
	private Boolean versaoMercado;

	@Override
	public String toString() {
		return url;
	}
}

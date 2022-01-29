package br.com.m3Tech.solucoesFromtis.config;

import org.springframework.stereotype.Component;

import br.com.m3Tech.solucoesFromtis.telas.Janela;

@Component
public class IniciarTelas {
	
	public void init() {
		new Janela();
	}

}

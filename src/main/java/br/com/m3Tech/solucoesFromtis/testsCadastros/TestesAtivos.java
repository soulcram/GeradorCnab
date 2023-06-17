package br.com.m3Tech.solucoesFromtis.testsCadastros;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TestesAtivos {
	
	private boolean testarFundo;
	private boolean testarCedente;
	private boolean testarSacado;
	
	public void selecionarTodos(boolean ativar) {
		this.testarCedente = ativar;
		this.testarFundo = ativar;
		this.testarSacado = ativar;
	}

}

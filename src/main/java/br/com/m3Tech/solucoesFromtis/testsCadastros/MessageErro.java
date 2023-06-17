package br.com.m3Tech.solucoesFromtis.testsCadastros;

public class MessageErro {
	
	public String getMessage(String metodo, String msgErro) {
		return this.getClass().getSimpleName() + "." + metodo + " : " + msgErro;
	}

}

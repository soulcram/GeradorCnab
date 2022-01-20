package br.com.m3Tech.solucoesFromtis.model;

public class Metodo {
	
	String nome;
	String conteudo;
	
	public Metodo(String s) {
		
		this.nome = getNomeDoMetodo(s);
		this.conteudo = s;
	}
	
	
	
    public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getConteudo() {
		return conteudo;
	}



	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}



	private String getNomeDoMetodo(String conteudo) {
    	String retorno = "";
    	
    	String[] split = conteudo.split("\\(");
    	
    	String[] split2 = split[0].trim().split(" ");
    	
    	retorno = split2[split2.length - 1];
    	
    	return retorno;
    }

}

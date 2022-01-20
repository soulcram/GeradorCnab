package br.com.m3Tech.solucoesFromtis.model;

public class MetodoAnalisado {

	String nome;
	String conteudoDestino;
	String conteudoSource;

	public MetodoAnalisado(Metodo destino, Metodo source) {

		this.nome = destino.getNome();
		this.conteudoDestino = destino.getConteudo();
		this.conteudoSource = source.getConteudo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudoDestino() {
		return conteudoDestino;
	}

	public void setConteudoDestino(String conteudoDestino) {
		this.conteudoDestino = conteudoDestino;
	}

	public String getConteudoSource() {
		return conteudoSource;
	}

	public void setConteudoSource(String conteudoSource) {
		this.conteudoSource = conteudoSource;
	}

}

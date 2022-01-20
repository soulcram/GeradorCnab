package br.com.m3Tech.solucoesFromtis.model;

public class ClasseAnalisada {
	
	String nomeClasse;
	
	String conteudoLegado;
	String conteudoMercado;
	
//	List<MetodoAnalisado> metodos;
	
	public String getConteudoLegado() {
		return conteudoLegado;
	}



	public void setConteudoLegado(String conteudoLegado) {
		this.conteudoLegado = conteudoLegado;
	}



	public String getConteudoMercado() {
		return conteudoMercado;
	}



	public void setConteudoMercado(String conteudoMercado) {
		this.conteudoMercado = conteudoMercado;
	}



	public ClasseAnalisada(){
//		metodos = Lists.newArrayList();
	}
	
	
	
    public String getNomeClasse() {
		return nomeClasse;
	}



	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}



//	public List<MetodoAnalisado> getMetodos() {
//		return metodos;
//	}
//
//
//	public void addMetodoAnalisado(MetodoAnalisado metodo) {
//		this.metodos.add(metodo);
//	}
}

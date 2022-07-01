package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.Random;

public class GeradorNomeFake {
	
	private final static String VOGAL = "aeiou";
	private final static String CONSOANTE_SIMPLES = "bcdfglmnprstvz";
	private final static String CONSOANTE_COMPLEXA = "xhkwyj";
	private final static String CONSOANTE_FINAL = "rsn";
	
	private final static String[] SEGMENTO = {"Mercado","Laticínios","Hamburgueria","Pastelaria","Lanchonete","Supermercado","Açougue",
											  "Fazenda","Restaurante"};
	
	private final static String LTDA = "ltda." ;
	
	private final static int TAM_NOME = 3;
	private final static int TAM_SOBRENOME= 4;
	
	public String gerarNomeCompleto() {
		return gerarNome() + " " + gerarSobrenome();
	}
	
	public String gerarNomeEmpresa() {
		
				
		return escolherSegmento() + " " + gerarEmpresa();
	}
	
	private String gerarEmpresa() {
		String empresa = "";
		empresa += gerarSilaba();
		empresa += escolherConsoanteFinal();
		empresa += gerarSilaba();
		empresa += escolherConsoanteComplexa();
		empresa += " ";
		empresa += LTDA;
				
		return empresa.substring(0,1).toUpperCase().concat(empresa.substring(1));
	}
	
	private String gerarNome() {
		String nome = "";
		
		Random r = new Random();
		int i = r.nextInt(CONSOANTE_SIMPLES.length());
		
		if(i % 2 == 0) {
			nome = gerarNomeSimples();
		}else {
			nome = gerarNomeComplexo();
		}
		
		return nome.substring(0,1).toUpperCase().concat(nome.substring(1));
	}
	
	private String gerarNomeSimples() {
		String nome = "";
		
		for(int i = 0; i < TAM_NOME; i++) {
			nome += gerarSilaba();
		}
		
		return nome.substring(0,1).toUpperCase().concat(nome.substring(1));
	}
	
	private String gerarNomeComplexo() {
		String nome = "";
		
		for(int i = 0; i < TAM_NOME; i++) {
			nome += gerarSilaba();
		}
		nome += escolherConsoanteFinal();
		return nome.substring(0,1).toUpperCase().concat(nome.substring(1));
	}
	
	private String gerarSobrenome() {
		String nome = "";
		
		for(int i = 0; i < TAM_SOBRENOME; i++) {
			nome += gerarSilaba();
		}
				
		return nome.substring(0,1).toUpperCase().concat(nome.substring(1));
	}
	
	private String escolherVogal() {
		Random r = new Random();
		int i = r.nextInt(VOGAL.length());
		
		return VOGAL.substring(i,i+1);
	}
	
	private String escolherConsoante() {
		Random r = new Random();
		int i = r.nextInt(CONSOANTE_SIMPLES.length());
		
		return CONSOANTE_SIMPLES.substring(i,i+1);
	}
	
	private String escolherConsoanteFinal() {
		Random r = new Random();
		int i = r.nextInt(CONSOANTE_FINAL.length());
		
		return CONSOANTE_FINAL.substring(i,i+1);
	}
	
	private String escolherConsoanteComplexa() {
		Random r = new Random();
		int i = r.nextInt(CONSOANTE_COMPLEXA.length());
		
		return CONSOANTE_COMPLEXA.substring(i,i+1);
	}
	
	private String escolherSegmento() {
		Random r = new Random();
		int i = r.nextInt(SEGMENTO.length);
		
		return SEGMENTO[i];
	}
	
	private String gerarSilaba() {
		return escolherConsoante() + escolherVogal();
	}

}

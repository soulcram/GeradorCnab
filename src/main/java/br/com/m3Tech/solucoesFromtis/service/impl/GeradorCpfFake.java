package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.Random;

public class GeradorCpfFake {
	
	private int[] prefixo = new int[11];
	
	private int[] validador1 = {10,9,8,7,6,5,4,3,2};
	private int[] validador2 = {11,10,9,8,7,6,5,4,3,2};
	
	public String gerarCpf() {
		
		gerarPrefixo();
		gerarPrimeiroDigito();
		gerarSegundoDigito();
		
		String num = "";
		
		for(int i = 0 ; i < prefixo.length ; i++) {
			num += String.valueOf(prefixo[i]);
		}
		
		return num;
	}
	
	private void gerarPrimeiroDigito() {
		
		int somatoria = 0;
		
		for(int i = 0 ; i < validador1.length; i++) {
			somatoria += prefixo[i] * validador1[i];
		}
				
		int digito1 = 0;
		
		if(11 - (somatoria % 11 ) > 9) {
			digito1 = somatoria % 11;
		}else {
			digito1 = 11 - (somatoria % 11 );
		}
		
		prefixo[9] = digito1;
	}
	private void gerarSegundoDigito() {
		
		
		int somatoria = 0;
		
		for(int i = 0 ; i < validador2.length; i++) {
			somatoria += prefixo[i] * validador2[i];
		}
				
		int digito2 = 0;
		
		if(11 - (somatoria % 11 ) > 9) {
			digito2 = somatoria % 11;
		}else {
			digito2 = 11 - (somatoria % 11 );
		}
		
		prefixo[10] = digito2;
	}
	
	private String gerarPrefixo() {
		
		String num = "";
		
		for(int i = 0 ; i < 9 ; i++) {
			prefixo[i] = escolherNumero();
		}
		
		return num;
	}
	
	private int escolherNumero() {
		Random r = new Random();
		return r.nextInt(9);
	}

}

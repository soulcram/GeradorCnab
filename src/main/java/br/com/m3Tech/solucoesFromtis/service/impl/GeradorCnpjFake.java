package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.Random;

public class GeradorCnpjFake {
	
	private int[] prefixo = new int[14];
	
	private int[] validador1 = {5,4,3,2,9,8,7,6,5,4,3,2};
	private int[] validador2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
	
	public String gerarCnpj() {
		
		prefixo[8] = 0;
		prefixo[9] = 0;
		prefixo[10] = 0;
		prefixo[11] = 1;
		
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
		
		int resto = somatoria % 11;
		
		if(resto  < 2) {
			digito1 = 0;
		}else {
			digito1 = 11 - resto;
		}
		
		prefixo[12] = digito1;
	}
	private void gerarSegundoDigito() {
		
		
		int somatoria = 0;
		
		for(int i = 0 ; i < validador2.length; i++) {
			somatoria += prefixo[i] * validador2[i];
		}
				
		int digito2 = 0;
		
		int resto = somatoria % 11;
		
		if(resto  < 2) {
			digito2 = 0;
		}else {
			digito2 = 11 - resto;
		}
		
		prefixo[13] = digito2;
	}
	
	private String gerarPrefixo() {
		
		String num = "";
		
		for(int i = 0 ; i < 8 ; i++) {
			prefixo[i] = escolherNumero();
		}
		
		return num;
	}
	
	private int escolherNumero() {
		Random r = new Random();
		return r.nextInt(9);
	}

}

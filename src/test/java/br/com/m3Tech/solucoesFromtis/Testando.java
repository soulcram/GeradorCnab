package br.com.m3Tech.solucoesFromtis;

import java.io.IOException;
import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.util.LocalDateUtils;

public class Testando {

	public static void main(String[] args) throws IOException {

		System.out.println("Maquina Home - Testando GIT");
		System.out.println("Maquina Sinqia - Testando GIT novo Teste");

		
	
		
		LocalDate parseDate = LocalDateUtils.parseDate("090422");
		
		System.out.println(parseDate);
		
	}

}

package br.com.m3Tech.geradorCnab;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

public class Testando {

	public static void main(String[] args) {

//		JOptionPane.showMessageDialog(null, "Teste","Ocorreu um erro", 0);
		
		LocalDate antiga = LocalDate.parse("2021-10-10");
		
		LocalDate hoje = LocalDate.now();

		System.out.println(antiga);
		System.out.println(hoje);
		
		System.out.println(ChronoUnit.DAYS.between(hoje, antiga));
		System.out.println(ChronoUnit.DAYS.between(antiga, hoje));
		
		
		

	}

}

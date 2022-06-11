package br.com.m3Tech.utils.solucoesFromtis;

import org.openqa.selenium.chrome.ChromeDriver;

import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testando {

	public static void main(String[] args) {
		

		GeradorCpfCnpjRgFake g = new GeradorCpfCnpjRgFake();
		
		for(int i = 0; i< 10; i++) {
			
			System.out.println(g.cnpj(true));
			
		}
		
	}

}

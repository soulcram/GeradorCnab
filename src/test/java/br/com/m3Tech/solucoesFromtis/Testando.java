//package br.com.m3Tech.solucoesFromtis;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//
//import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarSacado;
//import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCnpjFake;
//import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfFake;
//import br.com.m3Tech.solucoesFromtis.service.impl.GeradorNomeFake;
//
//public class Testando {
//	
//	
//	
//
//	@Test
//	public void teste() throws IOException, URISyntaxException {
//
//		System.out.println("Thread Running");
//		System.out.println("Versão 2021-10-02");
//		URL resource = CadastrarSacado.class.getClassLoader().getResource("chromedriver.exe");
//		Path p = Paths.get(resource.toURI());
//		File file = p.toFile();
//		System.out.println(file.getAbsolutePath());
//		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//		WebDriver driver = new ChromeDriver();
//		Actions action = new Actions(driver);
//		
//		driver.get("http://localhost:8080/fidcCustodia/login.xhtml");
//
////		driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("soulcram");
////		driver.findElement(By.xpath("//input[@id='j-password']")).sendKeys("p4r4tud0");
////
////		driver.findElement(By.xpath("//input[@id='j-password']")).submit();
//		
////		testeGeradorCnpj();
//		
////		testeGeradorCpf();
//		
////		testeGeradorNome();
//		
////		testeGeradorNomeEmpresa();
//	}
//	
//	private static void testeGeradorCnpj() {
//		GeradorCnpjFake g = new GeradorCnpjFake();
//		for(int i = 0; i < 10; i++) {
//			System.out.println(g.gerarCnpj());
//		}
//	}
//	
//	private static void testeGeradorCpf() {
//		GeradorCpfFake g = new GeradorCpfFake();
//		for(int i = 0; i < 100; i++) {
//			System.out.println(g.gerarCpf());
//		}
//	}
//	
//	private static void testeGeradorNome() {
//		GeradorNomeFake g = new GeradorNomeFake();
//		for(int i = 0; i < 100; i++) {
//			System.out.println(g.gerarNomeCompleto());
//		}
//	}
//	
//	private static void testeGeradorNomeEmpresa() {
//		GeradorNomeFake g = new GeradorNomeFake();
//		for(int i = 0; i < 100; i++) {
//			System.out.println(g.gerarNomeEmpresa());
//		}
//	}
//
//}

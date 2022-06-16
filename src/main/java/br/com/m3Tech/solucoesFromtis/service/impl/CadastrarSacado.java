package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import br.com.m3Tech.solucoesFromtis.model.CadastroSacado;
import br.com.m3Tech.solucoesFromtis.service.ICadastrarSacado;

public class CadastrarSacado implements ICadastrarSacado {

	@Override
	public void cadastrar(CadastroSacado cadastroSacado) {
		try {
			System.out.println("Thread Running");
			System.out.println("Versão 2021-10-02");
			URL resource = CadastrarSacado.class.getClassLoader().getResource("chromedriver.exe");
			Path p = Paths.get(resource.toURI());
			File file = p.toFile();
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			WebDriver driver = new ChromeDriver();
			Actions action = new Actions(driver);
			
			driver.get(cadastroSacado.getUrl());

			driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(cadastroSacado.getUsuario());
			driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(cadastroSacado.getSenha());

			driver.findElement(By.xpath("//input[@id='input-password']")).submit();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}

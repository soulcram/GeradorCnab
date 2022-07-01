package br.com.m3Tech.solucoesFromtis.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarPdd implements ICadastroAutomatizado {

	@Override
	public String executar(ParametrosCadastrosAutomaticos parametros) {
		
		GeradorNomeFake nomeFake = new GeradorNomeFake();
		GeradorCpfCnpjRgFake gerarDoc = new GeradorCpfCnpjRgFake();
		
			WebDriverManager.chromedriver().setup();
			
			ChromeDriver driver = new ChromeDriver();
			
			driver.get(parametros.getUrl() + "/fidcCustodia/login.xhtml");
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(parametros.getUsuario());
			driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys(parametros.getSenha());
	
			driver.findElement(By.xpath("//input[@id='j_password']")).submit();
			
			driver.get(parametros.getUrl() + "/fidcCustodia/pages/consultaPdd.xhtml");
			
//			WebElement tabela = driver.findElement(By.id("j_idt209:tabelaPdd"));
//			WebElement linha = tabela.findElement(By.xpath("//*[contains(text(), 'PddFaixaUnica')]"));
//			linha.findElement(By.xpath("//*[contains(text(), 'Excluir')]")).click();
			
			driver.findElement(By.xpath("//*[contains(text(), 'Novo')]")).click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			driver.findElement(By.id("j_idt209:numeroDiasMinimo")).sendKeys("0");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:numeroDiasMaximo"))).sendKeys("15");; 
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:percentualApropriado"))).sendKeys("10");;
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Adicionar')]"))).click();;
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:numeroDiasMaximo"))).sendKeys("30");; 
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:percentualApropriado"))).sendKeys("20");;
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Adicionar')]"))).click();;
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:numeroDiasMaximo"))).sendKeys("45");; 
			wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt209:percentualApropriado"))).sendKeys("30");;
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Adicionar')]"))).click();;
							
			driver.findElement(By.xpath("//*[contains(text(), 'Salvar')]")).click();
			
			driver.close();
					
			return "";
	}

}

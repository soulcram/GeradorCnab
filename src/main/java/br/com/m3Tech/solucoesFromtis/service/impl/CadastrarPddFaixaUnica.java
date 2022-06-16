package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarPddFaixaUnica implements ICadastroAutomatizado {

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

			
			driver.findElement(By.id("j_idt209:nomePdd")).sendKeys("PddFaixaUnica");
			
			driver.findElement(By.id("j_idt209:j_idt228")).click();
				
			driver.findElement(By.xpath("//*[contains(text(), 'Salvar')]")).click();
			
			driver.close();
					
			return "";
	}

}

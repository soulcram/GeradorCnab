package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarPddFaixaUnica implements ICadastroAutomatizado {

	@Override
	public String executar(ParametrosCadastrosAutomaticos parametros) {
		
		GeradorNomeFake nomeFake = new GeradorNomeFake();
		GeradorCpfCnpjRgFake gerarDoc = new GeradorCpfCnpjRgFake();
		
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
	    	options.addArguments("--remote-allow-origins=*");
			ChromeDriver driver = new ChromeDriver(options);
			
			String url = parametros.getUrl()+ (StringUtils.isEmpty(parametros.getContextoCustodia()) ? "" : parametros.getContextoCustodia());
			
			driver.get(url + "/login.xhtml");
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys(parametros.getUsuario());
			driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys(parametros.getSenha());
	
			driver.findElement(By.xpath("//input[@id='j_password']")).submit();
			
			driver.get(url + "/pages/consultaPdd.xhtml");
			
//			WebElement tabela = driver.findElement(By.id("j_idt209:tabelaPdd"));
//			WebElement linha = tabela.findElement(By.xpath("//*[contains(text(), 'PddFaixaUnica')]"));
//			linha.findElement(By.xpath("//*[contains(text(), 'Excluir')]")).click();
			
			driver.findElement(By.xpath("//*[contains(text(), 'Novo')]")).click();

			
			driver.findElement(By.id("form:nomePdd")).sendKeys("PddFaixaUnica");
			
			driver.findElement(By.id("form:pddFaixaUnica")).click();
			
//			driver.findElement(By.id("form:adicionarPdd")).click();
				
			driver.findElement(By.xpath("//*[contains(text(), 'Salvar')]")).click();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.quit();
					
			return "";
	}

}

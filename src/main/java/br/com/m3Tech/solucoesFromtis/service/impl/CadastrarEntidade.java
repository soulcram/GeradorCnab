package br.com.m3Tech.solucoesFromtis.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarEntidade implements ICadastroAutomatizado {

	@Override
	public void cadastrar(ParametrosCadastrosAutomaticos parametros) {
		
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
			
			driver.get(parametros.getUrl() + "/fidcCustodia/pages/consultaPessoa.xhtml");
			
			driver.findElement(By.xpath("//*[contains(text(), 'Novo')]")).click();
			
			
			
			
//			Select comboFundo = new Select(driver.findElement(By.id("form:fundo")));
//			comboFundo.selectByVisibleText(parametros.getFundo().getNomeFundo()); 
						
			String nomeEntidade = nomeFake.gerarNomeCompleto();
			
			driver.findElement(By.id("form:nome")).sendKeys(nomeEntidade);
			
			driver.findElement(By.id("form:cnpj")).sendKeys(gerarDoc.cnpj(true));
			
			driver.findElement(By.id("form:email")).sendKeys(nomeEntidade.replaceAll(" ", "") + "@gmail.com");
			
			driver.findElement(By.id("form:logradouro")).sendKeys("Rua: " + nomeFake.gerarNomeCompleto());
			
			driver.findElement(By.id("form:numero")).sendKeys(ValorAleatorioUtil.getValorNumerico(1000).toString());
			
			driver.findElement(By.id("form:cep")).sendKeys(ValorAleatorioUtil.getValorNumerico(11111111,99999999).toString());
			
			driver.findElement(By.id("form:bairro")).sendKeys("Parque " + nomeFake.gerarNomeCompleto());

			driver.findElement(By.id("form:cidade")).sendKeys("São Paulo");
			
			Select comboEstado = new Select(driver.findElement(By.id("form:estado")));
			comboEstado.selectByVisibleText("São Paulo"); 
			
			driver.findElement(By.id("form:telefone")).sendKeys(ValorAleatorioUtil.getStringNumeros(11));
			
			//Aba papeis desempenhados
			driver.findElement(By.xpath("//*[contains(text(), 'Papéis Desempenhados')]")).click();
			
			driver.findElement(By.id("form:administrador")).click();
			driver.findElement(By.id("form:gestor")).click();
			driver.findElement(By.id("form:custodiante")).click();
			driver.findElement(By.id("form:auditor")).click();
			driver.findElement(By.id("form:agencia")).click();
			driver.findElement(By.id("form:controladorAtivo")).click();
			driver.findElement(By.id("form:controladorPassivo")).click();
			driver.findElement(By.id("form:escriturador")).click();
			driver.findElement(By.id("form:originador")).click();
			driver.findElement(By.id("form:servico")).click();
			driver.findElement(By.id("form:terceiro")).click();
			
			//aba Conta Corrente Consultoria
			driver.findElement(By.xpath("//*[contains(text(), 'Conta Corrente Consultoria')]")).click();
			
			Select comboBanco = new Select(driver.findElement(By.id("form:banco")));
			comboBanco.selectByVisibleText("237 - Banco Bradesco S.A.");
			
			driver.findElement(By.id("form:ag")).sendKeys(ValorAleatorioUtil.getValorNumerico(9999).toString());
			
			driver.findElement(By.id("form:cc")).sendKeys(ValorAleatorioUtil.getValorNumerico(999999).toString());
			
			driver.findElement(By.id("form:descricao")).sendKeys("Conta padrao");

			driver.findElement(By.xpath("//*[@id='form:j_idt285']")).click();
			
			
			//aba Representantes
			driver.findElement(By.xpath("//*[contains(text(), 'Representantes')]")).click();
			
			String nomeRepresentante = nomeFake.gerarNomeCompleto();
			
			driver.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentante);
			
			driver.findElement(By.id("form:emailRepresentante")).sendKeys(nomeRepresentante.replaceAll(" ", "") + "@gmail.com");
			
			driver.findElement(By.id("form:representanteCnpj")).sendKeys(gerarDoc.cnpj(true));
			
			driver.findElement(By.id("form:telefoneRepresentante")).sendKeys(ValorAleatorioUtil.getStringNumeros(11));
			
			driver.findElement(By.xpath("//*[@id='form:j_idt335']")).click();
			//*[@id='form:j_idt335']
			
			
			
			//aba Partes Relacionadas
			driver.findElement(By.xpath("//*[contains(text(), 'Partes Relacionadas')]")).click();
			
			driver.findElement(By.id("form:nomeParteRelacionada")).sendKeys(nomeFake.gerarNomeCompleto());
			
			driver.findElement(By.id("form:cnpjParteRelacionada")).sendKeys(gerarDoc.cnpj(true));
			
			driver.findElement(By.xpath("//*[@id='form:j_idt403']")).click();
			//*[@id='form:j_idt403']
			
			
			
			driver.findElement(By.xpath("//*[contains(text(), 'Salvar')]")).click();
			
			driver.close();
					
	}

}

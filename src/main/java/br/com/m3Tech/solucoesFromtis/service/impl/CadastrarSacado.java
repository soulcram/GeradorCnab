package br.com.m3Tech.solucoesFromtis.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarSacado implements ICadastroAutomatizado {

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
			
			driver.get(url + "/pages/consultaSacado.xhtml");
			
			driver.findElement(By.xpath("//*[contains(text(), 'Novo')]")).click();
			
			Select comboFundo = new Select(driver.findElement(By.id("form:fundo")));
			comboFundo.selectByVisibleText(parametros.getFundo().getNomeFundo()); 
						
			String nomeSacado = nomeFake.gerarNomeCompleto();
			
			driver.findElement(By.id("form:nome")).sendKeys(nomeSacado);
			
			driver.findElement(By.id("form:cnpj")).sendKeys(gerarDoc.cnpj(true));
			
			driver.findElement(By.id("form:dtIniRelacInputDate")).sendKeys(LocalDate.now().minusWeeks(2L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			driver.findElement(By.id("form:fatAnual")).sendKeys(ValorAleatorioUtil.getValorDecimal(1000, 10000).toString());
			
			driver.findElement(By.id("form:conglomeradoEcon")).sendKeys(ValorAleatorioUtil.getValorNumerico(1000).toString());
			
			Select comboPorte = new Select(driver.findElement(By.id("form:porte")));
			comboPorte.selectByVisibleText("Micro"); 
			
			Select comboClassRisco = new Select(driver.findElement(By.id("form:classRisco")));
			comboClassRisco.selectByVisibleText("Classificação de risco A"); 
			
			Select comboTipoSociedade = new Select(driver.findElement(By.id("form:tipoSociedade")));
			comboTipoSociedade.selectByVisibleText("LTDA"); 
			
			driver.findElement(By.id("form:inscricaoEstadual")).sendKeys(ValorAleatorioUtil.getStringNumeros(4));
			
			driver.findElement(By.id("form:logradouro")).sendKeys("Rua: " + nomeFake.gerarNomeCompleto());
			
			driver.findElement(By.id("form:numero")).sendKeys(ValorAleatorioUtil.getValorNumerico(1000).toString());
			
			driver.findElement(By.id("form:cep")).sendKeys(ValorAleatorioUtil.getValorNumerico(11111111,99999999).toString());
			
			driver.findElement(By.id("form:bairro")).sendKeys("Parque " + nomeFake.gerarNomeCompleto());

			driver.findElement(By.id("form:cidade")).sendKeys("São Paulo");
			
			Select comboEstado = new Select(driver.findElement(By.id("form:uf")));
			comboEstado.selectByVisibleText("São Paulo"); 
			
			driver.findElement(By.id("form:email")).sendKeys(nomeSacado.replaceAll(" ", "") + "@gmail.com");
			
			driver.findElement(By.id("form:telefone")).sendKeys(ValorAleatorioUtil.getStringNumeros(11));
			
			driver.findElement(By.id("form:quemAssinaCessao:0")).click();
			
			driver.findElement(By.id("form:assinaSacado:1")).click();
			
			driver.findElement(By.id("form:assinaPorEndossoSacado:1")).click();
			
			driver.findElement(By.id("form:assinaTermoCessaoSacado:1")).click();
			
			driver.findElement(By.id("form:emiteDuplicataSacado:1")).click();
			
			String nomeRepresentante = nomeFake.gerarNomeCompleto();
			
			driver.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentante);
			
			driver.findElement(By.id("form:emailRepresentante")).sendKeys(nomeRepresentante.replaceAll(" ", "") + "@gmail.com");
			
			driver.findElement(By.id("form:representanteCnpj")).sendKeys(gerarDoc.cnpj(true));
			
			driver.findElement(By.id("form:telefoneRepresentante")).sendKeys(ValorAleatorioUtil.getStringNumeros(11));
			
			driver.findElement(By.xpath("//*[contains(text(), 'Adicionar')]")).click();
			
			driver.findElement(By.xpath("//*[contains(text(), 'Salvar')]")).click();
			
			driver.close();
			
			return nomeSacado;
					
	}

}

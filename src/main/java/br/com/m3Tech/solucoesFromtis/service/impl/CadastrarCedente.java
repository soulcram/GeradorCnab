package br.com.m3Tech.solucoesFromtis.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastrarCedente implements ICadastroAutomatizado {

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
			
			String docCedente = "";
			
			for(int i = 0; i < parametros.getRepeticoes(); i++) {
			
				driver.get(url + "/pages/consultaCedente.xhtml");
				
				driver.findElement(By.xpath("//*[contains(text(), 'Novo')]")).click();
				
				
				WebElement element = driver.findElement(By.id("form:fundo"));
				
				Select comboFundo = new Select(element);
				comboFundo.selectByVisibleText(parametros.getFundo().getNomeFundo()); 
							
				String nomeCedente = nomeFake.gerarNomeCompleto();
				
				driver.findElement(By.id("form:nome")).sendKeys(nomeCedente);
				
				//Seleciona o Tipo de Pessoa
				driver.findElement(By.id("form:tipoPessoa:1")).click();
				
				docCedente = gerarDoc.cnpj(true);
				
				driver.findElement(By.id("form:cpfCnpj")).sendKeys(docCedente);
				
				//Seleciona o Tipo Inscrição Estadual ISENTO
		        driver.findElement(By.id("form:tipoInscricaoEstadualIsento:1")).click();
	
		        //Selecionando Ramo de Atividade
		        element = driver.findElement(By.id("form:ramoAtiviade"));
		        Select ramoAtividade = new Select(element);
		        ramoAtividade.selectByVisibleText("FINANCEIRO");
	
		        //Preenchimento e-mail cedente
		        driver.findElement(By.id("form:email")).sendKeys(nomeCedente.replaceAll(" ", "") + "@gmail.com");
	
		        //Selecionando Porte Médio
		        element = driver.findElement(By.id("form:porte"));
		        Select porte = new Select(element);
		        porte.selectByVisibleText("Médio");
	
		        //Selecionando Tipo Sociedade Instituição Financeira
		        element = driver.findElement(By.id("form:tipoSociedade"));
		        Select tipoSociedade = new Select(element);
		        tipoSociedade.selectByVisibleText("INSTITUIÇÃO FINANCEIRA");
	
		        //Selecionando Classe de risco
		        element = driver.findElement(By.id("form:classRisco"));
		        Select classRisco = new Select(element);
		        classRisco.selectByVisibleText("Classificação de risco A");
	
		        //Preenchimento Inicio de Relacionamento
		        driver.findElement(By.id("form:iniRelacInputDate")).sendKeys(LocalDate.now().minusWeeks(2L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	
		        //Seleciona SIM na Autorização
		        driver.findElement(By.id("form:autorizacao:1")).click();
	
		        //Preenchimento do Logradouro
		        driver.findElement(By.id("form:logradouro")).sendKeys("RUA TESTE");
	
		        //Preenchimento numero
		        driver.findElement(By.id("form:numero")).sendKeys("422");
	
		        //Preenchimento complemento
		        driver.findElement(By.id("form:complemento")).sendKeys("11 ANDAR");
	
		        //Preenchimento do CEP
		        driver.findElement(By.id("form:cep")).sendKeys("05335-050");
	
		        //Preenchimento do Bairro
		        driver.findElement(By.id("form:bairro")).sendKeys("Jaguaré");
	
		        //Preenchimento da Cidade
		        driver.findElement(By.id("form:cidade")).sendKeys("São Paulo");
	
		        //Selecionando sigla da UF - SP
		        element = driver.findElement(By.id("form:estado"));
		        Select uf = new Select(element);
		        uf.selectByVisibleText("São Paulo");
	
		        //Selecionando o banco da agencia e conta padrão
		        element = driver.findElement(By.id("form:banco"));
		        Select codBanco = new Select(element);
		        codBanco.selectByVisibleText("098 - BANCO DESCONHECIDO");
	
		        //Preenchimento da agencia
		        driver.findElement(By.id("form:agencia")).sendKeys("9854");
	
	//	        //Preenchimento do digito da agencia
	//	        driver.findElement(By.id("form:digitoAgencia")).sendKeys("");
	
		        //Preenchimento da conta
		        driver.findElement(By.id("form:conta")).sendKeys("685734");
	
		        //Preenchimento do digito da conta
		        driver.findElement(By.id("form:digitoConta")).sendKeys("4");
	
		        //Preenchimento descrição da conta
		        driver.findElement(By.id("form:descricao")).sendKeys("BANCO CEDENTE");
	
		        //Seleciona SIM em Ativado
		        driver.findElement(By.id("form:ativado:1")).click();
	
		        //Seleciona SIM em Padrão
		        driver.findElement(By.id("form:padrao:1")).click();
	
		        //Clica no botão Adicionar
		        driver.findElement(By.id("form:adicionarContaCorrente")).click();	        
		        
		        String nomeRepresentante = nomeFake.gerarNomeCompleto();
	
		        //Preenchimento Nome Representante
		        driver.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentante);
	
		        //Preenchimento E-mail do Representante
		        driver.findElement(By.id("form:emailRepresentante")).sendKeys(nomeRepresentante.replaceAll(" ", "") + "@gmail.com");
	
		        //Seleciona Tipo de Pessoa CNPJ
		        driver.findElement(By.id("form:representanteTipoPessoa:1")).click();
	
		        //Preenchimento CNPJ do Representante
		        driver.findElement(By.id("form:representanteCnpj")).sendKeys(gerarDoc.cnpj(true));
		        
		        driver.findElement(By.id("form:telefoneRepresentante")).sendKeys(ValorAleatorioUtil.getStringNumeros(11));
	
		        driver.findElement(By.id("form:adicionarRepresentante")).click();
	
		        driver.findElement(By.linkText("Salvar")).click();
		        
		        try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        
			}
			
			driver.quit();
			
			return docCedente;
					
	}


}

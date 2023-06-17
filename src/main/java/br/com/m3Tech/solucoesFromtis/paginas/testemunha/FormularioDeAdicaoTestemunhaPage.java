package br.com.m3Tech.solucoesFromtis.paginas.testemunha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;


public class FormularioDeAdicaoTestemunhaPage extends MessageErro {

    private WebDriver navegador;

    public FormularioDeAdicaoTestemunhaPage (WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoTestemunhaPage selecionarFundoTestemunha (String fundoTestemunha) throws BussinesException{
    	try {
            WebElement element = navegador.findElement(By.id("form:fundo"));
            Select fundo = new Select(element);
            fundo.selectByVisibleText(fundoTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarFundoTestemunha", e.getMessage()));
		}

        return this;
    }

    public FormularioDeAdicaoTestemunhaPage preencherNomeTestemunha (String nomeTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:nome")).sendKeys(nomeTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNomeTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherCpfTestemunha (String cpfTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:cpf")).sendKeys(cpfTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCpfTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherEmailTestemunha (String emailTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:email")).sendKeys(emailTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherEmailTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherLogradouroTestemunha (String logradouroTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:logradouro")).sendKeys(logradouroTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherLogradouroTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherNumeroLogradouroTestemunha (String numerologradouroTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:numero")).sendKeys(numerologradouroTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroLogradouroTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherCepTestemunha(String cepTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:cep")).sendKeys(cepTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCepTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherBairroTestemunha (String bairroTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:bairro")).sendKeys(bairroTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherBairroTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage preencherCidadeTestemunha (String cidadeTestemunha) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:cidade")).sendKeys(cidadeTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCidadeTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage selecionarEstadoTestemunha (String estadoTestemunha) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:uf"));
            Select uTestemunha = new Select(element);
            uTestemunha.selectByVisibleText(estadoTestemunha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarEstadoTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoTestemunhaPage selecionarSituacaoTestemunha () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:situacao:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarSituacaoTestemunha", e.getMessage()));
		}
        
        return this;
    }
    public ConsultaTestemunhaPage submeterFoormularioDeAdicaComSucesso () throws BussinesException{
    	try {
    		 navegador.findElement(By.linkText("Salvar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFoormularioDeAdicaComSucesso", e.getMessage()));
		}
       
        return new ConsultaTestemunhaPage(navegador);
    }
    public ConsultaTestemunhaPage submeterFoormularioDeAdicaComErro () throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Salvar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFoormularioDeAdicaComErro", e.getMessage()));
		}
        
        return new ConsultaTestemunhaPage(navegador);
    }

}

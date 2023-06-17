package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaContasCorrenteFundoPage extends MessageErro {

    private WebDriver navegador;

    public FormularioAbaContasCorrenteFundoPage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaContasCorrenteFundoPage selecionarCodigoBancoContaCorrente(String codigoBancoContaCorrenteFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:banco"));
            Select codBanco = new Select(element);
            codBanco.selectByVisibleText(codigoBancoContaCorrenteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarCodigoBancoContaCorrente", e.getMessage()));
		}
        
        return this;
    }

    public FormularioAbaContasCorrenteFundoPage preencherNumeroAgenciaContaCorrente(String agenciaContaCorrenteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:agencia")).sendKeys(agenciaContaCorrenteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroAgenciaContaCorrente", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaContasCorrenteFundoPage preencherDigitoAgenciaContaCorrente(String digitoAgenciaContaCorrenteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:digitoAgencia")).sendKeys(digitoAgenciaContaCorrenteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherDigitoAgenciaContaCorrente", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaContasCorrenteFundoPage preencherNumeroContaCorrente(String numeroContaCorrenteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:conta")).sendKeys(numeroContaCorrenteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroContaCorrente", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaContasCorrenteFundoPage preencherDescricaoContaCorrente(String descricaoContaCorrenteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:descricao")).sendKeys(descricaoContaCorrenteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherDescricaoContaCorrente", e.getMessage()));
		}
        
        return this;
    }
    public ConsultaFundoPage submeterFormulariodeAdicaoComErro ()  throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Salvar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormulariodeAdicaoComErro", e.getMessage()));
		}
        
        return new ConsultaFundoPage(navegador);
    }
    public ConsultaFundoPage submeterFormulariodeAdicaoComSucesso () throws BussinesException {
    	try {
    		navegador.findElement(By.linkText("Salvar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormulariodeAdicaoComSucesso", e.getMessage()));
		}
        
        return new ConsultaFundoPage(navegador);
    }

}

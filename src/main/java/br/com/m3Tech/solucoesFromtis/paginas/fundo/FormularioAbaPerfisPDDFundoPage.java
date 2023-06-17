package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaPerfisPDDFundoPage extends MessageErro {

    private WebDriver navegador;

    public FormularioAbaPerfisPDDFundoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioAbaPerfisPDDFundoPage selecionarPerfilPdd(String perfilPddFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:perfilPdd"));
            Select perfilPDD = new Select(element);
            perfilPDD.selectByIndex(1);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarPerfilPdd", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaPerfisPDDFundoPage adicionarPerfilPdd() throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarPerfilPdd", e.getMessage()));
		}
        
        return this;
    }

    public FormularioAbaPrecificacaoFundoPage submeterFormularioAbaPrecificaçãoFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Precificação')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaPrecificaçãoFundo", e.getMessage()));
		}
        
        return new FormularioAbaPrecificacaoFundoPage(navegador);
    }
}

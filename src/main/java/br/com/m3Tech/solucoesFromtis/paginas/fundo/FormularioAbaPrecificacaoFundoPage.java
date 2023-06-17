package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaPrecificacaoFundoPage extends MessageErro {

    private WebDriver navegador;

    public FormularioAbaPrecificacaoFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaPrecificacaoFundoPage selecionarModeloPrecificacao(String modeloPrecificacaoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:metodoPrecificacao"));
            Select metodoPrecificacao = new Select(element);
            metodoPrecificacao.selectByVisibleText(modeloPrecificacaoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarModeloPrecificacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaPrecificacaoFundoPage selecionarAplicaSePrecificacao(String aplicarPrecificacaoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:aplicarPrecificacao"));
            Select todaCarteira = new Select(element);
            todaCarteira.selectByVisibleText(aplicarPrecificacaoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarAplicaSePrecificacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaCarteiraSistemaDeAtivosFundoPage submeterCarteiraSistemaDeAtivosFundo () throws BussinesException{
    	try {
    		 navegador.findElement(By.xpath("//*[contains(text(), 'Carteira Sistema de Ativos')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterCarteiraSistemaDeAtivosFundo", e.getMessage()));
		}
       
        return new FormularioAbaCarteiraSistemaDeAtivosFundoPage(navegador);
    }


}

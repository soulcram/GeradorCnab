package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;


public class FormularioAbaCarteiraSistemaDeAtivosFundoPage extends MessageErro {

    private WebDriver navegador;

    public FormularioAbaCarteiraSistemaDeAtivosFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Actions
    public FormularioAbaCarteiraSistemaDeAtivosFundoPage selecionarTipoCotaSac(String tipoCotaSacFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:tipoCotaSac"));
            Select tipoCota = new Select(element);
            tipoCota.selectByVisibleText(tipoCotaSacFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarTipoCotaSac", "Erro ao selecionar Tipo Cota Sac ."));
		}
        
        return this;
    }
    public FormularioAbaCarteiraSistemaDeAtivosFundoPage preencherNumeroCodigoCarteiraSac (String numeroCodigoCarteiraSacFundo) throws BussinesException{
    	try {
    		 navegador.findElement(By.id("form:codigoCarteiraSac")).sendKeys(numeroCodigoCarteiraSacFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroCodigoCarteiraSac", "Erro ao preencher Numero Codigo Carteira Sac ."));
		}
       
        return this;
    }
    public FormularioAbaCarteiraSistemaDeAtivosFundoPage adicionarTipoCota () throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarTipoCota", "Erro ao adicionar Tipo Cota ."));
		}
        
        return this;
    }
    public FormularioAbaAtivosFundoPage submeterFormularioAbaAtivosFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:aba7:header:inactive")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaAtivosFundo", "Erro ao submeter Formulario Aba Ativos Fundo ."));
		}
        
        return new FormularioAbaAtivosFundoPage(navegador);
    }


}

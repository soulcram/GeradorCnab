package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaOriginadoresFundoPage extends MessageErro {

    private WebDriver navegador;

    public  FormularioAbaOriginadoresFundoPage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaOriginadoresFundoPage selecionarOriginador () throws BussinesException{
    	
    	try {
    		
    		WebElement element = navegador.findElement(By.id("form:idOriginador"));
            Select originador = new Select(element);
            originador.selectByIndex(1);

		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarOriginador", "Erro ao selecionar o originador."));
		}
		
		return this;
        
    }
    public FormularioAbaOriginadoresFundoPage selecionarContaOriginador () throws BussinesException{
    	
    	try {
    		
    		Thread.sleep(2000);
    		
    		WebElement element = navegador.findElement(By.id("form:idContaOriginador"));
        	Select contaCorrenteOriginador = new Select(element);
			contaCorrenteOriginador.selectByIndex(1);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarContaOriginador", "Erro ao selecionar conta corrente do originador."));
		}
    	
        return this;
    }
    public FormularioAbaOriginadoresFundoPage selecionarDataInicioRelacionamentoOriginador () throws BussinesException{
        
        try {
        	
        	navegador.findElement(By.id("form:dtInicioOriginadorPopupButton")).click();
            navegador.findElement(By.id("form:dtInicioOriginadorDayCell30")).click();

		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarDataInicioRelacionamentoOriginador", "Erro ao definir data inicio de relacionamento do originador."));
		}
        return this;
    }
    public FormularioAbaOriginadoresFundoPage preencherCodigoOriginador (String preencherCodigoOriginadorFundo) throws BussinesException{
        
        try {
        	navegador.findElement(By.id("form:cdConsultoria")).sendKeys(preencherCodigoOriginadorFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCodigoOriginador", "Erro ao definir codigo do originador."));
		}
        return this;
    }
    public FormularioAbaOriginadoresFundoPage adicionarOriginador () throws BussinesException{
    	
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarOriginador", "Erro ao clicar no botão Adicionar originador."));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage submeterFormularioAbaRepresentantesFundo () throws BussinesException{
    	
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Representantes')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaRepresentantesFundo", "Erro ao clicar na aba Representantes."));
		}
        
        return new FormularioAbaRepresentantesFundoPage(navegador);
    }

}

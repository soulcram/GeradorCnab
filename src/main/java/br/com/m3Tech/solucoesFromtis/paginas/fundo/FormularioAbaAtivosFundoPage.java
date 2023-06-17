package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaAtivosFundoPage extends MessageErro {
    private WebDriver navegador;

    public FormularioAbaAtivosFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaAtivosFundoPage preencherAtivoAVencer (String ativoAVencerFundo) throws BussinesException{
    	
    	try {
    		navegador.findElement(By.id("form:ativoAVencer")).sendKeys(ativoAVencerFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherAtivoAVencer", "Erro ao preencher Ativo A Vencer."));
		}
        
        return this;
    }
    public FormularioAbaAtivosFundoPage preencherAtivoVencidos (String ativoVencidosFundo) throws BussinesException{
    	
    	try {
    		navegador.findElement(By.id("form:ativoVencidos")).sendKeys(ativoVencidosFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherAtivoVencidos", "Erro ao preencher Ativo Vencidos."));
		}
        
        return this;
    }
    public FormularioAbaAtivosFundoPage preencherAtivoPDD (String ativoPDDFundo) throws BussinesException{
    	
    	try {
    		navegador.findElement(By.id("form:ativoPDD")).sendKeys(ativoPDDFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherAtivoPDD", "Erro ao preencher Ativo PDD."));
		}
        
        return this;
    }
    public FormularioAbaOriginadoresFundoPage submeterFormularioAbaOriginadoresFundo () throws BussinesException{
    	
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Originadores')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaOriginadoresFundo", "Erro ao clicar na aba Originadores."));
		}
        
        return new FormularioAbaOriginadoresFundoPage(navegador);
    }

}

package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaRegraEnquadramentoFundoPage extends MessageErro{

    private WebDriver navegador;

    public FormularioAbaRegraEnquadramentoFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaRegraEnquadramentoFundoPage selecionarPerfilEnquadramento () throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:perfilEnquadramento"));
            element.click();
            Select nomeRegra = new Select(element);
            nomeRegra.selectByIndex(0);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarPerfilEnquadramento", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRegraEnquadramentoFundoPage selecionarDataInicioRelacionamentoEnquadramento () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:enqInicioRelacPopupButton")).click();
            navegador.findElement(By.id("form:enqInicioRelacDayCell32")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarDataInicioRelacionamentoEnquadramento", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRegraEnquadramentoFundoPage adicionarPerfilEnquadramento () throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarPerfilEnquadramento", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRegraEnquadramentoFundoPage ativarEnquadramento () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:enquadramentoAtivo:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("ativarEnquadramento", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaPerfisPDDFundoPage submeterFormularioAbaPerfisPDDFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Perfis PDD')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaPerfisPDDFundo", e.getMessage()));
		}
        
        return new FormularioAbaPerfisPDDFundoPage(navegador);
    }

}

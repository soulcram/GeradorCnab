package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;


public class FormularioAbaCertificadoraFundoPage extends MessageErro{

    private WebDriver navegador;

    public FormularioAbaCertificadoraFundoPage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaCertificadoraFundoPage ativarCertificadora () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:ativarCertificadora:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("ativarCertificadora", "Erro ao ativar Certificadora ."));
		}
        
        return this;
    }
    public FormularioAbaCertificadoraFundoPage selecionarCertificadora (String nomeCertificadora) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:comboCertificadores"));
            Select certificadora = new Select(element);
            certificadora.selectByIndex(0);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarCertificadora", "Erro ao selecionar Certificadora ."));
		}
        
        return this;
    }
    public FormularioAbaCertificadoraFundoPage selecionarCertificadoraPadrao () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:certificadoraPadrao:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarCertificadoraPadrao", "Erro ao selecionar Certificadora Padrao ."));
		}
        
        return this;
    }
    public FormularioAbaCertificadoraFundoPage adicionarCertificadora () throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarCertificadora", "Erro ao adicionar Certificadora ."));
		}
        
        return this;
    }
    public FormularioAbaContasCorrenteFundoPage submeterFormularioAbaContasCorrenteFundo () throws BussinesException {
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Contas Corrente')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaContasCorrenteFundo", e.getMessage()));
		}
        
        return new FormularioAbaContasCorrenteFundoPage(navegador);
    }

}

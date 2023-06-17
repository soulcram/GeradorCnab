package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class FormularioAbaRepresentantesFundoPage extends MessageErro{

    private WebDriver navegador;

    public FormularioAbaRepresentantesFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaRepresentantesFundoPage preencherNomeRepresentante (String nomeRepresentanteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentanteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNomeRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage preencherEmailRepresentante (String emailRepresentanteFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:emailRepresentante")).sendKeys(emailRepresentanteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherEmailRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage selecionarTipoPessoaRepresentante () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:representanteTipoPessoa:1")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarTipoPessoaRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage preencherNumeroTelefoneRepresentante (String numeroTelefoneRepresentanteFundo) throws BussinesException {
    	try {
    		navegador.findElement(By.id("form:telefoneRepresentante")).sendKeys(numeroTelefoneRepresentanteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroTelefoneRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage preencherNumeroCnpjRepresentante (String numeroNumeroCnpjRepresentanteFundo) throws BussinesException {
    	try {
    		navegador.findElement(By.id("form:representanteCnpj")).sendKeys(numeroNumeroCnpjRepresentanteFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherNumeroCnpjRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaRepresentantesFundoPage adicionarRepresentante () throws BussinesException {
    	try {
    		navegador.findElement(By.linkText("Adicionar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("adicionarRepresentante", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage submeterFormularioAbaLiquidacaoFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:aba10:header:inactive")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaLiquidacaoFundo", e.getMessage()));
		}
        
        return new FormularioAbaLiquidacaoFundoPage(navegador);
    }

}

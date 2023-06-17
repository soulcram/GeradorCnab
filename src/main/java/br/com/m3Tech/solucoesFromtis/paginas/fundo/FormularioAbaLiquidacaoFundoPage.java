package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;


public class FormularioAbaLiquidacaoFundoPage extends MessageErro{

    private WebDriver navegador;

    public FormularioAbaLiquidacaoFundoPage (WebDriver navegador){
        this.navegador=navegador;
    }

    public FormularioAbaLiquidacaoFundoPage ativaLiquidacao () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:ativarLiquidacao:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("ativaLiquidacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage ativarSolicitacaoPagamentoLiquidacao () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:ativarSolicitacaoPagto:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("ativarSolicitacaoPagamentoLiquidacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage preencherLimiteReembolsoLiquidacao (String limiteReembolsoLiquidacaoFundo ) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:limiteReembolso")).sendKeys(limiteReembolsoLiquidacaoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherLimiteReembolsoLiquidacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage selecionarPagarLiquidacaoVia (String pagarLiquidacaoViaFundo ) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:liquidacaoViaMatera"));
            Select pagarLiquidacao = new Select(element);
            pagarLiquidacao.selectByValue(pagarLiquidacaoViaFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarPagarLiquidacaoVia", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage selecionarPagarSolicitacaoLiquidacao (String pagarSolicitacaoLiquidacaoFundo ) throws BussinesException {
    	try {
    		
    		Thread.sleep(1000);
    		
    		WebElement element = navegador.findElement(By.id("form:solicitacaoViaMatera"));
            Select pagarSolicitacao = new Select(element);
            pagarSolicitacao.selectByValue(pagarSolicitacaoLiquidacaoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarPagarSolicitacaoLiquidacao", e.getMessage()));
		}
        
        return this;

    }
    public FormularioAbaLiquidacaoFundoPage preencherPercentualReembolsoLiquidacao (String percentualReembolsoLiquidacaoFundo ) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:percentualReembolso")).sendKeys(percentualReembolsoLiquidacaoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherPercentualReembolsoLiquidacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaLiquidacaoFundoPage selecionarNSeuNumeroCompostoLiquidacao () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:seuNumeroComposto:1")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarNSeuNumeroCompostoLiquidacao", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaCertificadoraFundoPage submeterFormularioAbaCertificadoraFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:aba11:header:inactive")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaCertificadoraFundo", e.getMessage()));
		}
        
        return new FormularioAbaCertificadoraFundoPage(navegador);
    }

}

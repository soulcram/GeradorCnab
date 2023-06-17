package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.cedente.ConsultaCedentePage;
import br.com.m3Tech.solucoesFromtis.paginas.sacado.ConsultaSacadoPage;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;



public class FundoPage extends MessageErro{
    private WebDriver navegador;

    public FundoPage (WebDriver navegador){

        this.navegador=navegador;
    }

    //Mapping e Action Cadastro Fundo
    public FundoPage submeterPrimeiroMenuFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Fundo')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterPrimeiroMenuFundo", e.getMessage()));
		}
        
        return this;
    }
    public ConsultaFundoPage submeterSegundoMenuFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.id("menuForm:fundo")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterSegundoMenuFundo", e.getMessage()));
		}
        
        return new ConsultaFundoPage(navegador);
    }

    //Mapping e Action Cadastro Cedente
    public ConsultaCedentePage submeterMenuCedente () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Cedente')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCedente", e.getMessage()));
		}
        
        return new ConsultaCedentePage(navegador);
    }
    
    //Mapping e Action Cadastro Cedente
    public ConsultaSacadoPage submeterMenuSacado () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Sacado')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuSacado", e.getMessage()));
		}
        
        return new ConsultaSacadoPage(navegador);
    }

}

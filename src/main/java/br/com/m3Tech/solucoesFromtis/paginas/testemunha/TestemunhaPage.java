package br.com.m3Tech.solucoesFromtis.paginas.testemunha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class TestemunhaPage extends MessageErro {

    private WebDriver navegador;

    public TestemunhaPage (WebDriver navegador) {

        this.navegador = navegador;
    }
    //Mapping e Action
    public ConsultaTestemunhaPage submeterMenuTestemunha() throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Testemunha')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuTestemunha", e.getMessage()));
		}
        
        return new ConsultaTestemunhaPage(navegador);
    }
}

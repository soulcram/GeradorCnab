package br.com.m3Tech.solucoesFromtis.paginas.testemunha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class ConsultaTestemunhaPage extends MessageErro {

    private WebDriver navegador;

    public ConsultaTestemunhaPage (WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoTestemunhaPage submeterFormularioDeAdicaoTestemunha () throws BussinesException{
    	try {
    		 navegador.findElement(By.linkText("Novo")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioDeAdicaoTestemunha", e.getMessage()));
		}
       
        return new FormularioDeAdicaoTestemunhaPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso() throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemApresentadaComSucesso", e.getMessage()));
		}
        
    }
    public String capturarMensagemApresentadaComErro() throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe CPF para o Fundo selecionado.')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemApresentadaComErro", e.getMessage()));
		}
        
    }
    public String capturarMensagemApresentadaDadosObrigatorios () throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Fundo é obrigatório')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemApresentadaDadosObrigatorios", e.getMessage()));
		}
        
    }
}

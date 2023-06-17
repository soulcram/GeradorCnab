package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class ConsultaFundoPage extends MessageErro{

    private WebDriver navegador;

    public ConsultaFundoPage (WebDriver navegador){

        this.navegador=navegador;
    }

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage submterFormularioDeAdicaoFundo() throws BussinesException{
    	try {
    		navegador.findElement(By.linkText("Novo")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submterFormularioDeAdicaoFundo", "Erro ao clicar no botao novo."));
		}
        
        return new FormularioDeAdicaoAbaDadosCadastraisFundoPage(navegador);
    }
    public String capturarMensagemComSucesso () throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemComSucesso", "Erro ao capturar Mensagem Com Sucesso ."));
		}

        
    }

    public String capturarMensagemApresentadaComErro (String cnpj) throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe um Fundo para o CNPJ: ("+cnpj+")')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemApresentadaComErro", "Erro ao capturar Mensagem Apresentada Com Erro ."));
		}
        
    }
    public String capturarMensagemApresentadaDadosObrigatorios () throws BussinesException{
    	try {
    		return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Código ISIN Aba Dados Cadastrais é obrigatório')]")).getText();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("capturarMensagemApresentadaDadosObrigatorios", "Erro ao capturar Mensagem Apresentada Dados Obrigatorios ."));
		}

        
    }

}

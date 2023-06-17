package br.com.m3Tech.solucoesFromtis.paginas.fundo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;


public class FormularioDeAdicaoAbaDadosCadastraisFundoPage extends MessageErro {

    private WebDriver navegador;

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage(WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Action - Não Preencher Dados Obrigatórios
    public ConsultaFundoPage submeterFormulariodeAdicaoComErro () throws BussinesException {
    	try {
    		navegador.findElement(By.linkText("Salvar")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormulariodeAdicaoComErro", e.getMessage()));
		}
        
        return new ConsultaFundoPage(navegador);
    }


    //Mapping e Action - Para preencher todo formulário
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage preencherCodigoIsinFundo(String codigoIsinFundo) throws BussinesException{
    	try {
    		 navegador.findElement(By.id("form:codigoISIN")).sendKeys(codigoIsinFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCodigoIsinFundo", e.getMessage()));
		}
       
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage preencherCodigoAnbidFundo(String codigoAnbidFundo) throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:codigoAnbid")).sendKeys(codigoAnbidFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherCodigoAnbidFundo", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage preencherInicioProcessamentoInputDatedFundo(String inicioProcessamentoInputDateFundo) throws BussinesException{
    	try {
    		 navegador.findElement(By.id("form:inicioProcessamentoInputDate")).sendKeys(inicioProcessamentoInputDateFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("preencherInicioProcessamentoInputDatedFundo", e.getMessage()));
		}
       
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarTipoContagemDiasFundo() throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:tipoContagemDias:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarTipoContagemDiasFundo", e.getMessage()));
		}
        
        return this;
    }

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarTipoDoFundo(String tipoDoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:tipoFundo"));
            Select tipoFundo = new Select(element);
            tipoFundo.selectByValue(tipoDoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarTipoDoFundo", e.getMessage()));
		}
        
        return this;
    }

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarOpcaoDoFundo() throws BussinesException{
    	try {
    		navegador.findElement(By.id("form:aberto:0")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarOpcaoDoFundo", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarSituacaoAtualDoFundo(String situacaoDoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:situacaoAtual"));
            Select situacaoFundo = new Select(element);
            situacaoFundo.selectByValue(situacaoDoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarSituacaoAtualDoFundo", e.getMessage()));
		}
        
        return this;
    }

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarNivelDeRiscoDoFundo(String nivelDoRisco) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:nivelRisco"));
            Select nivelRisco = new Select(element);
            nivelRisco.selectByValue(nivelDoRisco);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarNivelDeRiscoDoFundo", e.getMessage()));
		}
        
        return this;
    }

    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarStatusProcessamentoDoFundo(String statusProcessamentoDoFundo) throws BussinesException{
    	try {
    		 WebElement element = navegador.findElement(By.id("form:statusProcessamento"));
    	        Select statusProcessamento = new Select(element);
    	        statusProcessamento.selectByVisibleText("Disponível");
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarStatusProcessamentoDoFundo", e.getMessage()));
		}
       
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarLayoutAquisicaoDoFundo(String layoutAquisicaoDoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:layoutAquisicao"));
            Select layoutAquisicao = new Select(element);
            layoutAquisicao.selectByValue(layoutAquisicaoDoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarLayoutAquisicaoDoFundo", e.getMessage()));
		}
        
        return this;
    }
    public FormularioDeAdicaoAbaDadosCadastraisFundoPage selecionarLayoutOcorrenciaDoFundo(String layoutOcorrenciaDoFundo) throws BussinesException{
    	try {
    		WebElement element = navegador.findElement(By.id("form:layoutOcorrencia"));
            Select layoutOcorrencia = new Select(element);
            layoutOcorrencia.selectByValue(layoutOcorrenciaDoFundo);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarLayoutOcorrenciaDoFundo", e.getMessage()));
		}
        
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage submeterFormularioAbaDadosAdicionaisFundo () throws BussinesException{
    	try {
    		navegador.findElement(By.xpath("//*[contains(text(), 'Dados Adicionais')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioAbaDadosAdicionaisFundo", e.getMessage()));
		}
       
       return new FormularioAbaDadosAdicionaisFundoPage(navegador);

    }


}

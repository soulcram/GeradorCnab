package br.com.m3Tech.solucoesFromtis.paginas.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.banco.BancoPage;
import br.com.m3Tech.solucoesFromtis.paginas.certificadora.CertificadoraPage;
import br.com.m3Tech.solucoesFromtis.paginas.enquadramento.EnquadramentoPage;
import br.com.m3Tech.solucoesFromtis.paginas.entidade.EntidadePage;
import br.com.m3Tech.solucoesFromtis.paginas.fundo.FundoPage;
import br.com.m3Tech.solucoesFromtis.paginas.indexadores.IndexadoresPage;
import br.com.m3Tech.solucoesFromtis.paginas.recebiveis.RecebiveisPage;
import br.com.m3Tech.solucoesFromtis.paginas.sacado.SacadoPage;
import br.com.m3Tech.solucoesFromtis.paginas.testemunha.TestemunhaPage;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class HomePage extends MessageErro{

    private WebDriver navegador;

    public HomePage (WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Actions
    public EnquadramentoPage submeterMenuEnquadramento() throws BussinesException {
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Enquadramento')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuEnquadramento", "Erro ao clicar na aba Enquadramento."));
		}
        return new EnquadramentoPage(navegador);
    }

     public EntidadePage submeterMenuCadastroEntidade () throws BussinesException{
    	 try {
    		 WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
    	        Actions actions = new Actions(navegador);
    	        actions.moveToElement(menu).perform();
 		} catch (Exception e) {
 			 throw new BussinesException(getMessage("submeterMenuCadastroEntidade", "Erro ao clicar na aba CadastroEntidade."));
 		}
        
        return new EntidadePage(navegador);
    }

    public CertificadoraPage submeterMenuCadastroCertificadora () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroCertificadora", "Erro ao clicar na aba CadastroCertificadora."));
		}
        

        return new CertificadoraPage(navegador);
    }
    public BancoPage submeterMenuCadastroBanco () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroBanco", "Erro ao clicar na aba CadastroBanco."));
		}
        

        return new BancoPage(navegador);
    }
    public FundoPage submeterMenuCadastroFundo () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("selecionarContaOriginador", "Erro ao clicar na aba CadastroFundo."));
		}
        

        return new FundoPage(navegador);
    }
    public IndexadoresPage submeterMenuCadastroIndexadores () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroIndexadores", "Erro ao clicar na aba CadastroIndexadores."));
		}
        

        return new IndexadoresPage(navegador);
    }
    public RecebiveisPage submeterMenuCadastroRecebiveis () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroRecebiveis", "Erro ao clicar na aba CadastroRecebiveis."));
		}
        

        return new RecebiveisPage(navegador);
    }

    public SacadoPage submeterMenuCadastroSacado () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroSacado", "Erro ao clicar na aba CadastroSacado."));
		}
        

        return new SacadoPage(navegador);
    }
    public TestemunhaPage submeterMenuCadastroTestemunha () throws BussinesException{
    	try {
    		WebElement menu = navegador.findElement(By.xpath("//*[contains(text(), 'Cadastro')]"));
            Actions actions = new Actions(navegador);
            actions.moveToElement(menu).perform();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterMenuCadastroTestemunha", "Erro ao clicar na aba CadastroTestemunha."));
		}
        

        return new TestemunhaPage(navegador);
    }



}

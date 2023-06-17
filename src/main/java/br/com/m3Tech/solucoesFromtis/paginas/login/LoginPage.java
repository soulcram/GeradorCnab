package br.com.m3Tech.solucoesFromtis.paginas.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.home.HomePage;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;

public class LoginPage extends MessageErro{

    //Sempre que for criar uma classe com padrão Object necessário criar o Atibuto private e o encapsulamento

    private WebDriver navegador;

    public LoginPage (WebDriver navegador) {

        this.navegador = navegador ;
    }

    public LoginPage informarOUsuario(String usuario) throws BussinesException {
    	try {
    		navegador.findElement(By.id("j_username")).sendKeys(usuario);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("informarOUsuario", "Erro ao informar O Usuario."));
		}
        

        return this;

    }

    public LoginPage informarASenha (String senha) throws BussinesException {
    	try {
    		navegador.findElement(By.id("j_password")).sendKeys(senha);
		} catch (Exception e) {
			 throw new BussinesException(getMessage("informarASenha", "Erro ao informar A Senha."));
		}
        

        return this;

    }

    public HomePage submeterFormularioDeLogin() throws BussinesException {
    	try {
    		navegador.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();
		} catch (Exception e) {
			 throw new BussinesException(getMessage("submeterFormularioDeLogin", "Erro ao clicar em entrar."));
		}
        

        return new HomePage(navegador);
    }


}

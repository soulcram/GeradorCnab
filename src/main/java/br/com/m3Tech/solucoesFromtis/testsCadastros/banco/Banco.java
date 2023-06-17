package br.com.m3Tech.solucoesFromtis.testsCadastros.banco;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;


public class Banco  {
    private WebDriver navegador;

    public void beforeEach() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.navegador.get("http://sinsaon711:9080/fidcCustodia/login.xhtml");
        Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public void testPermitirCadastrarNovoBanco() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroBanco()
                .submeterMenuBanco()
                .submeterFormulariodeAdicaoDeBanco()
                .preencherNomeBanco("BANCO QA")
                .preencherDescricaoBanco("BANCO QA")
                .preencherNumeroBanco("998")
                .selecionarAtivarBanco()
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);
    }

    public void testNaoPermitirCadastrarBancoJaCadastrado() throws BussinesException {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroBanco()
                .submeterMenuBanco()
                .submeterFormulariodeAdicaoDeBanco()
                .preencherNomeBanco("BANCO QA TESTE")
                .preencherDescricaoBanco("BANCO QA TESTE")
                .preencherNumeroBanco("001")
                .selecionarAtivarBanco()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        //Validando a mensagem de Número de banco já cadastrado
        WebElement mensagem = navegador.findElement(By.xpath("//*[contains(text(), 'Número de banco já cadastrado.')]"));
        Assertions.assertEquals("Número de banco já cadastrado.",mensagem.getText());
    }

    public void testNaoPermitirCadastrarBancoSemDadosObrigatorios() throws BussinesException {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroBanco()
                .submeterMenuBanco()
                .submeterFormulariodeAdicaoDeBanco()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de Número de banco já cadastrado
        Assertions.assertEquals("O campo Nome é obrigatório",mensagemApresentada);
    }

    public void afterEach  (){

       navegador.quit();
    }

}

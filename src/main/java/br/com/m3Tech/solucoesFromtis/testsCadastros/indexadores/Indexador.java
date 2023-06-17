package br.com.m3Tech.solucoesFromtis.testsCadastros.indexadores;

import java.time.Duration;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;

public class Indexador {
    private WebDriver navegador;

    public void beforeEach(){
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        navegador.get("http://sinsaon711:9080/fidcCustodia/login.xhtml");
        Assert.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public void testPermitidoCadastrarNovoIndexador()  throws BussinesException{

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuIndexador()
                .submeterFormulariaDeAdicaoIndexador()
                .preencherNomeIndexador("IPCA QA - ANIVERSÁRIO")
                .preencherCodigoIndexador("6")
                .preencherDefasagemIndexador("0")
                .selecionarTipoDeCalculoIndexador("IPCA QA TESTE")
                .selecionarTipoDeTaxaIndexador("Mensal")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        Assertions.assertEquals("Operação realizada com sucesso",mensagemEsperada);

    }

    public void testNaoPermitirCadastrarIndexadorJaCadastrado() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuIndexador()
                .submeterFormulariaDeAdicaoIndexador()
                .preencherNomeIndexador("IPCA QA - ANIVERSÁRIO TESTE")
                .preencherCodigoIndexador("9")
                .preencherDefasagemIndexador("0")
                .selecionarTipoDeCalculoIndexador("IPCA QA TESTE")
                .selecionarTipoDeTaxaIndexador("Mensal")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        Assertions.assertEquals("Já existe Indexador com este Código",mensagemEsperada);

    }

    public void testNaoPermitirCadastrarIndexadorSemDadosObrigatorios() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuIndexador()
                .submeterFormulariaDeAdicaoIndexador()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        Assertions.assertEquals("O campo Nome é obrigatório",mensagemEsperada);
    }

    public void aferterEach (){
        navegador.quit();
    }

}

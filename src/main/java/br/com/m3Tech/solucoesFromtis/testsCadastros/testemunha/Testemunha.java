package br.com.m3Tech.solucoesFromtis.testsCadastros.testemunha;


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


public class Testemunha  {
    private WebDriver navegador;


    public void beforeEach() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.navegador.get("http://sinsaon711:9080/fidcCustodia/login.xhtml");
        Assert.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public void testPemitirCadastrarNovaTestemunha() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroTestemunha()
                .submeterMenuTestemunha()
                .submeterFormularioDeAdicaoTestemunha()
                .selecionarFundoTestemunha("FUNDO QA TESTE")
                .preencherNomeTestemunha("TESTEMUNHA QA")
                .preencherCpfTestemunha("055.591.376-77")
                .preencherEmailTestemunha("teste@teste.com")
                .preencherLogradouroTestemunha("Rua Teste")
                .preencherNumeroLogradouroTestemunha("422")
                .preencherCepTestemunha("05335-050")
                .preencherBairroTestemunha("Jaguaré")
                .preencherCidadeTestemunha("São Paulo")
                .selecionarEstadoTestemunha("São Paulo")
                .selecionarSituacaoTestemunha()
                .submeterFoormularioDeAdicaComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);

    }

    public void testNaoPemitirCadastroDeTestemunhaJaCadastrada() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroTestemunha()
                .submeterMenuTestemunha()
                .submeterFormularioDeAdicaoTestemunha()
                .selecionarFundoTestemunha("FUNDO QA TESTE")
                .preencherNomeTestemunha("TESTEMUNHA QA TESTE")
                .preencherCpfTestemunha("246.327.265-16")
                .preencherEmailTestemunha("teste@teste.com")
                .preencherLogradouroTestemunha("Rua Teste")
                .preencherNumeroLogradouroTestemunha("422")
                .preencherCepTestemunha("05335-050")
                .preencherBairroTestemunha("Jaguaré")
                .preencherCidadeTestemunha("São Paulo")
                .selecionarEstadoTestemunha("São Paulo")
                .selecionarSituacaoTestemunha()
                .submeterFoormularioDeAdicaComErro()
                .capturarMensagemApresentadaComErro();

        //Validando a mensagem de Erro
        Assertions.assertEquals("Já existe CPF para o Fundo selecionado.",mensagemApresentada);

    }

    public void testNaoPermitirCadastrarTestemunhaSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroTestemunha()
                .submeterMenuTestemunha()
                .submeterFormularioDeAdicaoTestemunha()
                .submeterFoormularioDeAdicaComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de Erro - Solicitando preencher os dados obrigatórios
        Assertions.assertEquals("O campo Fundo é obrigatório",mensagemApresentada);

    }

    public void afterEach  (){
        navegador.quit();
    }
}

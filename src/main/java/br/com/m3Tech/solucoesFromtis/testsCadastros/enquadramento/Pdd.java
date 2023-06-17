package br.com.m3Tech.solucoesFromtis.testsCadastros.enquadramento;


import java.time.Duration;

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

public class Pdd {

    private WebDriver navegador;

    public void beforeEach (){
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.navegador.get("http://sinsaon711:9080/fidcCustodia/login.xhtml");
        Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public void testPermitidoAdicionarUmNovoPdd() throws BussinesException {
        // Fazer Login
        new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuPDD()
                .acessarFormularianovoPDD()
                .preencherNomePDD("PDD QA")
                .faixaUnica()
                .submeterFormulariodeAdicaoComSucesso();
//No cadastro de um novo PDD não apresenta uma mensagem de PDD Cadastrado com sucesso, necessário que o DEV inclua uma mensagem
    }

    public  void testNaoPermitidoCadastroDePDDJaCadastrado () throws BussinesException{


        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuPDD()
                .acessarFormularianovoPDD()
                .preencherNomePDD("PDD TESTE")
                .faixaUnica()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        Assertions.assertEquals("Perfil de PDD \"PDD TESTE\" já cadastrado!",mensagemApresentada);

    }

    public  void testNaoPermitirCadastrarPDDSemDadosObrigatorios () throws BussinesException{

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuPDD()
                .acessarFormularianovoPDD()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        Assertions.assertEquals("O nome do PDD deve ser informado!",mensagemApresentada);

    }

    public void afterEach (){

        navegador.quit();

    }

}



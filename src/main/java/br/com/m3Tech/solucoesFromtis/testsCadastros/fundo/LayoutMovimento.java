package br.com.m3Tech.solucoesFromtis.testsCadastros.fundo;

import java.time.Duration;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;

public class LayoutMovimento {

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

        public void testCadastrarUmaNovaOcorrenciaMovimento () throws BussinesException{

             String mensagemApresentada = new LoginPage(navegador)
                     .informarOUsuario("fromtis")
                     .informarASenha("fromtis!Q@W#E")
                     .submeterFormularioDeLogin()
                     .submeterMenuCadastroRecebiveis()
                     .submeterPrimeiroMenuRecebiveis()
                     .submeterMenuLayoutMovimento()
                     .submeterFormularioDeAdicaoLayoutMovimento()
                     .selecionarLayoutMovimento("CNAB444 - REMESSA SOCOPA")
                     .selecionarTipoMovimento("AQUISIÇÃO")
                     .preencherCodigoOcorrenciaMovimento("98")
                     .submeterFormularioDeAdicaoComSucesso()
                     .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("Operação realizada com sucesso", mensagemApresentada);

    }

    public void testNaoPermitirCadastrarOcorrenciaJaCadastrada() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroRecebiveis()
                .submeterPrimeiroMenuRecebiveis()
                .submeterMenuLayoutMovimento()
                .submeterFormularioDeAdicaoLayoutMovimento()
                .selecionarLayoutMovimento("CNAB444 - REMESSA SOCOPA")
                .selecionarTipoMovimento("AQUISIÇÃO")
                .preencherCodigoOcorrenciaMovimento("1")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        //Validando a mensagem de erro
        Assertions.assertEquals("Código de Ocorrência já cadastrado para o layout", mensagemApresentada);

    }
    
    @DisplayName("Não permitir cadastrar uma nova ocorrência, sem preencher os dados obrigatórios")
    public void testNaoPermitirCadastrarNovaOcorrenciaSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroRecebiveis()
                .submeterPrimeiroMenuRecebiveis()
                .submeterMenuLayoutMovimento()
                .submeterFormularioDeAdicaoLayoutMovimento()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de erro - Mensagem de dados obrigatórios
        Assertions.assertEquals("O campo Layout é obrigatório", mensagemApresentada);

    }
        @AfterEach
    public void afterEach  (){
        navegador.quit();
    }

}

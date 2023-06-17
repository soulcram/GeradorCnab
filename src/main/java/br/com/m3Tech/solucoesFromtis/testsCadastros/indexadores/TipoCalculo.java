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


public class TipoCalculo {
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

    public void testNaoCadastrarTipoDeCalculoJaCadastrado() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuTipoCalculo()
                .submeterFormulariaDeAdicaoTipoCalculo()
                .preencherNomeTipoCalculo("IPCA QA")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);

        //Necessário Criar regra impossibilitando cadastrar o mesmo tipo de calculo
    }

    public void testNaoPermitirCadastrarTipoDeCalculoSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuTipoCalculo()
                .submeterFormulariaDeAdicaoTipoCalculo()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("O campo Nome é obrigatório",mensagemApresentada);
    }

    public void aferterEach (){

        navegador.quit();
    }
}

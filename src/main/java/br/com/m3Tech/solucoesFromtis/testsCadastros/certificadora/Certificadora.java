package br.com.m3Tech.solucoesFromtis.testsCadastros.certificadora;


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



public class Certificadora {

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

    public void testPermitirCadastrarNovaCertificadora() throws BussinesException {

       String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroCertificadora()
                .submeterMenuCertificadora()
                .submterFormularioDeAdicaoCertificadora()
                .preencherNomeCertificadora("CERTIFICADORA QA")
                .preencherCnpjCertificadora("59.421.282/0001-43")
                .preencherLogradouroCertificadora("Rua Teste")
                .preencherNumeroCertificadora("422")
                .preencherComplementoCertificadora("11 Andar - Sala 1102")
                .preencherCepCertificadora("05335-050")
                .preencherBairroCertificadora("Jaguaré")
                .preencherCidadeCertificadora("São Paulo")
                .selecionarEstadoCertificadora("São Paulo")
                .preencherLetrasIniciaisCertificadora("TQA")
                .preencherQnameCertificadora("http://webservices.portal.fidc.fromtis.com.br/")
                .preencherUrlCertificadora("http://sinsaon711:8089/fromtis/RequisicaoCertificacaoDigital?wsdl")
                .selecionarTipoCertificadora("Serviço padrão")
                .selecionarTipoServicoCertificadora("Padrão")
                .preencherNomeContatoCertificadora("QA")
                .preencherEmailContatoCertificadora("teste@teste.com")
                .submeterFormularioDeCertificadora()
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);

    }

    public void testNaoPermitirCadastroDeCertificadoraJaCadastrada() throws BussinesException {

        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroCertificadora()
                .submeterMenuCertificadora()
                .submterFormularioDeAdicaoCertificadora()
                .preencherNomeCertificadora("CERTIFICADORA QA TESTE")
                .preencherCnpjCertificadora("54.480.329/0001-26")
                .preencherLogradouroCertificadora("Rua Teste")
                .preencherNumeroCertificadora("422")
                .preencherComplementoCertificadora("11 Andar - Sala 1102")
                .preencherCepCertificadora("05335-050")
                .preencherBairroCertificadora("Jaguaré")
                .preencherCidadeCertificadora("São Paulo")
                .selecionarEstadoCertificadora("São Paulo")
                .preencherLetrasIniciaisCertificadora("TQE")
                .preencherQnameCertificadora("http://webservices.portal.fidc.fromtis.com.br/")
                .preencherUrlCertificadora("http://sinsaon711:8089/fromtis/RequisicaoCertificacaoDigital?wsdl")
                .selecionarTipoCertificadora("Serviço padrão")
                .selecionarTipoServicoCertificadora("Padrão")
                .preencherNomeContatoCertificadora("QA")
                .preencherEmailContatoCertificadora("teste@teste.com")
                .submeterFormularioDeCertificadora()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        //Validando a mensagem de certificadora já cadastrada
        Assertions.assertEquals("Já existe uma certificadora com o Iniciais do Arquivo cadastrado: TQE",mensagemApresentada);

    }

    public void testNaoPermitirCadastrarCertificadoraSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroCertificadora()
                .submeterMenuCertificadora()
                .submterFormularioDeAdicaoCertificadora()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de campo obrigatório
        Assertions.assertEquals("O campo Nome é obrigatório",mensagemApresentada);

    }

    public void afterEach (){
        navegador.quit();
    }
}

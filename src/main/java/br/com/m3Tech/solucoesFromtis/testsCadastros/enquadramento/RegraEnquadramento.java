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

public class RegraEnquadramento {

private WebDriver navegador;

   public void beforeEach () {

	ChromeOptions options = new ChromeOptions();
   	options.addArguments("--remote-allow-origins=*");
    this.navegador = new ChromeDriver(options);
    this.navegador.manage().window().maximize();
    this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    this.navegador.get("http://sinsaon711:9080/fidcCustodia/login.xhtml");
    Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
}

        public void testPermitidoAdicionarNovaRegraEnquadramento() throws BussinesException {

            String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuRegraDeEnquadramento()
                .acessarFormularioDeNovaRegraEnquadramento()
                .acessarRegraAplicadasPorSacado()
                .acessarSacadosNaoPermitidos()
                .preencherNomeDaRegra("Sacados Não Permitidos QA")
                .preencherDescricaoDaRegra("Sacados Não Permitidos QA")
                .selecionarTipoDePessoaJuridica()
                .preencherCnpjDaRegra("54833077000171")
                .adicionarRegra()
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

            Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);

    }

    public void testNaoPermitidoCadastroDeRegraJaCadastrada() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuRegraDeEnquadramento()
                .acessarFormularioDeNovaRegraEnquadramento()
                .acessarRegraAplicadasPorSacado()
                .acessarSacadosNaoPermitidos()
                .preencherNomeDaRegra("Sacados Não Permitidos Teste")
                .preencherDescricaoDaRegra("Sacados Não Permitidos Teste")
                .selecionarTipoDePessoaJuridica()
                .preencherCnpjDaRegra("31189581000185")
                .adicionarRegra()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        Assertions.assertEquals("Já existe Regra com o nome: Sacados Não Permitidos Teste",mensagemApresentada);

        //Necessário incluir uma validação e não deixar cadastrar a mesma regra, contendo o mesmo nome

    }

    public void testNaoPermitirCadastrarRegraSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuRegraDeEnquadramento()
                .acessarFormularioDeNovaRegraEnquadramento()
                .acessarRegraAplicadasPorSacado()
                .acessarSacadosNaoPermitidos()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        Assertions.assertEquals("O campo Nome da Regra é obrigatório",mensagemApresentada);


    }

    public void afterEach (){
        navegador.quit();
    }
}

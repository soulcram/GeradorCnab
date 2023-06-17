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

public class PerfilEnquadramento {

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

    public void testCadastroNovoPerfilEnquadramento() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuPerfilDeEnquadramento()
                .submeterAoFormularioDeAdicaoDoPerfil()
                .preencherNomeDoPerfil("Perfil Sacados Não Permitidos QA")
                .preencherDescricaoPerfil("Perfil Sacados Não Permitidos QA")
                .selecionarRegrasDeEnquadramento()
                .submeterFormulariodDeAdicaoComSucesso()
                .confirmarAlert()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Perfil Enquadramento cadastrado com sucesso.",mensagemApresentada);

    }

   public void testNaoPermitidoCadastroDePerfilJaCadastrado() throws BussinesException {

       String mensagemDeErroApresentada = new LoginPage(navegador)
               .informarOUsuario("fromtis")
               .informarASenha("fromtis!Q@W#E")
               .submeterFormularioDeLogin()
               .submeterMenuEnquadramento()
               .submeterMenuPerfilDeEnquadramento()
               .submeterAoFormularioDeAdicaoDoPerfil()
               .preencherNomeDoPerfil("Perfil Sacados Não Permitidos Teste")
               .preencherDescricaoPerfil("Perfil Sacados Não Permitidos Teste")
               .selecionarRegrasDeEnquadramento()
               .submeterFormulariodDeAdicaoComErro()
               .confirmarAlert()
               .capturarMensagemApresentadaComErro();

       Assertions.assertEquals("Já existe Perfil com o nome: Perfil Sacados Não Permitidos Teste",mensagemDeErroApresentada);

   }

    public void testNaoPermitirCadastrarPerfilSemDadosObrigatorios() throws BussinesException {

        String mensagemDeErroApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuEnquadramento()
                .submeterMenuPerfilDeEnquadramento()
                .submeterAoFormularioDeAdicaoDoPerfil()
                .submeterFormulariodDeAdicaoComErro()
                .confirmarAlert()
                .capturarMensagemApresentadaDadosObrigatorios();

        Assertions.assertEquals("O campo Nome é obrigatório",mensagemDeErroApresentada);

    }


    public void afterEach (){
        navegador.quit();
    }
}

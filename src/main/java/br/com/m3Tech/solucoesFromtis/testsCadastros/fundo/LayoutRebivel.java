package br.com.m3Tech.solucoesFromtis.testsCadastros.fundo;


import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;


public class LayoutRebivel  {
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

    public void testPermitirCadastrarLayoutRebivel() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroRecebiveis()
                .submeterPrimeiroMenuRecebiveis()
                .submeterMenuLayoutRecebivel()
                .submeterFormularioDeAdicaoLayoutRecebivel()
                .selecionarLayoutRebivel("CNAB444 - REMESSA SOCOPA")
                .selecionarTipoRebivel("Duplicata")
                .preencherEspecieRecebivel("99")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assert.assertEquals("Operação realizada com sucesso",mensagemApresentada);

    }

    public void testNaoPermitirCadastrarLayoutRebivelJaCadastrado() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroRecebiveis()
                .submeterPrimeiroMenuRecebiveis()
                .submeterMenuLayoutRecebivel()
                .submeterFormularioDeAdicaoLayoutRecebivel()
                .selecionarLayoutRebivel("CNAB444 - REMESSA SOCOPA")
                .selecionarTipoRebivel("Duplicata")
                .preencherEspecieRecebivel("1")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaComErro();

        //Validando a mensagem de erro - Código de ocorrência já cadastrado
        Assert.assertEquals("Código de Ocorrência já cadastrado para o layout",mensagemApresentada);

    }

    public void testNaoPermitirCadastrarNovaEspecieSemDadosObrigatorios() throws BussinesException {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroRecebiveis()
                .submeterPrimeiroMenuRecebiveis()
                .submeterMenuLayoutRecebivel()
                .submeterFormularioDeAdicaoLayoutRecebivel()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de erro - Mensagem de dados obrigatórios
        Assert.assertEquals("O campo Layout é obrigatório",mensagemApresentada);

    }

    public void afterEach  (){
        navegador.quit();
    }
}

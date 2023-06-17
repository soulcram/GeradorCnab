package br.com.m3Tech.solucoesFromtis.testsCadastros.indexadores;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;


public class TaxaIndexador {
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

    public void testCadastrarNovaTaxa() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuTaxaIdexador()
                .submeterFormularioDeAdicaoTaxaIndexador()
                .preencherDataTaxaIndexador("01/06/2022")
                .selecionarTipoTaxaIndexador("Mensal")
                .preencherValorTaxaIndexador("1,0600000000")
                .selecionarTipoIndexadorTaxa("IPCA QA - ANIVERSÁRIO TESTE")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        //Validando a mensagem de Operação realizado com sucesso
        Assert.assertEquals("Taxa replicado para o mês inteiro com sucesso",mensagemEsperada);

    }

    public void testNaoPermitirCadastrarTaxaIndexadorSemDadosObrigatorios() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroIndexadores()
                .submeterPrimeiroMenuIndexadores()
                .submeterMenuTaxaIdexador()
                .submeterFormularioDeAdicaoTaxaIndexador()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de Operação realizado com sucesso
        Assert.assertEquals("O campo Data é obrigatório",mensagemEsperada);

        //Analisar a regra de negócio, se pode sobrescrever uma taxa já inserida
    }

    public void aferterEach (){
       navegador.quit();
    }

}

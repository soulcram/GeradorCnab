package br.com.m3Tech.solucoesFromtis.testsCadastros.sacado;


import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.dto.ParametrosTestesDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Sacado {
    private WebDriver navegador;
    private String usuario;
    private String senha;
    private ParametrosTestesDto parametros;
    
    public Sacado(String url, String usuario, String senha, ParametrosTestesDto parametros){
    	
    	WebDriverManager.chromedriver().setup();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
    	
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        
        this.usuario = usuario;
        this.senha = senha;
        this.parametros = parametros;

        navegador.get(url + "/fidcCustodia/login.xhtml");
        Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }


    public Boolean testPermitirCadastrarNovoSacado()  throws BussinesException{

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuSacado()
                .submeterFormularioDeAdicaoSacado()
                .selecionarFundoSacado(parametros.getFundoExistente().getNomeFundo())
                .selecionarTipoPessoaSacado()
                .preencherNomeSacado("SACADO QA")
                .preencherCpfSacado(parametros.getDocSacadoNovo())
                .preencherDataInicioRelacionamentoSacado("07/07/2022")
                .selecionarPorteSacado("Mais de 1 a 2 salários mínimos")
                .selecionarClassificacaoDeRiscoSacado("Classificação de risco A")
                .selecionarTipoSociedadeSacado("INSTITUIÇÃO FINANCEIRA")
                .selecionarTipoIncricaoEstadualSacado()
                .preencherLogradouroSacado("Rua Teste")
                .preencherNumeroLogradouroSacado("422")
                .preencherCepSacado("05335-050")
                .preencherBairroSacado("Jaguaré")
                .preencherCidadeSacado("São Paulo")
                .selecionarEstadoSacado("São Paulo")
                .preencherEmailSacado("teste@teste.com")
                .selecionarAssinaturaSacado()
                .selecionarAssinaSozinhoSacado()
                .selecionarAssinaTermoCessaoSacado()
                .selecionarAssinaPorEndossoSacado()
                .selecionarEmiteDuplicataSacado()
                .preencherNomeRepresentanteSacado("TESTE REPRESENTANTE SACADO")
                .preencherEmailRepresentanteSacado("teste@teste.com")
                .selecionarTipoPessoaRepresentanteSacado()
                .preencherCnpjRepresentanteSacado("32.555.000/1040-04")
                .adicionarRepresentanteSacado()
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

        afterEach();
        return "Operação realizada com sucesso".equals(mensagemApresentada);

    }

    public Boolean testNaoPermitirCadastroDeSacadoJaCadadastrado() throws BussinesException {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuSacado()
                .submeterFormularioDeAdicaoSacado()
                .selecionarFundoSacado(parametros.getFundoExistente().getNomeFundo())
                .selecionarTipoPessoaSacado()
                .preencherNomeSacado("SACADO QA TESTE")
                .preencherCpfSacado(CpfCnpjUtils.inserirFormatacao(parametros.getCnpjSacadoExistente()))
                .preencherDataInicioRelacionamentoSacado("07/07/2022")
                .selecionarPorteSacado("Mais de 1 a 2 salários mínimos")
                .selecionarClassificacaoDeRiscoSacado("Classificação de risco A")
                .selecionarTipoSociedadeSacado("INSTITUIÇÃO FINANCEIRA")
                .selecionarTipoIncricaoEstadualSacado()
                .preencherLogradouroSacado("Rua Teste")
                .preencherNumeroLogradouroSacado("422")
                .preencherCepSacado("05335-050")
                .preencherBairroSacado("Jaguaré")
                .preencherCidadeSacado("São Paulo")
                .selecionarEstadoSacado("São Paulo")
                .preencherEmailSacado("teste@teste.com")
                .selecionarAssinaturaSacado()
                .selecionarAssinaSozinhoSacado()
                .selecionarAssinaTermoCessaoSacado()
                .selecionarAssinaPorEndossoSacado()
                .selecionarEmiteDuplicataSacado()
                .preencherNomeRepresentanteSacado("TESTE REPRESENTANTE SACADO")
                .preencherEmailRepresentanteSacado("teste@teste.com")
                .selecionarTipoPessoaRepresentanteSacado()
                .preencherCnpjRepresentanteSacado("32.555.000/1040-04")
                .adicionarRepresentanteSacado()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaComErro(parametros.getCnpjSacadoExistente());

        afterEach();
        return ("Já existe Sacado cadastrado com o Cnpj/Cpf: " + parametros.getCnpjSacadoExistente()).equals(mensagemApresentada);

    }

    public Boolean testNaoPermitirCadastrarSacadoSemDadosObrigatorios() throws BussinesException {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuSacado()
                .submeterFormularioDeAdicaoSacado()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        afterEach();
        return "O campo Fundo é obrigatório".equals(mensagemApresentada);

    }

    public void afterEach  (){
        navegador.quit();
    }
}

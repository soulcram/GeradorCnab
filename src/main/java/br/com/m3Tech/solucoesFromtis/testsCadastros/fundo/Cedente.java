package br.com.m3Tech.solucoesFromtis.testsCadastros.fundo;

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


public class Cedente  {

    private WebDriver navegador;
    private String usuario;
    private String senha;
    private ParametrosTestesDto parametros;

    public Cedente(String url, String usuario, String senha, ParametrosTestesDto parametros){
    	
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

    public Boolean testPermitirCadastrarNovoCedente() throws BussinesException {

        String MensagemApresentada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuCedente()
                .submeterFormularioDeAdicaoCedente()
                .selecionarFundo(parametros.getFundoExistente().getNomeFundo())
                .preencherNomeCedente("CEDENTE QA")
                .selecionarTipoPessoaCedente()
                .preencherNumeroCnpjCedente(parametros.getDocCedenteNovo())
                .selecionarTipoInscricaoEstadualCedente()
                .selecionarRamoDeAtividade("FINANCEIRO")
                .preencherEmailCedente("teste@sinqia.com.br")
                .selecionarPorteMedio("Médio")
                .selecionarTipoSociedadeInstituicaoFinanceira("INSTITUIÇÃO FINANCEIRA")
                .selecionarClasseDeRisco("Classificação de risco A")
                .preencherInicioDeRelacionamentoCedente("09/06/2022")
                .selecionarAutorizacaoCedente()
                .preencherLogradouroCedente("RUA TESTE")
                .preencherNumeroLogradouroCedente("422")
                .preencherComplementoCedente("11 ANDAR")
                .preencherCepCedente("05335-050")
                .preencherBairroCedente("Jaguaré")
                .preencherCidadeCedente("São Paulo")
                .selecionarEstadoCedente("São Paulo")
                .selecionarCodigoBancoContaCedente("098 - BANCO DESCONHECIDO")
                .preencherNumeroAgenciaCedente("0002")
                .preencherDigitoAgenciaCedente("1")
                .preencherNumeroContaCedente("685734")
                .preencherDigitoContaCedente("4")
                .preencherDescricaoContaCedente("BANCO CEDENTE")
                .selecionarAtivarContaCedente()
                .selecionarContaPadraoCedente()
                .adicionarContaCedente()
                .preencherNomeRepresentanteCedente("REPRESENTANTE DO CEDENTE")
                .preencherEmailRepresentanteCedente("teste@sinqia.com.br")
                .selecionarTipoPessoaRepresentanteCedente()
                .preencherCnpjRepresentanteCedente("78.355.898/0001-08")
                .adicionarRepresentanteCedente()
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemComSucesso();

        //Validando a mensagem de Operação realizada com sucesso

        afterEach();
        return "Operação realizada com sucesso".equals(MensagemApresentada);
    }

    public Boolean testNaoPermitirCadastrarCedenteJaCadastrado() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuCedente()
                .submeterFormularioDeAdicaoCedente()
                .selecionarFundo(parametros.getFundoExistente().getNomeFundo())
                .preencherNomeCedente("CEDENTE QA TESTE")
                .selecionarTipoPessoaCedente()
                .preencherNumeroCnpjCedente(CpfCnpjUtils.inserirFormatacao(parametros.getCnpjCedenteExistente()))
                .selecionarTipoInscricaoEstadualCedente()
                .selecionarRamoDeAtividade("FINANCEIRO")
                .preencherEmailCedente("teste@sinqia.com.br")
                .selecionarPorteMedio("Médio")
                .selecionarTipoSociedadeInstituicaoFinanceira("INSTITUIÇÃO FINANCEIRA")
                .selecionarClasseDeRisco("Classificação de risco A")
                .preencherInicioDeRelacionamentoCedente("09/06/2022")
                .selecionarAutorizacaoCedente()
                .preencherLogradouroCedente("RUA TESTE")
                .preencherNumeroLogradouroCedente("422")
                .preencherComplementoCedente("11 ANDAR")
                .preencherCepCedente("05335-050")
                .preencherBairroCedente("Jaguaré")
                .preencherCidadeCedente("São Paulo")
                .selecionarEstadoCedente("São Paulo")
                .selecionarCodigoBancoContaCedente("098 - BANCO DESCONHECIDO")
                .preencherNumeroAgenciaCedente("0002")
                .preencherDigitoAgenciaCedente("1")
                .preencherNumeroContaCedente("685734")
                .preencherDigitoContaCedente("4")
                .preencherDescricaoContaCedente("BANCO CEDENTE")
                .selecionarAtivarContaCedente()
                .selecionarContaPadraoCedente()
                .adicionarContaCedente()
                .preencherNomeRepresentanteCedente("REPRESENTANTE DO CEDENTE")
                .preencherEmailRepresentanteCedente("teste@sinqia.com.br")
                .selecionarTipoPessoaRepresentanteCedente()
                .preencherCnpjRepresentanteCedente("26.534.036/0001-49")
                .adicionarRepresentanteCedente()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaComErro(CpfCnpjUtils.inserirFormatacao(parametros.getCnpjCedenteExistente()),parametros.getFundoExistente().getNomeFundo());

        //Validando a mensagem de de Erro
        
        afterEach();
        
        return ("Já existe Cedente com o CNPJ: " + CpfCnpjUtils.inserirFormatacao(parametros.getCnpjCedenteExistente())  + " para o fundo: " + parametros.getFundoExistente().getNomeFundo()).equals(mensagemEsperada);
        
    }

    public Boolean testNaoPermitirCadastrarCedenteSemDadosObrigatorios() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterMenuCedente()
                .submeterFormularioDeAdicaoCedente()
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        //Validando a mensagem de dados obrigatorios
        
        afterEach();
        return "O campo Fundo é obrigatório".equals(mensagemEsperada);
    }

    public void afterEach(){

       navegador.quit();
    }
}

package br.com.m3Tech.solucoesFromtis.testsCadastros.entidade;

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


public class Entidade  {

    private WebDriver navegador;


    public void beforeEach (){

    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        navegador.get("http://localhost:8080/fidcCustodia/login.xhtml");
        Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public  void testPermitirCadastrarNovaEntidadeOriginador() throws BussinesException {

               String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("fromtis")
                .informarASenha("fromtis!Q@W#E")
                .submeterFormularioDeLogin()
                .submeterMenuCadastroEntidade()
                .submeterMenuEntidade()

                .submterFormularioDeAdicaoEntidade()
                .preencherNomeEntidade("ENTIDADE ORIGINADOR QA")
                .selecionaTipoDePessoaEntidade()
                .preencherCnpjEntidade("85.406.742/0001-74")
                .preencherEmailEntidade("teste@teste.com")
                .preencherLogradouroEntidade("Rua Teste")
                .preencherNumeroEntidade("422")
                .preencherComplementoEntidade("11 Andar - Sala 1102")
                .preencherCepEntidade("05335-050")
                .preencherBairroEntidade("Jaguaré")
                .preencherCidadeEntidade("São Paulo")
                .selecionarEstadoDaEntidade("São Paulo")
                .submeterFormularioPapeisDesempenhadosEntidade()

    //Preencher formulário da Aba - Papeis Desempenhados
                .selecionarPapelOriginadorEntidade()
                .submeterFormularioAbaContaCorrenteConsultoriaEntidade()

    //Preencher formulário da Aba - Conta Corrente Consultoria
                .selecionarCodBanco("198")
                .preencherAgenciaContaEntidade("0010")
                .preencherNumeroContaEntidade("123456")
                .preencherDescricaoContaEntidade("CONTA ORIGINADOR")
                .submeterFormularioDeContaCorrente()
                .submeterFormularioAbaRepresentantesEntidade()

   //Preencher formulário da Aba - Representante
                .preencherNomeRepresentante("REPRESENTANTE TESTE")
                .preencherEmailRepresentante("qa@teste.com")
                .selecionarTipoPessoaRepresentante()
                .preencherCnpjRepresentante("34.803.432/0001-52")
                .preencherNumeroDeTelefonRepresentante("(11) 45578-5455")
                .submeterFormularioDeRepresentante()
                .submeterFormularioAbaPartesRelacionadasEntidade()

    //Preencher formulário da Aba - Partes Relacionadas
                .preencherNomePartesRelacionadas("TESTE")
                .selecionarTipoPessoaPartesRelacionadas()
                .preencherCnpjPartesRelacionadas("77.841.354/0001-93")
                .submeterFormularioDePartesRelacionadas()
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemApresentadaComSucesso();

                Assertions.assertEquals("Operação realizada com sucesso",mensagemApresentada);
    }
//    @Test
//    @DisplayName("Não permitir cadastrar a mesma entidade originador")
//    public  void testNaoPermitirCadastrarEntidadeOriginadorJaCadastrada() {
//
//        String mensagemApresentada = new LoginPage(navegador)
//                .informarOUsuario("fromtis")
//                .informarASenha("fromtis!Q@W#E")
//                .submeterFormularioDeLogin()
//                .submeterMenuCadastroEntidade()
//                .submeterMenuEntidade()
//
//                .submterFormularioDeAdicaoEntidade()
//                .preencherNomeEntidade("ENTIDADE ORIGINADOR TESTE")
//                .selecionaTipoDePessoaEntidade()
//                .preencherCnpjEntidade("78.520.313/0001-68")
//                .preencherEmailEntidade("teste@teste.com")
//                .preencherLogradouroEntidade("Rua Teste")
//                .preencherNumeroEntidade("422")
//                .preencherComplementoEntidade("11 Andar - Sala 1102")
//                .preencherCepEntidade("05335-050")
//                .preencherBairroEntidade("Jaguaré")
//                .preencherCidadeEntidade("São Paulo")
//                .selecionarEstadoDaEntidade("São Paulo")
//
//                .submeterFormularioPapeisDesempenhadosEntidade()
//                .selecionarPapelOriginadorEntidade()
//
//                .submeterFormularioAbaContaCorrenteConsultoriaEntidade()
//                .selecionarCodBanco("198")
//                .preencherAgenciaContaEntidade("0010")
//                .preencherNumeroContaEntidade("123456")
//                .preencherDescricaoContaEntidade("CONTA ORIGINADOR")
//                .submeterFormularioDeContaCorrente()
//
//                .submeterFormularioAbaRepresentantesEntidade()
//                .preencherNomeRepresentante("REPRESENTANTE TESTE")
//                .preencherEmailRepresentante("qa@teste.com")
//                .selecionarTipoPessoaRepresentante()
//                .preencherCnpjRepresentante("34.803.432/0001-52")
//                .preencherNumeroDeTelefonRepresentante("(11) 45578-5455")
//                .submeterFormularioDeRepresentante()
//
//                .submeterFormularioAbaPartesRelacionadasEntidade()
//                .preencherNomePartesRelacionadas("TESTE")
//                .selecionarTipoPessoaPartesRelacionadas()
//                .preencherCnpjPartesRelacionadas("77.841.354/0001-93")
//                .submeterFormularioDePartesRelacionadas()
//
//                .submeterFormulariodeAdicaoComErro()
//                .capturarMensagemApresentadaComErro();
//
//        Assertions.assertEquals("CPf ou CNPJ já cadastrado",mensagemApresentada);
//    }
//    @Test
//    @DisplayName("Não permitir cadastrar entidade, sem o preenchimento dos dados obrigatórios")
//    public  void testNaoPermitirCadastrarEntidadeOriginadorSemDadosObrigatorios() {
//
//        String mensagemApresentada = new LoginPage(navegador)
//                .informarOUsuario("fromtis")
//                .informarASenha("fromtis!Q@W#E")
//                .submeterFormularioDeLogin()
//                .submeterMenuCadastroEntidade()
//                .submeterMenuEntidade()
//
//                .submterFormularioDeAdicaoEntidade()
//                .submeterFormulariodeAdicaoComErro()
//                .capturarMensagemApresentadaDadosObrigatorios();
//
//        Assertions.assertEquals("O campo Razão Social é obrigatório",mensagemApresentada);
//    }


    public void afterEach(){
       navegador.quit();

    }

}

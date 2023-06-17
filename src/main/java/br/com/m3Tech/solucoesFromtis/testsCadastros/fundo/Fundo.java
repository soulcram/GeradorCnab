package br.com.m3Tech.solucoesFromtis.testsCadastros.fundo;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.m3Tech.solucoesFromtis.dto.ParametrosTestesDto;
import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
import br.com.m3Tech.solucoesFromtis.paginas.login.LoginPage;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorNomeFake;
import br.com.m3Tech.solucoesFromtis.testsCadastros.MessageErro;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Fundo extends MessageErro {

    private WebDriver navegador;

    private String usuario;
    private String senha;
    private ParametrosTestesDto parametros;
    
    private GeradorNomeFake nomeFake = new GeradorNomeFake();

    public Fundo(String url, String contextoCustodia, String usuario, String senha, ParametrosTestesDto parametros){
    	
    	WebDriverManager.chromedriver().setup();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
		this.navegador = new ChromeDriver(options);
    	
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        
        this.usuario = usuario;
        this.senha = senha;
        this.parametros = parametros;

        navegador.get(url+ (StringUtils.isEmpty(contextoCustodia) ? "" : contextoCustodia) + "/login.xhtml");
        Assertions.assertEquals("Sinqia - FIDC Custódia", navegador.getTitle());
    }

    public Boolean testPermitirCadastroDeNovoFundo() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterSegundoMenuFundo()
                .submterFormularioDeAdicaoFundo()
                .preencherCodigoIsinFundo(ValorAleatorioUtil.getValor(5))
                .preencherCodigoAnbidFundo("002")
                .preencherInicioProcessamentoInputDatedFundo("21/06/2022")
                .selecionarTipoContagemDiasFundo()
                .selecionarTipoDoFundo("204")
                .selecionarOpcaoDoFundo()
                .selecionarSituacaoAtualDoFundo("1")
                .selecionarNivelDeRiscoDoFundo("3")
                .selecionarStatusProcessamentoDoFundo("Disponível")
                .selecionarLayoutAquisicaoDoFundo("16")
                .selecionarLayoutOcorrenciaDoFundo("16")
                .submeterFormularioAbaDadosAdicionaisFundo()

                //Preenchimento do formulário Aba Dados Adicionais
                .preencherNomeFundo("FUNDO " + nomeFake.gerarNomeEmpresa() )
                .preencherCnpjFundo(parametros.getDocFundoNovo())
                .selecionaragenciaClassRisco()
                .selecionarNomeAdministrador()
                .selecionarAgenteEscriturador()
                .selecionarNomeCustodiante()
                .selecionarControladorAtivo()
                .selecionarNomeGestor()
                .selecionarNomeControladorPassivo()
                .selecionarNomeAuditor()
                .preencherPrazoRecepcaoChaveNfe("5")
                .preencherPrazoRecepcaoLastro("5")
                .preencherValorRoboAssinatura("2000")
                .selecionaNaoConsiderarPosFixado()
                .selecionaAutorizaFechamentoAutomatico()
                .selecionaOperaComPLZerado()
                .selecionarNomeBancoRetorno("098 - BANCO DESCONHECIDO")
                .preencherCodigoOriginador("00000000000000000001")
                .preencherDataInicioRelacionamentoCobranca("21/06/2022")
                .adicionarCobranca()
                .submeterFormularioAbaRegraEnquadramentoFundo()

                //Preenchimento do formulário Aba Regra Enquadramento
//                .selecionarPerfilEnquadramento()
//                .selecionarDataInicioRelacionamentoEnquadramento()
//                .adicionarPerfilEnquadramento()
//                .ativarEnquadramento()
                .submeterFormularioAbaPerfisPDDFundo()

                //Preenchimento do formulário Aba Perfis PDD
                .selecionarPerfilPdd("PDD TESTE")
                .adicionarPerfilPdd()
                .submeterFormularioAbaPrecificaçãoFundo()

                //Preenchimento do formulário Aba Precificação
                .selecionarModeloPrecificacao("Por Recebível")
                .selecionarAplicaSePrecificacao("Toda Carteira")

                //Preenchimento do formulário Aba Carteira Sistema de Ativos
                .submeterCarteiraSistemaDeAtivosFundo()
                .selecionarTipoCotaSac("Única")
                .preencherNumeroCodigoCarteiraSac("0001")
                .adicionarTipoCota()
                .submeterFormularioAbaAtivosFundo()

                //Preenchimento do formulário Aba Ativos
                .preencherAtivoAVencer("A")
                .preencherAtivoVencidos("V")
                .preencherAtivoPDD("P")
                .submeterFormularioAbaOriginadoresFundo()

                //Preenchimento do formulário Aba Originadores
                .selecionarOriginador()
                .selecionarContaOriginador()
                .selecionarDataInicioRelacionamentoOriginador()
                .preencherCodigoOriginador("00000000000000000001")
                .adicionarOriginador()
                .submeterFormularioAbaRepresentantesFundo()

                //Preenchimento do formulário Aba Representante
                .preencherNomeRepresentante("Representante Teste")
                .preencherEmailRepresentante("teste@sinqia.com")
                .selecionarTipoPessoaRepresentante()
                .preencherNumeroTelefoneRepresentante("(11) 44557-7878")
                .preencherNumeroCnpjRepresentante("63.249.458/0001-07")
                .adicionarRepresentante()
                .submeterFormularioAbaLiquidacaoFundo()

                //Preenchimento do formulário Aba Liquidação
                .ativaLiquidacao()
                .ativarSolicitacaoPagamentoLiquidacao()
                .preencherLimiteReembolsoLiquidacao("10,00")
                .selecionarPagarLiquidacaoVia("BANCO_COBRADOR")
                .selecionarPagarSolicitacaoLiquidacao("BANCO_COBRADOR")
                .preencherPercentualReembolsoLiquidacao("10,00")
                .selecionarNSeuNumeroCompostoLiquidacao()
                .submeterFormularioAbaCertificadoraFundo()

                //Preenchimento do formulário Aba Certificadora
//                .ativarCertificadora()
//                .selecionarCertificadora("CERTIFICADORA QA TESTE")
//                .selecionarCertificadoraPadrao()
//                .adicionarCertificadora()
                .submeterFormularioAbaContasCorrenteFundo()

                //Preenchimento do formulário Aba Contas Corrente
                .selecionarCodigoBancoContaCorrente("098 - BANCO DESCONHECIDO")
                .preencherNumeroAgenciaContaCorrente("0022")
                .preencherDigitoAgenciaContaCorrente("8")
                .preencherNumeroContaCorrente("223344")
                .preencherDescricaoContaCorrente("CONTA FUNDO")
                .submeterFormulariodeAdicaoComSucesso()
                .capturarMensagemComSucesso();
        		
        
        		this.navegador.quit();
        return "Operação realizada com sucesso".equals(mensagemEsperada);
    }

    public Boolean testNaoPermitidoCadastroDeFundoJaCadastrado() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterSegundoMenuFundo()
                .submterFormularioDeAdicaoFundo()
                .preencherCodigoIsinFundo("001")
                .preencherCodigoAnbidFundo("001")
                .preencherInicioProcessamentoInputDatedFundo("21/06/2022")
                .selecionarTipoContagemDiasFundo()
                .selecionarTipoDoFundo("204")
                .selecionarOpcaoDoFundo()
                .selecionarSituacaoAtualDoFundo("1")
                .selecionarNivelDeRiscoDoFundo("3")
                .selecionarStatusProcessamentoDoFundo("Disponível")
                .selecionarLayoutAquisicaoDoFundo("16")
                .selecionarLayoutOcorrenciaDoFundo("16")
                .submeterFormularioAbaDadosAdicionaisFundo()

                //Preenchimento do formulário Aba Dados Adicionais
                .preencherNomeFundo("FUNDO QA TESTE")
                .preencherCnpjFundo(parametros.getCnpjFundoExistente())
                .selecionaragenciaClassRisco()
                .selecionarNomeAdministrador()
                .selecionarAgenteEscriturador()
                .selecionarNomeCustodiante()
                .selecionarControladorAtivo()
                .selecionarNomeGestor()
                .selecionarNomeControladorPassivo()
                .selecionarNomeAuditor()
                .preencherPrazoRecepcaoChaveNfe("5")
                .preencherPrazoRecepcaoLastro("5")
                .selecionaNaoConsiderarPosFixado()
                .selecionaAutorizaFechamentoAutomatico()
                .selecionaOperaComPLZerado()
                .selecionarNomeBancoRetorno("098 - BANCO DESCONHECIDO")
                .preencherCodigoOriginador("00000000000000000001")
                .preencherDataInicioRelacionamentoCobranca("21/06/2022")
                .adicionarCobranca()
                .submeterFormularioAbaRegraEnquadramentoFundo()

                //Preenchimento do formulário Aba Regra Enquadramento
//                .selecionarPerfilEnquadramento()
//                .selecionarDataInicioRelacionamentoEnquadramento()
//                .adicionarPerfilEnquadramento()
//                .ativarEnquadramento()
                .submeterFormularioAbaPerfisPDDFundo()

                //Preenchimento do formulário Aba Perfis PDD
                .selecionarPerfilPdd("PDD TESTE")
                .adicionarPerfilPdd()
                .submeterFormularioAbaPrecificaçãoFundo()

                //Preenchimento do formulário Aba Precificação
                .selecionarModeloPrecificacao("Por Recebível")
                .selecionarAplicaSePrecificacao("Toda Carteira")

                //Preenchimento do formulário Aba Carteira Sistema de Ativos
                .submeterCarteiraSistemaDeAtivosFundo()
                .selecionarTipoCotaSac("Única")
                .preencherNumeroCodigoCarteiraSac("0001")
                .adicionarTipoCota()
                .submeterFormularioAbaAtivosFundo()

                //Preenchimento do formulário Aba Ativos
                .preencherAtivoAVencer("A")
                .preencherAtivoVencidos("V")
                .preencherAtivoPDD("P")
                .submeterFormularioAbaOriginadoresFundo()

                //Preenchimento do formulário Aba Originadores
                .selecionarOriginador()
                .selecionarContaOriginador()
                .selecionarDataInicioRelacionamentoOriginador()
                .preencherCodigoOriginador("00000000000000000001")
                .adicionarOriginador()
                .submeterFormularioAbaRepresentantesFundo()

                //Preenchimento do formulário Aba Representante
                .preencherNomeRepresentante("Representante Teste")
                .preencherEmailRepresentante("teste@sinqia.com")
                .selecionarTipoPessoaRepresentante()
                .preencherNumeroTelefoneRepresentante("(11) 44557-7878")
                .preencherNumeroCnpjRepresentante("63.249.458/0001-07")
                .adicionarRepresentante()
                .submeterFormularioAbaLiquidacaoFundo()

                //Preenchimento do formulário Aba Liquidação
                .ativaLiquidacao()
                .ativarSolicitacaoPagamentoLiquidacao()
                .preencherLimiteReembolsoLiquidacao("10,00")
                .selecionarPagarLiquidacaoVia("BANCO_COBRADOR")
                .selecionarPagarSolicitacaoLiquidacao("BANCO_COBRADOR")
                .preencherPercentualReembolsoLiquidacao("10,00")
                .selecionarNSeuNumeroCompostoLiquidacao()
                .submeterFormularioAbaCertificadoraFundo()

                //Preenchimento do formulário Aba Certificadora
//                .ativarCertificadora()
//                .selecionarCertificadora("CERTIFICADORA QA TESTE")
//                .selecionarCertificadoraPadrao()
//                .adicionarCertificadora()
                .submeterFormularioAbaContasCorrenteFundo()

                //Preenchimento do formulário Aba Contas Corrente
                .selecionarCodigoBancoContaCorrente("098 - BANCO DESCONHECIDO")
                .preencherNumeroAgenciaContaCorrente("0022")
                .preencherDigitoAgenciaContaCorrente("8")
                .preencherNumeroContaCorrente("223344")
                .preencherDescricaoContaCorrente("CONTA FUNDO")
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaComErro(parametros.getCnpjFundoExistente());

        this.navegador.quit();
        
        return ("Já existe um Fundo para o CNPJ: ("+parametros.getCnpjFundoExistente()+")").equals(mensagemEsperada);
    }

    public Boolean testNaoPermitirCadastrarFundoSemDadosObrigatorios() throws BussinesException {

        String mensagemEsperada = new LoginPage(navegador)
                .informarOUsuario(usuario)
                .informarASenha(senha)
                .submeterFormularioDeLogin()
                .submeterMenuCadastroFundo()
                .submeterPrimeiroMenuFundo()
                .submeterSegundoMenuFundo()
                .submterFormularioDeAdicaoFundo()
                .submeterFormulariodeAdicaoComErro()
                .capturarMensagemApresentadaDadosObrigatorios();

        this.navegador.quit();
        
        return "O campo Código ISIN Aba Dados Cadastrais é obrigatório".equals(mensagemEsperada);
    }

    public void afterEach(){

        navegador.quit();
    }

}

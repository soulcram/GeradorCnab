package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.base.Preconditions;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IIndexadorService;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.util.LayoutUtils;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.BigDecimalUtils;
import br.com.m3Tech.utils.BooleanUtils;
import br.com.m3Tech.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabRecompraController implements Serializable {

    private static final long serialVersionUID = 1L;


    private static final String VOLTAR = "/pages/cadastros/index.xhtml";


    @Autowired
    private IBaseService baseService;
    @Autowired
    private IFundoService fundoService;
    @Autowired
    private IBancoService bancoService;
    @Autowired
    private IOriginadorService originadorService;
    @Autowired
    private IMovimentoService movimentoService;
    @Autowired
    private IConfGlobalService confGlobalService;
    @Autowired
    private IGeradorCnab geradorCnab;
    @Autowired
    private ISacadoService sacadoService;
    @Autowired
    private ICedenteService cedenteService;
    @Autowired
    private ITipoRecebivelService tipoRecebivelService;
    @Autowired
    private IIndexadorService indexadorService;
    @Autowired
    private IRiscoService riscoService;


    private Integer baseSelecionada;
    private Integer fundoSelecionado;
    private Integer bancoSelecionado;
    private Integer layoutSelecionado;
    private Integer originadorSelecionado;
    private Integer movimentoBaixaSelecionado;
    private Integer movimentoAquisicaoSelecionado;
    private Integer cedenteSelecionado;
    private Integer sacadoSelecionado;
    private Integer tipoRecebivelSelecionado;
    private Integer indexadorSelecionado;
    private Integer riscoSelecionado;

    private String path;
    private String chaveNfe;
    private String seuNumero;
    private String numeroDocumento;
    private String termoCessao;
    private String variacaoCambial;

    private TituloDto titulo;

    private Boolean coobrigacao;
    private Boolean importacaoAutomatica;

    private LocalDate dataGravacao;
    private LocalDate dataVencimento;
    private LocalDate dataCarencia;

    private BigDecimal valorAquisicao;
    private BigDecimal valorTitulo;
    private BigDecimal taxaJuros;
    private BigDecimal taxaJurosIndexador;

    private CnabDto cnab;

    private List<Base> bases;
    private List<FundoDto> fundos;
    private List<BancoDto> bancos;
    private List<OriginadorDto> originadores;
    private List<MovimentoDto> movimentosBaixaRecompra;
    private List<MovimentoDto> movimentosAquisicaoRecompra;
    private List<TituloDto> titulos;
    private List<TituloDto> titulosEmEstoque;
    private List<TipoRecebivelDto> tiposRecebiveis;
    private List<CedenteDto> cedentes;
    private List<SacadoDto> sacados;
    private List<IndexadorDto> indexadores;
    private List<RiscoDto> riscos;

    @PostConstruct
    public void init() {

        bases = baseService.findAll();
        fundos = new ArrayList<>();
        bancos = new ArrayList<>();
        originadores = new ArrayList<>();
        movimentosBaixaRecompra = new ArrayList<>();
        movimentosAquisicaoRecompra = new ArrayList<>();
        titulosEmEstoque = new ArrayList<>();
        cnab = new CnabDto();
        path = confGlobalService.getConfGlobal().getPath();
        titulo = new TituloDto();
        cedentes = new ArrayList<>();
        sacados = new ArrayList<>();
        tiposRecebiveis = new ArrayList<>();
        indexadores = new ArrayList<>();
        riscos = new ArrayList<>();
        valorAquisicao = BigDecimal.ZERO;
        valorTitulo = BigDecimal.ZERO;

    }

    public void selecionandoBase() {
        System.out.println("Selecionando Base " + baseSelecionada);
        cnab = new CnabDto();
        atualizarFundos();
        atualizarBancos();
        atualizarIndexadores();
        atualizarRiscos();
        atualizarTitulosEmEstoque();

    }


    public List<LayoutEnum> getLayoutsRemessa() {
        List<LayoutEnum> retorno = LayoutEnum.findAllRemessa();

        return retorno;
    }

    public void selecionandoFundo() {
        System.out.println("Selecionando Fundo " + fundoSelecionado);
        cnab = new CnabDto();
        Optional<FundoDto> optional = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst();

        if (optional.isPresent()) {
            try {
                Base base = baseService.findById(baseSelecionada);

                Connection con = Conexao.getConnection(base);
                FundoDto fundo = optional.get();

                dataGravacao = fundo.getDataFundo();
                dataVencimento = dataGravacao.plusDays(45);
                layoutSelecionado = fundo.getLayoutAquisicao();


                originadores = originadorService.findAll(con, fundo.getIdFundo());
                movimentosBaixaRecompra = movimentoService.findAllMovimentosRecompraBaixa(con, layoutSelecionado);
                movimentosAquisicaoRecompra = movimentoService.findAllMovimentosRecompraAquisicao(con, layoutSelecionado);
                cedentes = cedenteService.findAll(con, fundo.getIdFundo(), base);
                sacados = sacadoService.findAll(con, fundo.getIdFundo());
                tiposRecebiveis = tipoRecebivelService.findAllTipoRecebivel(con, layoutSelecionado);

                if (!cedentes.isEmpty()) {
                    coobrigacao = BooleanUtils.getBooleanOfString(cedentes.get(0).getCoobrigacao());
                }

                atualizarTitulosEmEstoque();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void selecionandoLayout() {
        try {

            if (baseSelecionada == null) {
                return;
            }

            Base base = baseService.findById(baseSelecionada);

            Connection con = Conexao.getConnection(base);

            movimentosBaixaRecompra = movimentoService.findAllMovimentosRecompraBaixa(con, layoutSelecionado);
            movimentosAquisicaoRecompra = movimentoService.findAllMovimentosRecompraAquisicao(con, layoutSelecionado);
            tiposRecebiveis = tipoRecebivelService.findAllTipoRecebivel(con, layoutSelecionado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selecionandoCedente() {

        Optional<CedenteDto> optional = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();

        if (optional.isPresent()) {

            CedenteDto cedenteDto = optional.get();
            coobrigacao = BooleanUtils.getBooleanOfString(cedenteDto.getCoobrigacao());
        }

    }

    private void atualizarIndexadores() {

        try {

            Base base = baseService.findById(baseSelecionada);

            indexadores = indexadorService.findAll(Conexao.getConnection(base));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarRiscos() {

        try {

            Base base = baseService.findById(baseSelecionada);

            riscos = riscoService.findAll(Conexao.getConnection(base));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selecionandoImportacaoAutomatica() {

        try {
            Preconditions.checkNotNull(baseSelecionada, "É obrigatório selecionar uma base");
            Preconditions.checkNotNull(fundoSelecionado, "É obrigatório selecionar uma fundo");

            Base base = baseService.findById(baseSelecionada);
            Optional<FundoDto> optional = fundos.stream().filter(c -> c.getIdFundo().equals(fundoSelecionado)).findFirst();

            if (optional.isPresent()) {
                FundoDto fundoSelecionado = optional.get();

                path = (confGlobalService.getPathSalvarArquivo(Conexao.getConnection(base), importacaoAutomatica, base.getVersaoMercado(), fundoSelecionado));
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    private void atualizarFundos() {

        try {

            Base base = baseService.findById(baseSelecionada);

            fundos = fundoService.findAll(Conexao.getConnection(base));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void atualizarTitulosEmEstoque() {

        try {
            Base base = baseService.findById(baseSelecionada);
            titulosEmEstoque = movimentoService.findAllTituloEmEstoqueByFundo(Conexao.getConnection(base), fundoSelecionado, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarBancos() {

        try {

            Base base = baseService.findById(baseSelecionada);

            bancos = bancoService.findAll(Conexao.getConnection(base));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarBases() {
        System.out.println("Atualizar Base");

        bases = baseService.findAll();
    }

    public void addTituloBaixa(TituloDto item) {

        Optional<MovimentoDto> optionalMovimento = movimentosBaixaRecompra.stream().filter(c -> c.getIdMovimento().equals(movimentoBaixaSelecionado)).findFirst();

        if (optionalMovimento.isPresent()) {
            item.setMovimento(optionalMovimento.get());
        }

        valorAquisicao = valorAquisicao.add(item.getValorAquisicao());
        valorTitulo = valorTitulo.add(item.getValorTitulo());

        this.cnab.getTitulos().add(item.getCopy());
        titulosEmEstoque.remove(item);
    }

    public void addTituloAquisicao() {

        if (fundoSelecionado == null || fundoSelecionado < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Selecione um fundo."));
            return;
        }

        if (BigDecimalUtils.isNullOrZero(valorTitulo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor do título é obrigatório."));
            return;
        }

        if (35 != layoutSelecionado && BigDecimalUtils.isNullOrZero(valorAquisicao)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição é obrigatório."));
            return;
        }

        if (35 != layoutSelecionado && valorAquisicao.compareTo(valorTitulo) > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição não pode ser maior que o valor do título."));
            return;
        }

        if (35 == layoutSelecionado && BigDecimalUtils.isNullOrZero(taxaJuros)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Taxa de Juros é obrigatório."));
            return;
        }

        if (cnab.getTitulos().size() > 999997) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997."));
            return;
        }

        if (StringUtils.emptyOrNull(seuNumero)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seu Numero é obrigatório."));
            return;
        }

        if (StringUtils.emptyOrNull(numeroDocumento)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Numero Documento é obrigatório."));
            return;
        }

        if (StringUtils.emptyOrNull(termoCessao)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Termo Cessão é obrigatório."));
            return;
        }
        Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
        Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();
        Optional<BancoDto> optionalBanco = bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst();
        Optional<MovimentoDto> optionalMovimento = movimentosAquisicaoRecompra.stream().filter(c -> c.getIdMovimento().equals(movimentoAquisicaoSelecionado)).findFirst();
        Optional<IndexadorDto> optionalIndexador = indexadores.stream().filter(c -> c.getIdIndexador().equals(indexadorSelecionado)).findFirst();
        Optional<RiscoDto> optionalRisco = riscos.stream().filter(c -> c.getIdRisco().equals(riscoSelecionado)).findFirst();
        Optional<TipoRecebivelDto> optionalTipoRecebivel = tiposRecebiveis.stream().filter(c -> c.getIdTipoRecebivel().equals(tipoRecebivelSelecionado)).findFirst();

        TituloDto titulo = new TituloDto(optionalCedente.isPresent() ? optionalCedente.get() : null,
                optionalSacado.isPresent() ? optionalSacado.get() : null,
                optionalBanco.isPresent() ? optionalBanco.get().getCodigoBanco() : null,
                dataVencimento,
                null, //dataLiquidacao,
                dataCarencia, //dataCarencia,
                null,
                optionalMovimento.isPresent() ? optionalMovimento.get() : null,
                optionalIndexador.isPresent() ? optionalIndexador.get() : null, //indexador,
                optionalRisco.isPresent() ? optionalRisco.get() : null, //risco,
                seuNumero,
                coobrigacao ? "01" : "02",
                null, //nossoNumero,
                numeroDocumento,
                optionalTipoRecebivel.isPresent() ? optionalTipoRecebivel.get().getCdEspecie() : null, //especie,
                termoCessao,
                chaveNfe,
                variacaoCambial, //variacaoCambial,
                null, //docOrigRecebivel,
                null, //nomeOrigRecebivel,
                null, //valorPago,
                valorTitulo,
                valorAquisicao,
                null, //valorAbatimento,
                taxaJurosIndexador, //taxaJurosIndexador
                taxaJuros,
                null,
                null,
                null,
                null,
                null
        );

        cnab.getTitulos().add(titulo.getCopy());

        clear();

    }

    public void gerar() {
        try {

            if (cnab.getTitulos() == null || cnab.getTitulos().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum título foi adicionado.."));
                return;
            }

            cnab.setBanco(bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst().get());
            cnab.setDataGravacao(dataGravacao);
            cnab.setFundo(fundos.stream().filter(c -> c.getIdFundo().equals(fundoSelecionado)).findFirst().get());
            cnab.setLayout(LayoutEnum.parse(layoutSelecionado));
            cnab.setOriginador(originadores.stream().filter(c -> c.getIdOriginador().equals(originadorSelecionado)).findFirst().get());

            ConfGlobal confGlobal = confGlobalService.getConfGlobal();
            cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
            confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
            if (!importacaoAutomatica) {
                confGlobal.setPath(path);
            }

            this.confGlobalService.salvar(confGlobal);

            geradorCnab.gerar(cnab, "RECOMPRA", importacaoAutomatica, path);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));

            cnab = new CnabDto();

            clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarNfe() {
        this.chaveNfe = "31190600006388319890559240000000311006164587";
    }

    public void gerarSeuNumero() {
        this.seuNumero = ValorAleatorioUtil.getValor(25);
    }

    public void gerarNumeroDocumento() {
        this.numeroDocumento = ValorAleatorioUtil.getValor(10);
    }

    public void gerarTermoCessao() {
        this.termoCessao = ValorAleatorioUtil.getValor(10);
    }

    public void gerarTaxaJuros() {
        this.taxaJuros = ValorAleatorioUtil.getTaxaDecimal();
    }

    private boolean validarValorParcial() {

        try {

            if (titulo.getValorPago() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "É obrigatorio informar um valor para valor Parcial"));
                return false;
            }

            if (titulo.getValorPago().compareTo(BigDecimal.ZERO) == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Valor da Parcial não pode ser zero."));
                return false;
            }


            if (titulo.getValorPago().compareTo(titulo.getValorTitulo()) >= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Valor da Parcial não pode ser maior que o valor do titulo."));
                return false;
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
            return false;
        }

        return true;

    }

    private void clear() {
        this.seuNumero = "";
        this.numeroDocumento = "";
        this.termoCessao = "";
        this.titulo = new TituloDto();
    }


    public void removerTitulo(TituloDto titulo) {
        cnab.getTitulos().remove(titulo);
    }

    public Boolean getExibirChaveNfe() {

        return LayoutUtils.exibirChaveNfe(layoutSelecionado);
    }

    public Boolean getExibirIndexadores() {

        return LayoutUtils.exibirIndexadores(layoutSelecionado);
    }

    public Boolean getExibirDataCarencia() {

        return LayoutUtils.exibirDataCarencia(layoutSelecionado);
    }

    public Boolean getExibirTaxaJurosIndexador() {

        return LayoutUtils.exibirTaxaJurosIndexador(layoutSelecionado);
    }

    public Boolean getExibirVariacaoCambial() {

        return LayoutUtils.exibirVariacaoCambial(layoutSelecionado);
    }

    public Boolean getExibirRisco() {

        return LayoutUtils.exibirRisco(layoutSelecionado);
    }

    public Boolean getExibirTaxaJuros() {

        if (layoutSelecionado == null) {
            return false;
        }

        if (layoutSelecionado == 35) {
            return true;
        }

        return false;
    }

    public String voltar() {
        return VOLTAR;
    }

}

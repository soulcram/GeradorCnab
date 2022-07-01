package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Duration;
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
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabAquisicaoDinamicoController implements Serializable {

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
    private ISacadoService sacadoService;
    @Autowired
    private ICedenteService cedenteService;
    @Autowired
    private IMovimentoService movimentoService;
    @Autowired
    private ITipoRecebivelService tipoRecebivelService;
    @Autowired
    private IConfGlobalService confGlobalService;
    @Autowired
    private IGeradorCnab geradorCnab;
    @Autowired
    private IIndexadorService indexadorService;
    @Autowired
    private IRiscoService riscoService;


    private Integer baseSelecionada;
    private Integer fundoSelecionado;
    private Integer bancoSelecionado;
    private Integer layoutSelecionado;
    private Integer originadorSelecionado;
    private Integer sacadoSelecionado;
    private Integer cedenteSelecionado;
    private Integer movimentoSelecionado;
    private Integer tipoRecebivelSelecionado;
    private Integer indexadorSelecionado;
    private Integer riscoSelecionado;
    private Integer quantidadeTitulos;

    private String path;

    private Boolean coobrigacao;
    private Boolean importacaoAutomatica;

    private LocalDate dataGravacao;
    private LocalDate dataVencimento;

    private BigDecimal valorAquisicao;
    private BigDecimal valorTitulo;
    private BigDecimal taxaJuros;
    private BigDecimal taxaJurosIndexador;

    private CnabDto cnab;

    private List<Base> bases;
    private List<FundoDto> fundos;
    private List<BancoDto> bancos;
    private List<OriginadorDto> originadores;
    private List<CedenteDto> cedentes;
    private List<SacadoDto> sacados;
    private List<MovimentoDto> movimentos;
    private List<TipoRecebivelDto> tiposRecebiveis;
    private List<TituloDto> titulos;
    private List<IndexadorDto> indexadores;
    private List<RiscoDto> riscos;

    private Bucket bucket;

    @PostConstruct
    public void init() {

        bases = baseService.findAll();
        fundos = new ArrayList<>();
        bancos = new ArrayList<>();
        originadores = new ArrayList<>();
        cedentes = new ArrayList<>();
        sacados = new ArrayList<>();
        movimentos = new ArrayList<>();
        tiposRecebiveis = new ArrayList<>();
        indexadores = new ArrayList<>();
        riscos = new ArrayList<>();
        cnab = new CnabDto();
        path = confGlobalService.getConfGlobal().getPath();
        quantidadeTitulos = 1;

        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();


    }

    public void selecionandoBase() {
        System.out.println("Selecionando Base " + baseSelecionada);
        cnab = new CnabDto();
        atualizarFundos();
        atualizarBancos();
        atualizarIndexadores();
        atualizarRiscos();

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
                layoutSelecionado = fundo.getLayoutAquisicao();
                dataVencimento = dataGravacao.plusDays(45);


                originadores = originadorService.findAll(con, fundo.getIdFundo());
                cedentes = cedenteService.findAll(con, fundo.getIdFundo(), base);
                sacados = sacadoService.findAll(con, fundo.getIdFundo());
                movimentos = movimentoService.findAllMovimentosAquisicao(con, layoutSelecionado);
                tiposRecebiveis = tipoRecebivelService.findAllTipoRecebivel(con, layoutSelecionado);

                if (!cedentes.isEmpty()) {
                    coobrigacao = BooleanUtils.getBooleanOfString(cedentes.get(0).getCoobrigacao());
                }
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

            movimentos = movimentoService.findAllMovimentosAquisicao(con, layoutSelecionado);
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

    private void atualizarBancos() {

        try {

            Base base = baseService.findById(baseSelecionada);

            bancos = bancoService.findAll(Conexao.getConnection(base));

        } catch (Exception e) {
            e.printStackTrace();
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

    public void atualizarBases() {
        System.out.println("Atualizar Base");

        bases = baseService.findAll();
    }

    //	public void adicionarTitulo() {
//
////		BigDecimal vlTaxaJurosIndexador = null;
////				
////		if(!"".equals(taxaJurosIndexador.getText())) {
////			vlTaxaJurosIndexador = new BigDecimal(taxaJurosIndexador.getText().replaceAll(",", "."));
////		}
//		
//		if(fundoSelecionado == null || fundoSelecionado < 1) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Selecione um fundo."));
//			return;
//		}
//		
//		if( BigDecimalUtils.isNullOrZero(valorTitulo)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor do título é obrigatório."));
//			return;
//		}
//		
//		if( 35 != layoutSelecionado && BigDecimalUtils.isNullOrZero(valorAquisicao)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição é obrigatório."));
//			return;
//		}
//		
//		if( 35 != layoutSelecionado && valorAquisicao.compareTo(valorTitulo) > 0) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição não pode ser maior que o valor do título."));
//			return;
//		}
//		
//		if( 35 == layoutSelecionado && BigDecimalUtils.isNullOrZero(taxaJuros)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Taxa de Juros é obrigatório."));
//			return;
//		}
//		
//		if(cnab.getTitulos().size() > 999997) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997."));
//			return;
//		}
//		
//		if(StringUtils.emptyOrNull(seuNumero)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seu Numero é obrigatório."));
//			return;
//		}
//		
//		if(StringUtils.emptyOrNull(numeroDocumento)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Numero Documento é obrigatório."));
//			return;
//		}
//		
//		if(StringUtils.emptyOrNull(termoCessao)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Termo Cessão é obrigatório."));
//			return;
//		}
//		Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
//		Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();
//		Optional<BancoDto> optionalBanco = bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst();
//		Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();
//		Optional<IndexadorDto> optionalIndexador = indexadores.stream().filter(c -> c.getIdIndexador().equals(indexadorSelecionado)).findFirst();
//		Optional<RiscoDto> optionalRisco = riscos.stream().filter(c -> c.getIdRisco().equals(riscoSelecionado)).findFirst();
//		Optional<TipoRecebivelDto> optionalTipoRecebivel = tiposRecebiveis.stream().filter(c -> c.getIdTipoRecebivel().equals(tipoRecebivelSelecionado)).findFirst();
//		
//		TituloDto titulo = new TituloDto(optionalCedente.isPresent() ? optionalCedente.get() : null, 
//				optionalSacado.isPresent() ? optionalSacado.get() : null, 
//				optionalBanco.isPresent() ? optionalBanco.get().getCodigoBanco() : null, 
//				dataVencimento, 
//				null, //dataLiquidacao, 
//				dataCarencia, //dataCarencia, 
//				optionalMovimento.isPresent() ? optionalMovimento.get() : null, 
//				optionalIndexador.isPresent() ? optionalIndexador.get() : null, //indexador, 
//				optionalRisco.isPresent() ? optionalRisco.get() : null, //risco, 
//				seuNumero, 
//				coobrigacao ? "01" : "02", 
//				null, //nossoNumero, 
//				numeroDocumento, 
//				optionalTipoRecebivel.isPresent() ? optionalTipoRecebivel.get().getCdEspecie() : null, //especie, 
//				termoCessao, 
//				chaveNfe, 
//				variacaoCambial, //variacaoCambial, 
//				null, //docOrigRecebivel, 
//				null, //nomeOrigRecebivel, 
//				null, //valorPago, 
//				valorTitulo, 
//				valorAquisicao, 
//				null, //valorAbatimento, 
//				taxaJurosIndexador, //taxaJurosIndexador
//				taxaJuros
//				);
//		
//		cnab.getTitulos().add(titulo.getCopy());
//		
//		clear();
//		
//	}
    public void addTitulo() {

        try {

            TituloDto titulo = new TituloDto();

            Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
            Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();

            Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();
            Optional<RiscoDto> optionalRisco = riscos.stream().filter(c -> c.getIdRisco().equals(riscoSelecionado)).findFirst();
            Optional<TipoRecebivelDto> optionalTipoRecebivel = tiposRecebiveis.stream().filter(c -> c.getIdTipoRecebivel().equals(tipoRecebivelSelecionado)).findFirst();

            BigDecimal valor = ValorAleatorioUtil.getValorDecimal(null, null);

            titulo.setValorTitulo(valor);
            titulo.setValorAquisicao(NumericUtils.getValorMenos20PorCento(valor));
            titulo.setTaxaJuros(ValorAleatorioUtil.getTaxaDecimal());
            titulo.setEspecie(optionalTipoRecebivel.isPresent() ? optionalTipoRecebivel.get().getCdEspecie() : "01");
            titulo.setMovimento(optionalMovimento.isPresent() ? optionalMovimento.get() : null);
            titulo.setCedente(optionalCedente.get());
            titulo.setSacado(optionalSacado.get());
            titulo.setDataVencimento(dataVencimento);
            titulo.setSeuNumero(ValorAleatorioUtil.getValor(25));
            titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
            titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
            titulo.setChaveNfe("31190600006388319890559240000000311006164587");
            titulo.setCoobrigacao(coobrigacao ? "01" : "02");
            titulo.setRisco(optionalRisco.isPresent() ? optionalRisco.get() : null);

            titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
            cnab.getTitulos().add(titulo.getCopy());

            titulo = new TituloDto();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
        }
    }

    public void gerar() {
        try {

            if (!bucket.tryConsume(1)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
                return;
            }

            if (quantidadeTitulos == null || quantidadeTitulos == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos é obrigatório"));
                return;
            }

            if (quantidadeTitulos > 999997) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997"));
                return;
            }


            Optional<BancoDto> optionalBanco = bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst();

            cnab.setBanco(optionalBanco.isPresent() ? optionalBanco.get() : null);
            cnab.setDataGravacao(dataGravacao);
            cnab.setFundo(fundos.stream().filter(c -> c.getIdFundo().equals(fundoSelecionado)).findFirst().get());
            cnab.setLayout(LayoutEnum.parse(layoutSelecionado));
            cnab.setOriginador(originadores.stream().filter(c -> c.getIdOriginador().equals(originadorSelecionado)).findFirst().get());

            for (int i = 0; i < quantidadeTitulos; i++) {

                addTitulo();
            }

            ConfGlobal confGlobal = confGlobalService.getConfGlobal();
            cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
            confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
            if (!importacaoAutomatica) {
                confGlobal.setPath(path);
            }

            this.confGlobalService.salvar(confGlobal);

            geradorCnab.gerar(cnab, "AQUISICAO", importacaoAutomatica, path);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));

            cnab = new CnabDto();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void gerarValorTitulo() {
        this.valorTitulo = ValorAleatorioUtil.getValorDecimal(10, 2000);

    }

    public void gerarValorAquisicao() {
        if (BigDecimalUtils.isNullOrZero(valorTitulo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "É necessário informar o valor do titulo."));
        } else {
            this.valorAquisicao = NumericUtils.getValorMenos20PorCento(valorTitulo);
        }
    }

    public void gerarTaxaJuros() {
        this.taxaJuros = ValorAleatorioUtil.getTaxaDecimal();
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

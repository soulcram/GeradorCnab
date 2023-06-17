package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.google.common.collect.Lists;

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
import br.com.m3Tech.solucoesFromtis.util.BigDecimalUtils;
import br.com.m3Tech.solucoesFromtis.util.BooleanUtils;
import br.com.m3Tech.solucoesFromtis.util.LayoutUtils;
import br.com.m3Tech.solucoesFromtis.util.NumericUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabAquisicaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


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
	private  IGeradorCnab geradorCnab;
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
	private Integer nuBanco;
	private Integer nuConta;
	private Integer nuAgencia;
	private Integer qtdeLastros = 0;
	private Integer tipoPagamento = 1;
	
	private String chaveNfe;
	private String seuNumero;
	private String numeroDocumento;
	private String termoCessao;
	private String path;
	private String variacaoCambial;
	private String especieSelecionado;
	
	private Boolean coobrigacao;
	private Boolean importacaoAutomatica;
	
	private LocalDate dataGravacao;
	private LocalDate dataVencimento;
	private LocalDate dataCarencia;
	
	private BigDecimal valorAquisicao;
	private BigDecimal valorRetencao;
	private BigDecimal valorTitulo;
	private BigDecimal taxaJuros;
	private BigDecimal taxaJurosIndexador;
	private BigDecimal taxaSpread;
	
	private CnabDto cnab;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	private List<BancoDto> bancos;
	private List<OriginadorDto> originadores;
	private List<CedenteDto> cedentes;
	private List<SacadoDto> sacados;
	private List<MovimentoDto> movimentos;
	private List<TipoRecebivelDto> tiposRecebiveis;
	private List<String> tiposRecebiveisLayout47 = Lists.newArrayList("DM", "CH","CT");
	private List<TituloDto> titulos;
	private List<IndexadorDto> indexadores;
	private List<RiscoDto> riscos;
	
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
		

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
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
		System.out.println("Selecionando Fundo " + fundoSelecionado );
		cnab = new CnabDto();
		Optional<FundoDto> optional = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst();
		
		if(optional.isPresent()) {
			try {
				Base base = baseService.findById(baseSelecionada);
			
				FundoDto fundo = optional.get();

				dataGravacao = fundo.getDataFundo();
				layoutSelecionado = fundo.getLayoutAquisicao();
				dataVencimento = dataGravacao.plusDays(45);

				

				originadores = originadorService.findAll(base, fundo.getIdFundo());
				cedentes = cedenteService.findAll(base, fundo.getIdFundo());
				sacados = sacadoService.findAll(base, fundo.getIdFundo());
				movimentos = movimentoService.findAllMovimentosAquisicao(base, layoutSelecionado);
				tiposRecebiveis =  tipoRecebivelService.findAllTipoRecebivel(base, layoutSelecionado);
				
				if(!cedentes.isEmpty()) {
					coobrigacao = BooleanUtils.getBooleanOfString(cedentes.get(0).getCoobrigacao());
					CedenteDto cedenteDto = cedentes.get(0);

					nuBanco = Integer.valueOf(cedenteDto.getBancoCedente());
					nuAgencia = Integer.valueOf(StringUtils.concatIfExists(cedenteDto.getAgenciaCedente(), cedenteDto.getDgAgenciaCedente()) );
					nuConta = Integer.valueOf(StringUtils.concatIfExists(cedenteDto.getContaCedente(), cedenteDto.getDgContaCedente()));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}
	
	public void selecionandoLayout() {
		try {
			
			if(baseSelecionada == null) {
				return;
			}
			
			Base base = baseService.findById(baseSelecionada);
			
			movimentos = movimentoService.findAllMovimentosAquisicao(base, layoutSelecionado);
			tiposRecebiveis =  tipoRecebivelService.findAllTipoRecebivel(base, layoutSelecionado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selecionandoCedente() {
		
		Optional<CedenteDto> optional = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
		
		if(optional.isPresent()) {
			
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
			
			if(optional.isPresent()) {
				FundoDto fundoSelecionado = optional.get();
				
				path = (confGlobalService.getPathSalvarArquivo(base, importacaoAutomatica, base.getVersaoMercado(), fundoSelecionado));
			}
			

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void atualizarFundos() {
		
		try {
		
			Base base = baseService.findById(baseSelecionada);
		
			fundos = fundoService.findAll(base);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	private void atualizarBancos() {

		try {

			Base base = baseService.findById(baseSelecionada);

			bancos = bancoService.findAll(base);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizarIndexadores() {

		try {

			Base base = baseService.findById(baseSelecionada);

			indexadores = indexadorService.findAll(base);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizarRiscos() {

		try {

			Base base = baseService.findById(baseSelecionada);

			riscos = riscoService.findAll(base);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void adicionarTitulo() {
		
		if(fundoSelecionado == null || fundoSelecionado < 1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Selecione um fundo."));
			return;
		}
		
		if( BigDecimalUtils.isNullOrZero(valorTitulo)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor do título é obrigatório."));
			return;
		}
		
		if( 35 != layoutSelecionado && BigDecimalUtils.isNullOrZero(valorAquisicao)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição é obrigatório."));
			return;
		}
		
		if( 35 != layoutSelecionado && valorAquisicao.compareTo(valorTitulo) > 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição não pode ser maior que o valor do título."));
			return;
		}
		
		if( 35 == layoutSelecionado && BigDecimalUtils.isNullOrZero(taxaJuros)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Taxa de Juros é obrigatório."));
			return;
		}
		
		if(cnab.getTitulos().size() > 999997) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997."));
			return;
		}
		
		if(StringUtils.emptyOrNull(seuNumero)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seu Numero é obrigatório."));
			return;
		}
		
		if(StringUtils.emptyOrNull(numeroDocumento)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Numero Documento é obrigatório."));
			return;
		}
		
		if(StringUtils.emptyOrNull(termoCessao)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Termo Cessão é obrigatório."));
			return;
		}
		Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
		Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();
		Optional<BancoDto> optionalBanco = bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst();
		Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();
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
				getEspecie(optionalTipoRecebivel), //especie, 
				termoCessao, 
				chaveNfe, 
				variacaoCambial, //variacaoCambial, 
				null, //docOrigRecebivel, 
				null, //nomeOrigRecebivel, 
				valorAquisicao, //valorPago, 
				valorTitulo, 
				valorAquisicao, 
				null, //valorAbatimento, 
				taxaJurosIndexador, //taxaJurosIndexador
				taxaSpread,
				taxaJuros,
				null,
				null,
				null,
				null,
				null,
				StringUtils.defaultIfNull(qtdeLastros,""),
				null
				);
		
		cnab.getTitulos().add(titulo.getCopy());
		
		clear();
		
	}
	
	private String getEspecie(Optional<TipoRecebivelDto> optionalTipoRecebivel) {
		
		if(LayoutEnum.CNAB_500_VERSAO_FINAXIS.getCdLayout().equals(layoutSelecionado) || LayoutEnum.CNAB_400_REMESSA_FINAXIS.getCdLayout().equals(layoutSelecionado)) {
			return especieSelecionado;
		}
		
		return optionalTipoRecebivel.isPresent() ? optionalTipoRecebivel.get().getCdEspecie() : "01";
	}
	
	public void gerar() {
		try {
			
			if(cnab.getTitulos() == null || cnab.getTitulos().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum título foi adicionado.."));
				return;
			}
			
			cnab.setBanco(bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst().get());
			cnab.setDataGravacao(dataGravacao);
			cnab.setFundo(fundos.stream().filter(c -> c.getIdFundo().equals(fundoSelecionado)).findFirst().get());
			cnab.setLayout(LayoutEnum.parse(layoutSelecionado));
			cnab.setOriginador(originadores.stream().filter(c -> c.getIdOriginador().equals(originadorSelecionado)).findFirst().get());
			cnab.setTipoPagamento(this.tipoPagamento);
			cnab.setValorRetencao(valorRetencao);	
			cnab.setBancoCedente(StringUtils.getNumeroComZerosAEsquerda(nuBanco,3));
			cnab.setAgenciaCedente(StringUtils.getNumeroComZerosAEsquerda(nuAgencia,4));
			cnab.setContaCedente(StringUtils.getNumeroComZerosAEsquerda(nuConta,1));
			

			
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
			if(!importacaoAutomatica) {
				confGlobal.setPath(path);
			}
		
			confGlobalService.salvar(confGlobal);
			
			geradorCnab.gerar(cnab, "AQUISICAO", importacaoAutomatica, path);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabDto();
			
			clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clear() {
		this.seuNumero = "";
		this.numeroDocumento = "";
		this.termoCessao = "";
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
	
	public void gerarValorTitulo() {
		this.valorTitulo = ValorAleatorioUtil.getValorDecimal(10,2000);
		
	}
	
	public void gerarValorAquisicao() {
		if(BigDecimalUtils.isNullOrZero(valorTitulo)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "É necessário informar o valor do titulo."));
		}else {
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
	
	public Boolean getExibirValorRetencao() {

		return LayoutUtils.exibirValorRetencao(layoutSelecionado);
	}
	
	public Boolean getExibirDadosConta() {

		return LayoutUtils.exibirDadosConta(layoutSelecionado);
	}
	
	public Boolean getExibirTaxaSpread() {

		return LayoutUtils.exibirTaxaSpread(layoutSelecionado);
	}
	
	public Boolean getExibirIndexadores() {

		return LayoutUtils.exibirIndexadores(layoutSelecionado);
	}
	
	public Boolean getExibirTipoPagamento() {

		return LayoutUtils.exibirTipoPagamento(layoutSelecionado);
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
	
	public Boolean getExibirQtdeLastros() {

		return LayoutUtils.exibirQtdeLastros(layoutSelecionado);
	}
	
	public Boolean getExibirRisco() {

		return LayoutUtils.exibirRisco(layoutSelecionado);
	}
	
	public Boolean getExibirEspecieNormal() {
		return LayoutUtils.exibirExpecieComum(layoutSelecionado);   
	}
	
	public Boolean getExibirTaxaJuros() {
		
		if(layoutSelecionado == null) {
			return false;
		}
		
		if(layoutSelecionado == 35 ) {
			return true;
		}
		
		return false;
	}

	public String voltar() {
		return VOLTAR;
	}

}

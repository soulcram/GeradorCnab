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
public class CnabAquisicaoDinamicoController implements Serializable {

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
	private Integer quantidadeTitulos;
	private Integer quantidadeArquivos;
	
	private String path;
	private String especieSelecionado;
	
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
		quantidadeTitulos = 1;
		quantidadeArquivos = 1;

				
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

	public void addTitulo() {
		
		try {
	
			TituloDto titulo = new TituloDto();
			
			Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
			Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();
			
			Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();
			Optional<RiscoDto> optionalRisco = riscos.stream().filter(c -> c.getIdRisco().equals(riscoSelecionado)).findFirst();
			Optional<TipoRecebivelDto> optionalTipoRecebivel = tiposRecebiveis.stream().filter(c -> c.getIdTipoRecebivel().equals(tipoRecebivelSelecionado)).findFirst();
			
			BigDecimal valor = ValorAleatorioUtil.getValorDecimal(null,null);
	
			titulo.setValorTitulo(valor);
			titulo.setValorAquisicao(NumericUtils.getValorMenos20PorCento(valor));
			titulo.setTaxaJuros(ValorAleatorioUtil.getTaxaDecimal());
			titulo.setTaxaSpread(ValorAleatorioUtil.getTaxaDecimal());
			titulo.setEspecie( getEspecie(optionalTipoRecebivel));
			titulo.setMovimento(optionalMovimento.isPresent() ? optionalMovimento.get() : null);
			titulo.setCedente(optionalCedente.get());
			titulo.setSacado(optionalSacado.get());
			titulo.setValorPago(NumericUtils.getValorMenos20PorCento(valor));
			titulo.setDataVencimento(dataVencimento);
			titulo.setSeuNumero(cnab.getLayout().equals(LayoutEnum.CNAB_450_REMESSA_GENIAL)?  ValorAleatorioUtil.getValor(12) + "001001 111111" : ValorAleatorioUtil.getValor(25));
			titulo.setNumeroDocumento(ValorAleatorioUtil.getValor(10));
			titulo.setTermoCessao(ValorAleatorioUtil.getValor(10));
//			titulo.setChaveNfe("31190600006388319890559240000000311006164587");
			titulo.setCoobrigacao(coobrigacao ? "01" : "02");
			titulo.setRisco(optionalRisco.isPresent() ? optionalRisco.get() : null);
			titulo.setQtdLastros("0");			titulo.setNumBanco(cnab.getBanco().getCodigoBanco());
			cnab.getTitulos().add(titulo.getCopy());
			
			titulo = new TituloDto();
		
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
		}
	}
	
	
	private String getEspecie(Optional<TipoRecebivelDto> optionalTipoRecebivel) {
		
		if(LayoutEnum.CNAB_500_VERSAO_FINAXIS.getCdLayout().equals(layoutSelecionado) || LayoutEnum.CNAB_400_REMESSA_FINAXIS.getCdLayout().equals(layoutSelecionado)) {
			return especieSelecionado;
		}
		
		return optionalTipoRecebivel.isPresent() ? optionalTipoRecebivel.get().getCdEspecie() : "01";
	}

	public void gerar() {
		try {
			
			if(quantidadeTitulos == null || quantidadeTitulos == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos é obrigatório"));
				return;
			}
			
			if(quantidadeArquivos == null || quantidadeArquivos == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Arquivos é obrigatório"));
				return;
			}
			
			if(quantidadeTitulos > 999997) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Quantidade de Títulos máxima é 999.997"));
				return;
			}

			
			Optional<BancoDto> optionalBanco = bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst();
			
			cnab.setBanco(optionalBanco.isPresent() ? optionalBanco.get() : null);
			cnab.setDataGravacao(dataGravacao);
			cnab.setFundo(fundos.stream().filter(c -> c.getIdFundo().equals(fundoSelecionado)).findFirst().get());
			cnab.setLayout(LayoutEnum.parse(layoutSelecionado));
			cnab.setOriginador(originadores.stream().filter(c -> c.getIdOriginador().equals(originadorSelecionado)).findFirst().get());
			cnab.setValorRetencao(ValorAleatorioUtil.getValorDecimal(2,10));
			cnab.setTaxaSpread(ValorAleatorioUtil.getTaxaDecimal());
			Optional<CedenteDto> optionalCedente = cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst();
			
			if(optionalCedente.isPresent()) {
				CedenteDto cedenteDto = optionalCedente.get();
				cnab.setBancoCedente(cedenteDto.getBancoCedente());
				cnab.setAgenciaCedente(StringUtils.concatIfExists(cedenteDto.getAgenciaCedente(),cedenteDto.getDgAgenciaCedente()));
				cnab.setContaCedente(StringUtils.concatIfExists(cedenteDto.getContaCedente(),cedenteDto.getDgContaCedente()));
			}
			
			for (int y = 0; y < quantidadeArquivos; y++) {
				for (int i = 0; i < quantidadeTitulos; i++) {
	
					addTitulo();
				}
				
				ConfGlobal confGlobal = confGlobalService.getConfGlobal();
				cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
				confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
				if(!importacaoAutomatica) {
					confGlobal.setPath(path);
				}
			
				confGlobalService.salvar(confGlobal);
				
				geradorCnab.gerar(cnab, "AQUISICAO", importacaoAutomatica, path);
				cnab.setTitulos(new ArrayList<>());
			
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabDto();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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

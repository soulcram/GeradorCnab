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
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
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
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.BooleanUtils;
import br.com.m3Tech.utils.StringUtils;
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
		
	
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer bancoSelecionado;
	private Integer layoutSelecionado;
	private Integer originadorSelecionado;
	private Integer sacadoSelecionado;
	private Integer cedenteSelecionado;
	private Integer movimentoSelecionado;
	private Integer tipoRecebivelSelecionado;
	
	private String chaveNfe;
	private String seuNumero;
	private String numeroDocumento;
	private String termoCessao;
	private String path;
	
	private Boolean coobrigacao;
	private Boolean importacaoAutomatica;
	
	private LocalDate dataGravacao;
	private LocalDate dataVencimento;
	
	private BigDecimal valorAquisicao;
	private BigDecimal valorTitulo;
	
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
		cnab = new CnabDto();
		path = confGlobalService.getConfGlobal().getPath();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabDto();
		atualizarFundos();
		atualizarBancos();
		
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
				
				Connection con = Conexao.getConnection(base);
				FundoDto fundo = optional.get();

				dataGravacao = fundo.getDataFundo();
				layoutSelecionado = fundo.getLayoutAquisicao();
				dataVencimento = dataGravacao.plusDays(45);

				

				originadores = originadorService.findAll(con, fundo.getIdFundo());
				cedentes = cedenteService.findAll(con, fundo.getIdFundo(), base);
				sacados = sacadoService.findAll(con, fundo.getIdFundo());
				movimentos = movimentoService.findAllMovimentosAquisicao(con, layoutSelecionado);
				tiposRecebiveis =  tipoRecebivelService.findAllTipoRecebivel(con, layoutSelecionado);
				
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
			Base base = baseService.findById(baseSelecionada);
			
			Connection con = Conexao.getConnection(base);
			
			movimentos = movimentoService.findAllMovimentosAquisicao(con, layoutSelecionado);
			tiposRecebiveis =  tipoRecebivelService.findAllTipoRecebivel(con, layoutSelecionado);
			
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
	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void adicionarTitulo() {

//		BigDecimal vlTaxaJurosIndexador = null;
//				
//		if(!"".equals(taxaJurosIndexador.getText())) {
//			vlTaxaJurosIndexador = new BigDecimal(taxaJurosIndexador.getText().replaceAll(",", "."));
//		}
		
		if(fundoSelecionado == null || fundoSelecionado < 1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Selecione um fundo."));
			return;
		}
		
		if(valorAquisicao.compareTo(valorTitulo) > 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor de aquisição não pode ser maior que o valor do título."));
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
		
		TituloDto titulo = new TituloDto(cedentes.stream().filter(c -> c.getIdCedente().equals(cedenteSelecionado)).findFirst().get(), 
				sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst().get(), 
				bancos.stream().filter(c -> c.getIdBanco().equals(bancoSelecionado)).findFirst().get().getCodigoBanco(), 
				dataVencimento, 
				null, //dataLiquidacao, 
				null, //dataCarencia, 
				movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst().get(), 
				null, //indexador, 
				null, //risco, 
				seuNumero, 
				coobrigacao ? "01" : "02", 
				null, //nossoNumero, 
				numeroDocumento, 
				tiposRecebiveis.stream().filter(c -> c.getIdTipoRecebivel().equals(tipoRecebivelSelecionado)).findFirst().get().getCdEspecie(), //especie, 
				termoCessao, 
				chaveNfe, 
				null, //variacaoCambial, 
				null, //docOrigRecebivel, 
				null, //nomeOrigRecebivel, 
				null, //valorPago, 
				valorTitulo, 
				valorAquisicao, 
				null, //valorAbatimento, 
				null //taxaJurosIndexador
				);
		
		cnab.getTitulos().add(titulo.getCopy());
		
		clear();
		
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
			
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
			if(!importacaoAutomatica) {
				confGlobal.setPath(path);
			}
		
		
			confGlobal.save();
			
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
	
	public void removerTitulo(TituloDto titulo) {
		cnab.getTitulos().remove(titulo);
	}
	

	public String voltar() {
		return VOLTAR;
	}

}

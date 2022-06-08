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
import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import br.com.m3Tech.utils.IntegerUtils;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabRetornoCobrancaController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IBancoService bancoService;
	@Autowired
	private IMovimentoService movimentoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
		
	
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer bancoSelecionado;
	private Integer layoutSelecionado;
	private Integer movimentoSelecionado;
	
	private String path;
	private String agenciaCobranca = StringUtils.getNumeroComZerosAEsquerda(ValorAleatorioUtil.getValorNumerico(9999), 4);
	private String idCobranca = StringUtils.getNumeroComZerosAEsquerda(ValorAleatorioUtil.getValorNumerico(999999), 6);
	
	private Boolean importacaoAutomatica;
	
	private LocalDate dataGravacao;
	
	private BigDecimal valorPago;
	private BigDecimal valorAbatimento = BigDecimal.ZERO;
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	private BigDecimal valorJuros = BigDecimal.ZERO;
	private BigDecimal valorDespesas = BigDecimal.ZERO;
	
	private CnabCobrancaDto cnab;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	private List<BancoDto> bancos;
	private List<OriginadorDto> originadores;
	private List<MovimentoDto> movimentos;
	private List<TituloDto> titulos;
	private List<TituloDto> titulosEmEstoque;
	private List<LayoutEnum> layoutsCobranca;
	private List<FundoCobrancaDto> cobrancas;
	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		bancos = new ArrayList<>();
		originadores = new ArrayList<>();
		movimentos = new ArrayList<>();
		titulosEmEstoque = new ArrayList<>();
		cobrancas = new ArrayList<>();
		cnab = new CnabCobrancaDto();
		path = confGlobalService.getConfGlobal().getPath();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabCobrancaDto();
		atualizarFundos();
		selecionandoLayout();
		
	}

	
	public void selecionandoFundo() {
		System.out.println("Selecionando Fundo " + fundoSelecionado );
		cnab = new CnabCobrancaDto();
		Optional<FundoDto> optional = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst();
		
		if(optional.isPresent()) {
			try {
				Base base = baseService.findById(baseSelecionada);
				
				Connection con = Conexao.getConnection(base);
				FundoDto fundo = optional.get();

				dataGravacao = fundo.getDataFundo();				
				selecionandoLayout(); 
//				movimentos = movimentoService.findAllMovimentos(con, layoutSelecionado);
//				atualizarTitulosEmEstoque();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}

	
	
	public void selecionandoLayout() {
		try {
			Base base = baseService.findById(baseSelecionada);
			
			layoutsCobranca = new ArrayList<>();
			bancos = new ArrayList<>();
			movimentos = new ArrayList<>();
			
			if(IntegerUtils.isZeroOrNull(fundoSelecionado)) {
				return;
			}
			
			cobrancas = fundoService.findCodCobrancas( Conexao.getConnection(base),fundoSelecionado);
			
			cobrancas.forEach(c -> {
				try {
					LayoutEnum layout = LayoutEnum.findCobrancaByCodBanco(c.getNuBanco());
					if(!layoutsCobranca.contains(layout)) {
						layoutsCobranca.add(layout);
					}
					
					BancoDto banco = bancoService.findOneByNumBanco(Conexao.getConnection(base), c.getNuBanco());
					if(!bancos.contains(banco)) {
						bancos.add(banco);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				});
			
			movimentos = movimentoService.findAllMovimentos(Conexao.getConnection(base), layoutSelecionado == null ? layoutsCobranca.get(0).getCdLayout():layoutSelecionado);
//			atualizarTitulosEmEstoque(); 
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
			
			selecionandoLayout();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	public void atualizarTitulosEmEstoque() {

		try {
			Base base = baseService.findById(baseSelecionada);
			
			if(movimentoSelecionado == null) {
				return;
			}
			
			Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();
			
			if(optionalMovimento.isPresent()) {
				
				MovimentoDto movimentoDto = optionalMovimento.get();
			
				titulosEmEstoque = movimentoService.findAllTituloEmEstoqueCobranca( Conexao.getConnection(base),fundoSelecionado,movimentoDto.getIdMovimento());
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void addTitulo(TituloDto item) {
		
		Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();

		if(optionalMovimento.isPresent()) {
			item.setMovimento(optionalMovimento.get());
		}
		
		item.setAgenciaCobranca(agenciaCobranca);
		item.setIdCobranca(idCobranca);
		
		
		
		item.setValorPago(valorPago); 
		item.setValorAbatimento(valorAbatimento);
		item.setValorDesconto(valorDesconto);
		item.setValorJuros(valorJuros);
		item.setValorDespesas(valorDespesas);
		
		cnab.getTitulos().add(item.getCopy());
		titulosEmEstoque.remove(item);
		
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
			
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
			if(!importacaoAutomatica) {
				confGlobal.setPath(path);
			}
		
		
			confGlobal.save();
			
			geradorCnab.gerarRetornoCobrança(cnab, "RETORNO_COBRANCA", importacaoAutomatica, path);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabCobrancaDto();
			
			clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clear() {

	}

	
	public void removerTitulo(TituloDto titulo) {
		cnab.getTitulos().remove(titulo);
	}

	public String voltar() {
		return VOLTAR;
	}

}

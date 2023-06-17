package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
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

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
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
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabBaixaController implements Serializable {

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
	private IMovimentoService movimentoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IGeradorCnab geradorCnab;
		
	
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer bancoSelecionado;
	private Integer layoutSelecionado;
	private Integer originadorSelecionado;
	private Integer movimentoSelecionado;
	
	private String path;
	
	private Boolean importacaoAutomatica;
	
	private LocalDate dataGravacao;
	
	private CnabDto cnab;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	private List<BancoDto> bancos;
	private List<OriginadorDto> originadores;
	private List<MovimentoDto> movimentos;
	private List<TituloDto> titulos;
	private List<TituloDto> titulosEmEstoque;
	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		bancos = new ArrayList<>();
		originadores = new ArrayList<>();
		movimentos = new ArrayList<>();
		titulosEmEstoque = new ArrayList<>();
		cnab = new CnabDto();
		path = confGlobalService.getConfGlobal().getPath();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabDto();
		atualizarFundos();
		atualizarBancos();
		atualizarTitulosEmEstoque();
		
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
				

				originadores = originadorService.findAll(base, fundo.getIdFundo());
				movimentos = movimentoService.findAllMovimentosBaixa(base, layoutSelecionado);
				atualizarTitulosEmEstoque();
				
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
			
			
			movimentos = movimentoService.findAllMovimentosBaixa(base, layoutSelecionado);
			
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

	private void atualizarTitulosEmEstoque() {

		try {
			Base base = baseService.findById(baseSelecionada);
			titulosEmEstoque = movimentoService.findAllTituloEmEstoqueByFundo( base,fundoSelecionado,false);
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
	
	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void addTitulo(TituloDto item) {
		
		Optional<MovimentoDto> optionalMovimento = movimentos.stream().filter(c -> c.getIdMovimento().equals(movimentoSelecionado)).findFirst();

		if(optionalMovimento.isPresent()) {
			item.setMovimento(optionalMovimento.get());
		}
		
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
			cnab.setOriginador(originadores.stream().filter(c -> c.getIdOriginador().equals(originadorSelecionado)).findFirst().get());
			
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);
			if(!importacaoAutomatica) {
				confGlobal.setPath(path);
			}
		
		
			confGlobalService.salvar(confGlobal);
			
			geradorCnab.gerar(cnab, "BAIXA", importacaoAutomatica, path);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabDto();
			
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

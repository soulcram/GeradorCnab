package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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

import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoBaixas;
import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabBaixaOrgaoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;
import br.com.m3Tech.solucoesFromtis.util.IntegerUtils;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CnabBaixasOrgaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private ISacadoService sacadoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
		
	
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer sacadoSelecionado;
	private Integer qtdeParcelas;
	
	private String path;
	
	
	private BigDecimal valor;
	
	private CnabBaixaOrgaoDto cnab;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	private List<SacadoDto> sacados;

	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		sacados = new ArrayList<>();
//		cnab = new CnabCobrancaDto();
		path = confGlobalService.getConfGlobal().getPath();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabBaixaOrgaoDto();
		atualizarFundos();
		
	}

	
	public void selecionandoFundo() {
		System.out.println("Selecionando Fundo " + fundoSelecionado );
		cnab = new CnabBaixaOrgaoDto();
		Optional<FundoDto> optional = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst();
		
		if(optional.isPresent()) {
			try {
				Base base = baseService.findById(baseSelecionada);
				
				FundoDto fundo = optional.get();
				sacados = sacadoService.findAll(base, fundo.getIdFundo());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void adicionarTitulo() {
		
		Optional<SacadoDto> optionalSacado = sacados.stream().filter(c -> c.getIdSacado().equals(sacadoSelecionado)).findFirst();
		
		if(optionalSacado.isPresent()) {
			
			SacadoDto sacadoDto = optionalSacado.get();
			
			cnab.getTitulos().add(new DadosArquivoBaixas(sacadoDto.getNomeSacado(), CpfCnpjUtils.removerFormatacao(sacadoDto.getDocSacado()), valor, qtdeParcelas));
		}
		
		clear();
		
	}
	
	public void gerar() {
		try {
			
			if(cnab.getTitulos() == null || cnab.getTitulos().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum título foi adicionado.."));
				return;
			}

			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			cnab.setNumSeqArquivo(confGlobal.getSeqArquivo());
			confGlobal.setSeqArquivo(confGlobal.getSeqArquivo() + 1);

			confGlobalService.salvar(confGlobal);
	
			geradorCnab.gerarBaixaOrgao(cnab, path);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabBaixaOrgaoDto();
			
			clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clear() {
		this.valor = null;
		this.qtdeParcelas = null;
	}

	
	public void removerTitulo(TituloDto titulo) {
		cnab.getTitulos().remove(titulo);
	}

	public String voltar() {
		return VOLTAR;
	}

}

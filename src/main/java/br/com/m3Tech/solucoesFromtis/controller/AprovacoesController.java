package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarCedente;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarCedenteAprovado;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarEntidade;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarFundo;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarPdd;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarPddFaixaUnica;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarSacado;
import br.com.m3Tech.solucoesFromtis.service.impl.ImportarCnabPortal;
import br.com.m3Tech.solucoesFromtis.service.impl.RoboAprovacoes;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;
import br.com.m3Tech.solucoesFromtis.util.IntegerUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
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
public class AprovacoesController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private ICedenteService cedenteService;
		
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer repeticoes = 1;
	private Integer repeticoesPortalServicos = 1;
	
	private Boolean custodianteAtivo = false;
	
	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	
	private String urlPortal;
	private String usuarioPortal;
	private String senhaPortal;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	
	private RoboAprovacoes robo;
	
	@PostConstruct
	public void init() {
		
		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		
		this.urlCustodia = confGlobal.getUrlCustodia();
		this.usuarioCustodia = confGlobal.getUsuarioCustodia();
		this.senhaCustodia = confGlobal.getSenhaCustodia();
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		

				
	}
	
	public void selecionandoBase() {
		atualizarFundos();
		
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
		
		bases = baseService.findAll();
	}
	
	
	
	public void ativarConsultoria() {
		try {
			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void ativarGestor() {
		try {
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void ativarAprovacoes() {
				
		try {
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();
			
			this.urlCustodia = confGlobal.getUrlCustodia();
			this.usuarioCustodia = confGlobal.getUsuarioCustodia();
			this.senhaCustodia = confGlobal.getSenhaCustodia();
			
			if(validarCustodia()) {
				return;
			}
			
			if(!custodianteAtivo) {
				custodianteAtivo = true;	
				robo = new RoboAprovacoes();
				robo.executar(urlCustodia, usuarioCustodia, senhaCustodia);
				
			}else {
				custodianteAtivo = false;
				robo.desativar();
			}
			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
//	public void cadastrarEntidade() {
//		try {
//			
//			if(validar(false)) {
//				return;
//			}
//			
//
//			
//			ICadastroAutomatizado cadastrarEntidade = new CadastrarEntidade();
//			
//			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
//					usuarioCustodia, 
//					senhaCustodia, 
//					null,
//					1);
//			
//
//			String nomeEntidade = cadastrarEntidade.executar(param);
//			
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Entidade " + nomeEntidade + " Cadastrado com sucesso"));
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
//			e.printStackTrace();
//		}
//	}
	
	
	
	private boolean validarCustodia() {

				
		if(StringUtils.emptyOrNull(urlCustodia)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar url custódia"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(usuarioCustodia)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar usuario custódia"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(senhaCustodia)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar senha custódia"));
			return true;
			
		}
		
		return false;
	}
	
	private boolean validarPortal() {

		
		if(StringUtils.emptyOrNull(urlPortal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar url portal"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(usuarioPortal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar usuario portal"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(senhaPortal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar senha portal"));
			return true;
			
		}
		
		return false;
	}
	


	private void clear() {
	}
	

	public String voltar() {
		return VOLTAR;
	}

}

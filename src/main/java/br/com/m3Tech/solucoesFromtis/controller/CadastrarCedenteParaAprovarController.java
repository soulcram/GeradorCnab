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

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
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
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarCedenteParaAprovar;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarEntidade;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarPdd;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarPddFaixaUnica;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarSacado;
import br.com.m3Tech.solucoesFromtis.service.impl.ImportarCnabPortal;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;
import br.com.m3Tech.utils.IntegerUtils;
import br.com.m3Tech.utils.StringUtils;
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
public class CadastrarCedenteParaAprovarController implements Serializable {

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
		
	private Bucket bucket;
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	private Integer repeticoesPortalServicos = 1;

	
	private String urlPortalServicos;
	private String usuarioPortalServicos;
	private String senhaPortalServicos;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	
	
	@PostConstruct
	public void init() {
		
		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		
		this.urlPortalServicos = confGlobal.getUrlPortalServicos();
		this.usuarioPortalServicos = confGlobal.getUsuarioPortalServicos();
		this.senhaPortalServicos = confGlobal.getSenhaPortalServicos();
		
		bases = baseService.findAll();
		fundos = new ArrayList<>();
		
		Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();

				
	}
	
	public void selecionandoBase() {
		atualizarFundos();
		
	}

	
	private void atualizarFundos() {
		
		try {
		
			Base base = baseService.findById(baseSelecionada);
		
			fundos = fundoService.findAll(Conexao.getConnection(base));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public void atualizarBases() {
		
		bases = baseService.findAll();
	}

	public void cadastrarCedenteAprovadoAleatorio() {
		try {
			
			if(validarPortalServicos(true)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastroAutomatizado = new CadastrarCedenteParaAprovar();
			FundoDto fundo = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst().get();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlPortalServicos, 
					usuarioPortalServicos, 
					senhaPortalServicos, 
					fundo,
					repeticoesPortalServicos
					);
		
			
			String msg = cadastroAutomatizado.executar(param);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}

	
	private boolean validarPortalServicos(boolean validarFundo) {
		if(validarFundo && IntegerUtils.isZeroOrNull(baseSelecionada)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório selecionar uma base"));
			return true;
			
		}
		
		if(validarFundo && IntegerUtils.isZeroOrNull(fundoSelecionado)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório selecionar um fundo"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(urlPortalServicos)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar url Portal Servicos"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(usuarioPortalServicos)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar usuario Portal Servicos"));
			return true;
			
		}
		
		if(StringUtils.emptyOrNull(senhaPortalServicos)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório informar senha Portal Servicos"));
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

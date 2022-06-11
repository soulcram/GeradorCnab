package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.ICadastroAutomatizado;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarEntidade;
import br.com.m3Tech.solucoesFromtis.service.impl.CadastrarSacado;
import br.com.m3Tech.utils.IntegerUtils;
import br.com.m3Tech.utils.StringUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class CadastrosAutomatizadosController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IConfGlobalService confGlobalService;
		
	private Bucket bucket;
	
	private Integer baseSelecionada;
	private Integer fundoSelecionado;
	
	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	
	private List<Base> bases;
	private List<FundoDto> fundos;
	
	
	@PostConstruct
	public void init() {
		
		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		
		this.urlCustodia = confGlobal.getUrlCustodia();
		this.usuarioCustodia = confGlobal.getUsuarioCustodia();
		this.senhaCustodia = confGlobal.getSenhaCustodia();
		
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
	
	
	
	public void cadastrarSacado() {
		try {
			
			if(validar(true)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastrarSacado = new CadastrarSacado();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst().get());
			

			cadastrarSacado.cadastrar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sacado Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastrarEntidade() {
		try {
			
			if(validar(false)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastrarSacado = new CadastrarEntidade();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					null);
			

			cadastrarSacado.cadastrar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Entidade Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	private boolean validar(boolean validarFundo) {
		if(IntegerUtils.isZeroOrNull(baseSelecionada)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório selecionar uma base"));
			return true;
			
		}
		
		if(validarFundo && IntegerUtils.isZeroOrNull(fundoSelecionado)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Obrigatório selecionar um fundo"));
			return true;
			
		}
		
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

	private void clear() {
	}
	

	public String voltar() {
		return VOLTAR;
	}

}

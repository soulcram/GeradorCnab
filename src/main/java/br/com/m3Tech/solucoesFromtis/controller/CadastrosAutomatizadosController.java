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
public class CadastrosAutomatizadosController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/index.xhtml";


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
	private Integer repeticoes = 1;
	private Integer repeticoesPortalServicos = 1;
	
	
	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	
	private String urlPortalServicos;
	private String usuarioPortalServicos;
	private String senhaPortalServicos;
	
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
					fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst().get(),
					repeticoes);
			

			String nomeSacado = cadastrarSacado.executar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sacado " + nomeSacado + " Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastrarCedente() {
		try {
			
			if(validar(true)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastroAutomatizado = new CadastrarCedente();
			FundoDto fundo = fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst().get();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					fundo,
					repeticoes
					);
			
			String cnpjCedente = "";
			
			cnpjCedente = cadastroAutomatizado.executar(param);
			System.out.println("Cedentes: " + cnpjCedente + " Cadastrado com Sucesso");
			
			
			Base base = baseService.findById(baseSelecionada);
						
			CedenteDto cedenteRetornado = cedenteService.getCedenteByCpfCnpj(Conexao.getConnection(base), fundo.getIdFundo(), CpfCnpjUtils.removerFormatacao(cnpjCedente), base);
			
			if(cedenteRetornado != null && cedenteRetornado.getIdCedente() != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cedentes Cadastrado com sucesso"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Cedentes não Cadastrado"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastrarCedenteAprovado() {
		try {
			
			if(validarPortalServicos(true)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastroAutomatizado = new CadastrarCedenteAprovado();
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
	
	
	public void importarCnab() {
		try {
			
			if(validar(true)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado importar = new ImportarCnabPortal();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					fundos.stream().filter(f -> f.getIdFundo().equals(fundoSelecionado)).findFirst().get(),
					repeticoes);
			

			importar.executar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab importado com sucesso"));
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
			
			ICadastroAutomatizado cadastrarEntidade = new CadastrarEntidade();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					null,
					1);
			

			String nomeEntidade = cadastrarEntidade.executar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Entidade " + nomeEntidade + " Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastrarPddFaixaUnica() {
		try {
			
			if(validar(false)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastrarPdd = new CadastrarPddFaixaUnica();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					null,
					1);
			

			cadastrarPdd.executar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pdd Faixa Única Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastrarPdd() {
		try {
			
			if(validar(false)) {
				return;
			}
			
			if (!bucket.tryConsume(1)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Excedeu limite de requisições por minuto"));
				return;
			}
			
			ICadastroAutomatizado cadastrarPdd = new CadastrarPdd();
			
			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(urlCustodia, 
					usuarioCustodia, 
					senhaCustodia, 
					null,
					1);
			

			cadastrarPdd.executar(param);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pdd Cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	private boolean validar(boolean validarFundo) {
		if(validarFundo && IntegerUtils.isZeroOrNull(baseSelecionada)) {
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

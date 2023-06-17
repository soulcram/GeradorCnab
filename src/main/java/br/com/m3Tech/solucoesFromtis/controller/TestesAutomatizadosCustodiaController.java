package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.beust.jcommander.internal.Lists;

import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteCustodiaDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.testsCadastros.TestesAtivos;
import br.com.m3Tech.solucoesFromtis.testsCadastros.service.IExecutorTestes;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class TestesAutomatizadosCustodiaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TestesAutomatizadosCustodiaController.class);

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";

	@Autowired
	private IBaseService baseService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private List<? extends IExecutorTestes> processes;

		
	private String urlCustodia;
	private String usuarioCustodia;
	private String senhaCustodia;
	private Base base;
	
	private TestesAtivos testesAtivos;
	private Boolean selecionaTodos;
	private ConfGlobal confGlobal;
	
	private List<ResultadoTesteCustodiaDto> resultados = Lists.newArrayList();

	@PostConstruct
	public void init() {
		this.confGlobal = confGlobalService.getConfGlobal();
		
		urlCustodia = confGlobal.getUrlCustodia();
		usuarioCustodia = confGlobal.getUsuarioCustodia();
		senhaCustodia = confGlobal.getSenhaCustodia();
		base = baseService.findById(confGlobal.getIdBasePadrao());
		testesAtivos = new TestesAtivos();
	}

	public void selecionarTodos() {
		logger.info("Selecionando todos");
		
		testesAtivos.selecionarTodos(selecionaTodos); 
	}
	
	public void aplicarTestes() {

		resultados = Lists.newArrayList();
		logger.info("Aplicando Testes");
		
		for (IExecutorTestes processador : processes) {
			List<ResultadoTesteCustodiaDto> resultadosTestes = processador.executarTeste(testesAtivos);
			
			if(resultadosTestes != null) {
				resultados.addAll(resultadosTestes);
			}
		}
		
		logger.info("Fim da aplicação dos testes");

	}

	public String voltar() {
		return VOLTAR;
	}

}

package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class LimparBaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LimparBaseController.class);

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";

	@Autowired
	private IBaseService baseService;
	@Autowired
	private IFundoService fundoService;
	@Autowired
	private IArquivoService arquivoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private IFilaService filaService;

	private Integer baseSelecionada;

	private Integer quantidadeArquivos;

	private LocalDate data;

	private List<Base> bases;

	@PostConstruct
	public void init() {

		bases = baseService.findAll();
		data = LocalDate.now();
	}

	public void atualizarValidacao() {

	}

	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada);

	}

	public void atualizarBases() {
		System.out.println("Atualizar Base");

		bases = baseService.findAll();
	}

	
	public void limparBase() {

		try {
			if (baseSelecionada == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Base é obrigatório"));
				return;
			}

			if (data == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data é obrigatório"));
				return;
			}

			Base base = baseService.findById(baseSelecionada);


			logger.info("Limpando base na data: {}", data);
			arquivoService.limparbase(base, data);
			logger.info("Limpando base concluida com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String voltar() {
		return VOLTAR;
	}

}

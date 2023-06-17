package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.beanio.DadosArquivoSng;
import br.com.m3Tech.solucoesFromtis.dto.CnabBaixaOrgaoDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabIntegracaoSngDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IGeradorCnab;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class SngController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private static final String VOLTAR = "/pages/cadastros/home.xhtml";


	@Autowired
	private IBaseService baseService;
	@Autowired
	private IArquivoService arquivoService;
	@Autowired
	private IConfGlobalService confGlobalService;
	@Autowired
	private  IGeradorCnab geradorCnab;
		
	
	
	private Integer baseSelecionada;
	
	private String path;
	
	private List<String> titulosNoBanco;
	
	private CnabIntegracaoSngDto cnab;
	
	private List<Base> bases;

	
	@PostConstruct
	public void init() {
		
		bases = baseService.findAll();
		path = confGlobalService.getConfGlobal().getPath();

				
	}
	
	public void selecionandoBase() {
		System.out.println("Selecionando Base " + baseSelecionada );
		cnab = new CnabIntegracaoSngDto();
		atualizarTabela();
		
	}


	
	public void atualizarTabela() {
		
		try {
		
			Base base = baseService.findById(baseSelecionada);
		
			titulosNoBanco = arquivoService.findChassis(base);
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	public void atualizarBases() {
		System.out.println("Atualizar Base");
		
		bases = baseService.findAll();
	}
	
	public void adicionarTitulo(String item) {
		cnab.getTitulos().add(new DadosArquivoSng(item));
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
	
			geradorCnab.gerarArquivoSng(cnab, path);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cnab Gerado Com Sucesso."));
			
			cnab = new CnabIntegracaoSngDto();
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String voltar() {
		return VOLTAR;
	}

}

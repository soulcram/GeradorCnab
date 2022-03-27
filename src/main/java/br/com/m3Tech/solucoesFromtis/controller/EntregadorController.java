//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.annotation.SessionScope;
//
//import br.com.m3Tech.solucoesFromtis.model.Entregador;
//import br.com.m3Tech.solucoesFromtis.service.IEntregadorService;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class EntregadorController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CONSULTAR = "/pages/cadastros/consultaEntregador.xhtml";
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarEntregador.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IEntregadorService entregadorService;
//		
//	//Filtros da tela
//	private String nomeEntregador;
//	private String docEntregador;
//	private String telefone;
//	
//	
//	private Entregador entregador;
//	private List<Entregador> entregadores;
//
//	
//	@PostConstruct
//	public void init() {
//		
//		this.entregador = new Entregador();
//				
//	}
//	
//	public void consultar() {
//		
//		this.entregadores = entregadorService.findAllByNomeOrDocOrTel(nomeEntregador,docEntregador,telefone);
//		
//		if(entregadores == null || entregadores.isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "A pesquisa n�o retornou nenhum resultado."));
//		}
//		
//	}
//	
//	public String cadastrar() {
//		clear();
//		return CADASTRAR;
//	}
//	
//	private void clear() {
//		this.entregador = new Entregador();
//	}
//
//	public String alterar(Integer id) {
//		this.entregador = entregadorService.findById(id).get();
//		return CADASTRAR;
//	}
//	
//	public String excluir(Integer id) {
//		this.entregadorService.deletar(id);
//		consultar();
//		return "";
//	}
//	
//	public String salvar() {
//		
//		if(cadastroInvalido()) {
//			return "";
//		}
//				
//		try {			
//			entregadorService.salvar(this.entregador);
//			consultar();
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
//			e.printStackTrace();
//		}
//		
//		
//		return CONSULTAR;
//	}
//	
//	
//	private boolean cadastroInvalido() {
//		if(StringUtils.emptyOrNull(entregador.getNome())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nome do entregador � obrigat�rio."));
//			return true;
//		}
//
//		return false;
//	}
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
//import org.primefaces.model.DualListModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.annotation.SessionScope;
//
//import br.com.m3Tech.solucoesFromtis.model.Oferta;
//import br.com.m3Tech.solucoesFromtis.model.Produto;
//import br.com.m3Tech.solucoesFromtis.service.IOfertaService;
//import br.com.m3Tech.solucoesFromtis.service.IProdutoService;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class OfertaController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarOferta.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IProdutoService produtoService;
//	
//	@Autowired
//	private IOfertaService ofertaService;
//	
//	private DualListModel<Produto> produtos;
//
//	@PostConstruct
//	public void init() {
//				
//        List<Produto> themesSource = produtoService.findAll();
//        List<Produto> themesTarget = new ArrayList<Produto>();
//        
//        for(Oferta itemOferta : ofertaService.findAll()){
//        	themesTarget.add(itemOferta.getProduto());
//        	themesSource.remove(itemOferta.getProduto());
//        }
//         
//        produtos = new DualListModel<Produto>(themesSource, themesTarget);
//				
//	}
//
//	
//	public void consultar() {
//		
//	}
//	
//	public String cadastrar() {
//		return VOLTAR;
//	}
//	
//	private void clear() {
//	}
//
//	public String alterar(Integer id) {
//		return CADASTRAR;
//	}
//	
//	public String excluir(Integer id) {
//		return VOLTAR;
//	}
//	
//	public String salvar() {
//		
//		if(ofertaInvalida()) {
//			return "";
//		}
//		
//		try {	
//			ofertaService.deletar(ofertaService.findAll());
//			for(Produto item : produtos.getTarget()) {
//				ofertaService.salvar(new Oferta(null, item));
//			}
//		
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
//			e.printStackTrace();
//		}
//		
//		clear();
//		return VOLTAR;
//	}
//
//	
//	private boolean ofertaInvalida() {
//		if(produtos.getTarget().isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "� necess�rio selecionar pelo menos 1item."));
//			return true;
//		}
//
//		return false;
//	}
//
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

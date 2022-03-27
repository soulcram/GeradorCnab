//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
//import org.primefaces.PrimeFaces;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.annotation.SessionScope;
//
//import br.com.m3Tech.solucoesFromtis.model.ConfiguracaoGlobal;
//import br.com.m3Tech.solucoesFromtis.model.Produto;
//import br.com.m3Tech.solucoesFromtis.service.IConfiguracaoGlobalService;
//import br.com.m3Tech.solucoesFromtis.service.IProdutoService;
//import br.com.m3Tech.utils.BigDecimalUtils;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class ProdutoController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
////	
////	private static final String CONSULTAR = "/pages/cadastros/consultaProduto.xhtml";
////	private static final String CADASTRAR = "/pages/cadastros/cadastrarProduto.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IProdutoService produtoService;
//	
//	@Autowired
//	private IConfiguracaoGlobalService configuracaoGlobalService;
//	
//	private List<Produto> produtos;
//
//    private Produto produtoSelecionado;
//
//    private List<Produto> produtosSelecionados;
//
//    @PostConstruct
//    public void init() {
//        this.produtos = this.produtoService.findAll();
//    }
//
//    public List<Produto> getProdutos() {
//        return produtos;
//    }
//
//    public Produto getprodutoSelecionado() {
//        return produtoSelecionado;
//    }
//
//    public void setprodutoSelecionado(Produto produtoSelecionado) {
//        this.produtoSelecionado = produtoSelecionado;
//    }
//
//    public List<Produto> getprodutosSelecionados() {
//        return produtosSelecionados;
//    }
//
//    public void setprodutosSelecionados(List<Produto> produtosSelecionados) {
//        this.produtosSelecionados = produtosSelecionados;
//    }
//
//    public void openNew() {
//        this.produtoSelecionado = new Produto();
//    }
//
//    public void salvarProduto() {
//    	
//    	if(!ehProdutoValido()) {
//    		return;
//    	}
//    	
//    	
//    	Integer idProduto = produtoSelecionado.getIdProduto();
//    	
//    	this.produtoService.salvar(produtoSelecionado);
//    	
//        if (idProduto == null) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Adicionado"));
//        }
//        else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Atualizado"));
//        }
//        
//        pesquisar();
//
//        this.produtoSelecionado = null;
//        
//        PrimeFaces.current().executeScript("PF('manageProdutoDialog').hide()");
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
//    }
//
//    private boolean ehProdutoValido() {
//
//    	if(StringUtils.emptyOrNull(produtoSelecionado.getCategoria())) {
//    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria é um campo obrigatório."));
//    		return false;
//    	}
//    	
//    	if(StringUtils.emptyOrNull(produtoSelecionado.getNome())) {
//    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nome é um campo obrigatório."));
//    		return false;
//    	}
//    	
//    	if(BigDecimalUtils.isNull(produtoSelecionado.getValor())){
//    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valor é um campo obrigatório."));
//    		return false;
//    	}
//    	
//		return true;
//	}
//
//	public void pesquisar() {
//    	this.produtos= produtoService.findAll();
//    }
//    
//    public void deletarProduto() {
//    	
//    	this.produtoService.deletar(this.produtoSelecionado.getIdProduto());
//    	
//        this.produtos.remove(this.produtoSelecionado);
//        this.produtoSelecionado = null;
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Removido"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
//    }
//
//    public String getDeleteButtonMessage() {
//        if (hasProdutosSelecionados()) {
//            int size = this.produtosSelecionados.size();
//            return size > 1 ? size + " Produtos selecionado" : "1 Produto selecionado";
//        }
//
//        return "Delete";
//    }
//
//    public boolean hasProdutosSelecionados() {
//        return this.produtosSelecionados != null && !this.produtosSelecionados.isEmpty();
//    }
//
//    public void deletarProdutosSelecionados() {
//    	
//    	
//    	produtosSelecionados.forEach(p -> produtoService.deletar(p.getIdProduto()));
//    	
//        this.produtos = produtoService.findAll();
//        this.produtosSelecionados = null;
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produtos Removido"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
////        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
//    }
//	
//	public List<String> getCategorias(){
//		
//		ConfiguracaoGlobal configuracaoGlobal = configuracaoGlobalService.findAll().get(0);
//		
//		return Arrays.asList(
//				configuracaoGlobal.getCategoria1(),
//				configuracaoGlobal.getCategoria2(),
//				configuracaoGlobal.getCategoria3()				
//		);
//	}
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

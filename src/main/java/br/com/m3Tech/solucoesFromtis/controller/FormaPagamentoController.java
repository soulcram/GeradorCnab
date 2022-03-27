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
//import br.com.m3Tech.solucoesFromtis.model.FormaPagamento;
//import br.com.m3Tech.solucoesFromtis.service.IFormaPagamentoService;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class FormaPagamentoController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CONSULTAR = "/pages/cadastros/consultaFormaPagamento.xhtml";
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarFormaPagamento.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IFormaPagamentoService formaPagamentoService;
//		
//	//Filtros da tela
//	private String nomeFormaPagamento;
//	
//	
//	private FormaPagamento formaPagamento;
//	private List<FormaPagamento> formaPagamentos;
//
//	
//	@PostConstruct
//	public void init() {
//		
//		this.formaPagamento = new FormaPagamento();
//				
//	}
//	
//	public void consultar() {
//		
//		this.formaPagamentos = formaPagamentoService.findAllByNome(nomeFormaPagamento);
//		
//		if(formaPagamentos == null || formaPagamentos.isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "A pesquisa nao retornou nenhum resultado."));
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
//		this.formaPagamento = new FormaPagamento();
//	}
//
//	public String alterar(Integer id) {
//		this.formaPagamento = formaPagamentoService.findById(id).get();
//		return CADASTRAR;
//	}
//	
//	public String excluir(Integer id) {
//		this.formaPagamentoService.deletar(id);
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
//			formaPagamentoService.salvar(this.formaPagamento);
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
//		if(StringUtils.emptyOrNull(formaPagamento.getNome())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nome da forma de pagamento eh obrigatorio."));
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

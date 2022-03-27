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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.annotation.SessionScope;
//
//import br.com.m3Tech.solucoesFromtis.exception.BussinesException;
//import br.com.m3Tech.solucoesFromtis.model.Endereco;
//import br.com.m3Tech.solucoesFromtis.service.IEnderecoService;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class EnderecoController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CONSULTAR = "/pages/cadastros/consultaEndereco.xhtml";
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarEndereco.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IEnderecoService enderecoService;
//	
//	//Filtros da tela
//	private String nomeDaRua;
//	private String cep;
//	
//	
//	private Endereco endereco;
//	private List<Endereco> enderecos;
//	private Integer idEndereco;
//
//	
//	@PostConstruct
//	public void init() {
//		
//		this.endereco = new Endereco();
//				
//	}
//	
//	public void consultar() {
//		
//		this.enderecos = enderecoService.findAllByRuaOrCep(nomeDaRua,cep);
//		
//		if(enderecos == null || enderecos.isEmpty()) {
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
//		idEndereco = null;
//		this.endereco = new Endereco();
//	}
//
//	public String alterar(Integer id) {
//		this.endereco = enderecoService.findById(id).get();
//		consultar();
//		return CADASTRAR;
//	}
//	
//	public String excluir(Integer id) {
//		this.enderecoService.deletar(id);
//		consultar();
//		return "";
//	}
//	
//	public String salvar() {
//		
//		if(enderecoInvalido()) {
//			return "";
//		}
//		
//		endereco.setCep(endereco.getCep().replaceAll("-",""));
//		
//		try {			
//			enderecoService.salvar(this.endereco);
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
//	public List<String> getEstados() {
//		List<String> retorno = new ArrayList<>();
//		retorno.add("RJ");
//		retorno.add("MG");
//		retorno.add("ES");
//		retorno.add("RS");
//		retorno.add("SC");
//		retorno.add("PR");
//		retorno.add("MT");
//		retorno.add("MS");
//		retorno.add("GO");
//		retorno.add("DF");
//		retorno.add("BA");
//		retorno.add("SE");
//		retorno.add("AL");
//		retorno.add("PE");
//		retorno.add("PB");
//		retorno.add("RN");
//		retorno.add("CE");
//		retorno.add("TO");
//		retorno.add("MA");
//		retorno.add("PA");
//		retorno.add("AP");
//		retorno.add("AM");
//		retorno.add("AC");
//		retorno.add("RR");
//		retorno.add("RO");
//		retorno.add("PI");
//		
//		return retorno;
//	}
//	
//	private boolean enderecoInvalido() {
//		if(StringUtils.emptyOrNull(endereco.getLogradouro())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nome da rua eh obrigatorio."));
//			return true;
//		}
//		
//		if(StringUtils.emptyOrNull(endereco.getCep())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cep eh obrigatorio."));
//			return true;
//		}
//		
//		if(StringUtils.emptyOrNull(endereco.getBairro())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Bairro eh obrigatorio."));
//			return true;
//		}
//		
//		if(StringUtils.emptyOrNull(endereco.getCidade())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cidade eh obrigatorio."));
//			return true;
//		}
//		
//		if(StringUtils.emptyOrNull(endereco.getEstado())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cidade eh obrigatorio."));
//			return true;
//		}
//		return false;
//	}
//	
//	public void buscarPorCep() {
//		try {
//			endereco = enderecoService.findByCep(endereco.getCep().replaceAll("-",""));
//			
//			if(endereco == null) {
//				endereco = new Endereco();
//			}
//		} catch (BussinesException e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cep invalido."));
//			e.printStackTrace();
//		}
//	}
//	
//	public void buscarPorLogradouro() {
//		try {
//			endereco = enderecoService.findOneByLogradouro(endereco.getLogradouro());
//			
//			if(endereco == null) {
//				endereco = new Endereco();
//			}
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erro ao consultar rua."));
//			e.printStackTrace();
//		}
//	}
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

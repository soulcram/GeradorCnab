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
//import br.com.m3Tech.solucoesFromtis.model.Cliente;
//import br.com.m3Tech.solucoesFromtis.model.ClienteEndereco;
//import br.com.m3Tech.solucoesFromtis.model.Endereco;
//import br.com.m3Tech.solucoesFromtis.service.IClienteService;
//import br.com.m3Tech.solucoesFromtis.service.IEnderecoService;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class ClienteController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CONSULTAR = "/pages/cadastros/consultaCliente.xhtml";
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarCliente.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	@Autowired
//	private IClienteService clienteService;
//	
//	@Autowired
//	private IEnderecoService enderecoService;
//		
//	//Filtros da tela
//	private String nomeCliente;
//	private String docCliente;
//	private String telefone;
//	
//	
//	private Cliente cliente;
//	private ClienteEndereco clienteEndereco;
//	private List<Cliente> clientes;
//	private List<Endereco> enderecos;
//	private Integer idCliente;
//
//	private boolean adicionandoNovo;
//	private boolean consultandoEnd;
//	
//	@PostConstruct
//	public void init() {
//		
//		this.cliente = new Cliente();
//				
//	}
//	
//	public void consultar() {
//		
//		this.clientes = clienteService.findAllByNomeOrDocOrTel(nomeCliente,docCliente,telefone);
//		
//		if(clientes == null || clientes.isEmpty()) {
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
//		idCliente = null;
//		this.cliente = new Cliente();
//	}
//
//	public String alterar(Integer id) {
//		this.cliente = clienteService.findById(id).get();
//		adicionandoNovo = false;
//		return CADASTRAR;
//	}
//	
//	public void alterarEndereco(ClienteEndereco clienteEndereco) {
//		this.clienteEndereco = clienteEndereco;
//		this.adicionandoNovo = true;
//	}
//	
//	public void novoEndereco() {
//		this.adicionandoNovo  = true;
//		clienteEndereco= new ClienteEndereco();
//	}
//	
//	public void adicionarEndereco() {
//		cliente.getClienteEnderecos().remove(clienteEndereco);
//		cliente.getClienteEnderecos().add(clienteEndereco);
//		this.adicionandoNovo = false;
//	}
//	
//	public void excluirEndereco(ClienteEndereco clienteEndereco) {
//		this.cliente.getClienteEnderecos().remove(clienteEndereco);
//	}
//	
//	public String excluir(Integer id) {
//		this.clienteService.deletar(id);
//		consultar();
//		return "";
//	}
//	
//	public String salvar() {
//		
//		if(enderecoInvalido()) {
//			return "";
//		}
//		if(cliente.getClienteEnderecos() != null && !cliente.getClienteEnderecos().isEmpty()) {
//			
//			for(ClienteEndereco item : cliente.getClienteEnderecos()) {
//				enderecoService.salvar(item.getEndereco());
//			}
//		}
//		
//		if(StringUtils.emptyOrNull(cliente.getTelefone())) {
//			cliente.setTelefone(telefone);
//		}
////		cliente.setCep(cliente.getCep().replaceAll("-",""));
//		
//		try {			
//			clienteService.salvar(this.cliente);
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
//		retorno.add("SP");
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
//	public void selecionarEndereco(Endereco endereco) {
//		consultandoEnd = false;
//		clienteEndereco.setEndereco(endereco);
//	}
//	
//	public void buscarPorCep() {
//		try {
//			clienteEndereco.setEndereco(enderecoService.findByCep(clienteEndereco.getEndereco().getCep().replaceAll("-","")));
//			
//			if(clienteEndereco.getEndereco() == null) {
//				clienteEndereco.setEndereco(new Endereco());
//			}
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cep inválido."));
//			e.printStackTrace();
//		}
//	}
//	
//	public void buscarPorLogradouro() {
//		try {
//			consultandoEnd = true;
//			enderecos = enderecoService.findByLogradouro(clienteEndereco.getEndereco().getLogradouro());
//
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erro ao consultar rua."));
//			e.printStackTrace();
//		}
//	}
//	
//	public void buscarPorCliente() {
//		try {
//			Cliente find = clienteService.findByTel(telefone);
//			if(find != null) {
//				cliente = find;
//			}else {
//				cliente = new Cliente();
//			}
//
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erro ao consulta cliente."));
//			e.printStackTrace();
//		}
//	}
//	
//	private boolean enderecoInvalido() {
//		if(StringUtils.emptyOrNull(cliente.getNome())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nome do cliente eh obrigatorio."));
//			return true;
//		}
//
//		
//		if(StringUtils.emptyOrNull(cliente.getTelefone()) && StringUtils.emptyOrNull(telefone)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Telefone eh obrigatorio."));
//			return true;
//		}
//		return false;
//	}
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

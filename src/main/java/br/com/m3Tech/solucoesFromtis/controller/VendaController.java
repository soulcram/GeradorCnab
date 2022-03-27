//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.annotation.SessionScope;
//
//import br.com.m3Tech.solucoesFromtis.dto.PedidoAbertoDto;
//import br.com.m3Tech.solucoesFromtis.model.Bina;
//import br.com.m3Tech.solucoesFromtis.model.Cliente;
//import br.com.m3Tech.solucoesFromtis.model.ClienteEndereco;
//import br.com.m3Tech.solucoesFromtis.model.ConfiguracaoGlobal;
//import br.com.m3Tech.solucoesFromtis.model.Endereco;
//import br.com.m3Tech.solucoesFromtis.model.Entregador;
//import br.com.m3Tech.solucoesFromtis.model.Pedido;
//import br.com.m3Tech.solucoesFromtis.model.Produto;
//import br.com.m3Tech.solucoesFromtis.model.ProdutoPedido;
//import br.com.m3Tech.solucoesFromtis.service.IBinaService;
//import br.com.m3Tech.solucoesFromtis.service.IClienteService;
//import br.com.m3Tech.solucoesFromtis.service.IConfiguracaoGlobalService;
//import br.com.m3Tech.solucoesFromtis.service.IEnderecoService;
//import br.com.m3Tech.solucoesFromtis.service.IEntregadorService;
//import br.com.m3Tech.solucoesFromtis.service.IFormaPagamentoService;
//import br.com.m3Tech.solucoesFromtis.service.IOfertaService;
//import br.com.m3Tech.solucoesFromtis.service.IPedidoService;
//import br.com.m3Tech.solucoesFromtis.service.IProdutoPedidoService;
//import br.com.m3Tech.solucoesFromtis.service.IProdutoService;
//import br.com.m3Tech.utils.BooleanUtils;
//import br.com.m3Tech.utils.ImpressoraUtils;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class VendaController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String CONSULTAR = "/pages/cadastros/consultaVenda.xhtml";
//	private static final String CADASTRAR = "/pages/cadastros/cadastrarPedido.xhtml";
//	private static final String VISUALIZAR = "/pages/cadastros/visualizarPedido.xhtml";
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	private IClienteService clienteService;
//	private IPedidoService pedidoService;
//	private IEnderecoService enderecoService;
//	private IProdutoService produtoService;
//	private IOfertaService ofertaService;
//	private IEntregadorService entregadorService;
//	private IFormaPagamentoService formaPagamentoService;
//	private IConfiguracaoGlobalService configuracaoGlobalService;
//	private IProdutoPedidoService produtoPedidoService;
//	private IBinaService binaService;
//	
//	@Autowired
//	public VendaController( IPedidoService pedidoService,
//							IClienteService clienteService,
//							IEnderecoService enderecoService,
//							IProdutoService produtoService,
//							IOfertaService ofertaService,
//							IEntregadorService entregadorService,
//							IFormaPagamentoService formaPagamentoService,
//						    IConfiguracaoGlobalService configuracaoGlobalService,
//						    IProdutoPedidoService produtoPedidoService,
//						    IBinaService binaService) {
//		
//		this.pedidoService = pedidoService;
//		this.clienteService = clienteService;
//		this.enderecoService = enderecoService;
//		this.produtoService = produtoService;
//		this.ofertaService = ofertaService;
//		this.entregadorService = entregadorService;
//		this.formaPagamentoService = formaPagamentoService;
//		this.configuracaoGlobalService = configuracaoGlobalService;
//		this.produtoPedidoService = produtoPedidoService;
//		this.binaService = binaService;
//		init();
//	}
//	
//	//Filtros da tela
//	private String nome;
//	private String documento;
//	private String nomeRua;
//	
//	//Filtros da tela
//	private String telefone;
//	private Date data;
//	
//	//controle de tela
//	
//	private Boolean editandoPedido = false;
//	private Boolean exibirCadastroCliente;
//	private Boolean consultandoEnd = false;
//	private boolean adicionandoNovo;
//	
//	private Pedido pedido;
//	private Cliente cliente;
//	private ClienteEndereco clienteEndereco;
//	private List<Cliente> clientes;
//	private List<Endereco> enderecos;
//	private List<Pedido> pedidos;
//	private Integer idPedido;
//	private String nomeCliente;
//	private Entregador entregador;
//	
//	private List<Produto> tipoProduto1 = new ArrayList<>();
//	private List<Produto> tipoProduto2 = new ArrayList<>();
//	private List<Produto> tipoProduto3 = new ArrayList<>();
//	private List<Entregador> entregadores = new ArrayList<>();
//	private List<String> formasDePagamentos = new ArrayList<>();
//	private List<Pedido> vendas;
//	private List<PedidoAbertoDto> listaPedidosEmAberto;
//	
//	
//	@PostConstruct
//	public void init() {
//		data = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//		this.pedido = new Pedido();
//		vendas = pedidoService.findAllByData(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		listaPedidosEmAberto = new ArrayList<>();
//		clientes =  new ArrayList<>();
//		idPedido = null;
//		clienteEndereco = new ClienteEndereco();
//		pedido.setTaxaEntrega(BigDecimal.ZERO);
//		this.cliente = new Cliente();
//		exibirCadastroCliente = true;
//		tipoProduto1 = new ArrayList<>();
//		tipoProduto2= new ArrayList<>();
//		tipoProduto3= new ArrayList<>();
//		entregadores = entregadorService.findAll();
//		entregador = entregadores.get(0);
//		pedido.setEntregador(entregador.getNome());
//		pedido.setCobrarTaxa(BooleanUtils.defaultFalseIfNull(entregador.getCobrarTaxa()));
//		formasDePagamentos = addFormasDePagamentos();
//		
//		atualizarlistaProdutos();
//				
//	}
//	
//	public void consultarVendas() {
//		
//		this.vendas = pedidoService.findAllByData(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		
//		if(vendas == null || vendas.isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "A pesquisa não retornou nenhum resultado."));
//		}
//		
//	}
//	
//	
//	public void consultarAuto() {
//
//		List<Bina> binas = binaService.findAllByData(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		
//		for(Bina b : binas) {
//			verificarEAddPedidoEmAberto(b.getNumero());
//		}
//
//
//	}
//	
//	private void verificarEAddPedidoEmAberto(String numero) {
//		if(!numeroAtendido(numero)) {
//			addPedidoEmAberto(numero);
//		}
//	}
//	
//	private void addPedidoEmAberto(String numero) {
//		Cliente cliente = clienteService.findByTel(numero);
//		
//		if(StringUtils.emptyOrNull(cliente.getTelefone())) {
//			cliente.setTelefone(numero);
//			cliente.setNome("Sem Cadastro");
//		}
//		
//		ClienteEndereco clienteEndereco = new ClienteEndereco();
//		
//		if(cliente.getClienteEnderecos() != null && !cliente.getClienteEnderecos().isEmpty()) {
//			clienteEndereco = cliente.getClienteEnderecos().get(0);
//		}
//		
//		Pedido novoPedido = new Pedido();
//		novoPedido.setTelefoneCliente(numero);
//		
//		listaPedidosEmAberto.add(new PedidoAbertoDto(novoPedido, cliente, clienteEndereco));
//		
//	}
//
//	private boolean numeroAtendido(String numero) {
//		return listaPedidosEmAberto.stream()
//				                   .filter(p -> numero.equals(p.getPedido().getTelefoneCliente()))
//				                   .findFirst().isPresent();
//	}
//
//
//	
//	public void buscarPorTelefone() {
//		
//		limparPedido();
//		
//		if(StringUtils.emptyOrNull(telefone)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone é obrigatório", ""));
//			return;
//		}
//
//		verificarEAddPedidoEmAberto(telefone);
//
//		if (listaPedidosEmAberto.size() == 1) {
//			this.pedido = listaPedidosEmAberto.get(0).getPedido();
//			this.cliente = listaPedidosEmAberto.get(0).getCliente();
//			this.clienteEndereco = listaPedidosEmAberto.get(0).getClienteEndereco();
//			this.editandoPedido = true;
//
//			if (cliente != null && cliente.getTelefone() != null) {
//				exibirCadastroCliente = false;
//			} else {
//				exibirCadastroCliente = true;
//			}
//
//			if(entregadores != null && !entregadores.isEmpty()) {
//				this.entregador = entregadores.get(0);
//				this.pedido.setCobrarTaxa(entregador.getCobrarTaxa());
//				this.pedido.setEntregador(entregador.getNome());
//			}
//			
//		}
//		
//		telefone = null;
//
//
//	}
//	
//	private void limparPedido() {
//		this.pedido = new Pedido();
//		this.cliente = new Cliente();
//		this.clienteEndereco = new ClienteEndereco();
//	}
//	
//	public void atenderPedidoAberto(PedidoAbertoDto pedidoAbertoDto) {
//		
//		this.pedido = pedidoAbertoDto.getPedido();
//		this.cliente = pedidoAbertoDto.getCliente();
//		this.clienteEndereco = pedidoAbertoDto.getClienteEndereco();		
//		this.editandoPedido = true;
//		Optional<Entregador> optional = entregadores.stream()
//		                 .filter(e -> e.getNome().equals(pedidoAbertoDto.getPedido().getEntregador()))
//		                 .findFirst();
//		
//		if(optional.isPresent()) {
//			this.entregador = optional.get();
//			this.pedido.setCobrarTaxa(entregador.getCobrarTaxa());
//			this.pedido.setEntregador(entregador.getNome());
//		}else{
//			if(entregadores != null && !entregadores.isEmpty()) {
//				this.entregador = entregadores.get(0);
//				this.pedido.setCobrarTaxa(entregador.getCobrarTaxa());
//				this.pedido.setEntregador(entregador.getNome());
//			}
//		}
//		
//		if(cliente != null && cliente.getTelefone() != null) {
//			exibirCadastroCliente = false;
//		}else {
//			exibirCadastroCliente = true;
//		}
//
//
//
//	}
//	
//	public void cancelarPedidoAberto(PedidoAbertoDto pedidoAbertoDto) {
//		
//		removerPedidoDaListaDePedidosEmAberto(pedidoAbertoDto.getCliente().getTelefone());
//		editandoPedido = false;
//	}
//	
//	public void cancelar() {
//		
//		removerPedidoDaListaDePedidosEmAberto(this.cliente.getTelefone());
//		editandoPedido = false;
//
//	}
//	
//	public String excluir() {
//		
//		pedidoService.deletar(pedido.getIdPedido());
//		
//		return CONSULTAR;
//	}
//
//
//	public String visualizar(Integer id) {
//		this.pedido = pedidoService.findById(id).get();
//		return VISUALIZAR;
//	}
//	
//	public void imprimirPedido() {
//		
//		String impressora = configuracaoGlobalService.findAll().get(0).getImpressora();
//		
//		try {
//		ImpressoraUtils.imprimir(impressora, this.pedido.montarPedido(configuracaoGlobalService.findAll().get(0).getNomeEmpresa()));
//	
//		}catch(Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Erro ao imprimir na impressora: " + impressora));
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void removerPedidoEmAberto(String numero) {
//		
//		binaService.deleteAllByTelefone(numero);
//		
//		Optional<PedidoAbertoDto> optional = listaPedidosEmAberto.stream()
//        .filter(p -> numero.equals(p.getPedido().getTelefoneCliente()))
//        .findFirst();
//		
//		if(optional.isPresent()) {
//			listaPedidosEmAberto.remove(optional.get());
//		}
//	}
//	
//	public boolean getExistePedidosEmAberto() {
//		return listaPedidosEmAberto != null && !listaPedidosEmAberto.isEmpty();
//	}
//	
//
//	public void atualizarClientes() {
//		clientes = clienteService.findAllByNome(pedido.getNomeCliente());
//	}
//	
//	public void selecionarCliente(Integer idCliente) {
//		cliente = clienteService.findById(idCliente).get();
//		
//		exibirCadastroCliente = false;
//		nomeCliente = null;
//		
//	}
//	
//	private void atualizarlistaProdutos() {
//		
//		tipoProduto1 = new ArrayList<>();
//		tipoProduto2 = new ArrayList<>();
//		tipoProduto3 = new ArrayList<>();
//		
//		ConfiguracaoGlobal configuracaoGlobal = configuracaoGlobalService.findAll().get(0);
//		
//		for(Produto item : produtoService.findAll()) {
//			
//			if(item.getAtivo()) {
//				
//				if(configuracaoGlobal.getCategoria1().equals(item.getCategoria())) {
//					tipoProduto1.add(item);
//				}else if(configuracaoGlobal.getCategoria2().equals(item.getCategoria())) {
//					tipoProduto2.add(item);
//				}else if(configuracaoGlobal.getCategoria3().equals(item.getCategoria())) {
//					tipoProduto3.add(item);
//				}
//				
//			}
//
//		}
//		
//	}
//
//	
//	public void consultar() {
//		
//	}
//	
//	public void setCliente(Cliente cli) {
//		this.cliente = cli;
//		exibirCadastroCliente = (cli == null);
//		if(this.cliente == null) {
//			this.cliente = new Cliente();
//		}
//	}
//	
//	public void novoPedido() {
//		this.pedido = new Pedido();
//		this.cliente = new Cliente();
//		this.clienteEndereco = new ClienteEndereco();
//		this.editandoPedido = true;
//		
//	}
//
//	public String alterar(Integer id) {
//		this.cliente = clienteService.findById(id).get();
//		return CADASTRAR;
//	}
//	
//	public void adicionarAoPedido(Integer id) {
//		if(pedidoJaTemProduto(id)) {
//			addQuantidade(id);
//		}else {
//			this.pedido.getProdutos().add(new ProdutoPedido(produtoService.findById(id).get(), 1));
//		}
//		this.pedido.calcularTotal();
//	}
//	
//	private boolean pedidoJaTemProduto(Integer id) {
//		
//		return pedido.getProdutos().stream().filter(p -> p.getIdProduto() == id).findFirst().isPresent();
//
//	}
//	
//	public void addQuantidade(Integer id) {
//		for(ProdutoPedido item : pedido.getProdutos()) {
//			if(item.getIdProduto() == id) {
//				item.addQuantidade();
//				this.pedido.calcularTotal();
//				if(item.getIdProdutoPedido() != null) {
//					produtoPedidoService.salvar(item);
//					pedidoService.salvar(pedido);
//				}
//				break;
//			}
//		}
//		
//	}
//	
//	public void diminuirQuantidade(Integer id) {
//		for(ProdutoPedido item : pedido.getProdutos()) {
//			if(item.getIdProduto() == id) {
//				if(item.getQuantidade() > 0) {
//					item.removerQuantidade();
//					this.pedido.calcularTotal();
//					if(item.getIdProdutoPedido() != null) {
//						produtoPedidoService.salvar(item);
//						pedidoService.salvar(pedido);;
//					}
//				}else {
//					removerDoPedido();
//					this.pedido.calcularTotal();
//					if(item.getIdProdutoPedido() != null) {
//						produtoPedidoService.deletar(item.getIdProdutoPedido());
//						pedidoService.salvar(pedido);
//					}
//				}
//				
//				break;
//			}
//		}
//	}
//	
//	public void removerDoPedido() {
//		
//		pedido.getProdutos().stream().filter(item1 -> item1.getQuantidade() < 1).forEach(item -> pedido.getProdutos().remove(item));
//		
//		this.pedido.calcularTotal();
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
//		if(pedidoInvalido() || clienteInvalido()) {
//			return "";
//		}
//
//		 this.pedido.setNomeCliente(cliente.getNome());
//		 this.pedido.setTelefoneCliente(cliente.getTelefone());
//		 this.pedido.setBairro(clienteEndereco.getEndereco().getBairro());
//		 this.pedido.setComplemento(clienteEndereco.getComplemento());
//		 this.pedido.setLogradouro(clienteEndereco.getEndereco().getLogradouro());
//		 this.pedido.setNumeroResidencia(clienteEndereco.getNumero());
//		 this.pedido.setObservacao(addObservacao());
//		 this.pedido.setData(LocalDate.now());
//				
//		try {			
//			pedidoService.salvar(this.pedido);
//			ImpressoraUtils.imprimir(configuracaoGlobalService.findAll().get(0).getImpressora(), pedido.montarPedido(configuracaoGlobalService.findAll().get(0).getNomeEmpresa()));
//			this.telefone = null;
//			editandoPedido = false;
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
//			e.printStackTrace();
//		}
//		
//		removerPedidoDaListaDePedidosEmAberto(pedido.getTelefoneCliente());
//		
//		if(entregadores != null && !entregadores.isEmpty()) {
//			this.entregador = entregadores.get(0);
//		}
//		
//		limparPedido();
//		
//		telefone = "";
//		nome = "";
//		documento = "";
////		init();
//		return CONSULTAR;
//	}
//	
//	private void removerPedidoDaListaDePedidosEmAberto(String telefoneCliente) {
//		
//		Optional<PedidoAbertoDto> optional = listaPedidosEmAberto.stream()
//		                    .filter(p -> p.getCliente().getTelefone().equals(telefoneCliente))
//		                    .findFirst();
//		
//		if(optional.isPresent()) {
//			listaPedidosEmAberto.remove(optional.get());
//		}
//		
//		binaService.deleteAllByTelefone(telefoneCliente);
//		
//	}
//
//	private String addObservacao() {
//		if(this.pedido.getObservacao() != null) {
//			if(pedido.getObservacao().length() > 250) {
//				return pedido.getObservacao().substring(0, 250);
//			}else {
//				return pedido.getObservacao();
//			}
//		}
//		return null;
//	}
//
//	public Pedido getUltimoPedidoByCliente() {
//		
//		if(this.cliente != null && this.cliente.getIdCliente() != null) {
//			return pedidoService.findLastPedidoByCliente(this.cliente.getTelefone());
//		}
//		
//		return null;
//	}
//	
//	public BigDecimal getValorTotalPedido() {
//		if(pedido.getValorDesconto() == null) {
//			pedido.setValorDesconto(BigDecimal.ZERO);
//		}
//		
//		if(pedido.getCobrarTaxa()) {
//			return pedido.getValorTotal() != null ? pedido.getValorTotal().add(pedido.getTaxaEntrega().subtract(pedido.getValorDesconto())) : BigDecimal.ZERO;
//		}else {
//			return pedido.getValorTotal() != null ? pedido.getValorTotal().subtract(pedido.getValorDesconto()) : BigDecimal.ZERO;
//		}
//		
//		
//	}
//
//	
//	public void atualizarTotalPedido() {
//		if(pedido.getValorDesconto() != null) {
//			try {
//				pedido.calcularTotal();
//			}catch(Exception ex) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Desconto não é um valor válido", ex.getMessage()));
//				ex.printStackTrace();
//			}
//		}
//		
//	}
//	
//	public void atualizarEntregador() {
//		
//		if(entregador.getCobrarTaxa()) {
//			pedido.setTaxaEntrega(configuracaoGlobalService.findAll().get(0).getTaxaEntrega());
//		}else {
//			pedido.setTaxaEntrega(BigDecimal.ZERO);
//		}
//
//		pedido.setEntregador(entregador.getNome());
//		pedido.setCobrarTaxa(entregador.getCobrarTaxa());
//		calcularTroco();
//	}
//	
//	public void calcularTroco() {
//		try {
//			if(!StringUtils.emptyOrNull(pedido.getFormaPagamento())) {
//				
//				try {
//				
//					BigDecimal valorPagamento = new BigDecimal(pedido.getFormaPagamento().trim().replaceAll(",", "."));
//				
//					this.pedido.setTroco(valorPagamento.subtract(getValorTotalPedido()));
//				
//				}catch(Exception e) {
//					this.pedido.setTroco(BigDecimal.ZERO);
//				}
//			}else {
//				this.pedido.setTroco(BigDecimal.ZERO);
//			}
//			
//			
//			
//			
//		}catch(Exception e) {
//			this.pedido.setTroco(BigDecimal.ZERO);
//		}
//	}
//	
//	
//	public List<String> addFormasDePagamentos(){
//		List<String> lista = new ArrayList<>();
//		formaPagamentoService.findAllOrderByOrdenacao().forEach(e -> lista.add(e.getNome()));		
//		return lista;
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
//	public void buscarPorCep() {
//
//		try {
//			
//			Endereco byCep = enderecoService.findByCep(clienteEndereco.getEndereco().getCep().replaceAll("-",""));
//			
//			if(byCep == null) {
//				clienteEndereco.setEndereco(new Endereco());
//			}else {
//				nomeRua = byCep.getLogradouro();
//				clienteEndereco.setEndereco(byCep);
//			}
//			
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cep invalido."));
//			e.printStackTrace();
//		}
//	}
//	
//	public void alterarDadosCliente() {
//		telefone = cliente.getTelefone();
//		documento = cliente.getDocumento();
//		nome = cliente.getNome();
////		nomeRua = cliente.getClienteEndereco().getEndereco().getLogradouro();
//		exibirCadastroCliente = true;
//	}
//	
//	public void salvarDadosCliente() {
//		
//		if(clienteInvalido()) {
//			return ;
//		}
//
//		exibirCadastroCliente = false;
//		
//		clienteService.salvar(cliente);
//	}
//	
//	public void buscarPorCliente() {
//		try {
//			Cliente find = clienteService.findByTel(telefone);
//			if(find != null && find.getTelefone() != null){
//				cliente = find;
//				List<ClienteEndereco> listaEnderecos = cliente.getClienteEnderecos();
//				clienteEndereco = listaEnderecos != null && !listaEnderecos.isEmpty() ? listaEnderecos.get(0) : new ClienteEndereco();
//				exibirCadastroCliente = false;
//				getUltimoPedidoByCliente();
//			}else {
//				cliente = new Cliente();
//				exibirCadastroCliente = true;
//			}
//
//			
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Cep invalido."));
//			e.printStackTrace();
//		}
//		
//		atualizarlistaProdutos();
//	}
//	
//	public void buscarPorLogradouro() {
//		try {
//			consultandoEnd = true;
//			
//			enderecos = enderecoService.findByLogradouro(clienteEndereco.getEndereco().getLogradouro());
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erro ao consultar rua."));
//			e.printStackTrace();
//		}
//	}
//	
//	public void selecionarEndereco(Endereco endereco) {
//						
//		clienteEndereco.setEndereco(endereco);
//		
//		consultandoEnd = false;
//	}
//	
//	public String getCategoria1() {
//		
//		String retorno = configuracaoGlobalService.findAll().get(0).getCategoria1();
//		
//		return retorno == null ? "Categoria 1" : retorno;
//		
//	}
//	
//	public String getCategoria2() {
//		
//		String retorno = configuracaoGlobalService.findAll().get(0).getCategoria2();
//		
//		return retorno == null ? "Categoria 2" : retorno;
//		
//	}
//	
//	public String getCategoria3() {
//		
//		String retorno = configuracaoGlobalService.findAll().get(0).getCategoria3();
//		
//		return retorno == null ? "Categoria 3" : retorno;
//		
//	}
//	
//	private boolean pedidoInvalido() {
//		if(pedido.getProdutos() == null || pedido.getProdutos().isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nenhum produto foi adicionado ao pedido."));
//			return true;
//		}
//
//		
//		if(cliente == null) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "É necessário salvar os dados do cliente."));
//			return true;
//		}
//		
//		
//		if(exibirCadastroCliente) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "É necessário salvar os dados do cliente."));
//			return true;
//		}
//
//		return false;
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
//	public void selecionarEndereco(ClienteEndereco clienteEndereco) {
//		this.clienteEndereco = clienteEndereco;
//		exibirCadastroCliente = false;
//	}
//	
//	private boolean clienteInvalido() {
//		if(StringUtils.emptyOrNull(cliente.getNome())) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nome do cliente é obrigatório."));
//			return true;
//		}
//
//		
//		if(StringUtils.emptyOrNull(cliente.getTelefone()) && StringUtils.emptyOrNull(this.telefone)) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Telefone é obrigatório."));
//			return true;
//		}
//
//		return false;
//	}
//	
//	public void addCliente(Cliente cliente) {
//		this.cliente = cliente;
//		List<ClienteEndereco> listaEnderecos = cliente.getClienteEnderecos();
//		this.clienteEndereco = listaEnderecos == null || !listaEnderecos.isEmpty() ? listaEnderecos.get(0) : new ClienteEndereco();
//		atualizarlistaProdutos();
//	}
//	
//	public String voltar() {
//		editandoPedido = false;
//		init();
//		return VOLTAR;
//	}
//	
//	public void voltarPedido() {
//		
//		removerPedidoDaListaDePedidosEmAberto(this.cliente.getTelefone());
//		
//		this.pedido.setEntregador(this.entregador.getNome());
//		
//		listaPedidosEmAberto.add(new PedidoAbertoDto(pedido, cliente, clienteEndereco));
//		
//		editandoPedido = false;
//	}
//	
//	public String fecharVisualizacao() {
//		init();
//		return CONSULTAR;
//	}
//
//}

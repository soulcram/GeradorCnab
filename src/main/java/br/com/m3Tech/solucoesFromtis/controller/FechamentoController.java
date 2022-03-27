//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
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
//import br.com.m3Tech.solucoesFromtis.dto.FechamentoDto;
//import br.com.m3Tech.solucoesFromtis.model.ConfiguracaoGlobal;
//import br.com.m3Tech.solucoesFromtis.model.Pedido;
//import br.com.m3Tech.solucoesFromtis.service.IConfiguracaoGlobalService;
//import br.com.m3Tech.solucoesFromtis.service.IPedidoService;
//import br.com.m3Tech.utils.BigDecimalUtils;
//import br.com.m3Tech.utils.StringUtils;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScope
//@Getter
//@Setter
//@Controller
//public class FechamentoController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//	private IPedidoService pedidoService;
//	private IConfiguracaoGlobalService configuracaoGlobalService;
//	
//	@Autowired
//	public FechamentoController( IPedidoService pedidoService,
//			IConfiguracaoGlobalService configuracaoGlobalService) {
//		
//		this.pedidoService = pedidoService;
//		this.configuracaoGlobalService = configuracaoGlobalService;
//		init();
//	}
//	
//	
//	
//
//	private Date data;
//	private BigDecimal subTotal;
//	private List<FechamentoDto> fechamentos;
//	private Integer qtdeCartao;
//	private Integer qtdePix;
//	private Integer qtdeDinheiro;
//	private BigDecimal totalCartao;
//	private BigDecimal totalPix;
//	private BigDecimal totalDinheiro;
//	private BigDecimal totalDescontos;
//	private Integer totalAlmoco;
//	private ConfiguracaoGlobal configuracaoGlobal;
//	
//	
//	@PostConstruct
//	public void init() {
//		
//		configuracaoGlobal = configuracaoGlobalService.findAll().get(0);
//		data = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//		fechamentos = pedidoService.findAllFechamentoByData(LocalDate.now());
//			
//		if(fechamentos != null && !fechamentos.isEmpty()) {
//			calcularValorSubTotal();
//			calcularOutrosValores();
//		}
//	}
//	
//	private void calcularOutrosValores() {
//		qtdeCartao = 0;
//		qtdeDinheiro = 0;
//		qtdePix = 0;
//		totalCartao = BigDecimal.ZERO;
//		totalDinheiro = BigDecimal.ZERO;
//		totalPix = BigDecimal.ZERO;
//		totalAlmoco = 0; 
//		totalDescontos = BigDecimal.ZERO;
//		
//		List<Pedido> pedidos = pedidoService.findAllByData(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		
//		pedidos.forEach(p -> {
//			totalDescontos = totalDescontos.add(p.getValorDesconto() != null ? p.getValorDesconto() : BigDecimal.ZERO );
//			if("cartao".equals(StringUtils.removerAcentos(p.getFormaPagamento()).toLowerCase())) {
//				qtdeCartao++;
//				totalCartao = totalCartao.add(p.getValorTotal()).subtract(p.getValorDesconto() != null ? p.getValorDesconto() : BigDecimal.ZERO);
//			}else if("Pix".equalsIgnoreCase(StringUtils.removerAcentos(p.getFormaPagamento()))) {
//				qtdePix++;
//				totalPix = totalPix.add(p.getValorTotal()).subtract(p.getValorDesconto() != null ? p.getValorDesconto() : BigDecimal.ZERO);
//			}else {
//				qtdeDinheiro++;
//				totalDinheiro = totalDinheiro.add(p.getValorTotal()).subtract(p.getValorDesconto() != null ? p.getValorDesconto() : BigDecimal.ZERO);
//			}
//			p.getProdutos().forEach(prod -> {
//				if(configuracaoGlobal.getCategoria1().equals(prod.getCategoria())) {
//					totalAlmoco += prod.getQuantidade();
//				}
//			});
//		});
//		
//	}
//
//	public void consultar() {
//		
//		this.fechamentos = pedidoService.findAllFechamentoByData(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		
//		if(fechamentos == null || fechamentos.isEmpty()) {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "A pesquisa não retornou nenhum resultado."));
//			subTotal = BigDecimal.ZERO;
//			qtdeCartao = 0;
//			qtdeDinheiro = 0;
//			totalCartao = BigDecimal.ZERO;
//			totalDinheiro = BigDecimal.ZERO;
//			totalAlmoco = 0;
//		}else {
//			calcularValorSubTotal();
//			calcularOutrosValores();
//		}
//		
//	}
//
//	private void calcularValorSubTotal() {
//		this.subTotal = BigDecimal.ZERO;
//		
//		
//		fechamentos.forEach(f -> subTotal = subTotal.add(BigDecimalUtils.ifNullThenZero(f.getSubTotal())));
//	}
//	
//	public BigDecimal getValorTotal() {
//		return BigDecimalUtils.ifNullThenZero(subTotal).subtract(BigDecimalUtils.ifNullThenZero(totalDescontos));
//	}
//
//	public String voltar() {
//		init();
//		return VOLTAR;
//	}
//
//
//}

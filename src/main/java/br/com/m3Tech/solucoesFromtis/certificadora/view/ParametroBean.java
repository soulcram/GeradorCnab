//package br.com.fromtis.certificadora.view;
//
//import br.com.fromtis.certificadora.domain.ParametroEntity;
//import br.com.fromtis.certificadora.service.ParametrosServiceBean;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.web.context.annotation.SessionScope;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//
//@ManagedBean
//@SessionScope
//@Getter @Setter
//public class ParametroBean extends BaseBean {
//	private static final long serialVersionUID = 3936676934905303208L;
//	private final String CADASTRO = "parametro";
//	
//	@ManagedProperty("#{parametroServiceBean}")
//	private ParametrosServiceBean parametrosServiceBean;
//
//	private String urlWsdlRequisicao;
//	private String urlWsdlRetorno;
//	private Boolean retornaAutomaticamente;
//	private Boolean zip;
//	
//	public String init() {
//		pegaDadosParametro();
//		return CADASTRO;
//	}
//	
//	public void salvaParametro() {
//		parametrosServiceBean.salvaParametro(preencheDadosParametro());
//		addMessageSucessoPadrao();
//	}
//	
//	private ParametroEntity preencheDadosParametro() {
//		ParametroEntity parametro = parametrosServiceBean.getParametro();
//		if(parametro == null) {
//			parametro = new ParametroEntity();
//		}
//		parametro.setUrlWsdlRequisicao(urlWsdlRequisicao);
//		parametro.setUrlWsdlRetorno(urlWsdlRetorno);
//		parametro.setRetornaAutomaticamente(retornaAutomaticamente);
//		parametro.setZip(zip);
//		return parametro;
//	}
//	
//	private void pegaDadosParametro() {
//		ParametroEntity parametro = parametrosServiceBean.getParametro();
//		if(parametro == null) {
//			parametro = new ParametroEntity();
//		}
//		urlWsdlRequisicao = parametro.getUrlWsdlRequisicao();
//		urlWsdlRetorno = parametro.getUrlWsdlRetorno();
//		retornaAutomaticamente = parametro.getRetornaAutomaticamente();
//		zip = parametro.getZip();
//	}
//}

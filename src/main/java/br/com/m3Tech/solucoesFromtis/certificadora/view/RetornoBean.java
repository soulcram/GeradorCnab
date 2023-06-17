//package br.com.m3Tech.solucoesFromtis.certificadora.view;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//
//import com.google.common.collect.Lists;
//
//import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
//import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
//import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GeradorXMLRetorno;
//import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;
//import lombok.Getter;
//import lombok.Setter;
//
//@SessionScoped
//@ManagedBean
//@Getter @Setter
//public class RetornoBean extends BaseBean implements Serializable {
//	private static final long serialVersionUID = 3512233989228686459L;
//
//	private static final String OUT = "retorno";
//	
//	@ManagedProperty("#{guardadorRequisicao}")
//	private GuardadorRequisicoesCertificadora guardadorRequisicao;
//	
//	@ManagedProperty("#{enviadorRetorno}")
//	private EnviadorRetornoCertificadora enviadorRetorno;
//
//	
//	private List<RequisicaoCertificadoraDigitalWrapper> requisicoes;
//	
//	public String init() {
//		atualizaRequisicoes();
//		return OUT;
//	}
//	
//	public void atualizaRequisicoes() {
//		requisicoes = Lists.newArrayList(guardadorRequisicao.pegaRequisicoes());
//	}
//	
//	public void enviarRetorno(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
//		if(parametrosServiceBean.getParametro() == null) {
//			addMessageErro("Cadastre os Parametros primeiro.");
//			return;
//		}
//		
//		RetornoProcessamento retornoEnvio = enviadorRetorno.enviarRetornoCertificadora(requisicaoWrapper.getRequisicao());
//		if(retornoEnvio != null && retornoEnvio.getStatus().equals("00")) {
//			guardadorRequisicao.atualizaRequisicao(requisicaoWrapper, StatusRequisicao.comSucesso());
//			atualizaRequisicoes();
//			addMessageInfo("Enviado o Retorno com Sucesso.");
//		}else {
//			guardadorRequisicao.atualizaRequisicao(requisicaoWrapper, StatusRequisicao.comErro(
//					new StringBuilder().append("Msg Retorno: ").append(retornoEnvio.geraMensagemDoErro()).append(" Status: ").append(retornoEnvio.getStatus()).toString()));
//			addMessageErro(new StringBuilder().append("Ocorreu um erro no envio, Retorno do webservice: ").append(retornoEnvio.geraMensagemDoErro()).append(" Status: ").append(retornoEnvio.getStatus()).toString());
//		}
//	}
//	
//	public void mostrarMsg(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Msg de Retorno", requisicaoWrapper.getStatusRequisicao().getMensagem()));
//	}
//
//	public void download(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
//		final String xml = new GeradorXMLRetorno().gerar(requisicaoWrapper.getRequisicao());
//		try {
//			ControllerUtils.downloadAsAttachment(
//					LocalDateTime.now().toString("yyyyMMddHHss")
//					+ "_" + requisicaoWrapper.getRequisicao().getId()
//					+ ".xml"
//					, new ByteArrayInputStream(xml.getBytes())
//			);
//		} catch (IOException e) {
//			e.printStackTrace();
//			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado");
//		}
//	}
//
//
//}

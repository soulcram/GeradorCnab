//package br.com.m3Tech.solucoesFromtis.certificadora.batch;
//
//import java.util.Set;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
//import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusEnum;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;
//import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
//import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
//import br.com.m3Tech.utils.BooleanUtils;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class GeradorRetornoCertificadoraImpl {
//	private static final String CONFIRMADO_SUCESSO = "00";
//
//	@Autowired
//	private GuardadorRequisicoesCertificadora guardadorRequisicao;
//
//	@Autowired
//	private EnviadorRetornoCertificadora enviadorRetorno;
//
//	@Autowired
//	private IConfGlobalService confGlobalService;
//
//	@Scheduled(fixedDelay = 1000)
//	public void enviaRetornoPelaRequisicao() {
//		
//		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
//		
//		if (BooleanUtils.defaultFalseIfNull(confGlobal.getEnviarRetornoAutomatico())) {
//			Set<RequisicaoCertificadoraDigitalWrapper> requisicoes = guardadorRequisicao.pegaRequisicoes();
//			for (RequisicaoCertificadoraDigitalWrapper requisicaoWrapper : requisicoes) {
//				if (StatusEnum.NAO_ENVIADO.equals(requisicaoWrapper.getStatusRequisicao().getStatus())) {
//					log.info("Encontrado Operacao: {} para Enviar Retorno.", requisicaoWrapper.getRequisicao().getId());
//					RetornoCertificadoDigital retornoCertificadoDigital = new RetornoCertificadoDigital();
//					BeanUtils.copyProperties(requisicaoWrapper.getRequisicao(), retornoCertificadoDigital);
//					retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());
//					
//					log.info("Inicio do Envio do retorno a certificadora.");
//					RetornoProcessamento retorno = enviadorRetorno
//							.enviarRetornoCertificadora(requisicaoWrapper.getRequisicao());
//					log.info("Fim do Envio do retorno a certificadora. Status de Retorno: {}", retorno.getStatus());
//					if (retorno != null && retorno.getStatus().equals(CONFIRMADO_SUCESSO)) {
//						guardadorRequisicao.atualizaRequisicao(requisicaoWrapper, StatusRequisicao.comSucesso());
//					} else {
//						guardadorRequisicao.atualizaRequisicao(requisicaoWrapper,
//								StatusRequisicao.comErro(
//										new StringBuilder().append("Msg Retorno: ")
//												.append(retorno.geraMensagemDoErro())
//												.append(" Status: ")
//												.append(retorno.getStatus()).toString()));
//					}
//				}
//			}
//		}
//	}
//}

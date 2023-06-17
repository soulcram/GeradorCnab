package br.com.m3Tech.solucoesFromtis.scheduled;

import java.io.File;
import java.io.PrintWriter;
import java.util.Set;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.GeradorServiceWS;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.TituloRetorno;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusEnum;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;

@Service
public class ExecucaoAutomaticaCertificadora {

	private static final Logger logger = LoggerFactory.getLogger(ExecucaoAutomaticaCertificadora.class);
	private static final String CONFIRMADO_SUCESSO = "00";
	

	private final IConfGlobalService confGlobalService;
	private final IFundoService fundoService;
	private final IBaseService baseService;
	private final GeradorServiceWS geradorServiceWS;

	@Inject
	public ExecucaoAutomaticaCertificadora(
			final IConfGlobalService confGlobalService,
			final GeradorServiceWS geradorServiceWS,
			final IFundoService fundoService,
			final IBaseService baseService) {
		this.confGlobalService = confGlobalService;
		this.geradorServiceWS = geradorServiceWS;
		this.fundoService = fundoService;
		this.baseService = baseService;
	}

	@Scheduled(fixedDelay = 3000)
	public void enviarRetornoCertificadoraPortalServicos() {
		try {

			ConfGlobal confGlobal = confGlobalService.getConfGlobal();


			Integer idBasePadrao = confGlobal.getIdBasePadrao();

			if (idBasePadrao == null || idBasePadrao.equals(0)) {
				return;
			}

			if (confGlobal.getUrlPortal() == null || confGlobal.getUrlPortalServicos().equals("")) {
				logger.warn("Url portal não cadastrado");
				return;
			}

			if (confGlobal.getUsuarioPortal() == null || confGlobal.getUsuarioPortalServicos().equals("")) {
				logger.warn("Usuario portal não cadastrado");
				return;
			}

			if (confGlobal.getSenhaPortal() == null || confGlobal.getSenhaPortalServicos().equals("")) {
				logger.warn("Senha portal não cadastrado");
				return;
			}

			if (!BooleanUtils.toBooleanDefaultIfNull(confGlobal.getEnviarRetornoAutomatico(), false)) {
				return;
			}

			GuardadorRequisicoesCertificadora guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
			Set<RequisicaoCertificadoraDigitalWrapper> requisicoes = guardadorRequisicao.pegaRequisicoesNaoEnviado();
			
			if(requisicoes == null || requisicoes.isEmpty()) {
				return;
			}
			
			RetornoCertificacaoDigital retorno = geradorServiceWS.criaRequestRetornoCertificadora();
			BindingProvider bp  = (BindingProvider) retorno;
			
			for (RequisicaoCertificadoraDigitalWrapper requisicaoWrapper : requisicoes) {
				if (StatusEnum.NAO_ENVIADO.equals(requisicaoWrapper.getStatusRequisicao().getStatus())) {
					logger.info("Encontrado Operacao: {} para Enviar Retorno.", requisicaoWrapper.getRequisicao().getId());
					RetornoCertificadoDigital retornoCertificadoDigital = new RetornoCertificadoDigital();
					BeanUtils.copyProperties(requisicaoWrapper.getRequisicao(), retornoCertificadoDigital);
					retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());

					logger.info("Inicio do Envio do retorno a certificadora.");
		
					try {
						requisicaoWrapper.getRequisicao().getCedente().criaCamposFaltando();
						retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());
						
						if(confGlobal.getEnviarPk7s()) {
							Base base = baseService.findById(idBasePadrao);
							int seqPkc7s = 1;
							
							FundoDto fundo = fundoService.findFundoByCnpj(base, retornoCertificadoDigital.getFundo().getCnpjFundo());
							
							if(fundo != null) {
								File caminho = new File(confGlobalService.getPathBancoDeDados(base) + File.separator + fundo.getCodigoIsin() + File.separator + "CERTIFICADORAS");
								
								for(TituloRetorno t : retornoCertificadoDigital.getCedente().getTitulos().getTitulo()) {
	
									String nomeArqP7s = "retornoCert_"+ (seqPkc7s++) +".p7s";
									
									if (!caminho.exists()) {
										caminho.mkdirs();
									}
									
									File file = new File(caminho, nomeArqP7s);
									
									logger.info("Salvando arquivo p7s. Caminho: {} Arquivo: {}",caminho,nomeArqP7s);
									
									PrintWriter writer = new PrintWriter(file, "UTF-8");
									writer.println("Teste fluxo p7s: " + nomeArqP7s);
									writer.close();
									
									t.setNomePkcs7(nomeArqP7s);
									
								}
							}
						}
						try {
							
//							JAXBContext jaxbContext = JAXBContext.newInstance(retornoCertificadoDigital.getClass());
//							Marshaller marshaller = jaxbContext.createMarshaller();
//							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//							marshaller.marshal(retornoCertificadoDigital, System.out);

						} catch (Exception e) {
						}

						
						RetornoProcessamento retornoProcessamento = retorno.retorno(retornoCertificadoDigital);
						logger.info("Fim do Envio do retorno a certificadora. Status de Retorno: {}", retornoProcessamento.getStatus());
						if(retornoProcessamento.getErros() != null && retornoProcessamento.getErros().getErro() != null && !retornoProcessamento.getErros().getErro().isEmpty() ) {
							
							retornoProcessamento.getErros().getErro().forEach(e -> 
							logger.info("Erro retorno da certificadora. Msg erro: {}", e.getMensagem())
									);
						}
						if (retornoProcessamento != null && retornoProcessamento.getStatus().equals(CONFIRMADO_SUCESSO)) {
							guardadorRequisicao.atualizaRequisicao(requisicaoWrapper, StatusRequisicao.comSucesso());
						} else {
							guardadorRequisicao.atualizaRequisicao(requisicaoWrapper,
									StatusRequisicao.comErro(
											new StringBuilder().append("Msg Retorno: ").append(retornoProcessamento.geraMensagemDoErro())
													.append(" Status: ").append(retornoProcessamento.getStatus()).toString()));
						}
						logger.info("Fim do Envio Retorno.");
						
					} catch (Exception e) {
						logger.error("Erro ao criar o Request do Retorno: {}", e);
						
					}
					
		
				}
			}
			

//			logger.info("Fim do envio do Retorno Certificadora automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}

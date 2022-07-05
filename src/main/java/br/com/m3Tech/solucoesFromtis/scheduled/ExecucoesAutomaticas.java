package br.com.m3Tech.solucoesFromtis.scheduled;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusEnum;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;
import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IOperacaoRecebivelService;
import br.com.m3Tech.solucoesFromtis.service.impl.AprovarConsultoriaPortalServicos;
import br.com.m3Tech.solucoesFromtis.service.impl.AprovarGestorPortalServicos;

@Service
public class ExecucoesAutomaticas {

	private static final Logger logger = LoggerFactory.getLogger(ExecucoesAutomaticas.class);

	private static final String CONFIRMADO_SUCESSO = "00";

	private final IOperacaoRecebivelService operacaoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IBaseService baseService;
	private final EnviadorRetornoCertificadora enviadorRetorno;

	@Inject
	public ExecucoesAutomaticas(final IOperacaoRecebivelService operacaoRecebivelService,
			final IConfGlobalService confGlobalService, final IBaseService baseService,
			final EnviadorRetornoCertificadora enviadorRetorno) {
		this.operacaoRecebivelService = operacaoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.baseService = baseService;
		this.enviadorRetorno = enviadorRetorno;
	}

	@Scheduled(fixedRate = 20000)
	public void aprovarConsultoriaPortalServicos() {
		try {
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();

			Integer idBasePadrao = confGlobal.getIdBasePadrao();

			if (idBasePadrao == null || idBasePadrao.equals(0)) {
				return;
			}

			if (confGlobal.getUrlPortalServicos() == null || confGlobal.getUrlPortalServicos().equals("")) {
				logger.warn("Url portal serviços não cadastrado");
				return;
			}

			if (confGlobal.getUsuarioPortalServicos() == null || confGlobal.getUsuarioPortalServicos().equals("")) {
				logger.warn("Usuario portal serviços não cadastrado");
				return;
			}

			if (confGlobal.getSenhaPortalServicos() == null || confGlobal.getSenhaPortalServicos().equals("")) {
				logger.warn("Senha portal serviços não cadastrado");
				return;
			}

			if (!BooleanUtils.toBooleanDefaultIfNull(confGlobal.getAprovarConsultoriaAutomatico(), false)) {
				return;
			}

			Base base = baseService.findById(idBasePadrao);

			Connection con = Conexao.getConnection(base);

			List<DadosOperacaoParaAprovacaoDto> allOperacoesAguardandoConsultoria = operacaoRecebivelService
					.findAllOperacoesAguardandoAprovacao(con, "PO");

			if (allOperacoesAguardandoConsultoria.isEmpty()) {
				return;
			}

			logger.info("Iniciando aprovações da consultoria automático");

			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(confGlobal.getUrlPortalServicos(),
					confGlobal.getUsuarioPortalServicos(), confGlobal.getSenhaPortalServicos(), null, 0);

			AprovarConsultoriaPortalServicos service = new AprovarConsultoriaPortalServicos();

			allOperacoesAguardandoConsultoria.forEach(o -> {

				String msg = service.executar(param, o);

				logger.info(msg);

			});

			logger.info("Fim das aprovações da consultoria automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Scheduled(fixedRate = 20000)
	public void aprovarGestorPortalServicos() {
		try {
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();

			Integer idBasePadrao = confGlobal.getIdBasePadrao();

			if (idBasePadrao == null || idBasePadrao.equals(0)) {
				return;
			}

			if (confGlobal.getUrlPortalServicos() == null || confGlobal.getUrlPortalServicos().equals("")) {
				logger.warn("Url portal serviços não cadastrado");
				return;
			}

			if (confGlobal.getUsuarioPortalServicos() == null || confGlobal.getUsuarioPortalServicos().equals("")) {
				logger.warn("Usuario portal serviços não cadastrado");
				return;
			}

			if (confGlobal.getSenhaPortalServicos() == null || confGlobal.getSenhaPortalServicos().equals("")) {
				logger.warn("Senha portal serviços não cadastrado");
				return;
			}

			if (!BooleanUtils.toBooleanDefaultIfNull(confGlobal.getAprovarGestorAutomatico(), false)) {
				return;
			}

			Base base = baseService.findById(idBasePadrao);

			Connection con = Conexao.getConnection(base);

			List<DadosOperacaoParaAprovacaoDto> allOperacoesAguardandoGestor = operacaoRecebivelService
					.findAllOperacoesAguardandoAprovacao(con, "PG");

			if (allOperacoesAguardandoGestor.isEmpty()) {
				return;
			}

			logger.info("Iniciando aprovações do Gestor automático");

			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(confGlobal.getUrlPortalServicos(),
					confGlobal.getUsuarioPortalServicos(), confGlobal.getSenhaPortalServicos(), null, 0);

			AprovarGestorPortalServicos service = new AprovarGestorPortalServicos();

			allOperacoesAguardandoGestor.forEach(o -> {

				String msg = service.executar(param, o);

				logger.info(msg);

			});

			logger.info("Fim das aprovações do Gestor automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Scheduled(fixedRate = 20000)
	public void enviarRetornoCertificadoraPortalServicos() {
		try {
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();

			Integer idBasePadrao = confGlobal.getIdBasePadrao();
			
			GuardadorRequisicoesCertificadora guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();

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

			Set<RequisicaoCertificadoraDigitalWrapper> requisicoes = guardadorRequisicao.pegaRequisicoesNaoEnviado();
			
			if(requisicoes.isEmpty()) {
				return;
			}
			
			for (RequisicaoCertificadoraDigitalWrapper requisicaoWrapper : requisicoes) {
				if (StatusEnum.NAO_ENVIADO.equals(requisicaoWrapper.getStatusRequisicao().getStatus())) {
					logger.info("Encontrado Operacao: {} para Enviar Retorno.", requisicaoWrapper.getRequisicao().getId());
					RetornoCertificadoDigital retornoCertificadoDigital = new RetornoCertificadoDigital();
					BeanUtils.copyProperties(requisicaoWrapper.getRequisicao(), retornoCertificadoDigital);
					retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());

					logger.info("Inicio do Envio do retorno a certificadora.");
					RetornoProcessamento retorno = enviadorRetorno
							.enviarRetornoCertificadora(requisicaoWrapper.getRequisicao());
					logger.info("Fim do Envio do retorno a certificadora. Status de Retorno: {}", retorno.getStatus());
					if (retorno != null && retorno.getStatus().equals(CONFIRMADO_SUCESSO)) {
						guardadorRequisicao.atualizaRequisicao(requisicaoWrapper, StatusRequisicao.comSucesso());
					} else {
						guardadorRequisicao.atualizaRequisicao(requisicaoWrapper,
								StatusRequisicao.comErro(
										new StringBuilder().append("Msg Retorno: ").append(retorno.geraMensagemDoErro())
												.append(" Status: ").append(retorno.getStatus()).toString()));
					}
				}
			}

			logger.info("Fim do envio do Retorno Certificadora automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}

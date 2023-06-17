package br.com.m3Tech.solucoesFromtis.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.JaxbUtils;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.scheduled.ExecucaoAutomaticaConsultoria;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class CertificadoraController {

	private static final Logger logger = LoggerFactory.getLogger(CertificadoraController.class);
	private static final String OUT = "certificadora";
	private static final String CONFIRMADO_SUCESSO = "00";

	private GuardadorRequisicoesCertificadora guardadorRequisicao;

	private List<RequisicaoCertificadoraDigitalWrapper> requisicoes;

	private final EnviadorRetornoCertificadora enviadorRetorno;
	private final IConfGlobalService confGlobalService;

	@Inject
	public CertificadoraController(final EnviadorRetornoCertificadora enviadorRetorno,
			final IConfGlobalService confGlobalService) {
		this.enviadorRetorno = enviadorRetorno;
		this.confGlobalService = confGlobalService;
	}

	public String init() {
		guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
		atualizaRequisicoes();
		return OUT;
	}

	public void removerTudo() {
		if (guardadorRequisicao == null) {
			guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
		}
		this.guardadorRequisicao.removerTudo();
		atualizaRequisicoes();
	}

	public void atualizaRequisicoes() {

		if (guardadorRequisicao == null) {
			guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
		}

		requisicoes = Lists.newArrayList(guardadorRequisicao.pegaRequisicoes());
	}

	public void enviarRetorno(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {

		try {
			ConfGlobal confGlobal = confGlobalService.getConfGlobal();

			Integer idBasePadrao = confGlobal.getIdBasePadrao();

			if (idBasePadrao == null || idBasePadrao.equals(0)) {
				return;
			}

			if (confGlobal.getUrlPortal() == null || confGlobal.getUrlPortalServicos().equals("")) {
				logger.warn("Url portal não cadastrado");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Url portal não cadastrado."));
				return;
			}

			if (confGlobal.getUsuarioPortal() == null || confGlobal.getUsuarioPortalServicos().equals("")) {
				logger.warn("Usuario portal não cadastrado");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Usuario portal não cadastrado."));
				return;
			}

			if (confGlobal.getSenhaPortal() == null || confGlobal.getSenhaPortalServicos().equals("")) {
				logger.warn("Senha portal não cadastrado");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Senha portal não cadastrado."));
				return;
			}

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
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Retorno enviado com sucesso."));
			} else {
				guardadorRequisicao.atualizaRequisicao(requisicaoWrapper,
						StatusRequisicao.comErro(
								new StringBuilder().append("Msg Retorno: ").append(retorno.geraMensagemDoErro())
										.append(" Status: ").append(retorno.getStatus()).toString()));
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "", retorno.geraMensagemDoErro()));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
		}

	}

	public void removeAquisicao(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
		guardadorRequisicao.removeRequisica(requisicaoWrapper);
		atualizaRequisicoes();
	}

	public void download(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
		final String xml = JaxbUtils.jaxbObjectToXML(requisicaoWrapper.getRequisicao());
		try {
			ControllerUtils.downloadAsAttachment(
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + "_"
							+ requisicaoWrapper.getRequisicao().getId() + ".xml",
					new ByteArrayInputStream(xml.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
		}
	}

}

package br.com.m3Tech.solucoesFromtis.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosAzumi;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;
import br.com.m3Tech.solucoesFromtis.pagamentos.sinacor.GuardadorPagamentosAzumiImpl;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class IntegracaoPagamentoAzumiController {

	private static final Logger logger = LoggerFactory.getLogger(IntegracaoPagamentoAzumiController.class);
	private static final String OUT = "integracaoPagamentoAzumi";

	private GuardadorPagamentosAzumi guardadorPagamentosAzumi;

	private List<OperacaoAzumi> operacoes;

	private final IConfGlobalService confGlobalService;

	@Inject
	public IntegracaoPagamentoAzumiController( final IConfGlobalService confGlobalService) {
		this.confGlobalService = confGlobalService;
	}

	public String init() {
		guardadorPagamentosAzumi = new GuardadorPagamentosAzumiImpl();
		atualizaOperacoes();
		return OUT;
	}

	public void removerTudo() {
		if (guardadorPagamentosAzumi == null) {
			guardadorPagamentosAzumi = new GuardadorPagamentosAzumiImpl();
		}
		this.guardadorPagamentosAzumi.removerTudo();
		atualizaOperacoes();
	}

	public void atualizaOperacoes() {

		if (guardadorPagamentosAzumi == null) {
			guardadorPagamentosAzumi = new GuardadorPagamentosAzumiImpl();
		}

		operacoes = Lists.newArrayList(guardadorPagamentosAzumi.pegaOperacoes());
	}



	public void removeOperacao(OperacaoAzumi operacao) {
		guardadorPagamentosAzumi.removeOperacao(operacao);
		atualizaOperacoes();
	}

	public void download(OperacaoAzumi operacao) {
		final String json = new Gson().toJson(operacao);
		try {
			ControllerUtils.downloadAsAttachment(
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + "_"
							+ operacao.getConta()+ operacao.getDocumento() + ".txt",
					new ByteArrayInputStream(json.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
		}
	}

}

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

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosGenial;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoGenial;
import br.com.m3Tech.solucoesFromtis.pagamentos.sinacor.GuardadorPagamentosGenialImpl;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class IntegracaoPagamentoGenialController {

	private static final Logger logger = LoggerFactory.getLogger(IntegracaoPagamentoGenialController.class);
	private static final String OUT = "integracaoPagamentoGenial";

	private GuardadorPagamentosGenial guardadorPagamentosGenial;

	private List<OperacaoGenial> operacoes;

	private final IConfGlobalService confGlobalService;

	@Inject
	public IntegracaoPagamentoGenialController( final IConfGlobalService confGlobalService) {
		this.confGlobalService = confGlobalService;
	}

	public String init() {
		guardadorPagamentosGenial = new GuardadorPagamentosGenialImpl();
		atualizaOperacoes();
		return OUT;
	}

	public void removerTudo() {
		if (guardadorPagamentosGenial == null) {
			guardadorPagamentosGenial = new GuardadorPagamentosGenialImpl();
		}
		this.guardadorPagamentosGenial.removerTudo();
		atualizaOperacoes();
	}

	public void atualizaOperacoes() {

		if (guardadorPagamentosGenial == null) {
			guardadorPagamentosGenial = new GuardadorPagamentosGenialImpl();
		}

		operacoes = Lists.newArrayList(guardadorPagamentosGenial.pegaOperacoes());
	}



	public void removeOperacao(OperacaoGenial operacao) {
		guardadorPagamentosGenial.removeOperacao(operacao);
		atualizaOperacoes();
	}

	public void download(OperacaoGenial operacao) {
		final String json = new Gson().toJson(operacao);
		try {
			ControllerUtils.downloadAsAttachment(
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + "_"
							+ operacao.getName()+ operacao.getCPFCNPJ() + ".txt",
					new ByteArrayInputStream(json.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
		}
	}

}

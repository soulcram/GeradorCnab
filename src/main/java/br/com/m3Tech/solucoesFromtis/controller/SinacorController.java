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

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosSinacor;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.dto.Operacao;
import br.com.m3Tech.solucoesFromtis.pagamentos.sinacor.GuardadorPagamentosSinacorImpl;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class SinacorController {

	private static final Logger logger = LoggerFactory.getLogger(SinacorController.class);
	private static final String OUT = "sinacor";

	private GuardadorPagamentosSinacor guardadorPagamentosSinacor;

	private List<Operacao> operacoes;

	private final EnviadorRetornoCertificadora enviadorRetorno;
	private final IConfGlobalService confGlobalService;

	@Inject
	public SinacorController(final EnviadorRetornoCertificadora enviadorRetorno,
			final IConfGlobalService confGlobalService) {
		this.enviadorRetorno = enviadorRetorno;
		this.confGlobalService = confGlobalService;
	}

	public String init() {
		guardadorPagamentosSinacor = new GuardadorPagamentosSinacorImpl();
		atualizaOperacoes();
		return OUT;
	}

	public void removerTudo() {
		if (guardadorPagamentosSinacor == null) {
			guardadorPagamentosSinacor = new GuardadorPagamentosSinacorImpl();
		}
		this.guardadorPagamentosSinacor.removerTudo();
		atualizaOperacoes();
	}

	public void atualizaOperacoes() {

		if (guardadorPagamentosSinacor == null) {
			guardadorPagamentosSinacor = new GuardadorPagamentosSinacorImpl();
		}

		operacoes = Lists.newArrayList(guardadorPagamentosSinacor.pegaOperacoes());
	}



	public void removeOperacao(Operacao operacao) {
		guardadorPagamentosSinacor.removeOperacao(operacao);
		atualizaOperacoes();
	}

	public void download(Operacao operacao) {
		final String json = new Gson().toJson(operacao);
		try {
			ControllerUtils.downloadAsAttachment(
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + "_"
							+ operacao.getAccount()+ operacao.getReceiver().getName() + ".txt",
					new ByteArrayInputStream(json.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
		}
	}

}

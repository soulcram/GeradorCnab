package br.com.m3Tech.solucoesFromtis.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRetornoCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorIntegracaoMaps;
import br.com.m3Tech.solucoesFromtis.dto.Carteira;
import br.com.m3Tech.solucoesFromtis.integracao.maps.GuardadorIntegracaoMapsImpl;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class MapsController {

	private static final Logger logger = LoggerFactory.getLogger(MapsController.class);
	private static final String OUT = "sinacor";

	private GuardadorIntegracaoMaps guardadorIntegracaoMaps;

	private List<Carteira> carteiras;

	private final EnviadorRetornoCertificadora enviadorRetorno;
	private final IConfGlobalService confGlobalService;

	@Inject
	public MapsController(final EnviadorRetornoCertificadora enviadorRetorno,
			final IConfGlobalService confGlobalService) {
		this.enviadorRetorno = enviadorRetorno;
		this.confGlobalService = confGlobalService;
	}

	public String init() {
		guardadorIntegracaoMaps = new GuardadorIntegracaoMapsImpl();
		atualizaCarteiras();
		return OUT;
	}

	public void removerTudo() {
		if (guardadorIntegracaoMaps == null) {
			guardadorIntegracaoMaps = new GuardadorIntegracaoMapsImpl();
		}
		this.guardadorIntegracaoMaps.removerTudo();
		atualizaCarteiras();
	}

	public void atualizaCarteiras() {

		if (guardadorIntegracaoMaps == null) {
			guardadorIntegracaoMaps = new GuardadorIntegracaoMapsImpl();
		}

		carteiras = Lists.newArrayList(guardadorIntegracaoMaps.pegaCarteiras());
	}



	public void removeCarteira(Carteira operacao) {
		guardadorIntegracaoMaps.removeCarteira(operacao);
		atualizaCarteiras();
	}

//	public void download(Carteira operacao) {
//		final String json = new Gson().toJson(operacao);
//		try {
//			ControllerUtils.downloadAsAttachment(
//					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + "_"
//							+ operacao.getAccount()+ operacao.getReceiver().getName() + ".txt",
//					new ByteArrayInputStream(json.getBytes()));
//		} catch (Exception e) {
//			e.printStackTrace();
//			ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
//		}
//	}

}

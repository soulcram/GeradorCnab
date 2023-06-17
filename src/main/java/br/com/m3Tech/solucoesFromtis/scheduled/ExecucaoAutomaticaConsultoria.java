package br.com.m3Tech.solucoesFromtis.scheduled;

import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.model.ParametrosCadastrosAutomaticos;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IOperacaoRecebivelService;
import br.com.m3Tech.solucoesFromtis.service.impl.AprovarConsultoriaPortalServicos;

@Service
@EnableAsync
public class ExecucaoAutomaticaConsultoria {

	private static final Logger logger = LoggerFactory.getLogger(ExecucaoAutomaticaConsultoria.class);
	

	private final IOperacaoRecebivelService operacaoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IBaseService baseService;

	@Inject
	public ExecucaoAutomaticaConsultoria(final IOperacaoRecebivelService operacaoRecebivelService,
			final IConfGlobalService confGlobalService, final IBaseService baseService) {
		this.operacaoRecebivelService = operacaoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.baseService = baseService;
	}
	@Async
	@Scheduled(fixedDelay = 30000)
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

			List<DadosOperacaoParaAprovacaoDto> allOperacoesAguardandoConsultoria = operacaoRecebivelService
					.findAllOperacoesAguardandoAprovacao(base, "PO");

			if (allOperacoesAguardandoConsultoria.isEmpty()) {
				return;
			}

			logger.info("Iniciando aprovações da consultoria automático");

			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(confGlobal.getUrlPortalServicos(),"",
					confGlobal.getUsuarioPortalServicos(), confGlobal.getSenhaPortalServicos(), null, 0);

			AprovarConsultoriaPortalServicos service = new AprovarConsultoriaPortalServicos();

			service.executar(param, allOperacoesAguardandoConsultoria);

			logger.info("Fim das aprovações da consultoria automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}

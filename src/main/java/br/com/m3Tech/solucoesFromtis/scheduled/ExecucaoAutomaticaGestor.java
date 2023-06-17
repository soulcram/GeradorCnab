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
import br.com.m3Tech.solucoesFromtis.service.impl.AprovarGestorPortalServicos;


@Service
public class ExecucaoAutomaticaGestor {

	private static final Logger logger = LoggerFactory.getLogger(ExecucaoAutomaticaGestor.class);
	

	private final IOperacaoRecebivelService operacaoRecebivelService;
	private final IConfGlobalService confGlobalService;
	private final IBaseService baseService;

	@Inject
	public ExecucaoAutomaticaGestor(final IOperacaoRecebivelService operacaoRecebivelService,
			final IConfGlobalService confGlobalService, final IBaseService baseService) {
		this.operacaoRecebivelService = operacaoRecebivelService;
		this.confGlobalService = confGlobalService;
		this.baseService = baseService;
	}
	

	@Scheduled(fixedDelay = 30000)
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

			List<DadosOperacaoParaAprovacaoDto> allOperacoesAguardandoGestor = operacaoRecebivelService
					.findAllOperacoesAguardandoAprovacao(base, "PG");

			if (allOperacoesAguardandoGestor.isEmpty()) {
				return;
			}

			logger.info("Iniciando aprovações do Gestor automático");

			ParametrosCadastrosAutomaticos param = new ParametrosCadastrosAutomaticos(confGlobal.getUrlPortalServicos(),"",
					confGlobal.getUsuarioPortalServicos(), confGlobal.getSenhaPortalServicos(), null, 0);

			AprovarGestorPortalServicos service = new AprovarGestorPortalServicos();

			service.executar(param, allOperacoesAguardandoGestor);

			logger.info("Fim das aprovações do Gestor automático");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	

}

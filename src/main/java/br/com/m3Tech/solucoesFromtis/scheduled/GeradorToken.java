package br.com.m3Tech.solucoesFromtis.scheduled;

import java.time.LocalTime;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.pagamentos.sinacor.TokenService;

@Service
public class GeradorToken {

	private static final Logger logger = LoggerFactory.getLogger(GeradorToken.class);
	
	@Inject
	private final TokenService tokenService;
	
	public GeradorToken(final TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Scheduled(fixedDelay = 1000)
	public void gerarNovoToken() {
		try {
		
			tokenService.gerarToken(LocalTime.now());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}

package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import br.com.m3Tech.solucoesFromtis.dto.Token;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.impl.ConfGlobalServiceImpl;

public class TokenServiceTest {
	

	
	@Test
	public void deveGerarTokenComTempoExpiracaoEm300() {
		
		ConfGlobal confGlobal = new ConfGlobal();
		confGlobal.setMinExpiracaoToken(5);
		
		IConfGlobalService confGlobalService = mock(ConfGlobalServiceImpl.class);

		when(confGlobalService.getConfGlobal()).thenReturn(confGlobal);
		
		TokenService tokenService = new TokenService(confGlobalService);
		
		Token token = tokenService.getToken();

		assertEquals("300", token.getExpires_in());
	}
	
	@Test
	public void deveGerarTokenComTempoExpiracaoEm60QuandoNaoForInformadoTempoDeExpiracao() {
		
		ConfGlobal confGlobal = new ConfGlobal();
		
		IConfGlobalService confGlobalService = mock(ConfGlobalServiceImpl.class);

		when(confGlobalService.getConfGlobal()).thenReturn(confGlobal);
		
		TokenService tokenService = new TokenService(confGlobalService);
		
		Token token = tokenService.getToken();

		assertEquals("60", token.getExpires_in());
	}
	
	@Test
	public void deveGerarNovoTokenQuantoTempoEstiverExpirado() {
		
		ConfGlobal confGlobal = new ConfGlobal();
		confGlobal.setMinExpiracaoToken(5);
		
		IConfGlobalService confGlobalService = mock(ConfGlobalServiceImpl.class);

		when(confGlobalService.getConfGlobal()).thenReturn(confGlobal);
		
		TokenService tokenService = new TokenService(confGlobalService);
		tokenService.setHoraGeracao(LocalTime.of(12, 0, 0));
		
		Token tokenAnterior = tokenService.getToken();
		
		tokenService.gerarToken(LocalTime.of(12, 5, 0));
		
		Token tokenAtual = tokenService.getToken();

		assertFalse(tokenAnterior.getAccess_token().equals(tokenAtual.getAccess_token()));
	}
	
	@Test
	public void naoDeveGerarNovoTokenQuandoTempoNaoEstiverExpirado() {
		
		ConfGlobal confGlobal = new ConfGlobal();
		
		IConfGlobalService confGlobalService = mock(ConfGlobalServiceImpl.class);

		when(confGlobalService.getConfGlobal()).thenReturn(confGlobal);
		
		TokenService tokenService = new TokenService(confGlobalService);
		tokenService.setHoraGeracao(LocalTime.of(12, 0, 0));
		
		Token tokenAnterior = tokenService.getToken();
		
		tokenService.gerarToken(LocalTime.of(12, 0, 59));
		
		Token tokenAtual = tokenService.getToken();

		assertTrue(tokenAnterior.getAccess_token().equals(tokenAtual.getAccess_token()));
	}
}

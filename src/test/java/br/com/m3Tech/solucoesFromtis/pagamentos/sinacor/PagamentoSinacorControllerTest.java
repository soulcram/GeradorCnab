package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.m3Tech.solucoesFromtis.FromtisSolucoesApplication;
import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosSinacor;
import br.com.m3Tech.solucoesFromtis.dto.Token;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import junit.framework.TestSuite;

@SpringBootTest(classes = FromtisSolucoesApplication.class)
@ContextConfiguration(classes = PagamentoSinacorController.class)
@AutoConfigureMockMvc
public class PagamentoSinacorControllerTest extends TestSuite {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IConfGlobalService confGlobalService;
	
	@MockBean
	private TokenService tokenService;
	
	@MockBean
	private GuardadorPagamentosSinacor guardadorPagamentosSinacor;
	

	@Test
	public void deveObterToken() throws Exception {
		
//		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		
		// given
		ConfGlobal confGlobal = mock(ConfGlobal.class);

		when(confGlobal.getUsuarioSinacor()).thenReturn("fromtis");
		when(confGlobal.getSenhaSinacor()).thenReturn("fromtis!Q@W#E");
		
		given(confGlobalService.getConfGlobal()).willReturn(confGlobal);

		//when
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("grant_type", "password");
		params.add("username", "fromtis");
		params.add("password", "fromtis!Q@W#E");
		
		mockMvc.perform(post("/auth_sinacor/oauth/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.params(params)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				//then
				.andExpect(status().isOk());

	}
	
	@Test
	public void deveMostrarStatusNaoAutorizadoComUsuarioIvalido() throws Exception {
				
		// given
		ConfGlobal confGlobal = mock(ConfGlobal.class);

		when(confGlobal.getUsuarioSinacor()).thenReturn("invallid");
		when(confGlobal.getSenhaSinacor()).thenReturn("invallid!Q@W#E");
		
		given(confGlobalService.getConfGlobal()).willReturn(confGlobal);

		//when
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("grant_type", "password");
		params.add("username", "fromtis");
		params.add("password", "fromtis!Q@W#E");
		
		mockMvc.perform(post("/auth_sinacor/oauth/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.params(params)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				//then
				.andExpect(status().isUnauthorized());

	}
	
	@Test
	public void deveGerarExceptionQuandoTokenPassadoDiferenteAcessoDisponivel() throws Exception {
		
		// given
		Token token = mock(Token.class);
		when(token.getAccess_token()).thenReturn("SEM_ACESSO");
		
		given(tokenService.getToken()).willReturn(token);
		
		//when 
		mockMvc.perform(post("/api/withdraw/adm/adm-fund")
				.header("Authorization","Bearer TOKEN"   )
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				//then
				.andExpect(status().isUnauthorized());

	}
	
	@Test
	public void deveRetornarSucessoQuandoTokenSaoIguais() throws Exception {
		
		// given
		Token token = mock(Token.class);
		when(token.getAccess_token()).thenReturn("TOKEN");
		
		given(tokenService.getToken()).willReturn(token);
		
		//when 
		mockMvc.perform(post("/api/withdraw/adm/adm-fund")
				.header("Authorization","Bearer TOKEN"   )
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				//then
				.andExpect(status().isOk());
		
		Mockito.verify(guardadorPagamentosSinacor, Mockito.times(1)).guardaOperacao(any());
		

	}
	
	

}

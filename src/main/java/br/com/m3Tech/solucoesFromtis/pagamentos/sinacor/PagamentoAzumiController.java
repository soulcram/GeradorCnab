package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosAzumi;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;
import br.com.m3Tech.solucoesFromtis.dto.TransferenciaResponse;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;

@Controller
@RequestMapping
public class PagamentoAzumiController {
	private static final Logger logger = LoggerFactory.getLogger(PagamentoAzumiController.class);
	
	private final TokenService tokenService;
	private final IConfGlobalService confGlobalService;
	private final GuardadorPagamentosAzumi guardadorPagamentoAzumi;
	
	public PagamentoAzumiController(final TokenService tokenService
			, final IConfGlobalService confGlobalService,
			final GuardadorPagamentosAzumi guardadorPagamentoAzumi) {
		this.tokenService = tokenService;
		this.confGlobalService = confGlobalService;
		this.guardadorPagamentoAzumi = guardadorPagamentoAzumi;
	}


	@PostMapping(value = "transferencia/nova-transferencia"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TransferenciaResponse> getReceberPagamento(@RequestParam("api_token") String token,
			@RequestBody String body) {
		
		logger.info("Recebendo pagamento: {}", body);
//		Token tokenAtual = tokenService.getToken();
//		
//		String[] split = token.split(" ");
//		
//		if(!split[1].equals(tokenAtual.getAccess_token())) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//		}
		
		OperacaoAzumi operacao = new Gson().fromJson(body, OperacaoAzumi.class);
		
		guardadorPagamentoAzumi.guardaOperacao(operacao);
		
		return new ResponseEntity<TransferenciaResponse>(new TransferenciaResponse(operacao.getId()), HttpStatus.OK);
	}

	
		
		
}

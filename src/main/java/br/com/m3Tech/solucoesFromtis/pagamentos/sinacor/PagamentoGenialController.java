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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosGenial;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoGenial;
import br.com.m3Tech.solucoesFromtis.dto.ResponseApi;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;

@Controller
@RequestMapping
public class PagamentoGenialController {
	private static final Logger logger = LoggerFactory.getLogger(PagamentoGenialController.class);
	
	private final TokenService tokenService;
	private final IConfGlobalService confGlobalService;
	private final GuardadorPagamentosGenial guardadorPagamentoGenial;
	
	public PagamentoGenialController(final TokenService tokenService
			, final IConfGlobalService confGlobalService,
			final GuardadorPagamentosGenial guardadorPagamentoGenial) {
		this.tokenService = tokenService;
		this.confGlobalService = confGlobalService;
		this.guardadorPagamentoGenial = guardadorPagamentoGenial;
	}


	@PostMapping(value = "api/BPO/v2/SendTransferPost"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getReceberPagamento(@RequestBody String body) {

		logger.info("Recebendo pagamento: {}", body);

		
		OperacaoGenial operacao = new Gson().fromJson(body, OperacaoGenial.class);
		
		guardadorPagamentoGenial.guardaOperacao(operacao);
		
		ResponseApi responseApi = new ResponseApi("Sucesso","0",Long.valueOf(ValorAleatorioUtil.getValorNumerico(5)));
		
		String json = new Gson().toJson(responseApi);
		
		return new ResponseEntity<String>( json, HttpStatus.OK);
	}

	
		
		
}

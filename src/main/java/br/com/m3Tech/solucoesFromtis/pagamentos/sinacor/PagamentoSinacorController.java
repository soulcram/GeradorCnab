package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosSinacor;
import br.com.m3Tech.solucoesFromtis.dto.Operacao;
import br.com.m3Tech.solucoesFromtis.dto.Token;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;

@Controller
@RequestMapping
public class PagamentoSinacorController {
	
	private final TokenService tokenService;
	private final IConfGlobalService confGlobalService;
	private final GuardadorPagamentosSinacor guardadorPagamentoSinacor;
	
	public PagamentoSinacorController(final TokenService tokenService
			, final IConfGlobalService confGlobalService,
			final GuardadorPagamentosSinacor guardadorPagamentoSinacor) {
		this.tokenService = tokenService;
		this.confGlobalService = confGlobalService;
		this.guardadorPagamentoSinacor = guardadorPagamentoSinacor;
	}

	@PostMapping(value = "auth_sinacor/oauth/token", 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ResponseEntity<Token> getToken(@RequestParam MultiValueMap<String,String> paramMap) {
		
		String usuario = "";
		String senha = "";
		
		for(String str : paramMap.keySet()){
			
			if("username".equals(str)) {
				usuario = paramMap.getFirst(str);
			}
			
			if("password".equals(str)) {
				senha = paramMap.getFirst(str);
			}
		}
		
		ConfGlobal confGlobal = confGlobalService.getConfGlobal();
		String usuarioCadastrado = confGlobal.getUsuarioSinacor() + confGlobal.getSenhaSinacor();
		
		if(!usuarioCadastrado.equals(usuario + senha)) {
			return new ResponseEntity<Token>(new Token(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Token>(tokenService.getToken(), HttpStatus.OK);
	}

	@PostMapping(value = "api/withdraw/adm/adm-fund"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public void getReceberPagamento(@RequestHeader("Authorization") String token,
			@RequestBody String body) {
		
		Token tokenAtual = tokenService.getToken();
		
		String[] split = token.split(" ");
		
		if(!split[1].equals(tokenAtual.getAccess_token())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		Operacao operacao = new Gson().fromJson(body, Operacao.class);
		
		guardadorPagamentoSinacor.guardaOperacao(operacao);
	}

}

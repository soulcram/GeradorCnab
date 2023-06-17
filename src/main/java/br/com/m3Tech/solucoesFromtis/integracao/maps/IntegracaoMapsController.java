package br.com.m3Tech.solucoesFromtis.integracao.maps;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorIntegracaoMaps;
import br.com.m3Tech.solucoesFromtis.dto.Carteira;
import br.com.m3Tech.solucoesFromtis.dto.CarteiraSac;
import br.com.m3Tech.solucoesFromtis.dto.PatrimonioLiquido;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;

@Controller
@RequestMapping
public class IntegracaoMapsController {
	
	private final IConfGlobalService confGlobalService;
	private final GuardadorIntegracaoMaps guardadorIntegracaoMaps;
	
	public IntegracaoMapsController(final IConfGlobalService confGlobalService,
			final GuardadorIntegracaoMaps guardadorIntegracaoMaps) {
		this.confGlobalService = confGlobalService;
		this.guardadorIntegracaoMaps = guardadorIntegracaoMaps;
	}

	@GetMapping(value = "maps", 
			produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ResponseEntity<PatrimonioLiquido>  getPl(@RequestParam String carteira, @RequestParam String data) {

		BigDecimal valorPl = confGlobalService.getConfGlobal().getValorPl();
		
		PatrimonioLiquido pl = new PatrimonioLiquido(valorPl);
	
		guardadorIntegracaoMaps.guardaCarteira(new Carteira(carteira, LocalDate.parse(data.substring(0, 10)), valorPl));
		
		return new ResponseEntity<PatrimonioLiquido>( pl, HttpStatus.OK);
		
	}

}

package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class SequencialController {
		
	private Integer numseq = 1;
	
	public SequencialController() {
	}

	@GetMapping(value = "controle_sequencial" )
	public @ResponseBody ResponseEntity<Integer> getSequencial() {
		
		numseq++;
		
		return new ResponseEntity<Integer>(numseq, HttpStatus.OK);
	}


}

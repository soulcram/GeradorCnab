package br.com.m3Tech.solucoesFromtis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistradoraController {

	static Logger log = LoggerFactory.getLogger(RegistradoraController.class);
	
	@GetMapping(value = "/aceiteIncondicional/{id}", produces = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String get(@PathVariable long id) {
		return "Funcionou " + id;
	}
	
}

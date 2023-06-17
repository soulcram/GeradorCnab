
package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class GerarDocumentosController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean formatado;
	
	private String cpf;
	private String cnpj;
	private String rg;
	
	private GeradorCpfCnpjRgFake geradorCpfCnpjRgFake;
	


	@PostConstruct
	public void init() {
		geradorCpfCnpjRgFake = new GeradorCpfCnpjRgFake();
		clear();
	}
	
	private void clear() {
		cpf = "";
		cnpj = "";
		rg = "";
		formatado = false;
	}


	public void gerarCnpj() {
		this.cnpj = geradorCpfCnpjRgFake.cnpj(formatado);
	}
	
	public void gerarCpf() {
		this.cpf = geradorCpfCnpjRgFake.cpf(formatado);
	}



}

package br.com.m3Tech.solucoesFromtis.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;

public class GeradorCpfCnpjRgFakeTest {
	
	@Test
	void deveGerarCnpjValido() {
		GeradorCpfCnpjRgFake gerador = new GeradorCpfCnpjRgFake();
		
		for(int i = 0 ; i< 100000; i++) {
			
			String cnpj = gerador.cnpj(true);
			
			assertTrue(gerador.isCNPJ(CpfCnpjUtils.removerFormatacao(cnpj)));
		}
		
	}

}

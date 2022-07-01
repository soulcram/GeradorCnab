package br.com.m3Tech.solucoesFromtis.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CpfCnpjUtilsTest {
	
	@Test
    void removerFormatacaoCnpj(){
		
		String cnpj = "91.386.143/0001-39";
		
		String cnpjSemFormatacao = CpfCnpjUtils.removerFormatacao(cnpj);
    	
		Assertions.assertEquals("91386143000139", cnpjSemFormatacao);
    }

}

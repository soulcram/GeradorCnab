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
	
	@Test
    void inserirFormatacaoCnpjJaComFormatacao(){
		
		String cnpj = "91.386.143/0001-39";
		
		String cnpjSemFormatacao = CpfCnpjUtils.inserirFormatacao(cnpj);
    	
		Assertions.assertEquals("91.386.143/0001-39", cnpjSemFormatacao);
    }
	
	@Test
    void inserirFormatacaoCnpjSemFormatacao(){
		
		String cnpj = "91386143000139";
		
		String cnpjSemFormatacao = CpfCnpjUtils.inserirFormatacao(cnpj);
    	
		Assertions.assertEquals("91.386.143/0001-39", cnpjSemFormatacao);
    }
	
	@Test
    void inserirFormatacaoCpfJaComFormatacao(){
		
		String cpf = "314.112.468-02";
		
		String cpfSemFormatacao = CpfCnpjUtils.inserirFormatacao(cpf);
    	
		Assertions.assertEquals("314.112.468-02", cpfSemFormatacao);
    }
	
	@Test
    void inserirFormatacaoCpfSemFormatacao(){
		
		String cpf = "31411246802";
		
		String cpfSemFormatacao = CpfCnpjUtils.inserirFormatacao(cpf);
    	
		Assertions.assertEquals("314.112.468-02", cpfSemFormatacao);
    }
	
	@Test
    void inserirFormatacaoComStringInválida(){
		
		String stringInvalida = "31411246802000A";
		
		String stringInvalidaRetorno = CpfCnpjUtils.inserirFormatacao(stringInvalida);
    	
		Assertions.assertEquals("31411246802000A", stringInvalidaRetorno);
    }

}

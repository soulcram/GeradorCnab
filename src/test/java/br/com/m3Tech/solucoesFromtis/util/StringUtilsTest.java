package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {
	
	@Test
    void testStringEmptyOrNull(){
		
		Assertions.assertEquals(StringUtils.emptyOrNull(null), true);
		Assertions.assertEquals(StringUtils.emptyOrNull(""), true);
		Assertions.assertEquals(StringUtils.emptyOrNull(" "), true);
		Assertions.assertEquals(StringUtils.emptyOrNull("A"), false);
    }
	
	@Test
    void testStringLimite(){
		
    	
		Assertions.assertEquals(StringUtils.limite("Marcos", 0), "");
		Assertions.assertEquals(StringUtils.limite("Marcos", null), "");
		Assertions.assertEquals(StringUtils.limite(null, 1), "");
		Assertions.assertEquals(StringUtils.limite("Marcos", 3), "Mar");
		Assertions.assertEquals(StringUtils.limite("Marcos", 10), "Marcos");
    }
	
	@Test
    void testConcatIfExists(){
		
    	
		Assertions.assertEquals(StringUtils.concatIfExists("Marcos", ""), "Marcos");
		Assertions.assertEquals(StringUtils.concatIfExists("Marcos", null), "Marcos");
		Assertions.assertEquals(StringUtils.concatIfExists(null, ""), "");
		Assertions.assertEquals(StringUtils.concatIfExists("Marcos", " "), "Marcos");
		Assertions.assertEquals(StringUtils.concatIfExists(null, "Marcos"), "Marcos");
		Assertions.assertEquals(StringUtils.concatIfExists("Marcos", "01"), "Marcos01");
    }
	
	@Test
    void testStringTamFinal(){
		
    	
		Assertions.assertEquals(StringUtils.tamFinal("Marcos", 0), "");
		Assertions.assertEquals(StringUtils.tamFinal("Marcos", null), "");
		Assertions.assertEquals(StringUtils.tamFinal(null, 1), "");
		Assertions.assertEquals(StringUtils.tamFinal("Marcos", 3), "Mar");
		Assertions.assertEquals(StringUtils.tamFinal("Marcos", 10), "Marcos    ");
		Assertions.assertEquals(StringUtils.tamFinal("Marcos", 6), "Marcos");
    }
	
	@Test
    void testStringRemoverAcentos(){
		
    	
		Assertions.assertEquals(StringUtils.removerAcentos("São Paulo"), "Sao Paulo");
		Assertions.assertEquals(StringUtils.removerAcentos("Transferência"), "Transferencia");
		Assertions.assertEquals(StringUtils.removerAcentos(null), "");
		Assertions.assertEquals(StringUtils.removerAcentos("Marcos"), "Marcos");
		Assertions.assertEquals(StringUtils.removerAcentos("Pacífico"), "Pacifico");
		Assertions.assertEquals(StringUtils.removerAcentos("Marcos"), "Marcos");
    }
	
	@Test
    void testStringRemoverPontoEVirgula(){
		
		
		Assertions.assertEquals(StringUtils.removerNaoNumeros(";São1 Paulo"), "1");
		Assertions.assertEquals(StringUtils.removerNaoNumeros("10Transferencia;"), "10");
		Assertions.assertEquals(StringUtils.removerNaoNumeros(null), "");
		Assertions.assertEquals(StringUtils.removerNaoNumeros("M2ar;c3os"), "23");
		Assertions.assertEquals(StringUtils.removerNaoNumeros("Mar;cos44"), "44");
		
    }
	
	@Test
    void testNumeroComZerosAEsquerda(){
		
		
		Assertions.assertEquals(StringUtils.getNumeroComZerosAEsquerda(10,1), "10");
		Assertions.assertEquals(StringUtils.getNumeroComZerosAEsquerda(null, 5), "00000");
		Assertions.assertEquals(StringUtils.getNumeroComZerosAEsquerda(5,null), "5");
		Assertions.assertEquals(StringUtils.getNumeroComZerosAEsquerda(10,10), "0000000010");
		Assertions.assertEquals(StringUtils.getNumeroComZerosAEsquerda(5,3), "005");
		
    }
	
	@Test
    void testIsNotEmpty(){
				
		Assertions.assertEquals(StringUtils.isNotEmpty(""), false);
		Assertions.assertEquals(StringUtils.isNotEmpty(" "), false);
		Assertions.assertEquals(StringUtils.isNotEmpty(null), false);
		Assertions.assertEquals(StringUtils.isNotEmpty(" A "), true);
		
    }
	
	@Test
    void testGetFormatoMoeda(){
			
		Assertions.assertEquals(StringUtils.getFormatoMoeda(new BigDecimal("10.00")), "R$ 10,00");
		
    }
	
	@Test
    void testIsNumeric(){
			
		Assertions.assertEquals(StringUtils.isNumeric("10800"), true);
		Assertions.assertEquals(StringUtils.isNumeric("10A00"), false);
		
    }

	@Test
    void testDefaultIfNull(){
			
		Assertions.assertEquals(StringUtils.defaultIfNull(10,"8"), "10");
		Assertions.assertEquals(StringUtils.defaultIfNull(null, "1.000"), "1.000");
		
    }
	
	@Test
    void testDeveRetornarNullSeFileForNull(){
			
		Assertions.assertEquals(null, StringUtils.deFileParaString(null));
		
    }
	
	@Test
    void testDeveRetornarTextoSeFileNaoForNull() throws URISyntaxException{
			
		URL url = StringUtilsTest.class.getResource("/files/teste_1.txt");
		File file = new File(url.toURI());
		
		Assertions.assertEquals("Hello Word", StringUtils.deFileParaString(file));
		
    }
}

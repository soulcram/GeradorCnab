package br.com.m3Tech.solucoesFromtis.util;

import java.math.BigDecimal;
import java.util.Random;

public class ValorAleatorioUtil {

	private static final String SEQUENCIA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private ValorAleatorioUtil() {
	}

	public static String getValor(Integer qtde) {
		String retorno = "";

		for (int i = 0; i < qtde; i++) {
			Random gerador = new Random();
			int pos = gerador.nextInt(35);
			retorno += SEQUENCIA.substring(pos, pos + 1);
		}

		return retorno;
	}

	public static Integer getValorNumerico(Integer qtde) {

		Random gerador = new Random();
		int pos = gerador.nextInt(qtde);

		return pos;
	}

	public static BigDecimal getValorDecimal(Integer min, Integer max) {
		
		if(min == null || min < 10) {
			min = 10;
		}
		
		if(max == null ) {
			max = 1000;
		}
		
		Random gerador = new Random();
		
		Double nextDouble = gerador.nextDouble();
		
		Double m = (nextDouble * (getValorNumerico(max))) + min; 
		
		
		BigDecimal retorno = new BigDecimal(m);
		
		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
		return retorno;
	}
	
	public static BigDecimal getTaxaDecimal() {
		
		Random gerador = new Random();
		
		Double nextDouble = gerador.nextDouble();		
		
		BigDecimal retorno = new BigDecimal(nextDouble);
		
		retorno = retorno.setScale(4, BigDecimal.ROUND_HALF_EVEN);
				
		return retorno;
	}

}

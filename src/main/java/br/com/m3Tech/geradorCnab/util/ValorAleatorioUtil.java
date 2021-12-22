package br.com.m3Tech.geradorCnab.util;

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

	public static BigDecimal getValorDecimal() {
		Random gerador = new Random();
		
		Double nextDouble = gerador.nextDouble();
		
		Double m = (nextDouble * (getValorNumerico(1000))) + 10; 
		
		
		BigDecimal retorno = new BigDecimal(m);
		
		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
		return retorno;
	}

}

package br.com.m3Tech.solucoesFromtis.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
	
	private BigDecimalUtils() {}
	
	public static boolean isNull(BigDecimal valor) {
		return valor == null;
	}
	
	public static boolean isNullOrZero(BigDecimal valor) {
		return valor == null || valor.compareTo(BigDecimal.ZERO) == 0;
	}
	
	public static BigDecimal ifNullThenZero(BigDecimal valor) {
		
		if(isNull(valor)) {
			return BigDecimal.ZERO;
		}else {
			return valor;
		}
	}

}

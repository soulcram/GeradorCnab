package br.com.m3Tech.solucoesFromtis.util;

import java.util.Random;

public class IntegerUtils {
	
	private IntegerUtils() {};
	
	public static boolean isNotNull(Integer valor) {
		
		return !isNull(valor);
	}
	
	public static boolean isNull(Integer valor) {
		if(valor == null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isZeroOrNull(Integer valor) {
		if(valor == null || valor.equals(0)) {
			return true;
		}
		
		return false;
	}
	
	public static Integer zeroIfNull(Integer valor) {
		if(isNull(valor)) {
			return 0;
		}
		
		return valor;
	}
	
	public static Integer random(Integer valorMin, Integer valorMax) {
		
		Random random = new Random();
		
		int nextInt = random.nextInt(valorMax);
		
		if(nextInt < valorMin) {
			nextInt += valorMin;
		}
		
		return nextInt;
		
	}


}
package br.com.m3Tech.solucoesFromtis.util;

public class BooleanUtils {
	
	private BooleanUtils() {}
	
	public static boolean isNull(Boolean valor) {
		return valor == null;
	}
	
	public static Boolean defaultFalseIfNull(Boolean valor) {
		return valor == null ? false : valor;
	}
	
	public static Boolean defaultTrueIfNull(Boolean valor) {
		return valor == null ? true : valor;
	}
	
	public static Boolean getBooleanOfInteger(Integer valor) {
		return valor == 1 ? true : false;
	}
	
	public static Boolean getBooleanOfString(String valor) {
		
		if("s" .equalsIgnoreCase(valor) ||
		   "true" .equalsIgnoreCase(valor) ||
		   "sim" .equalsIgnoreCase(valor) ||
		   "1" .equalsIgnoreCase(valor) ||
		   "yes" .equalsIgnoreCase(valor) ||
		   "y" .equalsIgnoreCase(valor) ) {
			
			return true;
			
		}else {
			return false;
		}
	}

}

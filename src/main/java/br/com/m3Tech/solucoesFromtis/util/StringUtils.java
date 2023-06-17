package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.Normalizer;
import java.text.NumberFormat;

import org.junit.Ignore;

public class StringUtils {
	
	private StringUtils() {}
	
	public static boolean emptyOrNull(String valor) {
		
		if(valor == null) {
			return true;
		}
		
		return "".equals(valor.trim());
		
	}

	public static String limite(String nome, Integer i) {
		
		if(nome == null || i == null) {
			return "";
		}
		
		if(i == 0) {
			return "";
		}
		
		if(nome.length() > i) {
			return nome.substring(0, i);
		}else {
			return nome;
		}
	}

	public static String tamFinal(String nome, Integer i) {
		
		if(nome == null || i == null) {
			return "";
		}
		
		if(nome.length() > i) {
			return nome.substring(0, i);
		}else if(nome.length() < i) {
			for(int t = nome.length(); t < i; t++ ) {
				nome += " ";
			}
			return nome;
		}else {
			return nome;
		}
	}

	public static String removerAcentos(String value) {

		if (isNull(value)) {
			return "";
		}

		return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String removerNaoNumeros(String value) {
		
		if (isNull(value)) {
			return "";
		}
		
		return value.replaceAll("[^0-9]", "");
	}
	
	
	public static String getNumeroComZerosAEsquerda(Integer valor, Integer qtdeNumLeft) {
		
		if(valor == null) {
			valor = 0;
		}
		
		if (qtdeNumLeft == null) {
			return valor.toString();
		}

		String patern = "%0" + qtdeNumLeft + "d";
				
		return String.format(patern, valor);
	}
	
	@Ignore
	public static String deFileParaString(File arquivo) {
		try {
			return new String(Files.readAllBytes(arquivo.toPath()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static boolean isNotEmpty(String valor) {

		return !isEmpty(valor);
	}

	public static boolean isEmpty(String valor) {
		if (isNull(valor)) {
			return true;
		}

		if (isBlank(valor)) {
			return true;
		}

		return false;
	}

	public static boolean isNull(String valor) {
		if (valor == null) {
			return true;
		}

		return false;
	}

	public static boolean isBlank(String valor) {
		if ("".equals(valor.trim())) {
			return true;
		}

		return false;
	}

	public static String getFormatoMoeda(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(valor);
	}

	public static boolean containsOnly(String s1, String s2) {

		for (char c : s1.toCharArray()) {
			String s = String.valueOf(c);
			if (!s2.contains(s)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNumeric(String s) {
		return containsOnly(s, "0123456789");
	}

	public static String defaultIfNull(Integer valor, String valorDefault) {
		if(valor != null) {
			return valor.toString();
		}
		return valorDefault;
	}

	public static String concatIfExists(String s1, String s2) {
		
		String valor1 = isEmpty(s1) ? "" : s1;
		String valor2 = isEmpty(s2) ? "" : s2;
		
		return valor1 + valor2;
	}

}

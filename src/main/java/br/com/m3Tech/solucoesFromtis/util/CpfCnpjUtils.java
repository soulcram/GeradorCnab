package br.com.m3Tech.solucoesFromtis.util;

public class CpfCnpjUtils {
	
	private CpfCnpjUtils() {}


	public static String removerFormatacao(String doc) {
	
		return doc.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "");
	}

}

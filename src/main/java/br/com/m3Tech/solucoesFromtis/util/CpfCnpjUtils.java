package br.com.m3Tech.solucoesFromtis.util;

public class CpfCnpjUtils {
	
	private CpfCnpjUtils() {}


	public static String removerFormatacao(String doc) {
	
		return doc.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "");
	}
	
	public static String inserirFormatacao(String doc) {
		
		if(doc.contains("-")) {
			return doc;
		}else if(doc.length() == 11) {
			return doc.substring(0, 3)+ "."+ doc.substring(3, 6)+ "."+ doc.substring(6, 9)+ "-"+ doc.substring(9, 11);
		}else if(doc.length() == 14) {
			return doc.substring(0, 2)+ "."+ doc.substring(2, 5)+ "."+ doc.substring(5, 8)+ "/"+ doc.substring(8, 12) + "-" + doc.substring(12, 14);
		}
		return doc;
	}

}

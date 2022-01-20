package br.com.m3Tech.solucoesFromtis.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.base.Charsets;

public class Classe {
	
	public String getConteudo() {
		return conteudo;
	}



	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}



	String nomeClasse;
	String conteudo;
	
//	List<Metodo> metodos;
	
	public Classe(File file){
		try {
			this.nomeClasse = file.getName();
			this.conteudo = getConteudo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
    private String getConteudo(File file) throws IOException {
    	String retorno = "";
    	List<String> readAllLines = Files.readAllLines(Paths.get(file.toURI()), Charsets.UTF_8);
    	
    	for(String s : readAllLines) {
    		
//    		if(s.contains(" class ") ) {
//        		this.nomeClasse = getNomeClasse(s);
//        	}
    		
    		retorno += s + "\n";
    		
    	}
	return retorno;
}



	public String getNomeClasse() {
		return nomeClasse;
	}



	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}



//	public List<Metodo> getMetodos() {
//		return metodos;
//	}
//
//
//
//	public void setMetodos(List<Metodo> metodos) {
//		this.metodos = metodos;
//	}



//	private List<Metodo> getMetodos(File file) throws IOException{
//    	
//        List<String> readAllLines = Files.readAllLines(Paths.get(file.toURI()), Charsets.UTF_8);
//        
//        List<Metodo> metodos = Lists.newArrayList(); 
//        
//        int chaveAberta = 0;
//        
//        String metodo = "";
//        
//        boolean abriu = false;
//        
//        for(String s : readAllLines) {
//        	
//        	if("".equals(s.trim())) {
//        		continue;
//        	}
//        	
//        	
//        	
//        	boolean naoAdicionou = true;
//        	
//        	if(s.contains("{")){
//        		chaveAberta++;
//        	}
//        	
//        	if(s.contains("}")){
//        		chaveAberta--;
//        	}
//        	
//        	if(s.contains(" class ") ) {
//        		this.nomeClasse = getNomeClasse(s);
//        		naoAdicionou = false;
//        	}
//        	
////        	if(s.contains("{") && chaveAberta == 1 && naoAdicionou) {
////        		
////        		metodos.add(new Metodo(s));
////        		
////        		naoAdicionou = false;
////        	}
//        	
//        	if(s.contains("{") && chaveAberta == 2 && naoAdicionou) {
//        		metodo += s + "\n";
//        		naoAdicionou = false;
//        		abriu = true;
//        	}
//        	
//        	if(s.contains("}") && chaveAberta == 1) {
//        		metodo += s + "\n";
//  
//        		metodos.add(new Metodo(metodo));
//        		
//        		metodo = "";
//        		naoAdicionou = false;
//        		abriu = false;
//        	}
//        	
//        	if(s.contains("}") && chaveAberta  > 2 && naoAdicionou) {
//        		metodo += s + "\n";
//        		naoAdicionou = false;
//        	}
//        	
//        	if(chaveAberta > 0 && naoAdicionou  && abriu) {
//        		metodo += s + "\n";
//        		naoAdicionou = false;
//        	}
//        	
//        }
//        
//        return metodos;
//    	
//    }

	private String getNomeClasse(String s) {
		String[] split = s.split(" ");
		
		int index = 0;
		
		for(int i = 0; i < split.length; i++) {
			if("class".equalsIgnoreCase(split[i])) {
				index = i;
			}
		}
		return split[index + 1].replaceAll("\\{", "");
	}

}

package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

public class FileUtil {

	private FileUtil() {
	}
	
	static List<File> retorno = Lists.newArrayList();

	public static List<File> getFiles(String caminho) {

		retorno = Lists.newArrayList();

		addFiles(caminho);
		
		return retorno;
	}
	
	public static List<File> getDiferencaNoSource(List<File> destino, List<File> source) {

		retorno = Lists.newArrayList();

		for(File fSource : source) {
			
			boolean encontrou = false;
			
			for(File fDestino : destino) {
				if(fDestino.getName().equals(fSource.getName())) {
					encontrou = true;
					break;
				}
			}
			
			if(!encontrou) {
				retorno.add(fSource);
			}
			
		}
		
		return retorno;
	}
	
	
	private static void addFiles(String caminho) {
		
		File diretorio = new File(caminho);
			
		

		for (File f : diretorio.listFiles()) {

			if (f.isFile()) {
				
				if (aceitarFile(f.getName())) {
					retorno.add(f);
				}
				
			} else if (f.isDirectory()) {
				if (!ignorarDiretorio(f.getName())) {
					addFiles(f.getPath());
				}

			}

		}
		
	}

	private static boolean aceitarFile(String name) {
		return name.contains(".java") ;
	}

	private static boolean ignorarDiretorio(String name) {
		List<String> pastaParaIgnorar = Lists.newArrayList("target", ".settings", "dist", ".svn");

		return pastaParaIgnorar.contains(name);
	}

}

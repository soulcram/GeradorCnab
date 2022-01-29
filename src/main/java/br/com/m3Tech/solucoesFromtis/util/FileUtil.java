package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

public class FileUtil {

	private FileUtil() {
	}
	
	static List<File> retorno = Lists.newArrayList();

	public static List<File> getFiles(String caminho, String filtro) {

		retorno = Lists.newArrayList();

		addFiles(caminho, filtro);
		
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
	
	
	private static void addFiles(String caminho, String filtro) {
		
		File diretorio = new File(caminho);
			
		

		for (File f : diretorio.listFiles()) {

			if (f.isFile()) {
				
				if (aceitarFile(f.getName(), filtro)) {
					retorno.add(f);
				}
				
			} else if (f.isDirectory()) {
				if (!ignorarDiretorio(f.getName())) {
					addFiles(f.getPath(), filtro);
				}

			}

		}
		
	}

	private static boolean aceitarFile(String name, String filtro) {
		
		List<String> arquivosParaIgnorar = Lists.newArrayList("project", "classpath", "xlsx", "svn","cmd");
		try {
			if(arquivosParaIgnorar.contains(name.split("\\.")[1])) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		
		if("Todos".equalsIgnoreCase(filtro)) {
			return true;
		}
		
		return name.contains(filtro) ;
	}

	private static boolean ignorarDiretorio(String name) {
		List<String> pastaParaIgnorar = Lists.newArrayList("target", ".settings", "dist", ".svn");

		return pastaParaIgnorar.contains(name);
	}

}

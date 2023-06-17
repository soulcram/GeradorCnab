//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.RequestScoped;
//import javax.inject.Named;
//
//import org.primefaces.event.FileUploadEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.beust.jcommander.internal.Lists;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@RequestScoped
//@Named
//public class AnalisadorLogController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LoggerFactory.getLogger(AnalisadorLogController.class);
//
//	private static final String VOLTAR = "/pages/cadastros/home.xhtml";
//
//
//	
//	@Getter
//	@Setter
//	private String path;
//
//	@PostConstruct
//	public void init() {
//
//	}
//
//	public void upload() {
//		if (arquivo != null) {
//			File arquivo = new File("D:\\Atual\\log.log");
//	        
//	        List<String> arquivoEmLista = Lists.newArrayList();
//	        try {
//	            FileReader leitor = new FileReader(arquivo);
//	            BufferedReader buffer = new BufferedReader(leitor);
//	            String linha = buffer.readLine();
//	            
//	            while (linha != null) {
//	            	arquivoEmLista.add(linha);
//	                linha = buffer.readLine();
//	            }
//	            buffer.close();
//	            leitor.close();
//	        } catch (IOException e) {
//	            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
//	        }
//	        
//	        List<AnalisadorLog> logEmLista = Lists.newArrayList();
//	        
//	        arquivoEmLista.forEach( l -> {
//	        	
//	        	String[] logDividido = l.split("\\|");
//	        	if(logDividido.length == 9 ) {
//	        		String[] dataEHora = logDividido[0].split(" ");
//	        		LocalDate data = LocalDate.parse(dataEHora[0]);
//	        		String hora = null;
//	        		if(dataEHora.length > 1) {
//	        			hora = dataEHora[1].trim();
//	        		}
//	        		
//	        		logEmLista.add(new AnalisadorLog(
//	        				data,
//	        				hora,
//	        				logDividido[1].trim(),
//	        				logDividido[2].trim(),
//	        				logDividido[3].trim(),
//	        				logDividido[4].trim(),
//	        				logDividido[5].trim(),
//	        				logDividido[6].trim(),
//	        				logDividido[7].trim(),
//	        				logDividido[8].trim()
//	        				));
//	        	}
//	        });
//	        
//	        logEmLista.stream().filter(l -> l.getStatus().equals("INFO")).collect(Collectors.toList()).forEach(l -> System.out.println(l));
//		}
//	}
//	
//	public void teste() {
//		
//		System.out.println(teste);
//	
//	}
//	
//	public void handleFileUpload(FileUploadEvent event) {
//        arquivo = event.getFile();
//        System.out.println("Nome do arquivo: " + arquivo.getFileName());
//    }
//
//	public String voltar() {
//		return VOLTAR;
//	}
//
//}

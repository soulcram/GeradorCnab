package br.com.m3Tech.solucoesFromtis;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tika.exception.TikaException;

import com.beust.jcommander.internal.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Testando {

	public static void main(String[] args) throws IOException, TikaException {
		
		// Data Hora | STATUS | THREAD | USUARIO | IDENTIFICADOR | IP 1 | IP 2 | CLASSE | MSG


//        List<Cachorro> list = Lists.newArrayList(new Cachorro("auau"),new Cachorro("doguinho"));
//        
//        String json = new Gson().toJson(list);
//        
//        System.out.println(json);
        
//        String novoJson = "[{\"nome\":\"auau\"},{\"nome\":\"doguinho\"}]";
//        
//        Type listType = new TypeToken<List<Cachorro>>(){}.getType();
//        
//        List<Cachorro> fromJson = new Gson().fromJson(novoJson, listType);
//        
//        
//        for(Cachorro c : fromJson) {
//        	System.out.println(c.getNome());
//        }
		
		LocalDate parse = LocalDate.parse("311299",DateTimeFormatter.ofPattern("ddMMyy") );
		
		System.out.println(parse);
        
	}

}

@Getter
@Setter
@AllArgsConstructor
@ToString
class Cachorro{
	String nome;
}

@Getter
@Setter
@AllArgsConstructor
class AnalisadorLog{
	
	private LocalDate data;
	private String hora;
	private String status;
	private String thread;
	private String usuario;
	private String identificador;
	private String ip1;
	private String ip2;
	private String classe;
	private String mensagem;
	@Override
	public String toString() {
		return  data + " | " + hora +  " | " + status + " | " + thread
				+ " | " + usuario + " | " + identificador + " | " + ip1 + " | " + ip2
				+ " | " + classe + " | " + mensagem;
	}
	
	
}

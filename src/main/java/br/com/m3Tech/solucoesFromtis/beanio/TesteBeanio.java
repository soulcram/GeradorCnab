package br.com.m3Tech.solucoesFromtis.beanio;

import java.io.File;
import java.time.LocalDate;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

public class TesteBeanio {

	public static void main(String[] args) {
		StreamFactory streamFactory = StreamFactory.newInstance();
		streamFactory.load("D:\\Ambiente\\Projetos\\SVN\\OutrasAplicacoes\\RoboFromtis\\develop\\src\\main\\resources\\beanio.xml");
		
		String path = "D:\\Atual";

		File pathArquivo = new File(path);

		if (!pathArquivo.exists()) {
			pathArquivo.mkdirs();
		}

		File arquivoFinal = new File(pathArquivo, "teste.txt");

		BeanWriter out = streamFactory.createWriter("cnab240CobrancaId", arquivoFinal);
		
		Cnab240HeaderArquivo cnab240HeaderArquivo = new Cnab240HeaderArquivo("439", "123", "456", "789", "1011", "1213", 1, LocalDate.now(), "1");
			
		out.write(cnab240HeaderArquivo);
			
		out.flush();
		out.close();

		System.out.println("Fim da Geração");


	}

}

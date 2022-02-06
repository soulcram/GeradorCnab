package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.time.LocalDate;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;

public class GeradorArquivoCnabUtils {
	
	private GeradorArquivoCnabUtils() {}
	
	public static void gerar(CnabDto cnab, String tipo, Boolean importacaoAutomatica, String path) {
		
		StreamFactory factory = StreamFactory.newInstance();
        
		factory.loadResource("beanio.xml");
        
        File pathArquivo = new File(path);
        
        if(!pathArquivo.exists()) {
        	pathArquivo.mkdirs();
        }
        
        File arquivoFinal = new File(pathArquivo, getNomeArquivo(cnab, tipo, importacaoAutomatica));
        
        BeanWriter out = factory.createWriter(cnab.getLayout().getNmLayout(),arquivoFinal );        
                
        out.write(new CnabHeader(cnab));
        int qtdeTitulos = 2;
        for(TituloDto dto : cnab.getTitulos()) {
        	out.write(new CnabDetail(dto, qtdeTitulos++));
        }
        out.write(new CnabTrailler(StringUtils.getNumeroComZerosAEsquerda(qtdeTitulos,6)));
        
        out.flush();
        out.close();
        
        System.out.println("Fim da Geração");
		
	}

	
	private static String getNomeArquivo(CnabDto cnab, String tipo, boolean importacaoAutomatica) {
		return  (importacaoAutomatica ? cnab.getFundo().getCnpjFundo() + "_" : "" )
	            + "CNAB_" 
				+ cnab.getLayout().getTamLayout() 
				+ "_" 
				+ tipo 
				+ "_"
				+ LocalDate.now().toString().replaceAll("-", "") 
				+ "_" 
				+ cnab.getNumSeqArquivo() 
				+ ".txt";
	}

}

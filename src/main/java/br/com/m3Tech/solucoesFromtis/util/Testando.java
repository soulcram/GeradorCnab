package br.com.m3Tech.solucoesFromtis.util;

import java.io.File;
import java.nio.file.Files;

import br.com.fromtis.fidc.portal.servicos.consulta.soap.ArquivoCnab;
import br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnabProxy;

public class Testando {

	public static void main(String[] args) throws Exception {
		ImportacaoArquivoCnabProxy testProxy = new ImportacaoArquivoCnabProxy();

        String test = "D:\\GeradorCnab\\CNAB_444_RECOMPRA_20220628_100_01.txt";

        File file = new File(test);



        ArquivoCnab arquivo = new ArquivoCnab("45438526711145",  Files.readAllBytes(file.toPath()));

        testProxy.importarArquivo(arquivo);

	}

}

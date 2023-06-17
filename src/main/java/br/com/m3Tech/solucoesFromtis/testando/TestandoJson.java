package br.com.m3Tech.solucoesFromtis.testando;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDetailDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONHeaderDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONTrailerDto;

public class TestandoJson {

	public static void main(String[] args) {


		ArquivoJSONDto cnabjson = new ArquivoJSONDto();
		
		cnabjson.setNomeArquivo("nomeArquivo");
		
		ArquivoJSONHeaderDto header = new ArquivoJSONHeaderDto("0", 
				"1", 
				"REMESSA", 
				"02", 
				"COBRANCA",
				"00000000000000000001", 
				"nome originador", 
				"001", 
				"banco do brasil", 
				"20220414", 
				"MX", 
				"10", 
				"20230414");
		
		ArquivoJSONDetailDto detail = new ArquivoJSONDetailDto("1", 
				"", 
				"", 
				"", 
				"1", 
				"", 
				"", 
				"", 
				"", 
				"A", 
				"", 
				"001", 
				"", 
				"", 
				"1000.00", 
				"", 
				"", 
				"20230414", 
				"", 
				"1", 
				"102030", 
				"20230614", 
				"1100.00", 
				"", 
				"", 
				"01", 
				"", 
				"20230414", 
				"", 
				"", 
				"2", 
				"", 
				"ABCD", 
				"1000.00", 
				"", 
				"2", 
				"31245874520001", 
				"nomeSacado", 
				"endereco sacado", 
				"125455", 
				"001", 
				"04843120", 
				"nome cedente", 
				"32654123540001", 
				"", 
				"", 
				"", 
				"", 
				"", 
				"", 
				"", 
				"", 
				"", 
				null);
		
		ArquivoJSONTrailerDto trailer = new ArquivoJSONTrailerDto("9");
		
		cnabjson.setHeader(Lists.newArrayList(header));
		cnabjson.setDetail(Lists.newArrayList(detail));
		cnabjson.setTrailer(Lists.newArrayList(trailer));

		
		String json = new Gson().toJson(cnabjson);
		
		System.out.println(json);

	}

}

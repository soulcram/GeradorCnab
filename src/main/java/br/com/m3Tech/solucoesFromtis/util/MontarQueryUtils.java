package br.com.m3Tech.solucoesFromtis.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.dto.ColunaDto;
import br.com.m3Tech.solucoesFromtis.dto.TabelaDto;

public class MontarQueryUtils {
	
	private MontarQueryUtils() {}
	
	public static String getQueryInsert(Map<String, String> parametros, TabelaDto tabela, String nomeDaColunaIdentity) {
		
		String retorno = "INSERT INTO " + tabela.getNomeTabela() + " (";
		
		String colunas = "";
		
		String values = " values(";
		
		List<ColunaDto> colunasAdicionadas = Lists.newArrayList();
				
		for(Entry<String, String> item : parametros.entrySet()) {
			
			ColunaDto coluna = getColuna(item.getKey(), tabela.getColunas());
			
			if(coluna != null) {
				
				colunasAdicionadas.add(coluna);
				
				colunas += coluna.getNomeColuna() + ",";
				values += item.getValue() + ",";
				
			}
		}
		
		List<ColunaDto> restante = tabela.getColunas().stream().filter(c -> !colunasAdicionadas.contains(c))
		.filter(c -> !c.getAceitaNull())
		.collect(Collectors.toList());
		
		for(ColunaDto c : restante) {
			if(!nomeDaColunaIdentity.equals(c.getNomeColuna())) {
				colunas += c.getNomeColuna() + ",";
				values += getValorDefault(c.getTipo()) + ",";
			}
		}

		retorno += colunas.substring(0, colunas.length() - 1)+ " ) " + values.substring(0, values.length() - 1) + " )";
		
		return retorno;
		
	}

	private static String getValorDefault(String tipo) {
		
		switch(tipo) {
		
		case "money":
		case "decimal":
		case "numeric":
		case "float":
			return "0.0";
		case "datetime2":
		case "smalldatetime":
		case "datetime":
		case "date":
			return "CONVERT(DATE,GETDATE())";
	
		case "time":
			return "12:00";
			
		case "int":
		case "varbinary":
		case "smallint":
		case "varchar":
		case "tinyint":
		case "char":
		case "bigint":
		case "nvarchar":
		case "bit":
			return "0";
		
		}
		return "0";
	}

	private static ColunaDto getColuna(String key, List<ColunaDto> colunas) {
		
		List<ColunaDto> collect = colunas.stream().filter(c -> key.equals(c.getNomeColuna())).collect(Collectors.toList());
		
		if(collect == null || collect.isEmpty()) {
			return null;
		}else {
			return collect.get(0);
		}
	}
	

}

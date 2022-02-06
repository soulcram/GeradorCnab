package br.com.m3Tech.solucoesFromtis.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabelaDto {

	private String nomeTabela;
	private List<ColunaDto> colunas;


}

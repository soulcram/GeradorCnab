package br.com.m3Tech.solucoesFromtis.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoImportadoDto {

	private Integer idArquivo;
	private FundoDto fundo;
	private Integer layout;
	private String nomeArquivo;
	private String pathRepositorio;
	private List<String> conteudo;


}

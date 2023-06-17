package br.com.m3Tech.solucoesFromtis.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoJSONDto {

	@SerializedName(value = "nome_arquivo")
	private String nomeArquivo;
	
	@SerializedName(value = "header")
	private List<ArquivoJSONHeaderDto> header;
	
	@SerializedName(value = "detail")
	private List<ArquivoJSONDetailDto> detail;
	
	@SerializedName(value = "trailer")
	private List<ArquivoJSONTrailerDto> trailer;
}

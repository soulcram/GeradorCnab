package br.com.m3Tech.solucoesFromtis.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArquivoJSONTrailerDto {

	@SerializedName(value = "ident_registro_detail_recebivel")
	private String identRegistroDetailRecebivel;
}

package br.com.m3Tech.solucoesFromtis.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArquivoJSONLinhaDetailMensagemDto {

	@SerializedName(value = "ident_registro_detail_mensagem")
    private String identRegistroDetailMensagem;
	
	@SerializedName(value = "percentual_multa")
    private String percentualMulta;
	
	@SerializedName(value = "mensagem")
    private String mensagem;
}

package br.com.m3Tech.solucoesFromtis.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArquivoJSONHeaderDto {
	
	@SerializedName(value = "ident_registro_header")
	private String identRegistroHeader;
	
	@SerializedName(value = "ident_remessa")
	private String identRemessa;
	
	@SerializedName(value = "literal_remessa")
	private String literalRemessa;
	
	@SerializedName(value = "codigo_servico")
	private String codigoServico;
	
	@SerializedName(value = "literal_servico")
	private String literalServico;
	
	@SerializedName(value = "codigo_originador")
	private String codigoOriginador;
	
	@SerializedName(value = "nome_originador")
	private String nomeOriginador;
	
	@SerializedName(value = "numero_banco")
	private String numeroBanco;
	
	@SerializedName(value = "nome_banco")
	private String nomeBanco;
	
	@SerializedName(value = "data_gravacao_arquivo")
	private String dataGravacaoArquivo;
	
	@SerializedName(value = "ident_sistema")
	private String identSistema;
	
	@SerializedName(value = "codigo_empresa")
	private String codigoEmpresa;
	
	@SerializedName(value = "data_cessao")
	private String dataCessao;
}

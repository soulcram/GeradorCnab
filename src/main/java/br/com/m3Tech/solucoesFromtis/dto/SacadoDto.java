package br.com.m3Tech.solucoesFromtis.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SacadoDto {

	private Integer idSacado;
	private String nomeSacado;
	private String docSacado;
	private String endereco;
	private String cep;
	private String numero;
	private String complemento;
	private String email;
	private String tipoPessoaEnum;
	private String bairro;
	private String cidade;
	private String UF;
	private String pais;
	private List<ParteRepresentanteDto> partes;

	@Override
	public String toString() {
		return nomeSacado;
	}
	
	

	public SacadoDto(Integer idSacado, String nomeSacado, String docSacado, String endereco, String cep) {
		this.idSacado = idSacado;
		this.nomeSacado = nomeSacado;
		this.docSacado = docSacado;
		this.endereco = endereco;
		this.cep = cep;
	}



	public SacadoDto(Integer idSacado, String nomeSacado, String docSacado, String endereco, String cep, String numero,
			String complemento, String email, String tipoPessoaEnum, String bairro, String cidade, String uF,
			String pais, List<ParteRepresentanteDto> partes) {
		this.idSacado = idSacado;
		this.nomeSacado = nomeSacado != null ? nomeSacado : "";
		this.docSacado = docSacado != null ? docSacado : "";
		this.endereco = endereco != null ? endereco : "";
		this.cep = cep != null ? cep : "";
		this.numero = numero != null ? numero : "";
		this.complemento = complemento != null ? complemento : "";
		this.email = email != null ? email : "";
		this.tipoPessoaEnum = tipoPessoaEnum != null ? tipoPessoaEnum : "";
		this.bairro = bairro != null ? bairro : "";
		this.cidade = cidade != null ? cidade : "";
		UF = uF != null ? uF : "";
		this.pais = pais != null ? pais : "";
		this.partes = partes != null ? partes : new ArrayList<>();
	}

}

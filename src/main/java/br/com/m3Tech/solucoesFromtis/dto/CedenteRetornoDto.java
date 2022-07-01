package br.com.m3Tech.solucoesFromtis.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CedenteRetornoDto {

	
	private String cnpj;
    private String nome;
    private String banco;
    private String agencia;
    private String conta;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private List<ParteRepresentanteDto> partes;
    private List<AvalistaDto> avalistas;
    private List<TituloRetornoDto> titulos;
    private List<TituloRetornoDto> titulosRecompra;
    
    
	public CedenteRetornoDto(String cnpj, String nome, String banco, String agencia, String conta, String endereco,
			String numero, String complemento, String bairro, String cidade, String uf, String cep, String telefone,
			String inscricaoEstadual, String inscricaoMunicipal, List<ParteRepresentanteDto> partes,
			List<AvalistaDto> avalistas, List<TituloRetornoDto> titulos, List<TituloRetornoDto> titulosRecompra) {
		super();
		this.cnpj = cnpj != null ? cnpj : "";
		this.nome = nome != null ? nome : "";
		this.banco = banco != null ? banco : "";
		this.agencia = agencia != null ? agencia : "";
		this.conta = conta != null ? conta : "";
		this.endereco = endereco != null ? endereco : "";
		this.numero = numero != null ? numero : "";
		this.complemento = complemento != null ? complemento : "";
		this.bairro = bairro != null ? bairro : "";
		this.cidade = cidade != null ? cidade : "";
		this.uf = uf != null ? uf : "";
		this.cep = cep != null ? cep : "";
		this.telefone = telefone != null ? telefone : "";
		this.inscricaoEstadual = inscricaoEstadual != null ? inscricaoEstadual : "";
		this.inscricaoMunicipal = inscricaoMunicipal != null ? inscricaoMunicipal : "";
		this.partes = partes != null ? partes : new ArrayList<>();
		this.avalistas = avalistas != null ? avalistas : new ArrayList<>();
		this.titulos = titulos != null ? titulos : new ArrayList<>();
		this.titulosRecompra = titulosRecompra != null ? titulosRecompra : new ArrayList<>();
	}


}

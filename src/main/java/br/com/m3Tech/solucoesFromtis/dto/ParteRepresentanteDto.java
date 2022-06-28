package br.com.m3Tech.solucoesFromtis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParteRepresentanteDto {

    private String nome;
    private String cpf;
    private String email;
    private String papel;
    private boolean assinaIsoladamente;
    private boolean emiteDuplicata;
    private boolean assinaPorEndosso;
    private boolean assinaTermoCessao;
    
    
	public ParteRepresentanteDto(String nome, String cpf, String email, String papel, boolean assinaIsoladamente,
			boolean emiteDuplicata, boolean assinaPorEndosso, boolean assinaTermoCessao) {
		super();
		this.nome = nome != null ? nome : "";
		this.cpf = cpf != null ? cpf : "";
		this.email = email != null ? email : "";
		this.papel = papel != null ? papel : "";
		this.assinaIsoladamente = assinaIsoladamente;
		this.emiteDuplicata = emiteDuplicata;
		this.assinaPorEndosso = assinaPorEndosso;
		this.assinaTermoCessao = assinaTermoCessao;
	}
    
    


}

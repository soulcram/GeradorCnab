package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvalistaDto {

	private String nome;
    private String cpf;
    private String email;
    private String papel;
    
	public AvalistaDto(String nome, String cpf, String email, String papel) {
		super();
		this.nome = nome != null ? nome : "";
		this.cpf = cpf != null ? cpf : "";
		this.email = email != null ? email : "";
		this.papel = papel != null ? papel : "";
	}
    
    

}

package br.com.m3Tech.solucoesFromtis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParteDto {
	
    private String nome;
    private String cpf;
    private String email;
    private String papel;
    private String tipoAssinaturaEnum;
    
	public ParteDto(String nome, String cpf, String email, String papel, String tipoAssinaturaEnum) {
		super();
		this.nome = nome != null ? nome : "";
		this.cpf = cpf != null ? cpf : "";
		this.email = email != null ? email : "";
		this.papel = papel != null ? papel : "";
		this.tipoAssinaturaEnum = tipoAssinaturaEnum != null ? tipoAssinaturaEnum : "";
	} 
    
    

}

package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Authentication {
    private String User;
    private String Password;
    private Integer Agency;
    private Integer AccountNumber;
    private String CPF_CNPJ;
}
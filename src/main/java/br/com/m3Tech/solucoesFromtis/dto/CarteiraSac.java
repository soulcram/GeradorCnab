package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarteiraSac {

    private String codigo;
    private String tipoCota;
    private String fundo;
}

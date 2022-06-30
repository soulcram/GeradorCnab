package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivosPorMinutoDto {

	private Integer minimo = 0;
    private Integer maximo = 0;
    private Integer media = 0;

}

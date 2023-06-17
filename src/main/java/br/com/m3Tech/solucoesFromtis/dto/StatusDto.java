package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

	private Integer aguardando = 0;
    private Integer enviado = 0;
    private Integer invalido = 0;
    private Integer validado = 0;
    private Integer finalizado = 0;
    private Integer processando = 0;
    private Integer erro = 0;

}

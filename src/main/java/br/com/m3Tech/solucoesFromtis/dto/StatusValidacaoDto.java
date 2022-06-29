package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusValidacaoDto {

	private Integer aguardando = 0;
    private Integer enviado = 0;
    private Integer invalido = 0;
    private Integer validado = 0;

}

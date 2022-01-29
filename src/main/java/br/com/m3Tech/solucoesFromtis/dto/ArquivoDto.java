package br.com.m3Tech.solucoesFromtis.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDto {

	private Integer idArquivo;
	private String nomeArquivo;
	private LocalDate dataEntrada;


}

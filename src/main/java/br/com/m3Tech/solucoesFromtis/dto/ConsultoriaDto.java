package br.com.m3Tech.solucoesFromtis.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultoriaDto {

	
	private String nomeConsultoria;
    private String cnpjConsultoria;
    private List<ParteDto> partes;


}

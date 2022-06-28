package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosRetornoCertificadoDigitalDto {

	private Integer id;
	private OperacaoDto operacao;
	private CustodianteDto custodiante;
	private FundoDto fundo;
	private ConsultoriaDto consultoria;
	private CedenteRetornoDto cedente;


}

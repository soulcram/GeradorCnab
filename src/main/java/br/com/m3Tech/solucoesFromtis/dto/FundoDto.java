package br.com.m3Tech.solucoesFromtis.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundoDto {

	private Integer idFundo;
	private String nomeFundo;
	private String cnpjFundo;
	private String codigoIsin;
	private Integer layoutAquisicao;
	private LocalDate dataFundo;
	private List<ParteDto> partes;
	private List<TestemunhaDto> testemunhas;

	@Override
	public String toString() {
		return idFundo + " - " + nomeFundo;
	}

	public FundoDto(Integer idFundo, String nomeFundo, String cnpjFundo, String codigoIsin, Integer layoutAquisicao,
			LocalDate dataFundo) {
		this.idFundo = idFundo;
		this.nomeFundo = nomeFundo;
		this.cnpjFundo = cnpjFundo;
		this.codigoIsin = codigoIsin;
		this.layoutAquisicao = layoutAquisicao;
		this.dataFundo = dataFundo;
	}

}

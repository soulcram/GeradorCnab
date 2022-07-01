package br.com.m3Tech.solucoesFromtis.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CnabCobrancaDto {
	
	private FundoDto fundo;
	private String codigoCobranca;
	private BancoDto banco;
	private LocalDate dataGravacao;
	private LayoutEnum layout;
	private Integer numSeqArquivo;
	private List<TituloDto> titulos;
	
	public CnabCobrancaDto() {
		this.fundo = new FundoDto();
		this.banco = new BancoDto();
		this.titulos = new ArrayList<TituloDto>();
	}

}

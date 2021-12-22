package br.com.m3Tech.geradorCnab.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.geradorCnab.enuns.LayoutEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CnabDto {
	
	private FundoDto fundo;
	private OriginadorDto originador;
	private BancoDto banco;
	private LocalDate dataGravacao;
	private LayoutEnum layout;
	private Integer numSeqArquivo;
	private List<TituloDto> titulos;
	
	public CnabDto() {
		this.fundo = new FundoDto();
		this.originador = new OriginadorDto();
		this.banco = new BancoDto();
		this.titulos = new ArrayList<TituloDto>();
	}

}

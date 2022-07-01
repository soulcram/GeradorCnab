package br.com.m3Tech.solucoesFromtis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
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
	private Integer tpPartRespTarifacao;
	private BigDecimal percTarifacao;//" position="139" length="4" />
	private BigDecimal taxaCessaoAnual;//" position="143" length="5" />
	private BigDecimal taxaCessao;
	
	public CnabDto() {
		this.fundo = new FundoDto();
		this.originador = new OriginadorDto();
		this.banco = new BancoDto();
		this.titulos = new ArrayList<TituloDto>();
	}

}

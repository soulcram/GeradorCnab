package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.util.LayoutUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabHeader {
	
	private String codigoOriginador;
	private String nomeOriginador;
	private String numeroBanco;
	private String nomeBanco;
	private LocalDate dataGravacao;
	private String numSeqArquivo;
	private String numSeqRegistro;
	private String coobrigacao;
	private Integer identificadorCoobrigacao;
	private Integer tpPartRespTarifacao;
	private BigDecimal percTarifacao;//" position="139" length="4" />
	private BigDecimal taxaCessaoAnual;//" position="143" length="5" />
	private BigDecimal taxaCessao;//" position="148" length="5" />
	
	public CnabHeader(CnabDto dto) {
		this.coobrigacao = (LayoutUtils.coobrigacaoNoHeader(dto.getLayout()) ? ("01".equals(dto.getTitulos().get(0).getCoobrigacao()) ? "S" : "N") : null);
		this.identificadorCoobrigacao = (LayoutUtils.coobrigacaoNoHeader(dto.getLayout()) ? ("01".equals(dto.getTitulos().get(0).getCoobrigacao()) ? 1 : 0) : null);
		this.codigoOriginador = dto.getOriginador().getCodigoOriginador();
		this.nomeOriginador = dto.getOriginador().getNomeOriginador();
		this.numeroBanco = dto.getBanco().getCodigoBanco();
		this.nomeBanco = dto.getBanco().getNomeBanco();
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqArquivo = StringUtils.getNumeroComZerosAEsquerda(dto.getNumSeqArquivo(), 7);
		this.numSeqRegistro = "000001";
		this.tpPartRespTarifacao = dto.getTpPartRespTarifacao();
		this.percTarifacao = dto.getPercTarifacao();
		this.taxaCessaoAnual = dto.getTaxaCessaoAnual();
		this.taxaCessao = dto.getTaxaCessao();
	}
}

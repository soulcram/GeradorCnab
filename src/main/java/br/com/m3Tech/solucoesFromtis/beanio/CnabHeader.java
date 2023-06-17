package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.m3Tech.solucoesFromtis.dto.CnabDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.util.LayoutUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
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
	private String nuReserva;
	private Integer identificadorCoobrigacao;
	private Integer tpPartRespTarifacao;
	private Integer tipoPagamento;
	private String docCedente;
	private String tipoDocCedente;
	private String nomeCedente;
	private BigDecimal percTarifacao;//" position="139" length="4" />
	private BigDecimal taxaCessaoAnual;//" position="143" length="5" />
	private BigDecimal taxaCessao;//" position="148" length="5" />
	private BigDecimal taxaSpread;
	private BigDecimal valorRetencao;
	private String bancoCedente;
	private String agenciaCedente;
	private String dgAgenciaCedente;
	private String contaCedente;
	private String dgContaCedente;
	
	public CnabHeader(CnabDto dto) {
		this.coobrigacao = (LayoutUtils.coobrigacaoNoHeader(dto.getLayout()) ? ("01".equals(dto.getTitulos().get(0).getCoobrigacao()) ? "S" : "N") : null);
		this.identificadorCoobrigacao = (LayoutUtils.coobrigacaoNoHeader(dto.getLayout()) ? ("01".equals(dto.getTitulos().get(0).getCoobrigacao()) ? 1 : 0) : null);
		this.codigoOriginador = dto.getOriginador().getCodigoOriginador();
		this.nomeOriginador = dto.getOriginador().getNomeOriginador();
		this.numeroBanco = dto.getBanco().getCodigoBanco();
		this.nomeBanco = dto.getBanco().getNomeBanco();
		this.dataGravacao = dto.getDataGravacao();
		this.numSeqArquivo = StringUtils.getNumeroComZerosAEsquerda(dto.getNumSeqArquivo(), 7);
		this.numSeqRegistro = (LayoutEnum.CNAB_450_REMESSA_GENIAL.equals(dto.getLayout()) || LayoutEnum.CNAB_450_REMESSA_BRASIL_PLURAL.equals(dto.getLayout()) )? "0000000001" : "000001";
		this.tpPartRespTarifacao = dto.getTpPartRespTarifacao();
		this.nuReserva = ValorAleatorioUtil.getStringNumeros(5);
		this.percTarifacao = dto.getPercTarifacao() != null ? dto.getPercTarifacao() : BigDecimal.TEN;
		this.taxaCessaoAnual = dto.getTaxaCessaoAnual()!= null ? dto.getTaxaCessaoAnual() : ValorAleatorioUtil.getTaxaDecimal();
		this.taxaCessao = dto.getTaxaCessao()!= null ? dto.getTaxaCessao() : ValorAleatorioUtil.getTaxaDecimal();
		this.taxaSpread = dto.getTaxaSpread();
		this.tipoPagamento = dto.getTipoPagamento();
		this.valorRetencao = dto.getValorRetencao() == null ? ValorAleatorioUtil.getValorDecimal(100, 2500) :dto.getValorRetencao();
		this.bancoCedente = dto.getBancoCedente();
		this.agenciaCedente = dto.getAgenciaCedente();
		this.dgAgenciaCedente = dto.getDgAgenciaCedente();
		this.contaCedente = dto.getContaCedente();
		this.nomeCedente = dto.getTitulos().get(0).getCedente().getNomeCedente();
		this.tipoDocCedente = dto.getTitulos().get(0).getCedente().getDocCedente().length() == 14 ? "2" : "1";
		this.docCedente = dto.getTitulos().get(0).getCedente().getDocCedente();
		
	}
}

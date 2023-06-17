package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabDetail {
	
	private String coobrigacao;
	private String seuNumero;
	private String numeroBanco;
	private String nossoNumero;
	private BigDecimal valorPago;
	private LocalDate dataLiquidacao;
	private String identOcorrencia;
	private String numDocumento;
	private LocalDate dataVencimento;
	private BigDecimal valorTitulo;
	private String especieTitulo;
	private LocalDate dataEmissao;
	private LocalDate dataCarencia;
	private String tipoPessoaCedente;
	private String docCedente;
	private String nomeCedente;
	private String termoCessao;
	private BigDecimal valorAquisicao;
	private BigDecimal valorAbatimento;
	private BigDecimal valorParcial;
	private BigDecimal taxaJurosIndexador;
	private BigDecimal taxaSpread;
	private BigDecimal taxaContrato;
	private BigDecimal taxaJuros;
	private BigDecimal taxaJurosMensal;
	private BigDecimal jurosMora;
	private BigDecimal multa;
	private String tipoPessoaSacado;
	private String docSacado;
	private String nomeSacado;
	private String enderecoSacado;
	private String cepSacado;
	private String chaveNfe;
	private String numSeqRegistro;
	private String tipoJuros;
	private String variacaoCambial;
	private String risco;
	private String tipoPessoaOrigRecebivel;
	private String cpfCnpjOrigRecebivel;
	private String nomeOrigRecebivel;
	private String qtdLastros;
	private String cet;
	private String notaFiscal;
	private String idOperacaoBanco;
	private String idLote;
	private String tpContrato;
	private String cnpjEnteConsignante;
	private String caracteristicaEspecial;
	private String modalidade;
	private String natureza;
	private String origemRecurso;
	private String anoVeiculo;
	private BigDecimal valorVeiculo;
	private BigDecimal valorContrato;
	private String chassi;
	private String numeroContratoC3;
	private String numeroParcelaC3;
	private LocalDate dataNascimentoSacado;
	private String classificacaoRiscoCedente;
	private String qtdParcelas;
	private BigDecimal valorJurosDiaAtraso;
	private String ltv;
	private String agio;
	private String codBancoCobranca;
	private String numeroNotaFiscal;
	private String numeroSerieNotaFiscalDuplicada;
	private String bancoCobranca;
	private String agenciaCobranca;
	private String cnpjOriginador;
	
	public CnabDetail(TituloDto dto,Integer numSeq, LayoutEnum layout) {
		
		this.dataCarencia = dto.getDataCarencia();
		this.risco = dto.getRisco() != null ? dto.getRisco().getCodRisco() : null;
		this.taxaJurosIndexador = dto.getTaxaJurosIndexador();
		this.taxaSpread = dto.getTaxaSpread();
		this.taxaJuros = dto.getTaxaJuros();
		this.tipoJuros = dto.getIndexador() != null && dto.getIndexador().getCodIndexador() != null ? dto.getIndexador().getCodIndexador().toString() : null;
		this.variacaoCambial = dto.getVariacaoCambial();
		this.coobrigacao = (35 == layout.getCdLayout()) ? Integer.valueOf(dto.getCoobrigacao()).toString() : dto.getCoobrigacao();
		this.seuNumero = dto.getSeuNumero();
		this.numeroBanco = dto.getNumBanco();
		this.nossoNumero = dto.getNossoNumero();
		this.valorPago = dto.getValorPago();
		this.dataLiquidacao = dto.getDataLiquidacao() == null ? LocalDate.now() : dto.getDataLiquidacao();
		this.identOcorrencia = dto.getMovimento().getCdOcorrencia();
		this.numDocumento = dto.getNumeroDocumento(); 
		this.dataVencimento = dto.getDataVencimento();
		this.valorTitulo = dto.getValorTitulo();
		this.especieTitulo = dto.getEspecie();
		this.dataEmissao = LocalDate.now();
		this.tipoPessoaCedente = dto.getCedente().getDocCedente().length() == 11 ? "01" : "02";
		this.docCedente = dto.getCedente().getDocCedente();
		this.nomeCedente = StringUtils.removerAcentos(dto.getCedente().getNomeCedente());
		this.termoCessao = dto.getTermoCessao();
		this.valorAquisicao = dto.getValorAquisicao();
		this.valorAbatimento = dto.getValorAbatimento();
		this.valorParcial = dto.getValorAbatimento();
		this.tipoPessoaSacado = LayoutEnum.CNAB_550_REMESSA_COMUM_CM_V2.equals(layout) ?  (dto.getSacado().getDocSacado().length() == 11 ? "1" : "2") : (dto.getSacado().getDocSacado().length() == 11 ? "01" : "02");
		this.docSacado = dto.getSacado().getDocSacado();
		this.nomeSacado = StringUtils.removerAcentos(dto.getSacado().getNomeSacado());
		this.enderecoSacado = dto.getSacado().getEndereco();
		this.cepSacado = dto.getSacado().getCep();
		this.chaveNfe = dto.getChaveNfe();
//		this.tipoPessoaOrigRecebivel = dto.getDocOrigRecebivel().length() == 11 ? "01" : "02";;
//		this.cpfCnpjOrigRecebivel = dto.getDocOrigRecebivel();
//		this.nomeOrigRecebivel = dto.getNomeOrigRecebivel();
		this.qtdLastros = dto.getQtdLastros();
		this.taxaJurosMensal =ValorAleatorioUtil.getTaxaDecimal();
		this.taxaContrato =ValorAleatorioUtil.getTaxaDecimal();
		this.jurosMora = (LayoutEnum.CNAB_450_REMESSA_BRASIL_PLURAL.equals(layout) ) ? ValorAleatorioUtil.getValorDecimal(1, 99) : ValorAleatorioUtil.getTaxaDecimal();
		this.multa = ValorAleatorioUtil.getTaxaDecimal();
		this.cet = ValorAleatorioUtil.getStringNumeros(5);
		this.notaFiscal = ValorAleatorioUtil.getStringNumeros(9);
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,(LayoutEnum.CNAB_450_REMESSA_GENIAL.equals(layout) || LayoutEnum.CNAB_450_REMESSA_BRASIL_PLURAL.equals(layout) ) ? 10 : 6) ;
		this.idOperacaoBanco = ValorAleatorioUtil.getStringNumeros(5);
		this.idLote = ValorAleatorioUtil.getStringNumeros(5);
		this.tpContrato = ValorAleatorioUtil.getStringNumeros(4);
		this.cnpjEnteConsignante = new GeradorCpfCnpjRgFake().cnpj(false);
		this.caracteristicaEspecial = ValorAleatorioUtil.getStringNumeros(2);
		this.modalidade = ValorAleatorioUtil.getStringNumeros(4);
		this.natureza = ValorAleatorioUtil.getStringNumeros(2);
		this.origemRecurso = ValorAleatorioUtil.getStringNumeros(4);
		this.anoVeiculo = ValorAleatorioUtil.getStringNumeros(4);
		this.valorVeiculo = ValorAleatorioUtil.getValorDecimal(10, 1000);
		this.valorContrato = ValorAleatorioUtil.getValorDecimal(10, 10000);
		this.chassi = ValorAleatorioUtil.getStringNumeros(17);
		this.numeroContratoC3 = ValorAleatorioUtil.getStringNumeros(20);
		this.numeroParcelaC3 = ValorAleatorioUtil.getStringNumeros(20);
		this.dataNascimentoSacado = LocalDate.now().minusYears(20);
		this.classificacaoRiscoCedente = "A";
		this.qtdParcelas = "003";
		this.valorJurosDiaAtraso = ValorAleatorioUtil.getValorDecimal(10, 1000);
		this.codBancoCobranca = ValorAleatorioUtil.getStringNumeros(1);
		this.numeroNotaFiscal = ValorAleatorioUtil.getStringNumeros(10);
		this.numeroSerieNotaFiscalDuplicada = ValorAleatorioUtil.getStringNumeros(3);
		this.agio = ValorAleatorioUtil.getStringNumeros(3);
		this.ltv = ValorAleatorioUtil.getStringNumeros(3);
		this.bancoCobranca = ValorAleatorioUtil.getStringNumeros(3);
		this.agenciaCobranca = ValorAleatorioUtil.getStringNumeros(5);
		this.cnpjOriginador = (new  GeradorCpfCnpjRgFake().cnpj(false)).substring(0, 8);
		
		
		
		
	}
	
	public CnabDetail(String[] colunas,Integer numSeq) {
		
		this.coobrigacao = colunas[14];
		this.seuNumero = colunas[2];
		this.numeroBanco = null;
		this.nossoNumero = null;
		this.valorPago = BigDecimal.ZERO;
		this.dataLiquidacao = null;
		this.identOcorrencia = "01";
		this.numDocumento = colunas[3]; 
		this.dataVencimento = LocalDate.parse(colunas[4],DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.valorTitulo = new BigDecimal(colunas[5].replaceAll(",", "."));
		this.especieTitulo = colunas[12];
		this.dataEmissao = LocalDate.parse(colunas[13],DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.tipoPessoaCedente = colunas[0].length() == 11 ? "01" : "02";
		this.docCedente = colunas[0];
		this.nomeCedente = colunas[1];
		this.termoCessao = null;
		this.valorAquisicao = new BigDecimal(colunas[8].replaceAll(",", "."));
		this.valorAbatimento = BigDecimal.ZERO;
		this.tipoPessoaSacado = colunas[6].length() == 11 ? "01" : "02";
		this.docSacado = colunas[6];
		this.nomeSacado = colunas[7];
		this.enderecoSacado = colunas[10];
		this.cepSacado = colunas[11];
		this.chaveNfe = colunas[15];
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}
	

}

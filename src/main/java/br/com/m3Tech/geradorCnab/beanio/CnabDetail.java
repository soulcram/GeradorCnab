package br.com.m3Tech.geradorCnab.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.m3Tech.geradorCnab.dto.TituloDto;
import br.com.m3Tech.geradorCnab.util.StringUtils;
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
	private String tipoPessoaCedente;
	private String docCedente;
	private String nomeCedente;
	private String termoCessao;
	private BigDecimal valorAquisicao;
	private BigDecimal valorAbatimento;
	private String tipoPessoaSacado;
	private String docSacado;
	private String nomeSacado;
	private String enderecoSacado;
	private String cepSacado;
	private String chaveNfe;
	private String numSeqRegistro;
	
	public CnabDetail(TituloDto dto,Integer numSeq) {
		
		this.coobrigacao = dto.getCoobrigacao();
		this.seuNumero = dto.getSeuNumero();
		this.numeroBanco = dto.getNumBanco();
		this.nossoNumero = dto.getNossoNumero();
		this.valorPago = dto.getValorPago();
		this.dataLiquidacao = dto.getDataLiquidacao();
		this.identOcorrencia = dto.getMovimento().getCdOcorrencia();
		this.numDocumento = dto.getNumeroDocumento(); 
		this.dataVencimento = dto.getDataVencimento();
		this.valorTitulo = dto.getValorTitulo();
		this.especieTitulo = dto.getEspecie();
		this.dataEmissao = LocalDate.now();
		this.tipoPessoaCedente = dto.getCedente().getDocCedente().length() == 11 ? "01" : "02";
		this.docCedente = dto.getCedente().getDocCedente();
		this.nomeCedente = dto.getCedente().getNomeCedente();
		this.termoCessao = dto.getTermoCessao();
		this.valorAquisicao = dto.getValorAquisicao();
		this.valorAbatimento = dto.getValorAbatimento();
		this.tipoPessoaSacado = dto.getSacado().getDocSacado().length() == 11 ? "01" : "02";
		this.docSacado = dto.getSacado().getDocSacado();
		this.nomeSacado = dto.getSacado().getNomeSacado();
		this.enderecoSacado = dto.getSacado().getEndereco();
		this.cepSacado = dto.getSacado().getCep();
		this.chaveNfe = dto.getChaveNfe();
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
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

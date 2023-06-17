package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorCpfCnpjRgFake;
import br.com.m3Tech.solucoesFromtis.service.impl.GeradorNomeFake;
import br.com.m3Tech.solucoesFromtis.util.BigDecimalUtils;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import br.com.m3Tech.solucoesFromtis.util.ValorAleatorioUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabDetail2L41 {
	
	private LocalDate dataEmissao;	
	private LocalDate dataVencimentoLastro;	
	private BigDecimal valorTituloLastro;
	private BigDecimal valorTotalPedido;
	private BigDecimal valorNotaFiscal;
	private String codigoEspecieLastro;
	private String tipoLastro;	
	private String numeroLastro;
	private String numeroPedido;
	private String tipoInscricaoLastro;
	private String cpfCnpjSacadoLastro;
	private String nomeSacadoLastro;
	private String logradouroSacadoLastro;
	private String inscricaoMunicipalLastro;	
	private String numeroNotaFiscalLastro;
	private String chaveNotaFiscal;
	private String numSeqRegistro;
	
	public CnabDetail2L41(TituloDto dto,Integer quant,Integer numSeq) {
		
		GeradorCpfCnpjRgFake g = new GeradorCpfCnpjRgFake();
		GeradorNomeFake nomeFake = new GeradorNomeFake();
		
		this.dataEmissao = LocalDate.now();
		this.dataVencimentoLastro = LocalDate.now().plusDays(15);
		this.valorTituloLastro = dto.getValorTitulo().divide(new BigDecimal(quant));
		this.valorTotalPedido = dto.getValorTitulo();
		this.valorNotaFiscal = ValorAleatorioUtil.getValorDecimal(10, 1000);
		this.codigoEspecieLastro = ValorAleatorioUtil.getStringNumeros(2);
		this.tipoLastro = ValorAleatorioUtil.getStringNumeros(2);
		this.numeroLastro = dto.getNumeroDocumento();
		this.numeroPedido = ValorAleatorioUtil.getStringNumeros(15);
		this.tipoInscricaoLastro = dto.getSacado().getDocSacado().length() == 14 ? "02": "01";
		this.cpfCnpjSacadoLastro = dto.getSacado().getDocSacado();
		this.nomeSacadoLastro = nomeFake.gerarNomeCompleto();
		this.logradouroSacadoLastro = "Rua: " + nomeFake.gerarNomeCompleto();
		this.inscricaoMunicipalLastro = ValorAleatorioUtil.getStringNumeros(8);
		this.numeroNotaFiscalLastro = ValorAleatorioUtil.getStringNumeros(20);
		this.chaveNotaFiscal = "31190600006388319890559240000000311006164587"; 
		this.numSeqRegistro = StringUtils.getNumeroComZerosAEsquerda(numSeq,6) ;
		
		
	}
		

}

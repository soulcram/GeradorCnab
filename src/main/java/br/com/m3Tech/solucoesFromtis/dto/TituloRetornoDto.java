package br.com.m3Tech.solucoesFromtis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TituloRetornoDto {

	
    private String numero;
    private String numeroControleParticipante;
    private LocalDate dataDeEmissaoDuplicata;
    private LocalDate dataDeVencimentoDuplicata;
    private BigDecimal valorBruto;
    private BigDecimal valorLiquido;
    private String numeroDaNotaFiscal;
    private String serieDaNotaFiscal;
    private String nomePkcs7;
    private String chaveNfe;
    private SacadoDto sacado;
    private boolean comCoobrigacao;
    private String tipoTituloEnum;


}

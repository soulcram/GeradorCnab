package br.com.m3Tech.solucoesFromtis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoDto {

    private BigDecimal valorLiquido;
    private BigDecimal valorBruto;
    private boolean comAdiantamento;
    private String termoCessao;
    private BigDecimal valorRecompra;


}

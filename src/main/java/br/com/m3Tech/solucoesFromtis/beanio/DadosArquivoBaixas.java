package br.com.m3Tech.solucoesFromtis.beanio;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosArquivoBaixas {
	
	private String nomeSacado;
	private String cpf;
	private BigDecimal valor;
	private Integer qtdeParcelas;

}

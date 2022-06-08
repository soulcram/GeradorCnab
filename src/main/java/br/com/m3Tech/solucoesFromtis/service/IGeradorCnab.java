package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.CnabCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.CnabDto;


public interface IGeradorCnab {
	
	
	public void gerar(CnabDto cnab, String tipo, Boolean importacaoAutomatica, String path);

	public void gerarRetornoCobrança(CnabCobrancaDto cnab, String tipo, Boolean importacaoAutomatica, String path);


}

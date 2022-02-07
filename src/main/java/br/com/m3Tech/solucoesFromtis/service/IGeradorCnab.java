package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.CnabDto;


public interface IGeradorCnab {
	
	
	public void gerar(CnabDto cnab, String tipo, Boolean importacaoAutomatica, String path);


}

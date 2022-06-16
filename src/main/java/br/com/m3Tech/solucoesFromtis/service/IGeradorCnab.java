package br.com.m3Tech.solucoesFromtis.service;

import java.io.File;

import br.com.m3Tech.solucoesFromtis.dto.CnabDto;


public interface IGeradorCnab {
	
	
	public File gerar(CnabDto cnab, String tipo, Boolean importacaoAutomatica, String path);


}

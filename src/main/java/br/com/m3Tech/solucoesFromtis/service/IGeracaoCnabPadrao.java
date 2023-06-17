package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IGeracaoCnabPadrao {
	
	
	public void gerarCnabAquisicao(Base base, FundoDto fundo) throws Exception;


}

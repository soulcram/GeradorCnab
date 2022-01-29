package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;


public interface IGeracaoCnabPadrao {
	
	
	public void gerarCnabAquisicao(Connection con, FundoDto fundo) throws Exception;


}

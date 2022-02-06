package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.m3Tech.solucoesFromtis.dto.TabelaDto;


public interface ITabelaService {
	
	
	public TabelaDto getTabela(Connection con, String nomeTabela) throws SQLException;

}

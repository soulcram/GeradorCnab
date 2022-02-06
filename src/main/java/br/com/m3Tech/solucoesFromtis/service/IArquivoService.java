package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;


public interface IArquivoService {
	
	
	public ArquivoDto inserirTbArquivo(Connection con, FundoDto fundo, String nomeArquivo) throws SQLException;

	public ArquivoDto findArquivoByNomeArquivo(Connection con, String nomeArquivo) throws SQLException;
	
	public boolean inserirTbArquivoImportado(Connection con, List<String> cnab, ArquivoDto arquivo) throws SQLException;

}

package br.com.m3Tech.solucoesFromtis.service;

import java.io.File;
import java.sql.Connection;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;


public interface IFilaService {
	
	
	public Long inserirFilaImportacaoArquivo(Connection con, FundoDto fundo);

	public Integer inserirArquivoValidacao(Connection con, Long idFilaImportacao, File arquivo, Integer cdLayout);
	

}

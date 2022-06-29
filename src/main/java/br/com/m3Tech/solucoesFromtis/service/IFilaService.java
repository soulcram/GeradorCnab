package br.com.m3Tech.solucoesFromtis.service;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.ArquivosPorMinutoValidacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.StatusValidacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.TempoValidacaoDto;


public interface IFilaService {
	
	
	public Long inserirFilaImportacaoArquivo(Connection con, FundoDto fundo);

	public Integer inserirArquivoValidacao(Connection con, Long idFilaImportacao, File arquivo, Integer cdLayout);

	public ArquivosPorMinutoValidacaoDto getArquivosPorMinutoFilaValidacao(Connection con);

	public StatusValidacaoDto getStatusArquivosFilaValidacao(Connection con);

	public List<TempoValidacaoDto> getTemposFilaValidacao(Connection con);
	

}

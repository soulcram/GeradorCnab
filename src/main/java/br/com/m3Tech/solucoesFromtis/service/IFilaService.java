package br.com.m3Tech.solucoesFromtis.service;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.ArquivosPorMinutoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.StatusDto;
import br.com.m3Tech.solucoesFromtis.dto.TempoDto;


public interface IFilaService {
	
	
	public Long inserirFilaImportacaoArquivo(Connection con, FundoDto fundo);

	public Integer inserirArquivoValidacao(Connection con, Long idFilaImportacao, File arquivo, Integer cdLayout);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaValidacao(Connection con, LocalDate data);

	public StatusDto getStatusArquivosFilaValidacao(Connection con, LocalDate data);

	public List<TempoDto> getTemposFilaValidacao(Connection con, LocalDate data);

	public StatusDto getStatusArquivosFilaMovimentacao(Connection con, LocalDate data);

	public List<TempoDto> getTemposFilaCarregarCnab(Connection con, LocalDate data);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaCarregarCnab(Connection con, LocalDate data);

	public StatusDto getStatusArquivosFilaCarregarCnab(Connection con, LocalDate data);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaMovimentacao(Connection con, LocalDate data);

	public List<TempoDto> getTemposFilaMovimentacao(Connection con, LocalDate data);
	

}

package br.com.m3Tech.solucoesFromtis.service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.ArquivosPorMinutoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.StatusDto;
import br.com.m3Tech.solucoesFromtis.dto.TempoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IFilaService {
	
	
	public Long inserirFilaImportacaoArquivo(Base base, FundoDto fundo);

	public Integer inserirArquivoValidacao(Base base, Long idFilaImportacao, File arquivo, Integer cdLayout, String path);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaValidacao(Base base, LocalDate data);

	public StatusDto getStatusArquivosFilaValidacao(Base base, LocalDate data);

	public List<TempoDto> getTemposFilaValidacao(Base base, LocalDate data);

	public StatusDto getStatusArquivosFilaMovimentacao(Base base, LocalDate data);

	public List<TempoDto> getTemposFilaCarregarCnab(Base base, LocalDate data);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaCarregarCnab(Base base, LocalDate data);

	public StatusDto getStatusArquivosFilaCarregarCnab(Base base, LocalDate data);

	public ArquivosPorMinutoDto getArquivosPorMinutoFilaMovimentacao(Base base, LocalDate data);

	public List<TempoDto> getTemposFilaMovimentacao(Base base, LocalDate data);

	void updateFilaImportacaoArquivo(Base base, Long idFila);
	

}

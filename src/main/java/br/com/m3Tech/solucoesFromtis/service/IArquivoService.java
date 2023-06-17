package br.com.m3Tech.solucoesFromtis.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoImportadoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IArquivoService {
	
	
	public ArquivoDto inserirTbArquivo(Base base, FundoDto fundo, String nomeArquivo) throws Exception;

	public ArquivoDto findArquivoByNomeArquivo(Base base, String nomeArquivo) throws Exception;
	
	public boolean inserirTbArquivoImportado(Base base, List<String> cnab, ArquivoDto arquivo) throws Exception;

	public Map<Integer, ArquivoImportadoDto> getArquivoImportadoByData(Base base, LocalDate data);

	public void limparbase(Base base, LocalDate data);

	public List<String> findChassis(Base base);

}

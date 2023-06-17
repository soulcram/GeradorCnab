package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoJSONDto;
import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;


public interface ISimularImportacaoJson {
	
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto, Integer idFundo) throws Exception;

	public ArquivoJSONDto gerarUnico(ImportacaoSimuladaDto importacaoSimuladaDto, Integer fundoSelecionado) throws Exception;


}

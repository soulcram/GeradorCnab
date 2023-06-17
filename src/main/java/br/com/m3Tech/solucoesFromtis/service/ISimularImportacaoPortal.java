package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.ImportacaoSimuladaDto;


public interface ISimularImportacaoPortal {
	
	public void gerar(ImportacaoSimuladaDto importacaoSimuladaDto) throws Exception;


}

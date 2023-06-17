package br.com.m3Tech.solucoesFromtis.service;

import br.com.m3Tech.solucoesFromtis.dto.TabelaDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ITabelaService {
	
	
	public TabelaDto getTabela(Base base, String nomeTabela) throws Exception;

}

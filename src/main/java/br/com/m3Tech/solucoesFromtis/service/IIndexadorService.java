package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IIndexadorService {
	
	
	public List<IndexadorDto> findAll(Base base);
	
	public IndexadorDto findOneById(Base base, Integer id);


}

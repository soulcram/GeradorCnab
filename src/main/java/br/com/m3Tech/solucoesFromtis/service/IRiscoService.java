package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IRiscoService {
	
	
	public List<RiscoDto> findAll(Base base);
	
	public RiscoDto findOneById(Base base, Integer id);


}

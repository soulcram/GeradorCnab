package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;


public interface IRiscoService {
	
	
	public List<RiscoDto> findAll(Connection con);
	
	public RiscoDto findOneById(Connection con, Integer id);


}

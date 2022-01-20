package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;


public interface IIndexadorService {
	
	
	public List<IndexadorDto> findAll(Connection con);
	
	public IndexadorDto findOneById(Connection con, Integer id);


}

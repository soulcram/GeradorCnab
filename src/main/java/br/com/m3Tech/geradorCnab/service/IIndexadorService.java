package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.IndexadorDto;


public interface IIndexadorService {
	
	
	public List<IndexadorDto> findAll(Connection con);
	
	public IndexadorDto findOneById(Connection con, Integer id);


}

package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.RiscoDto;


public interface IRiscoService {
	
	
	public List<RiscoDto> findAll(Connection con);
	
	public RiscoDto findOneById(Connection con, Integer id);


}

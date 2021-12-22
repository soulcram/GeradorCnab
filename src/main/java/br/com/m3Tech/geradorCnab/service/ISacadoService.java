package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.SacadoDto;


public interface ISacadoService {
	
	
	public List<SacadoDto> findAll(Connection con, Integer idFundo);

	public SacadoDto findOneById(Connection con, Integer idSacado);

}

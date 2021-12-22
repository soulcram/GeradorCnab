package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.CedenteDto;


public interface ICedenteService {
	
	
	public List<CedenteDto> findAll(Connection con, Integer idFundo);

	public CedenteDto findOneById(Connection con, Integer idCedente);

}

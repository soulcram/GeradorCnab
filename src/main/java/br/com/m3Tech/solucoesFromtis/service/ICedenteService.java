package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;


public interface ICedenteService {
	
	
	public List<CedenteDto> findAll(Connection con, Integer idFundo);

	public CedenteDto findOneById(Connection con, Integer idCedente);
	
	public CedenteDto getPrimeiroCedente(Connection con, Integer idFundo) throws SQLException;

}

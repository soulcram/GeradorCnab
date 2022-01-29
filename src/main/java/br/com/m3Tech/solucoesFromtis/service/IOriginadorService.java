package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;


public interface IOriginadorService {
	
	
	public List<OriginadorDto> findAll(Connection con, Integer idFundo);

	public OriginadorDto findOneById(Connection con, Integer idOriginador);

	public OriginadorDto getPrimeiroOriginador(Connection con, Integer idFundo) throws SQLException;

}

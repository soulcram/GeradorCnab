package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;


public interface IOriginadorService {
	
	
	public List<OriginadorDto> findAll(Connection con, Integer idFundo);

	public OriginadorDto findOneById(Connection con, Integer idOriginador);

}

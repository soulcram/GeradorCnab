package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;


public interface ISacadoService {
	
	
	public List<SacadoDto> findAll(Connection con, Integer idFundo);

	public SacadoDto findOneById(Connection con, Integer idSacado);

}

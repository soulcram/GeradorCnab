package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;


public interface IBancoService {
	
	
	public List<BancoDto> findAll(Connection con);

	public BancoDto findOneById(Connection con, Integer idBanco);
	
	public BancoDto findOneByNumBanco(Connection connection, String nuBanco);

}

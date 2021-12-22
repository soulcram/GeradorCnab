package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.BancoDto;


public interface IBancoService {
	
	
	public List<BancoDto> findAll(Connection con);

	public BancoDto findOneById(Connection con, Integer idBanco);

}

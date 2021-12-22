package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.FundoDto;


public interface IFundoService {
	
	
	public List<FundoDto> findAll(Connection con);
	
	public FundoDto findOneById(Connection con, Integer id);

}

package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.TipoRecebivelDto;


public interface ITipoRecebivelService {
	
	
	public List<TipoRecebivelDto> findAllTipoRecebivel(Connection con, Integer cdLayout);

	public TipoRecebivelDto findOneTipoRecebivelById(Connection con, Integer idMovimento);
	

}

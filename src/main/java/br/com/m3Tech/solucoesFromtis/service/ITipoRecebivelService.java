package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;


public interface ITipoRecebivelService {
	
	
	public List<TipoRecebivelDto> findAllTipoRecebivel(Connection con, Integer cdLayout);

	public TipoRecebivelDto findOneTipoRecebivelById(Connection con, Integer idMovimento);

	public TipoRecebivelDto getPrimeiroTipoRecebivelAquisicao(Connection con, Integer cdLayout) throws SQLException;
	

}

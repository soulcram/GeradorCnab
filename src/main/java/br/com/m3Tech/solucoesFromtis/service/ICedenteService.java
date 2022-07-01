package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ICedenteService {
	
	
	public List<CedenteDto> findAll(Connection con, Integer idFundo, Base base);

	public CedenteDto findOneById(Connection con, Integer idCedente, Base base);
	
	public CedenteDto getPrimeiroCedente(Connection con, Integer idFundo, Base base) throws SQLException;

	public CedenteDto getCedenteByCpfCnpj(Connection connection, Integer idFundo, String cnpjCedente, Base base);

}

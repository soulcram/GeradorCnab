package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.dto.ColunaDto;
import br.com.m3Tech.solucoesFromtis.dto.TabelaDto;
import br.com.m3Tech.solucoesFromtis.service.ITabelaService;


@Service
public class TabelaServiceImpl implements ITabelaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public TabelaDto getTabela(Connection con, String nomeTabela) throws SQLException {
		
		TabelaDto retorno = new TabelaDto(nomeTabela, null);
		
		String sqlQuery = "SELECT COLUMN_NAME, IS_NULLABLE, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+nomeTabela+"'";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		ResultSet rs = ps.getResultSet();
		
		List<ColunaDto> lista = Lists.newArrayList();
		
		while(rs.next()) {
			lista.add(new ColunaDto(rs.getString("COLUMN_NAME"), 
                                     "YES".equals(rs.getString("IS_NULLABLE")) , 
                                     rs.getString("DATA_TYPE")
                                      ));
		}
		
		retorno.setColunas(lista);
		
		return retorno;
	}

}

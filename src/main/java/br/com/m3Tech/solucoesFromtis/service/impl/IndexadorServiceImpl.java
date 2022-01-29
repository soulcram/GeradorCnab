package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.IndexadorDto;
import br.com.m3Tech.solucoesFromtis.service.IIndexadorService;


@Service
public class IndexadorServiceImpl implements IIndexadorService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<IndexadorDto> findAll(Connection con) {
		
		List<IndexadorDto> indexadores = new ArrayList<IndexadorDto>();
		
		try {
			
			String sqlQuery = "SELECT ID_TIPO_INDEXADOR, CODIGO, NOME FROM TB_TIPO_INDEXADOR";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				
				IndexadorDto indexador = new IndexadorDto(rs.getInt("ID_TIPO_INDEXADOR"), 
											  rs.getInt("CODIGO"), 
											  rs.getString("NOME"));
				
				indexadores.add(indexador);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return indexadores;
	}

	public IndexadorDto findOneById(Connection con, Integer id) {
		IndexadorDto indexador = null;
		
		try {
			
			String sqlQuery = "SELECT ID_TIPO_INDEXADOR, CODIGO, NOME FROM TB_TIPO_INDEXADOR WHERE ID_TIPO_INDEXADOR = ?";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				indexador = new IndexadorDto(rs.getInt("ID_TIPO_INDEXADOR"), 
											  rs.getInt("CODIGO"), 
											  rs.getString("NOME"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return indexador;
	}

}

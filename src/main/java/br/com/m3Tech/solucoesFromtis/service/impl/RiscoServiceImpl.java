package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.RiscoDto;
import br.com.m3Tech.solucoesFromtis.service.IRiscoService;


@Service
public class RiscoServiceImpl implements IRiscoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<RiscoDto> findAll(Connection con) {
		
		List<RiscoDto> riscos = new ArrayList<RiscoDto>();
		
		try {
			
			String sqlQuery = "SELECT ID_CLASS_RISCO, CD_CLASS_RISCO, DS_CLASS_RISCO FROM TB_CLASS_RISCO";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				
				RiscoDto risco = new RiscoDto(rs.getInt("ID_CLASS_RISCO"), 
											  rs.getString("CD_CLASS_RISCO"), 
											  rs.getString("DS_CLASS_RISCO"));
				
				riscos.add(risco);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return riscos;
	}

	public RiscoDto findOneById(Connection con, Integer id) {
		RiscoDto risco = null;
		
		try {
			
			String sqlQuery = "SELECT ID_CLASS_RISCO, CD_CLASS_RISCO, DS_CLASS_RISCO FROM TB_CLASS_RISCO WHERE ID_CLASS_RISCO = ?";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				risco = new RiscoDto(rs.getInt("ID_CLASS_RISCO"), 
											  rs.getString("CD_CLASS_RISCO"), 
											  rs.getString("DS_CLASS_RISCO"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return risco;
	}

}

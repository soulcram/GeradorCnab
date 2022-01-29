package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;


@Service
public class CedenteServiceImpl implements ICedenteService, Serializable{

	private static final long serialVersionUID = 1L;


	public List<CedenteDto> findAll(Connection con, Integer idFundo) {
		
		List<CedenteDto> cedentes = new ArrayList<CedenteDto>();
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ALL_CEDENTES);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"));
				
				cedentes.add(cedente);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cedentes;
	}

	public CedenteDto findOneById(Connection con, Integer idCedente) {
		CedenteDto cedente = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_CEDENTE);
			
			ps.setInt(1, idCedente);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cedente;
	}
	
	


}

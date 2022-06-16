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
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;


@Service
public class CedenteServiceImpl implements ICedenteService, Serializable{

	private static final long serialVersionUID = 1L;


	public List<CedenteDto> findAll(Connection con, Integer idFundo, Base base) {
		
		List<CedenteDto> cedentes = new ArrayList<CedenteDto>();
		
		String coob =  base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";
		
		String sqlQuery =  "SELECT ID_CEDENTE, NM_CEDENTE, NU_CPF_CNPJ, "
				 + coob + " FROM TB_FUNDO_CEDENTE WHERE ID_FUNDO = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"),
											  base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"));
				
				cedentes.add(cedente);
			}
			
		} catch (SQLException e) {
			String sqlQuery2 =  "SELECT ID_CEDENTE, NM_CEDENTE, NU_CPF_CNPJ FROM TB_FUNDO_CEDENTE WHERE ID_FUNDO = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sqlQuery2);
				
				ps.setInt(1, idFundo);
				
				ps.execute();
				
				ResultSet rs = ps.getResultSet();
				
				while(rs.next()) {
					CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
												  rs.getString("NM_CEDENTE"), 
												  rs.getString("NU_CPF_CNPJ"),
												  "N");
					
					cedentes.add(cedente);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return cedentes;
	}

	public CedenteDto findOneById(Connection con, Integer idCedente, Base base) {
		CedenteDto cedente = null;
		
		String coob =  base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";
		
		String sqlQuery = " SELECT ID_CEDENTE, NM_CEDENTE, NU_CPF_CNPJ, " 
				+ coob +  " FROM TB_FUNDO_CEDENTE WHERE ID_CEDENTE = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idCedente);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"),
											  base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cedente;
	}

	@Override
	public CedenteDto getPrimeiroCedente(Connection con, Integer idFundo, Base base) throws SQLException {

		CedenteDto cedente = null;
		
		String coob =  base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";

		String sqlQuery = "select TOP 1 ID_CEDENTE, NM_CEDENTE, NU_CPF_CNPJ, "
				+ coob + " FROM TB_FUNDO_CEDENTE WHERE ID_FUNDO = " + idFundo;

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		if(rs.next()) {
			cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
					rs.getString("NM_CEDENTE"), 
					rs.getString("NU_CPF_CNPJ"), 
					base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"));

		}

		return cedente;
	}
	
	


}

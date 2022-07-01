package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;


@Service
public class OriginadorServiceImpl implements IOriginadorService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<OriginadorDto> findAll(Connection con, Integer idFundo) {
		
		List<OriginadorDto> originadores = new ArrayList<OriginadorDto>();
		
		String sqlQuery = " SELECT ID_FUNDO_ORIGINADOR, DS_CODIGO_CEDENTE,NM_PESSOA, NU_CPF_CNPJ\r\n" + 
				" FROM TB_FUNDO_ORIGINADOR FO \r\n" + 
				" INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n" + 
				" WHERE ID_FUNDO = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				OriginadorDto originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), 
											  rs.getString("DS_CODIGO_CEDENTE"),
											  rs.getString("NM_PESSOA"),
											  rs.getString("NU_CPF_CNPJ"));
				
				originadores.add(originador);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return originadores;
	}

	public OriginadorDto findOneById(Connection con, Integer idOriginador) {
		OriginadorDto originador = null;
		
		// String sqlQuery = "";
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_ORIGINADOR);
			
			ps.setInt(1, idOriginador);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), 
											  rs.getString("DS_CODIGO_CEDENTE"),
											  rs.getString("NM_PESSOA"),
											  rs.getString("NU_CPF_CNPJ"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return originador;
	}

	@Override
	public OriginadorDto getPrimeiroOriginador(Connection con, Integer idFundo) throws SQLException {
		OriginadorDto originador = null;

		String sqlQuery = "SELECT TOP 1 ID_FUNDO_ORIGINADOR, DS_CODIGO_CEDENTE,NM_PESSOA,NU_CPF_CNPJ\r\n"
				+ "FROM TB_FUNDO_ORIGINADOR FO \r\n" + "INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n"
				+ "WHERE ID_FUNDO = " + idFundo;
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		while (rs.next()) {
			originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), rs.getString("DS_CODIGO_CEDENTE"),
					rs.getString("NM_PESSOA"),rs.getString("NU_CPF_CNPJ"));

		}

		return originador;
	}
	
	


}

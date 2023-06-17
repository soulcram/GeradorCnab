package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;


@Service
public class OriginadorServiceImpl implements IOriginadorService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<OriginadorDto> findAll(Base base, Integer idFundo) {
		
		List<OriginadorDto> originadores = new ArrayList<OriginadorDto>();
		
		String sqlQuery = " SELECT ID_FUNDO_ORIGINADOR, DS_CODIGO_CEDENTE,NM_PESSOA, NU_CPF_CNPJ\r\n" + 
				" FROM TB_FUNDO_ORIGINADOR FO \r\n" + 
				" INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n" + 
				" WHERE ID_FUNDO = ? \r\n" + 
				"AND FO.DT_FIM_RELACIONAMENTO IS NULL \r\n";
		
		try {
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
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
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return originadores;
	}

	public OriginadorDto findOneById(Base base, Integer idOriginador) {
		OriginadorDto originador = null;
		
		// String sqlQuery = "";
		
		try {
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
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
			
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return originador;
	}

	@Override
	public OriginadorDto getPrimeiroOriginador(Base base, Integer idFundo) throws Exception {
		OriginadorDto originador = null;

		String sqlQuery = "SELECT TOP 1 ID_FUNDO_ORIGINADOR, DS_CODIGO_CEDENTE,NM_PESSOA,NU_CPF_CNPJ\r\n"
				+ "FROM TB_FUNDO_ORIGINADOR FO \r\n" + "INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n"
				+ "WHERE ID_FUNDO = " + idFundo + "\r\n "
				+ "AND FO.DT_FIM_RELACIONAMENTO IS NULL \r\n";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		while (rs.next()) {
			originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), rs.getString("DS_CODIGO_CEDENTE"),
					rs.getString("NM_PESSOA"),rs.getString("NU_CPF_CNPJ"));

		}
		con.close();
		return originador;
	}
	
	


}

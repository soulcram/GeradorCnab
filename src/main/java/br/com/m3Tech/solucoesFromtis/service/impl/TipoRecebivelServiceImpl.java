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

import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;


@Service
public class TipoRecebivelServiceImpl implements ITipoRecebivelService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<TipoRecebivelDto> findAllTipoRecebivel(Base base, Integer cdLayout) {
		
		List<TipoRecebivelDto> retorno = new ArrayList<TipoRecebivelDto>();
		
		String sqlQuery ="SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
				"WHERE LR.CD_LAYOUT = ?";
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				TipoRecebivelDto TipoRecebivel = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
											  			  rs.getString("ID_TIPO_ESPECIE"),
											  			  rs.getString("NM_TIPO_RECEBIVEL"));
				
				retorno.add(TipoRecebivel);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(retorno.isEmpty()) {
			retorno.add(new TipoRecebivelDto(null, "01", "Duplicata"));
		}
		
		return retorno;
	}
	
	@Override
	public TipoRecebivelDto findTipoRecebivel(Base base, Integer cdLayout, Integer especie) {
		
		
		String sqlQuery ="SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
				"WHERE LR.CD_LAYOUT = ? \r\n" +
				"AND LR.ID_TIPO_ESPECIE = ?";
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, cdLayout);
			ps.setInt(2, especie);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return  new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
											  			  rs.getString("ID_TIPO_ESPECIE"),
											  			  rs.getString("NM_TIPO_RECEBIVEL"));
				
				
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null	;
	}

	public TipoRecebivelDto findOneTipoRecebivelById(Base base, Integer idTipoRecebivel) {
		TipoRecebivelDto retorno = null;
		
		String sqlQuery = "SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
				"WHERE LR.ID_TIPO_RECEBIVEL = ?";
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idTipoRecebivel);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				retorno = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
						  					  rs.getString("ID_TIPO_ESPECIE"),
						  					  rs.getString("NM_TIPO_RECEBIVEL"));
				
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	public TipoRecebivelDto getPrimeiroTipoRecebivelAquisicao(Base base, Integer cdLayout) throws Exception {
		TipoRecebivelDto retorno = null;

		String sqlQuery = "SELECT DISTINCT TOP 1 TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n"
				+ "FROM TB_LAYOUT_RECEBIVEL LR\r\n"
				+ "INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n"
				+ "WHERE LR.CD_LAYOUT = " + cdLayout;


		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		if (rs.next()) {
			retorno = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), rs.getString("ID_TIPO_ESPECIE"),
					rs.getString("NM_TIPO_RECEBIVEL"));

		}
		con.close();
		return retorno;
	}

}

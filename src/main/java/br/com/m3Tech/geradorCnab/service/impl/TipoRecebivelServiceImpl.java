package br.com.m3Tech.geradorCnab.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.TipoRecebivelDto;
import br.com.m3Tech.geradorCnab.service.ITipoRecebivelService;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class TipoRecebivelServiceImpl implements ITipoRecebivelService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<TipoRecebivelDto> findAllTipoRecebivel(Connection con, Integer cdLayout) {
		
		List<TipoRecebivelDto> retorno = new ArrayList<TipoRecebivelDto>();
		
		String sqlQuery ="SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
				"WHERE LR.CD_LAYOUT = ?";
		
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(retorno.isEmpty()) {
			retorno.add(new TipoRecebivelDto(null, "01", "Duplicata"));
		}
		
		return retorno;
	}

	public TipoRecebivelDto findOneTipoRecebivelById(Connection con, Integer idTipoRecebivel) {
		TipoRecebivelDto retorno = null;
		
		String sqlQuery = "SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
				"WHERE LR.ID_TIPO_RECEBIVEL = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idTipoRecebivel);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				retorno = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
						  					  rs.getString("ID_TIPO_ESPECIE"),
						  					  rs.getString("NM_TIPO_RECEBIVEL"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

}

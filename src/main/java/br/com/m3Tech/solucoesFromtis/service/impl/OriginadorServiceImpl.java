package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IOriginadorService;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class OriginadorServiceImpl implements IOriginadorService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<OriginadorDto> findAll(Connection con, Integer idFundo) {
		
		List<OriginadorDto> originadores = new ArrayList<OriginadorDto>();
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ALL_ORIGINADORES);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				OriginadorDto originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), 
											  rs.getString("DS_CODIGO_CEDENTE"),
											  rs.getString("NM_PESSOA"));
				
				originadores.add(originador);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return originadores;
	}

	public OriginadorDto findOneById(Connection con, Integer idOriginador) {
		OriginadorDto originador = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_ORIGINADOR);
			
			ps.setInt(1, idOriginador);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				originador = new OriginadorDto(rs.getInt("ID_FUNDO_ORIGINADOR"), 
											  rs.getString("DS_CODIGO_CEDENTE"),
											  rs.getString("NM_PESSOA"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return originador;
	}
	
	


}

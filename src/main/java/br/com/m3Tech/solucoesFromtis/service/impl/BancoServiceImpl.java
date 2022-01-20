package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BancoServiceImpl implements IBancoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<BancoDto> findAll(Connection con) {
		
		List<BancoDto> bancos = new ArrayList<BancoDto>();
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ALL_BANCOS);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				BancoDto banco = new BancoDto(rs.getInt("ID_BANCO"), 
											  rs.getString("NU_BANCO"),
											  rs.getString("NM_BANCO"));
				
				bancos.add(banco);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bancos;
	}

	public BancoDto findOneById(Connection con, Integer idBanco) {
		BancoDto banco = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_BANCO);
			
			ps.setInt(1, idBanco);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				banco = new BancoDto(rs.getInt("ID_BANCO"), 
											  rs.getString("NU_BANCO"),
											  rs.getString("NM_BANCO"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return banco;
	}
	
	


}

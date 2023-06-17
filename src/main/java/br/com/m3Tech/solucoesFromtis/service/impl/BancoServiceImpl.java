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

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IBancoService;


@Service
public class BancoServiceImpl implements IBancoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<BancoDto> findAll(Base base) {
		
		List<BancoDto> bancos = new ArrayList<BancoDto>();
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ALL_BANCOS);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				BancoDto banco = new BancoDto(rs.getInt("ID_BANCO"), 
											  rs.getString("NU_BANCO"),
											  rs.getString("NM_BANCO"));
				
				bancos.add(banco);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return bancos;
	}

	public BancoDto findOneById(Base base, Integer idBanco) {
		BancoDto banco = null;
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ONE_BANCO);
			
			ps.setInt(1, idBanco);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				banco = new BancoDto(rs.getInt("ID_BANCO"), 
											  rs.getString("NU_BANCO"),
											  rs.getString("NM_BANCO"));
				
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return banco;
	}

	@Override
	public BancoDto findOneByNumBanco(Base base, String nuBanco) {
		BancoDto banco = null;
		
		try {
			String query = "SELECT ID_BANCO, NM_BANCO, NU_BANCO \r\n" + 
					" FROM TB_BANCO \r\n" + 
					" WHERE NU_BANCO = '"+nuBanco+"'";
			

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			
			PreparedStatement ps = con.prepareStatement(query);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				banco = new BancoDto(rs.getInt("ID_BANCO"), 
											  rs.getString("NU_BANCO"),
											  rs.getString("NM_BANCO"));
				
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return banco;
	}
	
	


}

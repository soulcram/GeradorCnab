package br.com.m3Tech.solucoesFromtis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.m3Tech.solucoesFromtis.model.Base;


public class Conexao {
	
	private static Connection con;
	
	private Conexao() {
	}
	
	public static Connection getConnection(Base base) throws Exception {
		
		try {
			
			if(con != null) {
				con.close();
			}
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		}
		
		return con;
		
	}

}

package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;


@Service
public class SacadoServiceImpl implements ISacadoService, Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SacadoServiceImpl.class);

	public List<SacadoDto> findAll(Base base, Integer idFundo) {
		
		List<SacadoDto> sacados = new ArrayList<SacadoDto>();
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ALL_SACADOS);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				SacadoDto sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
											  rs.getString("NM_SACADO"), 
											  rs.getString("NU_CPF_CNPJ"),
											  rs.getString("DS_LOGRADOURO"),
											  rs.getString("NU_CEP"));
				
				sacados.add(sacado);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sacados;
	}

	public SacadoDto findOneById(Base base, Integer idSacado) {
		SacadoDto sacado = null;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ONE_SACADO);
			
			ps.setInt(1, idSacado);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
											  rs.getString("NM_SACADO"), 
											  rs.getString("NU_CPF_CNPJ"),
											  rs.getString("DS_LOGRADOURO"),
											  rs.getString("NU_CEP"));
				
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sacado;
	}

	@Override
	public SacadoDto getPrimeiroSacado(Base base, Integer idFundo) throws Exception {
		SacadoDto sacado = null;

		String sqlQuery = "select TOP 1 ID_SACADO, NM_SACADO, NU_CPF_CNPJ,DS_LOGRADOURO,NU_CEP from TB_FUNDO_SACADO where ID_FUNDO = " + idFundo;
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		if (rs.next()) {
			sacado = new SacadoDto(rs.getInt("ID_SACADO"), rs.getString("NM_SACADO"), rs.getString("NU_CPF_CNPJ"),
					rs.getString("DS_LOGRADOURO"), rs.getString("NU_CEP"));

		}
		con.close();
		return sacado;
	}
	
	@Override
	public void deleteByIdSacado(Base base,Integer idSacado) {

		logger.info("deletando cedente. {}",idSacado);
		String sqlQuery = "DECLARE @ID_SACADO INT = "+idSacado+"\r\n"
				+ "DECLARE @ID_REPRESENTANTE INT\r\n"
				+ "\r\n"
				+ "SELECT @ID_REPRESENTANTE = ID_REPRESENTANTE FROM TB_ASSOC_SACADO_REPRESENTANTE WHERE ID_SACADO = @ID_SACADO\r\n"
				+ "\r\n"
				+ "DELETE TB_ASSOC_SACADO_REPRESENTANTE WHERE ID_SACADO = @ID_SACADO\r\n"
				+ "DELETE TB_REPRESENTANTE WHERE ID_REPRESENTANTE = @ID_REPRESENTANTE\r\n"
				+ "DELETE TB_FUNDO_SACADO WHERE ID_SACADO = @ID_SACADO";
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	@Override
	public SacadoDto getSacadoByCpfCnpj(Base base, Integer idFundo, String docSacado) {
		SacadoDto sacado = null;


		String sqlQuery = "select TOP 1 ID_SACADO, NM_SACADO, NU_CPF_CNPJ,DS_LOGRADOURO,NU_CEP" 
		        + "	FROM TB_FUNDO_SACADO FS  \r\n"
				+ " WHERE ID_FUNDO = " + idFundo + " AND NU_CPF_CNPJ = '" + docSacado +"'";
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
									   rs.getString("NM_SACADO"),
									   rs.getString("NU_CPF_CNPJ"),
									   rs.getString("DS_LOGRADOURO"),
									   rs.getString("NU_CEP"));

			}
			con.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return sacado;
	}
	


}

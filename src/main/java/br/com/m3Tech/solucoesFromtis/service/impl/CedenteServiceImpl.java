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

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.ICedenteService;


@Service
public class CedenteServiceImpl implements ICedenteService, Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CedenteServiceImpl.class);


	public List<CedenteDto> findAll(Base base, Integer idFundo) {
		
		List<CedenteDto> cedentes = new ArrayList<CedenteDto>();
		
		String coob =  base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";
		
		String sqlQuery = "SELECT FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,\r\n"
				+ coob 
				+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
				+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
				+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
				+ "	WHERE ID_FUNDO = ?"  ;
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"),
											  base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"),
											  rs.getString("NU_BANCO"),
											  rs.getString("NU_AGENCIA"),
											  rs.getString("DIGITO_AG"),
											  rs.getString("NU_CONTA"),
											  rs.getString("DIGITO_CONTA"));
				
				cedentes.add(cedente);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			String sqlQuery2 =  "SELECT FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA\r\n"
					+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
					+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
					+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
					+ "	WHERE ID_FUNDO = ?" ;
			
			try {
				

				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
				
				PreparedStatement ps = con.prepareStatement(sqlQuery2);
				
				ps.setInt(1, idFundo);
				
				ps.execute();
				
				ResultSet rs = ps.getResultSet();
				
				while(rs.next()) {
					CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
												  rs.getString("NM_CEDENTE"), 
												  rs.getString("NU_CPF_CNPJ"),
												  "N",
												  rs.getString("NU_BANCO"),
												  rs.getString("NU_AGENCIA"),
												  rs.getString("DIGITO_AG"),
												  rs.getString("NU_CONTA"),
												  rs.getString("DIGITO_CONTA"));
					
					cedentes.add(cedente);
				}
				con.close();
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		return cedentes;
	}

	public CedenteDto findOneById(Base base, Integer idCedente) {
		CedenteDto cedente = null;
		
		String coob =  base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";
		
		String sqlQuery = " SELECT FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA, " 
				+ coob 
				+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
				+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
				+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
				+ "	WHERE FC.ID_CEDENTE = ?"  ;
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, idCedente);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
											  rs.getString("NM_CEDENTE"), 
											  rs.getString("NU_CPF_CNPJ"),
											  base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"),
													  rs.getString("NU_BANCO"),
													  rs.getString("NU_AGENCIA"),
													  rs.getString("DIGITO_AG"),
													  rs.getString("NU_CONTA"),
													  rs.getString("DIGITO_CONTA"));
				
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			String sqlQuery2 = "SELECT FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,\r\n"
					+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
					+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
					+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
					+ " WHERE ID_CEDENTE = ?";
			
			try {

				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
				
				PreparedStatement ps = con.prepareStatement(sqlQuery2);
				
				ps.setInt(1, idCedente);
				
				ps.execute();
				
				ResultSet rs = ps.getResultSet();
				
				while(rs.next()) {
					cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
												  rs.getString("NM_CEDENTE"), 
												  rs.getString("NU_CPF_CNPJ"),
												  "N",
												  rs.getString("NU_BANCO"),
												  rs.getString("NU_AGENCIA"),
												  rs.getString("DIGITO_AG"),
												  rs.getString("NU_CONTA"),
												  rs.getString("DIGITO_CONTA"));
					
				}
				
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		return cedente;
	}
	
	@Override
	public void deleteByIdCedente(Base base,Integer idCedente) {

		logger.info("deletando cedente. {}",idCedente);
		String sqlQuery = "DECLARE @ID_CEDENTE INT = "+idCedente+"\r\n"
				+ "DECLARE @ID_REPRESENTANTE INT\r\n"
				+ "\r\n"
				+ "SELECT @ID_REPRESENTANTE = ID_REPRESENTANTE FROM TB_ASSOC_CEDENTE_REPRESENTANTE WHERE ID_CEDENTE = @ID_CEDENTE\r\n"
				+ "\r\n"
				+ "DELETE TB_CONTA_CORRENTE WHERE ID_CEDENTE = @ID_CEDENTE\r\n"
				+ "DELETE TB_ASSOC_CEDENTE_REPRESENTANTE WHERE ID_CEDENTE = @ID_CEDENTE\r\n"
				+ "DELETE TB_REPRESENTANTE WHERE ID_REPRESENTANTE = @ID_REPRESENTANTE\r\n"
				+ "DELETE TB_FUNDO_CEDENTE WHERE ID_CEDENTE = @ID_CEDENTE";
		
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
	public CedenteDto getPrimeiroCedente(Base base, Integer idFundo) throws Exception {

		CedenteDto cedente = null;

		String coob = base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";

		String sqlQuery = "select TOP 1 FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA," 
		        + coob
				+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
				+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
				+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
				+ " WHERE ID_FUNDO = " + idFundo;
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), rs.getString("NM_CEDENTE"),
						rs.getString("NU_CPF_CNPJ"),
						base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"),
								rs.getString("NU_BANCO"),
								  rs.getString("NU_AGENCIA"),
								  rs.getString("DIGITO_AG"),
								  rs.getString("NU_CONTA"),
								  rs.getString("DIGITO_CONTA"));

			}
			con.close();
		} catch (Exception e) {
			try {

				String sqlQuery2 = "SELECT FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,\r\n"
						+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
						+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
						+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
						+ "	WHERE ID_FUNDO = " + idFundo;

				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
				
				PreparedStatement ps = con.prepareStatement(sqlQuery2);

				ps.execute();

				ResultSet rs = ps.getResultSet();

				if (rs.next()) {
					cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
							                 rs.getString("NM_CEDENTE"),
							                 rs.getString("NU_CPF_CNPJ"), 
							                 "N",
							                 rs.getString("NU_BANCO"),
											  rs.getString("NU_AGENCIA"),
											  rs.getString("DIGITO_AG"),
											  rs.getString("NU_CONTA"),
											  rs.getString("DIGITO_CONTA"));

				}
				con.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return cedente;
	}

	@Override
	public CedenteDto getCedenteByCpfCnpj(Base base, Integer idFundo, String cnpjCedente) {
		CedenteDto cedente = null;

		String coob = base.getVersaoMercado() ? "TP_COOBRIGACAO \r\n" : "IC_COOBRIGACAO \r\n";

		String sqlQuery = "select TOP 1 FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA, " 
		        + coob
		        + "	FROM TB_FUNDO_CEDENTE FC  \r\n"
				+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
				+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
				+ " WHERE ID_FUNDO = " + idFundo + " AND NU_CPF_CNPJ = '" + cnpjCedente +"'";
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), rs.getString("NM_CEDENTE"),
						rs.getString("NU_CPF_CNPJ"),
						base.getVersaoMercado() ? rs.getString("TP_COOBRIGACAO") : rs.getString("IC_COOBRIGACAO"),
								  rs.getString("NU_BANCO"),
								  rs.getString("NU_AGENCIA"),
								  rs.getString("DIGITO_AG"),
								  rs.getString("NU_CONTA"),
								  rs.getString("DIGITO_CONTA"));

			}
			con.close();
		} catch (Exception e) {
			try {

				String sqlQuery2 = "SELECT TOP 1 FC.ID_CEDENTE, FC.NM_CEDENTE, FC.NU_CPF_CNPJ, B.NU_BANCO, CC.NU_AGENCIA,CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA\r\n"
						+ "	FROM TB_FUNDO_CEDENTE FC  \r\n"
						+ "	LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 ) \r\n"
						+ "	LEFT JOIN TB_BANCO B ON (B.ID_BANCO = CC.ID_BANCO) \r\n"
						+ " WHERE ID_FUNDO = " + idFundo + " AND NU_CPF_CNPJ = '" + cnpjCedente +"'";

				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
				
				PreparedStatement ps = con.prepareStatement(sqlQuery2);

				ps.execute();

				ResultSet rs = ps.getResultSet();

				if (rs.next()) {
					cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
							                 rs.getString("NM_CEDENTE"),
							                 rs.getString("NU_CPF_CNPJ"), 
							                 "N",
							                 rs.getString("NU_BANCO"),
											  rs.getString("NU_AGENCIA"),
											  rs.getString("DIGITO_AG"),
											  rs.getString("NU_CONTA"),
											  rs.getString("DIGITO_CONTA"));

				}
				con.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return cedente;
	}
	
	


}

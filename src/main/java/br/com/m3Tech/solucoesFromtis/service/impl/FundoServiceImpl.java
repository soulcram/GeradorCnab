package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.FundoCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.util.CpfCnpjUtils;


@Service
public class FundoServiceImpl implements IFundoService, Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FundoServiceImpl.class);

	public List<FundoDto> findAll(Base base) {
		
		List<FundoDto> fundos = new ArrayList<FundoDto>();
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ALL_FUNDOS);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getString("NU_CNPJ"),
											  rs.getString("CODIGO_ISIN"),
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
				fundos.add(fundo);
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fundos;
	}
	
	@Override
	public List<FundoDto> findAllComDataAtual(Base base) {
		
		List<FundoDto> fundos = new ArrayList<FundoDto>();
		
		String sqlQuery = " SELECT ID_FUNDO, NM_FUNDO, NU_CNPJ, CODIGO_ISIN, DT_FUNDO, LAYOUT_AQUISICAO  FROM TB_FUNDO WHERE CONVERT(DATE,DT_FUNDO) = '" + LocalDate.now() + "'\r\n";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getString("NU_CNPJ"),
											  rs.getString("CODIGO_ISIN"),
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
				fundos.add(fundo);
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fundos;
	}
	
	@Override
	public List<FundoDto> findAllProrrogacao(Base base) {
		
		List<FundoDto> fundos = new ArrayList<FundoDto>();
		
		try {
			
			String sqlQuery = "SELECT ID_FUNDO, NM_FUNDO, NU_CNPJ, CODIGO_ISIN, DT_FUNDO, LAYOUT_AQUISICAO\r\n" + 
					"FROM TB_FUNDO F\r\n" + 
					"WHERE F.IC_PERMITIR_PRORROGACAO = 1";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getString("NU_CNPJ"),
											  rs.getString("CODIGO_ISIN"),
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
				fundos.add(fundo);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fundos;
	}

	@Override
	public FundoDto findOneById(Base base, Integer id) {
		FundoDto fundo = null;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ONE_FUNDO);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getString("NU_CNPJ"),
											  rs.getString("CODIGO_ISIN"),
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fundo;
	}
	
	@Override
	public FundoDto findFundoByCnpj(Base base, String cnpj) {
		FundoDto fundo = null;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			String sqlQuery = " SELECT ID_FUNDO, NM_FUNDO, NU_CNPJ, CODIGO_ISIN, DT_FUNDO, LAYOUT_AQUISICAO \r\n" + 
				    " FROM TB_FUNDO WHERE NU_CNPJ = '" + CpfCnpjUtils.removerFormatacao(cnpj) +"'";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getString("NU_CNPJ"),
											  rs.getString("CODIGO_ISIN"),
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fundo;
	}
	
	@Override
	public List<FundoCobrancaDto> findCodCobrancas(Base base, Integer id) {
		List<FundoCobrancaDto> retorno = new ArrayList<>();
		
		try {
			
			String query = "SELECT DISTINCT CD_COBRANCA, B.NU_BANCO, ID_FUNDO\r\n" + 
					"FROM TB_FUNDO_COBRANCA FC\r\n" + 
					"INNER JOIN TB_BANCO B ON B.ID_BANCO = FC.ID_BANCO\r\n" + 
					"WHERE (DT_FIM_RELACIONAMENTO IS NULL OR DT_FIM_RELACIONAMENTO > GETDATE())\r\n" + 
					"AND DT_INI_RELACIONAMENTO <= GETDATE()\r\n" + 
					"AND ID_FUNDO = " + id;
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {

				FundoCobrancaDto cobranca = new FundoCobrancaDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("CD_COBRANCA"), 
											  rs.getString("NU_BANCO"));
				
				retorno.add(cobranca);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	public Integer findDiasMaxProrrogacao(Base base, Integer idFundo) {

		Integer retorno = null;
		
		String sqlQuery = "SELECT PZ_MAX_ALTERACAO_DT_VCTO FROM TB_FUNDO WHERE ID_FUNDO = " + idFundo;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				retorno = rs.getInt("PZ_MAX_ALTERACAO_DT_VCTO");
				
				
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@Override
	public void deleteByIdFundo(Base base,Integer idFundo) {

		logger.info("deletando fundo. {}",idFundo);
		String sqlQuery = "DECLARE @ID_FUNDO INT = "+idFundo+"\r\n"

				+ "DECLARE @ID_REPRESENTANTE INT\r\n"
				+ "\r\n"
				+ "SELECT @ID_REPRESENTANTE = ID_REPRESENTANTE FROM TB_ASSOC_FUNDO_REPRESENTANTE WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "\r\n"
				+ "DELETE TB_ASSOC_FUNDO_REPRESENTANTE WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_REPRESENTANTE WHERE ID_REPRESENTANTE = @ID_REPRESENTANTE\r\n"
				+ "DELETE TB_ATIVO WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_CARTEIRA_SAC WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_SEG_PERMISSAO_FUNDO WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_FUNDO_COBRANCA WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_FUNDO_ORIGINADOR WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "DELETE TB_FUNDO_PERFIL_PDD WHERE ID_FUNDO = @ID_FUNDO\r\n"
				+ "\r\n"
				+ "DELETE TB_FUNDO WHERE ID_FUNDO = @ID_FUNDO";
		
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


}

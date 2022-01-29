package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;


@Service
public class FundoServiceImpl implements IFundoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<FundoDto> findAll(Connection con) {
		
		List<FundoDto> fundos = new ArrayList<FundoDto>();
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ALL_FUNDOS);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
				fundos.add(fundo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fundos;
	}
	
	@Override
	public List<FundoDto> findAllProrrogacao(Connection con) {
		
		List<FundoDto> fundos = new ArrayList<FundoDto>();
		
		try {
			
			String sqlQuery = "SELECT ID_FUNDO, NM_FUNDO, DT_FUNDO, LAYOUT_AQUISICAO\r\n" + 
					"FROM TB_FUNDO F\r\n" + 
					"WHERE F.IC_PERMITIR_PRORROGACAO = 1";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
				fundos.add(fundo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fundos;
	}

	public FundoDto findOneById(Connection con, Integer id) {
		FundoDto fundo = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_FUNDO);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				String dataFundo = rs.getString("DT_FUNDO");
				
				fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
											  rs.getString("NM_FUNDO"), 
											  rs.getInt("LAYOUT_AQUISICAO"),
											  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fundo;
	}

	@Override
	public Integer findDiasMaxProrrogacao(Connection con, Integer idFundo) {

		Integer retorno = null;
		
		String sqlQuery = "SELECT PZ_MAX_ALTERACAO_DT_VCTO FROM TB_FUNDO WHERE ID_FUNDO = " + idFundo;
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);
						
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				
				retorno = rs.getInt("PZ_MAX_ALTERACAO_DT_VCTO");
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	


}

package br.com.m3Tech.geradorCnab.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.FundoDto;
import br.com.m3Tech.geradorCnab.querys.Querys;
import br.com.m3Tech.geradorCnab.service.IFundoService;
import lombok.NoArgsConstructor;


@NoArgsConstructor
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
	
	


}

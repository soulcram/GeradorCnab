package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.ISacadoService;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class SacadoServiceImpl implements ISacadoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<SacadoDto> findAll(Connection con, Integer idFundo) {
		
		List<SacadoDto> sacados = new ArrayList<SacadoDto>();
		
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sacados;
	}

	public SacadoDto findOneById(Connection con, Integer idSacado) {
		SacadoDto sacado = null;
		
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sacado;
	}
	
	


}

package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;

@Service
public class ConfGlobalServiceImpl implements IConfGlobalService{

	@Override
	public ConfGlobal getConfGlobal() {
		
		try {
			List<ConfGlobal> confGlobais;

			confGlobais = new ConfGlobal().findAll();

			String pathPadrao = "C:\\GeradorCnab\\" + LocalDate.now().toString().replaceAll("-", "") + "\\";

			ConfGlobal confGlobal;

			if (confGlobais == null || confGlobais.isEmpty()) {
				confGlobal = new ConfGlobal(1, pathPadrao);
			} else {
				confGlobal = confGlobais.get(0);
			}

			if (StringUtils.EmptyOrNull(confGlobal.getPath())) {
				confGlobal.setPath(pathPadrao);
			}

			return confGlobal;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public String getPathRepositorio(Connection con) {
		
		
		try {
			
			String sqlQuery = "SELECT TOP 1 DS_PATH_REPOSITORIO FROM TB_GLOBAL_CONFIG";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getString("DS_PATH_REPOSITORIO");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	@Override
	public String getPathSalvarArquivo(Connection con, Boolean importacaoAutomatica, Boolean versaoMercado, FundoDto fundo) {
		if(importacaoAutomatica) {
			
			String pathRepositorio = getPathRepositorio(con);
			
			if(versaoMercado != null && versaoMercado) {
					return pathRepositorio + File.separator + "ENCONTRADOR_ARQUIVO";
				
			}else {
				
				return pathRepositorio 
						+ File.separator 
						+ fundo.getCodigoIsin()
						+ File.separator
						+ "REMESSA"
						+ File.separator
						+ fundo.getDataFundo().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
											
			}
			
		}else {
			return getConfGlobal().getPath();
		}
	}


}

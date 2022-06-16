package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;


@Service
public class FilaServiceImpl implements IFilaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Long inserirFilaImportacaoArquivo(Connection con, FundoDto fundo) {
		
		String sqlQuery = "INSERT INTO TB_FILA_IMPORTACAO_ARQUIVO\r\n" + 
				"           ([DT_ENTRADA]\r\n" + 
				"           ,[DT_INICIO_IMPORTACAO]\r\n" + 
				"           ,[ID_FUNDO]\r\n" + 
				"           ,[NM_USUARIO]\r\n" + 
				"           ,[STATUS_FILA_IMPORTACAO]\r\n" + 
				"           ,[IC_TEM_PRIORIDADE]\r\n" + 
				"           ,[DT_FIM_IMPORTACAO])\r\n" + 
				"     VALUES\r\n" + 
				"           ('" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) +"'\r\n" + 
				"           , null \r\n" + 
				"           , " + fundo.getIdFundo() +"\r\n" + 
				"           ,'sistema'\r\n" + 
				"           ,'AGUARDANDO'\r\n" + 
				"           ,0 \r\n" + 
				"           ,null)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = ps.executeUpdate();
			
	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Integer inserirArquivoValidacao(Connection con, Long idFilaImportacao, File arquivo, Integer cdLayout) {
		String sqlQuery = "INSERT INTO [dbo].[TB_ARQUIVO_VALIDACAO]\r\n" + 
				"           ([DADOS_ARQUIVO]\r\n" + 
				"           ,[CD_LAYOUT]\r\n" + 
				"           ,[MIME_TYPE]\r\n" + 
				"           ,[NM_ARQUIVO]\r\n" + 
				"           ,[STATUS_ARQUIVO]\r\n" + 
				"           ,[TAMANHO_ARQUIVO]\r\n" + 
				"           ,[ID_FILA_IMPORTACAO_ARQUIVO]\r\n" + 
				"           ,[MENSAGEM]\r\n" + 
				"           ,[DT_INICIO_VALIDACAO]\r\n" + 
				"           ,[DT_FIM_VALIDACAO]\r\n" + 
				"           ,[ID_ARQUIVO]\r\n" + 
				"           ,[CAMINHO_ARQUIVO]\r\n" + 
				"           ,[NM_ARQUIVO_FINAL]\r\n" + 
				"           ,[CAMINHO_ARQUIVO_LASTRO]\r\n" + 
				"           ,[ID_ARQUIVO_CNAB240])\r\n" + 
				"     VALUES\r\n" + 
				"           (null\r\n" + 
				"           ,"+cdLayout+"\r\n" + 
				"           ,null\r\n" + 
				"           ,'"+arquivo.getName()+"'\r\n" + 
				"           ,'AGUARDANDO'\r\n" + 
				"           ,"+arquivo.length()+"\r\n" + 
				"           ,"+idFilaImportacao+"\r\n" + 
				"           ,null \r\n" + 
				"           ,'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) +"'\r\n" + 
				"           ,null \r\n" + 
				"           ,null \r\n" + 
				"           ,'"+arquivo.getPath()+"'\r\n" + 
				"           ,null\r\n" + 
				"           ,null \r\n" + 
				"           ,null )";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = ps.executeUpdate();
			
			return affectedRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	
	


}

package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.ArquivosPorMinutoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.StatusDto;
import br.com.m3Tech.solucoesFromtis.dto.TempoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IFilaService;


@Service
public class FilaServiceImpl implements IFilaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Long inserirFilaImportacaoArquivo(Base base, FundoDto fundo) {
		
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
				"           ,'ATRIBUINDO_ARQUIVOS'\r\n" + 
				"           ,0 \r\n" + 
				"           ,null)";
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha())) {

			

			PreparedStatement ps = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			
	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	
	            	return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	            
	        }		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void updateFilaImportacaoArquivo(Base base, Long idFila) {
		
		String sqlQuery = "UPDATE TB_FILA_IMPORTACAO_ARQUIVO SET STATUS_FILA_IMPORTACAO = 'AGUARDANDO' WHERE ID_FILA_IMPORTACAO_ARQUIVO = " + idFila;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha())) {

			

			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer inserirArquivoValidacao(Base base, Long idFilaImportacao, File arquivo, Integer cdLayout, String path) {
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
				"           ,'"+(path+arquivo.getName())+"'\r\n" + 
				"           ,null\r\n" + 
				"           ,null \r\n" + 
				"           ,null )";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = ps.executeUpdate();
			con.close();
			return affectedRows;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	@Override
	public StatusDto getStatusArquivosFilaValidacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT  STATUS_FILA_IMPORTACAO  ,COUNT(*) AS QUANT\r\n" + 
				"FROM TB_FILA_IMPORTACAO_ARQUIVO AI WITH(NOLOCK)\r\n" + 
				"WHERE CONVERT(DATE,AI.DT_ENTRADA) = '" + data.toString()+ "'\r\n" + 
				"GROUP BY STATUS_FILA_IMPORTACAO";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			StatusDto retorno = new StatusDto();
			
			while(rs.next()) {
				 String status = rs.getString("STATUS_FILA_IMPORTACAO");
				 
				 if(status.equals("AGUARDANDO")) {
					 retorno.setAguardando(rs.getInt("QUANT"));
				 }else if(status.equals("ENVIADO")) {
					 retorno.setEnviado(rs.getInt("QUANT"));
				 }else if(status.equals("INVALIDO")) {
					 retorno.setInvalido(rs.getInt("QUANT"));
				 }else if(status.equals("VALIDADO")) {
					 retorno.setValidado(rs.getInt("QUANT"));
				 }
			}
			con.close();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new StatusDto();
		
	}
	
	@Override
	public ArquivosPorMinutoDto getArquivosPorMinutoFilaValidacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT MIN(PROCESSADOS) AS MINIMO, MAX(PROCESSADOS) AS MAXIMO , AVG(PROCESSADOS) AS MEDIA\r\n" + 
				"FROM(\r\n" + 
				"     SELECT LEFT(CONVERT(TIME,A.DT_FIM_VALIDACAO),5) AS HORARIO, COUNT(1) PROCESSADOS\r\n" + 
				"     FROM TB_ARQUIVO_VALIDACAO  A WITH(NOLOCK)\r\n" + 
				"     INNER JOIN TB_FILA_IMPORTACAO_ARQUIVO AI WITH(NOLOCK) ON A.ID_FILA_IMPORTACAO_ARQUIVO = AI.ID_FILA_IMPORTACAO_ARQUIVO\r\n" + 
				"     WHERE CONVERT(DATE,AI.DT_ENTRADA) = '" + data.toString()+ "'\r\n" + 
				"     AND DT_FIM_VALIDACAO IS NOT NULL\r\n" + 
				"     GROUP BY LEFT(CONVERT(TIME,A.DT_FIM_VALIDACAO),5) \r\n" + 
				"	 ) AS X";
		
		
		ArquivosPorMinutoDto retorno = new ArquivosPorMinutoDto();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			if(rs.next()) {
				retorno.setMinimo(rs.getInt("MINIMO"));
				retorno.setMaximo(rs.getInt("MAXIMO"));
				retorno.setMedia(rs.getInt("MEDIA"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public List<TempoDto> getTemposFilaValidacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT \r\n"
				+ "       CASE WHEN DATEDIFF(SS,DT_FIM_IMPORTACAO_2, DT_FIM_IMPORTACAO) < 0 \r\n"
				+ "	        THEN DATEDIFF(SS,DT_INICIO_IMPORTACAO, DT_FIM_IMPORTACAO) \r\n"
				+ "			ELSE DATEDIFF(SS,DT_FIM_IMPORTACAO_2, DT_FIM_IMPORTACAO)\r\n"
				+ "			END  AS TEMPO,COUNT(*) AS PROCESSADOS\r\n"
				+ "FROM(\r\n"
				+ "SELECT *, \r\n"
				+ "CASE WHEN DT_INICIO_IMPORTACAO > COALESCE(LAG(DT_FIM_IMPORTACAO,1,NULL) OVER (ORDER BY ID_FILA_IMPORTACAO_ARQUIVO ASC) , DT_INICIO_IMPORTACAO)\r\n"
				+ "THEN DT_INICIO_IMPORTACAO\r\n"
				+ "ELSE  COALESCE(LAG(DT_FIM_IMPORTACAO,1,NULL) OVER (ORDER BY ID_FILA_IMPORTACAO_ARQUIVO ASC) , DT_INICIO_IMPORTACAO)\r\n"
				+ "END \r\n"
				+ "AS DT_INICIO_IMPORTACAO_2\r\n"
				+ ",COALESCE(LAG(DT_FIM_IMPORTACAO,1,NULL) OVER (ORDER BY ID_FILA_IMPORTACAO_ARQUIVO ASC) , DT_FIM_IMPORTACAO) AS DT_FIM_IMPORTACAO_2\r\n"
				+ "FROM TB_FILA_IMPORTACAO_ARQUIVO\r\n"
				+ "WHERE CONVERT(DATE,DT_ENTRADA) = '"+data.toString()+"') AS X\r\n"
				+ "GROUP BY CASE WHEN DATEDIFF(SS,DT_FIM_IMPORTACAO_2, DT_FIM_IMPORTACAO) < 0 \r\n"
				+ "	        THEN DATEDIFF(SS,DT_INICIO_IMPORTACAO, DT_FIM_IMPORTACAO) \r\n"
				+ "			ELSE DATEDIFF(SS,DT_FIM_IMPORTACAO_2, DT_FIM_IMPORTACAO)\r\n"
				+ "			END\r\n"
				+ "ORDER BY TEMPO";
		
		
		List<TempoDto> retorno = new ArrayList<>();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			while(rs.next()) {
				retorno.add(new TempoDto(rs.getInt("TEMPO"), rs.getInt("PROCESSADOS")));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public StatusDto getStatusArquivosFilaCarregarCnab(Base base, LocalDate data) {
		String sqlQuery = "SELECT  STATUS_FILA  ,COUNT(*) AS QUANT\r\n" + 
				"FROM TB_FILA_CARREGAR_CNAB AI WITH(NOLOCK)\r\n" + 
				"WHERE CONVERT(DATE,AI.DATA) = '"+data.toString()+"'\r\n" + 
				"GROUP BY STATUS_FILA";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			StatusDto retorno = new StatusDto();
			
			while(rs.next()) {
				 String status = rs.getString("STATUS_FILA");
				 
				 if(status.equals("AGUARDANDO")) {
					 retorno.setAguardando(rs.getInt("QUANT"));
				 }else if(status.equals("FINALIZADO")) {
					 retorno.setFinalizado(rs.getInt("QUANT"));
				 }else if(status.equals("PROCESSANDO")) {
					 retorno.setProcessando(rs.getInt("QUANT"));
				 }else if(status.equals("ERRO")) {
					 retorno.setErro(rs.getInt("QUANT"));
				 }
			}
			con.close();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new StatusDto();
		
	}
	
	@Override
	public ArquivosPorMinutoDto getArquivosPorMinutoFilaCarregarCnab(Base base, LocalDate data) {
		String sqlQuery = "SELECT MIN(PROCESSADOS) AS MINIMO, MAX(PROCESSADOS) AS MAXIMO , AVG(PROCESSADOS) AS MEDIA\r\n" + 
				"FROM(\r\n" + 
				"     SELECT LEFT(CONVERT(TIME,AI.FINALIZOU),5) AS HORARIO, COUNT(1) PROCESSADOS\r\n" + 
				"     FROM TB_FILA_CARREGAR_CNAB AI   WITH(NOLOCK)\r\n" + 
				"     WHERE CONVERT(DATE,AI.DATA) = '"+data.toString()+"'\r\n" + 
				"     AND FINALIZOU IS NOT NULL\r\n" + 
				"     GROUP BY LEFT(CONVERT(TIME,AI.FINALIZOU),5) \r\n" + 
				"	 ) AS X";
		
		
		ArquivosPorMinutoDto retorno = new ArquivosPorMinutoDto();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			if(rs.next()) {
				retorno.setMinimo(rs.getInt("MINIMO"));
				retorno.setMaximo(rs.getInt("MAXIMO"));
				retorno.setMedia(rs.getInt("MEDIA"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public List<TempoDto> getTemposFilaCarregarCnab(Base base, LocalDate data) {
		String sqlQuery = "SELECT \r\n"
				+ "       CASE WHEN DATEDIFF(SS,INICIO_2, FINALIZOU) < 0 \r\n"
				+ "	        THEN DATEDIFF(SS,INICIO, FINALIZOU) \r\n"
				+ "			ELSE DATEDIFF(SS,INICIO_2, FINALIZOU)\r\n"
				+ "			END  AS TEMPO,COUNT(*) AS PROCESSADOS\r\n"
				+ "FROM(\r\n"
				+ "SELECT *, \r\n"
				+ "CASE WHEN INICIO > COALESCE(LAG(FINALIZOU,1,NULL) OVER (ORDER BY ID_FILA_CARREGAR_CNAB ASC) , INICIO)\r\n"
				+ "THEN INICIO\r\n"
				+ "ELSE  COALESCE(LAG(FINALIZOU,1,NULL) OVER (ORDER BY ID_FILA_CARREGAR_CNAB ASC) , INICIO)\r\n"
				+ "END \r\n"
				+ "AS INICIO_2\r\n"
				+ ",COALESCE(LAG(FINALIZOU,1,NULL) OVER (ORDER BY ID_FILA_CARREGAR_CNAB ASC) , FINALIZOU) AS FINALIZOU_2\r\n"
				+ "FROM TB_FILA_CARREGAR_CNAB\r\n"
				+ "WHERE DATA = '"+data.toString()+"') AS X\r\n"
				+ "GROUP BY CASE WHEN DATEDIFF(SS,INICIO_2, FINALIZOU) < 0 \r\n"
				+ "	        THEN DATEDIFF(SS,INICIO, FINALIZOU) \r\n"
				+ "			ELSE DATEDIFF(SS,INICIO_2, FINALIZOU)\r\n"
				+ "			END\r\n"
				+ "ORDER BY TEMPO ";
		
		
		List<TempoDto> retorno = new ArrayList<>();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			while(rs.next()) {
				retorno.add(new TempoDto(rs.getInt("TEMPO"), rs.getInt("PROCESSADOS")));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public StatusDto getStatusArquivosFilaMovimentacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT  STATUS_FILA  ,COUNT(*) AS QUANT\r\n" + 
				"FROM TB_FILA_GERAR_MOVIMENTACAO AI WITH(NOLOCK)\r\n" + 
				"WHERE CONVERT(DATE,AI.DATA) = '"+data.toString()+"'\r\n" + 
				"GROUP BY STATUS_FILA";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			StatusDto retorno = new StatusDto();
			
			while(rs.next()) {
				 String status = rs.getString("STATUS_FILA");
				 
				 if(status.equals("AGUARDANDO")) {
					 retorno.setAguardando(rs.getInt("QUANT"));
				 }else if(status.equals("FINALIZADO")) {
					 retorno.setFinalizado(rs.getInt("QUANT"));
				 }else if(status.equals("PROCESSANDO")) {
					 retorno.setProcessando(rs.getInt("QUANT"));
				 }else if(status.equals("ERRO")) {
					 retorno.setErro(rs.getInt("QUANT"));
				 }
			}
			con.close();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new StatusDto();
		
	}
	
	@Override
	public ArquivosPorMinutoDto getArquivosPorMinutoFilaMovimentacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT MIN(PROCESSADOS) AS MINIMO, MAX(PROCESSADOS) AS MAXIMO , AVG(PROCESSADOS) AS MEDIA\r\n" + 
				"FROM(\r\n" + 
				"     SELECT LEFT(CONVERT(TIME,AI.FINALIZOU),5) AS HORARIO, COUNT(1) PROCESSADOS\r\n" + 
				"     FROM TB_FILA_GERAR_MOVIMENTACAO AI   WITH(NOLOCK)\r\n" + 
				"     WHERE CONVERT(DATE,AI.DATA) = '"+data.toString()+"'\r\n" + 
				"     AND FINALIZOU IS NOT NULL \r\n" + 
				"     GROUP BY LEFT(CONVERT(TIME,AI.FINALIZOU),5) \r\n" + 
				"	 ) AS X";
		
		
		ArquivosPorMinutoDto retorno = new ArquivosPorMinutoDto();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			if(rs.next()) {
				retorno.setMinimo(rs.getInt("MINIMO"));
				retorno.setMaximo(rs.getInt("MAXIMO"));
				retorno.setMedia(rs.getInt("MEDIA"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public List<TempoDto> getTemposFilaMovimentacao(Base base, LocalDate data) {
		String sqlQuery = "SELECT DATEDIFF(SS,INICIO, FINALIZOU) AS TEMPO, COUNT(*) AS PROCESSADOS\r\n"
				+ "FROM TB_FILA_GERAR_MOVIMENTACAO WITH(NOLOCK)\r\n"
				+ "WHERE DATA = '"+data.toString()+"' \r\n"
				+ "AND INICIO IS NOT NULL\r\n"
				+ "AND FINALIZOU IS NOT NULL\r\n"
				+ "GROUP BY DATEDIFF(SS,INICIO, FINALIZOU)\r\n"
				+ "ORDER BY TEMPO ";
		
		
		List<TempoDto> retorno = new ArrayList<>();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();

			while(rs.next()) {
				retorno.add(new TempoDto(rs.getInt("TEMPO"), rs.getInt("PROCESSADOS")));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}


	
	
	


}

package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;


@Service
public class ArquivoServiceImpl implements IArquivoService, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public ArquivoDto inserirTbArquivo(Connection con, FundoDto fundo, String nomeArquivo) throws SQLException {
		
		String sqlQuery = "INSERT INTO TB_ARQUIVO(IC_COMPACTADO,DT_ENTRADA,DT_IMPORTACAO, CD_LAYOUT, NM_ARQUIVO, NM_ARQUIVO_ENTRADA,IC_PROC_REMOCAO,IC_STATUS_PROC,IC_TEMPORARIO,ID_FUNDO,ID_USUARIO,TIPO_LAYOUT,IC_ARQUIVO_RETORNO_GERADO)\r\n" + 
				"values(0,Convert(Date,'" + fundo.getDataFundo().toString() +"'),"
			  + "Convert(Date,'" + fundo.getDataFundo().toString() +"'), " + fundo.getLayoutAquisicao() +", '" + nomeArquivo +"', '" + nomeArquivo +"',0,'A',0,1,1,'R',0)";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		return findArquivoByNomeArquivo(con, nomeArquivo);
	}

	@Override
	public boolean inserirTbArquivoImportado(Connection con, List<String> cnab, ArquivoDto arquivo) throws SQLException {
		String sqlQuery = "INSERT INTO TB_ARQUIVO_IMPORTADO(ID_ARQUIVO,TP_REGISTRO,DS_REGISTRO,NU_SEQ_REGISTRO,ID_MOTIVO_REJEICAO,TP_REG_ARQUIVO_IMPORTADO,DS_NOSSO_NUMERO)\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",0,'"+cnab.get(0)+"',1,null,'R',null\r\n" + 
				"\r\n" + 
				"UNION ALL\r\n" + 
				"\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",1,'"+cnab.get(1)+"',2,null,'R',null\r\n" + 
				"\r\n" + 
				"UNION ALL\r\n" + 
				"\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",9,'"+cnab.get(2)+"',3,null,'R',null\r\n" + 
				"";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		return confirmarArquivoImportado(con, arquivo.getIdArquivo());
	}

	private boolean confirmarArquivoImportado(Connection con, Integer idArquivo) {

		try {
		String sqlQuery = "select * from TB_ARQUIVO_IMPORTADO where ID_ARQUIVO = " + idArquivo;
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		ResultSet rs = ps.getResultSet();
		
		if(rs.next()) {
			return true;
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public ArquivoDto findArquivoByNomeArquivo(Connection con, String nomeArquivo) throws SQLException {
		
		ArquivoDto retorno = null;
		
		String sqlQuery = "SELECT TOP 1 ID_ARQUIVO, NM_ARQUIVO, DT_ENTRADA\r\n" + 
				"FROM TB_ARQUIVO\r\n" + 
				"WHERE NM_ARQUIVO = '" + nomeArquivo + "'\r\n" + 
				"ORDER BY 1 DESC";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		ResultSet rs = ps.getResultSet();
		
		if(rs.next()) {
			retorno = new ArquivoDto(rs.getInt("ID_ARQUIVO"), 
                                     rs.getString("NM_ARQUIVO"), 
                                     LocalDate.parse(rs.getString("DT_ENTRADA"))
                                      );
		}
		
		return retorno;
	}


//	public List<CedenteDto> findAll(Connection con, Integer idFundo) {
//		
//		List<CedenteDto> cedentes = new ArrayList<CedenteDto>();
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(Querys.ALL_CEDENTES);
//			
//			ps.setInt(1, idFundo);
//			
//			ps.execute();
//			
//			ResultSet rs = ps.getResultSet();
//			
//			while(rs.next()) {
//				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
//											  rs.getString("NM_CEDENTE"), 
//											  rs.getString("NU_CPF_CNPJ"),
//											  rs.getString("TP_COOBRIGACAO"));
//				
//				cedentes.add(cedente);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return cedentes;
//	}
//
//	public CedenteDto findOneById(Connection con, Integer idCedente) {
//		CedenteDto cedente = null;
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(Querys.ONE_CEDENTE);
//			
//			ps.setInt(1, idCedente);
//			
//			ps.execute();
//			
//			ResultSet rs = ps.getResultSet();
//			
//			while(rs.next()) {
//				cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
//											  rs.getString("NM_CEDENTE"), 
//											  rs.getString("NU_CPF_CNPJ"),
//											  rs.getString("TP_COOBRIGACAO"));
//				
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return cedente;
//	}
//
//	@Override
//	public CedenteDto getPrimeiroCedente(Connection con, Integer idFundo) throws SQLException {
//
//		CedenteDto cedente = null;
//
//		String sqlQuery = "select TOP 1 ID_CEDENTE, NM_CEDENTE, NU_CPF_CNPJ, TP_COOBRIGACAO FROM TB_FUNDO_CEDENTE WHERE ID_FUNDO = " + idFundo;
//
//		PreparedStatement ps = con.prepareStatement(sqlQuery);
//
//		ps.execute();
//
//		ResultSet rs = ps.getResultSet();
//
//		if(rs.next()) {
//			cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
//					rs.getString("NM_CEDENTE"), 
//					rs.getString("NU_CPF_CNPJ"), 
//					rs.getString("TP_COOBRIGACAO"));
//
//		}
//
//		return cedente;
//	}
	
	


}

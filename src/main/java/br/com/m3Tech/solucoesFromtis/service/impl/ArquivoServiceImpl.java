package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.ITabelaService;
import br.com.m3Tech.solucoesFromtis.util.MontarQueryUtils;
import br.com.m3Tech.utils.LocalDateUtils;


@Service
public class ArquivoServiceImpl implements IArquivoService, Serializable{

	private static final long serialVersionUID = 1L;
	
	private final ITabelaService tabelaService;
	
	@Autowired
	public ArquivoServiceImpl(final ITabelaService tabelaService) {
		this.tabelaService =  tabelaService;
	}

	@Override
	public ArquivoDto inserirTbArquivo(Connection con, FundoDto fundo, String nomeArquivo) throws SQLException {
		
		Map<String,String> parametros = new HashMap<>();
		
		parametros.put("NM_ARQUIVO", "'"+nomeArquivo+"'");
		parametros.put("DT_ENTRADA", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
		parametros.put("DT_IMPORTACAO", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
		parametros.put("CD_LAYOUT", fundo.getLayoutAquisicao().toString());
		parametros.put("NM_ARQUIVO_ENTRADA", "'"+nomeArquivo+"'");
		parametros.put("IC_STATUS_PROC", "'A'");
		parametros.put("IC_TEMPORARIO", "0");
		parametros.put("ID_USUARIO", "1");
		parametros.put("ID_FUNDO", fundo.getIdFundo().toString());
		parametros.put("TIPO_LAYOUT", "'R'");
		parametros.put("IC_ARQUIVO_RETORNO_GERADO", "0");
		
		
		String sqlQuery = MontarQueryUtils.getQueryInsert(parametros, tabelaService.getTabela(con, "TB_ARQUIVO"), "ID_ARQUIVO");
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ps.execute();
		
		return findArquivoByNomeArquivo(con, nomeArquivo);
	}

	@Override
	public boolean inserirTbArquivoImportado(Connection con, List<String> cnab, ArquivoDto arquivo) throws SQLException {	
		
		String sqlQuery = "INSERT INTO TB_ARQUIVO_IMPORTADO(ID_ARQUIVO,TP_REGISTRO,DS_REGISTRO,NU_SEQ_REGISTRO,ID_MOTIVO_REJEICAO,TP_REG_ARQUIVO_IMPORTADO)\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",0,'"+cnab.get(0)+"',1,null,'R'\r\n" + 
				"\r\n" + 
				"UNION ALL\r\n" + 
				"\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",1,'"+cnab.get(1)+"',2,null,'R'\r\n" + 
				"\r\n" + 
				"UNION ALL\r\n" + 
				"\r\n" + 
				"SELECT " +arquivo.getIdArquivo() + ",9,'"+cnab.get(2)+"',3,null,'R'\r\n" + 
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
		
//		if(rs.next()) {
//			retorno = new ArquivoDto(rs.getInt("ID_ARQUIVO"), 
//                                     rs.getString("NM_ARQUIVO"), 
//                                     LocalDateUtils.getLocalDate(rs.getString("DT_ENTRADA"))
//                                      );
//		}
//		
		return retorno;
	}

}

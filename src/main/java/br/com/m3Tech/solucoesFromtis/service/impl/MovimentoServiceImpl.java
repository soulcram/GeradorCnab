package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloEmEstoqueDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;


@Service
public class MovimentoServiceImpl implements IMovimentoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<MovimentoDto> findAllMovimentos(Base base, Integer cdLayout, boolean isCobranca) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ALL_MOVIMENTOS);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"),
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(retorno.isEmpty() && !isCobranca) {
			retorno.add(new MovimentoDto(null, "01", "AQUISICAO"));
		}
		
		return retorno;
	}
	
	@Override
	public MovimentoDto findMovimento(Base base, Integer cdLayout, Integer cdOcorrencia) {
		
		
		String sqlQuery ="SELECT DISTINCT TM.ID_TIPO_MOVIMENTO, LM.CD_OCORRENCIA, TM.NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"WHERE CD_LAYOUT = ?\r\n" + 
				"AND CD_OCORRENCIA = ?";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.setInt(1, cdLayout);
			ps.setInt(2, cdOcorrencia);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return  new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null	;
	}

	public List<MovimentoDto> findAllMovimentosAquisicao(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE LM.CD_LAYOUT = ?\r\n" + 
				" AND IC_AQUISICAO = 1\r\n" + 
				" AND TMM.IC_BAIXAR_ATIVO = 0\r\n" + 
				" AND IC_RECOMPRA = 0";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosBaixa(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE LM.CD_LAYOUT = ?\r\n" + 
				" AND TMM.IC_BAIXAR_ATIVO = 1\r\n" + 
				" AND TMM.IC_AQUISICAO = 0\r\n" + 
				" AND TMM.IC_RECOMPRA = 0";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public List<MovimentoDto> findAllMovimentosCobranca(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE LM.CD_LAYOUT = ?\r\n";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosParcial(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO , TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO \r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_GERA_HIST_RECEBIVEL = 1 \r\n" + 
				"AND TMM.IC_AQUISICAO = 0 \r\n" + 
				"AND TMM.IC_RECOMPRA = 0";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@Override
	public List<MovimentoDto> findAllMovimentosProrrogacao(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO , TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO \r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_ALTERACAO_VENCIMENTO = 1 \r\n";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@Override
	public List<MovimentoDto> findAllMovimentosReativacao(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO , TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO \r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_REATIVACAO = 1 \r\n";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosRecompraAquisicao(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_AQUISICAO = 1";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public List<MovimentoDto> findAllMovimentosRecompraBaixa(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_BAIXAR_ATIVO = 1";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	public List<MovimentoDto> findAllMovimentosRecompraParcial(Base base, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_GERA_HIST_RECEBIVEL = 1";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<TituloEmEstoqueDto> findAllTitulosEmEstoque(Base base, Integer idFundo) {
		
		List<TituloEmEstoqueDto> retorno = new ArrayList<TituloEmEstoqueDto>();
		
		String query = "SELECT * \r\n" + 
				" FROM TB_RECEBIVEL R\r\n" + 
				" INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO\r\n" + 
				" INNER JOIN TB_ESTOQUE E ON E.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n" + 
				" WHERE R.ID_FUNDO = ?\r\n" + 
				" AND E.DT = DATEADD(DAY, -1, F.DT_FUNDO)";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idFundo);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				TituloEmEstoqueDto movimento = null;
//						new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
//											  			  rs.getString("CD_OCORRENCIA"),
//											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public MovimentoDto findOneMovimentoById(Base base, Integer idMovimento) {
		MovimentoDto retorno = null;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(Querys.ONE_MOVIMENTO);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				retorno = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), 
						  					  rs.getString("CD_OCORRENCIA"),
						  					  rs.getString("NM_TIPO_MOVIMENTO"));
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public Boolean isAquisicao(Base base, Integer idMovimento) {
		
		
		try {
			
			String query = "SELECT IC_AQUISICAO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE TM.ID_TIPO_MOVIMENTO = ?";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				boolean retorno = rs.getBoolean("IC_AQUISICAO");
				con.close();
				return retorno;
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<TituloDto> findAllTituloEmEstoqueByFundo(Base base, Integer idFundo, boolean isProrrogacao) {
		
		
		try {
			
			String query = "DECLARE @IDFUNDO INT = " + idFundo  +  "\r\n" + 
					"\r\n" + 
					"SELECT \r\n"
					+ "	DISTINCT TOP 20 R.ID_RECEBIVEL,\r\n"
					+ "	R.DS_SEU_NUMERO, \r\n"
					+ "	R.IC_COOBRIGACAO,\r\n"
					+ "	R.DS_NU_DOCUMENTO, \r\n"
					+ "	D.TERMO_CESSAO,\r\n"
					+ "	D.CHAVE_NFE, \r\n"
					+ "	R.VL_AQUISICAO,\r\n"
					+ "	(E.VL_NOMINAL - COALESCE(HR.VL_ABATIMENTO,0) -COALESCE(HR.VL_AMORTIZACAO,0) ) AS VL_NOMINAL ,\r\n"
					+ "	FC.ID_CEDENTE,FC.NM_CEDENTE,FC.NU_CPF_CNPJ AS DOC_CEDENTE, CC.NU_AGENCIA, CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA, \r\n"
					+ "	FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP ,\r\n"
					+ "	B.NU_BANCO, \r\n"
					+ "	R.DT_VENCIMENTO ,\r\n"
					+ "	D.ESPECIE  AS ID_TIPO_ESPECIE\r\n"
					+ "	FROM TB_ESTOQUE E with(nolock) \r\n"
					+ "	INNER JOIN TB_RECEBIVEL R with(nolock)  ON R.ID_RECEBIVEL = E.ID_RECEBIVEL \r\n"
					+ "	LEFT JOIN TB_HISTORICO_RECEBIVEL HR with(nolock)  ON HR.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n"
					+ "	INNER JOIN TB_FUNDO F with(nolock)  ON F.ID_FUNDO = R.ID_FUNDO \r\n"
					+ "	INNER JOIN TB_FUNDO_CEDENTE FC with(nolock)  ON FC.ID_CEDENTE = R.ID_CEDENTE \r\n"
					+ "	INNER JOIN TB_FUNDO_SACADO FS with(nolock)  ON FS.ID_SACADO = R.ID_SACADO \r\n"
					+ "	INNER JOIN TB_BANCO B with(nolock)  ON B.ID_BANCO = R.ID_BANCO \r\n"
					+ "    LEFT JOIN TB_CONTA_CORRENTE CC ON CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 \r\n"
					+ "	LEFT JOIN TB_STG_REMESSA STG with(nolock)  ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO \r\n"
					+ "	CROSS APPLY DBO.FC_VW_ARQUIVO_IMPORTADO_DETAIL_ID_ARQUIVO (R.ID_ARQUIVO,R.NU_SEQ_REGISTRO) AS D \r\n"
					+ "	WHERE E.ID_FUNDO = @IDFUNDO\r\n"
					+ "	AND NOT EXISTS(\r\n"
					+ "		SELECT Top 1 * FROM TB_STG_REMESSA STG1  with(nolock) \r\n"
					+ "		INNER JOIN TB_TIPO_MOVIMENTO TM with(nolock)  ON STG1.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n"
					+ "		INNER JOIN TB_TIPO_MOVIMENTACAO TMM with(nolock)  ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n"
					+ "		WHERE R.DS_SEU_NUMERO = STG1.DS_SEU_NUMERO\r\n"
					+ "		AND TMM.IC_BAIXAR_ATIVO = 1\r\n"
					+ "       AND DT_AQUISICAO = GETDATE() \r\n"
					+ "	)\r\n"
					+ "   AND NOT EXISTS(\r\n"
					+ "		SELECT Top 1 * FROM TB_MOVIMENTO M with(nolock) \r\n"
					+ "		INNER JOIN TB_TIPO_MOVIMENTO TM with(nolock)  ON M.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n"
					+ "		INNER JOIN TB_TIPO_MOVIMENTACAO TMM with(nolock)  ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n"
					+ "		WHERE R.ID_RECEBIVEL = M.ID_RECEBIVEL\r\n"
					+ "		AND TMM.IC_BAIXAR_ATIVO = 1\r\n"
					+ "	)\r\n"
					+ "	AND E.DT = DBO.FC_TRAZDIAUTIL(DATEADD(DD, - 1, F.DT_FUNDO), 'A') \r\n"; 
					
			if(isProrrogacao) {

					
		   query += "AND R.DT_VENCIMENTO BETWEEN F.DT_MIN_VENCIMENTO_ORIGINAL AND F.DT_MAX_VENCIMENTO_ORIGINAL\r\n"
		   		+ "AND F.PZ_MAX_ALTERACAO_DT_VCTO > (\r\n"
		   		+ "									SELECT COUNT(*) FROM TB_MOVIMENTO M\r\n"
		   		+ "									INNER JOIN TB_TIPO_MOVIMENTO TM ON M.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO \r\n"
		   		+ "									INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO \r\n"
		   		+ "									WHERE M.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n"
		   		+ "									AND IC_ALTERACAO_VENCIMENTO = 1 \r\n"
		   		+ "							     )\r\n" + 
					"" ;
			}
			
			query += " ORDER BY R.ID_RECEBIVEL DESC";
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			List<TituloDto> retorno = new ArrayList<TituloDto>();
			
			if(rs == null) {
				con.close();
				return retorno;
			}
			
			while(rs.next()) {
				
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
													rs.getString("NM_CEDENTE"), 
													rs.getString("DOC_CEDENTE"),
													null,
													rs.getString("NU_BANCO"),
													rs.getString("NU_AGENCIA"),
													rs.getString("DIGITO_AG"),
													rs.getString("NU_CONTA"),
													rs.getString("DIGITO_CONTA"));
				
				SacadoDto sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
												 rs.getString("NM_SACADO"), 
												 rs.getString("DOC_SACADO"), 
												 rs.getString("DS_LOGRADOURO"), 
												 rs.getString("NU_CEP"));
				
				
				TituloDto titulo = new TituloDto();
				
				titulo.setCedente(cedente);
				titulo.setSacado(sacado);
				titulo.setChaveNfe(rs.getString("CHAVE_NFE"));
				titulo.setCoobrigacao(rs.getBoolean("IC_COOBRIGACAO") ? "01" : "02");
				titulo.setEspecie(rs.getString("ID_TIPO_ESPECIE"));
				titulo.setNumBanco(rs.getString("NU_BANCO"));
				titulo.setNumeroDocumento(rs.getString("DS_NU_DOCUMENTO"));
				titulo.setSeuNumero(rs.getString("DS_SEU_NUMERO"));
				titulo.setTermoCessao(rs.getString("TERMO_CESSAO"));
				titulo.setValorAquisicao(rs.getBigDecimal("VL_AQUISICAO"));
				titulo.setValorPago(null);
				titulo.setValorTitulo(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setDataVencimento(LocalDate.parse(rs.getString("DT_VENCIMENTO").substring(0, 10)));
				titulo.setDataLiquidacao(LocalDate.now());
				
				retorno.add(titulo);
				
			}
			con.close();
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<TituloDto>();
	}
	
	@Override
	public List<TituloDto> findAllTituloDesativados(Base base, Integer idFundo) {
		
		
		try {
			
			String query = "DECLARE @IDFUNDO INT = "+idFundo+"\r\n"
					+ "\r\n"
					+ "SELECT  DISTINCT R.ID_RECEBIVEL, \r\n"
					+ "R.DS_SEU_NUMERO,  \r\n"
					+ "R.IC_COOBRIGACAO, \r\n"
					+ "R.DS_NU_DOCUMENTO,  \r\n"
					+ "STG.TERMO_CESSAO,  \r\n"
					+ "STG.CHAVE_NFE,  \r\n"
					+ "R.VL_AQUISICAO, \r\n"
					+ "R.VL_NOMINAL  AS VL_NOMINAL , \r\n"
					+ "FC.ID_CEDENTE,FC.NM_CEDENTE,FC.NU_CPF_CNPJ AS DOC_CEDENTE, CC.NU_AGENCIA, CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,  \r\n"
					+ "FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP , \r\n"
					+ "B.NU_BANCO,  \r\n"
					+ "R.DT_VENCIMENTO ,\r\n"
					+ "D.ESPECIE AS ID_TIPO_ESPECIE \r\n"
					+ "FROM TB_RECEBIVEL R   \r\n"
					+ "INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO \r\n"
					+ "INNER JOIN TB_FUNDO_CEDENTE FC ON FC.ID_CEDENTE = R.ID_CEDENTE AND FC.ID_FUNDO = R.ID_FUNDO  \r\n"
					+ "INNER JOIN TB_FUNDO_SACADO FS ON FS.ID_SACADO = R.ID_SACADO  AND FS.ID_FUNDO = R.ID_FUNDO \r\n"
					+ "INNER JOIN TB_BANCO B ON B.ID_BANCO = R.ID_BANCO  \r\n"
					+ "CROSS APPLY(\r\n"
					+ "		SELECT TOP 1 * FROM TB_CONTA_CORRENTE CC1 \r\n"
					+ "		WHERE CC1.ID_CEDENTE = FC.ID_CEDENTE AND CC1.IS_PADRAO = 1\r\n"
					+ ") as CC\r\n"
					+ "INNER JOIN TB_STG_REMESSA STG ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO   \r\n"
					+ "CROSS APPLY DBO.FC_VW_ARQUIVO_IMPORTADO_DETAIL_ID_ARQUIVO (R.ID_ARQUIVO,R.NU_SEQ_REGISTRO) AS D\r\n"
					+ "LEFT JOIN TB_ESTOQUE E ON (E.ID_RECEBIVEL = R.ID_RECEBIVEL AND E.DT = DBO.FC_TRAZDIAUTIL(DATEADD(DD, - 1, F.DT_FUNDO), 'A')) \r\n"
					+ "WHERE R.ID_FUNDO = @IDFUNDO \r\n"
					+ "AND E.ID_RECEBIVEL IS NUll\r\n"
					+ "AND NOT EXISTS(\r\n"
					+ "	SELECT Top 1 * FROM TB_MOVIMENTO M\r\n"
					+ "	INNER JOIN TB_TIPO_MOVIMENTO TM ON M.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n"
					+ "	INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n"
					+ "	WHERE R.ID_RECEBIVEL = M.ID_RECEBIVEL\r\n"
					+ "	AND TMM.IC_REATIVACAO = 1\r\n"
					+ "	AND M.DT = F.DT_FUNDO\r\n"
					+ ")\r\n"
					+ "		\r\n"
					+ "ORDER BY ID_RECEBIVEL DESC";
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			List<TituloDto> retorno = new ArrayList<TituloDto>();
			
			if(rs == null) {
				con.close();
				return retorno;
			}
			
			while(rs.next()) {
				
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
													rs.getString("NM_CEDENTE"), 
													rs.getString("DOC_CEDENTE"),
													null,
													rs.getString("NU_BANCO"),
													rs.getString("NU_AGENCIA"),
													rs.getString("DIGITO_AG"),
													rs.getString("NU_CONTA"),
													rs.getString("DIGITO_CONTA"));
				
				SacadoDto sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
												 rs.getString("NM_SACADO"), 
												 rs.getString("DOC_SACADO"), 
												 rs.getString("DS_LOGRADOURO"), 
												 rs.getString("NU_CEP"));
				
				
				TituloDto titulo = new TituloDto();
				
				titulo.setCedente(cedente);
				titulo.setSacado(sacado);
				titulo.setChaveNfe(rs.getString("CHAVE_NFE"));
				titulo.setCoobrigacao(rs.getBoolean("IC_COOBRIGACAO") ? "01" : "02");
				titulo.setEspecie(rs.getString("ID_TIPO_ESPECIE"));
				titulo.setNumBanco(rs.getString("NU_BANCO"));
				titulo.setNumeroDocumento(rs.getString("DS_NU_DOCUMENTO"));
				titulo.setSeuNumero(rs.getString("DS_SEU_NUMERO"));
				titulo.setTermoCessao(rs.getString("TERMO_CESSAO"));
				titulo.setValorAquisicao(rs.getBigDecimal("VL_AQUISICAO"));
				titulo.setValorPago(null);
				titulo.setValorTitulo(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setDataVencimento(LocalDate.parse(rs.getString("DT_VENCIMENTO").substring(0, 10)));
				titulo.setDataLiquidacao(LocalDate.now());
				
				retorno.add(titulo);
				
			}
			con.close();
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<TituloDto>();
	}
	
	@Override
	public List<TituloDto> findAllTituloEmEstoque(Base base) {
		
		try {
			
			String query = "" + 
					"\r\n" + 
					"Select x.*, D.ESPECIE AS ID_TIPO_ESPECIE\r\n" + 
					"from (\r\n" + 
					"	SELECT DISTINCT R.ID_RECEBIVEL,\r\n" + 
					"	R.DS_SEU_NUMERO, \r\n" + 
					"	R.IC_COOBRIGACAO,\r\n" + 
					"	R.DS_NU_DOCUMENTO, \r\n" + 
					"	STG.TERMO_CESSAO, \r\n" + 
					"	STG.CHAVE_NFE, \r\n" + 
					"	R.VL_AQUISICAO,\r\n" + 
					"	(E.VL_NOMINAL - COALESCE(HR.VL_ABATIMENTO,0) -COALESCE(HR.VL_AMORTIZACAO,0) ) AS VL_NOMINAL ,\r\n" + 
					"	FC.ID_CEDENTE,FC.NM_CEDENTE,FC.NU_CPF_CNPJ AS DOC_CEDENTE, CC.NU_AGENCIA, CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA, \r\n" + 
					"	FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP ,\r\n" + 
					"	B.NU_BANCO, \r\n" + 
					"	R.DT_VENCIMENTO, \r\n" + 
					"	F.ID_FUNDO \r\n" + 
					"	FROM TB_ESTOQUE E \r\n" + 
					"	INNER JOIN TB_RECEBIVEL R ON R.ID_RECEBIVEL = E.ID_RECEBIVEL \r\n" + 
					"	LEFT JOIN TB_HISTORICO_RECEBIVEL HR ON HR.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n" + 
					"	INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO \r\n" + 
					"	INNER JOIN TB_FUNDO_CEDENTE FC ON FC.ID_CEDENTE = R.ID_CEDENTE \r\n" + 
					"	INNER JOIN TB_FUNDO_SACADO FS ON FS.ID_SACADO = R.ID_SACADO \r\n" + 
					"	INNER JOIN TB_BANCO B ON B.ID_BANCO = R.ID_BANCO \r\n" + 
					"   LEFT JOIN TB_CONTA_CORRENTE CC ON CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1 \r\n" +
					"	INNER JOIN TB_STG_REMESSA STG ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO  \r\n" + 
					"	WHERE CONVERT(DATE,F.DT_FUNDO) = CONVERT(DATE,GETDATE())\r\n" + 
					"	AND NOT EXISTS(\r\n" +
				    "		SELECT Top 1 * FROM TB_STG_REMESSA STG1\r\n" +
					"		INNER JOIN TB_TIPO_MOVIMENTO TM ON STG1.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n" +
					"		INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" +
					"		WHERE R.DS_SEU_NUMERO = STG1.DS_SEU_NUMERO\r\n" +
					"		AND TMM.IC_BAIXAR_ATIVO = 1\r\n"+
					"       AND DT_AQUISICAO = GETDATE() \r\n" +
					"	)\r\n" +
					"   AND NOT EXISTS(\r\n" +
					"		SELECT Top 1 * FROM TB_MOVIMENTO M\r\n" +
					"		INNER JOIN TB_TIPO_MOVIMENTO TM ON M.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n" +
					"		INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" +
					"		WHERE R.ID_RECEBIVEL = M.ID_RECEBIVEL\r\n" +
					"		AND CONVERT(DATE,M.DT) = CONVERT(DATE,F.DT_FUNDO) \r\n" +
					"	)" +
					"	AND E.DT = DBO.FC_TRAZDIAUTIL(DATEADD(DD, - 1, F.DT_FUNDO), 'A') \r\n" + 
					") as X\r\n" + 
					"INNER JOIN TB_RECEBIVEL REC ON REC.DS_SEU_NUMERO = X.DS_SEU_NUMERO\r\n" + 
					"CROSS APPLY DBO.FC_VW_ARQUIVO_IMPORTADO_DETAIL_ID_ARQUIVO (REC.ID_ARQUIVO, REC.NU_SEQ_REGISTRO)  AS D\r\n"; 

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			List<TituloDto> retorno = new ArrayList<TituloDto>();
			
			if(rs == null) {
				con.close();
				return retorno;
			}
			
			while(rs.next()) {
				
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
													rs.getString("NM_CEDENTE"), 
													rs.getString("DOC_CEDENTE"),
													null,
													rs.getString("NU_BANCO"),
													rs.getString("NU_AGENCIA"),
													rs.getString("DIGITO_AG"),
													rs.getString("NU_CONTA"),
													rs.getString("DIGITO_CONTA"));
				
				SacadoDto sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
												 rs.getString("NM_SACADO"), 
												 rs.getString("DOC_SACADO"), 
												 rs.getString("DS_LOGRADOURO"), 
												 rs.getString("NU_CEP"));
				
				
				TituloDto titulo = new TituloDto();
				
				titulo.setCedente(cedente);
				titulo.setSacado(sacado);
				titulo.setChaveNfe(rs.getString("CHAVE_NFE"));
				titulo.setCoobrigacao(rs.getBoolean("IC_COOBRIGACAO") ? "01" : "02");
				titulo.setEspecie(rs.getString("ID_TIPO_ESPECIE"));
				titulo.setNumBanco(rs.getString("NU_BANCO"));
				titulo.setNumeroDocumento(rs.getString("DS_NU_DOCUMENTO"));
				titulo.setSeuNumero(rs.getString("DS_SEU_NUMERO"));
				titulo.setTermoCessao(rs.getString("TERMO_CESSAO"));
				titulo.setValorAquisicao(rs.getBigDecimal("VL_AQUISICAO"));
				titulo.setValorPago(null);
				titulo.setValorTitulo(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setDataVencimento(LocalDate.parse(rs.getString("DT_VENCIMENTO").substring(0, 10)));
				titulo.setDataLiquidacao(LocalDate.now());
				titulo.setIdFundo(rs.getInt("ID_FUNDO"));
				
				retorno.add(titulo);
				
			}
			con.close();
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<TituloDto>();
	}

	public Boolean isBaixa(Base base, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_BAIXAR_ATIVO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE TM.ID_TIPO_MOVIMENTO = ?";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				boolean icBaixarAtivo = rs.getBoolean("IC_BAIXAR_ATIVO");
				con.close();
				return icBaixarAtivo;
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public Boolean isRecompra(Base base, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_RECOMPRA\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE TM.ID_TIPO_MOVIMENTO = ?";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				boolean icRecompra = rs.getBoolean("IC_RECOMPRA");
				con.close();
				return icRecompra;
				
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public Boolean isProrrogacao(Base base, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_ALTERACAO_VENCIMENTO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE TM.ID_TIPO_MOVIMENTO = ?";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				boolean icAlteracaoVencimento = rs.getBoolean("IC_ALTERACAO_VENCIMENTO");
				con.close();
				return icAlteracaoVencimento;
				
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public MovimentoDto getPrimeiroMovimentoAquisicao(Base base, Integer cdLayout) throws Exception {
		MovimentoDto retorno = null;

		String sqlQuery = " SELECT TOP 1 TM.ID_TIPO_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM On TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE CD_LAYOUT = " +cdLayout+ " \r\n" + 
				" AND TMM.IC_AQUISICAO = 1";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		if (rs.next()) {
			retorno = new MovimentoDto(rs.getInt("ID_TIPO_MOVIMENTO"), rs.getString("CD_OCORRENCIA"),
					rs.getString("NM_TIPO_MOVIMENTO"));

		}
		con.close();
		return retorno;
	}

	@Override
	public List<TituloDto> findAllTituloEmEstoqueCobranca(Base base, Integer fundoSelecionado, Integer idMovimento, LayoutEnum layout) {
		
		List<TituloDto> retorno = new ArrayList<>();
		
		try {
			
			String query = "DECLARE @IDFUNDO INT = "+fundoSelecionado+",\r\n" + 
					"        @ID_TIPO_MOVIMENTO INT = "+idMovimento+"\r\n" + 
					"\r\n" + 
					"SELECT DISTINCT TOP 20 X.*, D.ESPECIE AS ID_TIPO_ESPECIE\r\n" + 
					"FROM (\r\n" + 
					"\r\n" + 
					"	SELECT DISTINCT R.ID_RECEBIVEL,\r\n" + 
					"	R.DS_SEU_NUMERO, \r\n" + 
					"	R.IC_COOBRIGACAO,\r\n" + 
					"	R.DS_NU_DOCUMENTO, \r\n" + 
					"	STG.TERMO_CESSAO, \r\n" + 
					"	STG.CHAVE_NFE, \r\n" + 
					"	R.VL_AQUISICAO,\r\n" + 
					"	R.VL_NOMINAL AS VL_NOMINAL ,\r\n" + 
					"	FC.ID_CEDENTE,FC.NM_CEDENTE, FC.NU_CPF_CNPJ AS DOC_CEDENTE,  \r\n" +
					(layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? "" : "   CC.NU_AGENCIA, CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,\r\n") + 
					"	FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP ,\r\n" + 
					"	B.NU_BANCO, \r\n" + 
					"	R.DT_VENCIMENTO  ,\r\n" + 
					"	R.DT_AQUISICAO\r\n" + 
					"	FROM TB_RECEBIVEL R  \r\n" + 
					"	INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO \r\n" + 
					"	INNER JOIN TB_FUNDO_CEDENTE FC ON FC.ID_CEDENTE = R.ID_CEDENTE \r\n" + 
					"   LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1) \r\n" +
					"	INNER JOIN TB_FUNDO_SACADO FS ON FS.ID_SACADO = R.ID_SACADO \r\n" + 
					"	INNER JOIN TB_BANCO B ON B.ID_BANCO = R.ID_BANCO \r\n" + 
					"	INNER JOIN TB_STG_REMESSA STG ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO  \r\n" + 
					"	WHERE R.ID_FUNDO = @IDFUNDO\r\n"
					+ "AND (\r\n"
					+ "	(SELECT COUNT(*) FROM TB_MOVIMENTO M \r\n"
					+ "	WHERE 1 = 1\r\n"
					+ "	AND M.ID_RECEBIVEL = R.ID_RECEBIVEL ) < 2\r\n"
					+ "	)" + 
					"\r\n" + 
					"	UNION ALL\r\n" + 
					"	SELECT DISTINCT R.ID_RECEBIVEL,\r\n" + 
					"	R.DS_SEU_NUMERO, \r\n" + 
					"	R.IC_COOBRIGACAO,\r\n" + 
					"	R.DS_NU_DOCUMENTO, \r\n" + 
					"	STG.TERMO_CESSAO, \r\n" + 
					"	STG.CHAVE_NFE, \r\n" + 
					"	R.VL_AQUISICAO,\r\n" + 
					"	(E.VL_NOMINAL - COALESCE(HR.VL_ABATIMENTO,0) -COALESCE(HR.VL_AMORTIZACAO,0) ) AS VL_NOMINAL ,\r\n" + 
					"	FC.ID_CEDENTE,FC.NM_CEDENTE,FC.NU_CPF_CNPJ AS DOC_CEDENTE, \r\n" + 
					(layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? "" : "   CC.NU_AGENCIA, CC.DIGITO_AG, CC.NU_CONTA, CC.DIGITO_CONTA,\r\n") + 
					"	FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP ,\r\n" + 
					"	B.NU_BANCO, \r\n" + 
					"	R.DT_VENCIMENTO ,\r\n" + 
					"	R.DT_AQUISICAO\r\n" + 
					"	FROM TB_ESTOQUE E \r\n" + 
					"	INNER JOIN TB_RECEBIVEL R ON R.ID_RECEBIVEL = E.ID_RECEBIVEL \r\n" + 
					"	LEFT JOIN TB_HISTORICO_RECEBIVEL HR ON HR.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n" + 
					"	INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO \r\n" + 
					"	INNER JOIN TB_FUNDO_CEDENTE FC ON FC.ID_CEDENTE = R.ID_CEDENTE \r\n" + 
					"   LEFT JOIN TB_CONTA_CORRENTE CC ON (CC.ID_CEDENTE = FC.ID_CEDENTE AND CC.IS_PADRAO = 1) \r\n" +
					"	INNER JOIN TB_FUNDO_SACADO FS ON FS.ID_SACADO = R.ID_SACADO \r\n" + 
					"	INNER JOIN TB_BANCO B ON B.ID_BANCO = R.ID_BANCO \r\n" + 
					"	INNER JOIN TB_STG_REMESSA STG ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO  \r\n" + 
					"	WHERE E.ID_FUNDO = @IDFUNDO\r\n" + 
					"	AND E.DT = DBO.FC_TRAZDIAUTIL(DATEADD(DD, - 1, F.DT_FUNDO), 'A') \r\n" + 
					"\r\n" + 
					") AS X\r\n" + 
					"INNER JOIN TB_RECEBIVEL REC ON REC.DS_SEU_NUMERO = X.DS_SEU_NUMERO\r\n" + 
					"CROSS APPLY DBO.FC_VW_ARQUIVO_IMPORTADO_DETAIL_ID_ARQUIVO (REC.ID_ARQUIVO, REC.NU_SEQ_REGISTRO) AS D\r\n" + 
					"WHERE NOT EXISTS(\r\n" + 
					"	SELECT * FROM TB_MOVIMENTO M \r\n" + 
					"	WHERE M.ID_TIPO_MOVIMENTO = @ID_TIPO_MOVIMENTO\r\n" + 
					"	AND M.ID_RECEBIVEL = X.ID_RECEBIVEL\r\n" + 
					")\r\n" + 
					"ORDER BY X.DT_AQUISICAO DESC\r\n"; 

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			
			if(rs == null) {
				return retorno;
			}
			
			while(rs.next()) {
				
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
													rs.getString("NM_CEDENTE"), 
													rs.getString("DOC_CEDENTE"),
													null,
													layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? null :rs.getString("NU_BANCO"),
													layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? null :  rs.getString("NU_AGENCIA"),
													layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? null :  rs.getString("DIGITO_AG"),
													layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? null :  rs.getString("NU_CONTA"),
													layout.equals(LayoutEnum.CNAB_450_COBRANCA_GENIAL) ? null :  rs.getString("DIGITO_CONTA"));
				
				SacadoDto sacado = new SacadoDto(rs.getInt("ID_SACADO"), 
												 rs.getString("NM_SACADO"), 
												 rs.getString("DOC_SACADO"), 
												 rs.getString("DS_LOGRADOURO"), 
												 rs.getString("NU_CEP"));
				
				
				TituloDto titulo = new TituloDto();
				
				titulo.setCedente(cedente);
				titulo.setSacado(sacado);
				titulo.setChaveNfe(rs.getString("CHAVE_NFE"));
				titulo.setCoobrigacao(rs.getBoolean("IC_COOBRIGACAO") ? "01" : "02");
				titulo.setEspecie(rs.getString("ID_TIPO_ESPECIE"));
				titulo.setNumBanco(rs.getString("NU_BANCO"));
				titulo.setNumeroDocumento(rs.getString("DS_NU_DOCUMENTO"));
				titulo.setSeuNumero(rs.getString("DS_SEU_NUMERO"));
				titulo.setTermoCessao(rs.getString("TERMO_CESSAO"));
				titulo.setValorAquisicao(rs.getBigDecimal("VL_AQUISICAO"));
				titulo.setValorPago(null);
				titulo.setValorTitulo(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setDataVencimento(LocalDate.parse(rs.getString("DT_VENCIMENTO").substring(0, 10)));
				titulo.setDataLiquidacao(LocalDate.now());
				titulo.setDataAquisicao(LocalDate.parse(rs.getString("DT_AQUISICAO").substring(0, 10)));
				
				retorno.add(titulo);
				
			}
			con.close();
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	


}

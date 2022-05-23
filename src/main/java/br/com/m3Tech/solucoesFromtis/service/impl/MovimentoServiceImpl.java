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

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloEmEstoqueDto;
import br.com.m3Tech.solucoesFromtis.querys.Querys;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;


@Service
public class MovimentoServiceImpl implements IMovimentoService, Serializable{

	private static final long serialVersionUID = 1L;

	public List<MovimentoDto> findAllMovimentos(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ALL_MOVIMENTOS);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(retorno.isEmpty()) {
			retorno.add(new MovimentoDto(null, "01", "AQUISICAO"));
		}
		
		return retorno;
	}
	
	@Override
	public MovimentoDto findMovimento(Connection con, Integer cdLayout, Integer cdOcorrencia) {
		
		
		String sqlQuery ="SELECT DISTINCT LM.ID_TIPO_MOVIMENTO, LM.CD_OCORRENCIA, TM.NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"WHERE CD_LAYOUT = ?\r\n" + 
				"AND CD_OCORRENCIA = ?";
		
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null	;
	}

	public List<MovimentoDto> findAllMovimentosAquisicao(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE LM.CD_LAYOUT = ?\r\n" + 
				" AND IC_AQUISICAO = 1\r\n" + 
				" AND TMM.IC_BAIXAR_ATIVO = 0\r\n" + 
				" AND IC_RECOMPRA = 0";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosBaixa(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE LM.CD_LAYOUT = ?\r\n" + 
				" AND TMM.IC_BAIXAR_ATIVO = 1\r\n" + 
				" AND TMM.IC_AQUISICAO = 0\r\n" + 
				" AND TMM.IC_RECOMPRA = 0";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosParcial(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO , TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO \r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_GERA_HIST_RECEBIVEL = 1 \r\n" + 
				"AND TMM.IC_AQUISICAO = 0 \r\n" + 
				"AND TMM.IC_RECOMPRA = 0";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@Override
	public List<MovimentoDto> findAllMovimentosProrrogacao(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO , TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO \r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO \r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_ALTERACAO_VENCIMENTO = 1 \r\n";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<MovimentoDto> findAllMovimentosRecompraAquisicao(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_AQUISICAO = 1";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public List<MovimentoDto> findAllMovimentosRecompraBaixa(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_BAIXAR_ATIVO = 1";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	public List<MovimentoDto> findAllMovimentosRecompraParcial(Connection con, Integer cdLayout) {
		
		List<MovimentoDto> retorno = new ArrayList<MovimentoDto>();
		
		String query = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				"FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				"INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = tm.CD_TIPO_MOVIMENTACAO\r\n" + 
				"WHERE LM.CD_LAYOUT = ?\r\n" + 
				"AND TMM.IC_RECOMPRA = 1\r\n" +
				"AND TMM.IC_GERA_HIST_RECEBIVEL = 1";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, cdLayout);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				MovimentoDto movimento = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
											  			  rs.getString("CD_OCORRENCIA"),
											  			  rs.getString("NM_TIPO_MOVIMENTO"));
				
				retorno.add(movimento);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public List<TituloEmEstoqueDto> findAllTitulosEmEstoque(Connection con, Integer idFundo) {
		
		List<TituloEmEstoqueDto> retorno = new ArrayList<TituloEmEstoqueDto>();
		
		String query = "SELECT * \r\n" + 
				" FROM TB_RECEBIVEL R\r\n" + 
				" INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO\r\n" + 
				" INNER JOIN TB_ESTOQUE E ON E.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n" + 
				" WHERE R.ID_FUNDO = ?\r\n" + 
				" AND E.DT = DATEADD(DAY, -1, F.DT_FUNDO)";
		
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public MovimentoDto findOneMovimentoById(Connection con, Integer idMovimento) {
		MovimentoDto retorno = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(Querys.ONE_MOVIMENTO);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				retorno = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), 
						  					  rs.getString("CD_OCORRENCIA"),
						  					  rs.getString("NM_TIPO_MOVIMENTO"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public Boolean isAquisicao(Connection con, Integer idMovimento) {
		
		
		try {
			
			String query = "SELECT IC_AQUISICAO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE ID_LAYOUT_MOVIMENTO = ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getBoolean("IC_AQUISICAO");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<TituloDto> findAllTituloEmEstoqueByFundo(Connection con, Integer idFundo, boolean isProrrogacao) {
		
		
		try {
			
			String query = "DECLARE @IDFUNDO INT = " + idFundo  +  "\r\n" + 
					"\r\n" + 
					"Select TOP 30 x.*, D.ESPECIE AS ID_TIPO_ESPECIE\r\n" + 
					"from (\r\n" + 
					"	SELECT DISTINCT R.ID_RECEBIVEL,\r\n" + 
					"	R.DS_SEU_NUMERO, \r\n" + 
					"	R.IC_COOBRIGACAO,\r\n" + 
					"	R.DS_NU_DOCUMENTO, \r\n" + 
					"	STG.TERMO_CESSAO, \r\n" + 
					"	STG.CHAVE_NFE, \r\n" + 
					"	R.VL_AQUISICAO,\r\n" + 
					"	(E.VL_NOMINAL - COALESCE(HR.VL_ABATIMENTO,0) -COALESCE(HR.VL_AMORTIZACAO,0) ) AS VL_NOMINAL ,\r\n" + 
					"	FC.ID_CEDENTE,FC.NM_CEDENTE,FC.NU_CPF_CNPJ AS DOC_CEDENTE, \r\n" + 
					"	FS.ID_SACADO, FS.NM_SACADO, FS.NU_CPF_CNPJ AS DOC_SACADO, FS.DS_LOGRADOURO,FS.NU_CEP ,\r\n" + 
					"	B.NU_BANCO, \r\n" + 
					"	R.DT_VENCIMENTO \r\n" + 
					"	FROM TB_ESTOQUE E \r\n" + 
					"	INNER JOIN TB_RECEBIVEL R ON R.ID_RECEBIVEL = E.ID_RECEBIVEL \r\n" + 
					"	LEFT JOIN TB_HISTORICO_RECEBIVEL HR ON HR.ID_RECEBIVEL = R.ID_RECEBIVEL\r\n" + 
					"	INNER JOIN TB_FUNDO F ON F.ID_FUNDO = R.ID_FUNDO \r\n" + 
					"	INNER JOIN TB_FUNDO_CEDENTE FC ON FC.ID_CEDENTE = R.ID_CEDENTE \r\n" + 
					"	INNER JOIN TB_FUNDO_SACADO FS ON FS.ID_SACADO = R.ID_SACADO \r\n" + 
					"	INNER JOIN TB_BANCO B ON B.ID_BANCO = R.ID_BANCO \r\n" + 
					"	INNER JOIN TB_STG_REMESSA STG ON STG.ID_ARQUIVO = R.ID_ARQUIVO AND STG.NU_SEQ_REGISTRO = R.NU_SEQ_REGISTRO  \r\n" + 
					"	WHERE E.ID_FUNDO = @IDFUNDO\r\n" + 
					"	AND E.DT = DBO.FC_TRAZDIAUTIL(DATEADD(DD, - 1, F.DT_FUNDO), 'A') \r\n" + 
					") as X\r\n" + 
					"INNER JOIN TB_RECEBIVEL REC ON REC.DS_SEU_NUMERO = X.DS_SEU_NUMERO\r\n" + 
					"INNER JOIN VW_ARQUIVO_IMPORTADO_DETAIL D ON D.ID_ARQUIVO = REC.ID_ARQUIVO AND D.NU_SEQ_REGISTRO = REC.NU_SEQ_REGISTRO\r\n"; 
					
			if(isProrrogacao) {

					
		   query += "INNER JOIN TB_FUNDO F ON F.ID_FUNDO = @IDFUNDO\r\n" + 
					"AND X.DT_VENCIMENTO BETWEEN F.DT_MIN_VENCIMENTO_ORIGINAL AND F.DT_MAX_VENCIMENTO_ORIGINAL\r\n" + 
					"AND F.PZ_MAX_ALTERACAO_DT_VCTO > (\r\n" + 
					"									SELECT COUNT(*) FROM TB_MOVIMENTO M\r\n" + 
					"									INNER JOIN TB_TIPO_MOVIMENTO TM ON M.ID_TIPO_MOVIMENTO = TM.ID_TIPO_MOVIMENTO\r\n" + 
					"									INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					"									WHERE M.ID_RECEBIVEL = X.ID_RECEBIVEL\r\n" + 
					"									AND IC_ALTERACAO_VENCIMENTO = 1 \r\n" + 
					"							     )\r\n" + 
					"" ;
			}
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			List<TituloDto> retorno = new ArrayList<TituloDto>();
			
			if(rs == null) {
				return retorno;
			}
			
			while(rs.next()) {
				
				CedenteDto cedente = new CedenteDto(rs.getInt("ID_CEDENTE"), 
													rs.getString("NM_CEDENTE"), 
													rs.getString("DOC_CEDENTE"),
													null);
				
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
				titulo.setValorPago(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setValorTitulo(rs.getBigDecimal("VL_NOMINAL"));
				titulo.setDataVencimento(LocalDate.parse(rs.getString("DT_VENCIMENTO").substring(0, 10)));
				titulo.setDataLiquidacao(LocalDate.now());
				
				retorno.add(titulo);
				
			}
			
			return retorno;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<TituloDto>();
	}

	public Boolean isBaixa(Connection con, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_BAIXAR_ATIVO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE ID_LAYOUT_MOVIMENTO = ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getBoolean("IC_BAIXAR_ATIVO");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public Boolean isRecompra(Connection con, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_RECOMPRA\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE ID_LAYOUT_MOVIMENTO = ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getBoolean("IC_RECOMPRA");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public Boolean isProrrogacao(Connection con, Integer idMovimento) {
		try {
			
			String query = "SELECT IC_ALTERACAO_VENCIMENTO\r\n" + 
					" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
					" INNER JOIN TB_TIPO_MOVIMENTACAO TMM ON TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
					" WHERE ID_LAYOUT_MOVIMENTO = ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, idMovimento);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getBoolean("IC_ALTERACAO_VENCIMENTO");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public MovimentoDto getPrimeiroMovimentoAquisicao(Connection con, Integer cdLayout) throws SQLException {
		MovimentoDto retorno = null;

		String sqlQuery = " SELECT TOP 1 ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
				" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
				" INNER JOIN TB_TIPO_MOVIMENTACAO TMM On TMM.CD_TIPO_MOVIMENTACAO = TM.CD_TIPO_MOVIMENTACAO\r\n" + 
				" WHERE CD_LAYOUT = " +cdLayout+ " \r\n" + 
				" AND TMM.IC_AQUISICAO = 1";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		ResultSet rs = ps.getResultSet();

		if (rs.next()) {
			retorno = new MovimentoDto(rs.getInt("ID_LAYOUT_MOVIMENTO"), rs.getString("CD_OCORRENCIA"),
					rs.getString("NM_TIPO_MOVIMENTO"));

		}

		return retorno;
	}
	
	


}

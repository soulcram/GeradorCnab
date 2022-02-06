package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteDto;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.IFundoService;
import br.com.m3Tech.solucoesFromtis.service.IGeracaoCnabPadrao;
import br.com.m3Tech.solucoesFromtis.service.ITesteService;
import br.com.m3Tech.solucoesFromtis.util.FileUtil;
import br.com.m3Tech.solucoesFromtis.util.LocalDateUtils;


@Service
public class TesteServiceImpl implements ITesteService, Serializable{

	private static final long serialVersionUID = 1L;
	
	private final IFundoService fundoService;
	private final IGeracaoCnabPadrao geracaoCnabPadrao;
	private final IArquivoService arquivoService;
	
	private final ConfGlobal confGlobal;
	
	private ArquivoDto arquivo = new ArquivoDto();
	
	private Integer idOperacaoRecebivel;
	private Integer idRecebivel;
	
	@Autowired
	public TesteServiceImpl(final IFundoService fundoService,
			final IGeracaoCnabPadrao geracaoCnabPadrao,
			final IConfGlobalService confGlobalService,
			final IArquivoService arquivoService) {
		
		this.fundoService = fundoService;
		this.geracaoCnabPadrao = geracaoCnabPadrao;
		this.arquivoService = arquivoService;
		
		this.confGlobal = confGlobalService.getConfGlobal();
		
	}

	@Override
	public List<ResultadoTesteDto> testarProcedures(Connection con, FundoDto fundo) {
		
		List<ResultadoTesteDto> retorno = Lists.newArrayList();
		
		LocalDate hoje = LocalDateUtils.getDiaUtil(LocalDate.now());
		
		
		
		FundoDto fundoAntes = fundoService.findOneById(con, fundo.getIdFundo());

		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando a data do fundo" , "O fundo " + fundo.getNomeFundo()+ " está na data: " + fundoAntes.getDataFundo().toString()));
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Processando o fundo " + fundo.getNomeFundo()+ " para a data: " + hoje.toString(), executarProcessarFundo(con, hoje, fundo.getIdFundo())));

		FundoDto fundoDepois = fundoService.findOneById(con, fundo.getIdFundo());
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando a data do fundo" , "O fundo " + fundo.getNomeFundo()+ " está na data: " + fundoDepois.getDataFundo().toString()));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Gerando arquivo de aquisição" , gerarCnabAquisicao(con, fundoDepois)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Inserindo arquivo na base" , inserirArquivoNaBase(con, fundoDepois)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Executando proc Gerar Movimentação" , executarGerarMovimentacao(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando o arquivo na Operação Recebivel" , verificarSeArquivoExisteNaOperacaoRecebivel(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando o arquivo na Staging Remessa" , verificarSeArquivoExisteNaStgRemessa(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Executando proc Intg Movimento" , executarIntgMovimento(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando o arquivo na Movimento" , verificarSeArquivoExisteNaMovimento(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando o arquivo na Recebivel" , verificarSeArquivoExisteNaRecebivel(con)));
		
		LocalDate amanha = LocalDateUtils.getDiaUtil(hoje).plusDays(2);
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Processando o fundo " + fundo.getNomeFundo()+ " para a data: " + amanha.toString(), executarProcessarFundo(con, amanha, fundo.getIdFundo())));
		
		FundoDto fundoAmanha = fundoService.findOneById(con, fundo.getIdFundo());
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando a data do fundo" , "O fundo " + fundo.getNomeFundo()+ " está na data: " + fundoAmanha.getDataFundo().toString()));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando o recebivel na Estoque" , verificarSeRecebivelExisteNaEstoque(con)));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Revertendo o fundo " + fundo.getNomeFundo()+ " para a data: " + fundoAntes.getDataFundo().toString(), executarReverterFundo(con, fundoAntes.getDataFundo(), fundo.getIdFundo())));
		
		FundoDto fundoRevertido = fundoService.findOneById(con, fundo.getIdFundo());
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Verificando a data do fundo" , "O fundo " + fundo.getNomeFundo()+ " está na data: " + fundoRevertido.getDataFundo().toString()));
		
		retorno.add(new ResultadoTesteDto("Testar Procedures", "Removendo Arquivo" , executarRemoverArquivo(con)));
		
		
		return retorno;
	}
	
	private String inserirArquivoNaBase(Connection con, FundoDto fundo)  {
		
		try {
			File diretorio = new File(confGlobal.getPath());
			
			List<String> cnab = null;
			
			String nomeArquivo = null;
			
			for(File f : diretorio.listFiles()) {
				
				nomeArquivo = f.getName();
				cnab = Files.readAllLines(f.toPath(), Charsets.UTF_8);
			}
			
			arquivo = arquivoService.inserirTbArquivo(con, fundo, nomeArquivo);
			
			if(arquivoService.inserirTbArquivoImportado(con, cnab, arquivo)) {
				return "Arquivo inserido na Base com sucesso.";
			}else {
				return "O arquivo não foi inserido na base.";
			}
		
		}catch(Exception e) {
			return "Erro ao inserir arquivo na base: " + e.getMessage();
		}
	}
	
	private String verificarSeRecebivelExisteNaEstoque(Connection con) {
		
		try {
			String sqlQuery = "select * from TB_ESTOQUE where ID_RECEBIVEL = " + idRecebivel ;

			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				
				return "O recebivel com id: " + idRecebivel + " encontrado na TB_ESTOQUE.";
			} else {
				return "ERRO: O recebivel com id: " + idRecebivel + " não foi encontrado na TB_ESTOQUE.";
			}

		} catch (Exception e) {
			return "Erro ao consultar O recebivel com id: " + idRecebivel + " na TB_ESTOQUE. "
					+ e.getMessage();
		}
	}
	
	private String verificarSeArquivoExisteNaOperacaoRecebivel(Connection con) {
		
		try {
			String sqlQuery = "select ID_OPERACAO_RECEBIVEL, ID_LIQ_DC,ST_OPERACAO, * from TB_OPERACAO_RECEBIVEL where ID_ARQUIVO = "
					+ arquivo.getIdArquivo();

			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				
				idOperacaoRecebivel = rs.getInt("ID_OPERACAO_RECEBIVEL");
				
				return "O arquivo com id: " + arquivo.getIdArquivo() + " encontrado na TB_OPERACAO_RECEBIVEL.";
			} else {
				return "ERRO: O arquivo com id: " + arquivo.getIdArquivo()
						+ " não foi encontrado na TB_OPERACAO_RECEBIVEL.";
			}

		} catch (Exception e) {
			return "Erro ao consultar O arquivo com id: " + arquivo.getIdArquivo() + " na TB_OPERACAO_RECEBIVEL. "
					+ e.getMessage();
		}
	}
	
	private String verificarSeArquivoExisteNaStgRemessa(Connection con) {
		try {
			String sqlQuery = "select * from TB_STG_REMESSA where ID_ARQUIVO = "
					+ arquivo.getIdArquivo();

			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				return "O arquivo com id: " + arquivo.getIdArquivo() + " encontrado na TB_STG_REMESSA.";
			} else {
				return "ERRO: O arquivo com id: " + arquivo.getIdArquivo()
						+ " não foi encontrado na TB_STG_REMESSA.";
			}

		} catch (Exception e) {
			return "Erro ao consultar O arquivo com id: " + arquivo.getIdArquivo() + " na TB_STG_REMESSA. "
					+ e.getMessage();
		}
	}
	
	private String verificarSeArquivoExisteNaRecebivel(Connection con) {
		
		try {
			String sqlQuery = "select ID_RECEBIVEL from TB_RECEBIVEL where ID_ARQUIVO = "
					+ arquivo.getIdArquivo();

			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				
				idRecebivel = rs.getInt("ID_RECEBIVEL");
				
				return "O arquivo com id: " + arquivo.getIdArquivo() + " encontrado na TB_RECEBIVEL.";
			} else {
				return "ERRO: O arquivo com id: " + arquivo.getIdArquivo()
						+ " não foi encontrado na TB_RECEBIVEL.";
			}

		} catch (Exception e) {
			return "Erro ao consultar O arquivo com id: " + arquivo.getIdArquivo() + " na TB_RECEBIVEL. "
					+ e.getMessage();
		}
	}
	
	private String verificarSeArquivoExisteNaMovimento(Connection con) {
		try {
			String sqlQuery = "select * from TB_MOVIMENTO where ID_ARQUIVO = "
					+ arquivo.getIdArquivo();

			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				return "O arquivo com id: " + arquivo.getIdArquivo() + " encontrado na TB_MOVIMENTO.";
			} else {
				return "ERRO: O arquivo com id: " + arquivo.getIdArquivo()
						+ " não foi encontrado na TB_MOVIMENTO.";
			}

		} catch (Exception e) {
			return "Erro ao consultar O arquivo com id: " + arquivo.getIdArquivo() + " na TB_MOVIMENTO. "
					+ e.getMessage();
		}
	}
	
	private String gerarCnabAquisicao(Connection con, FundoDto fundo) {
		
		try {
			
			
			
			FileUtil.limparDiretorio(confGlobal.getPath());
			
			geracaoCnabPadrao.gerarCnabAquisicao(con, fundo);
			
			File diretorio = new File(confGlobal.getPath());
			
			if(diretorio.listFiles().length > 0) {
				return "Cnab Aquisição gerado com sucesso.";
			}else {
				return "Cnab Aquisição não foi gerado.";
			}
			
			
		} catch (Exception e) {
			return "Erro ao gerar cnab Aquisição: " + e.getMessage();
		}
		
		
	}
	
	private String executarIntgMovimento(Connection con) {
		
		String sqlQuery ="exec SP_INTG_MOVIMENTO " + idOperacaoRecebivel + ", 'soulcram', 'testeSolucoesFromtis'";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

	
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		
		return "Procedure sp_intg_movimento executada com Sucesso.";
		
	}
	
	private String executarProcessarFundo(Connection con, LocalDate ateAData, Integer idFundo) {
		
		String sqlQuery ="exec sp_ProcessarFundo " + idFundo + ", '" +ateAData.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ "'";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

	
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		
		return "Procedure sp_processarFundo executada com Sucesso.";
		
	}
	
	private String executarRemoverArquivo(Connection con) {

		String sqlQuery = "exec sp_removerArquivo " + arquivo.getIdArquivo();

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

		} catch (SQLException e) {
			return e.getMessage();
		}

		return "Procedure sp_removerArquivo executada com Sucesso.";

	}
	
	private String executarGerarMovimentacao(Connection con) {
		
		String sqlQuery ="exec sp_gerarMovimentacao " + arquivo.getIdArquivo();
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

	
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		
		return "Procedure sp_gerarMovimentacao executada com Sucesso.";
		
	}
	
	private String executarReverterFundo(Connection con, LocalDate ateAData, Integer idFundo) {
		
		String sqlQuery ="exec sp_reverterFundo " + idFundo + ", '" +ateAData.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ "'";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

	
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		
		return "Procedure sp_reverterFundo executada com Sucesso.";
		
	}

//	public List<TipoRecebivelDto> findAllTipoRecebivel(Connection con, Integer cdLayout) {
//		
//		List<TipoRecebivelDto> retorno = new ArrayList<TipoRecebivelDto>();
//		
//		String sqlQuery ="SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
//				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
//				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
//				"WHERE LR.CD_LAYOUT = ?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sqlQuery);
//			
//			ps.setInt(1, cdLayout);
//			
//			ps.execute();
//			
//			ResultSet rs = ps.getResultSet();
//			
//			while(rs.next()) {
//				TipoRecebivelDto TipoRecebivel = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
//											  			  rs.getString("ID_TIPO_ESPECIE"),
//											  			  rs.getString("NM_TIPO_RECEBIVEL"));
//				
//				retorno.add(TipoRecebivel);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(retorno.isEmpty()) {
//			retorno.add(new TipoRecebivelDto(null, "01", "Duplicata"));
//		}
//		
//		return retorno;
//	}
//
//	public TipoRecebivelDto findOneTipoRecebivelById(Connection con, Integer idTipoRecebivel) {
//		TipoRecebivelDto retorno = null;
//		
//		String sqlQuery = "SELECT DISTINCT TR.ID_TIPO_RECEBIVEL, TR.NM_TIPO_RECEBIVEL, LR.ID_TIPO_ESPECIE\r\n" + 
//				"FROM TB_LAYOUT_RECEBIVEL LR\r\n" + 
//				"INNER JOIN TB_TIPO_RECEBIVEL TR ON LR.ID_TIPO_RECEBIVEL = TR.ID_TIPO_RECEBIVEL\r\n" + 
//				"WHERE LR.ID_TIPO_RECEBIVEL = ?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sqlQuery);
//			
//			ps.setInt(1, idTipoRecebivel);
//			
//			ps.execute();
//			
//			ResultSet rs = ps.getResultSet();
//			
//			if(rs.next()) {
//				retorno = new TipoRecebivelDto(rs.getInt("ID_TIPO_RECEBIVEL"), 
//						  					  rs.getString("ID_TIPO_ESPECIE"),
//						  					  rs.getString("NM_TIPO_RECEBIVEL"));
//				
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return retorno;
//	}

}

package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;

import br.com.m3Tech.solucoesFromtis.dto.ArquivoDto;
import br.com.m3Tech.solucoesFromtis.dto.ArquivoImportadoDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IArquivoService;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.service.ITabelaService;
import br.com.m3Tech.solucoesFromtis.util.LocalDateUtils;
import br.com.m3Tech.solucoesFromtis.util.MontarQueryUtils;

@Service
public class ArquivoServiceImpl implements IArquivoService, Serializable {

	private static final long serialVersionUID = 1L;

	private final ITabelaService tabelaService;
	private final IConfGlobalService confGlobalService;

	@Autowired
	public ArquivoServiceImpl(final ITabelaService tabelaService,
			final IConfGlobalService confGlobalService) {
		this.tabelaService = tabelaService;
		this.confGlobalService = confGlobalService;
	}

	@Override
	public ArquivoDto inserirTbArquivo(Base base, FundoDto fundo, String nomeArquivo) throws Exception {

		Map<String, String> parametros = new HashMap<>();

		parametros.put("NM_ARQUIVO", "'" + nomeArquivo + "'");
		parametros.put("DT_ENTRADA", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
		parametros.put("DT_IMPORTACAO", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
		parametros.put("CD_LAYOUT", fundo.getLayoutAquisicao().toString());
		parametros.put("NM_ARQUIVO_ENTRADA", "'" + nomeArquivo + "'");
		parametros.put("IC_STATUS_PROC", "'A'");
		parametros.put("IC_TEMPORARIO", "0");
		parametros.put("ID_USUARIO", "1");
		parametros.put("ID_FUNDO", fundo.getIdFundo().toString());
		parametros.put("TIPO_LAYOUT", "'R'");
		parametros.put("IC_ARQUIVO_RETORNO_GERADO", "0");

		String sqlQuery = MontarQueryUtils.getQueryInsert(parametros, tabelaService.getTabela(base, "TB_ARQUIVO"),
				"ID_ARQUIVO");

		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();
		con.close();
		return findArquivoByNomeArquivo(base, nomeArquivo);
	}

	@Override
	public boolean inserirTbArquivoImportado(Base base, List<String> cnab, ArquivoDto arquivo)
			throws Exception {

		String sqlQuery = "INSERT INTO TB_ARQUIVO_IMPORTADO(ID_ARQUIVO,TP_REGISTRO,DS_REGISTRO,NU_SEQ_REGISTRO,ID_MOTIVO_REJEICAO,TP_REG_ARQUIVO_IMPORTADO)\r\n"
				+ "SELECT " + arquivo.getIdArquivo() + ",0,'" + cnab.get(0) + "',1,null,'R'\r\n" + "\r\n"
				+ "UNION ALL\r\n" + "\r\n" + "SELECT " + arquivo.getIdArquivo() + ",1,'" + cnab.get(1)
				+ "',2,null,'R'\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n" + "SELECT " + arquivo.getIdArquivo() + ",9,'"
				+ cnab.get(2) + "',3,null,'R'\r\n" + "";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();
		con.close();
		return confirmarArquivoImportado(base, arquivo.getIdArquivo());
	}

	private boolean confirmarArquivoImportado(Base base, Integer idArquivo) {

		try {
			String sqlQuery = "select * from TB_ARQUIVO_IMPORTADO where ID_ARQUIVO = " + idArquivo;

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				con.close();
				return true;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public ArquivoDto findArquivoByNomeArquivo(Base base, String nomeArquivo) throws Exception {

		ArquivoDto retorno = null;

		String sqlQuery = "SELECT TOP 1 ID_ARQUIVO, NM_ARQUIVO, DT_ENTRADA\r\n" + "FROM TB_ARQUIVO\r\n"
				+ "WHERE NM_ARQUIVO = '" + nomeArquivo + "'\r\n" + "ORDER BY 1 DESC";

		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
		
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.execute();

		 ResultSet rs = ps.getResultSet();

		if(rs.next()) {
			retorno = new ArquivoDto(rs.getInt("ID_ARQUIVO"), 
                                     rs.getString("NM_ARQUIVO"), 
                                     LocalDateUtils.getLocalDate(rs.getString("DT_ENTRADA"))
                                      );
		}
		
		con.close();
		return retorno;
	}

	@Override
	public Map<Integer, ArquivoImportadoDto> getArquivoImportadoByData(Base base, LocalDate data ){
		
		String pathRepositorio = confGlobalService.getPathRepositorio(base);

		Map<Integer, ArquivoImportadoDto> retorno = Maps.newHashMap();
		
		String sqlQuery = "SELECT A.ID_ARQUIVO, F.ID_FUNDO, NM_FUNDO, NU_CNPJ, CODIGO_ISIN, DT_FUNDO, LAYOUT_AQUISICAO, A.CD_LAYOUT, COALESCE(A.NM_ARQUIVO_ENTRADA, A.NM_ARQUIVO) AS NM_ARQUIVO, AI.DS_REGISTRO\r\n"
				+ "FROM TB_ARQUIVO A WITH(NOLOCK)\r\n"
				+ "INNER JOIN TB_FUNDO F WITH(NOLOCK) ON F.ID_FUNDO = A.ID_FUNDO \r\n"
				+ "INNER JOIN TB_ARQUIVO_IMPORTADO AI WITH(NOLOCK) ON AI.ID_ARQUIVO = A.ID_ARQUIVO\r\n"
				+ "WHERE A.DT_ENTRADA = '"+data.toString()+"'\r\n"
				+ "ORDER BY A.ID_ARQUIVO, AI.NU_SEQ_REGISTRO";
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			while(rs.next()) {
				
				Integer idArquivo = rs.getInt("ID_ARQUIVO");
				
				if(retorno.get(idArquivo) == null) {
					List<String> lista = Lists.newArrayList();
					lista.add(rs.getString("DS_REGISTRO"));
					
					String dataFundo = rs.getString("DT_FUNDO");
					
					FundoDto fundo = new FundoDto(rs.getInt("ID_FUNDO"), 
												  rs.getString("NM_FUNDO"), 
												  rs.getString("NU_CNPJ"),
												  rs.getString("CODIGO_ISIN"),
												  rs.getInt("LAYOUT_AQUISICAO"),
												  LocalDate.parse(dataFundo.length() > 10 ? dataFundo.substring(0,10) : dataFundo));
					
					ArquivoImportadoDto dto = new ArquivoImportadoDto(idArquivo, fundo, rs.getInt("CD_LAYOUT"), 
							rs.getString("NM_ARQUIVO"),pathRepositorio, lista);
					
					retorno.put(idArquivo, dto);
				}else {
					retorno.get(idArquivo).getConteudo().add(rs.getString("DS_REGISTRO"));
				}
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}
	
	@Override
	public void limparbase(Base base, LocalDate data) {


		String sqlQuery = "DECLARE @DT_DO_DIIA DATE = '"+data.toString()+"' \r\n"
				+ "\r\n"
				+ "WHILE EXISTS(select * from TB_FUNDO where CONVERT(DATE,DT_FUNDO) > @DT_DO_DIIA)\r\n"
				+ "BEGIN\r\n"
				+ "	DECLARE @ID_FUNDO BIGINT\r\n"
				+ "    PRINT 'Revertendo FUNDO ' + CONVERT(VARCHAR,@ID_FUNDO)\r\n"
				+ "	select TOP 1 @ID_FUNDO = ID_FUNDO from TB_FUNDO where CONVERT(DATE,DT_FUNDO) > @DT_DO_DIIA\r\n"
				+ "	exec sp_ReverterFundo @ID_FUNDO,  @DT_DO_DIIA\r\n"
				+ "END\r\n"
				+ "\r\n"
				+ "while exists(select top 1 ID_ARQUIVO from TB_ARQUIVO where DT_ENTRADA = @DT_DO_DIIA)\r\n"
				+ "begin\r\n"
				+ "	\r\n"
				+ "	DECLARE @ID_ARQUIVO BIGINT\r\n"
				+ "	--PRINT @ID_ARQUIVO\r\n"
				+ "\r\n"
				+ "	select top 1 @ID_ARQUIVO = ID_ARQUIVO from TB_ARQUIVO where DT_ENTRADA = @DT_DO_DIIA\r\n"
				+ "\r\n"
				+ "	exec sp_RemoverArquivo @ID_ARQUIVO\r\n"
				+ "\r\n"
				+ "end\r\n"
				+ "\r\n"
				+ "select R.ID_REGISTRO_CNAB400 \r\n"
				+ "INTO #REGISTRO\r\n"
				+ "from TB_REGISTRO_CNAB400  R\r\n"
				+ "INNER JOIN TB_ARQUIVO_CNAB400 C ON C.ID_ARQUIVO = R.ID_ARQUIVO\r\n"
				+ "where C.DT_ENTRADA = @DT_DO_DIIA\r\n"
				+ "\r\n"
				+ "while exists(select top 1 * from #REGISTRO)\r\n"
				+ "BEGIN\r\n"
				+ "	\r\n"
				+ "	DECLARE @ID_REGISTRO BIGINT\r\n"
				+ "\r\n"
				+ "	select top 1 @ID_REGISTRO = ID_REGISTRO_CNAB400 from #REGISTRO\r\n"
				+ "	--PRINT @ID_REGISTRO\r\n"
				+ "	delete TB_CAMPO_INVALIDO_CNAB400 where ID_REGISTRO_CNAB400 = @ID_REGISTRO\r\n"
				+ "	delete TB_REGISTRO_CNAB400 where ID_REGISTRO_CNAB400 = @ID_REGISTRO\r\n"
				+ "	delete #REGISTRO where ID_REGISTRO_CNAB400 = @ID_REGISTRO\r\n"
				+ "END\r\n"
				+ "\r\n"
				+ "delete V FROM TB_ARQUIVO_VALIDACAO V\r\n"
				+ "INNER JOIN TB_FILA_IMPORTACAO_ARQUIVO I ON I.ID_FILA_IMPORTACAO_ARQUIVO = V.ID_FILA_IMPORTACAO_ARQUIVO\r\n"
				+ "where CONVERT(DATE,DT_ENTRADA) = @DT_DO_DIIA \r\n"
				+ "\r\n"
				+ "delete TB_ARQUIVO_CNAB400 where DT_ENTRADA = @DT_DO_DIIA\r\n"
				+ "\r\n"
				+ "delete TB_FILA_CARREGAR_CNAB where DATA = @DT_DO_DIIA\r\n"
				+ "delete TB_FILA_ENVIO_CERTIFICADORA where CONVERT(DATE,DT_INICIO_PROCESSAMENTO) = @DT_DO_DIIA \r\n"
				+ "delete TB_FILA_GERAR_MOVIMENTACAO where DATA = @DT_DO_DIIA \r\n"
				+ "delete TB_FILA_IMPORTACAO_ARQUIVO where CONVERT(DATE,DT_ENTRADA) = @DT_DO_DIIA \r\n"
				+ "delete TB_FILA_LOCALIZACAO_ARQUIVO where CONVERT(DATE,DT_ENTRADA) = @DT_DO_DIIA\r\n"
				+ "\r\n"
				+ "PRINT 'Processo Base limpa executada com sucesso'\r\n"
				+ "\r\n"
				+ "drop table #REGISTRO\r\n"
				+ "";
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();
			
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> findChassis(Base base) {
		List<String> retorno = new ArrayList<>();
		try {
			String sqlQuery = "select DS_CHASSI from TB_ARQUIVO_CHASSI_SNG";
	
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
	
			ps.execute();
	
			ResultSet rs = ps.getResultSet();
	
			while(rs.next()) {
				retorno.add(rs.getString("DS_CHASSI"));
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}

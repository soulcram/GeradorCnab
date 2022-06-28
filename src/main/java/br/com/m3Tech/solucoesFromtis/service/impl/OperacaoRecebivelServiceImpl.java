package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.AvalistaDto;
import br.com.m3Tech.solucoesFromtis.dto.CedenteRetornoDto;
import br.com.m3Tech.solucoesFromtis.dto.ConsultoriaDto;
import br.com.m3Tech.solucoesFromtis.dto.ContaCorrenteDto;
import br.com.m3Tech.solucoesFromtis.dto.CustodianteDto;
import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.DadosRetornoCertificadoDigitalDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.ParteDto;
import br.com.m3Tech.solucoesFromtis.dto.ParteRepresentanteDto;
import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.dto.TestemunhaDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloRetornoDto;
import br.com.m3Tech.solucoesFromtis.service.IOperacaoRecebivelService;

@Service
public class OperacaoRecebivelServiceImpl implements IOperacaoRecebivelService, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<DadosOperacaoParaAprovacaoDto> findAllOperacoesAguardandoAprovacao(Connection con, String situacao) {

		List<DadosOperacaoParaAprovacaoDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT F.NU_CNPJ, FC.NU_CPF_CNPJ, A.NM_ARQUIVO, CC.NU_AGENCIA, CC.NU_CONTA, B.NU_BANCO\r\n"
				+ "FROM TB_OPERACAO_RECEBIVEL O\r\n"
				+ "INNER JOIN TB_FUNDO F WITH(NOLOCK) ON F.ID_FUNDO = O.ID_FUNDO\r\n"
				+ "INNER JOIN TB_FUNDO_CEDENTE FC WITH(NOLOCK) ON FC.ID_CEDENTE = O.ID_CEDENTE\r\n"
				+ "INNER JOIN TB_CONTA_CORRENTE CC WITH(NOLOCK) ON CC.ID_CONTA_CORRENTE = O.ID_CONTA_CORRENTE\r\n"
				+ "INNER JOIN TB_BANCO B WITH(NOLOCK) ON B.ID_BANCO = CC.ID_BANCO\r\n"
				+ "INNER JOIN TB_ARQUIVO A WITH(NOLOCK) ON A.ID_ARQUIVO = O.ID_ARQUIVO\r\n" + "WHERE ST_OPERACAO = '"
				+ situacao + "'\r\n" + "AND A.DT_ENTRADA = CONVERT(DATE,GETDATE()) \r\n"
				+ "GROUP BY F.NU_CNPJ, FC.NU_CPF_CNPJ, A.NM_ARQUIVO, CC.NU_AGENCIA, CC.NU_CONTA, B.NU_BANCO";

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				ContaCorrenteDto contaCorrente = new ContaCorrenteDto(rs.getString("NU_AGENCIA"),
						rs.getString("NU_CONTA"), rs.getString("NU_BANCO"));

				retorno.add(new DadosOperacaoParaAprovacaoDto(rs.getString("NU_CNPJ"), rs.getString("NU_CPF_CNPJ"),
						rs.getString("NM_ARQUIVO"), BigDecimal.ZERO, contaCorrente)

				);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	@Override
	public List<DadosRetornoCertificadoDigitalDto> findAllOperacoesAguardandoRetorno(Connection con) {
		
		List<DadosRetornoCertificadoDigitalDto> retorno = new ArrayList<>();
		
		String sqlQuery = "SELECT O.ID_OPERACAO_RECEBIVEL\r\n" + 
				", PC.NM_PESSOA   AS NM_CUSTODIANTE\r\n" + 
				", PC.NU_CPF_CNPJ AS DOC_CUSTODIANTE\r\n" + 
				", O.ID_ORIGINADOR\r\n" + 
				", O.ID_ARQUIVO\r\n" + 
				"\r\n" + 
				", F.ID_FUNDO    \r\n" + 
				", F.NM_FUNDO\r\n" + 
				", F.NU_CNPJ \r\n" + 
				", F.CODIGO_ISIN\r\n" + 
				", F.LAYOUT_AQUISICAO\r\n" + 
				", CONVERT(DATE,F.DT_FUNDO) AS DT_FUNDO\r\n" + 
				"\r\n" + 
				", FC.ID_CEDENTE\r\n" + 
				", FC.NU_CPF_CNPJ AS DOC_CEDENTE\r\n" + 
				", FC.NM_CEDENTE \r\n" + 
				", FC.DS_LOGRADOURO \r\n" + 
				", FC.NU_LOGRADOURO \r\n" + 
				", FC.DS_COMPLEMENTO \r\n" + 
				", FC.DS_BAIRRO \r\n" + 
				", FC.DS_CIDADE \r\n" + 
				", FC.DS_ESTADO \r\n" + 
				", FC.NU_CEP  \r\n" + 
				", FC.NU_TELEFONE \r\n" + 
				", FC.INSCRICAO_ESTADUAL \r\n" + 
				", FC.INSCRICAO_MUNICIPAL \r\n" + 
				"\r\n" + 
				", P.NM_PESSOA   AS NM_CONSULTORIA\r\n" + 
				", P.NU_CPF_CNPJ AS DOC_CONSULTORIA\r\n" + 
				", B.NU_BANCO\r\n" + 
				", CC.NU_AGENCIA\r\n" + 
				", CC.NU_CONTA\r\n" + 
				"\r\n" + 
				"FROM TB_OPERACAO_RECEBIVEL O\r\n" + 
				"INNER JOIN TB_FUNDO_CEDENTE FC WITH(NOLOCK) ON FC.ID_CEDENTE = O.ID_CEDENTE\r\n" + 
				"INNER JOIN TB_PESSOA ORI WITH(NOLOCK) ON ORI.ID_PESSOA = O.ID_ORIGINADOR\r\n" + 
				"INNER JOIN TB_FUNDO F WITH(NOLOCK) ON F.ID_FUNDO = O.ID_FUNDO\r\n" + 
				"INNER JOIN TB_PESSOA PC WITH(NOLOCK) ON PC.ID_PESSOA = F.ID_CUSTODIANTE\r\n" + 
				"INNER JOIN TB_PESSOA P WITH(NOLOCK) ON P.ID_PESSOA = O.ID_ORIGINADOR\r\n" + 
				"INNER JOIN TB_ARQUIVO A WITH(NOLOCK) ON A.ID_ARQUIVO = O.ID_ARQUIVO\r\n" + 
				"INNER JOIN TB_CONTA_CORRENTE CC WITH(NOLOCK) ON CC.ID_CONTA_CORRENTE = O.ID_CONTA_CORRENTE\r\n" + 
				"INNER JOIN TB_BANCO B WITH(NOLOCK) ON B.ID_BANCO = CC.ID_BANCO\r\n" + 
				"WHERE O.ST_OPERACAO = 'AC'\r\n" + 
				"AND A.DT_ENTRADA = CONVERT(DATE, GETDATE())";

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {
				
				Integer idFundo = rs.getInt("ID_FUNDO");
				Integer idOperacao = rs.getInt("ID_OPERACAO_RECEBIVEL");
				Integer idOriginador = rs.getInt("ID_ORIGINADOR");
				Integer idCedente = rs.getInt("ID_CEDENTE");
				Integer idArquivo = rs.getInt("ID_ARQUIVO");
				
				OperacaoDto operacao = getOperacao(con, idOperacao);
				
				
				CustodianteDto custodiante = new CustodianteDto(rs.getString("NM_CUSTODIANTE"), rs.getString("DOC_CUSTODIANTE"));
				
				
				FundoDto fundo = new FundoDto(idFundo, rs.getString("NM_FUNDO"), rs.getString("NU_CNPJ"), 
						rs.getString("CODIGO_ISIN"), rs.getInt("LAYOUT_AQUISICAO"), 
						LocalDate.parse(rs.getString("DT_FUNDO")), getPartesFundo(con, idFundo), getTestemunhasFundo(con, idFundo));
				
				ConsultoriaDto consultoria = new ConsultoriaDto(rs.getString("NM_CONSULTORIA"), rs.getString("DOC_CONSULTORIA"), getPartesConsultoria(con, idOriginador));
				
				CedenteRetornoDto cedente = new CedenteRetornoDto(rs.getString("DOC_CEDENTE"), rs.getString("NM_CEDENTE"), rs.getString("NU_BANCO"), 
						rs.getString("NU_AGENCIA"), rs.getString("NU_CONTA"), rs.getString("DS_LOGRADOURO"), rs.getString("NU_LOGRADOURO"), 
						rs.getString("DS_COMPLEMENTO"), rs.getString("DS_BAIRRO"), rs.getString("DS_CIDADE"), rs.getString("DS_ESTADO"), 
						rs.getString("NU_CEP"), rs.getString("NU_TELEFONE"), rs.getString("INSCRICAO_ESTADUAL"), 
						rs.getString("INSCRICAO_MUNICIPAL"), getPartesCedente(con, idCedente), getAvalistas(con, idOperacao), getTitulos(con, idArquivo), getTitulosRecompra(con, idArquivo));
						
				retorno.add(new DadosRetornoCertificadoDigitalDto(idOperacao, 
						operacao, 
						custodiante, 
						fundo, 
						consultoria, 
						cedente));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private OperacaoDto getOperacao(Connection con, Integer idOperacao) {

		String sqlQuery = "SELECT \r\n"
				+ "	SUM(S.VL_PAGO)                                                                      AS  VALOR_LIQUIDO, \r\n"
				+ "    SUM(S.VL_NOMINAL)                                                                   AS VALOR_BRUTO,\r\n"
				+ "    0                                                                                   AS COM_ADIANTAMENTO,\r\n"
				+ "    MIN(S.TERMO_CESSAO)                                                                 AS TERMO_CESSAO,\r\n"
				+ "    SUM(CASE WHEN TM.IC_RECOMPRA = 1 AND TM.IC_AQUISICAO = 0 THEN S.VL_PAGO ELSE 0 END) AS VALOR_RECOMPRA\r\n"
				+ "FROM TB_OPERACAO_RECEBIVEL O\r\n"
				+ "INNER JOIN TB_STG_REMESSA  S WITH(NOLOCK) ON S.ID_ARQUIVO = O.ID_ARQUIVO\r\n"
				+ "INNER JOIN TB_TIPO_MOVIMENTO T ON T.ID_TIPO_MOVIMENTO = S.ID_TIPO_MOVIMENTO\r\n"
				+ "INNER JOIN TB_TIPO_MOVIMENTACAO TM ON TM.CD_TIPO_MOVIMENTACAO = T.CD_TIPO_MOVIMENTACAO\r\n"
				+ "WHERE O.ID_OPERACAO_RECEBIVEL = " + idOperacao;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {

				OperacaoDto operacao = new OperacaoDto(rs.getBigDecimal("VALOR_LIQUIDO"),
						rs.getBigDecimal("VALOR_BRUTO"), false, rs.getString("TERMO_CESSAO"),
						rs.getBigDecimal("VALOR_RECOMPRA"));

				return operacao;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private OperacaoDto getCustodiante(Connection con, Integer idOperacao) {

		String sqlQuery = "SELECT \r\n"
				+ "	SUM(S.VL_PAGO)                                                                      AS  VALOR_LIQUIDO, \r\n"
				+ "    SUM(S.VL_NOMINAL)                                                                   AS VALOR_BRUTO,\r\n"
				+ "    0                                                                                   AS COM_ADIANTAMENTO,\r\n"
				+ "    MIN(S.TERMO_CESSAO)                                                                 AS TERMO_CESSAO,\r\n"
				+ "    SUM(CASE WHEN TM.IC_RECOMPRA = 1 AND TM.IC_AQUISICAO = 0 THEN S.VL_PAGO ELSE 0 END) AS VALOR_RECOMPRA\r\n"
				+ "FROM TB_OPERACAO_RECEBIVEL O\r\n"
				+ "INNER JOIN TB_STG_REMESSA  S WITH(NOLOCK) ON S.ID_ARQUIVO = O.ID_ARQUIVO\r\n"
				+ "INNER JOIN TB_TIPO_MOVIMENTO T ON T.ID_TIPO_MOVIMENTO = S.ID_TIPO_MOVIMENTO\r\n"
				+ "INNER JOIN TB_TIPO_MOVIMENTACAO TM ON TM.CD_TIPO_MOVIMENTACAO = T.CD_TIPO_MOVIMENTACAO\r\n"
				+ "WHERE O.ID_OPERACAO_RECEBIVEL = " + idOperacao;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {

				OperacaoDto operacao = new OperacaoDto(rs.getBigDecimal("VALOR_LIQUIDO"),
						rs.getBigDecimal("VALOR_BRUTO"), false, rs.getString("TERMO_CESSAO"),
						rs.getBigDecimal("VALOR_RECOMPRA"));

				return operacao;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private List<ParteDto> getPartesFundo(Connection con, Integer idFundo) {

		List<ParteDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT	'Individual' as TIPO\r\n" + "    , R.NM_REPRESENTANTE \r\n"
				+ "    , R.NU_CPF_CNPJ AS DOC_REPRESENTANTE\r\n" + "    , R.DS_EMAIL \r\n"
				+ "    , 'Representante' as PAPEL\r\n" + "  FROM TB_ASSOC_FUNDO_REPRESENTANTE FR \r\n"
				+ "  INNER JOIN TB_REPRESENTANTE R ON R.ID_REPRESENTANTE = FR.ID_REPRESENTANTE\r\n"
				+ "  WHERE FR.ID_FUNDO = " + idFundo;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				ParteDto parte = new ParteDto(rs.getString("NM_REPRESENTANTE"), rs.getString("DOC_REPRESENTANTE"),
						rs.getString("DS_EMAIL"), rs.getString("PAPEL"), rs.getString("TIPO"));

				retorno.add(parte);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<ParteDto> getPartesConsultoria(Connection con, Integer idOriginador) {

		List<ParteDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT 'Individual' as TIPO\r\n" + "	, R.NM_REPRESENTANTE\r\n"
				+ "    , R.NU_CPF_CNPJ AS DOC_REPRESENTANTE_CONSULTORIA\r\n" + "    , R.DS_EMAIL \r\n"
				+ "    , 'Representante' as PAPEL\r\n" + "FROM TB_PESSOA ORI \r\n"
				+ "INNER JOIN TB_ASSOC_PESSOA_REPRESENTANTE APR ON APR.ID_PESSOA = ORI.ID_PESSOA\r\n"
				+ "INNER JOIN TB_REPRESENTANTE R ON R.ID_REPRESENTANTE = APR.ID_REPRESENTANTE \r\n"
				+ "WHERE ORI.ID_PESSOA = " + idOriginador;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				ParteDto parte = new ParteDto(rs.getString("NM_REPRESENTANTE"),
						rs.getString("DOC_REPRESENTANTE_CONSULTORIA"), rs.getString("DS_EMAIL"), rs.getString("PAPEL"),
						rs.getString("TIPO"));

				retorno.add(parte);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<TestemunhaDto> getTestemunhasFundo(Connection con, Integer idFundo) {

		List<TestemunhaDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT	\r\n" + "      FR.NM_TESTEMUNHA \r\n" + "    , FR.NU_CPF AS DOC_TESTEMUNHA\r\n"
				+ "    , FR.DS_EMAIL \r\n" + "  FROM TB_FUNDO_TESTEMUNHA FR \r\n" + "  WHERE FR.ID_FUNDO = " + idFundo;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				TestemunhaDto testemunha = new TestemunhaDto(rs.getString("NM_TESTEMUNHA"),
						rs.getString("DOC_TESTEMUNHA"), rs.getString("DS_EMAIL"));

				retorno.add(testemunha);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<ParteRepresentanteDto> getPartesCedente(Connection con, Integer idCedente) {

		List<ParteRepresentanteDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT R.NM_REPRESENTANTE \r\n" + "       , R.NU_CPF_CNPJ \r\n" + "	   , r.DS_EMAIL \r\n"
				+ "       , 'Representante' as PAPEL\r\n" + "	   , R.IC_ASS_ISOLADAMENTE\r\n"
				+ "       , R.IC_EMITE_DUPLICATA \r\n" + "       , R.IC_ASS_POR_ENDOSSO \r\n"
				+ "       , R.IC_ASS_TERMO_CESSAO\r\n" + "FROM TB_ASSOC_CEDENTE_REPRESENTANTE ACR \r\n"
				+ "INNER JOIN TB_REPRESENTANTE R ON R.ID_REPRESENTANTE = ACR.ID_REPRESENTANTE\r\n"
				+ "WHERE ACR.ID_CEDENTE =" + idCedente;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				ParteRepresentanteDto parte = new ParteRepresentanteDto(rs.getString("NM_REPRESENTANTE"),
						rs.getString("NU_CPF_CNPJ"), rs.getString("DS_EMAIL"), rs.getString("PAPEL"),
						rs.getBoolean("IC_ASS_ISOLADAMENTE"), rs.getBoolean("IC_EMITE_DUPLICATA"),
						rs.getBoolean("IC_ASS_POR_ENDOSSO"), rs.getBoolean("IC_ASS_TERMO_CESSAO"));

				retorno.add(parte);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<AvalistaDto> getAvalistas(Connection con, Integer idOperacao) {

		List<AvalistaDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT AOR.NM_AVALISTA \r\n" + "      , AOR.NU_CPF_CNPJ \r\n" + "      , AOR.DS_EMAIL \r\n"
				+ "      , 'Avalista'  as PAPEL\r\n" + "    FROM TB_AVALISTA_OPERACAO_RECEBIVEL AOR \r\n"
				+ "	WHERE AOR.ID_OPERACAO_RECEBIVEL = " + idOperacao;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			while (rs.next()) {

				AvalistaDto avalista = new AvalistaDto(rs.getString("NM_AVALISTA"), rs.getString("NU_CPF_CNPJ"),
						rs.getString("DS_EMAIL"), rs.getString("PAPEL"));

				retorno.add(avalista);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<TituloRetornoDto> getTitulos(Connection con, Integer idArquivo) {

		List<TituloRetornoDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT TOP 100 TR.NM_TIPO_RECEBIVEL \r\n" + "	  , ST.DS_NU_DOCUMENTO \r\n"
				+ "      , ST.DS_SEU_NUMERO \r\n" + "      , CONVERT(DATE,ST.DT) AS DT \r\n" + "      , CONVERT(DATE,ST.DT_VENCIMENTO) AS DT_VENCIMENTO\r\n"
				+ "      , ST.VL_NOMINAL AS VALOR_BRUTO\r\n" + "      , ST.VL_PAGO AS VALOR_LIQUIDO\r\n"
				+ "      , ST.NUM_NOTA_FISCAL \r\n" + "      , ST.SERIE_NOTA_FISCAL \r\n" + "      , ST.CHAVE_NFE \r\n"
				+ "      , FS.NM_SACADO \r\n" + "      , FS.NU_CPF_CNPJ AS DOC_SACADO\r\n"
				+ "      , FS.DS_LOGRADOURO \r\n" + "      , FS.NU_LOGRADOURO \r\n" + "      , FS.DS_COMPLEMENTO \r\n"
				+ "	  , FS.DS_EMAIL \r\n" + "	  , FS.NU_CEP\r\n" + "	  , FS.DS_BAIRRO\r\n"
				+ "	  , FS.DS_CIDADE\r\n" + "	  , FS.DS_ESTADO\r\n"
				+ "      , CASE WHEN LEN(FS.NU_CPF_CNPJ) = 11 THEN 'CPF' ELSE 'CNPJ' END  AS TIPO\r\n" + "\r\n"
				+ "    FROM TB_STG_REMESSA ST\r\n"
				+ "    INNER JOIN TB_TIPO_MOVIMENTO T WITH(NOLOCK) ON T.ID_TIPO_MOVIMENTO = ST.ID_TIPO_MOVIMENTO\r\n"
				+ "    INNER JOIN TB_TIPO_MOVIMENTACAO TM WITH(NOLOCK) ON TM.CD_TIPO_MOVIMENTACAO = T.CD_TIPO_MOVIMENTACAO\r\n"
				+ "	INNER JOIN TB_TIPO_RECEBIVEL TR WITH(NOLOCK) ON TR.ID_TIPO_RECEBIVEL = ST.ID_TIPO_RECEBIVEL\r\n"
				+ "	INNER JOIN TB_FUNDO_SACADO FS WITH(NOLOCK) ON FS.ID_SACADO = ST.ID_SACADO\r\n"
				+ "    WHERE TM.IC_RECOMPRA = 0\r\n" + "	 AND TM.IC_AQUISICAO = 1 \r\n" + "	 AND ST.ID_ARQUIVO = "
				+ idArquivo;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			int count = 1;

			while (rs.next()) {

				SacadoDto sacado = new SacadoDto(null, rs.getString("NM_SACADO"), rs.getString("DOC_SACADO"),
						rs.getString("DS_LOGRADOURO"), rs.getString("NU_CEP"), rs.getString("NU_LOGRADOURO"),
						rs.getString("DS_COMPLEMENTO"), rs.getString("DS_EMAIL"), rs.getString("TIPO"),
						rs.getString("DS_BAIRRO"), rs.getString("DS_CIDADE"), rs.getString("DS_ESTADO"), "Brasil",
						null);

				TituloRetornoDto titulo = new TituloRetornoDto(rs.getString("DS_NU_DOCUMENTO"),
						rs.getString("DS_SEU_NUMERO"), LocalDate.parse(rs.getString("DT")),
						LocalDate.parse(rs.getString("DT_VENCIMENTO")), rs.getBigDecimal("VALOR_BRUTO"),
						rs.getBigDecimal("VALOR_LIQUIDO"), rs.getString("NUM_NOTA_FISCAL"),
						rs.getString("SERIE_NOTA_FISCAL"), "arquivoPkcs7" + count, rs.getString("CHAVE_NFE"), sacado,
						false, rs.getString("NM_TIPO_RECEBIVEL"));

				retorno.add(titulo);
				count++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	private List<TituloRetornoDto> getTitulosRecompra(Connection con, Integer idArquivo) {

		List<TituloRetornoDto> retorno = new ArrayList<>();

		String sqlQuery = "SELECT TOP 100 TR.NM_TIPO_RECEBIVEL \r\n" + "	  , ST.DS_NU_DOCUMENTO \r\n"
				+ "      , ST.DS_SEU_NUMERO \r\n" + "      , ST.DT \r\n" + "      , ST.DT_VENCIMENTO\r\n"
				+ "      , ST.VL_NOMINAL AS VALOR_BRUTO\r\n" + "      , ST.VL_PAGO AS VALOR_LIQUIDO\r\n"
				+ "      , ST.NUM_NOTA_FISCAL \r\n" + "      , ST.SERIE_NOTA_FISCAL \r\n" + "      , ST.CHAVE_NFE \r\n"
				+ "      , FS.NM_SACADO \r\n" + "      , FS.NU_CPF_CNPJ AS DOC_SACADO\r\n"
				+ "      , FS.DS_LOGRADOURO \r\n" + "      , FS.NU_LOGRADOURO \r\n" + "      , FS.DS_COMPLEMENTO \r\n"
				+ "	  , FS.DS_EMAIL \r\n" + "	  , FS.NU_CEP\r\n" + "	  , FS.DS_BAIRRO\r\n"
				+ "	  , FS.DS_CIDADE\r\n" + "	  , FS.DS_ESTADO\r\n"
				+ "      , CASE WHEN LEN(FS.NU_CPF_CNPJ) = 11 THEN 'CPF' ELSE 'CNPJ' END  AS TIPO\r\n" + "\r\n"
				+ "    FROM TB_STG_REMESSA ST\r\n"
				+ "    INNER JOIN TB_TIPO_MOVIMENTO T WITH(NOLOCK) ON T.ID_TIPO_MOVIMENTO = ST.ID_TIPO_MOVIMENTO\r\n"
				+ "    INNER JOIN TB_TIPO_MOVIMENTACAO TM WITH(NOLOCK) ON TM.CD_TIPO_MOVIMENTACAO = T.CD_TIPO_MOVIMENTACAO\r\n"
				+ "	INNER JOIN TB_TIPO_RECEBIVEL TR WITH(NOLOCK) ON TR.ID_TIPO_RECEBIVEL = ST.ID_TIPO_RECEBIVEL\r\n"
				+ "	INNER JOIN TB_FUNDO_SACADO FS WITH(NOLOCK) ON FS.ID_SACADO = ST.ID_SACADO\r\n"
				+ "    WHERE TM.IC_RECOMPRA = 1\r\n" + "	 AND TM.IC_AQUISICAO = 1 \r\n" + "	 AND ST.ID_ARQUIVO = "
				+ idArquivo;

		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ResultSet rs = ps.getResultSet();

			int count = 1;

			while (rs.next()) {

				SacadoDto sacado = new SacadoDto(null, rs.getString("NM_SACADO"), rs.getString("DOC_SACADO"),
						rs.getString("DS_LOGRADOURO"), rs.getString("NU_CEP"), rs.getString("NU_LOGRADOURO"),
						rs.getString("DS_COMPLEMENTO"), rs.getString("DS_EMAIL"), rs.getString("TIPO"),
						rs.getString("DS_BAIRRO"), rs.getString("DS_CIDADE"), rs.getString("DS_ESTADO"), "Brasil",
						null);

				TituloRetornoDto titulo = new TituloRetornoDto(rs.getString("DS_NU_DOCUMENTO"),
						rs.getString("DS_SEU_NUMERO"), LocalDate.parse(rs.getString("DT")),
						LocalDate.parse(rs.getString("DT_VENCIMENTO")), rs.getBigDecimal("VALOR_BRUTO"),
						rs.getBigDecimal("VALOR_LIQUIDO"), rs.getString("NUM_NOTA_FISCAL"),
						rs.getString("SERIE_NOTA_FISCAL"), "arquivoPkcs7" + count, rs.getString("CHAVE_NFE"), sacado,
						false, rs.getString("NM_TIPO_RECEBIVEL"));

				retorno.add(titulo);
				count++;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

}

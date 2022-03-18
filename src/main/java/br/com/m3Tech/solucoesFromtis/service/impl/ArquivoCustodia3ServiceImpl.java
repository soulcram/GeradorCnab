package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.service.IArquivoCustodia3Service;
import br.com.m3Tech.solucoesFromtis.service.IMovimentoService;
import br.com.m3Tech.solucoesFromtis.service.ITipoRecebivelService;


@Service
public class ArquivoCustodia3ServiceImpl implements IArquivoCustodia3Service, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITipoRecebivelService tipoRecebivelService;
	
	@Autowired
	private IMovimentoService movimentoService;
	
	@Override
	public void processar(Connection con, FundoDto fundo, String nomeArquivo, List<String> readAllLines) throws SQLException {

		Integer id = inserirTB_IM_STG_ARQ(con, fundo.getIdFundo(), nomeArquivo);
		
		for(String s : readAllLines) {
			
			Integer seqRegistro = Integer.valueOf(s.substring(494, 500));
			
			String sqlQuery = "INSERT INTO TB_IM_STG_ARQ_IMP (ID_IM_STG_ARQ, DS_REGISTRO, NU_SEQ_REGISTRO)\r\n" + 
					"VALUES("+ id +",'" + s + "',"+seqRegistro+")";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ps.close();
			
		}
		
		executarProcSp_im_arq_validador(con, id);
		executarProcSP_IM_REMESSA(con);
		
	}

	@Override
	public void processar(Connection con, FundoDto fundo,OriginadorDto originador, String nomeArquivo, CnabHeader header,
			List<CnabDetail> listaDetail, CnabTrailler cnabTrailler) throws SQLException {
		Integer id = inserirTB_IM_STG_ARQ(con, fundo.getIdFundo(), nomeArquivo);
		
		inserirTB_IM_STG_ARQ_TEMP(con, fundo, header, listaDetail, cnabTrailler, id, originador);
		
		executarProcSP_IM_REMESSA(con);
		
		
		
	}

	private void executarProcSp_im_arq_validador(Connection con, Integer idArquivo) {
		String sqlQuery ="exec sp_im_arq_validador " + idArquivo;
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ps.close();
	
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void executarProcSP_IM_REMESSA(Connection con) {
		String sqlQuery ="exec SP_IM_REMESSA ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ps.execute();

			ps.close();
	
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void inserirTB_IM_STG_ARQ_TEMP(Connection con, FundoDto fundo, CnabHeader header,
			List<CnabDetail> listaDetail, CnabTrailler cnabTrailler, Integer id, OriginadorDto originador) throws SQLException {
		
		
		for(CnabDetail detail : listaDetail) {
			
			TipoRecebivelDto tipoRecebivel = tipoRecebivelService.findTipoRecebivel(con, 24, Integer.parseInt(detail.getEspecieTitulo()));
			
			if(tipoRecebivel == null) {
				
				JOptionPane.showMessageDialog(null,"Especie recebivel " +detail.getEspecieTitulo()+ " não cadastrada para o Layout 24");
				return;
			}
			
			MovimentoDto movimentoDto = movimentoService.findMovimento(con, 24, Integer.parseInt(detail.getIdentOcorrencia()));
			
			if(movimentoDto == null) {
				
				JOptionPane.showMessageDialog(null,"Ocorrencia " +detail.getIdentOcorrencia()+ "  não cadastrada para o Layout 24");
				return;
			}
		
			String sqlQuery = "INSERT INTO TB_IM_STG_ARQ_TEMP(\r\n" + 
					"ID_FUNDO	\r\n" + 
					",NU_SEQ_REGISTRO	\r\n" + 
					",DT_AQUISICAO	\r\n" + 
					",DT_VENCIMENTO	\r\n" + 
					",DS_NU_DOCUMENTO	\r\n" + 
					",DS_SEU_NUMERO	\r\n" + 
					",TX_JURO	\r\n" + 
					",VL_PAGO	\r\n" + 
					",VL_NOMINAL	\r\n" + 
					",ID_BANCO	\r\n" + 
					",ID_ORIGINADOR	\r\n" + 
					",ID_TIPO_RECEBIVEL	\r\n" + 
					",DT_EMISSAO_TITULO	\r\n" + 
					",NU_CPF_CNPJ_SACADO	\r\n" + 
					",NM_SACADO	\r\n" + 
					",DS_LOGRADOURO_SACADO\r\n" + 
					",NU_LOGRADOURO_SACADO	\r\n" + 
					",NU_CEP_SACADO	\r\n" + 
					",DT_CESSAO	\r\n" + 
					",id_tipo_movimento	\r\n" + 
					",TERMO_CESSAO	\r\n" + 
					",CHAVE_NFE	\r\n" + 
					",IC_COOBRIGACAO	\r\n" + 
					",CD_MODALIDADE	\r\n" + 
					",NU_CNPJ_ORIGINADOR	\r\n" + 
					",CEDENTE_NU_CPF_CNPJ	\r\n" + 
					",ID_IM_STG_ARQ	\r\n" + 
					",DT_ATUAL	\r\n" + 
					",MULTA	\r\n" + 
					",MORA	\r\n" + 
					",RISCO_ATRASO	\r\n" + 
					",CODIGO_OPERACAO)\r\n" + 
					"VALUES(\r\n" + 
					 fundo.getIdFundo() + "--ID_FUNDO	select * from TB_FUNDO\r\n" + 
					"," + detail.getNumSeqRegistro() +"--NU_SEQ_REGISTRO	\r\n" + 
					",'" +LocalDate.now().toString()+"'	--DT_AQUISICAO	\r\n" + 
					",'" + detail.getDataVencimento() +"'	--DT_VENCIMENTO	\r\n" + 
					",'" + detail.getNumDocumento() + "'	--DS_NU_DOCUMENTO	\r\n" + 
					",'" + detail.getSeuNumero() + "'	--DS_SEU_NUMERO	\r\n" + 
					",00.139900	--TX_JURO	\r\n" + 
					"," + detail.getValorAquisicao() + "	--VL_PAGO	\r\n" + 
					"," + detail.getValorTitulo() + "--VL_NOMINAL	\r\n" + 
					"," + detail.getNumeroBanco() + "	--ID_BANCO	\r\n" + 
					"," + originador.getIdOriginador() + "	--ID_ORIGINADOR	 select * from TB_FUNDO_ORIGINADOR\r\n" + 
					"," + tipoRecebivel.getIdTipoRecebivel() + "	--ID_TIPO_RECEBIVEL	   select * from TB_TIPO_RECEBIVEL\r\n" + 
					",'"+ LocalDate.now().toString() +"'	--DT_EMISSAO_TITULO	\r\n" + 
					",'"+ detail.getDocSacado() +"'	--NU_CPF_CNPJ_SACADO	\r\n" + 
					",'"+ detail.getNomeSacado()+"'	--NM_SACADO	\r\n" + 
					",'"+ detail.getEnderecoSacado()+"'	--DS_LOGRADOURO_SACADO\r\n" + 
					",NULL	--NU_LOGRADOURO_SACADO	\r\n" + 
					",'"+detail.getCepSacado()+"'	--NU_CEP_SACADO	\r\n" + 
					",'"+ LocalDate.now().toString() +"'	--DT_CESSAO	\r\n" + 
					","+movimentoDto.getIdMovimento()+"	--id_tipo_movimento	\r\n" + 
					",'"+detail.getTermoCessao()+"'	--TERMO_CESSAO	\r\n" + 
					",NULL	--CHAVE_NFE	\r\n" + 
					",'"+detail.getCoobrigacao()+"'	--IC_COOBRIGACAO	\r\n" + 
					",'0000'	--CD_MODALIDADE	\r\n" + 
					",'"+originador.getDocOriginador()+"'	--NU_CNPJ_ORIGINADOR	select * from TB_PESSOA where ID_PESSOA = 4148815\r\n" + 
					",'"+detail.getDocCedente()+"'	--CEDENTE_NU_CPF_CNPJ	select * from TB_FUNDO_CEDENTE where ID_FUNDO = 2\r\n" + 
					"," + id + " 	--ID_IM_STG_ARQ	\r\n" + 
					",'"+ LocalDate.now().toString() +"'	--DT_ATUAL	\r\n" + 
					",0.02	--MULTA	\r\n" + 
					",0.01	--MORA	\r\n" + 
					",'"+detail.getRisco()+"'	--RISCO_ATRASO	\r\n" + 
					",'05'	--CODIGO_OPERACAO\r\n" + 
					")";
			
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			
			ps.execute();
	        ps.close();
			
			}
		
	}

	private Integer inserirTB_IM_STG_ARQ(Connection con, Integer idFundo, String nomeArquivo) throws SQLException {

		String sqlQuery = "INSERT INTO TB_IM_STG_ARQ(\r\n" + 
				"ID_FUNDO\r\n" + 
				",NM_ARQUIVO\r\n" + 
				",DT_IMPORTACAO\r\n" + 
				",IMP_STATUS\r\n" + 
				")values(\r\n" + 
				 idFundo + "\r\n" + 
				",'" + nomeArquivo +"'\r\n" + 
				",'" + LocalDate.now().toString() +"'\r\n" + 
				",1\r\n" + 
				")";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
//		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		Integer id = null;
		
        if(rs.next())
        {
        	id = rs.getInt(1);
        }
		
        rs.close();
        ps.close();
        
		return id;
	}



//
//	@Override
//	public ArquivoDto inserirTbArquivo(Connection con, FundoDto fundo, String nomeArquivo) throws SQLException {
//		
//		Map<String,String> parametros = new HashMap<>();
//		
//		parametros.put("NM_ARQUIVO", "'"+nomeArquivo+"'");
//		parametros.put("DT_ENTRADA", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
//		parametros.put("DT_IMPORTACAO", "Convert(Date,'" + fundo.getDataFundo().toString() + "')");
//		parametros.put("CD_LAYOUT", fundo.getLayoutAquisicao().toString());
//		parametros.put("NM_ARQUIVO_ENTRADA", "'"+nomeArquivo+"'");
//		parametros.put("IC_STATUS_PROC", "'A'");
//		parametros.put("IC_TEMPORARIO", "0");
//		parametros.put("ID_USUARIO", "1");
//		parametros.put("ID_FUNDO", fundo.getIdFundo().toString());
//		parametros.put("TIPO_LAYOUT", "'R'");
//		parametros.put("IC_ARQUIVO_RETORNO_GERADO", "0");
//		
//		
//		String sqlQuery = MontarQueryUtils.getQueryInsert(parametros, tabelaService.getTabela(con, "TB_ARQUIVO"), "ID_ARQUIVO");
//		
//		PreparedStatement ps = con.prepareStatement(sqlQuery);
//		
//		ps.execute();
//		
//		return findArquivoByNomeArquivo(con, nomeArquivo);
//	}
//
//	@Override
//	public boolean inserirTbArquivoImportado(Connection con, List<String> cnab, ArquivoDto arquivo) throws SQLException {	
//		
//		String sqlQuery = "INSERT INTO TB_ARQUIVO_IMPORTADO(ID_ARQUIVO,TP_REGISTRO,DS_REGISTRO,NU_SEQ_REGISTRO,ID_MOTIVO_REJEICAO,TP_REG_ARQUIVO_IMPORTADO)\r\n" + 
//				"SELECT " +arquivo.getIdArquivo() + ",0,'"+cnab.get(0)+"',1,null,'R'\r\n" + 
//				"\r\n" + 
//				"UNION ALL\r\n" + 
//				"\r\n" + 
//				"SELECT " +arquivo.getIdArquivo() + ",1,'"+cnab.get(1)+"',2,null,'R'\r\n" + 
//				"\r\n" + 
//				"UNION ALL\r\n" + 
//				"\r\n" + 
//				"SELECT " +arquivo.getIdArquivo() + ",9,'"+cnab.get(2)+"',3,null,'R'\r\n" + 
//				"";
//		
//		PreparedStatement ps = con.prepareStatement(sqlQuery);
//		
//		ps.execute();
//		
//		return confirmarArquivoImportado(con, arquivo.getIdArquivo());
//	}
//
//	private boolean confirmarArquivoImportado(Connection con, Integer idArquivo) {
//
//		try {
//		String sqlQuery = "select * from TB_ARQUIVO_IMPORTADO where ID_ARQUIVO = " + idArquivo;
//		
//		PreparedStatement ps = con.prepareStatement(sqlQuery);
//		
//		ps.execute();
//		
//		ResultSet rs = ps.getResultSet();
//		
//		if(rs.next()) {
//			return true;
//		}
//		
//		}catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		return false;
//	}
//
//	@Override
//	public ArquivoDto findArquivoByNomeArquivo(Connection con, String nomeArquivo) throws SQLException {
//		
//		ArquivoDto retorno = null;
//		
//		String sqlQuery = "SELECT TOP 1 ID_ARQUIVO, NM_ARQUIVO, DT_ENTRADA\r\n" + 
//				"FROM TB_ARQUIVO\r\n" + 
//				"WHERE NM_ARQUIVO = '" + nomeArquivo + "'\r\n" + 
//				"ORDER BY 1 DESC";
//		
//		PreparedStatement ps = con.prepareStatement(sqlQuery);
//		
//		ps.execute();
//		
//		ResultSet rs = ps.getResultSet();
//		
//		if(rs.next()) {
//			retorno = new ArquivoDto(rs.getInt("ID_ARQUIVO"), 
//                                     rs.getString("NM_ARQUIVO"), 
//                                     LocalDateUtils.getLocalDate(rs.getString("DT_ENTRADA"))
//                                      );
//		}
//		
//		return retorno;
//	}

}

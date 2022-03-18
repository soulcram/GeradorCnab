package br.com.m3Tech.solucoesFromtis.querys;

public class Querys {
	
	private static final String FUNDO = "ID_FUNDO, NM_FUNDO, NU_CNPJ, CODIGO_ISIN, DT_FUNDO, LAYOUT_AQUISICAO";
	private static final String SACADO = "ID_SACADO, NM_SACADO, NU_CPF_CNPJ,DS_LOGRADOURO,NU_CEP";
	private static final String ORIGINADOR = "ID_FUNDO_ORIGINADOR, DS_CODIGO_CEDENTE,NM_PESSOA,NU_CPF_CNPJ";
	private static final String BANCO = "ID_BANCO, NM_BANCO, NU_BANCO";
	
	
	public static final String ALL_FUNDOS = " SELECT " + FUNDO + " \r\n" + 
										    " FROM TB_FUNDO";
	public static final String ONE_FUNDO = " SELECT " + FUNDO + " \r\n" + 
		    " FROM TB_FUNDO WHERE ID_FUNDO = ?";

	
	
	public static final String ALL_SACADOS = " SELECT " + SACADO + " \r\n" + 
			" FROM TB_FUNDO_SACADO WHERE ID_FUNDO = ?";
	public static final String ONE_SACADO = " SELECT " + SACADO + " \r\n" + 
			" FROM TB_FUNDO_SACADO WHERE ID_SACADO = ?";
	
	
	public static final String ALL_ORIGINADORES = " SELECT " + ORIGINADOR + " \r\n" + 
			" FROM TB_FUNDO_ORIGINADOR FO \r\n" + 
			" INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n" + 
			" WHERE ID_FUNDO = ?";
	public static final String ONE_ORIGINADOR = " SELECT " + ORIGINADOR + " \r\n" + 
			" FROM TB_FUNDO_ORIGINADOR FO \r\n" + 
			" INNER JOIN TB_PESSOA P ON P.ID_PESSOA = FO.ID_ORIGINADOR  \r\n" + 
			" WHERE ID_FUNDO_ORIGINADOR = ?";
	
	
	public static final String ALL_BANCOS = "SELECT " + BANCO + " FROM TB_BANCO";
	public static final String ONE_BANCO = "SELECT " + BANCO + " \r\n" + 
			" FROM TB_BANCO \r\n" + 
			" WHERE ID_BANCO = ?";
	
	public static final String ALL_MOVIMENTOS = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
			" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
			" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
			" WHERE LM.CD_LAYOUT = ?";
	public static final String ONE_MOVIMENTO = "SELECT ID_LAYOUT_MOVIMENTO, CD_OCORRENCIA, NM_TIPO_MOVIMENTO\r\n" + 
			" FROM TB_LAYOUT_MOVIMENTO LM\r\n" + 
			" INNER JOIN TB_TIPO_MOVIMENTO TM ON TM.ID_TIPO_MOVIMENTO = LM.ID_TIPO_MOVIMENTO\r\n" + 
			" WHERE ID_LAYOUT_MOVIMENTO = ?";

}

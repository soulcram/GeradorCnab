package br.com.m3Tech.solucoesFromtis.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArquivoJSONDetailDto {

	@SerializedName(value = "ident_registro_detail_recebivel")
	private String identRegistroDetailRecebivel;
	
	@SerializedName(value = "data_carencia")
	private String dataCarencia;
	
	@SerializedName(value = "tipo_juros")
	private String tipoJuros;
	
	@SerializedName(value = "taxa_juros")
	private String taxaJuros;
	
	@SerializedName(value = "coobrigacao")
	private String coobrigacao;
	
	@SerializedName(value = "caracterisca_especial")
	private String caracteriscaEspecial;
	
	@SerializedName(value = "modalidade_operacao")
	private String modalidadeOperacao;
	
	@SerializedName(value = "natureza_operacao")
	private String naturezaOperacao;
	
	@SerializedName(value = "origem_recurso")
	private String origemRecurso;
	
	@SerializedName(value = "classe_risco_operacao")
	private String classeRiscoOperacao;
	
	@SerializedName(value = "n_controle_participante")
	private String numeroControleParticipante;
	
	@SerializedName(value = "n_do_banco")
	private String numeroBanco;
	
	@SerializedName(value = "codigo_multa")
	private String codigoMulta;
	
	@SerializedName(value = "valor_desconto")
	private String valorDesconto;
	
	@SerializedName(value = "valor_pago")
	private String valorPago;
	
	@SerializedName(value = "codigo_carteira_condicao")
	private String codigoCarteiraCondicao;
	
	@SerializedName(value = "ident_debito_automatico")
	private String identDebitoAutomatico;
	
	@SerializedName(value = "data_liquidacao")
	private String dataLiquidacao;
	
	@SerializedName(value = "data_desconto_ate")
	private String dataDescontoAte;
	
	@SerializedName(value = "ident_ocorrencia")
	private String identOcorrencia;
	
	@SerializedName(value = "n_documento")
	private String numeroDocumento;
	
	@SerializedName(value = "data_vencimento")
	private String dataVencimento;
	
	@SerializedName(value = "valor_titulo")
	private String valorTitulo;
	
	@SerializedName(value = "banco_cobranca")
	private String bancoCobranca;
	
	@SerializedName(value = "agencia_depositaria")
	private String agenciaDepositaria;
	
	@SerializedName(value = "especie_titulo")
	private String especieTitulo;
	
	@SerializedName(value = "identi_recebivel")
	private String identiRecebivel;
	
	@SerializedName(value = "data_emissao")
	private String dataEmissao;
	
	@SerializedName(value = "primeira_instrucao")
	private String primeiraInstrucao;
	
	@SerializedName(value = "segunda_instrucao")
	private String segundaInstrucao;
	
	@SerializedName(value = "tipo_cedente")
	private String tipoCedente;
	
	@SerializedName(value = "valor_juros_mora")
	private String valorJurosMora;
	
	@SerializedName(value = "numero_termo_cessao")
	private String numeroTermoCessao;
	
	@SerializedName(value = "valor_aquisicao")
	private String valorAquisicao;
	
	@SerializedName(value = "valor_abatimento")
	private String valorAbatimento;
	
	@SerializedName(value = "tipo_sacado")
	private String tipoSacado;
	
	@SerializedName(value = "n_inscricao_sacado")
	private String numeroInscricaoSacado;
	
	@SerializedName(value = "nome_sacado")
	private String nomeSacado;
	
	@SerializedName(value = "endereco_completo")
	private String enderecoCompleto;
	
	@SerializedName(value = "n_nota_fiscal")
	private String numeroNotaFiscal;
	
	@SerializedName(value = "n_serie_nota_fiscal")
	private String numeroSerieNotaFiscal;
	
	@SerializedName(value = "cep_sacado")
	private String cepSacado;
	
	@SerializedName(value = "nome_cedente")
	private String nomeCedente;
	
	@SerializedName(value = "n_inscricao_cedente")
	private String numeroInscricaoCedente;
	
	@SerializedName(value = "chave_nota")
	private String chaveNota;
	
	@SerializedName(value = "n_parcela")
	private String numeroParcela;
	
	@SerializedName(value = "total_parcela")
	private String totalParcela;
	
	@SerializedName(value = "cod_loja")
	private String codigoLoja;
	
	@SerializedName(value = "cod_produto")
	private String codigoProduto;
	
	@SerializedName(value = "indice")
	private String indice;
	
	@SerializedName(value = "tipo_contrato")
	private String tipoContrato;
	
	@SerializedName(value = "valor_contrato")
	private String valorContrato;
	
	@SerializedName(value = "cnpj_base_consignado")
	private String cnpjBaseConsignado;
	
	@SerializedName(value = "linha_detail_mensagem")
	private List<ArquivoJSONLinhaDetailMensagemDto> linhaDetailMensagem;
}


package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de tipoTituloEnum.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoTituloEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Duplicata"/>
 *     &lt;enumeration value="Cheque"/>
 *     &lt;enumeration value="Contrato"/>
 *     &lt;enumeration value="Contrato Fisico"/>
 *     &lt;enumeration value="Duplicata de Servico"/>
 *     &lt;enumeration value="Duplicata de Servico Fisico"/>
 *     &lt;enumeration value="Nota Promissoria"/>
 *     &lt;enumeration value="Nota Promissoria Fisica"/>
 *     &lt;enumeration value="Duplicata de Transporte Digital CTE"/>
 *     &lt;enumeration value="Duplicata de Transporte Fisica CTE"/>
 *     &lt;enumeration value="FATURA DE CARTAO DE CREDITO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoTituloEnum")
@XmlEnum
public enum TipoTituloEnum {

	@XmlEnumValue("Duplicata")
    DUPLICATA("Duplicata"),

    @XmlEnumValue("Cheque")
    CHEQUE("Cheque"),

    @XmlEnumValue("Contrato")
    CONTRATO("Contrato"),

    @XmlEnumValue("Contrato Fisico")
    CONTRATO_FISICO("Contrato Fisico"),

    @XmlEnumValue("Duplicata de Servico")
    DUPLICATA_SERVICO("Duplicata de Servico"),

    @XmlEnumValue("Duplicata de Servico Fisico")
    DUPLICATA_SERVICO_FISICO("Duplicata de Servico Fisico"),

    @XmlEnumValue("Nota Promissoria")
    NOTA_PROMISSORIA("Nota Promissoria"),

    @XmlEnumValue("Nota Promissoria Fisica")
    NOTA_PROMISSORIA_FISICA("Nota Promissoria Fisica"),

    @XmlEnumValue("Duplicata de Transporte Digital CTE")
    DUPLICATA_TRANSPORTE_DIGITAL_CTE("Duplicata de Transporte Digital CTE"),

    @XmlEnumValue("Duplicata de Transporte Fisica CTE")
    DUPLICATA_TRANSPORTE_FISICA_CTE("Duplicata de Transporte Fisica CTE"),

    @XmlEnumValue("FATURA DE CARTAO DE CREDITO")
    FATURA_DE_CARTAO_DE_CREDITO("FATURA DE CARTAO DE CREDITO"),

    @XmlEnumValue("CDC Outros Bens")
    CDC_OUTROS_BENS("CDC Outros Bens"),

    @XmlEnumValue("Consignado")
    CONSIGNADO("Consignado"),

    @XmlEnumValue("Credito Pessoal")
    CREDITO_PESSOAL("Crédito Pessoal"),

    @XmlEnumValue("Veiculo")
    VEICULO("Veículo"),

	@XmlEnumValue("CCB PRE FIXADO")
    CCB_PRE_FIXADO("CCB PRE FIXADO"),

    @XmlEnumValue("CCB Digital")
    CCB_DIGITAL("CCB Digital"),

    @XmlEnumValue("Fatura de Cartao de Credito Digital")
    FATURA_CARTAO_DE_CREDITO_DIGITAL("Fatura de Cartao de Credito Digital"),

    @XmlEnumValue("CCB PRE FISICO")
    CCB_PRE_FISICO("CCB PRE FISICO"),

    @XmlEnumValue("CCB PRE BALCAO")
    CCB_PRE_BALCAO("CCB PRE BALCAO"),

    @XmlEnumValue("CCB PRE CETIPADA")
    CCB_PRE_CETIPADA("CCB PRE CETIPADA"),

    @XmlEnumValue("CCB PRE DIGITAL")
    CCB_PRE_DIGITAL("CCB PRE DIGITAL"),

    @XmlEnumValue("Operacao Cartao de Credito DIGITAL")
    OPERACAO_CARTAO_CREDITO_DIGITAL("Operacao Cartao de Credito DIGITAL"),

    @XmlEnumValue("Operacao Cartao de Credito")
    OPERACAO_CARTAO_CREDITO("Operacao Cartao de Credito"),

    @XmlEnumValue("CCB PMT PL")
    CCB_PMT_PL("CCB PMT PL"),

    @XmlEnumValue("CCB FINAL PL")
    CCB_FINAL_PL("CCB FINAL PL"),

    @XmlEnumValue("CCB PMT MatCon")
    CCB_PMT_MATCON("CCB PMT MatCon"),

    @XmlEnumValue("CCB FINAL MatCon")
    CCB_FINAL_MATCON("CCB FINAL MatCon"),

    @XmlEnumValue("CCB PMT MAIS / CO BRANDED")
    CCB_PMT_MAIS_CO_BRANDED("CCB PMT MAIS / CO BRANDED"),

    @XmlEnumValue("CCB FINAL MAIS / CO BRANDED")
    CCB_FINAL_MAIS_CO_BRANDED("CCB FINAL MAIS / CO BRANDED"),

    @XmlEnumValue("CCB - FORMALIZACAO FONADA")
    CCB_FORMALIZACAO_FONADA("CCB - FORMALIZACAO FONADA"),
    
    @XmlEnumValue("CCB DIGITAL CONSIGNADO")
    CCB_DIGITAL_CONSIGNADO("CCB DIGITAL CONSIGNADO"),
    
    @XmlEnumValue("CCB DIGITAL FGTS")
    CCB_DIGITAL_FGTS("CCB DIGITAL FGTS"),   

    @XmlEnumValue("Unidades de recebivel")
    UNIDADE_RECEBIVEL("Unidades de recebivel"),

    @XmlEnumValue("CCI POS COM ALIENACAO FIDUCIARIA - DIGITAL")
    CCI_POS_ALIENACAO_FIDUCIARIA_DIGITAL("CCI POS COM ALIENACAO FIDUCIARIA - DIGITAL"),
    
    @XmlEnumValue("CCI POS COM ALIENACAO FIDUCIARIA - FISICA")
    CCI_POS_ALIENACAO_FIDUCIARIA_FISICA("CCI POS COM ALIENACAO FIDUCIARIA - FISICA"),
    
    @XmlEnumValue("Duplicata Mercantil")
    DUPLICATA_MERCANTIL("Duplicata Mercantil"),
    
    @XmlEnumValue("Conhecimento de Transporte Eletronico CTE")
    CONHECIMENTO_TRANSPORTE_ELETRONICO_CTE("Conhecimento de Transporte Eletronico CTE"),
    
    @XmlEnumValue("Letras de Cambio")
    LETRAS_CAMBIO("Letras de Cambio"),
    
    @XmlEnumValue("Termo Investimento")
    TERMO_INVESTIMENTO("Termo Investimento"),
    
    @XmlEnumValue("Recibo")
    RECIBO("Recibo"),
    
    @XmlEnumValue("Nota de Debito")
    NOTA_DE_DEBITO("Nota de Debito"),
    
    @XmlEnumValue("Confissao de Divida")
    CONFISSAO_DE_DIVIDA("Confissao de Divida"),
    
    @XmlEnumValue("Confissao de Divida")
    CONFISSAO_DIVIDA("Confissao de Divida"),
    
    @XmlEnumValue("Assuncao de Divida")
    ASSUNCAO_DE_DIVIDA("Assuncao de Divida"),
    
    @XmlEnumValue("Cotas de Consorcio")
    COTAS_CONSORCIO("Cotas de Consorcio"),
    
    @XmlEnumValue("Cotas de Condominio")
    COTAS_CONDOMINIO("Cotas de Condominio"),
    
    @XmlEnumValue("Triplicata")
    TRIPLICATA("Triplicata"),
    
    @XmlEnumValue("CCB Motos")
    CCB_MOTOS("CCB Motos"),
    
    @XmlEnumValue("Precatorio")
    PRECATORIO("Precatorio"),
    
    @XmlEnumValue("Acao Judicial")
    ACAO_JUDICIAL("Acao Judicial"),
    
    @XmlEnumValue("Nota Comercial")
    NOTA_COMERCIAL("Nota Comercial"),
    
    @XmlEnumValue("NF-E")
    NFE("NF-E"),
    
    @XmlEnumValue("CCB")
    CCB("CCB"),
    
    @XmlEnumValue("Contrato de Fornecedor")
    CONTRATO_DE_FORNECEDOR("Contrato de Fornecedor"),
    
    @XmlEnumValue("Contrato Fisico Futuro")
    CONTRATO_FISICO_FUTURO("Contrato Fisico Futuro"),
    
    @XmlEnumValue("CONSORCIO ATIVO")
    CONSORCIO_ATIVO("CONSORCIO ATIVO"),
    
    @XmlEnumValue("CONSORCIO INATIVO")
    CONSORCIO_INATIVO("CONSORCIO INATIVO"),
    
    @XmlEnumValue("Duplicata/Fatura")
    DuplicataFatura("Duplicata/Fatura"),
    
    @XmlEnumValue("CPR")
    CPR("CPR"),
    
    @XmlEnumValue("Nota Fiscal Eletronica")
    NOTA_FISCAL_ELETRONICA("Nota Fiscal Eletronica"),
    
    @XmlEnumValue("ACAO JUDICIAL")
    ACAO_JUDICIAL_UC("ACAO JUDICIAL"),

    @XmlEnumValue("BARTER - DOLAR")
    BARTER_DOLAR("BARTER - DOLAR"),

    @XmlEnumValue("BARTER - REAL")
    BARTER_REAL("BARTER - REAL"),

    @XmlEnumValue("CCB - CURTO PRAZO")
    CCB_CURTO_PRAZO("CCB - CURTO PRAZO"),

    @XmlEnumValue("CCB - FGTS")
    CCB_FGTS("CCB - FGTS"),

    @XmlEnumValue("CCB - LONGO PRAZO")
    CCB_LONGO_PRAZO("CCB - LONGO PRAZO"),

    @XmlEnumValue("CCB - POS FIXADO")
    CCB_POS_FIXADO("CCB - POS FIXADO"),

    @XmlEnumValue("CCB DIG - EXERCITO")
    CCB_DIG_EXERCITO("CCB DIG - EXERCITO"),

    @XmlEnumValue("CCB DIG - FORCA AREA")
    CCB_DIG_FORCA_AEREA("CCB DIG - FORCA AREA"),

    @XmlEnumValue("CCB DIG - INSS")
    CCB_DIG_INSS("CCB DIG - INSS"),

    @XmlEnumValue("CCB DIG - MARINHA")
    CCB_DIG_MARINHA("CCB DIG - MARINHA"),

    @XmlEnumValue("CCB DIG - SIAPE")
    CCB_DIG_SIAPE("CCB DIG - SIAPE"),

    @XmlEnumValue("CCI Pos-Chaves com Alienacao")
    CCI_POS_CHAVE_COM_ALIENACAO("CCI Pos-Chaves com Alienacao"),

    @XmlEnumValue("CDCA")
    CDCA("CDCA"),

    @XmlEnumValue("CHEQUE - MANUAL")
    CHEQUE_MANUAL("CHEQUE - MANUAL"),

    @XmlEnumValue("CONTRATO ANUAL")
    CONTRATO_ANUAL("CONTRATO ANUAL"),

    @XmlEnumValue("CONTRATO EM IMPLAT")
    CONTRATO_EM_IMPLAT("CONTRATO EM IMPLAT"),

    @XmlEnumValue("CONTRATO IMPLANT")
    CONTRATO_IMPLANT("CONTRATO IMPLANT"),

    @XmlEnumValue("CONTRATO SEMANAL")
    CONTRATO_SEMANAL("CONTRATO SEMANAL"),

    @XmlEnumValue("Cotas de Consorcio - Carrego")
    COTAS_CONSORCIO_CARREGO("Cotas de Consorcio - Carrego"),

    @XmlEnumValue("Cotas de Consorcio - Contemplacao")
    COTAS_CONSORCIO_CONTEMPLACAO("Cotas de Consorcio - Contemplacao"),

    @XmlEnumValue("CPR - DOLAR")
    CPR_DOLAR("CPR - DOLAR"),

    @XmlEnumValue("CPR-F - POS FIXADO")
    CPRF_POS_FIXADO("CPR-F - POS FIXADO"),

    @XmlEnumValue("CPR-F (AC)")
    CPRF_AC("CPR-F (AC)"),

    @XmlEnumValue("CPR-F (AL)")
    CPRF_AL("CPR-F (AL)"),

    @XmlEnumValue("CPR-F (AM)")
    CPRF_AM("CPR-F (AM)"),

    @XmlEnumValue("CPR-F (AP)")
    CPRF_AP("CPR-F (AP)"),

    @XmlEnumValue("CPR-F (BA)")
    CPRF_BA("CPR-F (BA)"),

    @XmlEnumValue("CPR-F (DF)")
    CPRF_DF("CPR-F (DF)"),

    @XmlEnumValue("CPR-F (ES)")
    CPRF_ES("CPR-F (ES)"),

    @XmlEnumValue("CPR-F (GO)")
    CPRF_GO("CPR-F (GO)"),

    @XmlEnumValue("CPR-F (MA)")
    CPRF_MA("CPR-F (MA)"),

    @XmlEnumValue("CPR-F (MG)")
    CPRF_MG("CPR-F (MG)"),

    @XmlEnumValue("CPR-F (MS)")
    CPRF_MS("CPR-F (MS)"),

    @XmlEnumValue("CPR-F (MT)")
    CPRF_MT("CPR-F (MT)"),

    @XmlEnumValue("CPR-F (PA)")
    CPRF_PA("CPR-F (PA)"),

    @XmlEnumValue("CPR-F (PB)")
    CPRF_PB("CPR-F (PB)"),

    @XmlEnumValue("CPR-F (PE)")
    CPRF_PE("CPR-F (PE)"),

    @XmlEnumValue("CPR-F (PI)")
    CPRF_PI("CPR-F (PI)"),

    @XmlEnumValue("CPR-F (PR)")
    CPRF_PR("CPR-F (PR)"),

    @XmlEnumValue("CPR-F (RJ)")
    CPRF_RJ("CPR-F (RJ)"),

    @XmlEnumValue("CPR-F (RN)")
    CPRF_RN("CPR-F (RN)"),

    @XmlEnumValue("CPR-F (RO)")
    CPRF_RO("CPR-F (RO)"),

    @XmlEnumValue("CPR-F (RR)")
    CPRF_RR("CPR-F (RR)"),

    @XmlEnumValue("CPR-F (RS)")
    CPRF_RS("CPR-F (RS)"),

    @XmlEnumValue("CPR-F (SC)")
    CPRF_SC("CPR-F (SC)"),

    @XmlEnumValue("CPR-F (SE)")
    CPRF_SE("CPR-F (SE)"),

    @XmlEnumValue("CPR-F (SP)")
    CPRF_SP("CPR-F (SP)"),

    @XmlEnumValue("CPR-F (TO)")
    CPRF_TO("CPR-F (TO)"),

    @XmlEnumValue("Creditorio Federal")
    CREDITORIO_FEDERAL("Creditorio Federal"),

    @XmlEnumValue("Direitos Credit Outros")
    DIREITOS_CREDIT_OUTROS("Direitos Credit Outros"),

    @XmlEnumValue("NFE - DOLAR")
    NFE_DOLAR("NFE - DOLAR"),

    @XmlEnumValue("NOTA COMERCIAL")
    NOTA_COMERCIAL_UC("NOTA COMERCIAL"),

    @XmlEnumValue("Nota Fiscal de Serviço")
    NOTA_FISCAL_SERVICO("Nota Fiscal de Serviço"),    

    @XmlEnumValue("Precatorio Estado SP")
    PRECATORIO_ESTADO_SP("Precatorio Estado SP"),

    @XmlEnumValue("Precatorio Federal")
    PRECATORIO_FEDERAL("Precatorio Federal"),

    @XmlEnumValue("Precatorio Municipal SP")
    PRECATORIO_MUNICIPAL_SP("Precatorio Municipal SP"),

    @XmlEnumValue("VEICULO-NOVO/MARCADO")
    VEICULO_NOVO_MARCADO("VEICULO-NOVO/MARCADO"),

    @XmlEnumValue("VEICULO-USADO/MARCADO")
    VEICULO_USADO_MARCADO("VEICULO-USADO/MARCADO"),

    @XmlEnumValue("VEICULO-USADO/REMARCADO")
    VEICULO_USADO_REMARCADO("VEICULO-USADO/REMARCADO");
    
    private final String value;

    TipoTituloEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoTituloEnum fromValue(String v) {
        for (TipoTituloEnum c: TipoTituloEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


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
    CCB_FORMALIZACAO_FONADA("CCB - FORMALIZACAO FONADA");
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


package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoTituloEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoPessoaEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CPF"/>
 *     &lt;enumeration value="CNPJ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoPessoaEnum")
@XmlEnum
public enum TipoPessoaEnum {

    @XmlEnumValue("CPF")
    CPF("CPF"),
    @XmlEnumValue("CNPJ")
    CNPJ("CNPJ");
    private final String value;

    TipoPessoaEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoPessoaEnum fromValue(String v) {
        for (TipoPessoaEnum c: TipoPessoaEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de tipoPessoaEnum.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
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

    CPF,
    CNPJ;

    public String value() {
        return name();
    }

    public static TipoPessoaEnum fromValue(String v) {
        return valueOf(v);
    }

}

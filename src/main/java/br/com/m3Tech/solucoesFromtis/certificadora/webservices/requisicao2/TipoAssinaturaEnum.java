
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoAssinaturaEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoAssinaturaEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Individual"/>
 *     &lt;enumeration value="Conjunto"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoAssinaturaEnum")
@XmlEnum
public enum TipoAssinaturaEnum {

    @XmlEnumValue("Individual")
    INDIVIDUAL("Individual"),
    @XmlEnumValue("Conjunto")
    CONJUNTO("Conjunto");
    private final String value;

    TipoAssinaturaEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoAssinaturaEnum fromValue(String v) {
        for (TipoAssinaturaEnum c: TipoAssinaturaEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

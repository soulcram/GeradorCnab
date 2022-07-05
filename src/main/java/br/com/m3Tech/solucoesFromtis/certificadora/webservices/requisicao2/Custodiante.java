
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for custodiante complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="custodiante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomeCustodiante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cnpjCustodiante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "custodiante", propOrder = {
    "nomeCustodiante",
    "cnpjCustodiante"
})
public class Custodiante {

    @XmlElement(required = true)
    protected String nomeCustodiante;
    @XmlElement(required = true)
    protected String cnpjCustodiante;
}
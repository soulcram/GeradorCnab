
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de custodiante complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
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

    /**
     * Obt�m o valor da propriedade nomeCustodiante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCustodiante() {
        return nomeCustodiante;
    }

    /**
     * Define o valor da propriedade nomeCustodiante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCustodiante(String value) {
        this.nomeCustodiante = value;
    }

    /**
     * Obt�m o valor da propriedade cnpjCustodiante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpjCustodiante() {
        return cnpjCustodiante;
    }

    /**
     * Define o valor da propriedade cnpjCustodiante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpjCustodiante(String value) {
        this.cnpjCustodiante = value;
    }

}

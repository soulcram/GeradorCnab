
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requisicao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requisicao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retornoAquisicao" type="{http://webservices.portal.fidc.fromtis.com.br/}requisicaoCertificadoDigital" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requisicao", propOrder = {
    "retornoAquisicao"
}, namespace="")
public class Requisicao {

    protected RequisicaoCertificadoDigital retornoAquisicao;

    /**
     * Gets the value of the retornoAquisicao property.
     * 
     * @return
     *     possible object is
     *     {@link RequisicaoCertificadoDigital }
     *     
     */
    public RequisicaoCertificadoDigital getRetornoAquisicao() {
        return retornoAquisicao;
    }

    /**
     * Sets the value of the retornoAquisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequisicaoCertificadoDigital }
     *     
     */
    public void setRetornoAquisicao(RequisicaoCertificadoDigital value) {
        this.retornoAquisicao = value;
    }

}


package br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de retorno complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="retorno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retornoAquisicao" type="{http://webservices.portal.fidc.fromtis.com.br/}retornoCertificadoDigital" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retorno", propOrder = {
    "retornoAquisicao"
})
public class Retorno {

    protected RetornoCertificadoDigital retornoAquisicao;

    /**
     * Obt�m o valor da propriedade retornoAquisicao.
     * 
     * @return
     *     possible object is
     *     {@link RetornoCertificadoDigital }
     *     
     */
    public RetornoCertificadoDigital getRetornoAquisicao() {
        return retornoAquisicao;
    }

    /**
     * Define o valor da propriedade retornoAquisicao.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoCertificadoDigital }
     *     
     */
    public void setRetornoAquisicao(RetornoCertificadoDigital value) {
        this.retornoAquisicao = value;
    }

}

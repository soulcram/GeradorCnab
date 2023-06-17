
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;


/**
 * <p>Classe Java de retornoResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="retornoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservices.portal.fidc.fromtis.com.br/}retornoProcessamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retornoResponse", propOrder = {
    "_return"
})
public class RetornoResponse {

    @XmlElement(name = "return")
    protected RetornoProcessamento _return;

    /**
     * Obt�m o valor da propriedade return.
     * 
     * @return
     *     possible object is
     *     {@link RetornoProcessamento }
     *     
     */
    public RetornoProcessamento getReturn() {
        return _return;
    }

    /**
     * Define o valor da propriedade return.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoProcessamento }
     *     
     */
    public void setReturn(RetornoProcessamento value) {
        this._return = value;
    }

}

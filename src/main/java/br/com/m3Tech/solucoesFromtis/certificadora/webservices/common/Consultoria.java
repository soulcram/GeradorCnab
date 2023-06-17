
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java de consultoria complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="consultoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomeConsultoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cnpjConsultoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parte" type="{http://webservices.portal.fidc.fromtis.com.br/}parte" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultoria", propOrder = {
    "nomeConsultoria",
    "cnpjConsultoria",
    "partes"
})
public class Consultoria {

    @XmlElement(required = true)
    protected String nomeConsultoria;
    @XmlElement(required = true)
    protected String cnpjConsultoria;
    @XmlElement(required = true)
    protected Consultoria.Partes partes;

    /**
     * Obt�m o valor da propriedade nomeConsultoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeConsultoria() {
        return nomeConsultoria;
    }

    /**
     * Define o valor da propriedade nomeConsultoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeConsultoria(String value) {
        this.nomeConsultoria = value;
    }

    /**
     * Obt�m o valor da propriedade cnpjConsultoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpjConsultoria() {
        return cnpjConsultoria;
    }

    /**
     * Define o valor da propriedade cnpjConsultoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpjConsultoria(String value) {
        this.cnpjConsultoria = value;
    }

    /**
     * Obt�m o valor da propriedade partes.
     * 
     * @return
     *     possible object is
     *     {@link Consultoria.Partes }
     *     
     */
    public Consultoria.Partes getPartes() {
        return partes;
    }

    /**
     * Define o valor da propriedade partes.
     * 
     * @param value
     *     allowed object is
     *     {@link Consultoria.Partes }
     *     
     */
    public void setPartes(Consultoria.Partes value) {
        this.partes = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parte" type="{http://webservices.portal.fidc.fromtis.com.br/}parte" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parte"
    })
    public static class Partes {

        @XmlElement(nillable = true)
        protected List<Parte> parte;

        /**
         * Gets the value of the parte property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parte property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParte().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Parte }
         * 
         * 
         */
        public List<Parte> getParte() {
            if (parte == null) {
                parte = new ArrayList<Parte>();
            }
            return this.parte;
        }

    }

}

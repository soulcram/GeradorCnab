
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java de fundo complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="fundo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cnpjFundo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomeFundo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="testemunhas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="testemunha" type="{http://webservices.portal.fidc.fromtis.com.br/}testemunha" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "fundo", propOrder = {
    "cnpjFundo",
    "nomeFundo",
    "partes",
    "testemunhas"
})
public class Fundo {

    @XmlElement(required = true)
    protected String cnpjFundo;
    @XmlElement(required = true)
    protected String nomeFundo;
    @XmlElement(required = true)
    protected Fundo.Partes partes;
    @XmlElement(required = true)
    protected Fundo.Testemunhas testemunhas;

    /**
     * Obt�m o valor da propriedade cnpjFundo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpjFundo() {
        return cnpjFundo;
    }

    /**
     * Define o valor da propriedade cnpjFundo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpjFundo(String value) {
        this.cnpjFundo = value;
    }

    /**
     * Obt�m o valor da propriedade nomeFundo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFundo() {
        return nomeFundo;
    }

    /**
     * Define o valor da propriedade nomeFundo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFundo(String value) {
        this.nomeFundo = value;
    }

    /**
     * Obt�m o valor da propriedade partes.
     * 
     * @return
     *     possible object is
     *     {@link Fundo.Partes }
     *     
     */
    public Fundo.Partes getPartes() {
        return partes;
    }

    /**
     * Define o valor da propriedade partes.
     * 
     * @param value
     *     allowed object is
     *     {@link Fundo.Partes }
     *     
     */
    public void setPartes(Fundo.Partes value) {
        this.partes = value;
    }

    /**
     * Obt�m o valor da propriedade testemunhas.
     * 
     * @return
     *     possible object is
     *     {@link Fundo.Testemunhas }
     *     
     */
    public Fundo.Testemunhas getTestemunhas() {
        return testemunhas;
    }

    /**
     * Define o valor da propriedade testemunhas.
     * 
     * @param value
     *     allowed object is
     *     {@link Fundo.Testemunhas }
     *     
     */
    public void setTestemunhas(Fundo.Testemunhas value) {
        this.testemunhas = value;
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
     *         &lt;element name="testemunha" type="{http://webservices.portal.fidc.fromtis.com.br/}testemunha" maxOccurs="unbounded" minOccurs="0"/>
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
        "testemunha"
    })
    public static class Testemunhas {

        @XmlElement(nillable = true)
        protected List<Testemunha> testemunha;

        /**
         * Gets the value of the testemunha property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the testemunha property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTestemunha().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Testemunha }
         * 
         * 
         */
        public List<Testemunha> getTestemunha() {
            if (testemunha == null) {
                testemunha = new ArrayList<Testemunha>();
            }
            return this.testemunha;
        }

    }

}

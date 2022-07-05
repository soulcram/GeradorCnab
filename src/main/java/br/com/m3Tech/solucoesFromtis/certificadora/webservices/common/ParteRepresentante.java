
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de parteRepresentante complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="parteRepresentante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="papel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="assinaIsoladamente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="emiteDuplicata" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="assinaPorEndosso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="assinaTermoCessao" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parteRepresentante", propOrder = {
    "nome",
    "cpf",
    "email",
    "papel",
    "assinaIsoladamente",
    "emiteDuplicata",
    "assinaPorEndosso",
    "assinaTermoCessao"
})
public class ParteRepresentante {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cpf;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String papel;
    protected boolean assinaIsoladamente;
    protected boolean emiteDuplicata;
    protected boolean assinaPorEndosso;
    protected boolean assinaTermoCessao;

    /**
     * Obt�m o valor da propriedade nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Obt�m o valor da propriedade cpf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o valor da propriedade cpf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
    }

    /**
     * Obt�m o valor da propriedade email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o valor da propriedade email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obt�m o valor da propriedade papel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPapel() {
        return papel;
    }

    /**
     * Define o valor da propriedade papel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPapel(String value) {
        this.papel = value;
    }

    /**
     * Obt�m o valor da propriedade assinaIsoladamente.
     * 
     */
    public boolean isAssinaIsoladamente() {
        return assinaIsoladamente;
    }

    /**
     * Define o valor da propriedade assinaIsoladamente.
     * 
     */
    public void setAssinaIsoladamente(boolean value) {
        this.assinaIsoladamente = value;
    }

    /**
     * Obt�m o valor da propriedade emiteDuplicata.
     * 
     */
    public boolean isEmiteDuplicata() {
        return emiteDuplicata;
    }

    /**
     * Define o valor da propriedade emiteDuplicata.
     * 
     */
    public void setEmiteDuplicata(boolean value) {
        this.emiteDuplicata = value;
    }

    /**
     * Obt�m o valor da propriedade assinaPorEndosso.
     * 
     */
    public boolean isAssinaPorEndosso() {
        return assinaPorEndosso;
    }

    /**
     * Define o valor da propriedade assinaPorEndosso.
     * 
     */
    public void setAssinaPorEndosso(boolean value) {
        this.assinaPorEndosso = value;
    }

    /**
     * Obt�m o valor da propriedade assinaTermoCessao.
     * 
     */
    public boolean isAssinaTermoCessao() {
        return assinaTermoCessao;
    }

    /**
     * Define o valor da propriedade assinaTermoCessao.
     * 
     */
    public void setAssinaTermoCessao(boolean value) {
        this.assinaTermoCessao = value;
    }

}

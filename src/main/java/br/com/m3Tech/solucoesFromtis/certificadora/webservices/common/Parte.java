
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java de parte complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="parte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="papel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="tipo" use="required" type="{http://webservices.portal.fidc.fromtis.com.br/}tipoAssinaturaEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parte", propOrder = {
    "nome",
    "cpf",
    "email",
    "papel"
})
public class Parte {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cpf;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String papel;
    @XmlAttribute(name = "tipo", required = true)
    protected TipoAssinaturaEnum tipo;

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
     * Obt�m o valor da propriedade tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoAssinaturaEnum }
     *     
     */
    public TipoAssinaturaEnum getTipo() {
        return tipo;
    }

    /**
     * Define o valor da propriedade tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAssinaturaEnum }
     *     
     */
    public void setTipo(TipoAssinaturaEnum value) {
        this.tipo = value;
    }

}

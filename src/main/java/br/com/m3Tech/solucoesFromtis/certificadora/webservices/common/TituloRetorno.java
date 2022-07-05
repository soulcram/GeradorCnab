
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Classe Java de tituloRetorno complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tituloRetorno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroControleParticipante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataDeEmissaoDuplicata" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataDeVencimentoDuplicata" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="valorBruto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="valorLiquido" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="numeroDaNotaFiscal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serieDaNotaFiscal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomePkcs7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chaveNfe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sacado" type="{http://webservices.portal.fidc.fromtis.com.br/}sacado"/>
 *         &lt;element name="comCoobrigacao" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="tipo" use="required" type="{http://webservices.portal.fidc.fromtis.com.br/}tipoTituloEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tituloRetorno", propOrder = {
    "numero",
    "numeroControleParticipante",
    "dataDeEmissaoDuplicata",
    "dataDeVencimentoDuplicata",
    "valorBruto",
    "valorLiquido",
    "numeroDaNotaFiscal",
    "serieDaNotaFiscal",
    "nomePkcs7",
    "chaveNfe",
    "sacado",
    "comCoobrigacao"
})
public class TituloRetorno {

    @XmlElement(required = true)
    protected String numero;
    @XmlElement(required = true)
    protected String numeroControleParticipante;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDeEmissaoDuplicata;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDeVencimentoDuplicata;
    @XmlElement(required = true)
    protected BigDecimal valorBruto;
    @XmlElement(required = true)
    protected BigDecimal valorLiquido;
    @XmlElement(required = true)
    protected String numeroDaNotaFiscal;
    protected String serieDaNotaFiscal;
    @XmlElement(required = true)
    protected String nomePkcs7;
    protected String chaveNfe;
    @XmlElement(required = true)
    protected Sacado sacado;
    protected boolean comCoobrigacao;
    @XmlAttribute(name = "tipo", required = true)
    protected TipoTituloEnum tipo;

    /**
     * Obt�m o valor da propriedade numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define o valor da propriedade numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obt�m o valor da propriedade numeroControleParticipante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroControleParticipante() {
        return numeroControleParticipante;
    }

    /**
     * Define o valor da propriedade numeroControleParticipante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroControleParticipante(String value) {
        this.numeroControleParticipante = value;
    }

    /**
     * Obt�m o valor da propriedade dataDeEmissaoDuplicata.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeEmissaoDuplicata() {
        return dataDeEmissaoDuplicata;
    }

    /**
     * Define o valor da propriedade dataDeEmissaoDuplicata.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeEmissaoDuplicata(XMLGregorianCalendar value) {
        this.dataDeEmissaoDuplicata = value;
    }

    /**
     * Obt�m o valor da propriedade dataDeVencimentoDuplicata.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeVencimentoDuplicata() {
        return dataDeVencimentoDuplicata;
    }

    /**
     * Define o valor da propriedade dataDeVencimentoDuplicata.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeVencimentoDuplicata(XMLGregorianCalendar value) {
        this.dataDeVencimentoDuplicata = value;
    }

    /**
     * Obt�m o valor da propriedade valorBruto.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    /**
     * Define o valor da propriedade valorBruto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorBruto(BigDecimal value) {
        this.valorBruto = value;
    }

    /**
     * Obt�m o valor da propriedade valorLiquido.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    /**
     * Define o valor da propriedade valorLiquido.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorLiquido(BigDecimal value) {
        this.valorLiquido = value;
    }

    /**
     * Obt�m o valor da propriedade numeroDaNotaFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDaNotaFiscal() {
        return numeroDaNotaFiscal;
    }

    /**
     * Define o valor da propriedade numeroDaNotaFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDaNotaFiscal(String value) {
        this.numeroDaNotaFiscal = value;
    }

    /**
     * Obt�m o valor da propriedade serieDaNotaFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieDaNotaFiscal() {
        return serieDaNotaFiscal;
    }

    /**
     * Define o valor da propriedade serieDaNotaFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieDaNotaFiscal(String value) {
        this.serieDaNotaFiscal = value;
    }

    /**
     * Obt�m o valor da propriedade nomePkcs7.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePkcs7() {
        return nomePkcs7;
    }

    /**
     * Define o valor da propriedade nomePkcs7.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePkcs7(String value) {
        this.nomePkcs7 = value;
    }

    /**
     * Obt�m o valor da propriedade chaveNfe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveNfe() {
        return chaveNfe;
    }

    /**
     * Define o valor da propriedade chaveNfe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveNfe(String value) {
        this.chaveNfe = value;
    }

    /**
     * Obt�m o valor da propriedade sacado.
     * 
     * @return
     *     possible object is
     *     {@link Sacado }
     *     
     */
    public Sacado getSacado() {
        return sacado;
    }

    /**
     * Define o valor da propriedade sacado.
     * 
     * @param value
     *     allowed object is
     *     {@link Sacado }
     *     
     */
    public void setSacado(Sacado value) {
        this.sacado = value;
    }

    /**
     * Obt�m o valor da propriedade comCoobrigacao.
     * 
     */
    public boolean isComCoobrigacao() {
        return comCoobrigacao;
    }

    /**
     * Define o valor da propriedade comCoobrigacao.
     * 
     */
    public void setComCoobrigacao(boolean value) {
        this.comCoobrigacao = value;
    }

    /**
     * Obt�m o valor da propriedade tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoTituloEnum }
     *     
     */
    public TipoTituloEnum getTipo() {
        return tipo;
    }

    /**
     * Define o valor da propriedade tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoTituloEnum }
     *     
     */
    public void setTipo(TipoTituloEnum value) {
        this.tipo = value;
    }

}

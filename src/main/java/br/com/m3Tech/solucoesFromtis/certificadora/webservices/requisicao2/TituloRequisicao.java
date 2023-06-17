
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for tituloRequisicao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tituloRequisicao">
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
 *         &lt;element name="nomePkcs7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="termoCessao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tituloRequisicao", propOrder = {
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
public class TituloRequisicao {

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
    @XmlElement(required = false)
    protected String serieDaNotaFiscal;
    @XmlElement(required = false)
    protected String nomePkcs7;
    @XmlElement(required = false)
    protected String chaveNfe;
    @XmlElement(required = true)
    protected Sacado sacado;
    @XmlAttribute(name = "tipo", required = true)
    protected TipoTituloEnum tipo;
    @XmlElement(required = true)
    protected boolean comCoobrigacao;
}
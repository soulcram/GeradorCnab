
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sacado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sacado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://webservices.portal.fidc.fromtis.com.br/}tipopessoaEnum"/>
 *         &lt;element name="partes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parte" type="{http://webservices.portal.fidc.fromtis.com.br/}parteRepresentante" maxOccurs="unbounded" minOccurs="0"/>
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
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sacado", propOrder = {
    "nome",
    "cpf",
    "endereco",
    "numero",
    "complemento",
    "email",
    "tipo",
    "partes"
})
public class Sacado {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cpf;
    @XmlElement(required = true)
    protected String endereco;
    @XmlElement(required = true, nillable=true)
    protected String numero;
    @XmlElement(required = true, nillable=true)
    protected String complemento;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected TipoPessoaEnum tipo;
    @XmlElement(required = true)
    protected Partes partes;
}
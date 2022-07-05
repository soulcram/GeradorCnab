
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for parte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
@Data
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
}
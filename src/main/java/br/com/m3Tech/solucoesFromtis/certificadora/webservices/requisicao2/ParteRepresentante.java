
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parteRepresentante complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
@Data
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
    @XmlElement(required = true)
    protected boolean assinaIsoladamente;
    @XmlElement(required = true)
    protected boolean emiteDuplicata;
    @XmlElement(required = true)
    protected boolean assinaPorEndosso;
    @XmlElement(required = true)
    protected boolean assinaTermoCessao;
}
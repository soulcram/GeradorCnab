
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for operacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valorLiquido" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="valorBruto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="comAdiantamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "operacao", propOrder = {
    "valorLiquido",
    "valorBruto",
    "comAdiantamento",
    "termoCessao",
    "valorRecompra"
})
public class Operacao {

    @XmlElement(required = true)
    protected BigDecimal valorLiquido;
    @XmlElement(required = true)
    protected BigDecimal valorBruto;
    @XmlElement(required = true)
    protected boolean comAdiantamento;
    @XmlElement(required = true)
    private String termoCessao = "";
    @XmlElement(required = true)
    private BigDecimal valorRecompra;
}
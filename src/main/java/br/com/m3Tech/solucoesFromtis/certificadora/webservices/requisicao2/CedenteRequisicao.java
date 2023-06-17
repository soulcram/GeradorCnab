
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for cedenteRequisicao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cedenteRequisicao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cnpj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inscricaoEstadual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inscricaoMunicipal" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="avalistas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="avalista" type="{http://webservices.portal.fidc.fromtis.com.br/}avalista" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="titulos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRequisicao" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="titulosRecompra">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRequisicao" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "cedenteRequisicao", propOrder = {
    "cnpj",
    "nome",
    "endereco",
    "numero",
    "complemento",
    "bairro",
    "cidade",
    "uf",
    "cep",
    "telefone",
    "inscricaoEstadual",
    "inscricaoMunicipal",
    "partes",
    "avalistas",
    "titulos",
    "titulosRecompra"
})
public class CedenteRequisicao {

    @XmlElement(required = true)
    protected String cnpj;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String endereco;
    @XmlElement(required = false)
    protected String numero;
    @XmlElement(required = false)
    protected String complemento;
    @XmlElement(required = true)
    protected String bairro;
    @XmlElement(required = true)
    protected String cidade;
    @XmlElement(required = true)
    protected String uf;
    @XmlElement(required = true)
    protected String cep;
    @XmlElement(required = true)
    protected String telefone;
    @XmlElement(required = true)
    protected String inscricaoEstadual;
    @XmlElement(required = true)
    protected String inscricaoMunicipal;
    @XmlElement(required = true)
    protected Partes partes;
    @XmlElement(required = true)
    protected CedenteRequisicao.Avalistas avalistas;
    @XmlElement(required = true)
    protected CedenteRequisicao.Titulos titulos;
    @XmlElement(required = true)
    protected CedenteRequisicao.Titulos titulosRecompra;


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="avalista" type="{http://webservices.portal.fidc.fromtis.com.br/}avalista" maxOccurs="unbounded" minOccurs="0"/>
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
        "avalista"
    })
    public static class Avalistas {

        protected List<Avalista> avalista;

        /**
         * Gets the value of the avalista property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the avalista property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAvalista().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Avalista }
         * 
         * 
         */
        public List<Avalista> getAvalista() {
            if (avalista == null) {
                avalista = new ArrayList<Avalista>();
            }
            return this.avalista;
        }
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRequisicao" maxOccurs="unbounded" minOccurs="0"/>
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
        "titulo"
    })
    public static class Titulos {

        protected List<TituloRequisicao> titulo;

        /**
         * Gets the value of the titulo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the titulo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTitulo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TituloRequisicao }
         * 
         * 
         */
        public List<TituloRequisicao> getTitulo() {
            if (titulo == null) {
                titulo = new ArrayList<TituloRequisicao>();
            }
            return this.titulo;
        }
    }
}
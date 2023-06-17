package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de cedenteRetorno complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="cedenteRetorno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cnpj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRetorno" maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRetorno" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "cedenteRetorno", propOrder = {
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
public class Cedente {

    @XmlElement(required = true)
    protected String cnpj;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String endereco;
    protected String numero;
    protected String complemento;
    @XmlElement(required = true)
    protected String bairro;
    @XmlElement(required = true)
    protected String cidade;
    @XmlElement(required = true)
    protected String uf = "";
    @XmlElement(required = true)
    protected String cep;
    @XmlElement(required = true)
    protected String telefone;
    @XmlElement(required = true)
    protected String inscricaoEstadual;
    @XmlElement(required = true)
    protected String inscricaoMunicipal;
    @XmlElement(required = true)
    protected Cedente.Partes partes;
    @XmlElement(required = true)
    protected Cedente.Avalistas avalistas;
    @XmlElement(required = true)
    protected Cedente.Titulos titulos;
    @XmlElement(required = true)
    protected Cedente.TitulosRecompra titulosRecompra;

    /**
     * Obt�m o valor da propriedade cnpj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpj(String value) {
        this.cnpj = value;
    }

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
     * Obt�m o valor da propriedade endereco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o valor da propriedade endereco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndereco(String value) {
        this.endereco = value;
    }

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
     * Obt�m o valor da propriedade complemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define o valor da propriedade complemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Obt�m o valor da propriedade bairro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Define o valor da propriedade bairro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBairro(String value) {
        this.bairro = value;
    }

    /**
     * Obt�m o valor da propriedade cidade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define o valor da propriedade cidade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidade(String value) {
        this.cidade = value;
    }

    /**
     * Obt�m o valor da propriedade uf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUf() {
        return uf;
    }

    /**
     * Define o valor da propriedade uf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUf(String value) {
        this.uf = value;
    }

    /**
     * Obt�m o valor da propriedade cep.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCep() {
        return cep;
    }

    /**
     * Define o valor da propriedade cep.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCep(String value) {
        this.cep = value;
    }

    /**
     * Obt�m o valor da propriedade telefone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o valor da propriedade telefone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefone(String value) {
        this.telefone = value;
    }

    /**
     * Obt�m o valor da propriedade inscricaoEstadual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    /**
     * Define o valor da propriedade inscricaoEstadual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscricaoEstadual(String value) {
        this.inscricaoEstadual = value;
    }

    /**
     * Obt�m o valor da propriedade inscricaoMunicipal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    /**
     * Define o valor da propriedade inscricaoMunicipal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscricaoMunicipal(String value) {
        this.inscricaoMunicipal = value;
    }

    /**
     * Obt�m o valor da propriedade partes.
     * 
     * @return
     *     possible object is
     *     {@link Cedente.Partes }
     *     
     */
    public Cedente.Partes getPartes() {
        return partes;
    }

    /**
     * Define o valor da propriedade partes.
     * 
     * @param value
     *     allowed object is
     *     {@link Cedente.Partes }
     *     
     */
    public void setPartes(Cedente.Partes value) {
        this.partes = value;
    }

    /**
     * Obt�m o valor da propriedade avalistas.
     * 
     * @return
     *     possible object is
     *     {@link Cedente.Avalistas }
     *     
     */
    public Cedente.Avalistas getAvalistas() {
        return avalistas;
    }

    /**
     * Define o valor da propriedade avalistas.
     * 
     * @param value
     *     allowed object is
     *     {@link Cedente.Avalistas }
     *     
     */
    public void setAvalistas(Cedente.Avalistas value) {
        this.avalistas = value;
    }

    /**
     * Obt�m o valor da propriedade titulos.
     * 
     * @return
     *     possible object is
     *     {@link Cedente.Titulos }
     *     
     */
    public Cedente.Titulos getTitulos() {
        return titulos;
    }

    /**
     * Define o valor da propriedade titulos.
     * 
     * @param value
     *     allowed object is
     *     {@link Cedente.Titulos }
     *     
     */
    public void setTitulos(Cedente.Titulos value) {
        this.titulos = value;
    }

    /**
     * Obt�m o valor da propriedade titulosRecompra.
     * 
     * @return
     *     possible object is
     *     {@link Cedente.TitulosRecompra }
     *     
     */
    public Cedente.TitulosRecompra getTitulosRecompra() {
        return titulosRecompra;
    }

    /**
     * Define o valor da propriedade titulosRecompra.
     * 
     * @param value
     *     allowed object is
     *     {@link Cedente.TitulosRecompra }
     *     
     */
    public void setTitulosRecompra(Cedente.TitulosRecompra value) {
        this.titulosRecompra = value;
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

        @XmlElement(nillable = true)
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
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parte" type="{http://webservices.portal.fidc.fromtis.com.br/}parteRepresentante" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<ParteRepresentante> parte;

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
         * {@link ParteRepresentante }
         * 
         * 
         */
        public List<ParteRepresentante> getParte() {
            if (parte == null) {
                parte = new ArrayList<ParteRepresentante>();
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
     *         &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRetorno" maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(nillable = true)
        protected List<TituloRetorno> titulo;

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
         * {@link TituloRetorno }
         * 
         * 
         */
        public List<TituloRetorno> getTitulo() {
            if (titulo == null) {
                titulo = new ArrayList<TituloRetorno>();
            }
            return this.titulo;
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
     *         &lt;element name="titulo" type="{http://webservices.portal.fidc.fromtis.com.br/}tituloRetorno" maxOccurs="unbounded" minOccurs="0"/>
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
    public static class TitulosRecompra {

        @XmlElement(nillable = true)
        protected List<TituloRetorno> titulo;

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
         * {@link TituloRetorno }
         * 
         * 
         */
        public List<TituloRetorno> getTitulo() {
            if (titulo == null) {
                titulo = new ArrayList<TituloRetorno>();
            }
            return this.titulo;
        }

    }


	public void criaCamposFaltando() {
		int i = 0;
		for(TituloRetorno tituloRetorno : getTitulos().getTitulo()) {
			i++;
			tituloRetorno.setNomePkcs7("MOCKWS" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy'T'HHmmss")) + (String.valueOf(i = i++)) + ("PKCS7") );
			tituloRetorno.getSacado().setEmail("mockEmail");
			tituloRetorno.getSacado().setEndereco("ENDMOCK" + i);
			for(ParteRepresentante  p : tituloRetorno.getSacado().getPartes().getParte()) {
				p.setEmail("mockEmail");
			}
		}
		
		for(TituloRetorno titulosRecompra : getTitulosRecompra().getTitulo()) {
			titulosRecompra.setNomePkcs7("MOCKWS" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy'T'HHmmss")) + (String.valueOf(i = i++)) + ("PKCS7") );
			titulosRecompra.getSacado().setEmail("mockEmail");
			titulosRecompra.getSacado().setEndereco("ENDMOCK" + i);
			for(ParteRepresentante  p : titulosRecompra.getSacado().getPartes().getParte()) {
				p.setEmail("mockEmail");
			}
		}
	}

}

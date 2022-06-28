/**
 * TituloRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

import java.math.BigDecimal;

public class TituloRetorno  implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;

	private java.lang.String numero = "";

    private java.lang.String numeroControleParticipante = "";

    private java.util.Calendar dataDeEmissaoDuplicata;

    private java.util.Calendar dataDeVencimentoDuplicata;

    private java.math.BigDecimal valorBruto = BigDecimal.ZERO;

    private java.math.BigDecimal valorLiquido = BigDecimal.ZERO;

    private java.lang.String numeroDaNotaFiscal = "";

    private java.lang.String serieDaNotaFiscal = "";

    private java.lang.String nomePkcs7 = "";

    private java.lang.String chaveNfe = "";

    private br.com.fromtis.fidc.portal.webservices.Sacado sacado;

    private boolean comCoobrigacao;

    private br.com.fromtis.fidc.portal.webservices.TipoTituloEnum tipo = TipoTituloEnum.value1;  // attribute

    public TituloRetorno() {
    }

    public TituloRetorno(
           java.lang.String numero,
           java.lang.String numeroControleParticipante,
           java.util.Calendar dataDeEmissaoDuplicata,
           java.util.Calendar dataDeVencimentoDuplicata,
           java.math.BigDecimal valorBruto,
           java.math.BigDecimal valorLiquido,
           java.lang.String numeroDaNotaFiscal,
           java.lang.String serieDaNotaFiscal,
           java.lang.String nomePkcs7,
           java.lang.String chaveNfe,
           br.com.fromtis.fidc.portal.webservices.Sacado sacado,
           boolean comCoobrigacao,
           br.com.fromtis.fidc.portal.webservices.TipoTituloEnum tipo) {
           this.numero = numero;
           this.numeroControleParticipante = numeroControleParticipante;
           this.dataDeEmissaoDuplicata = dataDeEmissaoDuplicata;
           this.dataDeVencimentoDuplicata = dataDeVencimentoDuplicata;
           this.valorBruto = valorBruto;
           this.valorLiquido = valorLiquido;
           this.numeroDaNotaFiscal = numeroDaNotaFiscal;
           this.serieDaNotaFiscal = serieDaNotaFiscal;
           this.nomePkcs7 = nomePkcs7;
           this.chaveNfe = chaveNfe;
           this.sacado = sacado;
           this.comCoobrigacao = comCoobrigacao;
           this.tipo = tipo;
    }


    /**
     * Gets the numero value for this TituloRetorno.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this TituloRetorno.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the numeroControleParticipante value for this TituloRetorno.
     * 
     * @return numeroControleParticipante
     */
    public java.lang.String getNumeroControleParticipante() {
        return numeroControleParticipante;
    }


    /**
     * Sets the numeroControleParticipante value for this TituloRetorno.
     * 
     * @param numeroControleParticipante
     */
    public void setNumeroControleParticipante(java.lang.String numeroControleParticipante) {
        this.numeroControleParticipante = numeroControleParticipante;
    }


    /**
     * Gets the dataDeEmissaoDuplicata value for this TituloRetorno.
     * 
     * @return dataDeEmissaoDuplicata
     */
    public java.util.Calendar getDataDeEmissaoDuplicata() {
        return dataDeEmissaoDuplicata;
    }


    /**
     * Sets the dataDeEmissaoDuplicata value for this TituloRetorno.
     * 
     * @param dataDeEmissaoDuplicata
     */
    public void setDataDeEmissaoDuplicata(java.util.Calendar dataDeEmissaoDuplicata) {
        this.dataDeEmissaoDuplicata = dataDeEmissaoDuplicata;
    }


    /**
     * Gets the dataDeVencimentoDuplicata value for this TituloRetorno.
     * 
     * @return dataDeVencimentoDuplicata
     */
    public java.util.Calendar getDataDeVencimentoDuplicata() {
        return dataDeVencimentoDuplicata;
    }


    /**
     * Sets the dataDeVencimentoDuplicata value for this TituloRetorno.
     * 
     * @param dataDeVencimentoDuplicata
     */
    public void setDataDeVencimentoDuplicata(java.util.Calendar dataDeVencimentoDuplicata) {
        this.dataDeVencimentoDuplicata = dataDeVencimentoDuplicata;
    }


    /**
     * Gets the valorBruto value for this TituloRetorno.
     * 
     * @return valorBruto
     */
    public java.math.BigDecimal getValorBruto() {
        return valorBruto;
    }


    /**
     * Sets the valorBruto value for this TituloRetorno.
     * 
     * @param valorBruto
     */
    public void setValorBruto(java.math.BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }


    /**
     * Gets the valorLiquido value for this TituloRetorno.
     * 
     * @return valorLiquido
     */
    public java.math.BigDecimal getValorLiquido() {
        return valorLiquido;
    }


    /**
     * Sets the valorLiquido value for this TituloRetorno.
     * 
     * @param valorLiquido
     */
    public void setValorLiquido(java.math.BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }


    /**
     * Gets the numeroDaNotaFiscal value for this TituloRetorno.
     * 
     * @return numeroDaNotaFiscal
     */
    public java.lang.String getNumeroDaNotaFiscal() {
        return numeroDaNotaFiscal;
    }


    /**
     * Sets the numeroDaNotaFiscal value for this TituloRetorno.
     * 
     * @param numeroDaNotaFiscal
     */
    public void setNumeroDaNotaFiscal(java.lang.String numeroDaNotaFiscal) {
        this.numeroDaNotaFiscal = numeroDaNotaFiscal;
    }


    /**
     * Gets the serieDaNotaFiscal value for this TituloRetorno.
     * 
     * @return serieDaNotaFiscal
     */
    public java.lang.String getSerieDaNotaFiscal() {
        return serieDaNotaFiscal;
    }


    /**
     * Sets the serieDaNotaFiscal value for this TituloRetorno.
     * 
     * @param serieDaNotaFiscal
     */
    public void setSerieDaNotaFiscal(java.lang.String serieDaNotaFiscal) {
        this.serieDaNotaFiscal = serieDaNotaFiscal;
    }


    /**
     * Gets the nomePkcs7 value for this TituloRetorno.
     * 
     * @return nomePkcs7
     */
    public java.lang.String getNomePkcs7() {
        return nomePkcs7;
    }


    /**
     * Sets the nomePkcs7 value for this TituloRetorno.
     * 
     * @param nomePkcs7
     */
    public void setNomePkcs7(java.lang.String nomePkcs7) {
        this.nomePkcs7 = nomePkcs7;
    }


    /**
     * Gets the chaveNfe value for this TituloRetorno.
     * 
     * @return chaveNfe
     */
    public java.lang.String getChaveNfe() {
        return chaveNfe;
    }


    /**
     * Sets the chaveNfe value for this TituloRetorno.
     * 
     * @param chaveNfe
     */
    public void setChaveNfe(java.lang.String chaveNfe) {
        this.chaveNfe = chaveNfe;
    }


    /**
     * Gets the sacado value for this TituloRetorno.
     * 
     * @return sacado
     */
    public br.com.fromtis.fidc.portal.webservices.Sacado getSacado() {
        return sacado;
    }


    /**
     * Sets the sacado value for this TituloRetorno.
     * 
     * @param sacado
     */
    public void setSacado(br.com.fromtis.fidc.portal.webservices.Sacado sacado) {
        this.sacado = sacado;
    }


    /**
     * Gets the comCoobrigacao value for this TituloRetorno.
     * 
     * @return comCoobrigacao
     */
    public boolean isComCoobrigacao() {
        return comCoobrigacao;
    }


    /**
     * Sets the comCoobrigacao value for this TituloRetorno.
     * 
     * @param comCoobrigacao
     */
    public void setComCoobrigacao(boolean comCoobrigacao) {
        this.comCoobrigacao = comCoobrigacao;
    }


    /**
     * Gets the tipo value for this TituloRetorno.
     * 
     * @return tipo
     */
    public br.com.fromtis.fidc.portal.webservices.TipoTituloEnum getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this TituloRetorno.
     * 
     * @param tipo
     */
    public void setTipo(br.com.fromtis.fidc.portal.webservices.TipoTituloEnum tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TituloRetorno)) return false;
        TituloRetorno other = (TituloRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.numeroControleParticipante==null && other.getNumeroControleParticipante()==null) || 
             (this.numeroControleParticipante!=null &&
              this.numeroControleParticipante.equals(other.getNumeroControleParticipante()))) &&
            ((this.dataDeEmissaoDuplicata==null && other.getDataDeEmissaoDuplicata()==null) || 
             (this.dataDeEmissaoDuplicata!=null &&
              this.dataDeEmissaoDuplicata.equals(other.getDataDeEmissaoDuplicata()))) &&
            ((this.dataDeVencimentoDuplicata==null && other.getDataDeVencimentoDuplicata()==null) || 
             (this.dataDeVencimentoDuplicata!=null &&
              this.dataDeVencimentoDuplicata.equals(other.getDataDeVencimentoDuplicata()))) &&
            ((this.valorBruto==null && other.getValorBruto()==null) || 
             (this.valorBruto!=null &&
              this.valorBruto.equals(other.getValorBruto()))) &&
            ((this.valorLiquido==null && other.getValorLiquido()==null) || 
             (this.valorLiquido!=null &&
              this.valorLiquido.equals(other.getValorLiquido()))) &&
            ((this.numeroDaNotaFiscal==null && other.getNumeroDaNotaFiscal()==null) || 
             (this.numeroDaNotaFiscal!=null &&
              this.numeroDaNotaFiscal.equals(other.getNumeroDaNotaFiscal()))) &&
            ((this.serieDaNotaFiscal==null && other.getSerieDaNotaFiscal()==null) || 
             (this.serieDaNotaFiscal!=null &&
              this.serieDaNotaFiscal.equals(other.getSerieDaNotaFiscal()))) &&
            ((this.nomePkcs7==null && other.getNomePkcs7()==null) || 
             (this.nomePkcs7!=null &&
              this.nomePkcs7.equals(other.getNomePkcs7()))) &&
            ((this.chaveNfe==null && other.getChaveNfe()==null) || 
             (this.chaveNfe!=null &&
              this.chaveNfe.equals(other.getChaveNfe()))) &&
            ((this.sacado==null && other.getSacado()==null) || 
             (this.sacado!=null &&
              this.sacado.equals(other.getSacado()))) &&
            this.comCoobrigacao == other.isComCoobrigacao() &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getNumeroControleParticipante() != null) {
            _hashCode += getNumeroControleParticipante().hashCode();
        }
        if (getDataDeEmissaoDuplicata() != null) {
            _hashCode += getDataDeEmissaoDuplicata().hashCode();
        }
        if (getDataDeVencimentoDuplicata() != null) {
            _hashCode += getDataDeVencimentoDuplicata().hashCode();
        }
        if (getValorBruto() != null) {
            _hashCode += getValorBruto().hashCode();
        }
        if (getValorLiquido() != null) {
            _hashCode += getValorLiquido().hashCode();
        }
        if (getNumeroDaNotaFiscal() != null) {
            _hashCode += getNumeroDaNotaFiscal().hashCode();
        }
        if (getSerieDaNotaFiscal() != null) {
            _hashCode += getSerieDaNotaFiscal().hashCode();
        }
        if (getNomePkcs7() != null) {
            _hashCode += getNomePkcs7().hashCode();
        }
        if (getChaveNfe() != null) {
            _hashCode += getChaveNfe().hashCode();
        }
        if (getSacado() != null) {
            _hashCode += getSacado().hashCode();
        }
        _hashCode += (isComCoobrigacao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TituloRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("tipo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tipoTituloEnum"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroControleParticipante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroControleParticipante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDeEmissaoDuplicata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataDeEmissaoDuplicata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDeVencimentoDuplicata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataDeVencimentoDuplicata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBruto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorBruto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorLiquido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorLiquido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDaNotaFiscal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDaNotaFiscal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieDaNotaFiscal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serieDaNotaFiscal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomePkcs7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomePkcs7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chaveNfe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chaveNfe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sacado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sacado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "sacado"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comCoobrigacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comCoobrigacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

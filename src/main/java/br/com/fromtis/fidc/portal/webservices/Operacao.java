/**
 * Operacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

import java.math.BigDecimal;

public class Operacao  implements java.io.Serializable {
    private java.math.BigDecimal valorLiquido = BigDecimal.ZERO;

    private java.math.BigDecimal valorBruto = BigDecimal.ZERO;

    private boolean comAdiantamento;

    private java.lang.String termoCessao = "";

    private java.math.BigDecimal valorRecompra;

    public Operacao() {
    }

    public Operacao(
           java.math.BigDecimal valorLiquido,
           java.math.BigDecimal valorBruto,
           boolean comAdiantamento,
           java.lang.String termoCessao,
           java.math.BigDecimal valorRecompra) {
           this.valorLiquido = valorLiquido;
           this.valorBruto = valorBruto;
           this.comAdiantamento = comAdiantamento;
           this.termoCessao = termoCessao;
           this.valorRecompra = valorRecompra;
    }


    /**
     * Gets the valorLiquido value for this Operacao.
     * 
     * @return valorLiquido
     */
    public java.math.BigDecimal getValorLiquido() {
        return valorLiquido;
    }


    /**
     * Sets the valorLiquido value for this Operacao.
     * 
     * @param valorLiquido
     */
    public void setValorLiquido(java.math.BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }


    /**
     * Gets the valorBruto value for this Operacao.
     * 
     * @return valorBruto
     */
    public java.math.BigDecimal getValorBruto() {
        return valorBruto;
    }


    /**
     * Sets the valorBruto value for this Operacao.
     * 
     * @param valorBruto
     */
    public void setValorBruto(java.math.BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }


    /**
     * Gets the comAdiantamento value for this Operacao.
     * 
     * @return comAdiantamento
     */
    public boolean isComAdiantamento() {
        return comAdiantamento;
    }


    /**
     * Sets the comAdiantamento value for this Operacao.
     * 
     * @param comAdiantamento
     */
    public void setComAdiantamento(boolean comAdiantamento) {
        this.comAdiantamento = comAdiantamento;
    }


    /**
     * Gets the termoCessao value for this Operacao.
     * 
     * @return termoCessao
     */
    public java.lang.String getTermoCessao() {
        return termoCessao;
    }


    /**
     * Sets the termoCessao value for this Operacao.
     * 
     * @param termoCessao
     */
    public void setTermoCessao(java.lang.String termoCessao) {
        this.termoCessao = termoCessao;
    }


    /**
     * Gets the valorRecompra value for this Operacao.
     * 
     * @return valorRecompra
     */
    public java.math.BigDecimal getValorRecompra() {
        return valorRecompra;
    }


    /**
     * Sets the valorRecompra value for this Operacao.
     * 
     * @param valorRecompra
     */
    public void setValorRecompra(java.math.BigDecimal valorRecompra) {
        this.valorRecompra = valorRecompra;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Operacao)) return false;
        Operacao other = (Operacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorLiquido==null && other.getValorLiquido()==null) || 
             (this.valorLiquido!=null &&
              this.valorLiquido.equals(other.getValorLiquido()))) &&
            ((this.valorBruto==null && other.getValorBruto()==null) || 
             (this.valorBruto!=null &&
              this.valorBruto.equals(other.getValorBruto()))) &&
            this.comAdiantamento == other.isComAdiantamento() &&
            ((this.termoCessao==null && other.getTermoCessao()==null) || 
             (this.termoCessao!=null &&
              this.termoCessao.equals(other.getTermoCessao()))) &&
            ((this.valorRecompra==null && other.getValorRecompra()==null) || 
             (this.valorRecompra!=null &&
              this.valorRecompra.equals(other.getValorRecompra())));
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
        if (getValorLiquido() != null) {
            _hashCode += getValorLiquido().hashCode();
        }
        if (getValorBruto() != null) {
            _hashCode += getValorBruto().hashCode();
        }
        _hashCode += (isComAdiantamento() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTermoCessao() != null) {
            _hashCode += getTermoCessao().hashCode();
        }
        if (getValorRecompra() != null) {
            _hashCode += getValorRecompra().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Operacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "operacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorLiquido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorLiquido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBruto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorBruto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comAdiantamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comAdiantamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termoCessao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "termoCessao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorRecompra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorRecompra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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

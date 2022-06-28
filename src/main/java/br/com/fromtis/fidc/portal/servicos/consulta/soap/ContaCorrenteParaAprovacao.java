/**
 * ContaCorrenteParaAprovacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class ContaCorrenteParaAprovacao  implements java.io.Serializable {
    private java.lang.String agencia;

    private java.lang.String conta;

    private java.lang.String codigoBanco;

    public ContaCorrenteParaAprovacao() {
    }

    public ContaCorrenteParaAprovacao(
           java.lang.String agencia,
           java.lang.String conta,
           java.lang.String codigoBanco) {
           this.agencia = agencia;
           this.conta = conta;
           this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the agencia value for this ContaCorrenteParaAprovacao.
     * 
     * @return agencia
     */
    public java.lang.String getAgencia() {
        return agencia;
    }


    /**
     * Sets the agencia value for this ContaCorrenteParaAprovacao.
     * 
     * @param agencia
     */
    public void setAgencia(java.lang.String agencia) {
        this.agencia = agencia;
    }


    /**
     * Gets the conta value for this ContaCorrenteParaAprovacao.
     * 
     * @return conta
     */
    public java.lang.String getConta() {
        return conta;
    }


    /**
     * Sets the conta value for this ContaCorrenteParaAprovacao.
     * 
     * @param conta
     */
    public void setConta(java.lang.String conta) {
        this.conta = conta;
    }


    /**
     * Gets the codigoBanco value for this ContaCorrenteParaAprovacao.
     * 
     * @return codigoBanco
     */
    public java.lang.String getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this ContaCorrenteParaAprovacao.
     * 
     * @param codigoBanco
     */
    public void setCodigoBanco(java.lang.String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContaCorrenteParaAprovacao)) return false;
        ContaCorrenteParaAprovacao other = (ContaCorrenteParaAprovacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.agencia==null && other.getAgencia()==null) || 
             (this.agencia!=null &&
              this.agencia.equals(other.getAgencia()))) &&
            ((this.conta==null && other.getConta()==null) || 
             (this.conta!=null &&
              this.conta.equals(other.getConta()))) &&
            ((this.codigoBanco==null && other.getCodigoBanco()==null) || 
             (this.codigoBanco!=null &&
              this.codigoBanco.equals(other.getCodigoBanco())));
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
        if (getAgencia() != null) {
            _hashCode += getAgencia().hashCode();
        }
        if (getConta() != null) {
            _hashCode += getConta().hashCode();
        }
        if (getCodigoBanco() != null) {
            _hashCode += getCodigoBanco().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContaCorrenteParaAprovacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contaCorrenteParaAprovacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

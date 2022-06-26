/**
 * FundoCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class FundoCadCedente  implements java.io.Serializable {
    private java.lang.String cnpjFundo;

    private java.lang.String nomeFundo;

    public FundoCadCedente() {
    }

    public FundoCadCedente(
           java.lang.String cnpjFundo,
           java.lang.String nomeFundo) {
           this.cnpjFundo = cnpjFundo;
           this.nomeFundo = nomeFundo;
    }


    /**
     * Gets the cnpjFundo value for this FundoCadCedente.
     * 
     * @return cnpjFundo
     */
    public java.lang.String getCnpjFundo() {
        return cnpjFundo;
    }


    /**
     * Sets the cnpjFundo value for this FundoCadCedente.
     * 
     * @param cnpjFundo
     */
    public void setCnpjFundo(java.lang.String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }


    /**
     * Gets the nomeFundo value for this FundoCadCedente.
     * 
     * @return nomeFundo
     */
    public java.lang.String getNomeFundo() {
        return nomeFundo;
    }


    /**
     * Sets the nomeFundo value for this FundoCadCedente.
     * 
     * @param nomeFundo
     */
    public void setNomeFundo(java.lang.String nomeFundo) {
        this.nomeFundo = nomeFundo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FundoCadCedente)) return false;
        FundoCadCedente other = (FundoCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cnpjFundo==null && other.getCnpjFundo()==null) || 
             (this.cnpjFundo!=null &&
              this.cnpjFundo.equals(other.getCnpjFundo()))) &&
            ((this.nomeFundo==null && other.getNomeFundo()==null) || 
             (this.nomeFundo!=null &&
              this.nomeFundo.equals(other.getNomeFundo())));
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
        if (getCnpjFundo() != null) {
            _hashCode += getCnpjFundo().hashCode();
        }
        if (getNomeFundo() != null) {
            _hashCode += getNomeFundo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FundoCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "fundoCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjFundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjFundo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeFundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeFundo"));
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

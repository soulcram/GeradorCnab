/**
 * Custodiante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class Custodiante  implements java.io.Serializable {
    private java.lang.String nomeCustodiante = "";

    private java.lang.String cnpjCustodiante = "";

    public Custodiante() {
    }

    public Custodiante(
           java.lang.String nomeCustodiante,
           java.lang.String cnpjCustodiante) {
           this.nomeCustodiante = nomeCustodiante;
           this.cnpjCustodiante = cnpjCustodiante;
    }


    /**
     * Gets the nomeCustodiante value for this Custodiante.
     * 
     * @return nomeCustodiante
     */
    public java.lang.String getNomeCustodiante() {
        return nomeCustodiante;
    }


    /**
     * Sets the nomeCustodiante value for this Custodiante.
     * 
     * @param nomeCustodiante
     */
    public void setNomeCustodiante(java.lang.String nomeCustodiante) {
        this.nomeCustodiante = nomeCustodiante;
    }


    /**
     * Gets the cnpjCustodiante value for this Custodiante.
     * 
     * @return cnpjCustodiante
     */
    public java.lang.String getCnpjCustodiante() {
        return cnpjCustodiante;
    }


    /**
     * Sets the cnpjCustodiante value for this Custodiante.
     * 
     * @param cnpjCustodiante
     */
    public void setCnpjCustodiante(java.lang.String cnpjCustodiante) {
        this.cnpjCustodiante = cnpjCustodiante;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Custodiante)) return false;
        Custodiante other = (Custodiante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeCustodiante==null && other.getNomeCustodiante()==null) || 
             (this.nomeCustodiante!=null &&
              this.nomeCustodiante.equals(other.getNomeCustodiante()))) &&
            ((this.cnpjCustodiante==null && other.getCnpjCustodiante()==null) || 
             (this.cnpjCustodiante!=null &&
              this.cnpjCustodiante.equals(other.getCnpjCustodiante())));
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
        if (getNomeCustodiante() != null) {
            _hashCode += getNomeCustodiante().hashCode();
        }
        if (getCnpjCustodiante() != null) {
            _hashCode += getCnpjCustodiante().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Custodiante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "custodiante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCustodiante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCustodiante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjCustodiante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjCustodiante"));
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

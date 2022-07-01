/**
 * RetornoImportacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class RetornoImportacao  implements java.io.Serializable {
    private java.lang.String statusCode;

    private java.lang.String descricaoRetorno;

    public RetornoImportacao() {
    }

    public RetornoImportacao(
           java.lang.String statusCode,
           java.lang.String descricaoRetorno) {
           this.statusCode = statusCode;
           this.descricaoRetorno = descricaoRetorno;
    }


    /**
     * Gets the statusCode value for this RetornoImportacao.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this RetornoImportacao.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the descricaoRetorno value for this RetornoImportacao.
     * 
     * @return descricaoRetorno
     */
    public java.lang.String getDescricaoRetorno() {
        return descricaoRetorno;
    }


    /**
     * Sets the descricaoRetorno value for this RetornoImportacao.
     * 
     * @param descricaoRetorno
     */
    public void setDescricaoRetorno(java.lang.String descricaoRetorno) {
        this.descricaoRetorno = descricaoRetorno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetornoImportacao)) return false;
        RetornoImportacao other = (RetornoImportacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.descricaoRetorno==null && other.getDescricaoRetorno()==null) || 
             (this.descricaoRetorno!=null &&
              this.descricaoRetorno.equals(other.getDescricaoRetorno())));
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
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getDescricaoRetorno() != null) {
            _hashCode += getDescricaoRetorno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetornoImportacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "retornoImportacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

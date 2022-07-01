/**
 * ArquivoCnab.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class ArquivoCnab  implements java.io.Serializable {
    private java.lang.String cnpjFundo;

    private byte[] arquivo;

    public ArquivoCnab() {
    }

    public ArquivoCnab(
           java.lang.String cnpjFundo,
           byte[] arquivo) {
           this.cnpjFundo = cnpjFundo;
           this.arquivo = arquivo;
    }


    /**
     * Gets the cnpjFundo value for this ArquivoCnab.
     * 
     * @return cnpjFundo
     */
    public java.lang.String getCnpjFundo() {
        return cnpjFundo;
    }


    /**
     * Sets the cnpjFundo value for this ArquivoCnab.
     * 
     * @param cnpjFundo
     */
    public void setCnpjFundo(java.lang.String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }


    /**
     * Gets the arquivo value for this ArquivoCnab.
     * 
     * @return arquivo
     */
    public byte[] getArquivo() {
        return arquivo;
    }


    /**
     * Sets the arquivo value for this ArquivoCnab.
     * 
     * @param arquivo
     */
    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArquivoCnab)) return false;
        ArquivoCnab other = (ArquivoCnab) obj;
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
            ((this.arquivo==null && other.getArquivo()==null) || 
             (this.arquivo!=null &&
              java.util.Arrays.equals(this.arquivo, other.getArquivo())));
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
        if (getArquivo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArquivo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArquivo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArquivoCnab.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "arquivoCnab"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjFundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjFundo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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

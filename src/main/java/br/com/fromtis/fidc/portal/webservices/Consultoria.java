/**
 * Consultoria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class Consultoria  implements java.io.Serializable {
    private java.lang.String nomeConsultoria = "";

    private java.lang.String cnpjConsultoria = "";

    private br.com.fromtis.fidc.portal.webservices.Parte[] partes = {new Parte()};

    public Consultoria() {
    }

    public Consultoria(
           java.lang.String nomeConsultoria,
           java.lang.String cnpjConsultoria,
           br.com.fromtis.fidc.portal.webservices.Parte[] partes) {
           this.nomeConsultoria = nomeConsultoria;
           this.cnpjConsultoria = cnpjConsultoria;
           this.partes = partes;
    }


    /**
     * Gets the nomeConsultoria value for this Consultoria.
     * 
     * @return nomeConsultoria
     */
    public java.lang.String getNomeConsultoria() {
        return nomeConsultoria;
    }


    /**
     * Sets the nomeConsultoria value for this Consultoria.
     * 
     * @param nomeConsultoria
     */
    public void setNomeConsultoria(java.lang.String nomeConsultoria) {
        this.nomeConsultoria = nomeConsultoria;
    }


    /**
     * Gets the cnpjConsultoria value for this Consultoria.
     * 
     * @return cnpjConsultoria
     */
    public java.lang.String getCnpjConsultoria() {
        return cnpjConsultoria;
    }


    /**
     * Sets the cnpjConsultoria value for this Consultoria.
     * 
     * @param cnpjConsultoria
     */
    public void setCnpjConsultoria(java.lang.String cnpjConsultoria) {
        this.cnpjConsultoria = cnpjConsultoria;
    }


    /**
     * Gets the partes value for this Consultoria.
     * 
     * @return partes
     */
    public br.com.fromtis.fidc.portal.webservices.Parte[] getPartes() {
        return partes;
    }


    /**
     * Sets the partes value for this Consultoria.
     * 
     * @param partes
     */
    public void setPartes(br.com.fromtis.fidc.portal.webservices.Parte[] partes) {
        this.partes = partes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Consultoria)) return false;
        Consultoria other = (Consultoria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeConsultoria==null && other.getNomeConsultoria()==null) || 
             (this.nomeConsultoria!=null &&
              this.nomeConsultoria.equals(other.getNomeConsultoria()))) &&
            ((this.cnpjConsultoria==null && other.getCnpjConsultoria()==null) || 
             (this.cnpjConsultoria!=null &&
              this.cnpjConsultoria.equals(other.getCnpjConsultoria()))) &&
            ((this.partes==null && other.getPartes()==null) || 
             (this.partes!=null &&
              java.util.Arrays.equals(this.partes, other.getPartes())));
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
        if (getNomeConsultoria() != null) {
            _hashCode += getNomeConsultoria().hashCode();
        }
        if (getCnpjConsultoria() != null) {
            _hashCode += getCnpjConsultoria().hashCode();
        }
        if (getPartes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartes(), i);
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
        new org.apache.axis.description.TypeDesc(Consultoria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "consultoria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeConsultoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeConsultoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjConsultoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjConsultoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parte"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parte"));
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

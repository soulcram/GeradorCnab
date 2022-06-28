/**
 * Fundo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class Fundo  implements java.io.Serializable {
    private java.lang.String cnpjFundo = "";

    private java.lang.String nomeFundo = "";

    private br.com.fromtis.fidc.portal.webservices.Parte[] partes = {new Parte()};

    private br.com.fromtis.fidc.portal.webservices.Testemunha[] testemunhas = {new Testemunha()};

    public Fundo() {
    }

    public Fundo(
           java.lang.String cnpjFundo,
           java.lang.String nomeFundo,
           br.com.fromtis.fidc.portal.webservices.Parte[] partes,
           br.com.fromtis.fidc.portal.webservices.Testemunha[] testemunhas) {
           this.cnpjFundo = cnpjFundo;
           this.nomeFundo = nomeFundo;
           this.partes = partes;
           this.testemunhas = testemunhas;
    }


    /**
     * Gets the cnpjFundo value for this Fundo.
     * 
     * @return cnpjFundo
     */
    public java.lang.String getCnpjFundo() {
        return cnpjFundo;
    }


    /**
     * Sets the cnpjFundo value for this Fundo.
     * 
     * @param cnpjFundo
     */
    public void setCnpjFundo(java.lang.String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }


    /**
     * Gets the nomeFundo value for this Fundo.
     * 
     * @return nomeFundo
     */
    public java.lang.String getNomeFundo() {
        return nomeFundo;
    }


    /**
     * Sets the nomeFundo value for this Fundo.
     * 
     * @param nomeFundo
     */
    public void setNomeFundo(java.lang.String nomeFundo) {
        this.nomeFundo = nomeFundo;
    }


    /**
     * Gets the partes value for this Fundo.
     * 
     * @return partes
     */
    public br.com.fromtis.fidc.portal.webservices.Parte[] getPartes() {
        return partes;
    }


    /**
     * Sets the partes value for this Fundo.
     * 
     * @param partes
     */
    public void setPartes(br.com.fromtis.fidc.portal.webservices.Parte[] partes) {
        this.partes = partes;
    }


    /**
     * Gets the testemunhas value for this Fundo.
     * 
     * @return testemunhas
     */
    public br.com.fromtis.fidc.portal.webservices.Testemunha[] getTestemunhas() {
        return testemunhas;
    }


    /**
     * Sets the testemunhas value for this Fundo.
     * 
     * @param testemunhas
     */
    public void setTestemunhas(br.com.fromtis.fidc.portal.webservices.Testemunha[] testemunhas) {
        this.testemunhas = testemunhas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Fundo)) return false;
        Fundo other = (Fundo) obj;
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
              this.nomeFundo.equals(other.getNomeFundo()))) &&
            ((this.partes==null && other.getPartes()==null) || 
             (this.partes!=null &&
              java.util.Arrays.equals(this.partes, other.getPartes()))) &&
            ((this.testemunhas==null && other.getTestemunhas()==null) || 
             (this.testemunhas!=null &&
              java.util.Arrays.equals(this.testemunhas, other.getTestemunhas())));
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
        if (getTestemunhas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTestemunhas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTestemunhas(), i);
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
        new org.apache.axis.description.TypeDesc(Fundo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "fundo"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parte"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parte"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testemunhas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "testemunhas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "testemunha"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "testemunha"));
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

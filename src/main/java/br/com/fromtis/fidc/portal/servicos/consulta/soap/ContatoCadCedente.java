/**
 * ContatoCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class ContatoCadCedente  implements java.io.Serializable {
    private java.lang.String nomeContato;

    private java.lang.String emailContato;

    private java.lang.String telContato;

    public ContatoCadCedente() {
    }

    public ContatoCadCedente(
           java.lang.String nomeContato,
           java.lang.String emailContato,
           java.lang.String telContato) {
           this.nomeContato = nomeContato;
           this.emailContato = emailContato;
           this.telContato = telContato;
    }


    /**
     * Gets the nomeContato value for this ContatoCadCedente.
     * 
     * @return nomeContato
     */
    public java.lang.String getNomeContato() {
        return nomeContato;
    }


    /**
     * Sets the nomeContato value for this ContatoCadCedente.
     * 
     * @param nomeContato
     */
    public void setNomeContato(java.lang.String nomeContato) {
        this.nomeContato = nomeContato;
    }


    /**
     * Gets the emailContato value for this ContatoCadCedente.
     * 
     * @return emailContato
     */
    public java.lang.String getEmailContato() {
        return emailContato;
    }


    /**
     * Sets the emailContato value for this ContatoCadCedente.
     * 
     * @param emailContato
     */
    public void setEmailContato(java.lang.String emailContato) {
        this.emailContato = emailContato;
    }


    /**
     * Gets the telContato value for this ContatoCadCedente.
     * 
     * @return telContato
     */
    public java.lang.String getTelContato() {
        return telContato;
    }


    /**
     * Sets the telContato value for this ContatoCadCedente.
     * 
     * @param telContato
     */
    public void setTelContato(java.lang.String telContato) {
        this.telContato = telContato;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContatoCadCedente)) return false;
        ContatoCadCedente other = (ContatoCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeContato==null && other.getNomeContato()==null) || 
             (this.nomeContato!=null &&
              this.nomeContato.equals(other.getNomeContato()))) &&
            ((this.emailContato==null && other.getEmailContato()==null) || 
             (this.emailContato!=null &&
              this.emailContato.equals(other.getEmailContato()))) &&
            ((this.telContato==null && other.getTelContato()==null) || 
             (this.telContato!=null &&
              this.telContato.equals(other.getTelContato())));
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
        if (getNomeContato() != null) {
            _hashCode += getNomeContato().hashCode();
        }
        if (getEmailContato() != null) {
            _hashCode += getEmailContato().hashCode();
        }
        if (getTelContato() != null) {
            _hashCode += getTelContato().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContatoCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contatoCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeContato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeContato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailContato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailContato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telContato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telContato"));
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

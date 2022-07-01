/**
 * AvalistaCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class AvalistaCadCedente  implements java.io.Serializable {
    private java.lang.String nomeAvalista;

    private java.lang.String tipoPessoaAvalista;

    private java.lang.String cnpjCpfAvalista;

    private java.lang.String email;

    public AvalistaCadCedente() {
    }

    public AvalistaCadCedente(
           java.lang.String nomeAvalista,
           java.lang.String tipoPessoaAvalista,
           java.lang.String cnpjCpfAvalista,
           java.lang.String email) {
           this.nomeAvalista = nomeAvalista;
           this.tipoPessoaAvalista = tipoPessoaAvalista;
           this.cnpjCpfAvalista = cnpjCpfAvalista;
           this.email = email;
    }


    /**
     * Gets the nomeAvalista value for this AvalistaCadCedente.
     * 
     * @return nomeAvalista
     */
    public java.lang.String getNomeAvalista() {
        return nomeAvalista;
    }


    /**
     * Sets the nomeAvalista value for this AvalistaCadCedente.
     * 
     * @param nomeAvalista
     */
    public void setNomeAvalista(java.lang.String nomeAvalista) {
        this.nomeAvalista = nomeAvalista;
    }


    /**
     * Gets the tipoPessoaAvalista value for this AvalistaCadCedente.
     * 
     * @return tipoPessoaAvalista
     */
    public java.lang.String getTipoPessoaAvalista() {
        return tipoPessoaAvalista;
    }


    /**
     * Sets the tipoPessoaAvalista value for this AvalistaCadCedente.
     * 
     * @param tipoPessoaAvalista
     */
    public void setTipoPessoaAvalista(java.lang.String tipoPessoaAvalista) {
        this.tipoPessoaAvalista = tipoPessoaAvalista;
    }


    /**
     * Gets the cnpjCpfAvalista value for this AvalistaCadCedente.
     * 
     * @return cnpjCpfAvalista
     */
    public java.lang.String getCnpjCpfAvalista() {
        return cnpjCpfAvalista;
    }


    /**
     * Sets the cnpjCpfAvalista value for this AvalistaCadCedente.
     * 
     * @param cnpjCpfAvalista
     */
    public void setCnpjCpfAvalista(java.lang.String cnpjCpfAvalista) {
        this.cnpjCpfAvalista = cnpjCpfAvalista;
    }


    /**
     * Gets the email value for this AvalistaCadCedente.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this AvalistaCadCedente.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvalistaCadCedente)) return false;
        AvalistaCadCedente other = (AvalistaCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeAvalista==null && other.getNomeAvalista()==null) || 
             (this.nomeAvalista!=null &&
              this.nomeAvalista.equals(other.getNomeAvalista()))) &&
            ((this.tipoPessoaAvalista==null && other.getTipoPessoaAvalista()==null) || 
             (this.tipoPessoaAvalista!=null &&
              this.tipoPessoaAvalista.equals(other.getTipoPessoaAvalista()))) &&
            ((this.cnpjCpfAvalista==null && other.getCnpjCpfAvalista()==null) || 
             (this.cnpjCpfAvalista!=null &&
              this.cnpjCpfAvalista.equals(other.getCnpjCpfAvalista()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail())));
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
        if (getNomeAvalista() != null) {
            _hashCode += getNomeAvalista().hashCode();
        }
        if (getTipoPessoaAvalista() != null) {
            _hashCode += getTipoPessoaAvalista().hashCode();
        }
        if (getCnpjCpfAvalista() != null) {
            _hashCode += getCnpjCpfAvalista().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AvalistaCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "avalistaCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeAvalista");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeAvalista"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPessoaAvalista");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPessoaAvalista"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjCpfAvalista");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjCpfAvalista"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
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

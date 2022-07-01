/**
 * RetornoCadastroCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class RetornoCadastroCedente  implements java.io.Serializable {
    private java.lang.String nomeCedente;

    private java.lang.String cnpjCedente;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.MensagemRetorno mensagemRetorno;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.ErroValidacaoCedCedente[] errosValidacao;

    public RetornoCadastroCedente() {
    }

    public RetornoCadastroCedente(
           java.lang.String nomeCedente,
           java.lang.String cnpjCedente,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.MensagemRetorno mensagemRetorno,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.ErroValidacaoCedCedente[] errosValidacao) {
           this.nomeCedente = nomeCedente;
           this.cnpjCedente = cnpjCedente;
           this.mensagemRetorno = mensagemRetorno;
           this.errosValidacao = errosValidacao;
    }


    /**
     * Gets the nomeCedente value for this RetornoCadastroCedente.
     * 
     * @return nomeCedente
     */
    public java.lang.String getNomeCedente() {
        return nomeCedente;
    }


    /**
     * Sets the nomeCedente value for this RetornoCadastroCedente.
     * 
     * @param nomeCedente
     */
    public void setNomeCedente(java.lang.String nomeCedente) {
        this.nomeCedente = nomeCedente;
    }


    /**
     * Gets the cnpjCedente value for this RetornoCadastroCedente.
     * 
     * @return cnpjCedente
     */
    public java.lang.String getCnpjCedente() {
        return cnpjCedente;
    }


    /**
     * Sets the cnpjCedente value for this RetornoCadastroCedente.
     * 
     * @param cnpjCedente
     */
    public void setCnpjCedente(java.lang.String cnpjCedente) {
        this.cnpjCedente = cnpjCedente;
    }


    /**
     * Gets the mensagemRetorno value for this RetornoCadastroCedente.
     * 
     * @return mensagemRetorno
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.MensagemRetorno getMensagemRetorno() {
        return mensagemRetorno;
    }


    /**
     * Sets the mensagemRetorno value for this RetornoCadastroCedente.
     * 
     * @param mensagemRetorno
     */
    public void setMensagemRetorno(br.com.fromtis.fidc.portal.servicos.consulta.soap.MensagemRetorno mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }


    /**
     * Gets the errosValidacao value for this RetornoCadastroCedente.
     * 
     * @return errosValidacao
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.ErroValidacaoCedCedente[] getErrosValidacao() {
        return errosValidacao;
    }


    /**
     * Sets the errosValidacao value for this RetornoCadastroCedente.
     * 
     * @param errosValidacao
     */
    public void setErrosValidacao(br.com.fromtis.fidc.portal.servicos.consulta.soap.ErroValidacaoCedCedente[] errosValidacao) {
        this.errosValidacao = errosValidacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetornoCadastroCedente)) return false;
        RetornoCadastroCedente other = (RetornoCadastroCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeCedente==null && other.getNomeCedente()==null) || 
             (this.nomeCedente!=null &&
              this.nomeCedente.equals(other.getNomeCedente()))) &&
            ((this.cnpjCedente==null && other.getCnpjCedente()==null) || 
             (this.cnpjCedente!=null &&
              this.cnpjCedente.equals(other.getCnpjCedente()))) &&
            ((this.mensagemRetorno==null && other.getMensagemRetorno()==null) || 
             (this.mensagemRetorno!=null &&
              this.mensagemRetorno.equals(other.getMensagemRetorno()))) &&
            ((this.errosValidacao==null && other.getErrosValidacao()==null) || 
             (this.errosValidacao!=null &&
              java.util.Arrays.equals(this.errosValidacao, other.getErrosValidacao())));
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
        if (getNomeCedente() != null) {
            _hashCode += getNomeCedente().hashCode();
        }
        if (getCnpjCedente() != null) {
            _hashCode += getCnpjCedente().hashCode();
        }
        if (getMensagemRetorno() != null) {
            _hashCode += getMensagemRetorno().hashCode();
        }
        if (getErrosValidacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrosValidacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrosValidacao(), i);
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
        new org.apache.axis.description.TypeDesc(RetornoCadastroCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "retornoCadastroCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeCedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjCedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjCedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensagemRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensagemRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "mensagemRetorno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errosValidacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errosValidacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "erroValidacaoCedCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "erro"));
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

/**
 * OperacaoAprovacaoGestor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class OperacaoAprovacaoGestor  implements java.io.Serializable {
    private java.lang.String cnpjFundo;

    private java.lang.String cpfCnpjCedente;

    private java.lang.String nomeArquivo;

    public OperacaoAprovacaoGestor() {
    }

    public OperacaoAprovacaoGestor(
           java.lang.String cnpjFundo,
           java.lang.String cpfCnpjCedente,
           java.lang.String nomeArquivo) {
           this.cnpjFundo = cnpjFundo;
           this.cpfCnpjCedente = cpfCnpjCedente;
           this.nomeArquivo = nomeArquivo;
    }


    /**
     * Gets the cnpjFundo value for this OperacaoAprovacaoGestor.
     * 
     * @return cnpjFundo
     */
    public java.lang.String getCnpjFundo() {
        return cnpjFundo;
    }


    /**
     * Sets the cnpjFundo value for this OperacaoAprovacaoGestor.
     * 
     * @param cnpjFundo
     */
    public void setCnpjFundo(java.lang.String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }


    /**
     * Gets the cpfCnpjCedente value for this OperacaoAprovacaoGestor.
     * 
     * @return cpfCnpjCedente
     */
    public java.lang.String getCpfCnpjCedente() {
        return cpfCnpjCedente;
    }


    /**
     * Sets the cpfCnpjCedente value for this OperacaoAprovacaoGestor.
     * 
     * @param cpfCnpjCedente
     */
    public void setCpfCnpjCedente(java.lang.String cpfCnpjCedente) {
        this.cpfCnpjCedente = cpfCnpjCedente;
    }


    /**
     * Gets the nomeArquivo value for this OperacaoAprovacaoGestor.
     * 
     * @return nomeArquivo
     */
    public java.lang.String getNomeArquivo() {
        return nomeArquivo;
    }


    /**
     * Sets the nomeArquivo value for this OperacaoAprovacaoGestor.
     * 
     * @param nomeArquivo
     */
    public void setNomeArquivo(java.lang.String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OperacaoAprovacaoGestor)) return false;
        OperacaoAprovacaoGestor other = (OperacaoAprovacaoGestor) obj;
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
            ((this.cpfCnpjCedente==null && other.getCpfCnpjCedente()==null) || 
             (this.cpfCnpjCedente!=null &&
              this.cpfCnpjCedente.equals(other.getCpfCnpjCedente()))) &&
            ((this.nomeArquivo==null && other.getNomeArquivo()==null) || 
             (this.nomeArquivo!=null &&
              this.nomeArquivo.equals(other.getNomeArquivo())));
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
        if (getCpfCnpjCedente() != null) {
            _hashCode += getCpfCnpjCedente().hashCode();
        }
        if (getNomeArquivo() != null) {
            _hashCode += getNomeArquivo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OperacaoAprovacaoGestor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "operacaoAprovacaoGestor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjFundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjFundo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfCnpjCedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfCnpjCedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeArquivo"));
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

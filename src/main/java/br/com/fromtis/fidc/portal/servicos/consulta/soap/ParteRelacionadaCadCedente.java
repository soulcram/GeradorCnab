/**
 * ParteRelacionadaCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class ParteRelacionadaCadCedente  implements java.io.Serializable {
    private java.lang.String nomeParteRelacionada;

    private java.lang.String tipoPessoaParteRelacionada;

    private java.lang.String cnpjCpfParteRelacionada;

    public ParteRelacionadaCadCedente() {
    }

    public ParteRelacionadaCadCedente(
           java.lang.String nomeParteRelacionada,
           java.lang.String tipoPessoaParteRelacionada,
           java.lang.String cnpjCpfParteRelacionada) {
           this.nomeParteRelacionada = nomeParteRelacionada;
           this.tipoPessoaParteRelacionada = tipoPessoaParteRelacionada;
           this.cnpjCpfParteRelacionada = cnpjCpfParteRelacionada;
    }


    /**
     * Gets the nomeParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @return nomeParteRelacionada
     */
    public java.lang.String getNomeParteRelacionada() {
        return nomeParteRelacionada;
    }


    /**
     * Sets the nomeParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @param nomeParteRelacionada
     */
    public void setNomeParteRelacionada(java.lang.String nomeParteRelacionada) {
        this.nomeParteRelacionada = nomeParteRelacionada;
    }


    /**
     * Gets the tipoPessoaParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @return tipoPessoaParteRelacionada
     */
    public java.lang.String getTipoPessoaParteRelacionada() {
        return tipoPessoaParteRelacionada;
    }


    /**
     * Sets the tipoPessoaParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @param tipoPessoaParteRelacionada
     */
    public void setTipoPessoaParteRelacionada(java.lang.String tipoPessoaParteRelacionada) {
        this.tipoPessoaParteRelacionada = tipoPessoaParteRelacionada;
    }


    /**
     * Gets the cnpjCpfParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @return cnpjCpfParteRelacionada
     */
    public java.lang.String getCnpjCpfParteRelacionada() {
        return cnpjCpfParteRelacionada;
    }


    /**
     * Sets the cnpjCpfParteRelacionada value for this ParteRelacionadaCadCedente.
     * 
     * @param cnpjCpfParteRelacionada
     */
    public void setCnpjCpfParteRelacionada(java.lang.String cnpjCpfParteRelacionada) {
        this.cnpjCpfParteRelacionada = cnpjCpfParteRelacionada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParteRelacionadaCadCedente)) return false;
        ParteRelacionadaCadCedente other = (ParteRelacionadaCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeParteRelacionada==null && other.getNomeParteRelacionada()==null) || 
             (this.nomeParteRelacionada!=null &&
              this.nomeParteRelacionada.equals(other.getNomeParteRelacionada()))) &&
            ((this.tipoPessoaParteRelacionada==null && other.getTipoPessoaParteRelacionada()==null) || 
             (this.tipoPessoaParteRelacionada!=null &&
              this.tipoPessoaParteRelacionada.equals(other.getTipoPessoaParteRelacionada()))) &&
            ((this.cnpjCpfParteRelacionada==null && other.getCnpjCpfParteRelacionada()==null) || 
             (this.cnpjCpfParteRelacionada!=null &&
              this.cnpjCpfParteRelacionada.equals(other.getCnpjCpfParteRelacionada())));
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
        if (getNomeParteRelacionada() != null) {
            _hashCode += getNomeParteRelacionada().hashCode();
        }
        if (getTipoPessoaParteRelacionada() != null) {
            _hashCode += getTipoPessoaParteRelacionada().hashCode();
        }
        if (getCnpjCpfParteRelacionada() != null) {
            _hashCode += getCnpjCpfParteRelacionada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParteRelacionadaCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "parteRelacionadaCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeParteRelacionada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeParteRelacionada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPessoaParteRelacionada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPessoaParteRelacionada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjCpfParteRelacionada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjCpfParteRelacionada"));
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

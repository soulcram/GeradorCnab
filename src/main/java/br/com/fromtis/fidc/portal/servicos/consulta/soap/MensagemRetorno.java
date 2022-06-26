/**
 * MensagemRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class MensagemRetorno  implements java.io.Serializable {
    private br.com.fromtis.fidc.portal.servicos.consulta.soap.TipoRetorno tipoRetorno;

    private java.lang.String descricaoRetorno;

    public MensagemRetorno() {
    }

    public MensagemRetorno(
           br.com.fromtis.fidc.portal.servicos.consulta.soap.TipoRetorno tipoRetorno,
           java.lang.String descricaoRetorno) {
           this.tipoRetorno = tipoRetorno;
           this.descricaoRetorno = descricaoRetorno;
    }


    /**
     * Gets the tipoRetorno value for this MensagemRetorno.
     * 
     * @return tipoRetorno
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }


    /**
     * Sets the tipoRetorno value for this MensagemRetorno.
     * 
     * @param tipoRetorno
     */
    public void setTipoRetorno(br.com.fromtis.fidc.portal.servicos.consulta.soap.TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }


    /**
     * Gets the descricaoRetorno value for this MensagemRetorno.
     * 
     * @return descricaoRetorno
     */
    public java.lang.String getDescricaoRetorno() {
        return descricaoRetorno;
    }


    /**
     * Sets the descricaoRetorno value for this MensagemRetorno.
     * 
     * @param descricaoRetorno
     */
    public void setDescricaoRetorno(java.lang.String descricaoRetorno) {
        this.descricaoRetorno = descricaoRetorno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MensagemRetorno)) return false;
        MensagemRetorno other = (MensagemRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoRetorno==null && other.getTipoRetorno()==null) || 
             (this.tipoRetorno!=null &&
              this.tipoRetorno.equals(other.getTipoRetorno()))) &&
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
        if (getTipoRetorno() != null) {
            _hashCode += getTipoRetorno().hashCode();
        }
        if (getDescricaoRetorno() != null) {
            _hashCode += getDescricaoRetorno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MensagemRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "mensagemRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "tipoRetorno"));
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

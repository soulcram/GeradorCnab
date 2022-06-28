/**
 * RetornoCertificadoDigital.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class RetornoCertificadoDigital  implements java.io.Serializable {
    private br.com.fromtis.fidc.portal.webservices.RetornoProcessamento retornoProcessamento;

    private br.com.fromtis.fidc.portal.webservices.Operacao operacao;

    private br.com.fromtis.fidc.portal.webservices.Custodiante custodiante;

    private br.com.fromtis.fidc.portal.webservices.Fundo fundo;

    private br.com.fromtis.fidc.portal.webservices.Consultoria consultoria;

    private br.com.fromtis.fidc.portal.webservices.CedenteRetorno cedente;

    private int id;  // attribute

    public RetornoCertificadoDigital() {
    }

    public RetornoCertificadoDigital(
           br.com.fromtis.fidc.portal.webservices.RetornoProcessamento retornoProcessamento,
           br.com.fromtis.fidc.portal.webservices.Operacao operacao,
           br.com.fromtis.fidc.portal.webservices.Custodiante custodiante,
           br.com.fromtis.fidc.portal.webservices.Fundo fundo,
           br.com.fromtis.fidc.portal.webservices.Consultoria consultoria,
           br.com.fromtis.fidc.portal.webservices.CedenteRetorno cedente,
           int id) {
           this.retornoProcessamento = retornoProcessamento;
           this.operacao = operacao;
           this.custodiante = custodiante;
           this.fundo = fundo;
           this.consultoria = consultoria;
           this.cedente = cedente;
           this.id = id;
    }


    /**
     * Gets the retornoProcessamento value for this RetornoCertificadoDigital.
     * 
     * @return retornoProcessamento
     */
    public br.com.fromtis.fidc.portal.webservices.RetornoProcessamento getRetornoProcessamento() {
        return retornoProcessamento;
    }


    /**
     * Sets the retornoProcessamento value for this RetornoCertificadoDigital.
     * 
     * @param retornoProcessamento
     */
    public void setRetornoProcessamento(br.com.fromtis.fidc.portal.webservices.RetornoProcessamento retornoProcessamento) {
        this.retornoProcessamento = retornoProcessamento;
    }


    /**
     * Gets the operacao value for this RetornoCertificadoDigital.
     * 
     * @return operacao
     */
    public br.com.fromtis.fidc.portal.webservices.Operacao getOperacao() {
        return operacao;
    }


    /**
     * Sets the operacao value for this RetornoCertificadoDigital.
     * 
     * @param operacao
     */
    public void setOperacao(br.com.fromtis.fidc.portal.webservices.Operacao operacao) {
        this.operacao = operacao;
    }


    /**
     * Gets the custodiante value for this RetornoCertificadoDigital.
     * 
     * @return custodiante
     */
    public br.com.fromtis.fidc.portal.webservices.Custodiante getCustodiante() {
        return custodiante;
    }


    /**
     * Sets the custodiante value for this RetornoCertificadoDigital.
     * 
     * @param custodiante
     */
    public void setCustodiante(br.com.fromtis.fidc.portal.webservices.Custodiante custodiante) {
        this.custodiante = custodiante;
    }


    /**
     * Gets the fundo value for this RetornoCertificadoDigital.
     * 
     * @return fundo
     */
    public br.com.fromtis.fidc.portal.webservices.Fundo getFundo() {
        return fundo;
    }


    /**
     * Sets the fundo value for this RetornoCertificadoDigital.
     * 
     * @param fundo
     */
    public void setFundo(br.com.fromtis.fidc.portal.webservices.Fundo fundo) {
        this.fundo = fundo;
    }


    /**
     * Gets the consultoria value for this RetornoCertificadoDigital.
     * 
     * @return consultoria
     */
    public br.com.fromtis.fidc.portal.webservices.Consultoria getConsultoria() {
        return consultoria;
    }


    /**
     * Sets the consultoria value for this RetornoCertificadoDigital.
     * 
     * @param consultoria
     */
    public void setConsultoria(br.com.fromtis.fidc.portal.webservices.Consultoria consultoria) {
        this.consultoria = consultoria;
    }


    /**
     * Gets the cedente value for this RetornoCertificadoDigital.
     * 
     * @return cedente
     */
    public br.com.fromtis.fidc.portal.webservices.CedenteRetorno getCedente() {
        return cedente;
    }


    /**
     * Sets the cedente value for this RetornoCertificadoDigital.
     * 
     * @param cedente
     */
    public void setCedente(br.com.fromtis.fidc.portal.webservices.CedenteRetorno cedente) {
        this.cedente = cedente;
    }


    /**
     * Gets the id value for this RetornoCertificadoDigital.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this RetornoCertificadoDigital.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetornoCertificadoDigital)) return false;
        RetornoCertificadoDigital other = (RetornoCertificadoDigital) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.retornoProcessamento==null && other.getRetornoProcessamento()==null) || 
             (this.retornoProcessamento!=null &&
              this.retornoProcessamento.equals(other.getRetornoProcessamento()))) &&
            ((this.operacao==null && other.getOperacao()==null) || 
             (this.operacao!=null &&
              this.operacao.equals(other.getOperacao()))) &&
            ((this.custodiante==null && other.getCustodiante()==null) || 
             (this.custodiante!=null &&
              this.custodiante.equals(other.getCustodiante()))) &&
            ((this.fundo==null && other.getFundo()==null) || 
             (this.fundo!=null &&
              this.fundo.equals(other.getFundo()))) &&
            ((this.consultoria==null && other.getConsultoria()==null) || 
             (this.consultoria!=null &&
              this.consultoria.equals(other.getConsultoria()))) &&
            ((this.cedente==null && other.getCedente()==null) || 
             (this.cedente!=null &&
              this.cedente.equals(other.getCedente()))) &&
            this.id == other.getId();
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
        if (getRetornoProcessamento() != null) {
            _hashCode += getRetornoProcessamento().hashCode();
        }
        if (getOperacao() != null) {
            _hashCode += getOperacao().hashCode();
        }
        if (getCustodiante() != null) {
            _hashCode += getCustodiante().hashCode();
        }
        if (getFundo() != null) {
            _hashCode += getFundo().hashCode();
        }
        if (getConsultoria() != null) {
            _hashCode += getConsultoria().hashCode();
        }
        if (getCedente() != null) {
            _hashCode += getCedente().hashCode();
        }
        _hashCode += getId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetornoCertificadoDigital.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoCertificadoDigital"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retornoProcessamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retornoProcessamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoProcessamento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "operacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("custodiante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "custodiante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "custodiante"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "fundo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consultoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "consultoria"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "cedenteRetorno"));
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

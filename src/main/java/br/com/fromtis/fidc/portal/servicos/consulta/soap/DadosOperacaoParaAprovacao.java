/**
 * DadosOperacaoParaAprovacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class DadosOperacaoParaAprovacao  implements java.io.Serializable {
    private java.lang.String cnpjFundo;

    private java.lang.String cpfCnpjCedente;

    private java.lang.String nomeArquivo;

    private java.math.BigDecimal valorReembolso;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteParaAprovacao contaCorrente;

    public DadosOperacaoParaAprovacao() {
    }

    public DadosOperacaoParaAprovacao(
           java.lang.String cnpjFundo,
           java.lang.String cpfCnpjCedente,
           java.lang.String nomeArquivo,
           java.math.BigDecimal valorReembolso,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteParaAprovacao contaCorrente) {
           this.cnpjFundo = cnpjFundo;
           this.cpfCnpjCedente = cpfCnpjCedente;
           this.nomeArquivo = nomeArquivo;
           this.valorReembolso = valorReembolso;
           this.contaCorrente = contaCorrente;
    }


    /**
     * Gets the cnpjFundo value for this DadosOperacaoParaAprovacao.
     * 
     * @return cnpjFundo
     */
    public java.lang.String getCnpjFundo() {
        return cnpjFundo;
    }


    /**
     * Sets the cnpjFundo value for this DadosOperacaoParaAprovacao.
     * 
     * @param cnpjFundo
     */
    public void setCnpjFundo(java.lang.String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }


    /**
     * Gets the cpfCnpjCedente value for this DadosOperacaoParaAprovacao.
     * 
     * @return cpfCnpjCedente
     */
    public java.lang.String getCpfCnpjCedente() {
        return cpfCnpjCedente;
    }


    /**
     * Sets the cpfCnpjCedente value for this DadosOperacaoParaAprovacao.
     * 
     * @param cpfCnpjCedente
     */
    public void setCpfCnpjCedente(java.lang.String cpfCnpjCedente) {
        this.cpfCnpjCedente = cpfCnpjCedente;
    }


    /**
     * Gets the nomeArquivo value for this DadosOperacaoParaAprovacao.
     * 
     * @return nomeArquivo
     */
    public java.lang.String getNomeArquivo() {
        return nomeArquivo;
    }


    /**
     * Sets the nomeArquivo value for this DadosOperacaoParaAprovacao.
     * 
     * @param nomeArquivo
     */
    public void setNomeArquivo(java.lang.String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }


    /**
     * Gets the valorReembolso value for this DadosOperacaoParaAprovacao.
     * 
     * @return valorReembolso
     */
    public java.math.BigDecimal getValorReembolso() {
        return valorReembolso;
    }


    /**
     * Sets the valorReembolso value for this DadosOperacaoParaAprovacao.
     * 
     * @param valorReembolso
     */
    public void setValorReembolso(java.math.BigDecimal valorReembolso) {
        this.valorReembolso = valorReembolso;
    }


    /**
     * Gets the contaCorrente value for this DadosOperacaoParaAprovacao.
     * 
     * @return contaCorrente
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteParaAprovacao getContaCorrente() {
        return contaCorrente;
    }


    /**
     * Sets the contaCorrente value for this DadosOperacaoParaAprovacao.
     * 
     * @param contaCorrente
     */
    public void setContaCorrente(br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteParaAprovacao contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DadosOperacaoParaAprovacao)) return false;
        DadosOperacaoParaAprovacao other = (DadosOperacaoParaAprovacao) obj;
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
              this.nomeArquivo.equals(other.getNomeArquivo()))) &&
            ((this.valorReembolso==null && other.getValorReembolso()==null) || 
             (this.valorReembolso!=null &&
              this.valorReembolso.equals(other.getValorReembolso()))) &&
            ((this.contaCorrente==null && other.getContaCorrente()==null) || 
             (this.contaCorrente!=null &&
              this.contaCorrente.equals(other.getContaCorrente())));
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
        if (getValorReembolso() != null) {
            _hashCode += getValorReembolso().hashCode();
        }
        if (getContaCorrente() != null) {
            _hashCode += getContaCorrente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosOperacaoParaAprovacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "dadosOperacaoParaAprovacao"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorReembolso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorReembolso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contaCorrente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contaCorrente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contaCorrenteParaAprovacao"));
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

/**
 * ContaCorrenteCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class ContaCorrenteCadCedente  implements java.io.Serializable {
    private java.lang.String banco;

    private java.lang.String agencia;

    private java.lang.String contaCorrente;

    private java.lang.String descricao;

    private java.lang.String padrao;

    private java.lang.String administrada;

    public ContaCorrenteCadCedente() {
    }

    public ContaCorrenteCadCedente(
           java.lang.String banco,
           java.lang.String agencia,
           java.lang.String contaCorrente,
           java.lang.String descricao,
           java.lang.String padrao,
           java.lang.String administrada) {
           this.banco = banco;
           this.agencia = agencia;
           this.contaCorrente = contaCorrente;
           this.descricao = descricao;
           this.padrao = padrao;
           this.administrada = administrada;
    }


    /**
     * Gets the banco value for this ContaCorrenteCadCedente.
     * 
     * @return banco
     */
    public java.lang.String getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this ContaCorrenteCadCedente.
     * 
     * @param banco
     */
    public void setBanco(java.lang.String banco) {
        this.banco = banco;
    }


    /**
     * Gets the agencia value for this ContaCorrenteCadCedente.
     * 
     * @return agencia
     */
    public java.lang.String getAgencia() {
        return agencia;
    }


    /**
     * Sets the agencia value for this ContaCorrenteCadCedente.
     * 
     * @param agencia
     */
    public void setAgencia(java.lang.String agencia) {
        this.agencia = agencia;
    }


    /**
     * Gets the contaCorrente value for this ContaCorrenteCadCedente.
     * 
     * @return contaCorrente
     */
    public java.lang.String getContaCorrente() {
        return contaCorrente;
    }


    /**
     * Sets the contaCorrente value for this ContaCorrenteCadCedente.
     * 
     * @param contaCorrente
     */
    public void setContaCorrente(java.lang.String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }


    /**
     * Gets the descricao value for this ContaCorrenteCadCedente.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ContaCorrenteCadCedente.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the padrao value for this ContaCorrenteCadCedente.
     * 
     * @return padrao
     */
    public java.lang.String getPadrao() {
        return padrao;
    }


    /**
     * Sets the padrao value for this ContaCorrenteCadCedente.
     * 
     * @param padrao
     */
    public void setPadrao(java.lang.String padrao) {
        this.padrao = padrao;
    }


    /**
     * Gets the administrada value for this ContaCorrenteCadCedente.
     * 
     * @return administrada
     */
    public java.lang.String getAdministrada() {
        return administrada;
    }


    /**
     * Sets the administrada value for this ContaCorrenteCadCedente.
     * 
     * @param administrada
     */
    public void setAdministrada(java.lang.String administrada) {
        this.administrada = administrada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContaCorrenteCadCedente)) return false;
        ContaCorrenteCadCedente other = (ContaCorrenteCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            ((this.agencia==null && other.getAgencia()==null) || 
             (this.agencia!=null &&
              this.agencia.equals(other.getAgencia()))) &&
            ((this.contaCorrente==null && other.getContaCorrente()==null) || 
             (this.contaCorrente!=null &&
              this.contaCorrente.equals(other.getContaCorrente()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            ((this.padrao==null && other.getPadrao()==null) || 
             (this.padrao!=null &&
              this.padrao.equals(other.getPadrao()))) &&
            ((this.administrada==null && other.getAdministrada()==null) || 
             (this.administrada!=null &&
              this.administrada.equals(other.getAdministrada())));
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
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        if (getAgencia() != null) {
            _hashCode += getAgencia().hashCode();
        }
        if (getContaCorrente() != null) {
            _hashCode += getContaCorrente().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        if (getPadrao() != null) {
            _hashCode += getPadrao().hashCode();
        }
        if (getAdministrada() != null) {
            _hashCode += getAdministrada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContaCorrenteCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contaCorrenteCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contaCorrente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contaCorrente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("padrao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "padrao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("administrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "administrada"));
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

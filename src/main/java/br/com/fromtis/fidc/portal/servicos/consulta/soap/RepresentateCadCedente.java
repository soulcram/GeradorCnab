/**
 * RepresentateCadCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class RepresentateCadCedente  implements java.io.Serializable {
    private java.lang.String nomeRepresentante;

    private java.lang.String cpfRepresentante;

    private java.lang.String emailRepresentante;

    private java.lang.String telefoneRepresentante;

    private java.lang.String assinaIsoladamente;

    private java.lang.String emiteDuplicata;

    private java.lang.String assinaPorEndosso;

    private java.lang.String assinaTermoCessao;

    public RepresentateCadCedente() {
    }

    public RepresentateCadCedente(
           java.lang.String nomeRepresentante,
           java.lang.String cpfRepresentante,
           java.lang.String emailRepresentante,
           java.lang.String telefoneRepresentante,
           java.lang.String assinaIsoladamente,
           java.lang.String emiteDuplicata,
           java.lang.String assinaPorEndosso,
           java.lang.String assinaTermoCessao) {
           this.nomeRepresentante = nomeRepresentante;
           this.cpfRepresentante = cpfRepresentante;
           this.emailRepresentante = emailRepresentante;
           this.telefoneRepresentante = telefoneRepresentante;
           this.assinaIsoladamente = assinaIsoladamente;
           this.emiteDuplicata = emiteDuplicata;
           this.assinaPorEndosso = assinaPorEndosso;
           this.assinaTermoCessao = assinaTermoCessao;
    }


    /**
     * Gets the nomeRepresentante value for this RepresentateCadCedente.
     * 
     * @return nomeRepresentante
     */
    public java.lang.String getNomeRepresentante() {
        return nomeRepresentante;
    }


    /**
     * Sets the nomeRepresentante value for this RepresentateCadCedente.
     * 
     * @param nomeRepresentante
     */
    public void setNomeRepresentante(java.lang.String nomeRepresentante) {
        this.nomeRepresentante = nomeRepresentante;
    }


    /**
     * Gets the cpfRepresentante value for this RepresentateCadCedente.
     * 
     * @return cpfRepresentante
     */
    public java.lang.String getCpfRepresentante() {
        return cpfRepresentante;
    }


    /**
     * Sets the cpfRepresentante value for this RepresentateCadCedente.
     * 
     * @param cpfRepresentante
     */
    public void setCpfRepresentante(java.lang.String cpfRepresentante) {
        this.cpfRepresentante = cpfRepresentante;
    }


    /**
     * Gets the emailRepresentante value for this RepresentateCadCedente.
     * 
     * @return emailRepresentante
     */
    public java.lang.String getEmailRepresentante() {
        return emailRepresentante;
    }


    /**
     * Sets the emailRepresentante value for this RepresentateCadCedente.
     * 
     * @param emailRepresentante
     */
    public void setEmailRepresentante(java.lang.String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }


    /**
     * Gets the telefoneRepresentante value for this RepresentateCadCedente.
     * 
     * @return telefoneRepresentante
     */
    public java.lang.String getTelefoneRepresentante() {
        return telefoneRepresentante;
    }


    /**
     * Sets the telefoneRepresentante value for this RepresentateCadCedente.
     * 
     * @param telefoneRepresentante
     */
    public void setTelefoneRepresentante(java.lang.String telefoneRepresentante) {
        this.telefoneRepresentante = telefoneRepresentante;
    }


    /**
     * Gets the assinaIsoladamente value for this RepresentateCadCedente.
     * 
     * @return assinaIsoladamente
     */
    public java.lang.String getAssinaIsoladamente() {
        return assinaIsoladamente;
    }


    /**
     * Sets the assinaIsoladamente value for this RepresentateCadCedente.
     * 
     * @param assinaIsoladamente
     */
    public void setAssinaIsoladamente(java.lang.String assinaIsoladamente) {
        this.assinaIsoladamente = assinaIsoladamente;
    }


    /**
     * Gets the emiteDuplicata value for this RepresentateCadCedente.
     * 
     * @return emiteDuplicata
     */
    public java.lang.String getEmiteDuplicata() {
        return emiteDuplicata;
    }


    /**
     * Sets the emiteDuplicata value for this RepresentateCadCedente.
     * 
     * @param emiteDuplicata
     */
    public void setEmiteDuplicata(java.lang.String emiteDuplicata) {
        this.emiteDuplicata = emiteDuplicata;
    }


    /**
     * Gets the assinaPorEndosso value for this RepresentateCadCedente.
     * 
     * @return assinaPorEndosso
     */
    public java.lang.String getAssinaPorEndosso() {
        return assinaPorEndosso;
    }


    /**
     * Sets the assinaPorEndosso value for this RepresentateCadCedente.
     * 
     * @param assinaPorEndosso
     */
    public void setAssinaPorEndosso(java.lang.String assinaPorEndosso) {
        this.assinaPorEndosso = assinaPorEndosso;
    }


    /**
     * Gets the assinaTermoCessao value for this RepresentateCadCedente.
     * 
     * @return assinaTermoCessao
     */
    public java.lang.String getAssinaTermoCessao() {
        return assinaTermoCessao;
    }


    /**
     * Sets the assinaTermoCessao value for this RepresentateCadCedente.
     * 
     * @param assinaTermoCessao
     */
    public void setAssinaTermoCessao(java.lang.String assinaTermoCessao) {
        this.assinaTermoCessao = assinaTermoCessao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepresentateCadCedente)) return false;
        RepresentateCadCedente other = (RepresentateCadCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeRepresentante==null && other.getNomeRepresentante()==null) || 
             (this.nomeRepresentante!=null &&
              this.nomeRepresentante.equals(other.getNomeRepresentante()))) &&
            ((this.cpfRepresentante==null && other.getCpfRepresentante()==null) || 
             (this.cpfRepresentante!=null &&
              this.cpfRepresentante.equals(other.getCpfRepresentante()))) &&
            ((this.emailRepresentante==null && other.getEmailRepresentante()==null) || 
             (this.emailRepresentante!=null &&
              this.emailRepresentante.equals(other.getEmailRepresentante()))) &&
            ((this.telefoneRepresentante==null && other.getTelefoneRepresentante()==null) || 
             (this.telefoneRepresentante!=null &&
              this.telefoneRepresentante.equals(other.getTelefoneRepresentante()))) &&
            ((this.assinaIsoladamente==null && other.getAssinaIsoladamente()==null) || 
             (this.assinaIsoladamente!=null &&
              this.assinaIsoladamente.equals(other.getAssinaIsoladamente()))) &&
            ((this.emiteDuplicata==null && other.getEmiteDuplicata()==null) || 
             (this.emiteDuplicata!=null &&
              this.emiteDuplicata.equals(other.getEmiteDuplicata()))) &&
            ((this.assinaPorEndosso==null && other.getAssinaPorEndosso()==null) || 
             (this.assinaPorEndosso!=null &&
              this.assinaPorEndosso.equals(other.getAssinaPorEndosso()))) &&
            ((this.assinaTermoCessao==null && other.getAssinaTermoCessao()==null) || 
             (this.assinaTermoCessao!=null &&
              this.assinaTermoCessao.equals(other.getAssinaTermoCessao())));
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
        if (getNomeRepresentante() != null) {
            _hashCode += getNomeRepresentante().hashCode();
        }
        if (getCpfRepresentante() != null) {
            _hashCode += getCpfRepresentante().hashCode();
        }
        if (getEmailRepresentante() != null) {
            _hashCode += getEmailRepresentante().hashCode();
        }
        if (getTelefoneRepresentante() != null) {
            _hashCode += getTelefoneRepresentante().hashCode();
        }
        if (getAssinaIsoladamente() != null) {
            _hashCode += getAssinaIsoladamente().hashCode();
        }
        if (getEmiteDuplicata() != null) {
            _hashCode += getEmiteDuplicata().hashCode();
        }
        if (getAssinaPorEndosso() != null) {
            _hashCode += getAssinaPorEndosso().hashCode();
        }
        if (getAssinaTermoCessao() != null) {
            _hashCode += getAssinaTermoCessao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepresentateCadCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "representateCadCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefoneRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefoneRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaIsoladamente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaIsoladamente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emiteDuplicata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emiteDuplicata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaPorEndosso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaPorEndosso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaTermoCessao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaTermoCessao"));
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

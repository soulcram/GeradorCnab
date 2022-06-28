/**
 * ParteRepresentante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class ParteRepresentante  implements java.io.Serializable {
    private java.lang.String nome = "";

    private java.lang.String cpf = "";

    private java.lang.String email = "";

    private java.lang.String papel = "";

    private boolean assinaIsoladamente;

    private boolean emiteDuplicata;

    private boolean assinaPorEndosso;

    private boolean assinaTermoCessao;

    public ParteRepresentante() {
    }

    public ParteRepresentante(
           java.lang.String nome,
           java.lang.String cpf,
           java.lang.String email,
           java.lang.String papel,
           boolean assinaIsoladamente,
           boolean emiteDuplicata,
           boolean assinaPorEndosso,
           boolean assinaTermoCessao) {
           this.nome = nome != null ? nome : "";
           this.cpf = cpf != null ? cpf : "";
           this.email = email != null ? email : "";
           this.papel = papel != null ? papel : "";
           this.assinaIsoladamente = assinaIsoladamente;
           this.emiteDuplicata = emiteDuplicata;
           this.assinaPorEndosso = assinaPorEndosso;
           this.assinaTermoCessao = assinaTermoCessao;
    }


    /**
     * Gets the nome value for this ParteRepresentante.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this ParteRepresentante.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the cpf value for this ParteRepresentante.
     * 
     * @return cpf
     */
    public java.lang.String getCpf() {
        return cpf;
    }


    /**
     * Sets the cpf value for this ParteRepresentante.
     * 
     * @param cpf
     */
    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }


    /**
     * Gets the email value for this ParteRepresentante.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this ParteRepresentante.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the papel value for this ParteRepresentante.
     * 
     * @return papel
     */
    public java.lang.String getPapel() {
        return papel;
    }


    /**
     * Sets the papel value for this ParteRepresentante.
     * 
     * @param papel
     */
    public void setPapel(java.lang.String papel) {
        this.papel = papel;
    }


    /**
     * Gets the assinaIsoladamente value for this ParteRepresentante.
     * 
     * @return assinaIsoladamente
     */
    public boolean isAssinaIsoladamente() {
        return assinaIsoladamente;
    }


    /**
     * Sets the assinaIsoladamente value for this ParteRepresentante.
     * 
     * @param assinaIsoladamente
     */
    public void setAssinaIsoladamente(boolean assinaIsoladamente) {
        this.assinaIsoladamente = assinaIsoladamente;
    }


    /**
     * Gets the emiteDuplicata value for this ParteRepresentante.
     * 
     * @return emiteDuplicata
     */
    public boolean isEmiteDuplicata() {
        return emiteDuplicata;
    }


    /**
     * Sets the emiteDuplicata value for this ParteRepresentante.
     * 
     * @param emiteDuplicata
     */
    public void setEmiteDuplicata(boolean emiteDuplicata) {
        this.emiteDuplicata = emiteDuplicata;
    }


    /**
     * Gets the assinaPorEndosso value for this ParteRepresentante.
     * 
     * @return assinaPorEndosso
     */
    public boolean isAssinaPorEndosso() {
        return assinaPorEndosso;
    }


    /**
     * Sets the assinaPorEndosso value for this ParteRepresentante.
     * 
     * @param assinaPorEndosso
     */
    public void setAssinaPorEndosso(boolean assinaPorEndosso) {
        this.assinaPorEndosso = assinaPorEndosso;
    }


    /**
     * Gets the assinaTermoCessao value for this ParteRepresentante.
     * 
     * @return assinaTermoCessao
     */
    public boolean isAssinaTermoCessao() {
        return assinaTermoCessao;
    }


    /**
     * Sets the assinaTermoCessao value for this ParteRepresentante.
     * 
     * @param assinaTermoCessao
     */
    public void setAssinaTermoCessao(boolean assinaTermoCessao) {
        this.assinaTermoCessao = assinaTermoCessao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParteRepresentante)) return false;
        ParteRepresentante other = (ParteRepresentante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.cpf==null && other.getCpf()==null) || 
             (this.cpf!=null &&
              this.cpf.equals(other.getCpf()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.papel==null && other.getPapel()==null) || 
             (this.papel!=null &&
              this.papel.equals(other.getPapel()))) &&
            this.assinaIsoladamente == other.isAssinaIsoladamente() &&
            this.emiteDuplicata == other.isEmiteDuplicata() &&
            this.assinaPorEndosso == other.isAssinaPorEndosso() &&
            this.assinaTermoCessao == other.isAssinaTermoCessao();
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
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getCpf() != null) {
            _hashCode += getCpf().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getPapel() != null) {
            _hashCode += getPapel().hashCode();
        }
        _hashCode += (isAssinaIsoladamente() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isEmiteDuplicata() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAssinaPorEndosso() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAssinaTermoCessao() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParteRepresentante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parteRepresentante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("papel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "papel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaIsoladamente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaIsoladamente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emiteDuplicata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emiteDuplicata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaPorEndosso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaPorEndosso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assinaTermoCessao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assinaTermoCessao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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

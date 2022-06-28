/**
 * CedenteRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class CedenteRetorno  implements java.io.Serializable {
    private java.lang.String cnpj = "";

    private java.lang.String nome = "";

    private java.lang.String banco = "";

    private java.lang.String agencia = "";

    private java.lang.String conta = "";

    private java.lang.String endereco = "";

    private java.lang.String numero = "";

    private java.lang.String complemento = "";

    private java.lang.String bairro = "";

    private java.lang.String cidade = "";

    private java.lang.String uf = "";

    private java.lang.String cep = "";

    private java.lang.String telefone = "";

    private java.lang.String inscricaoEstadual = "";

    private java.lang.String inscricaoMunicipal = "";

    private br.com.fromtis.fidc.portal.webservices.ParteRepresentante[] partes = {new ParteRepresentante()};

    private br.com.fromtis.fidc.portal.webservices.Avalista[] avalistas = {new Avalista()};

    private br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulos = {new TituloRetorno()};

    private br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulosRecompra = {new TituloRetorno()};

    public CedenteRetorno() {
    }

    public CedenteRetorno(
           java.lang.String cnpj,
           java.lang.String nome,
           java.lang.String banco,
           java.lang.String agencia,
           java.lang.String conta,
           java.lang.String endereco,
           java.lang.String numero,
           java.lang.String complemento,
           java.lang.String bairro,
           java.lang.String cidade,
           java.lang.String uf,
           java.lang.String cep,
           java.lang.String telefone,
           java.lang.String inscricaoEstadual,
           java.lang.String inscricaoMunicipal,
           br.com.fromtis.fidc.portal.webservices.ParteRepresentante[] partes,
           br.com.fromtis.fidc.portal.webservices.Avalista[] avalistas,
           br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulos,
           br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulosRecompra) {
           this.cnpj = cnpj;
           this.nome = nome != null ? nome : "";
           this.banco = banco;
           this.agencia = agencia;
           this.conta = conta;
           this.endereco = endereco;
           this.numero = numero;
           this.complemento = complemento;
           this.bairro = bairro;
           this.cidade = cidade;
           this.uf = uf;
           this.cep = cep;
           this.telefone = telefone;
           this.inscricaoEstadual = inscricaoEstadual;
           this.inscricaoMunicipal = inscricaoMunicipal;
           this.partes = partes;
           this.avalistas = avalistas;
           this.titulos = titulos;
           this.titulosRecompra = titulosRecompra;
    }


    /**
     * Gets the cnpj value for this CedenteRetorno.
     * 
     * @return cnpj
     */
    public java.lang.String getCnpj() {
        return cnpj;
    }


    /**
     * Sets the cnpj value for this CedenteRetorno.
     * 
     * @param cnpj
     */
    public void setCnpj(java.lang.String cnpj) {
        this.cnpj = cnpj;
    }


    /**
     * Gets the nome value for this CedenteRetorno.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this CedenteRetorno.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the banco value for this CedenteRetorno.
     * 
     * @return banco
     */
    public java.lang.String getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this CedenteRetorno.
     * 
     * @param banco
     */
    public void setBanco(java.lang.String banco) {
        this.banco = banco;
    }


    /**
     * Gets the agencia value for this CedenteRetorno.
     * 
     * @return agencia
     */
    public java.lang.String getAgencia() {
        return agencia;
    }


    /**
     * Sets the agencia value for this CedenteRetorno.
     * 
     * @param agencia
     */
    public void setAgencia(java.lang.String agencia) {
        this.agencia = agencia;
    }


    /**
     * Gets the conta value for this CedenteRetorno.
     * 
     * @return conta
     */
    public java.lang.String getConta() {
        return conta;
    }


    /**
     * Sets the conta value for this CedenteRetorno.
     * 
     * @param conta
     */
    public void setConta(java.lang.String conta) {
        this.conta = conta;
    }


    /**
     * Gets the endereco value for this CedenteRetorno.
     * 
     * @return endereco
     */
    public java.lang.String getEndereco() {
        return endereco;
    }


    /**
     * Sets the endereco value for this CedenteRetorno.
     * 
     * @param endereco
     */
    public void setEndereco(java.lang.String endereco) {
        this.endereco = endereco;
    }


    /**
     * Gets the numero value for this CedenteRetorno.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this CedenteRetorno.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the complemento value for this CedenteRetorno.
     * 
     * @return complemento
     */
    public java.lang.String getComplemento() {
        return complemento;
    }


    /**
     * Sets the complemento value for this CedenteRetorno.
     * 
     * @param complemento
     */
    public void setComplemento(java.lang.String complemento) {
        this.complemento = complemento;
    }


    /**
     * Gets the bairro value for this CedenteRetorno.
     * 
     * @return bairro
     */
    public java.lang.String getBairro() {
        return bairro;
    }


    /**
     * Sets the bairro value for this CedenteRetorno.
     * 
     * @param bairro
     */
    public void setBairro(java.lang.String bairro) {
        this.bairro = bairro;
    }


    /**
     * Gets the cidade value for this CedenteRetorno.
     * 
     * @return cidade
     */
    public java.lang.String getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this CedenteRetorno.
     * 
     * @param cidade
     */
    public void setCidade(java.lang.String cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the uf value for this CedenteRetorno.
     * 
     * @return uf
     */
    public java.lang.String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this CedenteRetorno.
     * 
     * @param uf
     */
    public void setUf(java.lang.String uf) {
        this.uf = uf;
    }


    /**
     * Gets the cep value for this CedenteRetorno.
     * 
     * @return cep
     */
    public java.lang.String getCep() {
        return cep;
    }


    /**
     * Sets the cep value for this CedenteRetorno.
     * 
     * @param cep
     */
    public void setCep(java.lang.String cep) {
        this.cep = cep;
    }


    /**
     * Gets the telefone value for this CedenteRetorno.
     * 
     * @return telefone
     */
    public java.lang.String getTelefone() {
        return telefone;
    }


    /**
     * Sets the telefone value for this CedenteRetorno.
     * 
     * @param telefone
     */
    public void setTelefone(java.lang.String telefone) {
        this.telefone = telefone;
    }


    /**
     * Gets the inscricaoEstadual value for this CedenteRetorno.
     * 
     * @return inscricaoEstadual
     */
    public java.lang.String getInscricaoEstadual() {
        return inscricaoEstadual;
    }


    /**
     * Sets the inscricaoEstadual value for this CedenteRetorno.
     * 
     * @param inscricaoEstadual
     */
    public void setInscricaoEstadual(java.lang.String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }


    /**
     * Gets the inscricaoMunicipal value for this CedenteRetorno.
     * 
     * @return inscricaoMunicipal
     */
    public java.lang.String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }


    /**
     * Sets the inscricaoMunicipal value for this CedenteRetorno.
     * 
     * @param inscricaoMunicipal
     */
    public void setInscricaoMunicipal(java.lang.String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }


    /**
     * Gets the partes value for this CedenteRetorno.
     * 
     * @return partes
     */
    public br.com.fromtis.fidc.portal.webservices.ParteRepresentante[] getPartes() {
        return partes;
    }


    /**
     * Sets the partes value for this CedenteRetorno.
     * 
     * @param partes
     */
    public void setPartes(br.com.fromtis.fidc.portal.webservices.ParteRepresentante[] partes) {
        this.partes = partes;
    }


    /**
     * Gets the avalistas value for this CedenteRetorno.
     * 
     * @return avalistas
     */
    public br.com.fromtis.fidc.portal.webservices.Avalista[] getAvalistas() {
        return avalistas;
    }


    /**
     * Sets the avalistas value for this CedenteRetorno.
     * 
     * @param avalistas
     */
    public void setAvalistas(br.com.fromtis.fidc.portal.webservices.Avalista[] avalistas) {
        this.avalistas = avalistas;
    }


    /**
     * Gets the titulos value for this CedenteRetorno.
     * 
     * @return titulos
     */
    public br.com.fromtis.fidc.portal.webservices.TituloRetorno[] getTitulos() {
        return titulos;
    }


    /**
     * Sets the titulos value for this CedenteRetorno.
     * 
     * @param titulos
     */
    public void setTitulos(br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulos) {
        this.titulos = titulos;
    }


    /**
     * Gets the titulosRecompra value for this CedenteRetorno.
     * 
     * @return titulosRecompra
     */
    public br.com.fromtis.fidc.portal.webservices.TituloRetorno[] getTitulosRecompra() {
        return titulosRecompra;
    }


    /**
     * Sets the titulosRecompra value for this CedenteRetorno.
     * 
     * @param titulosRecompra
     */
    public void setTitulosRecompra(br.com.fromtis.fidc.portal.webservices.TituloRetorno[] titulosRecompra) {
        this.titulosRecompra = titulosRecompra;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CedenteRetorno)) return false;
        CedenteRetorno other = (CedenteRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cnpj==null && other.getCnpj()==null) || 
             (this.cnpj!=null &&
              this.cnpj.equals(other.getCnpj()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            ((this.agencia==null && other.getAgencia()==null) || 
             (this.agencia!=null &&
              this.agencia.equals(other.getAgencia()))) &&
            ((this.conta==null && other.getConta()==null) || 
             (this.conta!=null &&
              this.conta.equals(other.getConta()))) &&
            ((this.endereco==null && other.getEndereco()==null) || 
             (this.endereco!=null &&
              this.endereco.equals(other.getEndereco()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.complemento==null && other.getComplemento()==null) || 
             (this.complemento!=null &&
              this.complemento.equals(other.getComplemento()))) &&
            ((this.bairro==null && other.getBairro()==null) || 
             (this.bairro!=null &&
              this.bairro.equals(other.getBairro()))) &&
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf()))) &&
            ((this.cep==null && other.getCep()==null) || 
             (this.cep!=null &&
              this.cep.equals(other.getCep()))) &&
            ((this.telefone==null && other.getTelefone()==null) || 
             (this.telefone!=null &&
              this.telefone.equals(other.getTelefone()))) &&
            ((this.inscricaoEstadual==null && other.getInscricaoEstadual()==null) || 
             (this.inscricaoEstadual!=null &&
              this.inscricaoEstadual.equals(other.getInscricaoEstadual()))) &&
            ((this.inscricaoMunicipal==null && other.getInscricaoMunicipal()==null) || 
             (this.inscricaoMunicipal!=null &&
              this.inscricaoMunicipal.equals(other.getInscricaoMunicipal()))) &&
            ((this.partes==null && other.getPartes()==null) || 
             (this.partes!=null &&
              java.util.Arrays.equals(this.partes, other.getPartes()))) &&
            ((this.avalistas==null && other.getAvalistas()==null) || 
             (this.avalistas!=null &&
              java.util.Arrays.equals(this.avalistas, other.getAvalistas()))) &&
            ((this.titulos==null && other.getTitulos()==null) || 
             (this.titulos!=null &&
              java.util.Arrays.equals(this.titulos, other.getTitulos()))) &&
            ((this.titulosRecompra==null && other.getTitulosRecompra()==null) || 
             (this.titulosRecompra!=null &&
              java.util.Arrays.equals(this.titulosRecompra, other.getTitulosRecompra())));
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
        if (getCnpj() != null) {
            _hashCode += getCnpj().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        if (getAgencia() != null) {
            _hashCode += getAgencia().hashCode();
        }
        if (getConta() != null) {
            _hashCode += getConta().hashCode();
        }
        if (getEndereco() != null) {
            _hashCode += getEndereco().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getComplemento() != null) {
            _hashCode += getComplemento().hashCode();
        }
        if (getBairro() != null) {
            _hashCode += getBairro().hashCode();
        }
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        if (getUf() != null) {
            _hashCode += getUf().hashCode();
        }
        if (getCep() != null) {
            _hashCode += getCep().hashCode();
        }
        if (getTelefone() != null) {
            _hashCode += getTelefone().hashCode();
        }
        if (getInscricaoEstadual() != null) {
            _hashCode += getInscricaoEstadual().hashCode();
        }
        if (getInscricaoMunicipal() != null) {
            _hashCode += getInscricaoMunicipal().hashCode();
        }
        if (getPartes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAvalistas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAvalistas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAvalistas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTitulos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTitulos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTitulos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTitulosRecompra() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTitulosRecompra());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTitulosRecompra(), i);
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
        new org.apache.axis.description.TypeDesc(CedenteRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "cedenteRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpj");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("conta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endereco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complemento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "complemento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bairro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cep");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inscricaoEstadual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inscricaoEstadual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inscricaoMunicipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inscricaoMunicipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parteRepresentante"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parte"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avalistas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "avalistas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "avalista"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "avalista"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "titulo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulosRecompra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulosRecompra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "titulo"));
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

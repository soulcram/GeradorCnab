/**
 * CadastroCedente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class CadastroCedente  implements java.io.Serializable {
    private br.com.fromtis.fidc.portal.servicos.consulta.soap.FundoCadCedente fundo;

    private java.lang.String tipoPessoa;

    private java.lang.String cnpjCpf;

    private java.lang.String nome;

    private java.lang.String email;

    private java.lang.String isentoInscricaoEstadual;

    private java.lang.String inscricaoEstadual;

    private java.lang.String inscricaoMunicipal;

    private java.lang.String porte;

    private java.lang.String ramodeAtividade;

    private java.lang.String tipodeSociedade;

    private java.lang.String faturamentoAnual;

    private java.lang.String conglomeradoEconomico;

    private java.lang.String classRisco;

    private java.lang.String autorizacao;

    private java.lang.String endereco;

    private java.lang.String numEndereco;

    private java.lang.String compEndereco;

    private java.lang.String cep;

    private java.lang.String bairro;

    private java.lang.String cidade;

    private java.lang.String uf;

    private java.lang.String dataContrato;

    private java.lang.String telefone;

    private java.lang.String limiteCredito;

    private java.lang.String fax;

    private java.lang.String minAprovacao;

    private java.lang.String codigoCoobrigacao;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteCadCedente[] contaCorrentes;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.ContatoCadCedente[] contatos;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.RepresentateCadCedente[] representantes;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.AvalistaCadCedente[] avalistas;

    private br.com.fromtis.fidc.portal.servicos.consulta.soap.ParteRelacionadaCadCedente[] partesRelacionadas;

    private java.lang.String instituicaoFinanceira;

    public CadastroCedente() {
    }

    public CadastroCedente(
           br.com.fromtis.fidc.portal.servicos.consulta.soap.FundoCadCedente fundo,
           java.lang.String tipoPessoa,
           java.lang.String cnpjCpf,
           java.lang.String nome,
           java.lang.String email,
           java.lang.String isentoInscricaoEstadual,
           java.lang.String inscricaoEstadual,
           java.lang.String inscricaoMunicipal,
           java.lang.String porte,
           java.lang.String ramodeAtividade,
           java.lang.String tipodeSociedade,
           java.lang.String faturamentoAnual,
           java.lang.String conglomeradoEconomico,
           java.lang.String classRisco,
           java.lang.String autorizacao,
           java.lang.String endereco,
           java.lang.String numEndereco,
           java.lang.String compEndereco,
           java.lang.String cep,
           java.lang.String bairro,
           java.lang.String cidade,
           java.lang.String uf,
           java.lang.String dataContrato,
           java.lang.String telefone,
           java.lang.String limiteCredito,
           java.lang.String fax,
           java.lang.String minAprovacao,
           java.lang.String codigoCoobrigacao,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteCadCedente[] contaCorrentes,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.ContatoCadCedente[] contatos,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.RepresentateCadCedente[] representantes,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.AvalistaCadCedente[] avalistas,
           br.com.fromtis.fidc.portal.servicos.consulta.soap.ParteRelacionadaCadCedente[] partesRelacionadas,
           java.lang.String instituicaoFinanceira) {
           this.fundo = fundo;
           this.tipoPessoa = tipoPessoa;
           this.cnpjCpf = cnpjCpf;
           this.nome = nome;
           this.email = email;
           this.isentoInscricaoEstadual = isentoInscricaoEstadual;
           this.inscricaoEstadual = inscricaoEstadual;
           this.inscricaoMunicipal = inscricaoMunicipal;
           this.porte = porte;
           this.ramodeAtividade = ramodeAtividade;
           this.tipodeSociedade = tipodeSociedade;
           this.faturamentoAnual = faturamentoAnual;
           this.conglomeradoEconomico = conglomeradoEconomico;
           this.classRisco = classRisco;
           this.autorizacao = autorizacao;
           this.endereco = endereco;
           this.numEndereco = numEndereco;
           this.compEndereco = compEndereco;
           this.cep = cep;
           this.bairro = bairro;
           this.cidade = cidade;
           this.uf = uf;
           this.dataContrato = dataContrato;
           this.telefone = telefone;
           this.limiteCredito = limiteCredito;
           this.fax = fax;
           this.minAprovacao = minAprovacao;
           this.codigoCoobrigacao = codigoCoobrigacao;
           this.contaCorrentes = contaCorrentes;
           this.contatos = contatos;
           this.representantes = representantes;
           this.avalistas = avalistas;
           this.partesRelacionadas = partesRelacionadas;
           this.instituicaoFinanceira = instituicaoFinanceira;
    }


    /**
     * Gets the fundo value for this CadastroCedente.
     * 
     * @return fundo
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.FundoCadCedente getFundo() {
        return fundo;
    }


    /**
     * Sets the fundo value for this CadastroCedente.
     * 
     * @param fundo
     */
    public void setFundo(br.com.fromtis.fidc.portal.servicos.consulta.soap.FundoCadCedente fundo) {
        this.fundo = fundo;
    }


    /**
     * Gets the tipoPessoa value for this CadastroCedente.
     * 
     * @return tipoPessoa
     */
    public java.lang.String getTipoPessoa() {
        return tipoPessoa;
    }


    /**
     * Sets the tipoPessoa value for this CadastroCedente.
     * 
     * @param tipoPessoa
     */
    public void setTipoPessoa(java.lang.String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }


    /**
     * Gets the cnpjCpf value for this CadastroCedente.
     * 
     * @return cnpjCpf
     */
    public java.lang.String getCnpjCpf() {
        return cnpjCpf;
    }


    /**
     * Sets the cnpjCpf value for this CadastroCedente.
     * 
     * @param cnpjCpf
     */
    public void setCnpjCpf(java.lang.String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }


    /**
     * Gets the nome value for this CadastroCedente.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this CadastroCedente.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the email value for this CadastroCedente.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this CadastroCedente.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the isentoInscricaoEstadual value for this CadastroCedente.
     * 
     * @return isentoInscricaoEstadual
     */
    public java.lang.String getIsentoInscricaoEstadual() {
        return isentoInscricaoEstadual;
    }


    /**
     * Sets the isentoInscricaoEstadual value for this CadastroCedente.
     * 
     * @param isentoInscricaoEstadual
     */
    public void setIsentoInscricaoEstadual(java.lang.String isentoInscricaoEstadual) {
        this.isentoInscricaoEstadual = isentoInscricaoEstadual;
    }


    /**
     * Gets the inscricaoEstadual value for this CadastroCedente.
     * 
     * @return inscricaoEstadual
     */
    public java.lang.String getInscricaoEstadual() {
        return inscricaoEstadual;
    }


    /**
     * Sets the inscricaoEstadual value for this CadastroCedente.
     * 
     * @param inscricaoEstadual
     */
    public void setInscricaoEstadual(java.lang.String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }


    /**
     * Gets the inscricaoMunicipal value for this CadastroCedente.
     * 
     * @return inscricaoMunicipal
     */
    public java.lang.String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }


    /**
     * Sets the inscricaoMunicipal value for this CadastroCedente.
     * 
     * @param inscricaoMunicipal
     */
    public void setInscricaoMunicipal(java.lang.String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }


    /**
     * Gets the porte value for this CadastroCedente.
     * 
     * @return porte
     */
    public java.lang.String getPorte() {
        return porte;
    }


    /**
     * Sets the porte value for this CadastroCedente.
     * 
     * @param porte
     */
    public void setPorte(java.lang.String porte) {
        this.porte = porte;
    }


    /**
     * Gets the ramodeAtividade value for this CadastroCedente.
     * 
     * @return ramodeAtividade
     */
    public java.lang.String getRamodeAtividade() {
        return ramodeAtividade;
    }


    /**
     * Sets the ramodeAtividade value for this CadastroCedente.
     * 
     * @param ramodeAtividade
     */
    public void setRamodeAtividade(java.lang.String ramodeAtividade) {
        this.ramodeAtividade = ramodeAtividade;
    }


    /**
     * Gets the tipodeSociedade value for this CadastroCedente.
     * 
     * @return tipodeSociedade
     */
    public java.lang.String getTipodeSociedade() {
        return tipodeSociedade;
    }


    /**
     * Sets the tipodeSociedade value for this CadastroCedente.
     * 
     * @param tipodeSociedade
     */
    public void setTipodeSociedade(java.lang.String tipodeSociedade) {
        this.tipodeSociedade = tipodeSociedade;
    }


    /**
     * Gets the faturamentoAnual value for this CadastroCedente.
     * 
     * @return faturamentoAnual
     */
    public java.lang.String getFaturamentoAnual() {
        return faturamentoAnual;
    }


    /**
     * Sets the faturamentoAnual value for this CadastroCedente.
     * 
     * @param faturamentoAnual
     */
    public void setFaturamentoAnual(java.lang.String faturamentoAnual) {
        this.faturamentoAnual = faturamentoAnual;
    }


    /**
     * Gets the conglomeradoEconomico value for this CadastroCedente.
     * 
     * @return conglomeradoEconomico
     */
    public java.lang.String getConglomeradoEconomico() {
        return conglomeradoEconomico;
    }


    /**
     * Sets the conglomeradoEconomico value for this CadastroCedente.
     * 
     * @param conglomeradoEconomico
     */
    public void setConglomeradoEconomico(java.lang.String conglomeradoEconomico) {
        this.conglomeradoEconomico = conglomeradoEconomico;
    }


    /**
     * Gets the classRisco value for this CadastroCedente.
     * 
     * @return classRisco
     */
    public java.lang.String getClassRisco() {
        return classRisco;
    }


    /**
     * Sets the classRisco value for this CadastroCedente.
     * 
     * @param classRisco
     */
    public void setClassRisco(java.lang.String classRisco) {
        this.classRisco = classRisco;
    }


    /**
     * Gets the autorizacao value for this CadastroCedente.
     * 
     * @return autorizacao
     */
    public java.lang.String getAutorizacao() {
        return autorizacao;
    }


    /**
     * Sets the autorizacao value for this CadastroCedente.
     * 
     * @param autorizacao
     */
    public void setAutorizacao(java.lang.String autorizacao) {
        this.autorizacao = autorizacao;
    }


    /**
     * Gets the endereco value for this CadastroCedente.
     * 
     * @return endereco
     */
    public java.lang.String getEndereco() {
        return endereco;
    }


    /**
     * Sets the endereco value for this CadastroCedente.
     * 
     * @param endereco
     */
    public void setEndereco(java.lang.String endereco) {
        this.endereco = endereco;
    }


    /**
     * Gets the numEndereco value for this CadastroCedente.
     * 
     * @return numEndereco
     */
    public java.lang.String getNumEndereco() {
        return numEndereco;
    }


    /**
     * Sets the numEndereco value for this CadastroCedente.
     * 
     * @param numEndereco
     */
    public void setNumEndereco(java.lang.String numEndereco) {
        this.numEndereco = numEndereco;
    }


    /**
     * Gets the compEndereco value for this CadastroCedente.
     * 
     * @return compEndereco
     */
    public java.lang.String getCompEndereco() {
        return compEndereco;
    }


    /**
     * Sets the compEndereco value for this CadastroCedente.
     * 
     * @param compEndereco
     */
    public void setCompEndereco(java.lang.String compEndereco) {
        this.compEndereco = compEndereco;
    }


    /**
     * Gets the cep value for this CadastroCedente.
     * 
     * @return cep
     */
    public java.lang.String getCep() {
        return cep;
    }


    /**
     * Sets the cep value for this CadastroCedente.
     * 
     * @param cep
     */
    public void setCep(java.lang.String cep) {
        this.cep = cep;
    }


    /**
     * Gets the bairro value for this CadastroCedente.
     * 
     * @return bairro
     */
    public java.lang.String getBairro() {
        return bairro;
    }


    /**
     * Sets the bairro value for this CadastroCedente.
     * 
     * @param bairro
     */
    public void setBairro(java.lang.String bairro) {
        this.bairro = bairro;
    }


    /**
     * Gets the cidade value for this CadastroCedente.
     * 
     * @return cidade
     */
    public java.lang.String getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this CadastroCedente.
     * 
     * @param cidade
     */
    public void setCidade(java.lang.String cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the uf value for this CadastroCedente.
     * 
     * @return uf
     */
    public java.lang.String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this CadastroCedente.
     * 
     * @param uf
     */
    public void setUf(java.lang.String uf) {
        this.uf = uf;
    }


    /**
     * Gets the dataContrato value for this CadastroCedente.
     * 
     * @return dataContrato
     */
    public java.lang.String getDataContrato() {
        return dataContrato;
    }


    /**
     * Sets the dataContrato value for this CadastroCedente.
     * 
     * @param dataContrato
     */
    public void setDataContrato(java.lang.String dataContrato) {
        this.dataContrato = dataContrato;
    }


    /**
     * Gets the telefone value for this CadastroCedente.
     * 
     * @return telefone
     */
    public java.lang.String getTelefone() {
        return telefone;
    }


    /**
     * Sets the telefone value for this CadastroCedente.
     * 
     * @param telefone
     */
    public void setTelefone(java.lang.String telefone) {
        this.telefone = telefone;
    }


    /**
     * Gets the limiteCredito value for this CadastroCedente.
     * 
     * @return limiteCredito
     */
    public java.lang.String getLimiteCredito() {
        return limiteCredito;
    }


    /**
     * Sets the limiteCredito value for this CadastroCedente.
     * 
     * @param limiteCredito
     */
    public void setLimiteCredito(java.lang.String limiteCredito) {
        this.limiteCredito = limiteCredito;
    }


    /**
     * Gets the fax value for this CadastroCedente.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this CadastroCedente.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the minAprovacao value for this CadastroCedente.
     * 
     * @return minAprovacao
     */
    public java.lang.String getMinAprovacao() {
        return minAprovacao;
    }


    /**
     * Sets the minAprovacao value for this CadastroCedente.
     * 
     * @param minAprovacao
     */
    public void setMinAprovacao(java.lang.String minAprovacao) {
        this.minAprovacao = minAprovacao;
    }


    /**
     * Gets the codigoCoobrigacao value for this CadastroCedente.
     * 
     * @return codigoCoobrigacao
     */
    public java.lang.String getCodigoCoobrigacao() {
        return codigoCoobrigacao;
    }


    /**
     * Sets the codigoCoobrigacao value for this CadastroCedente.
     * 
     * @param codigoCoobrigacao
     */
    public void setCodigoCoobrigacao(java.lang.String codigoCoobrigacao) {
        this.codigoCoobrigacao = codigoCoobrigacao;
    }


    /**
     * Gets the contaCorrentes value for this CadastroCedente.
     * 
     * @return contaCorrentes
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteCadCedente[] getContaCorrentes() {
        return contaCorrentes;
    }


    /**
     * Sets the contaCorrentes value for this CadastroCedente.
     * 
     * @param contaCorrentes
     */
    public void setContaCorrentes(br.com.fromtis.fidc.portal.servicos.consulta.soap.ContaCorrenteCadCedente[] contaCorrentes) {
        this.contaCorrentes = contaCorrentes;
    }


    /**
     * Gets the contatos value for this CadastroCedente.
     * 
     * @return contatos
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.ContatoCadCedente[] getContatos() {
        return contatos;
    }


    /**
     * Sets the contatos value for this CadastroCedente.
     * 
     * @param contatos
     */
    public void setContatos(br.com.fromtis.fidc.portal.servicos.consulta.soap.ContatoCadCedente[] contatos) {
        this.contatos = contatos;
    }


    /**
     * Gets the representantes value for this CadastroCedente.
     * 
     * @return representantes
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.RepresentateCadCedente[] getRepresentantes() {
        return representantes;
    }


    /**
     * Sets the representantes value for this CadastroCedente.
     * 
     * @param representantes
     */
    public void setRepresentantes(br.com.fromtis.fidc.portal.servicos.consulta.soap.RepresentateCadCedente[] representantes) {
        this.representantes = representantes;
    }


    /**
     * Gets the avalistas value for this CadastroCedente.
     * 
     * @return avalistas
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.AvalistaCadCedente[] getAvalistas() {
        return avalistas;
    }


    /**
     * Sets the avalistas value for this CadastroCedente.
     * 
     * @param avalistas
     */
    public void setAvalistas(br.com.fromtis.fidc.portal.servicos.consulta.soap.AvalistaCadCedente[] avalistas) {
        this.avalistas = avalistas;
    }


    /**
     * Gets the partesRelacionadas value for this CadastroCedente.
     * 
     * @return partesRelacionadas
     */
    public br.com.fromtis.fidc.portal.servicos.consulta.soap.ParteRelacionadaCadCedente[] getPartesRelacionadas() {
        return partesRelacionadas;
    }


    /**
     * Sets the partesRelacionadas value for this CadastroCedente.
     * 
     * @param partesRelacionadas
     */
    public void setPartesRelacionadas(br.com.fromtis.fidc.portal.servicos.consulta.soap.ParteRelacionadaCadCedente[] partesRelacionadas) {
        this.partesRelacionadas = partesRelacionadas;
    }


    /**
     * Gets the instituicaoFinanceira value for this CadastroCedente.
     * 
     * @return instituicaoFinanceira
     */
    public java.lang.String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }


    /**
     * Sets the instituicaoFinanceira value for this CadastroCedente.
     * 
     * @param instituicaoFinanceira
     */
    public void setInstituicaoFinanceira(java.lang.String instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CadastroCedente)) return false;
        CadastroCedente other = (CadastroCedente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fundo==null && other.getFundo()==null) || 
             (this.fundo!=null &&
              this.fundo.equals(other.getFundo()))) &&
            ((this.tipoPessoa==null && other.getTipoPessoa()==null) || 
             (this.tipoPessoa!=null &&
              this.tipoPessoa.equals(other.getTipoPessoa()))) &&
            ((this.cnpjCpf==null && other.getCnpjCpf()==null) || 
             (this.cnpjCpf!=null &&
              this.cnpjCpf.equals(other.getCnpjCpf()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.isentoInscricaoEstadual==null && other.getIsentoInscricaoEstadual()==null) || 
             (this.isentoInscricaoEstadual!=null &&
              this.isentoInscricaoEstadual.equals(other.getIsentoInscricaoEstadual()))) &&
            ((this.inscricaoEstadual==null && other.getInscricaoEstadual()==null) || 
             (this.inscricaoEstadual!=null &&
              this.inscricaoEstadual.equals(other.getInscricaoEstadual()))) &&
            ((this.inscricaoMunicipal==null && other.getInscricaoMunicipal()==null) || 
             (this.inscricaoMunicipal!=null &&
              this.inscricaoMunicipal.equals(other.getInscricaoMunicipal()))) &&
            ((this.porte==null && other.getPorte()==null) || 
             (this.porte!=null &&
              this.porte.equals(other.getPorte()))) &&
            ((this.ramodeAtividade==null && other.getRamodeAtividade()==null) || 
             (this.ramodeAtividade!=null &&
              this.ramodeAtividade.equals(other.getRamodeAtividade()))) &&
            ((this.tipodeSociedade==null && other.getTipodeSociedade()==null) || 
             (this.tipodeSociedade!=null &&
              this.tipodeSociedade.equals(other.getTipodeSociedade()))) &&
            ((this.faturamentoAnual==null && other.getFaturamentoAnual()==null) || 
             (this.faturamentoAnual!=null &&
              this.faturamentoAnual.equals(other.getFaturamentoAnual()))) &&
            ((this.conglomeradoEconomico==null && other.getConglomeradoEconomico()==null) || 
             (this.conglomeradoEconomico!=null &&
              this.conglomeradoEconomico.equals(other.getConglomeradoEconomico()))) &&
            ((this.classRisco==null && other.getClassRisco()==null) || 
             (this.classRisco!=null &&
              this.classRisco.equals(other.getClassRisco()))) &&
            ((this.autorizacao==null && other.getAutorizacao()==null) || 
             (this.autorizacao!=null &&
              this.autorizacao.equals(other.getAutorizacao()))) &&
            ((this.endereco==null && other.getEndereco()==null) || 
             (this.endereco!=null &&
              this.endereco.equals(other.getEndereco()))) &&
            ((this.numEndereco==null && other.getNumEndereco()==null) || 
             (this.numEndereco!=null &&
              this.numEndereco.equals(other.getNumEndereco()))) &&
            ((this.compEndereco==null && other.getCompEndereco()==null) || 
             (this.compEndereco!=null &&
              this.compEndereco.equals(other.getCompEndereco()))) &&
            ((this.cep==null && other.getCep()==null) || 
             (this.cep!=null &&
              this.cep.equals(other.getCep()))) &&
            ((this.bairro==null && other.getBairro()==null) || 
             (this.bairro!=null &&
              this.bairro.equals(other.getBairro()))) &&
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf()))) &&
            ((this.dataContrato==null && other.getDataContrato()==null) || 
             (this.dataContrato!=null &&
              this.dataContrato.equals(other.getDataContrato()))) &&
            ((this.telefone==null && other.getTelefone()==null) || 
             (this.telefone!=null &&
              this.telefone.equals(other.getTelefone()))) &&
            ((this.limiteCredito==null && other.getLimiteCredito()==null) || 
             (this.limiteCredito!=null &&
              this.limiteCredito.equals(other.getLimiteCredito()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.minAprovacao==null && other.getMinAprovacao()==null) || 
             (this.minAprovacao!=null &&
              this.minAprovacao.equals(other.getMinAprovacao()))) &&
            ((this.codigoCoobrigacao==null && other.getCodigoCoobrigacao()==null) || 
             (this.codigoCoobrigacao!=null &&
              this.codigoCoobrigacao.equals(other.getCodigoCoobrigacao()))) &&
            ((this.contaCorrentes==null && other.getContaCorrentes()==null) || 
             (this.contaCorrentes!=null &&
              java.util.Arrays.equals(this.contaCorrentes, other.getContaCorrentes()))) &&
            ((this.contatos==null && other.getContatos()==null) || 
             (this.contatos!=null &&
              java.util.Arrays.equals(this.contatos, other.getContatos()))) &&
            ((this.representantes==null && other.getRepresentantes()==null) || 
             (this.representantes!=null &&
              java.util.Arrays.equals(this.representantes, other.getRepresentantes()))) &&
            ((this.avalistas==null && other.getAvalistas()==null) || 
             (this.avalistas!=null &&
              java.util.Arrays.equals(this.avalistas, other.getAvalistas()))) &&
            ((this.partesRelacionadas==null && other.getPartesRelacionadas()==null) || 
             (this.partesRelacionadas!=null &&
              java.util.Arrays.equals(this.partesRelacionadas, other.getPartesRelacionadas()))) &&
            ((this.instituicaoFinanceira==null && other.getInstituicaoFinanceira()==null) || 
             (this.instituicaoFinanceira!=null &&
              this.instituicaoFinanceira.equals(other.getInstituicaoFinanceira())));
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
        if (getFundo() != null) {
            _hashCode += getFundo().hashCode();
        }
        if (getTipoPessoa() != null) {
            _hashCode += getTipoPessoa().hashCode();
        }
        if (getCnpjCpf() != null) {
            _hashCode += getCnpjCpf().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getIsentoInscricaoEstadual() != null) {
            _hashCode += getIsentoInscricaoEstadual().hashCode();
        }
        if (getInscricaoEstadual() != null) {
            _hashCode += getInscricaoEstadual().hashCode();
        }
        if (getInscricaoMunicipal() != null) {
            _hashCode += getInscricaoMunicipal().hashCode();
        }
        if (getPorte() != null) {
            _hashCode += getPorte().hashCode();
        }
        if (getRamodeAtividade() != null) {
            _hashCode += getRamodeAtividade().hashCode();
        }
        if (getTipodeSociedade() != null) {
            _hashCode += getTipodeSociedade().hashCode();
        }
        if (getFaturamentoAnual() != null) {
            _hashCode += getFaturamentoAnual().hashCode();
        }
        if (getConglomeradoEconomico() != null) {
            _hashCode += getConglomeradoEconomico().hashCode();
        }
        if (getClassRisco() != null) {
            _hashCode += getClassRisco().hashCode();
        }
        if (getAutorizacao() != null) {
            _hashCode += getAutorizacao().hashCode();
        }
        if (getEndereco() != null) {
            _hashCode += getEndereco().hashCode();
        }
        if (getNumEndereco() != null) {
            _hashCode += getNumEndereco().hashCode();
        }
        if (getCompEndereco() != null) {
            _hashCode += getCompEndereco().hashCode();
        }
        if (getCep() != null) {
            _hashCode += getCep().hashCode();
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
        if (getDataContrato() != null) {
            _hashCode += getDataContrato().hashCode();
        }
        if (getTelefone() != null) {
            _hashCode += getTelefone().hashCode();
        }
        if (getLimiteCredito() != null) {
            _hashCode += getLimiteCredito().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getMinAprovacao() != null) {
            _hashCode += getMinAprovacao().hashCode();
        }
        if (getCodigoCoobrigacao() != null) {
            _hashCode += getCodigoCoobrigacao().hashCode();
        }
        if (getContaCorrentes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContaCorrentes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContaCorrentes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContatos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContatos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContatos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRepresentantes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRepresentantes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRepresentantes(), i);
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
        if (getPartesRelacionadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartesRelacionadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartesRelacionadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInstituicaoFinanceira() != null) {
            _hashCode += getInstituicaoFinanceira().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CadastroCedente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "cadastroCedente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "fundoCadCedente"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPessoa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPessoa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnpjCpf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnpjCpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isentoInscricaoEstadual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isentoInscricaoEstadual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("porte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramodeAtividade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ramodeAtividade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipodeSociedade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipodeSociedade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faturamentoAnual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faturamentoAnual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conglomeradoEconomico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conglomeradoEconomico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classRisco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classRisco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autorizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autorizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endereco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numEndereco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numEndereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compEndereco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "compEndereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cep");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bairro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minAprovacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minAprovacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCoobrigacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoCoobrigacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contaCorrentes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contaCorrentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contaCorrenteCadCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "contaCorrente"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contatos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "contatoCadCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "contato"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("representantes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "representantes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "representateCadCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "representante"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avalistas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "avalistas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "avalistaCadCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "avalista"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partesRelacionadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partesRelacionadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "parteRelacionadaCadCedente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parteRelacionada"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instituicaoFinanceira");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instituicaoFinanceira"));
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

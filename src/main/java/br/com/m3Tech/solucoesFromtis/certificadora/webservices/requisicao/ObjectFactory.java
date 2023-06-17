
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Avalista;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Cedente.Partes;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Consultoria;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Custodiante;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Fundo;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Operacao;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Parte;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.ParteRepresentante;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Sacado;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Testemunha;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2.CedenteRequisicao;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao2.TituloRequisicao;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.fromtis.fidc.portal.webservices.requisicao package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Requisicao_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "requisicao");
    private final static QName _RetornoAquisicao_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoAquisicao");
    private final static QName _Cedente_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "cedente");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.fromtis.fidc.portal.webservices.requisicao
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Consultoria }
     * 
     */
    public Consultoria createConsultoria() {
        return new Consultoria();
    }

    /**
     * Create an instance of {@link Fundo }
     * 
     */
    public Fundo createFundo() {
        return new Fundo();
    }

    /**
     * Create an instance of {@link CedenteRequisicao }
     * 
     */
    public CedenteRequisicao createCedenteRequisicao() {
        return new CedenteRequisicao();
    }

    /**
     * Create an instance of {@link RequisicaoCertificadoDigital }
     * 
     */
    public RequisicaoCertificadoDigital createRequisicaoCertificadoDigital() {
        return new RequisicaoCertificadoDigital();
    }

    /**
     * Create an instance of {@link Requisicao }
     * 
     */
    public Requisicao createRequisicao() {
        return new Requisicao();
    }

    /**
     * Create an instance of {@link Sacado }
     * 
     */
    public Sacado createSacado() {
        return new Sacado();
    }

    /**
     * Create an instance of {@link ParteRepresentante }
     * 
     */
    public ParteRepresentante createParteRepresentante() {
        return new ParteRepresentante();
    }

    /**
     * Create an instance of {@link Custodiante }
     * 
     */
    public Custodiante createCustodiante() {
        return new Custodiante();
    }

    /**
     * Create an instance of {@link TituloRequisicao }
     * 
     */
    public TituloRequisicao createTituloRequisicao() {
        return new TituloRequisicao();
    }

    /**
     * Create an instance of {@link Operacao }
     * 
     */
    public Operacao createOperacao() {
        return new Operacao();
    }

    /**
     * Create an instance of {@link Avalista }
     * 
     */
    public Avalista createAvalista() {
        return new Avalista();
    }

    /**
     * Create an instance of {@link Parte }
     * 
     */
    public Parte createParte() {
        return new Parte();
    }

    /**
     * Create an instance of {@link Consultoria.Partes }
     * 
     */
    public Consultoria.Partes createConsultoriaPartes() {
        return new Consultoria.Partes();
    }

    /**
     * Create an instance of {@link Fundo.Partes }
     * 
     */
    public Fundo.Partes createFundoPartes() {
        return new Fundo.Partes();
    }

    /**
     * Create an instance of {@link CedenteRequisicao.Partes }
     * 
     */
    public Partes createPartes() {
        return new Partes();
    }

    /**
     * Create an instance of {@link CedenteRequisicao.Avalistas }
     * 
     */
    public CedenteRequisicao.Avalistas createCedenteRequisicaoAvalistas() {
        return new CedenteRequisicao.Avalistas();
    }

    /**
     * Create an instance of {@link CedenteRequisicao.Titulos }
     * 
     */
    public CedenteRequisicao.Titulos createCedenteRequisicaoTitulos() {
        return new CedenteRequisicao.Titulos();
    }
    
    

    /**
     * Create an instance of {@link Fundo.testemnhas }
     * 
     */
    public Fundo.Testemunhas createFundoTestemunhas() {
        return new Fundo.Testemunhas();
    }
    
    
    /**
     * Create an instance of {@link Testemunha }
     * 
     */
    public Testemunha createTestemunha() {
        return new Testemunha();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Requisicao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "requisicao")
    public JAXBElement<Requisicao> createRequisicao(Requisicao value) {
        return new JAXBElement<Requisicao>(_Requisicao_QNAME, Requisicao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequisicaoCertificadoDigital }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "retornoAquisicao")
    public JAXBElement<RequisicaoCertificadoDigital> createRetornoAquisicao(RequisicaoCertificadoDigital value) {
        return new JAXBElement<RequisicaoCertificadoDigital>(_RetornoAquisicao_QNAME, RequisicaoCertificadoDigital.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CedenteRequisicao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "cedente")
    public JAXBElement<CedenteRequisicao> createCedente(CedenteRequisicao value) {
        return new JAXBElement<CedenteRequisicao>(_Cedente_QNAME, CedenteRequisicao.class, null, value);
    }
}
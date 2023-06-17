
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Avalista;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Cedente;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Consultoria;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Custodiante;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Erro;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Fundo;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Operacao;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Parte;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.ParteRepresentante;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Sacado;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Testemunha;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.TituloRetorno;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.fromtis.fidc.portal.webservices package. 
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

    private final static QName _Retorno_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "retorno");
    private final static QName _Testemunha_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "testemunha");
    private final static QName _RetornoResponse_QNAME = new QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.fromtis.fidc.portal.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fundo }
     * 
     */
    public Fundo createFundo() {
        return new Fundo();
    }

    /**
     * Create an instance of {@link Sacado }
     * 
     */
    public Sacado createSacado() {
        return new Sacado();
    }

    /**
     * Create an instance of {@link Consultoria }
     * 
     */
    public Consultoria createConsultoria() {
        return new Consultoria();
    }

    /**
     * Create an instance of {@link Cedente }
     * 
     */
    public Cedente createCedenteRetorno() {
        return new Cedente();
    }

    /**
     * Create an instance of {@link RetornoProcessamento }
     * 
     */
    public RetornoProcessamento createRetornoProcessamento() {
        return new RetornoProcessamento();
    }

    /**
     * Create an instance of {@link Retorno }
     * 
     */
    public Retorno createRetorno() {
        return new Retorno();
    }

    /**
     * Create an instance of {@link RetornoResponse }
     * 
     */
    public RetornoResponse createRetornoResponse() {
        return new RetornoResponse();
    }

    /**
     * Create an instance of {@link Testemunha }
     * 
     */
    public Testemunha createTestemunha() {
        return new Testemunha();
    }

    /**
     * Create an instance of {@link RetornoCertificadoDigital }
     * 
     */
    public RetornoCertificadoDigital createRetornoCertificadoDigital() {
        return new RetornoCertificadoDigital();
    }

    /**
     * Create an instance of {@link Erro }
     * 
     */
    public Erro createErro() {
        return new Erro();
    }

    /**
     * Create an instance of {@link Custodiante }
     * 
     */
    public Custodiante createCustodiante() {
        return new Custodiante();
    }

    /**
     * Create an instance of {@link Parte }
     * 
     */
    public Parte createParte() {
        return new Parte();
    }

    /**
     * Create an instance of {@link Avalista }
     * 
     */
    public Avalista createAvalista() {
        return new Avalista();
    }

    /**
     * Create an instance of {@link ParteRepresentante }
     * 
     */
    public ParteRepresentante createParteRepresentante() {
        return new ParteRepresentante();
    }

    /**
     * Create an instance of {@link TituloRetorno }
     * 
     */
    public TituloRetorno createTituloRetorno() {
        return new TituloRetorno();
    }

    /**
     * Create an instance of {@link Operacao }
     * 
     */
    public Operacao createOperacao() {
        return new Operacao();
    }

    /**
     * Create an instance of {@link Fundo.Partes }
     * 
     */
    public Fundo.Partes createFundoPartes() {
        return new Fundo.Partes();
    }

    /**
     * Create an instance of {@link Fundo.Testemunhas }
     * 
     */
    public Fundo.Testemunhas createFundoTestemunhas() {
        return new Fundo.Testemunhas();
    }

    /**
     * Create an instance of {@link Sacado.Partes }
     * 
     */
    public Sacado.Partes createSacadoPartes() {
        return new Sacado.Partes();
    }

    /**
     * Create an instance of {@link Consultoria.Partes }
     * 
     */
    public Consultoria.Partes createConsultoriaPartes() {
        return new Consultoria.Partes();
    }

    /**
     * Create an instance of {@link Cedente.Partes }
     * 
     */
    public Cedente.Partes createCedenteRetornoPartes() {
        return new Cedente.Partes();
    }

    /**
     * Create an instance of {@link Cedente.Avalistas }
     * 
     */
    public Cedente.Avalistas createCedenteRetornoAvalistas() {
        return new Cedente.Avalistas();
    }

    /**
     * Create an instance of {@link Cedente.Titulos }
     * 
     */
    public Cedente.Titulos createCedenteRetornoTitulos() {
        return new Cedente.Titulos();
    }

    /**
     * Create an instance of {@link Cedente.TitulosRecompra }
     * 
     */
    public Cedente.TitulosRecompra createCedenteRetornoTitulosRecompra() {
        return new Cedente.TitulosRecompra();
    }

    /**
     * Create an instance of {@link RetornoProcessamento.Erros }
     * 
     */
    public RetornoProcessamento.Erros createRetornoProcessamentoErros() {
        return new RetornoProcessamento.Erros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Retorno }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "retorno")
    public JAXBElement<Retorno> createRetorno(Retorno value) {
        return new JAXBElement<Retorno>(_Retorno_QNAME, Retorno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Testemunha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "testemunha")
    public JAXBElement<Testemunha> createTestemunha(Testemunha value) {
        return new JAXBElement<Testemunha>(_Testemunha_QNAME, Testemunha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.portal.fidc.fromtis.com.br/", name = "retornoResponse")
    public JAXBElement<RetornoResponse> createRetornoResponse(RetornoResponse value) {
        return new JAXBElement<RetornoResponse>(_RetornoResponse_QNAME, RetornoResponse.class, null, value);
    }

}

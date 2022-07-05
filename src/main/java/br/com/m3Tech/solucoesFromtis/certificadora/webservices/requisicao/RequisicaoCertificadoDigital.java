
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requisicaoCertificadoDigital", propOrder = {
    "operacao",
    "custodiante",
    "fundo",
    "consultoria",
    "cedente"
})
@XmlRootElement(name="requisicaoCertificado")
public class RequisicaoCertificadoDigital {

    @XmlElement(required = true)
    private Operacao operacao;
    @XmlElement(required = true)
    private Custodiante custodiante;
    @XmlElement(required = true)
    private Fundo fundo;
    @XmlElement(required = true)
    private Consultoria consultoria;
    @XmlElement(required = true)
    private Cedente cedente;
    @XmlAttribute(name = "id", required = true)
    private int id;
    
}
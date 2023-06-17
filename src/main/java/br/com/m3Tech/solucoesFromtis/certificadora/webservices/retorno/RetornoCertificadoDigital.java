
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Cedente;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Consultoria;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Custodiante;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Fundo;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.Operacao;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retornoCertificadoDigital", propOrder = {
    "retornoProcessamento",
    "operacao",
    "custodiante",
    "fundo",
    "consultoria",
    "cedente"
})
@Getter @Setter
@NoArgsConstructor
@XmlRootElement(name = "retornoCertificadoDigital")
public class RetornoCertificadoDigital {

    @XmlElement(required = true)
    private RetornoProcessamento retornoProcessamento;
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

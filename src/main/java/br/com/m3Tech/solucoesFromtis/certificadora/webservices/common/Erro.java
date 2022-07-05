
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "erro", propOrder = {
    "mensagem"
})
public class Erro {

    @XmlElement(required = true)
    protected String mensagem;
    
    @XmlAttribute(name = "codigo", required = true)
    protected String codigo;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String value) {
        this.mensagem = value;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String value) {
        this.codigo = value;
    }
    
    public static Erro novoErroComSucesso() {
    	Erro erro = new Erro();
    	erro.setCodigo("00");
    	erro.setMensagem("Aprovado");
    	return erro; 
    }
    
    
    public static Erro novoErroComErro(final String mensagem) {
    	Erro erro = new Erro();
    	erro.setCodigo("99");
    	erro.setMensagem(mensagem);
    	return erro; 
    }

}

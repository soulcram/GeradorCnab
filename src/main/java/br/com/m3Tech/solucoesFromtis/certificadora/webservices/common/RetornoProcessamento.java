
package br.com.m3Tech.solucoesFromtis.certificadora.webservices.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retornoProcessamento", propOrder = {
    "status",
    "erros"
})
public class RetornoProcessamento {

    @XmlElement(required = true)
    protected String status;
    
    @XmlElement(required = true)
    protected RetornoProcessamento.Erros erros;

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public RetornoProcessamento.Erros getErros() {
        return erros;
    }

    public void setErros(RetornoProcessamento.Erros value) {
        this.erros = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "erro"
    })
    public static class Erros {

        @XmlElement(nillable = true)
        protected List<Erro> erro;

        public List<Erro> getErro() {
            if (erro == null) {
                erro = new ArrayList<Erro>();
            }
            return this.erro;
        }
        
        public static Erros criaErros() {
        	return new Erros();
        }

    }
    
    public String geraMensagemDoErro() {
    	StringBuilder sb = new StringBuilder();
    	for(Erro erro : erros.erro) {
    		sb.append("Erro: ").append(erro.getMensagem()).append(" \n");
    	}
    	return sb.toString();
    }


	public static RetornoProcessamento novoSucesso() {
		RetornoProcessamento retornoProcessamento = new RetornoProcessamento();
		retornoProcessamento.setStatus("00");
		retornoProcessamento.setErros(Erros.criaErros());
		retornoProcessamento.getErros().getErro().add(Erro.novoErroComSucesso());
		return retornoProcessamento;
	}

	public static RetornoProcessamento erroProcessamento(final String mensagem) {
		RetornoProcessamento retornoProcessamento = new RetornoProcessamento();
		retornoProcessamento.setStatus("99");
		retornoProcessamento.setErros(Erros.criaErros());
		retornoProcessamento.getErros().getErro().add(Erro.novoErroComErro(mensagem));
		return retornoProcessamento;
	}

}

package br.com.m3Tech.solucoesFromtis.certificadora.view;


import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.io.Serializable;


public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private void addMessage(final Severity severity, final String tipoMensagem,  final String descricaoMensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, tipoMensagem, descricaoMensagem));
    }
    

    protected void addMessageSucessoPadrao() {
    	addMessage(FacesMessage.SEVERITY_INFO, "Info", "Açao Realizada com Sucesso.");
    }

    protected void addMessageSucesso(final String key) {
        addMessageInfo(key);
    }
    
    protected void addMessageInfo(final String mensagem) {
        addMessage(FacesMessage.SEVERITY_INFO, "Info", mensagem);
    }
    
    public void addMessageErro(final String mensagem) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Erro",  mensagem);
    }
}

package br.com.m3Tech.solucoesFromtis.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

abstract class CrudController {

	void addInfoMsg(String msg) {
		addMsg(FacesMessage.SEVERITY_INFO, msg);
	}

	void addErrorMsg(String msg) {
		addMsg(FacesMessage.SEVERITY_ERROR, msg);
	}

	void addErrorMsg(String msg, Exception exception) {
		addMsgDetail(FacesMessage.SEVERITY_ERROR, msg, exception.getMessage());
	}

	void addWarnMsg(String msg) {
		addMsg(FacesMessage.SEVERITY_WARN, msg);
	}

	void addSuccessMsg() {
		addMsg(FacesMessage.SEVERITY_INFO, "Operação executada com Sucesso!");
	}

	private void addMsg(FacesMessage.Severity severity, String msg) {
		if (msg != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
		}
	}

	private void addMsgDetail(FacesMessage.Severity severity, String msg, String detail) {
		if (msg != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, detail));
		}
	}

}

package br.com.m3Tech.solucoesFromtis.controller

import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext

abstract class CrudControlller {

    fun addInfoMsg(msg: String?) {
        addMsg(FacesMessage.SEVERITY_INFO, msg)
    }

    fun addErrorMsg(msg: String?) {
        addMsg(FacesMessage.SEVERITY_ERROR, msg)
    }

    fun addErrorMsg(msg: String?, exception: java.lang.Exception) {
        addMsgDetail(FacesMessage.SEVERITY_ERROR, msg, exception.message)
    }

    fun addWarnMsg(msg: String?) {
        addMsg(FacesMessage.SEVERITY_WARN, msg)
    }

    fun addSuccessMsg() {
        addMsg(FacesMessage.SEVERITY_INFO, "Operação executada com Sucesso!")
    }

    private fun addMsg(severity: FacesMessage.Severity, msg: String?) {
        msg?.let {
            FacesContext.getCurrentInstance().addMessage(null, FacesMessage(severity, it, null))
        }
    }

    private fun addMsgDetail(severity: FacesMessage.Severity, msg: String?, detail: String?) {
        msg?.let {
            FacesContext.getCurrentInstance().addMessage(null, FacesMessage(severity, it, detail))
        }
    }
}
package br.com.m3Tech.solucoesFromtis.beans;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TemaBean implements Serializable {    


    private static final long serialVersionUID = 1L;

    private String tema = "hot-sneaks";

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

}

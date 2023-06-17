package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FormularioAbaPapeisDesempenhadosEntidadePage {

    private WebDriver navegador;

    public FormularioAbaPapeisDesempenhadosEntidadePage(WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Actions
    public FormularioAbaPapeisDesempenhadosEntidadePage selecionarPapelOriginadorEntidade(){
        navegador.findElement(By.id("form:originador")).click();
        return this;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage submeterFormularioAbaContaCorrenteConsultoriaEntidade(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Conta Corrente Consultoria')]")).click();
        return new FormularioAbaContaCorrenteConsultoriaEntidadePage(navegador);
    }

}

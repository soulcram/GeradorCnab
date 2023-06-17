package br.com.m3Tech.solucoesFromtis.paginas.indexadores.tipoCalculo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoTipoCalculoPage {

    private WebDriver navegador;
    public FormularioDeAdicaoTipoCalculoPage(WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoTipoCalculoPage preencherNomeTipoCalculo (String nomeTipoCalculo){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeTipoCalculo);
        return this;
    }
    public ConsultaTipoCalculoPage submeterFormularioDeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaTipoCalculoPage(navegador);
    }
    public ConsultaTipoCalculoPage submeterFormularioDeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaTipoCalculoPage(navegador);
    }

}

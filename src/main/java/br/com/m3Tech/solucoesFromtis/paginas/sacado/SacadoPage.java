package br.com.m3Tech.solucoesFromtis.paginas.sacado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SacadoPage {
    private WebDriver navegador;

    public SacadoPage (WebDriver navegador) {

        this.navegador = navegador;
    }
    //Mapping e Action
    public ConsultaSacadoPage submeterMenuSacado(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Sacado')]")).click();
        return new ConsultaSacadoPage(navegador);
    }
}

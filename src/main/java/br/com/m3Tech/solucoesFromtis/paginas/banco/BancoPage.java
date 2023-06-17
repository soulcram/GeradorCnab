package br.com.m3Tech.solucoesFromtis.paginas.banco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BancoPage {

    private WebDriver navegador;

    public BancoPage (WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Action
    public ConsultaBancoPage submeterMenuBanco(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Banco')]")).click();
        return new ConsultaBancoPage(navegador);
    }

}

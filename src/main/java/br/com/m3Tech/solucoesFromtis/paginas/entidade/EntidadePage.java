package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class EntidadePage {

    private WebDriver navegador;

    public EntidadePage (WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Actions
    public ConsultaPessoaPage submeterMenuEntidade(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Entidade')]")).click();
        return new ConsultaPessoaPage(navegador);
    }


}

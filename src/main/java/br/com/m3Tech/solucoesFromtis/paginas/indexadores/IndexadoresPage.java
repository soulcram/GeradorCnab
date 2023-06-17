package br.com.m3Tech.solucoesFromtis.paginas.indexadores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.paginas.indexadores.indexador.ConsultaIndexadorPage;
import br.com.m3Tech.solucoesFromtis.paginas.indexadores.taxaIndexador.ConsultaTaxaIndexadorPage;
import br.com.m3Tech.solucoesFromtis.paginas.indexadores.tipoCalculo.ConsultaTipoCalculoPage;

public class IndexadoresPage {

    private WebDriver navegador;

    public IndexadoresPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public IndexadoresPage submeterPrimeiroMenuIndexadores (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Indexadores')]")).click();
        return this;
    }
    public ConsultaTipoCalculoPage submeterMenuTipoCalculo (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Tipo de Cálculo')]")).click();
        return new ConsultaTipoCalculoPage(navegador);
    }
    public ConsultaIndexadorPage submeterMenuIndexador (){
        navegador.findElement(By.id("menuForm:j_idt63")).click();
        //navegador.findElement(By.xpath("//*[contains(text(),'Indexador')]")).click();
        return new ConsultaIndexadorPage(navegador);
    }
    public ConsultaTaxaIndexadorPage submeterMenuTaxaIdexador (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Taxa Indexador')]")).click();
        return new ConsultaTaxaIndexadorPage(navegador);
    }

}

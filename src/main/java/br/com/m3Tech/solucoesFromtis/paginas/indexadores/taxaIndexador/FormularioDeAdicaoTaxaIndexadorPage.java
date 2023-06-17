package br.com.m3Tech.solucoesFromtis.paginas.indexadores.taxaIndexador;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioDeAdicaoTaxaIndexadorPage {

    private WebDriver navegador;

    public FormularioDeAdicaoTaxaIndexadorPage(WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoTaxaIndexadorPage preencherDataTaxaIndexador (String dataTaxaIndexador){
        navegador.findElement(By.id("form:dataInputDate")).sendKeys(dataTaxaIndexador);
        return this;
    }
    public FormularioDeAdicaoTaxaIndexadorPage selecionarTipoTaxaIndexador (String tipoTaxaIndexador){
        WebElement element = navegador.findElement(By.id("form:tipoTaxa"));
        Select tipoTaxa = new Select(element);
        tipoTaxa.selectByVisibleText(tipoTaxaIndexador);
        return this;
    }
    public FormularioDeAdicaoTaxaIndexadorPage preencherValorTaxaIndexador (String valorTaxaIndexador){
        navegador.findElement(By.id("form:valorTaxa")).sendKeys(valorTaxaIndexador);
        return this;
    }
    public FormularioDeAdicaoTaxaIndexadorPage selecionarTipoIndexadorTaxa (String tipoIndexadorTaxa){
        WebElement element = navegador.findElement(By.id("form:tipoIndexador"));
        Select tipoIndexador = new Select(element);
        tipoIndexador.selectByVisibleText(tipoIndexadorTaxa);
        return this;
    }
    public ConsultaTaxaIndexadorPage submeterFormularioDeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaTaxaIndexadorPage(navegador);
    }
    public ConsultaTaxaIndexadorPage submeterFormularioDeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaTaxaIndexadorPage(navegador);
    }

}

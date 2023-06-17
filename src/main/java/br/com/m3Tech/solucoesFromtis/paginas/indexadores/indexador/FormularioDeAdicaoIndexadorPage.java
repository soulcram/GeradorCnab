package br.com.m3Tech.solucoesFromtis.paginas.indexadores.indexador;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormularioDeAdicaoIndexadorPage {

    private WebDriver navegador;
    public FormularioDeAdicaoIndexadorPage(WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoIndexadorPage preencherNomeIndexador (String nomeIndexador){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeIndexador);
        return this;
    }
    public FormularioDeAdicaoIndexadorPage preencherCodigoIndexador (String codigoIndexador){
        navegador.findElement(By.id("form:codigo")).sendKeys(codigoIndexador);
        return this;
    }
    public FormularioDeAdicaoIndexadorPage preencherDefasagemIndexador (String defasagemIndexador){
        navegador.findElement(By.id("form:defasagem")).sendKeys(defasagemIndexador);
        return this;
    }
    public FormularioDeAdicaoIndexadorPage selecionarTipoDeCalculoIndexador (String tipoDeCalculoIndexador){
        WebElement element = navegador.findElement(By.id("form:tipoCalculo"));
        Select tipoCalculo = new Select(element);
        tipoCalculo.selectByVisibleText(tipoDeCalculoIndexador);
        return this;
    }
    public FormularioDeAdicaoIndexadorPage selecionarTipoDeTaxaIndexador (String tipoDeTaxaIndexador){
        WebElement element = navegador.findElement(By.id("form:tipoTaxa"));
        Select tipoTaxa = new Select(element);
        tipoTaxa.selectByVisibleText(tipoDeTaxaIndexador);
        return this;
    }
    public ConsultaIndexadorPage submeterFormularioDeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaIndexadorPage(navegador);
    }
    public ConsultaIndexadorPage submeterFormularioDeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaIndexadorPage(navegador);
    }

}

package br.com.m3Tech.solucoesFromtis.paginas.banco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FormulariodeAdicaoDeBancoPage {

    private WebDriver navegador;

    public FormulariodeAdicaoDeBancoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormulariodeAdicaoDeBancoPage preencherNomeBanco (String nomeBanco){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeBanco);
        return this;
    }
    public FormulariodeAdicaoDeBancoPage preencherDescricaoBanco (String descricaoBanco){
        navegador.findElement(By.id("form:descricao")).sendKeys(descricaoBanco);
        return this;
    }
    public FormulariodeAdicaoDeBancoPage preencherNumeroBanco (String numeroBanco){
        navegador.findElement(By.id("form:numero")).sendKeys(numeroBanco);
        return this;
    }
    public FormulariodeAdicaoDeBancoPage selecionarAtivarBanco (){
        navegador.findElement(By.id("form:registroAtivo:0")).click();
        return this;
    }
    public ConsultaBancoPage submeterFormulariodeAdicaoComSucesso(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaBancoPage(navegador);
    }
    public ConsultaBancoPage submeterFormulariodeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaBancoPage(navegador);
    }

}

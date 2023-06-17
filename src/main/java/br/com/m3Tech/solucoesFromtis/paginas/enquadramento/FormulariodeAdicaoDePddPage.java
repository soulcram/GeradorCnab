package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormulariodeAdicaoDePddPage {

    private WebDriver navegador;

    public FormulariodeAdicaoDePddPage (WebDriver navegador){

        this.navegador=navegador;
    }

                                    //Mapping e Actions
    public  FormulariodeAdicaoDePddPage preencherNomePDD(String nome){
        navegador.findElement(By.id("j_idt210:nomePdd")).sendKeys(nome);
         return this;

        //elemento Master = j_idt210:nomePdd
        //elemento Daycoval = j_idt209:nomePdd
        //elemento BRL = j_idt210:nomePdd
    }

    public FormulariodeAdicaoDePddPage faixaUnica (){
        navegador.findElement(By.id("j_idt210:j_idt229")).click();
        return  this;

        //elemento Master = j_idt210:j_idt229
        //elemento Daycoval = j_idt209:j_idt228
        //elemento BRL = j_idt210:j_idt229
    }

    public ConsultaPddPage submeterFormulariodeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaPddPage(navegador);
    }

    public ConsultaPddPage submeterFormulariodeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaPddPage(navegador);

    }




}

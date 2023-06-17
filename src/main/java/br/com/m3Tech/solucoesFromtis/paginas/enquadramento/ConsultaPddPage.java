package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ConsultaPddPage {

    private WebDriver navegador;

    public ConsultaPddPage(WebDriver navegador){

        this.navegador=navegador;
    }

                                      //Mapping e Actions
    public FormulariodeAdicaoDePddPage acessarFormularianovoPDD (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormulariodeAdicaoDePddPage(navegador);
    }

    public String capturarMensagemApresentadaComSucesso (){

        return navegador.findElement(By.cssSelector("span[class='rf-msgs-sum']")).getText();

    }
    public String capturarMensagemApresentadaComErro (){

        return navegador.findElement(By.cssSelector("span[class='rf-msgs-sum']")).getText();

    }
    public String capturarMensagemApresentadaDadosObrigatorios (){

        return navegador.findElement(By.cssSelector("span[class='rf-msgs-sum']")).getText();
    }

}

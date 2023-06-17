package br.com.m3Tech.solucoesFromtis.paginas.indexadores.tipoCalculo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaTipoCalculoPage {

    private WebDriver navegador;

    public ConsultaTipoCalculoPage (WebDriver navegador){
        this.navegador=navegador;
    }

    public FormularioDeAdicaoTipoCalculoPage submeterFormulariaDeAdicaoTipoCalculo (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoTipoCalculoPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome é obrigatório')]")).getText();
    }

}

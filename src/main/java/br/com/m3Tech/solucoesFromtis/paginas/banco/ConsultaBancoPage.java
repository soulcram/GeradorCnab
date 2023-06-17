package br.com.m3Tech.solucoesFromtis.paginas.banco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaBancoPage {

    private WebDriver navegador;

    public ConsultaBancoPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormulariodeAdicaoDeBancoPage submeterFormulariodeAdicaoDeBanco (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormulariodeAdicaoDeBancoPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaComErro (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Número de banco já cadastrado.')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome é obrigatório')]")).getText();
    }
}

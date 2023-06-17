package br.com.m3Tech.solucoesFromtis.paginas.sacado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaSacadoPage {

    private WebDriver navegador;

    public ConsultaSacadoPage (WebDriver navegador) {

        this.navegador = navegador;
    }
    public FormularioDeAdicaoSacadoPage submeterFormularioDeAdicaoSacado (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoSacadoPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaComErro(String docSacado){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe Sacado cadastrado com o Cnpj/Cpf: " + docSacado + "')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Fundo é obrigatório')]")).getText();
    }
}

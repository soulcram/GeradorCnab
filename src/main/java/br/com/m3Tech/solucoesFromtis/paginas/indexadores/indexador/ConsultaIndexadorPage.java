package br.com.m3Tech.solucoesFromtis.paginas.indexadores.indexador;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaIndexadorPage {
    private WebDriver navegador;
    public ConsultaIndexadorPage(WebDriver navegador) {

        this.navegador = navegador;
    }
    public FormularioDeAdicaoIndexadorPage submeterFormulariaDeAdicaoIndexador(){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoIndexadorPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaComErro(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe Indexador com este Código')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome é obrigatório')]")).getText();
    }

}

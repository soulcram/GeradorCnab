package br.com.m3Tech.solucoesFromtis.paginas.indexadores.taxaIndexador;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaTaxaIndexadorPage {

    private WebDriver navegador;

    public ConsultaTaxaIndexadorPage (WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioDeAdicaoTaxaIndexadorPage submeterFormularioDeAdicaoTaxaIndexador (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoTaxaIndexadorPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Taxa replicado para o mês inteiro com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Data é obrigatório')]")).getText();
    }
}

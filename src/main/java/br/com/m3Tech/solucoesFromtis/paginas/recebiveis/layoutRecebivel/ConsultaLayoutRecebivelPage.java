package br.com.m3Tech.solucoesFromtis.paginas.recebiveis.layoutRecebivel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaLayoutRecebivelPage {
    private WebDriver navegador;

    public ConsultaLayoutRecebivelPage (WebDriver navegador){
        this.navegador=navegador;
    }

    // Mapping e Action
    public FormularioDeAdicaoLayoutRecebivelPage submeterFormularioDeAdicaoLayoutRecebivel (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoLayoutRecebivelPage(navegador);
    }

    //Mensagens apresentadas em tela
    public String capturarMensagemApresentadaComSucesso(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String capturarMensagemApresentadaComErro(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Código de Ocorrência já cadastrado para o layout')]")).getText();
    }
    public String capturarMensagemApresentadaDadosObrigatorios(){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Layout é obrigatório')]")).getText();
    }


}

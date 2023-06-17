package br.com.m3Tech.solucoesFromtis.paginas.cedente;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaCedentePage {

    private WebDriver navegador;


    public ConsultaCedentePage(WebDriver navegador) {
        this.navegador=navegador;
    }

    public FormulariodeAdicaoDeCedentePage submeterFormularioDeAdicaoCedente(){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormulariodeAdicaoDeCedentePage(navegador);
    }
    public String capturarMensagemComSucesso (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }

    public String capturarMensagemApresentadaComErro (String docCedente, String nomeFundo){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe Cedente com o CNPJ: " + docCedente + " para o fundo: "+nomeFundo+"')]")).getText();
    }

    public String capturarMensagemApresentadaDadosObrigatorios (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Fundo é obrigatório')]")).getText();
    }
}


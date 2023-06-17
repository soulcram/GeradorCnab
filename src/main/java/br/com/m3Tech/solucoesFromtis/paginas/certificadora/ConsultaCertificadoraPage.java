package br.com.m3Tech.solucoesFromtis.paginas.certificadora;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaCertificadoraPage {

    private WebDriver navegador;

    public ConsultaCertificadoraPage (WebDriver navegador){

        this.navegador=navegador;
    }
    public FormularioDeAdicaoDeCertificadoraPage submterFormularioDeAdicaoCertificadora(){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoDeCertificadoraPage(navegador);
    }
    public String capturarMensagemApresentadaComSucesso (){

        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }

    public String capturarMensagemApresentadaComErro (){

        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe uma certificadora com o Iniciais do Arquivo cadastrado: TQE')]")).getText();
    }

    public String capturarMensagemApresentadaDadosObrigatorios (){

        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome é obrigatório')]")).getText();
    }

}

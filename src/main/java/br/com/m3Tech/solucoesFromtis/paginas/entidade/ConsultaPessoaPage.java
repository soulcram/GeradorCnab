package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ConsultaPessoaPage {

    private WebDriver navegador;

    public ConsultaPessoaPage(WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Actions
    public FormularioDeAdicaoDeEntidadePage submterFormularioDeAdicaoEntidade(){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoDeEntidadePage(navegador);
    }

    public String capturarMensagemApresentadaComSucesso (){

       return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }

    public String capturarMensagemApresentadaComErro (){

        return navegador.findElement(By.xpath("//*[contains(text(), 'CPf ou CNPJ já cadastrado')]")).getText();
    }

    public String capturarMensagemApresentadaDadosObrigatorios (){

        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Razão Social é obrigatório')]")).getText();
    }


}

package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaRegraEnquadramentoPage {

    private WebDriver navegador;

    public ConsultaRegraEnquadramentoPage (WebDriver navegador){
        this.navegador = navegador;
    }

                                              //Mapping e Actions
    public FormulariodeAdicaoDeRegraEnquadramentoPage acessarFormularioDeNovaRegraEnquadramento (){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormulariodeAdicaoDeRegraEnquadramentoPage(navegador);
    }

    public String  capturarMensagemApresentadaComSucesso (){
        //return navegador.findElement(By.cssSelector("span[class='rf-msgs-sum']")).getText();
        return navegador.findElement(By.xpath("//*[contains(text(), 'Operação realizada com sucesso')]")).getText();
    }
    public String  capturarMensagemApresentadaComErro (){
        //return navegador.findElement(By.cssSelector("span[class='rf-msgs-sum']")).getText();
        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe Regra com o nome: Sacados Não Permitidos Teste')]")).getText();
    }

    public String capturarMensagemApresentadaDadosObrigatorios (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome da Regra é obrigatório')]")).getText();
    }

}

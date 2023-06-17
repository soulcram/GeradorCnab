package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ConsultaPerfilEnquadramentoPage {

    private WebDriver navegador;

    public ConsultaPerfilEnquadramentoPage (WebDriver navegador){
        this.navegador=navegador;
    }

                                            //Mapping e Actions
    public FormularioDeAdicaoDePerfilEnquadramentoPage submeterAoFormularioDeAdicaoDoPerfil(){
        navegador.findElement(By.linkText("Novo")).click();
        return new FormularioDeAdicaoDePerfilEnquadramentoPage(navegador);
    }

    public String  capturarMensagemApresentada (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Perfil Enquadramento cadastrado com sucesso.')]")).getText();
    }

    public String capturarMensagemApresentadaComErro (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'Já existe Perfil com o nome: Perfil Sacados Não Permitidos Teste')]")).getText();
    }

    public String capturarMensagemApresentadaDadosObrigatorios (){
        return navegador.findElement(By.xpath("//*[contains(text(), 'O campo Nome é obrigatório')]")).getText();
    }

}

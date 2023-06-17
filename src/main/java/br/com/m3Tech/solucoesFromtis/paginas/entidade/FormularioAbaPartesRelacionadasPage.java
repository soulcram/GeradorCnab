package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAbaPartesRelacionadasPage {
    private WebDriver navegador;

    public  FormularioAbaPartesRelacionadasPage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaPartesRelacionadasPage preencherNomePartesRelacionadas(String nomePartesRelacionadas){
        navegador.findElement(By.id("form:nomeParteRelacionada")).sendKeys(nomePartesRelacionadas);
        return this;
    }
    public FormularioAbaPartesRelacionadasPage selecionarTipoPessoaPartesRelacionadas() {
        navegador.findElement(By.id("form:tipoParteRelacionada:1")).click();
        return this;
    }
    public FormularioAbaPartesRelacionadasPage preencherCnpjPartesRelacionadas(String numeroCnpjPartesRelacionadas) {
        navegador.findElement(By.id("form:cnpjParteRelacionada")).sendKeys(numeroCnpjPartesRelacionadas);
        return this;
    }
    public FormularioAbaPartesRelacionadasPage submeterFormularioDePartesRelacionadas() {
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }

    public ConsultaPessoaPage submeterFormulariodeAdicaoComSucesso(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaPessoaPage(navegador);
    }

    public ConsultaPessoaPage submeterFormulariodeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaPessoaPage(navegador);
    }

}

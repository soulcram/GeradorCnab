package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FormularioAbaRepresentantesEntidadePage {

    private WebDriver navegador;

    public FormularioAbaRepresentantesEntidadePage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaRepresentantesEntidadePage preencherNomeRepresentante(String nomeRepresentante){
        navegador.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentante);
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage preencherEmailRepresentante(String emailRepresentante){
        navegador.findElement(By.id("form:emailRepresentante")).sendKeys(emailRepresentante);
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage selecionarTipoPessoaRepresentante(){
        navegador.findElement(By.id("form:representanteTipoPessoa:1")).click();
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage preencherCnpjRepresentante(String cnpjRepresentante){
        navegador.findElement(By.id("form:representanteCnpj")).sendKeys(cnpjRepresentante);
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage preencherNumeroDeTelefonRepresentante(String numeroTelefoneRepresentante){
        navegador.findElement(By.id("form:telefoneRepresentante")).sendKeys(numeroTelefoneRepresentante);
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage submeterFormularioDeRepresentante(){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }
    public FormularioAbaPartesRelacionadasPage submeterFormularioAbaPartesRelacionadasEntidade(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Partes Relacionadas')]")).click();
        return new FormularioAbaPartesRelacionadasPage(navegador);
    }


}

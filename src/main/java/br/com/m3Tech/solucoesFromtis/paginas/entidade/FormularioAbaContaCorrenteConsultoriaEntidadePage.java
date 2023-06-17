package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioAbaContaCorrenteConsultoriaEntidadePage {

    private WebDriver navegador;

    public FormularioAbaContaCorrenteConsultoriaEntidadePage(WebDriver navegador){
        this.navegador=navegador;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage selecionarCodBanco(String codBancoEntidade){
        WebElement element = navegador.findElement(By.id("form:banco"));
        Select comboBanco = new Select(element);
        comboBanco.selectByValue(codBancoEntidade);
        return this;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage preencherAgenciaContaEntidade(String agenciaContaEntidade){
        navegador.findElement(By.id("form:ag")).sendKeys(agenciaContaEntidade);
        return this;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage preencherNumeroContaEntidade(String numeroContaEntidade){
        navegador.findElement(By.id("form:cc")).sendKeys(numeroContaEntidade);
        return this;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage preencherDescricaoContaEntidade(String descricaoContaEntidade){
        navegador.findElement(By.id("form:descricao")).sendKeys(descricaoContaEntidade);
        return this;
    }
    public FormularioAbaContaCorrenteConsultoriaEntidadePage submeterFormularioDeContaCorrente(){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }
    public FormularioAbaRepresentantesEntidadePage submeterFormularioAbaRepresentantesEntidade(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Representantes')]")).click();
        return new FormularioAbaRepresentantesEntidadePage(navegador);
    }





}

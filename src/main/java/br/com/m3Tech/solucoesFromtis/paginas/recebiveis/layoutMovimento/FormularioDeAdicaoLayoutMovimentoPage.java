package br.com.m3Tech.solucoesFromtis.paginas.recebiveis.layoutMovimento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioDeAdicaoLayoutMovimentoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoLayoutMovimentoPage (WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoLayoutMovimentoPage selecionarLayoutMovimento (String layoutMovimento){
        WebElement element = navegador.findElement(By.id("j_idt210:layout"));
        Select layMovimento = new Select(element);
        layMovimento.selectByVisibleText(layoutMovimento);
        return this;
    }
    public FormularioDeAdicaoLayoutMovimentoPage selecionarTipoMovimento (String tipoMovimento){
        WebElement element = navegador.findElement(By.id("j_idt210:movimento"));
        Select tpMovimento = new Select(element);
        tpMovimento.selectByVisibleText(tipoMovimento);
        return this;
    }
    public FormularioDeAdicaoLayoutMovimentoPage preencherCodigoOcorrenciaMovimento (String codigoOcorrenciaMovimento){
        navegador.findElement(By.id("j_idt210:ocorrencia")).sendKeys(codigoOcorrenciaMovimento);
        return this;
    }
    public ConsultaLayoutMovimentoPage submeterFormularioDeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaLayoutMovimentoPage(navegador);
    }
    public ConsultaLayoutMovimentoPage submeterFormularioDeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaLayoutMovimentoPage(navegador);
    }

}

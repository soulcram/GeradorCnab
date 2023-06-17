package br.com.m3Tech.solucoesFromtis.paginas.recebiveis.layoutRecebivel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioDeAdicaoLayoutRecebivelPage {

    private WebDriver navegador;

    public FormularioDeAdicaoLayoutRecebivelPage (WebDriver navegador){
        this.navegador=navegador;
    }

    //Mapping e Action
    public  FormularioDeAdicaoLayoutRecebivelPage selecionarLayoutRebivel( String layoutRebivel){
        WebElement element = navegador.findElement(By.id("j_idt210:layout"));
        Select layoutM = new Select(element);
        layoutM.selectByVisibleText(layoutRebivel);
        return this;
    }
    public  FormularioDeAdicaoLayoutRecebivelPage selecionarTipoRebivel( String tipoRecebivel){
        WebElement element = navegador.findElement(By.id("j_idt210:tipoRecebivel"));
        Select tipoR = new Select(element);
        tipoR.selectByVisibleText(tipoRecebivel);
        return this;
    }
    public  FormularioDeAdicaoLayoutRecebivelPage preencherEspecieRecebivel( String especieRecebivel){
         navegador.findElement(By.id("j_idt210:especie")).sendKeys(especieRecebivel);
         return this;
    }
    public ConsultaLayoutRecebivelPage submeterFormularioDeAdicaoComSucesso (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaLayoutRecebivelPage(navegador);
    }
    public ConsultaLayoutRecebivelPage submeterFormularioDeAdicaoComErro (){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaLayoutRecebivelPage(navegador);
    }


}

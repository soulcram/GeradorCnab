package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class FormularioDeAdicaoDePerfilEnquadramentoPage {

    private WebDriver navegador;

    public FormularioDeAdicaoDePerfilEnquadramentoPage(WebDriver navegador){
        this.navegador=navegador;
    }

                                             //Mapping e Actions
    public FormularioDeAdicaoDePerfilEnquadramentoPage preencherNomeDoPerfil(String nomePerfil){
        navegador.findElement(By.id("j_idt210:nome")).sendKeys(nomePerfil);
        return this;
    }

    public FormularioDeAdicaoDePerfilEnquadramentoPage preencherDescricaoPerfil (String descricaoPerfil){
        navegador.findElement(By.id("j_idt210:descricao")).sendKeys(descricaoPerfil);

        //Elemento Master = j_idt210:descricao
        //Elemento Planner Trustee = j_idt205:descricao
        //Elemento Daycoval = j_idt209:descricao
        //Elemento BRL = j_idt210:descricao

        return this;
    }

    public FormularioDeAdicaoDePerfilEnquadramentoPage selecionarRegrasDeEnquadramento() {
        navegador.findElement(By.cssSelector("[id='j_idt210:regrasSourceItems']"));
        WebElement perfil = navegador.findElement(By.id("j_idt210:regrasItem0"));
        Actions actions = new Actions(navegador);
        actions.doubleClick(perfil).perform();

        //Elemento Master = j_idt210:regrasItem0
        //Elemento Planner Trustee =j_idt205:regrasItem0
        //Elemento Daycoval = j_idt209:regrasItem0
        //Elemento BRL = j_idt210:regrasItem0

        return this;
    }
    public FormularioDeAdicaoDePerfilEnquadramentoPage submeterFormulariodDeAdicaoComSucesso ()
    {
        navegador.findElement(By.linkText("Salvar")).click();
        return this;
    }
    public FormularioDeAdicaoDePerfilEnquadramentoPage submeterFormulariodDeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return this;
    }
    public ConsultaPerfilEnquadramentoPage confirmarAlert (){
        Alert alerta = navegador.switchTo().alert();
        Assertions.assertEquals("Confirma alteração?", alerta.getText());
        alerta.accept();
        return new ConsultaPerfilEnquadramentoPage(navegador);
    }


}

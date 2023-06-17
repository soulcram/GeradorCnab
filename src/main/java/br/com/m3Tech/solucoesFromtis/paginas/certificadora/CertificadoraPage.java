package br.com.m3Tech.solucoesFromtis.paginas.certificadora;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CertificadoraPage {

    private WebDriver navegador;

    public CertificadoraPage (WebDriver navegador) {

        this.navegador=navegador;
    }

    //Mapping e Actions
    public ConsultaCertificadoraPage submeterMenuCertificadora (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Certificadora')]")).click();
        return new ConsultaCertificadoraPage(navegador);
    }

}

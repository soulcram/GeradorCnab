package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class EnquadramentoPage {

    private WebDriver navegador;

    public EnquadramentoPage  (WebDriver navegador) {
        this.navegador = navegador;
    }

                                          //Mapping e Actions
    public ConsultaPddPage submeterMenuPDD (){
        navegador.findElement(By.xpath("//*[contains(text(), 'PDD')]")).click();
        return new ConsultaPddPage(navegador);
    }

    public ConsultaRegraEnquadramentoPage submeterMenuRegraDeEnquadramento(){
        navegador.findElement(By.xpath("//*[contains(text(), 'Regra Enquadramento')]")).click();
        return new ConsultaRegraEnquadramentoPage(navegador);

    }
    public ConsultaPerfilEnquadramentoPage submeterMenuPerfilDeEnquadramento (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Perfil Enquadramento')]")).click();
        return new ConsultaPerfilEnquadramentoPage (navegador);
    }


}

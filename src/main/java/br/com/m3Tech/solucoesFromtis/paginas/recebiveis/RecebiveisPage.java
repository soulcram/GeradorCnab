package br.com.m3Tech.solucoesFromtis.paginas.recebiveis;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.m3Tech.solucoesFromtis.paginas.recebiveis.layoutMovimento.ConsultaLayoutMovimentoPage;
import br.com.m3Tech.solucoesFromtis.paginas.recebiveis.layoutRecebivel.ConsultaLayoutRecebivelPage;

public class RecebiveisPage {

    private WebDriver navegador;

    public RecebiveisPage (WebDriver navegador){
        this.navegador=navegador;
    }

    // Mapping e Action
    public RecebiveisPage submeterPrimeiroMenuRecebiveis (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Recebíveis')]")).click();
        return this;
    }

    public ConsultaLayoutRecebivelPage submeterMenuLayoutRecebivel (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Layout de Recebível')]")).click();
        return new ConsultaLayoutRecebivelPage(navegador);
    }
    public ConsultaLayoutMovimentoPage submeterMenuLayoutMovimento (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Layout de Movimento')]")).click();
        return new ConsultaLayoutMovimentoPage(navegador);
    }

}

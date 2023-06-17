package br.com.m3Tech.solucoesFromtis.paginas.certificadora;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioDeAdicaoDeCertificadoraPage {

    private WebDriver navegador;

    public FormularioDeAdicaoDeCertificadoraPage(WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Actions
    public FormularioDeAdicaoDeCertificadoraPage preencherNomeCertificadora(String nomeCertificadora){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherCnpjCertificadora(String numeroCnpjCertificadora){
        navegador.findElement(By.id("form:cnpj")).sendKeys(numeroCnpjCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherLogradouroCertificadora(String logradouroCertificadora){
        navegador.findElement(By.id("form:logradouro")).sendKeys(logradouroCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherNumeroCertificadora(String numeroCertificadora){
        navegador.findElement(By.id("form:numero")).sendKeys(numeroCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherComplementoCertificadora(String complementoCertificadora){
        navegador.findElement(By.id("form:complemento")).sendKeys(complementoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherCepCertificadora(String cepCertificadora){
        navegador.findElement(By.id("form:cep")).sendKeys(cepCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherBairroCertificadora(String bairroCertificadora){
        navegador.findElement(By.id("form:bairro")).sendKeys(bairroCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherCidadeCertificadora(String cidadeCertificadora){
        navegador.findElement(By.id("form:cidade")).sendKeys(cidadeCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage selecionarEstadoCertificadora(String estadoCertificadora){
        WebElement element = navegador.findElement(By.id("form:estado"));
        Select estadoC = new Select(element);
        estadoC.selectByVisibleText(estadoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherLetrasIniciaisCertificadora(String letrasIniciaisCertificadora){
        navegador.findElement(By.id("form:iniciaisDoArquivo")).sendKeys(letrasIniciaisCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherQnameCertificadora(String qNameCertificadora){
        navegador.findElement(By.id("form:qName")).sendKeys(qNameCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherUrlCertificadora(String urlCertificadora){
        navegador.findElement(By.id("form:url")).sendKeys(urlCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage selecionarTipoCertificadora(String tipoCertificadora){
        WebElement element = navegador.findElement(By.id("form:tipoCertificadora"));
        Select tipoC = new Select(element);
        tipoC.selectByVisibleText(tipoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage selecionarTipoServicoCertificadora(String tipoServicoCertificadora){
        WebElement element = navegador.findElement(By.id("form:tipoServico"));
        Select tipoS = new Select(element);
        tipoS.selectByVisibleText(tipoServicoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherNomeContatoCertificadora(String nomeContatoCertificadora) {
        navegador.findElement(By.id("form:nomeContato")).sendKeys(nomeContatoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage preencherEmailContatoCertificadora(String emailContatoCertificadora) {
        navegador.findElement(By.id("form:emailContato")).sendKeys(emailContatoCertificadora);
        return this;
    }
    public FormularioDeAdicaoDeCertificadoraPage submeterFormularioDeCertificadora() {
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }

    public ConsultaCertificadoraPage submeterFormulariodeAdicaoComSucesso(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaCertificadoraPage(navegador);
    }

    public ConsultaCertificadoraPage submeterFormulariodeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaCertificadoraPage(navegador);
    }


}

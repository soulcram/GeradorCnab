package br.com.m3Tech.solucoesFromtis.paginas.sacado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioDeAdicaoSacadoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoSacadoPage (WebDriver navegador) {

        this.navegador = navegador;
    }

    //Mapping e Action
    public FormularioDeAdicaoSacadoPage selecionarFundoSacado (String fundoSacado){
        WebElement element = navegador.findElement(By.id("form:fundo"));
        Select fundo = new Select(element);
        fundo.selectByVisibleText(fundoSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarTipoPessoaSacado (){
        navegador.findElement(By.id("form:tipoPessoa:0")).click();
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherNomeSacado (String nomeSacado){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherCpfSacado (String numeroCpfSacado){
        navegador.findElement(By.id("form:cpf")).sendKeys(numeroCpfSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherDataInicioRelacionamentoSacado (String dataInicioRelacionamentoSacado){
        navegador.findElement(By.id("form:dtIniRelacInputDate")).sendKeys(dataInicioRelacionamentoSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarPorteSacado (String porteSacado){
        WebElement element = navegador.findElement(By.id("form:porte"));
        Select porte = new Select(element);
        porte.selectByVisibleText(porteSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarClassificacaoDeRiscoSacado (String classificacaoDeRiscoSacado){
        WebElement element = navegador.findElement(By.id("form:classRisco"));
        Select classRisco = new Select(element);
        classRisco.selectByVisibleText(classificacaoDeRiscoSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarTipoSociedadeSacado (String tipoSociedadeSacado){
        WebElement element = navegador.findElement(By.id("form:tipoSociedade"));
        Select tipoSociedade = new Select(element);
        tipoSociedade.selectByVisibleText(tipoSociedadeSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarTipoIncricaoEstadualSacado (){
        navegador.findElement(By.id("form:tipoInscricaoEstadualIsento:1")).click();
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherLogradouroSacado (String logradouroSacado){
        navegador.findElement(By.id("form:logradouro")).sendKeys(logradouroSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherNumeroLogradouroSacado (String NumerologradouroSacado){
        navegador.findElement(By.id("form:numero")).sendKeys(NumerologradouroSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherCepSacado (String cepSacado){
        navegador.findElement(By.id("form:cep")).sendKeys(cepSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherBairroSacado (String bairroSacado){
        navegador.findElement(By.id("form:bairro")).sendKeys(bairroSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherCidadeSacado (String cidadeSacado){
        navegador.findElement(By.id("form:cidade")).sendKeys(cidadeSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarEstadoSacado (String estadoSacado){
        WebElement element = navegador.findElement(By.id("form:uf"));
        Select ufSacado = new Select(element);
        ufSacado.selectByVisibleText(estadoSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherEmailSacado (String emailSacado){
        navegador.findElement(By.id("form:email")).sendKeys(emailSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarAssinaturaSacado (){
        navegador.findElement(By.id("form:quemAssinaCessao:0")).click();
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarAssinaSozinhoSacado (){
        navegador.findElement(By.id("form:assinaSacado:0")).click();;
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarAssinaTermoCessaoSacado (){
        navegador.findElement(By.id("form:assinaTermoCessaoSacado:0")).click();;
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarAssinaPorEndossoSacado (){
        navegador.findElement(By.id("form:assinaPorEndossoSacado:0")).click();;
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarEmiteDuplicataSacado (){
        navegador.findElement(By.id("form:emiteDuplicataSacado:0")).click();;
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherNomeRepresentanteSacado (String nomeRepresentanteSacado){
        navegador.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentanteSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherEmailRepresentanteSacado (String emailRepresentanteSacado){
        navegador.findElement(By.id("form:emailRepresentante")).sendKeys(emailRepresentanteSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage selecionarTipoPessoaRepresentanteSacado (){
        navegador.findElement(By.id("form:representanteTipoPessoa:1")).click();
        return this;
    }
    public FormularioDeAdicaoSacadoPage preencherCnpjRepresentanteSacado (String cnpjRepresentanteSacado){
        navegador.findElement(By.id("form:representanteCnpj")).sendKeys(cnpjRepresentanteSacado);
        return this;
    }
    public FormularioDeAdicaoSacadoPage adicionarRepresentanteSacado (){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }
    public ConsultaSacadoPage submeterFormularioDeAdicaoComSucesso(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaSacadoPage(navegador);
    }
    public ConsultaSacadoPage submeterFormularioDeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaSacadoPage(navegador);
    }

}

package br.com.m3Tech.solucoesFromtis.paginas.fundo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class FormularioAbaDadosAdicionaisFundoPage {

    private WebDriver navegador;

    public FormularioAbaDadosAdicionaisFundoPage (WebDriver navegador){

        this.navegador=navegador;
    }

    public FormularioAbaDadosAdicionaisFundoPage preencherNomeFundo (String nomeFundo){
        navegador.findElement(By.id("form:nomeFundo")).sendKeys(nomeFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage preencherCnpjFundo (String numeroCnpjFundo){
        navegador.findElement(By.id("form:cnpjFundo")).sendKeys(numeroCnpjFundo);
        return this;
    }
    
    public FormularioAbaDadosAdicionaisFundoPage preencherValorRoboAssinatura (String valor){
        navegador.findElement(By.id("form:valorMaximoRoboAssinatura")).sendKeys(valor);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionaragenciaClassRisco (){
        WebElement element = navegador.findElement(By.id("form:agenciaRating"));
        element.click();
        Select agenciaClassRisco = new Select(element);
        agenciaClassRisco.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeAdministrador (){
        WebElement element = navegador.findElement(By.id("form:nomeAdministrador"));
        element.click();
        Select nomeAdministrador = new Select(element);
        nomeAdministrador.selectByIndex(1);
        return this;

        //elemento Daycoval= form:j_idt351
        //elemento BRL= form:j_idt352
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarAgenteEscriturador () {
        WebElement element = navegador.findElement(By.id("form:agenteEscriturador"));
        element.click();
        Select agenteEscriturador = new Select(element);
        agenteEscriturador.selectByIndex(1);
        return this;

        //elemento Daycoval= form:j_idt351
        //elemento BRL= form:j_idt355
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeCustodiante () {
        WebElement element = navegador.findElement(By.id("form:nomeCustodiante"));
        element.click();
        Select nomeCustodiante = new Select(element);
        nomeCustodiante.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarControladorAtivo () {
        WebElement element = navegador.findElement(By.id("form:nomeControladorAtivo"));
        element.click();
        Select nomeControladorAtivo = new Select(element);
        nomeControladorAtivo.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeGestor () {
        WebElement element = navegador.findElement(By.id("form:nomeGestor"));
        element.click();
        Select nomeGestor = new Select(element);
        nomeGestor.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeControladorPassivo () {
        WebElement element = navegador.findElement(By.id("form:nomeControladorPassivo"));
        element.click();
        Select nomeControladorPassivo = new Select(element);
        nomeControladorPassivo.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeAuditor () {
        WebElement element = navegador.findElement(By.id("form:nomeAuditor"));
        element.click();
        Select nomeAuditor = new Select(element);
        nomeAuditor.selectByIndex(1);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage preencherPrazoRecepcaoChaveNfe (String prazoRecepcaoChaveNfeFundo){
        navegador.findElement(By.id("form:prazoRecepcaoChaveNfe")).sendKeys(prazoRecepcaoChaveNfeFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage preencherPrazoRecepcaoLastro (String prazoRecepcaoLastroFundo){
        navegador.findElement(By.id("form:prazoRecepcaoLastro")).sendKeys(prazoRecepcaoLastroFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionaNaoConsiderarPosFixado (){
        navegador.findElement(By.id("form:considerarPosFixado:1")).click();
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionaAutorizaFechamentoAutomatico (){
        navegador.findElement(By.id("form:autorizaFechamentoFundoAutomatico")).click();
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionaOperaComPLZerado (){
        navegador.findElement(By.id("form:operaPlZerado:0")).click();
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage selecionarNomeBancoRetorno (String nomeBancoRetornoFundo) {
        WebElement element = navegador.findElement(By.id("form:nomeBanco"));
        Select nomeBancoCobranca = new Select(element);
        nomeBancoCobranca.selectByVisibleText(nomeBancoRetornoFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage preencherCodigoOriginador (String codigoCobrancaFundo) {
        navegador.findElement(By.id("form:codigoCobranca")).sendKeys(codigoCobrancaFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage preencherDataInicioRelacionamentoCobranca (String dataInicioRelacionamentoCobrancaFundo) {
        navegador.findElement(By.id("form:dataInicioRelacionamentoCobrancaInputDate")).sendKeys(dataInicioRelacionamentoCobrancaFundo);
        return this;
    }
    public FormularioAbaDadosAdicionaisFundoPage adicionarCobranca (){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }
    public FormularioAbaRegraEnquadramentoFundoPage submeterFormularioAbaRegraEnquadramentoFundo (){

        navegador.findElement(By.xpath("//*[contains(text(), 'Regras Enquadramento')]")).click();
        return new FormularioAbaRegraEnquadramentoFundoPage(navegador);
    }

}


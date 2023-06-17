package br.com.m3Tech.solucoesFromtis.paginas.cedente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormulariodeAdicaoDeCedentePage {

    private WebDriver navegador;

    public FormulariodeAdicaoDeCedentePage(WebDriver navegador) {

        this.navegador=navegador;
    }

    //Mapping e Action
    public FormulariodeAdicaoDeCedentePage selecionarFundo (String fundoCedente){
        WebElement element = navegador.findElement(By.id("form:fundo"));
        Select fundo = new Select(element);
        fundo.selectByVisibleText(fundoCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherNomeCedente (String nomeCedente){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarTipoPessoaCedente (){
        navegador.findElement(By.id("form:tipoPessoa:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherNumeroCnpjCedente (String numeroCnpjCedente){
        navegador.findElement(By.id("form:cpfCnpj")).sendKeys(numeroCnpjCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarTipoInscricaoEstadualCedente (){
        navegador.findElement(By.id("form:tipoInscricaoEstadualIsento:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarRamoDeAtividade (String ramoDeAtividadeCedente){
        WebElement element = navegador.findElement(By.id("form:ramoAtiviade"));
        Select ramoAtividade = new Select(element);
        ramoAtividade.selectByVisibleText(ramoDeAtividadeCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherEmailCedente (String emailCedente){
        navegador.findElement(By.id("form:email")).sendKeys(emailCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarPorteMedio (String porteMedioCedente){
        WebElement element = navegador.findElement(By.id("form:porte"));
        Select porte = new Select(element);
        porte.selectByVisibleText(porteMedioCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarTipoSociedadeInstituicaoFinanceira (String tipoSociedadeInstituicaoFinanceiraCedente){
        WebElement element = navegador.findElement(By.id("form:tipoSociedade"));
        Select tipoSociedade = new Select(element);
        tipoSociedade.selectByVisibleText(tipoSociedadeInstituicaoFinanceiraCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarClasseDeRisco (String classeDeRiscoCedente){
        WebElement element = navegador.findElement(By.id("form:classRisco"));
        Select classRisco = new Select(element);
        classRisco.selectByVisibleText(classeDeRiscoCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherInicioDeRelacionamentoCedente (String inicioDeRelacionamentoCedente){
        navegador.findElement(By.id("form:iniRelacInputDate")).sendKeys(inicioDeRelacionamentoCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarAutorizacaoCedente (){
        navegador.findElement(By.id("form:autorizacao:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherLogradouroCedente (String logradouroCedente){
        navegador.findElement(By.id("form:logradouro")).sendKeys(logradouroCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherNumeroLogradouroCedente (String numeroLogradouroCedente){
        navegador.findElement(By.id("form:numero")).sendKeys(numeroLogradouroCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherComplementoCedente (String complementoCedente){
        navegador.findElement(By.id("form:complemento")).sendKeys(complementoCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherCepCedente (String cepCedente){
        navegador.findElement(By.id("form:cep")).sendKeys(cepCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherBairroCedente (String bairroCedente){
        navegador.findElement(By.id("form:bairro")).sendKeys(bairroCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherCidadeCedente (String cidadeCedente){
        navegador.findElement(By.id("form:cidade")).sendKeys(cidadeCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarEstadoCedente (String estadoCedente){
        WebElement element = navegador.findElement(By.id("form:estado"));
        Select uf = new Select(element);
        uf.selectByVisibleText(estadoCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarCodigoBancoContaCedente (String codigoBancoContaCedente){
        WebElement element = navegador.findElement(By.id("form:banco"));
        Select codBanco = new Select(element);
        codBanco.selectByVisibleText(codigoBancoContaCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherNumeroAgenciaCedente (String numeroAgenciaCedente){
        navegador.findElement(By.id("form:agencia")).sendKeys(numeroAgenciaCedente);
        return this;
    }

    public FormulariodeAdicaoDeCedentePage preencherDigitoAgenciaCedente (String digitoAgenciaCedente){
        navegador.findElement(By.id("form:digitoAgencia")).sendKeys(digitoAgenciaCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherNumeroContaCedente (String numeroContaCedente){
        navegador.findElement(By.id("form:conta")).sendKeys(numeroContaCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherDigitoContaCedente (String digitoContaCedente){
        navegador.findElement(By.id("form:digitoConta")).sendKeys(digitoContaCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherDescricaoContaCedente (String descricaoContaCedente){
        navegador.findElement(By.id("form:descricao")).sendKeys(descricaoContaCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarAtivarContaCedente (){
        navegador.findElement(By.id("form:ativado:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarContaPadraoCedente (){
        navegador.findElement(By.id("form:padrao:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage adicionarContaCedente (){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }

    public FormulariodeAdicaoDeCedentePage preencherNomeRepresentanteCedente (String nomeRepresentanteCedente){
        navegador.findElement(By.id("form:representanteNome")).sendKeys(nomeRepresentanteCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherEmailRepresentanteCedente (String emailRepresentanteCedente){
        navegador.findElement(By.id("form:emailRepresentante")).sendKeys(emailRepresentanteCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage selecionarTipoPessoaRepresentanteCedente (){
        navegador.findElement(By.id("form:representanteTipoPessoa:1")).click();
        return this;
    }
    public FormulariodeAdicaoDeCedentePage preencherCnpjRepresentanteCedente (String cnpjRepresentanteCedente){
        navegador.findElement(By.id("form:representanteCnpj")).sendKeys(cnpjRepresentanteCedente);
        return this;
    }
    public FormulariodeAdicaoDeCedentePage adicionarRepresentanteCedente (){
        navegador.findElement(By.id("form:representanteButtons_body")).findElement(By.linkText("Adicionar")).click();
        return this;
    }
    public ConsultaCedentePage submeterFormularioDeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaCedentePage(navegador);
    }

    public ConsultaCedentePage submeterFormulariodeAdicaoComSucesso () {
        navegador.findElement(By.linkText("Salvar")).click();

        return new ConsultaCedentePage(navegador);
   }

}



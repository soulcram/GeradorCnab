package br.com.m3Tech.solucoesFromtis.paginas.entidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class FormularioDeAdicaoDeEntidadePage {

    private WebDriver navegador;

    public FormularioDeAdicaoDeEntidadePage(WebDriver navegador){

        this.navegador=navegador;
    }

    //Mapping e Action - Não Preencher Dados Obrigatórios
    public ConsultaPessoaPage submeterFormulariodeAdicaoComErro(){
        navegador.findElement(By.linkText("Salvar")).click();
        return new ConsultaPessoaPage(navegador);
    }


    //Mapping e Action - Para preencher todo formulário
    public FormularioDeAdicaoDeEntidadePage preencherNomeEntidade(String nomeEntidade){
        navegador.findElement(By.id("form:nome")).sendKeys(nomeEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage selecionaTipoDePessoaEntidade(){
        navegador.findElement(By.id("form:tipoPessoa:1")).click();
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherCnpjEntidade (String cnpjEntidade){
        navegador.findElement(By.id("form:cnpj")).sendKeys(cnpjEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherEmailEntidade (String emailEntidade){
        navegador.findElement(By.id("form:email")).sendKeys(emailEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherLogradouroEntidade (String logradouroEntidade){
        navegador.findElement(By.id("form:logradouro")).sendKeys(logradouroEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherNumeroEntidade (String numeroEntidade){
        navegador.findElement(By.id("form:numero")).sendKeys(numeroEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherComplementoEntidade (String complementoEntidade){
        navegador.findElement(By.id("form:complemento")).sendKeys(complementoEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherCepEntidade (String cepEntidade){
        navegador.findElement(By.id("form:cep")).sendKeys(cepEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherBairroEntidade (String bairroEntidade){
        navegador.findElement(By.id("form:bairro")).sendKeys(bairroEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage preencherCidadeEntidade (String cidadeEntidade){
        navegador.findElement(By.id("form:cidade")).sendKeys(cidadeEntidade);
        return this;
    }
    public FormularioDeAdicaoDeEntidadePage selecionarEstadoDaEntidade (String estadoEntidade){
        WebElement element = navegador.findElement(By.id("form:estado"));
        Select comboEstado = new Select(element);
        comboEstado.selectByVisibleText(estadoEntidade);
        return this;
    }
    public FormularioAbaPapeisDesempenhadosEntidadePage submeterFormularioPapeisDesempenhadosEntidade (){
        navegador.findElement(By.xpath("//*[contains(text(), 'Papéis Desempenhados')]")).click();
        return new FormularioAbaPapeisDesempenhadosEntidadePage(navegador);
    }


}

package br.com.m3Tech.solucoesFromtis.paginas.enquadramento;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class FormulariodeAdicaoDeRegraEnquadramentoPage {

    private WebDriver navegador;

    public FormulariodeAdicaoDeRegraEnquadramentoPage (WebDriver navegador) {
        this.navegador=navegador;
    }

                                           //Mapping e Actions
    public FormulariodeAdicaoDeRegraEnquadramentoPage acessarRegraAplicadasPorSacado (){
        navegador.findElement(By.id("cadastro:j_idt266:header")).click();
        return this;
    }

    public FormulariodeAdicaoDeRegraEnquadramentoPage acessarSacadosNaoPermitidos(){
        navegador.findElement(By.linkText("Sacados não permitidos")).click();
        return this;
    }
    public FormulariodeAdicaoDeRegraEnquadramentoPage preencherNomeDaRegra (String nomeRegra){
        navegador.findElement(By.id("cadastro:nome")).sendKeys(nomeRegra);
        Assertions.assertEquals("Sacados Não Permitidos QA", nomeRegra);
        return this;
    }
    public FormulariodeAdicaoDeRegraEnquadramentoPage preencherDescricaoDaRegra(String descricaoRegra){
        navegador.findElement(By.id("cadastro:descricao")).sendKeys(descricaoRegra);
//        Assert.assertEquals("Sacados Não Permitidos QA", descricaoRegra);
        return this;
    }
    public FormulariodeAdicaoDeRegraEnquadramentoPage selecionarTipoDePessoaJuridica (){
        WebElement tipoPessoa = navegador.findElement(By.id("cadastro:tipoPessoa"));
        Select combo = new Select(tipoPessoa);
        combo.selectByValue("JURIDICA");

        java.util.List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assertions.assertEquals(1, allSelectedOptions.size());
        return this;
    }
    public FormulariodeAdicaoDeRegraEnquadramentoPage preencherCnpjDaRegra (String cnpj){
        navegador.findElement(By.id("cadastro:cpfOrCnpj")).sendKeys(cnpj);
        return this;
    }
    public FormulariodeAdicaoDeRegraEnquadramentoPage adicionarRegra (){
        navegador.findElement(By.linkText("Adicionar")).click();
        return this;
    }

    public ConsultaRegraEnquadramentoPage submeterFormulariodeAdicaoComSucesso ()
    {
        navegador.findElement(By.id("cadastro:btnGravar")).click();
        return new ConsultaRegraEnquadramentoPage(navegador);
    }
    public ConsultaRegraEnquadramentoPage submeterFormulariodeAdicaoComErro ()
    {
        navegador.findElement(By.id("cadastro:btnGravar")).click();
        return new ConsultaRegraEnquadramentoPage(navegador);
    }



}

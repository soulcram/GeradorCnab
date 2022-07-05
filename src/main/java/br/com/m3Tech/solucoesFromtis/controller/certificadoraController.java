package br.com.m3Tech.solucoesFromtis.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
import br.com.m3Tech.solucoesFromtis.certificadora.utils.JaxbUtils;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class certificadoraController {
    private static final String OUT = "certificadora";

    private GuardadorRequisicoesCertificadora guardadorRequisicao;

    private List<RequisicaoCertificadoraDigitalWrapper> requisicoes;

    public String init() {
    	guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
        atualizaRequisicoes();
        return OUT;
    }

    public void removerTudo() {
    	if(guardadorRequisicao == null) {
    		guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
    	}
        this.guardadorRequisicao.removerTudo();
        atualizaRequisicoes();
    }

    public void atualizaRequisicoes() {
    	
    	if(guardadorRequisicao == null) {
    		guardadorRequisicao = new GuardadorRequisicaoCertificadoraImpl();
    	}
    	
        requisicoes = Lists.newArrayList(guardadorRequisicao.pegaRequisicoes());
    }

    public void removeAquisicao(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
        guardadorRequisicao.removeRequisica(requisicaoWrapper);
        atualizaRequisicoes();
    }

    public void download(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
        final String xml = JaxbUtils.jaxbObjectToXML(requisicaoWrapper.getRequisicao());
        try {
            ControllerUtils.downloadAsAttachment(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"))
                    + "_" + requisicaoWrapper.getRequisicao().getId()
                    + ".xml", new ByteArrayInputStream(xml.getBytes())
            );
        } catch (Exception e) {
            e.printStackTrace();
            ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
        }
    }

}

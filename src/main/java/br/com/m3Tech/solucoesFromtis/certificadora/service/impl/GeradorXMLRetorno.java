package br.com.m3Tech.solucoesFromtis.certificadora.service.impl;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.BeanUtils;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;

public class GeradorXMLRetorno {

    public String gerar(RequisicaoCertificadoDigital requisicaoCertificadoDigital) {
        RetornoCertificadoDigital retornoCertificadoDigital = new RetornoCertificadoDigital();
        BeanUtils.copyProperties(requisicaoCertificadoDigital, retornoCertificadoDigital);
        requisicaoCertificadoDigital.getCedente().criaCamposFaltando();
        retornoCertificadoDigital.setRetornoProcessamento(RetornoProcessamento.novoSucesso());

        try {
            final StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(retornoCertificadoDigital.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(retornoCertificadoDigital, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}

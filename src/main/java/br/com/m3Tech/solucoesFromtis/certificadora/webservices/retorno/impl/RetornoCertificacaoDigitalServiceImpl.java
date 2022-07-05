package br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.common.RetornoProcessamento;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificadoDigital;

@WebService(name = "RetornoCertificacaoDigital", targetNamespace = "http://webservices.portal.fidc.fromtis.com.br/", 
												serviceName = "RetornoCertificacaoDigital", portName = "RetornoCertificacaoDigitalPort")
public class RetornoCertificacaoDigitalServiceImpl implements RetornoCertificacaoDigital {

	@Override
	@WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retorno", targetNamespace = "http://webservices.portal.fidc.fromtis.com.br/", className = "br.com.fromtis.fidc.portal.webservices.Retorno")
    @ResponseWrapper(localName = "retornoResponse", targetNamespace = "http://webservices.portal.fidc.fromtis.com.br/", className = "br.com.fromtis.fidc.portal.webservices.RetornoResponse")
    @Action(input = "http://webservices.portal.fidc.fromtis.com.br/RetornoCertificacaoDigital/retornoRequest", output = "http://webservices.portal.fidc.fromtis.com.br/RetornoCertificacaoDigital/retornoResponse")
	public RetornoProcessamento retorno(@WebParam(name = "retornoAquisicao", targetNamespace = "")RetornoCertificadoDigital retornoAquisicao) {
		return RetornoProcessamento.novoSucesso();
	}
	
}

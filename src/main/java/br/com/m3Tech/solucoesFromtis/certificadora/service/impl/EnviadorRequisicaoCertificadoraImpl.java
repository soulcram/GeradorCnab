package br.com.m3Tech.solucoesFromtis.certificadora.service.impl;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.certificadora.service.EnviadorRequisicaoCertificadora;

@Service("enviadorRequisicao")
public class EnviadorRequisicaoCertificadoraImpl implements EnviadorRequisicaoCertificadora {
	
	public void enviarRequisicao(String conteudoXmlRequisicao) {
		
//		new GeradorServiceWS().criaRequestRequisicaoCertificadora().requisicao(retornoAquisicao);;
	}
}

package br.com.m3Tech.solucoesFromtis.certificadora.service;

import java.util.Set;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;

public interface GuardadorRequisicoesCertificadora {

	void guardaRequisicao(RequisicaoCertificadoraDigitalWrapper requisicao);

	Set<RequisicaoCertificadoraDigitalWrapper> pegaRequisicoes();

	void removeRequisica(RequisicaoCertificadoraDigitalWrapper requisicao);
	
	void atualizaRequisicao(RequisicaoCertificadoraDigitalWrapper requisicao, StatusRequisicao statusRequisicao);

	void removerTudo();
}

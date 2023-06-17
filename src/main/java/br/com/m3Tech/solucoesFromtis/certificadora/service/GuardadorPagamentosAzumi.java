package br.com.m3Tech.solucoesFromtis.certificadora.service;

import java.util.Set;

import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;

public interface GuardadorPagamentosAzumi {

	void guardaOperacao(OperacaoAzumi operacao);

	Set<OperacaoAzumi> pegaOperacoes();

	void removeOperacao(OperacaoAzumi operacao);
	
	void atualizaOperacao(OperacaoAzumi operacao);

	void removerTudo();
}

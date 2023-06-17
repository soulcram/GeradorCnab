package br.com.m3Tech.solucoesFromtis.certificadora.service;

import java.util.Set;

import br.com.m3Tech.solucoesFromtis.dto.OperacaoGenial;

public interface GuardadorPagamentosGenial {

	void guardaOperacao(OperacaoGenial operacao);

	Set<OperacaoGenial> pegaOperacoes();

	void removeOperacao(OperacaoGenial operacao);
	
	void atualizaOperacao(OperacaoGenial operacao);

	void removerTudo();
}

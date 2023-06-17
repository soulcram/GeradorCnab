package br.com.m3Tech.solucoesFromtis.certificadora.service;

import java.util.Set;

import br.com.m3Tech.solucoesFromtis.dto.Operacao;

public interface GuardadorPagamentosSinacor {

	void guardaOperacao(Operacao operacao);

	Set<Operacao> pegaOperacoes();

	void removeOperacao(Operacao operacao);
	
	void atualizaOperacao(Operacao operacao);

	void removerTudo();
}

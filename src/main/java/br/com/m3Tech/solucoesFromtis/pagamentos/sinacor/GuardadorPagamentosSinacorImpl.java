package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosSinacor;
import br.com.m3Tech.solucoesFromtis.dto.Operacao;

@Service
public class GuardadorPagamentosSinacorImpl implements GuardadorPagamentosSinacor {

	private static Set<Operacao> operacoes = Sets.newHashSet();
	
	public GuardadorPagamentosSinacorImpl() {

	}
	
	@Override
	public void guardaOperacao(Operacao operacao) {
		operacoes.add(operacao);
	}

	@Override
	public Set<Operacao> pegaOperacoes() {
		return operacoes;
	}
	
	@Override
	public void removeOperacao(Operacao operacao) {
		operacoes.remove(operacao);
	}

	@Override
	public void atualizaOperacao(Operacao operacao) {
		operacoes.remove(operacao);
		guardaOperacao(operacao);
	}

	@Override
	public void removerTudo() {
		GuardadorPagamentosSinacorImpl.operacoes = Sets.newHashSet();
	}

}

package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosGenial;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoGenial;

@Service
public class GuardadorPagamentosGenialImpl implements GuardadorPagamentosGenial {

	private static Set<OperacaoGenial> operacoes = Sets.newHashSet();
	
	public GuardadorPagamentosGenialImpl() {

	}
	
	@Override
	public void guardaOperacao(OperacaoGenial operacao) {
		operacoes.add(operacao);
	}

	@Override
	public Set<OperacaoGenial> pegaOperacoes() {
		return operacoes;
	}
	
	@Override
	public void removeOperacao(OperacaoGenial operacao) {
		operacoes.remove(operacao);
	}

	@Override
	public void atualizaOperacao(OperacaoGenial operacao) {
		operacoes.remove(operacao);
		guardaOperacao(operacao);
	}

	@Override
	public void removerTudo() {
		GuardadorPagamentosGenialImpl.operacoes = Sets.newHashSet();
	}

}

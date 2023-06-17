package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosAzumi;
import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;

@Service
public class GuardadorPagamentosAzumiImpl implements GuardadorPagamentosAzumi {

	private static Set<OperacaoAzumi> operacoes = Sets.newHashSet();
	
	public GuardadorPagamentosAzumiImpl() {

	}
	
	@Override
	public void guardaOperacao(OperacaoAzumi operacao) {
		operacoes.add(operacao);
	}

	@Override
	public Set<OperacaoAzumi> pegaOperacoes() {
		return operacoes;
	}
	
	@Override
	public void removeOperacao(OperacaoAzumi operacao) {
		operacoes.remove(operacao);
	}

	@Override
	public void atualizaOperacao(OperacaoAzumi operacao) {
		operacoes.remove(operacao);
		guardaOperacao(operacao);
	}

	@Override
	public void removerTudo() {
		GuardadorPagamentosAzumiImpl.operacoes = Sets.newHashSet();
	}

}

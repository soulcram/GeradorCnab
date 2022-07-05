package br.com.m3Tech.solucoesFromtis.certificadora.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusEnum;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.StatusRequisicao;

public class GuardadorRequisicaoCertificadoraImpl implements GuardadorRequisicoesCertificadora {

	private static Set<RequisicaoCertificadoraDigitalWrapper> guardarRequisicoes = RequisicoesCertificadora.guardarRequisicoes;
	
	public GuardadorRequisicaoCertificadoraImpl() {

	}
	
	@Override
	public void guardaRequisicao(RequisicaoCertificadoraDigitalWrapper requisicao) {
		guardarRequisicoes.add(requisicao);
	}

	@Override
	public Set<RequisicaoCertificadoraDigitalWrapper> pegaRequisicoes() {
		return guardarRequisicoes;
	}
	
	@Override
	public Set<RequisicaoCertificadoraDigitalWrapper> pegaRequisicoesNaoEnviado() {
		
		return guardarRequisicoes.stream().filter(r -> StatusEnum.NAO_ENVIADO.equals(r.getStatusRequisicao().getStatus())).collect(Collectors.toSet());
	}

	@Override
	public void removeRequisica(RequisicaoCertificadoraDigitalWrapper requisicao) {
		guardarRequisicoes.remove(requisicao);
	}

	@Override
	public void atualizaRequisicao(RequisicaoCertificadoraDigitalWrapper requisicao, StatusRequisicao statusRequisicao) {
		guardarRequisicoes.remove(requisicao);
		requisicao.setStatusRequisicao(statusRequisicao);
		guardaRequisicao(requisicao);
	}

	@Override
	public void removerTudo() {
		this.guardarRequisicoes = Sets.newHashSet();
	}

}

package br.com.m3Tech.solucoesFromtis.certificadora.service;

import java.util.Set;

import br.com.m3Tech.solucoesFromtis.dto.Carteira;

public interface GuardadorIntegracaoMaps {

	void guardaCarteira(Carteira carteira);

	Set<Carteira> pegaCarteiras();

	void removeCarteira(Carteira carteira);
	
	void atualizaCarteira(Carteira carteira);

	void removerTudo();
}

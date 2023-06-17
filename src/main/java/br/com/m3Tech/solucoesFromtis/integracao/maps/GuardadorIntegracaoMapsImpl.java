package br.com.m3Tech.solucoesFromtis.integracao.maps;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorIntegracaoMaps;
import br.com.m3Tech.solucoesFromtis.dto.Carteira;

@Service
public class GuardadorIntegracaoMapsImpl implements GuardadorIntegracaoMaps {

	private static Set<Carteira> carteiras = Sets.newHashSet();
	
	public GuardadorIntegracaoMapsImpl() {

	}
	
	@Override
	public void guardaCarteira(Carteira carteira) {
		carteiras.add(carteira);
	}

	@Override
	public Set<Carteira> pegaCarteiras() {
		return carteiras;
	}
	
	@Override
	public void removeCarteira(Carteira carteira) {
		carteiras.remove(carteira);
	}

	@Override
	public void atualizaCarteira(Carteira carteira) {
		carteiras.remove(carteira);
		guardaCarteira(carteira);
	}

	@Override
	public void removerTudo() {
		GuardadorIntegracaoMapsImpl.carteiras = Sets.newHashSet();
	}

}

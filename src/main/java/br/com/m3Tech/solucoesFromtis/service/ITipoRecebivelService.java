package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.TipoRecebivelDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ITipoRecebivelService {
	
	
	public List<TipoRecebivelDto> findAllTipoRecebivel(Base base, Integer cdLayout);

	public TipoRecebivelDto findOneTipoRecebivelById(Base base, Integer idMovimento);

	public TipoRecebivelDto getPrimeiroTipoRecebivelAquisicao(Base base, Integer cdLayout) throws Exception;

	public TipoRecebivelDto findTipoRecebivel(Base base, Integer cdLayout, Integer especie);
	

}

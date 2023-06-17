package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.BancoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IBancoService {
	
	
	public List<BancoDto> findAll(Base base);

	public BancoDto findOneById(Base base, Integer idBanco);

	public BancoDto findOneByNumBanco(Base base, String nuBanco);

}

package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IOriginadorService {
	
	
	public List<OriginadorDto> findAll(Base base, Integer idFundo);

	public OriginadorDto findOneById(Base base, Integer idOriginador);

	public OriginadorDto getPrimeiroOriginador(Base base, Integer idFundo) throws Exception;

}

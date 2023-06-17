package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.CedenteDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ICedenteService {
	
	
	public List<CedenteDto> findAll(Base base, Integer idFundo);

	public CedenteDto findOneById(Base base, Integer idCedente);
	
	public CedenteDto getPrimeiroCedente(Base base, Integer idFundo) throws Exception;

	public CedenteDto getCedenteByCpfCnpj(Base base, Integer idFundo, String cnpjCedente);
	
	void deleteByIdCedente(Base base, Integer idCedente);

}

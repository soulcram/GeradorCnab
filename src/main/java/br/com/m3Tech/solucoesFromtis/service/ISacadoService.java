package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.SacadoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ISacadoService {
	
	
	public List<SacadoDto> findAll(Base base, Integer idFundo);

	public SacadoDto findOneById(Base base, Integer idSacado);

	public SacadoDto getPrimeiroSacado(Base base, Integer idFundo) throws Exception;

	public SacadoDto getSacadoByCpfCnpj(Base base, Integer idFundo, String docSacado);
	
	void deleteByIdSacado(Base base, Integer idSacado);

}

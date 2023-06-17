package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.FundoCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IFundoService {
	
	
	public List<FundoDto> findAll(Base base);
	
	public FundoDto findOneById(Base base, Integer id);

	public List<FundoDto> findAllProrrogacao(Base base);

	public Integer findDiasMaxProrrogacao(Base base, Integer idFundo);

	public List<FundoCobrancaDto> findCodCobrancas(Base base, Integer id);

	public List<FundoDto> findAllComDataAtual(Base base);

	public FundoDto findFundoByCnpj(Base base, String cnpjFundo);

	public void deleteByIdFundo(Base base, Integer idFundo);

}

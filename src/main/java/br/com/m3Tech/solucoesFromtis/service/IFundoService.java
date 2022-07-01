package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.FundoCobrancaDto;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;


public interface IFundoService {
	
	
	public List<FundoDto> findAll(Connection con);
	
	public FundoDto findOneById(Connection con, Integer id);

	public List<FundoDto> findAllProrrogacao(Connection con);

	public Integer findDiasMaxProrrogacao(Connection con, Integer idFundo);

	public List<FundoCobrancaDto> findCodCobrancas(Connection con, Integer id);

	public List<FundoDto> findAllComDataAtual(Connection con);

}

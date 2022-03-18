package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface ITesteService {
	
	
	public List<ResultadoTesteDto> testarProcedures(Connection con, FundoDto fundo, Base base);
	

}

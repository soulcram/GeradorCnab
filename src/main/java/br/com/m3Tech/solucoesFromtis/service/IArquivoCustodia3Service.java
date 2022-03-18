package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;


public interface IArquivoCustodia3Service {
	
	
	public void processar(Connection con, FundoDto fundo,OriginadorDto originador, String nomeArquivo, CnabHeader cnaHeader, List<CnabDetail> listaDetail, CnabTrailler cnabTrailler) throws SQLException;

	public void processar(Connection connection, FundoDto fundoDto, String name, List<String> readAllLines) throws SQLException;

}

package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.beanio.CnabDetail;
import br.com.m3Tech.solucoesFromtis.beanio.CnabHeader;
import br.com.m3Tech.solucoesFromtis.beanio.CnabTrailler;
import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.dto.OriginadorDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IArquivoCustodia3Service {
	
	
	public void processar(Base base, FundoDto fundo,OriginadorDto originador, String nomeArquivo, CnabHeader cnaHeader, List<CnabDetail> listaDetail, CnabTrailler cnabTrailler) throws Exception;

	public void processar(Base base, FundoDto fundoDto, String name, List<String> readAllLines) throws Exception;

}

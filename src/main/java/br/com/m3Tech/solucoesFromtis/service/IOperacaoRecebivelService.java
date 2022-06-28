package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.DadosRetornoCertificadoDigitalDto;


public interface IOperacaoRecebivelService {
	
	
	public List<DadosOperacaoParaAprovacaoDto> findAllOperacoesAguardandoAprovacao(Connection con, String situacao);

	public List<DadosRetornoCertificadoDigitalDto> findAllOperacoesAguardandoRetorno(Connection con);

	



}

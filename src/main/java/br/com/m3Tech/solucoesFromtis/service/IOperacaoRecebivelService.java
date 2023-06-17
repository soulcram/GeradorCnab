package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.DadosOperacaoParaAprovacaoDto;
import br.com.m3Tech.solucoesFromtis.dto.DadosRetornoCertificadoDigitalDto;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IOperacaoRecebivelService {
	
	
	public List<DadosOperacaoParaAprovacaoDto> findAllOperacoesAguardandoAprovacao(Base base, String situacao);

	public List<DadosRetornoCertificadoDigitalDto> findAllOperacoesAguardandoRetorno(Base base);

	



}

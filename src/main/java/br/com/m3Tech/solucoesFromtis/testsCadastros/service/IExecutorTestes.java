package br.com.m3Tech.solucoesFromtis.testsCadastros.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.ResultadoTesteCustodiaDto;
import br.com.m3Tech.solucoesFromtis.testsCadastros.TestesAtivos;

public interface IExecutorTestes {
	
	public List<ResultadoTesteCustodiaDto> executarTeste(TestesAtivos testesAtivos);

}

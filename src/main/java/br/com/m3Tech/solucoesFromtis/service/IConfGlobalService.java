package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;


public interface IConfGlobalService {
	
	
	public ConfGlobal getConfGlobal();
	public String getPathRepositorio(Base base);
	public String getPathSalvarArquivo(Base base, Boolean importacaoAutomatica, Boolean versaoMercado, FundoDto fundo);
	public String getPathSalvarArquivoCobranca(Base base, Boolean importacaoAutomatica, FundoDto fundo);
	public void salvar(ConfGlobal configuracaoGlobal) throws Exception;
	public List<ConfGlobal> findAll();
	public String getPathBancoDeDados(Base base);
	

}

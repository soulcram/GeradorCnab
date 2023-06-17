package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloEmEstoqueDto;
import br.com.m3Tech.solucoesFromtis.enuns.LayoutEnum;
import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IMovimentoService {
	
	
	public List<MovimentoDto> findAllMovimentos(Base base, Integer cdLayout, boolean isCobranca);
	
	public List<MovimentoDto> findAllMovimentosAquisicao(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosBaixa(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraAquisicao(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraBaixa(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraParcial(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosParcial(Base base, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosProrrogacao(Base base, Integer cdLayout);
	
	public List<TituloEmEstoqueDto> findAllTitulosEmEstoque(Base base, Integer idFundo);

	public MovimentoDto findOneMovimentoById(Base base, Integer idMovimento);
	
	public Boolean isAquisicao(Base base, Integer idMovimento);
	public Boolean isBaixa(Base base, Integer idMovimento);
	public Boolean isRecompra(Base base, Integer idMovimento);
	public Boolean isProrrogacao(Base base, Integer idMovimento);

	public List<TituloDto> findAllTituloEmEstoqueByFundo(Base base, Integer idFundo, boolean isProrrogacao);

	public MovimentoDto getPrimeiroMovimentoAquisicao(Base base, Integer cdLayout) throws Exception;

	public MovimentoDto findMovimento(Base base, Integer cdLayout, Integer cdOcorrencia);

	public List<TituloDto> findAllTituloEmEstoqueCobranca(Base basenection, Integer fundoSelecionado, Integer idMovimento, LayoutEnum layout);

	public List<TituloDto> findAllTituloEmEstoque(Base base);

	public List<TituloDto> findAllTituloDesativados(Base base, Integer idFundo);

	List<MovimentoDto> findAllMovimentosReativacao(Base base, Integer cdLayout);

	



}

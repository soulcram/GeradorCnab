package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.MovimentoDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloDto;
import br.com.m3Tech.solucoesFromtis.dto.TituloEmEstoqueDto;


public interface IMovimentoService {
	
	
	public List<MovimentoDto> findAllMovimentos(Connection con, Integer cdLayout);
	
	public List<MovimentoDto> findAllMovimentosAquisicao(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosBaixa(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraAquisicao(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraBaixa(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosRecompraParcial(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosParcial(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosProrrogacao(Connection con, Integer cdLayout);
	
	public List<TituloEmEstoqueDto> findAllTitulosEmEstoque(Connection con, Integer idFundo);

	public MovimentoDto findOneMovimentoById(Connection con, Integer idMovimento);
	
	public Boolean isAquisicao(Connection con, Integer idMovimento);
	public Boolean isBaixa(Connection con, Integer idMovimento);
	public Boolean isRecompra(Connection con, Integer idMovimento);
	public Boolean isProrrogacao(Connection con, Integer idMovimento);

	public List<TituloDto> findAllTituloEmEstoqueByFundo(Connection con, Integer idFundo, boolean isProrrogacao);

	public MovimentoDto getPrimeiroMovimentoAquisicao(Connection con, Integer cdLayout) throws SQLException;

	public MovimentoDto findMovimento(Connection con, Integer cdLayout, Integer cdOcorrencia);

	public List<TituloDto> findAllTituloEmEstoqueCobranca(Connection connection, Integer fundoSelecionado, Integer idMovimento);

	



}

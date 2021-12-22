package br.com.m3Tech.geradorCnab.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.geradorCnab.dto.MovimentoDto;
import br.com.m3Tech.geradorCnab.dto.TituloDto;
import br.com.m3Tech.geradorCnab.dto.TituloEmEstoqueDto;


public interface IMovimentoService {
	
	
	public List<MovimentoDto> findAllMovimentos(Connection con, Integer cdLayout);
	
	public List<MovimentoDto> findAllMovimentosAquisicao(Connection con, Integer cdLayout);
	public List<MovimentoDto> findAllMovimentosBaixa(Connection con, Integer cdLayout);
	
	public List<TituloEmEstoqueDto> findAllTitulosEmEstoque(Connection con, Integer idFundo);

	public MovimentoDto findOneMovimentoById(Connection con, Integer idMovimento);
	
	public Boolean isAquisicao(Connection con, Integer idMovimento);
	public Boolean isBaixa(Connection con, Integer idMovimento);
	public Boolean isRecompra(Connection con, Integer idMovimento);
	public Boolean isProrrogacao(Connection con, Integer idMovimento);

	public List<TituloDto> findAllTituloEmEstoqueByFundo(Connection con, Integer idFundo, Integer cdLayout);

	public List<MovimentoDto> findAllMovimentosParcial(Connection con, Integer cdLayout);

	List<MovimentoDto> findAllMovimentosRecompraAquisicao(Connection con, Integer cdLayout);

	List<MovimentoDto> findAllMovimentosRecompraBaixa(Connection con, Integer cdLayout);

}

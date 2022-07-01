package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;


public interface IConfGlobalService {
    ConfGlobal getConfGlobal();

    String getPathRepositorio(Connection con);

    String getPathSalvarArquivo(Connection con, Boolean importacaoAutomatica, Boolean versaoMercado, FundoDto fundo);

    String getPathSalvarArquivoCobranca(Connection con, Boolean importacaoAutomatica, FundoDto fundo);

    void salvar(ConfGlobal configuracaoGlobal);

    List<ConfGlobal> findAll();

}

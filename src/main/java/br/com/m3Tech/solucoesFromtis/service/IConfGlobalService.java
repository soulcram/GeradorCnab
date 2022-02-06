package br.com.m3Tech.solucoesFromtis.service;

import java.sql.Connection;

import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;


public interface IConfGlobalService {
	
	
	public ConfGlobal getConfGlobal();
	public String getPathRepositorio(Connection con);
	

}

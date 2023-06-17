package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.model.Base;


public interface IBaseService {
	
	public void salvar(Base base) throws Exception;

	public List<Base> findAll();

	public void delete(Base base);

	public Base findById(String id);

	public Base findById(Integer id);
	

}

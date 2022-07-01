package br.com.m3Tech.solucoesFromtis.service;

import java.util.List;

import br.com.m3Tech.solucoesFromtis.model.Base;


 public interface IBaseService {
	
	 void salvar(Base base) throws Exception;

	 List<Base> findAll();

	 void delete(Base base);

	 Base findById(Integer id);
	

}

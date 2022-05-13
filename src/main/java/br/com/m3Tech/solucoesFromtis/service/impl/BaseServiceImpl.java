package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;

@Service
public class BaseServiceImpl implements IBaseService {

	@Override
	public void salvar(Base base) throws Exception {

		base.save();
		
	}

	@Override
	public List<Base> findAll() {
		try {
			return new Base().findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Base>();
	}

	@Override
	public void delete(Base base) {
		try {
			base.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Base findById(String id) {
		
		try {
			return new Base().findById(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public Base findById(Integer id) {
		
		try {
			return new Base().findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	

}

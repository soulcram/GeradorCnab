package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.repositories.BaseRepository;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements IBaseService {

    private final BaseRepository baseRepository;

    @Override
    public void salvar(Base base) throws Exception {
        this.baseRepository.save(base);
    }

    @Override
    public List<Base> findAll() {
        return this.baseRepository.findAll();
    }

    @Override
    public void delete(Base base) {
        this.baseRepository.delete(base);
    }

    @Override
    public Base findById(Integer id) {
        return this.baseRepository.findById(id).orElse(null);
    }

	@Override
	public Base findById(String id) {
		
		if(StringUtils.isNumeric(id)) {
			return findById(Integer.valueOf(id));
		}
		return null;
		
		
	}


}

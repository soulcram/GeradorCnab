package br.com.m3Tech.solucoesFromtis.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.repositories.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;

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


}

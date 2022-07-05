//package br.com.fromtis.certificadora.service.impl;
//
//import br.com.fromtis.certificadora.domain.ParametroEntity;
//import br.com.fromtis.certificadora.repository.ParametroRepository;
//import br.com.fromtis.certificadora.service.ParametrosServiceBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service("parametroServiceBean")
//public class ParametrosServiceImplBean implements ParametrosServiceBean {
//	
//	@Autowired
//	private ParametroRepository parametroRepository;
//	
//	public void atualizaParametro() {
//		
//	}
//
//	@Override
//	public ParametroEntity getParametro() {
//		return parametroRepository.findOne(1);
//	}
//
//	@Override
//	public void salvaParametro(ParametroEntity parametroBeanSalvar) {
//		parametroRepository.save(parametroBeanSalvar);
////		BeanUtils.copyProperties(parametroBeanSalvar, parametrosBean);
//	}
//	
//	
//	
//}

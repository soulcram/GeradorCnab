//package br.com.fromtis.certificadora.service.impl;
//
//import br.com.fromtis.certificadora.domain.UsuarioEntity;
//import br.com.fromtis.certificadora.repository.UsuarioRepository;
//import br.com.fromtis.certificadora.service.UsuarioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service("usuarioService")
//public class UsuarioServiceImpl implements UsuarioService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    public void atualizaParametro() {
//
//    }
//
//    @Override
//    public UsuarioEntity getUsuario() {
//        final List<UsuarioEntity> usuarioRepositoryAll = usuarioRepository.findAll();
//        if (usuarioRepositoryAll != null && usuarioRepositoryAll.isEmpty()) {
//            return new UsuarioEntity();
//        } else {
//            return usuarioRepositoryAll.get(0);
//        }
//    }
//
//    @Override
//    public void salva(UsuarioEntity usuarioEntity) {
//        this.usuarioRepository.save(usuarioEntity);
////		BeanUtils.copyProperties(parametroBeanSalvar, parametrosBean);
//    }
//
//
//}

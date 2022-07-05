//package br.com.fromtis.certificadora.view;
//
//import br.com.fromtis.certificadora.domain.UsuarioEntity;
//import br.com.fromtis.certificadora.service.UsuarioService;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.web.context.annotation.SessionScope;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//
//@ManagedBean
//@SessionScope
//@Getter @Setter
//public class UsuarioBean extends BaseBean {
//	private static final long serialVersionUID = 3936676934905303208L;
//	private final String CADASTRO = "usuario";
//	
//	@ManagedProperty("#{usuarioService}")
//	private UsuarioService usuarioService;
//
//	private String nome;
//	private String senha;
//
//	public String init() {
//		UsuarioEntity usuario = this.usuarioService.getUsuario();
//		this.nome = usuario.getNome();
//		this.senha = usuario.getSenha();
//		return CADASTRO;
//	}
//	
//	public void salva() {
//		final UsuarioEntity usuario = this.usuarioService.getUsuario();
//		usuario.setNome(this.nome);
//		usuario.setSenha(this.senha);
//		this.usuarioService.salva(usuario);
//		addMessageSucessoPadrao();
//	}
//}

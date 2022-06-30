
package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.dao.Conexao;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBaseService baseService;

	private Base base;
	private List<Base> bases;

	@PostConstruct
	public void init() {

		base = new Base();
		bases = baseService.findAll();

	}
	
	public void alterar(Base base) {
		this.base = base;
	}
	
	public void excluir(Base base) {
		baseService.delete(base);
		
		atualizarBases();
	}

	public void salvar() {
		try {
			
			if(!validarBase()) {
				return;
			}
			
			if(!testarConexao()) {
				return;
			}
			
			
			baseService.salvar(base);
			
			base  = new Base();
			
			atualizarBases(); 
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}

	}

	private boolean testarConexao() {

		try {

			Connection con = Conexao.getConnection(base);

			if (con == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Base inválida", "Erro ao conectar com a base informada"));
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Base inválida", "Erro ao conectar com a base informada: " + e.getMessage()));
			e.printStackTrace();
			return false;
		}

	}
	
	private void atualizarBases() {
		bases = baseService.findAll();
	}

	private boolean validarBase() {

		if (StringUtils.EmptyOrNull(base.getUrl())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Base inválida","Url é obrigatório"));
			return false;
		}

		if (StringUtils.EmptyOrNull(base.getUsuario())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Base inválida", "Usuário é obrigatório"));
			return false;
		}

		if (StringUtils.EmptyOrNull(base.getSenha())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Base inválida", "Senha é obrigatório"));
			return false;
		}

		return true;

	}

}

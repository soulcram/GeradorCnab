package br.com.m3Tech.solucoesFromtis.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.m3Tech.solucoesFromtis.beans.TemaBean;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.Getter;
import lombok.Setter;

@SessionScope
@Getter
@Setter
@Controller
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TemaBean temaBean;
	
	@Autowired
	private IConfGlobalService confGlobalService;

	public String init() {
		
		temaBean.setTema(confGlobalService.getConfGlobal().getTema());

		return "/pages/cadastros/home.xhtml";

	}

}

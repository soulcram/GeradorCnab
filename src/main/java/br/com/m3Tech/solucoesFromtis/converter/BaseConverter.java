package br.com.m3Tech.solucoesFromtis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.service.IBaseService;


@Component
@Scope("request")
@FacesConverter(value = "entregadorConverter")
public class BaseConverter implements Converter {
	
	@Autowired
	private IBaseService baseService;

	public Base getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		String[] split = arg2.split(" - ");
		
		Base base  = baseService.findById(Integer.valueOf(split[0].trim()));
		
		return base;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";

		Base base = ((Base) arg2);

		str = base.getId().toString() + " - " + base.getUrl();

		return str;
	}

}

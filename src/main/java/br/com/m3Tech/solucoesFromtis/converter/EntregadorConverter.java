//package br.com.m3Tech.solucoesFromtis.converter;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import br.com.m3Tech.solucoesFromtis.model.Entregador;
//import br.com.m3Tech.solucoesFromtis.service.IEntregadorService;
//
//@Component
//@Scope("request")
//@FacesConverter(value = "entregadorConverter")
//public class EntregadorConverter implements Converter {
//	
//	@Inject
//	private IEntregadorService entregadorService;
//
//	public Entregador getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
//		
//		String[] split = arg2.split(" - ");
//		
//		Integer id = Integer.valueOf(split[0]);
//
//		Entregador entregador = entregadorService.findById(id).get();
//		
//		return entregador;
//	}
//
//	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		String str = "";
//
//		Entregador e = ((Entregador) arg2);
//
//		str = e.getIdEntregador().toString() + " - " + e.getNome();
//
//		return str;
//	}
//
//}

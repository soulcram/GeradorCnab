//package br.com.m3Tech.solucoesFromtis.converter;
//
//import java.util.Iterator;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//import org.primefaces.component.picklist.PickList;
//import org.primefaces.model.DualListModel;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import br.com.m3Tech.solucoesFromtis.model.Produto;
//
//@Component
//@Scope("request")
//@FacesConverter(value = "produtoConverter")
//public class ProdutoConverter implements Converter {
//
//	@SuppressWarnings("rawtypes")
//	public Produto getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
//		Produto ret = null;
//		if (arg1 instanceof PickList) {
//			Object dualList = ((PickList) arg1).getValue();
//			DualListModel dl = (DualListModel) dualList;
//			for (Iterator iterator = dl.getSource().iterator(); iterator
//					.hasNext();) {
//				Produto o = (Produto) iterator.next();
//				String id = o.getIdProduto().toString();
//				if (arg2.equals(id)) {
//					ret = o;
//					break;
//				}
//			}
//
//			if (ret == null) {
//				for (Iterator iterator1 = dl.getTarget().iterator(); iterator1
//						.hasNext();) {
//					Produto o = (Produto) iterator1.next();
//					String id = o.getIdProduto().toString();
//					if (arg2.equals(id)) {
//						ret = o;
//						break;
//					}
//				}
//
//			}
//		}
//		return ret;
//	}
//
//	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		String str = "";
//			str = ((Produto) arg2).getIdProduto().toString();
//		return str;
//	}
//
//
//}

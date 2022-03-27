//package br.com.m3Tech.solucoesFromtis.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.faces.context.FacesContext;
//import javax.faces.event.PhaseId;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//
//import br.com.m3Tech.solucoesFromtis.model.Produto;
//import br.com.m3Tech.solucoesFromtis.service.IProdutoService;
//
//@Named
//public class Images implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	@Inject
//    private IProdutoService service;
//
//    public StreamedContent getImagemProduto() throws IOException {
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
//            return new DefaultStreamedContent();
//        }else {
//            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
//            String id = context.getExternalContext().getRequestParameterMap().get("id");
//            
//            Produto produto = service.findById(Integer.valueOf(id)).get();
//            
//            if(produto.getImagem() != null) {
//            	return new DefaultStreamedContent(new ByteArrayInputStream(produto.getImagem()));
//            }
//            
//            return null;
//        }
//    }
//
//}

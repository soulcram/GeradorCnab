package br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.impl;

import javax.jws.HandlerChain;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;

import br.com.m3Tech.solucoesFromtis.certificadora.service.impl.GuardadorRequisicaoCertificadoraImpl;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificadoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;

@HandlerChain(file = "handlers.xml")
@WebService(name="RequisicaoCertificacaoDigital", serviceName="RequisicaoCertificacaoDigital", 
						targetNamespace = "http://webservices.portal.fidc.fromtis.com.br/",	portName = "RequisicaoCertificacaoDigitalPort")
public class RequisicaoCertificacaoDigitalImpl  implements RequisicaoCertificacaoDigital {


	@Oneway
	@WebMethod
	@RequestWrapper(localName = "requisicao", targetNamespace = "http://webservices.portal.fidc.fromtis.com.br/", className = "br.com.fromtis.fidc.portal.webservices.requisicao.Requisicao")
	@Action(input = "http://webservices.portal.fidc.fromtis.com.br/RequisicaoCertificacaoDigital/requisicao")
	public void requisicao(@WebParam(name="requisicaoCertificado") RequisicaoCertificadoDigital requisicaoCertificado) {
		
		new GuardadorRequisicaoCertificadoraImpl().guardaRequisicao(RequisicaoCertificadoraDigitalWrapper.requisicaoNaoEnviada(requisicaoCertificado));
		
		/*	try {
				JAXBContext jaxbContext = JAXBContext.newInstance(requisicaoCertificado.getClass());
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(requisicaoCertificado, System.out);
				
			} catch (Exception e) {
			}*/
	}

}

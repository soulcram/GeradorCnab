package br.com.m3Tech.solucoesFromtis.certificadora.webservices;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.apache.axis.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler.AutenticadorRequestHandler;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler.RetornoHandler;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.requisicao.RequisicaoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.certificadora.webservices.retorno.RetornoCertificacaoDigital;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.scheduled.ExecucoesAutomaticas;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeradorServiceWS  {
	
	private static final Logger logger = LoggerFactory.getLogger(ExecucoesAutomaticas.class);
	
	public RetornoCertificacaoDigital criaRequestRetornoCertificadora() {
		try {
			ConfGlobal confGlobal = new ConfGlobal().findAll().get(0);
			
			
			if(confGlobal == null || StringUtils.isEmpty(confGlobal.getUrlPortal())) {
				log.error("Erro, não existe parametro cadsatrado.");
				throw new Exception();
			}
			
			
			RetornoCertificacaoDigital retornoCertificacaoDigital = criaServico(confGlobal.getUrlPortal() + "/portal/retornoCertificacaoDigital?wsdl", "RetornoCertificacaoDigital", false).getPort(new QName("http://webservices.portal.fidc.fromtis.com.br/", "RetornoCertificacaoDigitalPort"), RetornoCertificacaoDigital.class);
			
//			BindingProvider bp  = (BindingProvider) retornoCertificacaoDigital;
//			bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 3000);
//			bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
			
			return retornoCertificacaoDigital;
		}catch(Exception e ) {
			log.error("Erro ao criar o Servico com o Webservice Retorno");
			throw new WebServiceException(e.getMessage());
		}
	}
	
	public RequisicaoCertificacaoDigital criaRequestRequisicaoCertificadora() {
		try {
			ConfGlobal confGlobal = new ConfGlobal().findAll().get(0);
			return criaServico(confGlobal.getUrlPortal() + "/portal/requisicaoCertificacaoDigital?wsdl", "RequisicaoCertificacaoDigital", false).getPort(new QName("http://webservices.portal.fidc.fromtis.com.br/", "RequisicaoCertificacaoDigitalPort"), RequisicaoCertificacaoDigital.class);
		}catch(Exception e ) {
			log.error("Erro ao criar o Servico com o Webservice Retorno");
			throw new WebServiceException(e.getMessage());
		}
	}

	private Service criaServico(final String url, final String serviceName, final Boolean retornoGzip) throws Exception {
		logger.info("Criando Servico Url: {} " , url);
		Service service = Service.create(new URL(url), new QName("http://webservices.portal.fidc.fromtis.com.br/", serviceName));
		service.setHandlerResolver(new HandlerResolver() {
			
			@Override
			public List<Handler> getHandlerChain(PortInfo portInfo) {
				List<Handler> listaHandler = Lists.newArrayList();
				listaHandler.add(new AutenticadorRequestHandler());
				
				if(retornoGzip){
					listaHandler.add(new RetornoHandler());
				}
				return listaHandler;
			}
		});
		return service;
	}
}

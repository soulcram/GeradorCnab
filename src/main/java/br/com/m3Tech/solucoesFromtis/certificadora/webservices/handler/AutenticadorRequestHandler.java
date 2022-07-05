package br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;


@Service
public class AutenticadorRequestHandler  implements SOAPHandler<SOAPMessageContext> {

	@Autowired
	private IConfGlobalService confGlobalService;

	@Override
	public void close(MessageContext mc) {
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext smc) {
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext smc) {

		ConfGlobal confGlobal = null;
		try {
			confGlobal = new ConfGlobal().findAll().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Boolean outBound = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outBound) {
			Map<String, List<String>> requestHeaders =  (Map<String, List<String>>) smc.get(MessageContext.HTTP_REQUEST_HEADERS);
			if(requestHeaders == null) {
				requestHeaders = Maps.newHashMap();
				smc.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
			}

			requestHeaders.put("Username", Lists.newArrayList(confGlobal.getUsuarioPortalServicos()));
			requestHeaders.put("Password", Lists.newArrayList(confGlobal.getSenhaPortalServicos()));
		} 
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}

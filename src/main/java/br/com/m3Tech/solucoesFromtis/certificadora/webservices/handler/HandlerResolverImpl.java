package br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler;

import com.google.common.collect.Lists;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerResolverImpl implements HandlerResolver {
	
	@Autowired
	private AutenticadorRequestHandler autenticadorRequestHandler;
	
	@Autowired
	private RetornoHandler retornoHandler;

	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> listaHandler = Lists.newArrayList();
		listaHandler.add(autenticadorRequestHandler);
		listaHandler.add(retornoHandler);
		return listaHandler;
	}
}

package br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler;

import com.google.common.collect.Lists;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.List;

public class HandlerResolverImpl implements HandlerResolver {

	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> listaHandler = Lists.newArrayList();
		listaHandler.add(new AutenticadorRequestHandler());
		listaHandler.add(new RetornoHandler());
		return listaHandler;
	}
}

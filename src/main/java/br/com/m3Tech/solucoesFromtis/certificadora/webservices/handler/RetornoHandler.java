package br.com.m3Tech.solucoesFromtis.certificadora.webservices.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class RetornoHandler implements SOAPHandler<SOAPMessageContext>{
	
	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if(isRequest){

			try{
				SOAPMessage soapMessage = context.getMessage();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		        soapMessage.writeTo(out);
		        
		        Map<String, List<String>> requestHeaders = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
				if(requestHeaders == null) {
					requestHeaders = Maps.newHashMap();
					context.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
				}

				requestHeaders.put("Content-Encoding", Collections.singletonList("gzip"));
				requestHeaders.put("Accept-Encoding", Collections.singletonList("gzip"));
				context.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
				
			}catch(SOAPException e){
				System.err.println(e);
			}catch(IOException e){
				System.err.println(e);
			} 
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}

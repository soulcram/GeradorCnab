package br.com.fromtis.fidc.portal.servicos.consulta.soap;

import java.util.Hashtable;
import java.util.Map;

public class AprovacaoOperacaoGestorWSProxy implements br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorWS {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorWS aprovacaoOperacaoGestorWS = null;
  
  public AprovacaoOperacaoGestorWSProxy() {
    _initAprovacaoOperacaoGestorWSProxy();
  }
  
  public AprovacaoOperacaoGestorWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initAprovacaoOperacaoGestorWSProxy();
  }
  
  public AprovacaoOperacaoGestorWSProxy(String endpoint, String usuario, String senha) {
	    _endpoint = endpoint;
	    _initAprovacaoOperacaoGestorWSProxy(usuario, senha);
	  }
  
  private void _initAprovacaoOperacaoGestorWSProxy(String usuario, String senha) {
	    try {
	      aprovacaoOperacaoGestorWS = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorServiceLocator()).getAprovacaoOperacaoGestorPort();
	      if (aprovacaoOperacaoGestorWS != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	      Map<String, String> credentials = new Hashtable<String,String>();

		 credentials.put("username", usuario);
		 credentials.put("password", senha);
	        
	     ((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._setProperty("HTTP-Request-Headers", credentials);
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  private void _initAprovacaoOperacaoGestorWSProxy() {
    try {
      aprovacaoOperacaoGestorWS = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorServiceLocator()).getAprovacaoOperacaoGestorPort();
      if (aprovacaoOperacaoGestorWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aprovacaoOperacaoGestorWS != null)
      ((javax.xml.rpc.Stub)aprovacaoOperacaoGestorWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoOperacaoGestorWS getAprovacaoOperacaoGestorWS() {
    if (aprovacaoOperacaoGestorWS == null)
      _initAprovacaoOperacaoGestorWSProxy();
    return aprovacaoOperacaoGestorWS;
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoAprovacao aprovarGestor(br.com.fromtis.fidc.portal.servicos.consulta.soap.OperacaoAprovacaoGestor operacao) throws java.rmi.RemoteException{
    if (aprovacaoOperacaoGestorWS == null)
      _initAprovacaoOperacaoGestorWSProxy();
    return aprovacaoOperacaoGestorWS.aprovarGestor(operacao);
  }
  
  
}
package br.com.fromtis.fidc.portal.servicos.consulta.soap;

import java.util.Hashtable;
import java.util.Map;

public class AprovacaoConsultoriaWSProxy implements br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaWS {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaWS aprovacaoConsultoriaWS = null;
  
  public AprovacaoConsultoriaWSProxy() {
    _initAprovacaoConsultoriaWSProxy();
  }
  
  public AprovacaoConsultoriaWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initAprovacaoConsultoriaWSProxy();
  }
  
  public AprovacaoConsultoriaWSProxy(String endpoint,String usuario, String senha) {
	    _endpoint = endpoint;
	    _initAprovacaoConsultoriaWSProxy(usuario,senha);
	  }
  
  private void _initAprovacaoConsultoriaWSProxy() {
    try {
      aprovacaoConsultoriaWS = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaServiceLocator()).getAprovacaoConsultoriaPort();
      if (aprovacaoConsultoriaWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  private void _initAprovacaoConsultoriaWSProxy(String usuario, String senha) {
	    try {
	      aprovacaoConsultoriaWS = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaServiceLocator()).getAprovacaoConsultoriaPort();
	      if (aprovacaoConsultoriaWS != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	      Map<String, String> credentials = new Hashtable<String,String>();

		 credentials.put("username", usuario);
		 credentials.put("password", senha);
	        
	     ((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._setProperty("HTTP-Request-Headers", credentials);
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aprovacaoConsultoriaWS != null)
      ((javax.xml.rpc.Stub)aprovacaoConsultoriaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.AprovacaoConsultoriaWS getAprovacaoConsultoriaWS() {
    if (aprovacaoConsultoriaWS == null)
      _initAprovacaoConsultoriaWSProxy();
    return aprovacaoConsultoriaWS;
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoAprovacao aprovarOperacaoConsultoria(br.com.fromtis.fidc.portal.servicos.consulta.soap.DadosOperacaoParaAprovacao dadosOperacao) throws java.rmi.RemoteException{
    if (aprovacaoConsultoriaWS == null)
      _initAprovacaoConsultoriaWSProxy();
    return aprovacaoConsultoriaWS.aprovarOperacaoConsultoria(dadosOperacao);
  }
  
  
}
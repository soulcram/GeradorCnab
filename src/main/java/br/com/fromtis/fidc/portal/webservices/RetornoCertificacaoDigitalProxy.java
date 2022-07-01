package br.com.fromtis.fidc.portal.webservices;

import java.util.Hashtable;
import java.util.Map;

public class RetornoCertificacaoDigitalProxy implements br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType retornoCertificacaoDigital_PortType = null;
  
  public RetornoCertificacaoDigitalProxy() {
    _initRetornoCertificacaoDigitalProxy();
  }
  
  public RetornoCertificacaoDigitalProxy(String endpoint) {
    _endpoint = endpoint;
    _initRetornoCertificacaoDigitalProxy();
  }
  
  private void _initRetornoCertificacaoDigitalProxy() {
    try {
      retornoCertificacaoDigital_PortType = (new br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_ServiceLocator()).getRetornoCertificacaoDigitalPort();
      if (retornoCertificacaoDigital_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)retornoCertificacaoDigital_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)retornoCertificacaoDigital_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public RetornoCertificacaoDigitalProxy(String endpoint, String usuario, String senha) {
	    _endpoint = endpoint;
	    _initRetornoCertificacaoDigitalProxy(usuario, senha);
  }
	  
	private void _initRetornoCertificacaoDigitalProxy(String usuario, String senha) {
		try {
			retornoCertificacaoDigital_PortType = (new br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_ServiceLocator())
					.getRetornoCertificacaoDigitalPort();
			if (retornoCertificacaoDigital_PortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) retornoCertificacaoDigital_PortType)
							._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) retornoCertificacaoDigital_PortType)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

			Map<String, String> credentials = new Hashtable<String, String>();

			credentials.put("username", usuario);
			credentials.put("password", senha);

			((javax.xml.rpc.Stub) retornoCertificacaoDigital_PortType)._setProperty("HTTP-Request-Headers",
					credentials);

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (retornoCertificacaoDigital_PortType != null)
      ((javax.xml.rpc.Stub)retornoCertificacaoDigital_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType getRetornoCertificacaoDigital_PortType() {
    if (retornoCertificacaoDigital_PortType == null)
      _initRetornoCertificacaoDigitalProxy();
    return retornoCertificacaoDigital_PortType;
  }
  
  public br.com.fromtis.fidc.portal.webservices.RetornoProcessamento retorno(br.com.fromtis.fidc.portal.webservices.RetornoCertificadoDigital retornoAquisicao) throws java.rmi.RemoteException{
    if (retornoCertificacaoDigital_PortType == null)
      _initRetornoCertificacaoDigitalProxy();
    return retornoCertificacaoDigital_PortType.retorno(retornoAquisicao);
  }
  
  
}
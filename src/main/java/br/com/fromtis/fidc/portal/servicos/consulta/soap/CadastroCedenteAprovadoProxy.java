package br.com.fromtis.fidc.portal.servicos.consulta.soap;

import java.util.Hashtable;
import java.util.Map;

public class CadastroCedenteAprovadoProxy implements br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado cadastroCedenteAprovado = null;
  
  public CadastroCedenteAprovadoProxy() {
    _initCadastroCedenteAprovadoProxy();
  }
  
  public CadastroCedenteAprovadoProxy(String usuario, String senha) {
	    _initCadastroCedenteAprovadoProxy(usuario, senha);
	  }
  
  public CadastroCedenteAprovadoProxy(String endpoint) {
    _endpoint = endpoint;
    _initCadastroCedenteAprovadoProxy();
  }
  
  public CadastroCedenteAprovadoProxy(String endpoint,String usuario, String senha) {
	    _endpoint = endpoint;
	    _initCadastroCedenteAprovadoProxy(usuario,senha);
	  }
  
  private void _initCadastroCedenteAprovadoProxy() {
    try {
      cadastroCedenteAprovado = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoServiceLocator()).getCadastroCedenteAprovadoPortBinding();
      if (cadastroCedenteAprovado != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cadastroCedenteAprovado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cadastroCedenteAprovado)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }


@SuppressWarnings("deprecation")
private void _initCadastroCedenteAprovadoProxy(String usuario, String senha) {
    try {
    	
    	CadastroCedenteAprovadoServiceLocator locator = new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoServiceLocator();

    	cadastroCedenteAprovado = locator.getCadastroCedenteAprovadoPortBinding();
    	if (cadastroCedenteAprovado != null) {
    	  
    		
 		 
	        if (_endpoint != null) {
	          ((javax.xml.rpc.Stub)cadastroCedenteAprovado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        }else {
	          _endpoint = (String)((javax.xml.rpc.Stub)cadastroCedenteAprovado)._getProperty("javax.xml.rpc.service.endpoint.address");
	        }
	        
	        Map<String, String> credentials = new Hashtable<String,String>();

 		 	credentials.put("username", usuario);
 		 	credentials.put("password", senha);
	        
	        ((javax.xml.rpc.Stub)cadastroCedenteAprovado)._setProperty("HTTP-Request-Headers", credentials);
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cadastroCedenteAprovado != null)
      ((javax.xml.rpc.Stub)cadastroCedenteAprovado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado getCadastroCedenteAprovado() {
    if (cadastroCedenteAprovado == null)
      _initCadastroCedenteAprovadoProxy();
    return cadastroCedenteAprovado;
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoCadastroCedente[] cadastroCedenteAprovado(br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedente[] cadastroCedentes) throws java.rmi.RemoteException{
    if (cadastroCedenteAprovado == null)
      _initCadastroCedenteAprovadoProxy();
    return cadastroCedenteAprovado.cadastroCedenteAprovado(cadastroCedentes);
  }
  
  
}
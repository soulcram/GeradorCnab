package br.com.fromtis.fidc.portal.servicos.consulta.soap;

import java.util.Hashtable;
import java.util.Map;

public class CadastroCedenteParaAprovarProxy implements br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar cadastroCedenteParaAprovar = null;
  
  public CadastroCedenteParaAprovarProxy() {
    _initCadastroCedenteParaAprovarProxy();
  }
  
  public CadastroCedenteParaAprovarProxy(String endpoint) {
    _endpoint = endpoint;
    _initCadastroCedenteParaAprovarProxy();
  }
  
  public CadastroCedenteParaAprovarProxy(String endpoint, String usuario, String senha) {
	    _endpoint = endpoint;
	    _initCadastroCedenteParaAprovarProxy(usuario,senha);
	  }
  
  private void _initCadastroCedenteParaAprovarProxy() {
    try {
      cadastroCedenteParaAprovar = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarServiceLocator()).getCadastroCedenteParaAprovarPortBinding();
      if (cadastroCedenteParaAprovar != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  private void _initCadastroCedenteParaAprovarProxy(String usuario, String senha) {
	    try {
	      cadastroCedenteParaAprovar = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarServiceLocator()).getCadastroCedenteParaAprovarPortBinding();
	      if (cadastroCedenteParaAprovar != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	      Map<String, String> credentials = new Hashtable<String,String>();

	      credentials.put("username", usuario);
		  credentials.put("password", senha);
		 	
		  ((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._setProperty("HTTP-Request-Headers", credentials);
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cadastroCedenteParaAprovar != null)
      ((javax.xml.rpc.Stub)cadastroCedenteParaAprovar)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar getCadastroCedenteParaAprovar() {
    if (cadastroCedenteParaAprovar == null)
      _initCadastroCedenteParaAprovarProxy();
    return cadastroCedenteParaAprovar;
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoCadastroCedente[] cadastroCedenteParaAprovar(br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedente[] cadastroCedente) throws java.rmi.RemoteException{
    if (cadastroCedenteParaAprovar == null)
      _initCadastroCedenteParaAprovarProxy();
    return cadastroCedenteParaAprovar.cadastroCedenteParaAprovar(cadastroCedente);
  }
  
  
}
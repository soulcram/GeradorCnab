package br.com.fromtis.fidc.portal.servicos.consulta.soap;

import java.util.Hashtable;
import java.util.Map;

public class ImportacaoArquivoCnabProxy implements br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnab {
  private String _endpoint = null;
  private br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnab importacaoArquivoCnab = null;
  
  public ImportacaoArquivoCnabProxy() {
    _initImportacaoArquivoCnabProxy();
  }
  
  public ImportacaoArquivoCnabProxy(String endpoint) {
    _endpoint = endpoint;
    _initImportacaoArquivoCnabProxy();
  }
  
  public ImportacaoArquivoCnabProxy(String endpoint, String usuario, String senha) {
	    _endpoint = endpoint;
	    _initImportacaoArquivoCnabProxy(usuario, senha);
	  }
  
  private void _initImportacaoArquivoCnabProxy(String usuario, String senha) {
	    try {
	      importacaoArquivoCnab = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnabServiceLocator()).getImportacaoArquivoCnabPortBinding();
	      if (importacaoArquivoCnab != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)importacaoArquivoCnab)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)importacaoArquivoCnab)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	        Map<String, String> credentials = new Hashtable<String,String>();

		 	credentials.put("username", usuario);
		 	credentials.put("password", senha);
	        
	        ((javax.xml.rpc.Stub)importacaoArquivoCnab)._setProperty("HTTP-Request-Headers", credentials);
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
  
  private void _initImportacaoArquivoCnabProxy() {
    try {
      importacaoArquivoCnab = (new br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnabServiceLocator()).getImportacaoArquivoCnabPortBinding();
      if (importacaoArquivoCnab != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)importacaoArquivoCnab)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)importacaoArquivoCnab)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (importacaoArquivoCnab != null)
      ((javax.xml.rpc.Stub)importacaoArquivoCnab)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.ImportacaoArquivoCnab getImportacaoArquivoCnab() {
    if (importacaoArquivoCnab == null)
      _initImportacaoArquivoCnabProxy();
    return importacaoArquivoCnab;
  }
  
  public br.com.fromtis.fidc.portal.servicos.consulta.soap.RetornoImportacao importarArquivo(br.com.fromtis.fidc.portal.servicos.consulta.soap.ArquivoCnab arquivoCnab) throws java.rmi.RemoteException{
    if (importacaoArquivoCnab == null)
      _initImportacaoArquivoCnabProxy();
    return importacaoArquivoCnab.importarArquivo(arquivoCnab);
  }
  
  
}
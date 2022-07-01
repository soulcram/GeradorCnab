/**
 * CadastroCedenteParaAprovarServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class CadastroCedenteParaAprovarServiceLocator extends org.apache.axis.client.Service implements br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarService {

    public CadastroCedenteParaAprovarServiceLocator() {
    }


    public CadastroCedenteParaAprovarServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CadastroCedenteParaAprovarServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CadastroCedenteParaAprovarPortBinding
    private java.lang.String CadastroCedenteParaAprovarPortBinding_address = "http://localhost:8080/portal-servicos/servicos/soap/cadastroCedenteParaAprovar";

    public java.lang.String getCadastroCedenteParaAprovarPortBindingAddress() {
        return CadastroCedenteParaAprovarPortBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CadastroCedenteParaAprovarPortBindingWSDDServiceName = "CadastroCedenteParaAprovarPortBinding";

    public java.lang.String getCadastroCedenteParaAprovarPortBindingWSDDServiceName() {
        return CadastroCedenteParaAprovarPortBindingWSDDServiceName;
    }

    public void setCadastroCedenteParaAprovarPortBindingWSDDServiceName(java.lang.String name) {
        CadastroCedenteParaAprovarPortBindingWSDDServiceName = name;
    }

    public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar getCadastroCedenteParaAprovarPortBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CadastroCedenteParaAprovarPortBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCadastroCedenteParaAprovarPortBinding(endpoint);
    }

    public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar getCadastroCedenteParaAprovarPortBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarPortBindingBindingStub _stub = new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarPortBindingBindingStub(portAddress, this);
            _stub.setPortName(getCadastroCedenteParaAprovarPortBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCadastroCedenteParaAprovarPortBindingEndpointAddress(java.lang.String address) {
        CadastroCedenteParaAprovarPortBinding_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovar.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarPortBindingBindingStub _stub = new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteParaAprovarPortBindingBindingStub(new java.net.URL(CadastroCedenteParaAprovarPortBinding_address), this);
                _stub.setPortName(getCadastroCedenteParaAprovarPortBindingWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CadastroCedenteParaAprovarPortBinding".equals(inputPortName)) {
            return getCadastroCedenteParaAprovarPortBinding();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "CadastroCedenteParaAprovarService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "CadastroCedenteParaAprovarPortBinding"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CadastroCedenteParaAprovarPortBinding".equals(portName)) {
            setCadastroCedenteParaAprovarPortBindingEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

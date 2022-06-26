/**
 * CadastroCedenteAprovadoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.servicos.consulta.soap;

public class CadastroCedenteAprovadoServiceLocator extends org.apache.axis.client.Service implements br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoService {

    public CadastroCedenteAprovadoServiceLocator() {
    }


    public CadastroCedenteAprovadoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CadastroCedenteAprovadoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CadastroCedenteAprovadoPortBinding
    private java.lang.String CadastroCedenteAprovadoPortBinding_address = "http://localhost:8080/portal-servicos/servicos/soap/cadastroCedenteAprovado";

    public java.lang.String getCadastroCedenteAprovadoPortBindingAddress() {
        return CadastroCedenteAprovadoPortBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CadastroCedenteAprovadoPortBindingWSDDServiceName = "CadastroCedenteAprovadoPortBinding";

    public java.lang.String getCadastroCedenteAprovadoPortBindingWSDDServiceName() {
        return CadastroCedenteAprovadoPortBindingWSDDServiceName;
    }

    public void setCadastroCedenteAprovadoPortBindingWSDDServiceName(java.lang.String name) {
        CadastroCedenteAprovadoPortBindingWSDDServiceName = name;
    }

    public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado getCadastroCedenteAprovadoPortBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CadastroCedenteAprovadoPortBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCadastroCedenteAprovadoPortBinding(endpoint);
    }

    public br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado getCadastroCedenteAprovadoPortBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoPortBindingBindingStub _stub = new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoPortBindingBindingStub(portAddress, this);
            _stub.setPortName(getCadastroCedenteAprovadoPortBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCadastroCedenteAprovadoPortBindingEndpointAddress(java.lang.String address) {
        CadastroCedenteAprovadoPortBinding_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovado.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoPortBindingBindingStub _stub = new br.com.fromtis.fidc.portal.servicos.consulta.soap.CadastroCedenteAprovadoPortBindingBindingStub(new java.net.URL(CadastroCedenteAprovadoPortBinding_address), this);
                _stub.setPortName(getCadastroCedenteAprovadoPortBindingWSDDServiceName());
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
        if ("CadastroCedenteAprovadoPortBinding".equals(inputPortName)) {
            return getCadastroCedenteAprovadoPortBinding();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "CadastroCedenteAprovadoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.consulta.servicos.portal.fidc.fromtis.com.br/", "CadastroCedenteAprovadoPortBinding"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CadastroCedenteAprovadoPortBinding".equals(portName)) {
            setCadastroCedenteAprovadoPortBindingEndpointAddress(address);
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

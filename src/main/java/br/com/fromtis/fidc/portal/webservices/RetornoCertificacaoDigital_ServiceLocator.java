/**
 * RetornoCertificacaoDigital_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class RetornoCertificacaoDigital_ServiceLocator extends org.apache.axis.client.Service implements br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_Service {

    public RetornoCertificacaoDigital_ServiceLocator() {
    }


    public RetornoCertificacaoDigital_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RetornoCertificacaoDigital_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RetornoCertificacaoDigitalPort
    private java.lang.String RetornoCertificacaoDigitalPort_address = "http://localhost:8080/portal/retornoCertificacaoDigital";

    public java.lang.String getRetornoCertificacaoDigitalPortAddress() {
        return RetornoCertificacaoDigitalPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RetornoCertificacaoDigitalPortWSDDServiceName = "RetornoCertificacaoDigitalPort";

    public java.lang.String getRetornoCertificacaoDigitalPortWSDDServiceName() {
        return RetornoCertificacaoDigitalPortWSDDServiceName;
    }

    public void setRetornoCertificacaoDigitalPortWSDDServiceName(java.lang.String name) {
        RetornoCertificacaoDigitalPortWSDDServiceName = name;
    }

    public br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType getRetornoCertificacaoDigitalPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RetornoCertificacaoDigitalPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRetornoCertificacaoDigitalPort(endpoint);
    }

    public br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType getRetornoCertificacaoDigitalPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigitalPortBindingStub _stub = new br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigitalPortBindingStub(portAddress, this);
            _stub.setPortName(getRetornoCertificacaoDigitalPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRetornoCertificacaoDigitalPortEndpointAddress(java.lang.String address) {
        RetornoCertificacaoDigitalPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigitalPortBindingStub _stub = new br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigitalPortBindingStub(new java.net.URL(RetornoCertificacaoDigitalPort_address), this);
                _stub.setPortName(getRetornoCertificacaoDigitalPortWSDDServiceName());
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
        if ("RetornoCertificacaoDigitalPort".equals(inputPortName)) {
            return getRetornoCertificacaoDigitalPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "RetornoCertificacaoDigital");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "RetornoCertificacaoDigitalPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RetornoCertificacaoDigitalPort".equals(portName)) {
            setRetornoCertificacaoDigitalPortEndpointAddress(address);
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

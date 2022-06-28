/**
 * RetornoCertificacaoDigitalPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fromtis.fidc.portal.webservices;

public class RetornoCertificacaoDigitalPortBindingStub extends org.apache.axis.client.Stub implements br.com.fromtis.fidc.portal.webservices.RetornoCertificacaoDigital_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retorno");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "retornoAquisicao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoCertificadoDigital"), br.com.fromtis.fidc.portal.webservices.RetornoCertificadoDigital.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoProcessamento"));
        oper.setReturnClass(br.com.fromtis.fidc.portal.webservices.RetornoProcessamento.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

    }

    public RetornoCertificacaoDigitalPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RetornoCertificacaoDigitalPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RetornoCertificacaoDigitalPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">cedenteRetorno>avalistas");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Avalista[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "avalista");
            qName2 = new javax.xml.namespace.QName("", "avalista");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">cedenteRetorno>partes");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.ParteRepresentante[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parteRepresentante");
            qName2 = new javax.xml.namespace.QName("", "parte");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">cedenteRetorno>titulos");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TituloRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno");
            qName2 = new javax.xml.namespace.QName("", "titulo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">cedenteRetorno>titulosRecompra");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TituloRetorno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno");
            qName2 = new javax.xml.namespace.QName("", "titulo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">consultoria>partes");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Parte[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parte");
            qName2 = new javax.xml.namespace.QName("", "parte");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">fundo>partes");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Parte[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parte");
            qName2 = new javax.xml.namespace.QName("", "parte");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">fundo>testemunhas");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Testemunha[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "testemunha");
            qName2 = new javax.xml.namespace.QName("", "testemunha");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">retornoProcessamento>erros");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Erro[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "erro");
            qName2 = new javax.xml.namespace.QName("", "erro");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", ">sacado>partes");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.ParteRepresentante[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parteRepresentante");
            qName2 = new javax.xml.namespace.QName("", "parte");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "avalista");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Avalista.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "cedenteRetorno");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.CedenteRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "consultoria");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Consultoria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "custodiante");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Custodiante.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "erro");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Erro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "fundo");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Fundo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "operacao");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Operacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parte");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Parte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "parteRepresentante");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.ParteRepresentante.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoCertificadoDigital");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.RetornoCertificadoDigital.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retornoProcessamento");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.RetornoProcessamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "sacado");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Sacado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "testemunha");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.Testemunha.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tipoAssinaturaEnum");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TipoAssinaturaEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tipoPessoaEnum");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TipoPessoaEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tipoTituloEnum");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TipoTituloEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "tituloRetorno");
            cachedSerQNames.add(qName);
            cls = br.com.fromtis.fidc.portal.webservices.TituloRetorno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public br.com.fromtis.fidc.portal.webservices.RetornoProcessamento retorno(br.com.fromtis.fidc.portal.webservices.RetornoCertificadoDigital retornoAquisicao) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservices.portal.fidc.fromtis.com.br/", "retorno"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {retornoAquisicao});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.fromtis.fidc.portal.webservices.RetornoProcessamento) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.fromtis.fidc.portal.webservices.RetornoProcessamento) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.fromtis.fidc.portal.webservices.RetornoProcessamento.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

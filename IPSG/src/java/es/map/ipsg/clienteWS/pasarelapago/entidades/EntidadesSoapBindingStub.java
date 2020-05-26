/**
 * EntidadesSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Class EntidadesSoapBindingStub.
 */
public class EntidadesSoapBindingStub extends org.apache.axis.client.Stub implements es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades {
    
    /** el cached ser classes. */
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    
    /** el cached ser Q names. */
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    
    /** el cached ser factories. */
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    
    /** el cached deser factories. */
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    /** el operations. */
    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    /**
     * En el operation desc 1.
     */
    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("peticion");
        oper.setReturnType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "RespuestaGetListaEntidades"));
        oper.setReturnClass(es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("peticionACuenta");
        oper.setReturnType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "RespuestaGetListaEntidades"));
        oper.setReturnClass(es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticionACuentaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("peticionConTarjeta");
        oper.setReturnType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "RespuestaGetListaEntidades"));
        oper.setReturnClass(es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticionConTarjetaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    /**
     * Crea una nueva entidades soap binding stub.
     *
     * @throws AxisFault el axis fault
     */
    public EntidadesSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    /**
     * Crea una nueva entidades soap binding stub.
     *
     * @param endpointURL el endpoint URL
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public EntidadesSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    /**
     * Crea una nueva entidades soap binding stub.
     *
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public EntidadesSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "ArrayOfEntidad");
            cachedSerQNames.add(qName);
            cls = es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "Entidad");
            qName2 = new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "Entidad");
            cachedSerQNames.add(qName);
            cls = es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "RespuestaGetListaEntidades");
            cachedSerQNames.add(qName);
            cls = es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    /**
     * Crea el call.
     *
     * @return el org.apache.axis.client. call
     * @throws RemoteException el remote exception
     */
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

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticion()
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticion() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) org.apache.axis.utils.JavaUtils.convert(_resp, es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticionACuenta()
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionACuenta() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticionACuenta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) org.apache.axis.utils.JavaUtils.convert(_resp, es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticionConTarjeta()
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionConTarjeta() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "peticionConTarjeta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades) org.apache.axis.utils.JavaUtils.convert(_resp, es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

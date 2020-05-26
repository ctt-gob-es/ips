/**
 * IntegracionMixtaSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Class IntegracionMixtaSoapBindingStub.
 */
public class IntegracionMixtaSoapBindingStub extends org.apache.axis.client.Stub implements es.map.pasarelapago.service.ServicioIntegracion {
    
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
        _operations = new org.apache.axis.description.OperationDesc[2];
        _initOperationDesc1();
    }

    /**
     * En el operation desc 1.
     */
    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarPago");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "numeroJustificante"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "documentoObligado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "idTramitacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "organismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatosPagoIM"));
        oper.setReturnClass(es.map.pasarelapago.service.DatosPagoIM.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "consultarPagoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault"),
                      "es.map.pasarelapago.service.IntegracionMixtaGenericException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaGenericException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault1"),
                      "es.map.pasarelapago.service.IntegracionMixtaNoPagadoException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaNoPagadoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault3"),
                      "es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaIncidenciaPagoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault2"),
                      "es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaPagoNotFoundException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearPagoPendiente");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "pagoPendiente"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "PagoPendiente"), es.map.pasarelapago.service.PagoPendiente.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "organismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "crearPagoPendienteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault4"),
                      "es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaIdTramitacionRepetidoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault"),
                      "es.map.pasarelapago.service.IntegracionMixtaGenericException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaGenericException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fault5"),
                      "es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException",
                      new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaNifJustificanteRepetidoException"), 
                      true
                     ));
        _operations[1] = oper;

    }

    /**
     * Crea una nueva integracion mixta soap binding stub.
     *
     * @throws AxisFault el axis fault
     */
    public IntegracionMixtaSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    /**
     * Crea una nueva integracion mixta soap binding stub.
     *
     * @param endpointURL el endpoint URL
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public IntegracionMixtaSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    /**
     * Crea una nueva integracion mixta soap binding stub.
     *
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public IntegracionMixtaSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/integracionMixta", "ListaDatosAdicionales");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.integracionMixta.ListaDatosAdicionales.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "ArrayOfDatoAdicional");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.DatoAdicional[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatoAdicional");
            qName2 = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatoAdicional");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.DatoAdicional.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatosPagoIM");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.DatosPagoIM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaGenericException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaGenericException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaIdTramitacionRepetidoException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaIncidenciaPagoException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaNifJustificanteRepetidoException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaNoPagadoException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaNoPagadoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaPagoNotFoundException");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "PagoPendiente");
            cachedSerQNames.add(qName);
            cls = es.map.pasarelapago.service.PagoPendiente.class;
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
     * @see es.map.pasarelapago.service.ServicioIntegracion#consultarPago(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public es.map.pasarelapago.service.DatosPagoIM consultarPago(java.lang.String numeroJustificante, java.lang.String documentoObligado, java.lang.String idTramitacion, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNoPagadoException, es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException, es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "consultarPago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroJustificante, documentoObligado, idTramitacion, organismo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.map.pasarelapago.service.DatosPagoIM) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.map.pasarelapago.service.DatosPagoIM) org.apache.axis.utils.JavaUtils.convert(_resp, es.map.pasarelapago.service.DatosPagoIM.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaGenericException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaGenericException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaNoPagadoException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaNoPagadoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.ServicioIntegracion#crearPagoPendiente(es.map.pasarelapago.service.PagoPendiente, java.lang.String)
     */
    public java.lang.String crearPagoPendiente(es.map.pasarelapago.service.PagoPendiente pagoPendiente, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "crearPagoPendiente"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pagoPendiente, organismo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaGenericException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaGenericException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException) {
              throw (es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}

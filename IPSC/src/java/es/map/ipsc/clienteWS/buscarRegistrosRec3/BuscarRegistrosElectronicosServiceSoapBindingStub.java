/**
 * BuscarRegistrosElectronicosServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.buscarRegistrosRec3;

/**
 * El Class BuscarRegistrosElectronicosServiceSoapBindingStub.
 */
public class BuscarRegistrosElectronicosServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.map.ipsc.clienteWS.buscarRegistrosRec3.BuscarRegistrosElectronicos {
    
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
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    /**
     * En el operation desc 1.
     */
    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarRegistro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "autenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "AutenticacionType"),es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "criteriosBusqueda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "CriteriosBusquedaType"), es.map.ipsc.clienteWS.buscarRegistrosRec3.CriteriosBusquedaType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "ArrayOf_tns1_JustificanteType"));
        oper.setReturnClass(es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "buscarRegistrosElectronicosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

    }

    /**
     * Crea una nueva buscar registros electronicos service soap binding stub.
     *
     * @throws AxisFault el axis fault
     */
    public BuscarRegistrosElectronicosServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    /**
     * Crea una nueva buscar registros electronicos service soap binding stub.
     *
     * @param endpointURL el endpoint URL
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public BuscarRegistrosElectronicosServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    /**
     * Crea una nueva buscar registros electronicos service soap binding stub.
     *
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public BuscarRegistrosElectronicosServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "ArrayOf_tns1_JustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "JustificanteType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "CriteriosBusquedaType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.buscarRegistrosRec3.CriteriosBusquedaType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "ArrayOf_tns1_DocumentoJustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "DocumentoJustificanteType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "ArrayOf_tns1_InteresadoJustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "InteresadoJustificanteType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "AutenticacionType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "DocumentoJustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "InteresadoJustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "JustificanteType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.JustificanteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "RespuestaType");
            cachedSerQNames.add(qName);
            cls = es.map.ipsc.clienteWS.registroRec3.type.RespuestaType.class;
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
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
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
     * @see es.map.ipsc.clienteWS.buscarRegistrosRec3.BuscarRegistrosElectronicos#buscarRegistro(es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType, es.map.ipsc.clienteWS.buscarRegistrosRec3.CriteriosBusquedaType)
     */
    public es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[] buscarRegistro(es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType autenticacion, es.map.ipsc.clienteWS.buscarRegistrosRec3.CriteriosBusquedaType criteriosBusqueda) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://busqueda.ws.rec2are.map.es", "buscarRegistro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {autenticacion, criteriosBusqueda});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

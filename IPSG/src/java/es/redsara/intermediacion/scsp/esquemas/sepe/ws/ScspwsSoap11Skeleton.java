package es.redsara.intermediacion.scsp.esquemas.sepe.ws;

/**
 * ScspwsSoap11Skeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class ScspwsSoap11Skeleton implements es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws, org.apache.axis.wsdl.Skeleton {
    
    /** el impl. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws impl;
    
    /** el my operations. */
    private static java.util.Map _myOperations = new java.util.Hashtable();
    
    /** el my operations list. */
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
     * Returns List of OperationDesc objects with this name.
     *
     * @param methodName el method name
     * @return el operation desc by name
     */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
     * Returns Collection of OperationDescs.
     *
     * @return el operation descs
     */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "PeticionSincrona"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">PeticionSincrona"), es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("peticionSincrona", _params, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Respuesta"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Respuesta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "peticionSincrona"));
        _oper.setSoapAction("peticionSincrona");
        _myOperationsList.add(_oper);
        if (_myOperations.get("peticionSincrona") == null) {
            _myOperations.put("peticionSincrona", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("peticionSincrona")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "PeticionAsincrona"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">PeticionAsincrona"), es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("peticionAsincrona", _params, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/confirmacionPeticion", "ConfirmacionPeticion"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/confirmacionPeticion", ">ConfirmacionPeticion"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "peticionAsincrona"));
        _oper.setSoapAction("peticionAsincrona");
        _myOperationsList.add(_oper);
        if (_myOperations.get("peticionAsincrona") == null) {
            _myOperations.put("peticionAsincrona", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("peticionAsincrona")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/solicitudRespuesta", "SolicitudRespuesta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/solicitudRespuesta", ">SolicitudRespuesta"), es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("solicitudRespuesta", _params, new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Respuesta"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Respuesta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "solicitudRespuesta"));
        _oper.setSoapAction("solicitudRespuesta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("solicitudRespuesta") == null) {
            _myOperations.put("solicitudRespuesta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("solicitudRespuesta")).add(_oper);
    }

    /**
     * Crea una nueva scspws soap 11 skeleton.
     */
    public ScspwsSoap11Skeleton() {
        this.impl = new es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsSoap11Impl();
    }

    /**
     * Crea una nueva scspws soap 11 skeleton.
     *
     * @param impl el impl
     */
    public ScspwsSoap11Skeleton(es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws impl) {
        this.impl = impl;
    }
    
    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona)
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona peticionSincrona) throws java.rmi.RemoteException
    {
        es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta ret = impl.peticionSincrona(peticionSincrona);
        return ret;
    }

    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona)
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona peticionAsincrona) throws java.rmi.RemoteException
    {
        es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion ret = impl.peticionAsincrona(peticionAsincrona);
        return ret;
    }

    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta)
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta solicitudRespuesta) throws java.rmi.RemoteException
    {
        es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta ret = impl.solicitudRespuesta(solicitudRespuesta);
        return ret;
    }

}


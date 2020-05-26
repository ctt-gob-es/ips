/**
 * PPServiceInterfaceSoap11Skeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class PPServiceInterfaceSoap11Skeleton.
 */
public class PPServiceInterfaceSoap11Skeleton implements ePago.gob.es.schemas.PPServiceInterface, org.apache.axis.wsdl.Skeleton {
    
    /** el impl. */
    private ePago.gob.es.schemas.PPServiceInterface impl;
    
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("hacerPago", _params, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPago"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("hacerPago") == null) {
            _myOperations.put("hacerPago", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("hacerPago")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "almacen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "Map"), ePago.gob.es.schemas.MapItem[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("hacerPagoYRegistro", _params, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoYRegistroReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoRegistroOut"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoYRegistro"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("hacerPagoYRegistro") == null) {
            _myOperations.put("hacerPagoYRegistro", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("hacerPagoYRegistro")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosNRCIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCIn"), ePago.gob.es.schemas.DefaultDatosNRCIn.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("verificarNRC", _params, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "verificarNRCReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCOut"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "verificarNRC"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("verificarNRC") == null) {
            _myOperations.put("verificarNRC", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("verificarNRC")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "modelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoTasa"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getJustificante", _params, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "getJustificanteReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "getJustificante"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getJustificante") == null) {
            _myOperations.put("getJustificante", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getJustificante")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarPago", _params, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPagoReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPago"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarPago") == null) {
            _myOperations.put("consultarPago", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarPago")).add(_oper);
    }

    /**
     * Crea una nueva PP service interface soap 11 skeleton.
     */
    public PPServiceInterfaceSoap11Skeleton() {
        this.impl = new ePago.gob.es.schemas.PPServiceInterfaceSoap11Impl();
    }

    /**
     * Crea una nueva PP service interface soap 11 skeleton.
     *
     * @param impl el impl
     */
    public PPServiceInterfaceSoap11Skeleton(ePago.gob.es.schemas.PPServiceInterface impl) {
        this.impl = impl;
    }
    
    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException
    {
        ePago.gob.es.schemas.DefaultDatosPagoOut ret = impl.hacerPago(datosPagoIn, idOrganismo);
        return ret;
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn, ePago.gob.es.schemas.MapItem[], int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, ePago.gob.es.schemas.MapItem[] almacen, int idOrganismo) throws java.rmi.RemoteException
    {
        ePago.gob.es.schemas.DefaultDatosPagoRegistroOut ret = impl.hacerPagoYRegistro(datosPagoIn, almacen, idOrganismo);
        return ret;
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn, int idOrganismo) throws java.rmi.RemoteException
    {
        ePago.gob.es.schemas.DefaultDatosNRCOut ret = impl.verificarNRC(datosNRCIn, idOrganismo);
        return ret;
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#getJustificante(java.lang.String, java.lang.String, int)
     */
    public java.lang.String getJustificante(java.lang.String modelo, java.lang.String codigoTasa, int idOrganismo) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getJustificante(modelo, codigoTasa, idOrganismo);
        return ret;
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException
    {
        ePago.gob.es.schemas.DefaultDatosPagoOut ret = impl.consultarPago(datosPagoIn, idOrganismo);
        return ret;
    }

}

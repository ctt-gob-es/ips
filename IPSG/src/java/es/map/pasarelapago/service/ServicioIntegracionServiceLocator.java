/**
 * ServicioIntegracionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;


/**
 * El Class ServicioIntegracionServiceLocator.
 */
public class ServicioIntegracionServiceLocator extends org.apache.axis.client.Service implements es.map.pasarelapago.service.ServicioIntegracionService {

    /**
     * Crea una nueva servicio integracion service locator.
     */
    public ServicioIntegracionServiceLocator() {
    }


    /**
     * Crea una nueva servicio integracion service locator.
     *
     * @param config el config
     */
    public ServicioIntegracionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva servicio integracion service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public ServicioIntegracionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el Integracion mixta address. */
    // Use to get a proxy class for IntegracionMixta
    private java.lang.String IntegracionMixta_address = "http://des-ppmptws.inter060.es/services/IntegracionMixta";

    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.ServicioIntegracionService#getIntegracionMixtaAddress()
     */
    public java.lang.String getIntegracionMixtaAddress() {
        return IntegracionMixta_address;
    }

    /** el Integracion mixta WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String IntegracionMixtaWSDDServiceName = "IntegracionMixta";

    /**
     * Obtiene el integracion mixta WSDD service name.
     *
     * @return el integracion mixta WSDD service name
     */
    public java.lang.String getIntegracionMixtaWSDDServiceName() {
        return IntegracionMixtaWSDDServiceName;
    }

    /**
     * Establece el integracion mixta WSDD service name.
     *
     * @param name el nuevo integracion mixta WSDD service name
     */
    public void setIntegracionMixtaWSDDServiceName(java.lang.String name) {
        IntegracionMixtaWSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.ServicioIntegracionService#getIntegracionMixta()
     */
    public es.map.pasarelapago.service.ServicioIntegracion getIntegracionMixta() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IntegracionMixta_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIntegracionMixta(endpoint);
    }

    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.ServicioIntegracionService#getIntegracionMixta(java.net.URL)
     */
    public es.map.pasarelapago.service.ServicioIntegracion getIntegracionMixta(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.map.pasarelapago.service.IntegracionMixtaSoapBindingStub _stub = new es.map.pasarelapago.service.IntegracionMixtaSoapBindingStub(portAddress, this);
            _stub.setPortName(getIntegracionMixtaWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el integracion mixta endpoint address.
     *
     * @param address el nuevo integracion mixta endpoint address
     */
    public void setIntegracionMixtaEndpointAddress(java.lang.String address) {
        IntegracionMixta_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     *
     * @param serviceEndpointInterface el service endpoint interface
     * @return el port
     * @throws ServiceException el service exception
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.map.pasarelapago.service.ServicioIntegracion.class.isAssignableFrom(serviceEndpointInterface)) {
                es.map.pasarelapago.service.IntegracionMixtaSoapBindingStub _stub = new es.map.pasarelapago.service.IntegracionMixtaSoapBindingStub(new java.net.URL(IntegracionMixta_address), this);
                _stub.setPortName(getIntegracionMixtaWSDDServiceName());
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
     *
     * @param portName el port name
     * @param serviceEndpointInterface el service endpoint interface
     * @return el port
     * @throws ServiceException el service exception
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IntegracionMixta".equals(inputPortName)) {
            return getIntegracionMixta();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getServiceName()
     */
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "ServicioIntegracionService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixta"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     *
     * @param portName el port name
     * @param address el address
     * @throws ServiceException el service exception
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IntegracionMixta".equals(portName)) {
            setIntegracionMixtaEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     *
     * @param portName el port name
     * @param address el address
     * @throws ServiceException el service exception
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

/**
 * ScspwsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.svdi.ws;

/**
 * El Class ScspwsServiceLocator.
 */
public class ScspwsServiceLocator extends org.apache.axis.client.Service implements es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsService {

    /**
     * Crea una nueva scspws service locator.
     */
    public ScspwsServiceLocator() {
    }


    /**
     * Crea una nueva scspws service locator.
     *
     * @param config el config
     */
    public ScspwsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva scspws service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public ScspwsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el scspws soap 11 address. */
    // Use to get a proxy class for scspwsSoap11
    private java.lang.String scspwsSoap11_address = "http://localhost:8080/scsp-ws/ws";

    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsService#getscspwsSoap11Address()
     */
    public java.lang.String getscspwsSoap11Address() {
        return scspwsSoap11_address;
    }

    /** el scspws soap 11 WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String scspwsSoap11WSDDServiceName = "scspwsSoap11";

    /**
     * Obtiene el scspws soap 11 WSDD service name.
     *
     * @return el scspws soap 11 WSDD service name
     */
    public java.lang.String getscspwsSoap11WSDDServiceName() {
        return scspwsSoap11WSDDServiceName;
    }

    /**
     * Establece el scspws soap 11 WSDD service name.
     *
     * @param name el nuevo scspws soap 11 WSDD service name
     */
    public void setscspwsSoap11WSDDServiceName(java.lang.String name) {
        scspwsSoap11WSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsService#getscspwsSoap11()
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.ws.Scspws getscspwsSoap11() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(scspwsSoap11_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getscspwsSoap11(endpoint);
    }

    /* (non-Javadoc)
     * @see es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsService#getscspwsSoap11(java.net.URL)
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.ws.Scspws getscspwsSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsSoap11Stub _stub = new es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsSoap11Stub(portAddress, this);
            _stub.setPortName(getscspwsSoap11WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el scspws soap 11 endpoint address.
     *
     * @param address el nuevo scspws soap 11 endpoint address
     */
    public void setscspwsSoap11EndpointAddress(java.lang.String address) {
        scspwsSoap11_address = address;
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
            if (es.redsara.intermediacion.scsp.esquemas.svdi.ws.Scspws.class.isAssignableFrom(serviceEndpointInterface)) {
                es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsSoap11Stub _stub = new es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsSoap11Stub(new java.net.URL(scspwsSoap11_address), this);
                _stub.setPortName(getscspwsSoap11WSDDServiceName());
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
        if ("scspwsSoap11".equals(inputPortName)) {
            return getscspwsSoap11();
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
        return new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws", "scspwsService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws", "scspwsSoap11"));
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
        
if ("scspwsSoap11".equals(portName)) {
            setscspwsSoap11EndpointAddress(address);
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

/**
 * PPServiceInterfaceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class PPServiceInterfaceServiceLocator.
 */
public class PPServiceInterfaceServiceLocator extends org.apache.axis.client.Service implements ePago.gob.es.schemas.PPServiceInterfaceService {

    /**
     * Crea una nueva PP service interface service locator.
     */
    public PPServiceInterfaceServiceLocator() {
    }


    /**
     * Crea una nueva PP service interface service locator.
     *
     * @param config el config
     */
    public PPServiceInterfaceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva PP service interface service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public PPServiceInterfaceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el PP service interface soap 11 address. */
    // Use to get a proxy class for PPServiceInterfaceSoap11
    private java.lang.String PPServiceInterfaceSoap11_address = "https://pre-epago.redsara.es:443/pp/pasarela/";

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterfaceService#getPPServiceInterfaceSoap11Address()
     */
    public java.lang.String getPPServiceInterfaceSoap11Address() {
        return PPServiceInterfaceSoap11_address;
    }

    /** el PP service interface soap 11 WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String PPServiceInterfaceSoap11WSDDServiceName = "PPServiceInterfaceSoap11";

    /**
     * Obtiene el PP service interface soap 11 WSDD service name.
     *
     * @return el PP service interface soap 11 WSDD service name
     */
    public java.lang.String getPPServiceInterfaceSoap11WSDDServiceName() {
        return PPServiceInterfaceSoap11WSDDServiceName;
    }

    /**
     * Establece el PP service interface soap 11 WSDD service name.
     *
     * @param name el nuevo PP service interface soap 11 WSDD service name
     */
    public void setPPServiceInterfaceSoap11WSDDServiceName(java.lang.String name) {
        PPServiceInterfaceSoap11WSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterfaceService#getPPServiceInterfaceSoap11()
     */
    public ePago.gob.es.schemas.PPServiceInterface getPPServiceInterfaceSoap11() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PPServiceInterfaceSoap11_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPPServiceInterfaceSoap11(endpoint);
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterfaceService#getPPServiceInterfaceSoap11(java.net.URL)
     */
    public ePago.gob.es.schemas.PPServiceInterface getPPServiceInterfaceSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub _stub = new ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub(portAddress, this);
            _stub.setPortName(getPPServiceInterfaceSoap11WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el PP service interface soap 11 endpoint address.
     *
     * @param address el nuevo PP service interface soap 11 endpoint address
     */
    public void setPPServiceInterfaceSoap11EndpointAddress(java.lang.String address) {
        PPServiceInterfaceSoap11_address = address;
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
            if (ePago.gob.es.schemas.PPServiceInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub _stub = new ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub(new java.net.URL(PPServiceInterfaceSoap11_address), this);
                _stub.setPortName(getPPServiceInterfaceSoap11WSDDServiceName());
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
        if ("PPServiceInterfaceSoap11".equals(inputPortName)) {
            return getPPServiceInterfaceSoap11();
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
        return new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "PPServiceInterfaceService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "PPServiceInterfaceSoap11"));
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
        
if ("PPServiceInterfaceSoap11".equals(portName)) {
            setPPServiceInterfaceSoap11EndpointAddress(address);
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

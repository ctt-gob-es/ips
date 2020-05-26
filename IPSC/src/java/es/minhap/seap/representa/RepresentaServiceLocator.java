/**
 * RepresentaServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class RepresentaServiceLocator.
 */
public class RepresentaServiceLocator extends org.apache.axis.client.Service implements es.minhap.seap.representa.RepresentaService {

    /**
     * Crea una nueva representa service locator.
     */
    public RepresentaServiceLocator() {
    }


    /**
     * Crea una nueva representa service locator.
     *
     * @param config el config
     */
    public RepresentaServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva representa service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public RepresentaServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el Representa port address. */
    // Use to get a proxy class for RepresentaPort
    private java.lang.String RepresentaPort_address = "http://pre-representa.redsara.es/Representa/services/comprobarColectivos";

    /* (non-Javadoc)
     * @see es.minhap.seap.representa.RepresentaService#getRepresentaPortAddress()
     */
    public java.lang.String getRepresentaPortAddress() {
        return RepresentaPort_address;
    }

    /** el Representa port WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String RepresentaPortWSDDServiceName = "RepresentaPort";

    /**
     * Obtiene el representa port WSDD service name.
     *
     * @return el representa port WSDD service name
     */
    public java.lang.String getRepresentaPortWSDDServiceName() {
        return RepresentaPortWSDDServiceName;
    }

    /**
     * Establece el representa port WSDD service name.
     *
     * @param name el nuevo representa port WSDD service name
     */
    public void setRepresentaPortWSDDServiceName(java.lang.String name) {
        RepresentaPortWSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.minhap.seap.representa.RepresentaService#getRepresentaPort()
     */
    public es.minhap.seap.representa.Representa getRepresentaPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RepresentaPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRepresentaPort(endpoint);
    }

    /* (non-Javadoc)
     * @see es.minhap.seap.representa.RepresentaService#getRepresentaPort(java.net.URL)
     */
    public es.minhap.seap.representa.Representa getRepresentaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.minhap.seap.representa.RepresentaSoapBindingStub _stub = new es.minhap.seap.representa.RepresentaSoapBindingStub(portAddress, this);
            _stub.setPortName(getRepresentaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el representa port endpoint address.
     *
     * @param address el nuevo representa port endpoint address
     */
    public void setRepresentaPortEndpointAddress(java.lang.String address) {
        RepresentaPort_address = address;
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
            if (es.minhap.seap.representa.Representa.class.isAssignableFrom(serviceEndpointInterface)) {
                es.minhap.seap.representa.RepresentaSoapBindingStub _stub = new es.minhap.seap.representa.RepresentaSoapBindingStub(new java.net.URL(RepresentaPort_address), this);
                _stub.setPortName(getRepresentaPortWSDDServiceName());
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
        if ("RepresentaPort".equals(inputPortName)) {
            return getRepresentaPort();
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
        return new javax.xml.namespace.QName("http://representa.seap.minhap.es", "RepresentaService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "RepresentaPort"));
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
        
if ("RepresentaPort".equals(portName)) {
            setRepresentaPortEndpointAddress(address);
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

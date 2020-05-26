/**
 * RegistrarServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registrarRec3;

/**
 * El Class RegistrarServiceLocator.
 */
public class RegistrarServiceLocator extends org.apache.axis.client.Service implements es.map.ipsc.clienteWS.registrarRec3.RegistrarService {

    /**
     * Crea una nueva registrar service locator.
     */
    public RegistrarServiceLocator() {
    }


    /**
     * Crea una nueva registrar service locator.
     *
     * @param config el config
     */
    public RegistrarServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva registrar service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public RegistrarServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el Registrar service address. */
    // Use to get a proxy class for RegistrarService
    private java.lang.String RegistrarService_address = "http://pre-rec2ws.redsara.es/registro2/services/RegistrarService";

    /* (non-Javadoc)
     * @see es.map.ipsc.clienteWS.registrarRec3.RegistrarService#getRegistrarServiceAddress()
     */
    public java.lang.String getRegistrarServiceAddress() {
        return RegistrarService_address;
    }

    /** el Registrar service WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String RegistrarServiceWSDDServiceName = "RegistrarService";

    /**
     * Obtiene el registrar service WSDD service name.
     *
     * @return el registrar service WSDD service name
     */
    public java.lang.String getRegistrarServiceWSDDServiceName() {
        return RegistrarServiceWSDDServiceName;
    }

    /**
     * Establece el registrar service WSDD service name.
     *
     * @param name el nuevo registrar service WSDD service name
     */
    public void setRegistrarServiceWSDDServiceName(java.lang.String name) {
        RegistrarServiceWSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.map.ipsc.clienteWS.registrarRec3.RegistrarService#getRegistrarService()
     */
    public es.map.ipsc.clienteWS.registrarRec3.RegistrarElectronicosService getRegistrarService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegistrarService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegistrarService(endpoint);
    }

    /* (non-Javadoc)
     * @see es.map.ipsc.clienteWS.registrarRec3.RegistrarService#getRegistrarService(java.net.URL)
     */
    public es.map.ipsc.clienteWS.registrarRec3.RegistrarElectronicosService getRegistrarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceSoapBindingStub _stub = new es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getRegistrarServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el registrar service endpoint address.
     *
     * @param address el nuevo registrar service endpoint address
     */
    public void setRegistrarServiceEndpointAddress(java.lang.String address) {
        RegistrarService_address = address;
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
            if (es.map.ipsc.clienteWS.registrarRec3.RegistrarElectronicosService.class.isAssignableFrom(serviceEndpointInterface)) {
            	es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceSoapBindingStub _stub = new es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceSoapBindingStub(new java.net.URL(RegistrarService_address), this);
                _stub.setPortName(getRegistrarServiceWSDDServiceName());
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
        if ("RegistrarService".equals(inputPortName)) {
            return getRegistrarService();
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
        return new javax.xml.namespace.QName("http://registrar.ws2.rec2are.map.es", "RegistrarService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://registrar.ws2.rec2are.map.es", "RegistrarService"));
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
        
if ("RegistrarService".equals(portName)) {
            setRegistrarServiceEndpointAddress(address);
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

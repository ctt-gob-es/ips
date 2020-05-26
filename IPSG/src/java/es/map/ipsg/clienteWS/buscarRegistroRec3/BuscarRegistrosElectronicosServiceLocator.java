/**
 * BuscarRegistrosElectronicosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.buscarRegistroRec3;

/**
 * El Class BuscarRegistrosElectronicosServiceLocator.
 */
public class BuscarRegistrosElectronicosServiceLocator extends org.apache.axis.client.Service implements es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosService {

    /**
     * Crea una nueva buscar registros electronicos service locator.
     */
    public BuscarRegistrosElectronicosServiceLocator() {
    }


    /**
     * Crea una nueva buscar registros electronicos service locator.
     *
     * @param config el config
     */
    public BuscarRegistrosElectronicosServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva buscar registros electronicos service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public BuscarRegistrosElectronicosServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el Buscar registros electronicos service address. */
    // Use to get a proxy class for BuscarRegistrosElectronicosService
    private java.lang.String BuscarRegistrosElectronicosService_address = "http://pre-rec2ws.redsara.es/registro2/services/BuscarRegistrosElectronicosService";

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosService#getBuscarRegistrosElectronicosServiceAddress()
     */
    public java.lang.String getBuscarRegistrosElectronicosServiceAddress() {
        return BuscarRegistrosElectronicosService_address;
    }

    /** el Buscar registros electronicos service WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String BuscarRegistrosElectronicosServiceWSDDServiceName = "BuscarRegistrosElectronicosService";

    /**
     * Obtiene el buscar registros electronicos service WSDD service name.
     *
     * @return el buscar registros electronicos service WSDD service name
     */
    public java.lang.String getBuscarRegistrosElectronicosServiceWSDDServiceName() {
        return BuscarRegistrosElectronicosServiceWSDDServiceName;
    }

    /**
     * Establece el buscar registros electronicos service WSDD service name.
     *
     * @param name el nuevo buscar registros electronicos service WSDD service name
     */
    public void setBuscarRegistrosElectronicosServiceWSDDServiceName(java.lang.String name) {
        BuscarRegistrosElectronicosServiceWSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosService#getBuscarRegistrosElectronicosService()
     */
    public es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos getBuscarRegistrosElectronicosService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BuscarRegistrosElectronicosService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBuscarRegistrosElectronicosService(endpoint);
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosService#getBuscarRegistrosElectronicosService(java.net.URL)
     */
    public es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos getBuscarRegistrosElectronicosService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub _stub = new es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBuscarRegistrosElectronicosServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el buscar registros electronicos service endpoint address.
     *
     * @param address el nuevo buscar registros electronicos service endpoint address
     */
    public void setBuscarRegistrosElectronicosServiceEndpointAddress(java.lang.String address) {
        BuscarRegistrosElectronicosService_address = address;
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
            if (es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos.class.isAssignableFrom(serviceEndpointInterface)) {
            	es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub _stub = new es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub(new java.net.URL(BuscarRegistrosElectronicosService_address), this);
                _stub.setPortName(getBuscarRegistrosElectronicosServiceWSDDServiceName());
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
        if ("BuscarRegistrosElectronicosService".equals(inputPortName)) {
            return getBuscarRegistrosElectronicosService();
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
        return new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "BuscarRegistrosElectronicosService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "BuscarRegistrosElectronicosService"));
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
        
if ("BuscarRegistrosElectronicosService".equals(portName)) {
            setBuscarRegistrosElectronicosServiceEndpointAddress(address);
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

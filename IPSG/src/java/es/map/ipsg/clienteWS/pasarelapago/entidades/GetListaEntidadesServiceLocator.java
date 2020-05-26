/**
 * GetListaEntidadesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Class GetListaEntidadesServiceLocator.
 */
public class GetListaEntidadesServiceLocator extends org.apache.axis.client.Service implements es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidadesService {

    /**
     * Crea una nueva obtiene el lista entidades service locator.
     */
    public GetListaEntidadesServiceLocator() {
    }


    /**
     * Crea una nueva obtiene el lista entidades service locator.
     *
     * @param config el config
     */
    public GetListaEntidadesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     * Crea una nueva obtiene el lista entidades service locator.
     *
     * @param wsdlLoc el wsdl loc
     * @param sName el s name
     * @throws ServiceException el service exception
     */
    public GetListaEntidadesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    /** el Entidades address. */
    // Use to get a proxy class for Entidades
    private java.lang.String Entidades_address = "http://des-ppmapejemplo.inter060.es/services/Entidades";

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidadesService#getEntidadesAddress()
     */
    public java.lang.String getEntidadesAddress() {
        return Entidades_address;
    }

    /** el Entidades WSDD service name. */
    // The WSDD service name defaults to the port name.
    private java.lang.String EntidadesWSDDServiceName = "Entidades";

    /**
     * Obtiene el entidades WSDD service name.
     *
     * @return el entidades WSDD service name
     */
    public java.lang.String getEntidadesWSDDServiceName() {
        return EntidadesWSDDServiceName;
    }

    /**
     * Establece el entidades WSDD service name.
     *
     * @param name el nuevo entidades WSDD service name
     */
    public void setEntidadesWSDDServiceName(java.lang.String name) {
        EntidadesWSDDServiceName = name;
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidadesService#getEntidades()
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getEntidades() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Entidades_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEntidades(endpoint);
    }

    /* (non-Javadoc)
     * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidadesService#getEntidades(java.net.URL)
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getEntidades(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.map.ipsg.clienteWS.pasarelapago.entidades.EntidadesSoapBindingStub _stub = new es.map.ipsg.clienteWS.pasarelapago.entidades.EntidadesSoapBindingStub(portAddress, this);
            _stub.setPortName(getEntidadesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * Establece el entidades endpoint address.
     *
     * @param address el nuevo entidades endpoint address
     */
    public void setEntidadesEndpointAddress(java.lang.String address) {
        Entidades_address = address;
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
            if (es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades.class.isAssignableFrom(serviceEndpointInterface)) {
                es.map.ipsg.clienteWS.pasarelapago.entidades.EntidadesSoapBindingStub _stub = new es.map.ipsg.clienteWS.pasarelapago.entidades.EntidadesSoapBindingStub(new java.net.URL(Entidades_address), this);
                _stub.setPortName(getEntidadesWSDDServiceName());
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
        if ("Entidades".equals(inputPortName)) {
            return getEntidades();
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
        return new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "GetListaEntidadesService");
    }

    /** el ports. */
    private java.util.HashSet ports = null;

    /* (non-Javadoc)
     * @see org.apache.axis.client.Service#getPorts()
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "Entidades"));
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
        
if ("Entidades".equals(portName)) {
            setEntidadesEndpointAddress(address);
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

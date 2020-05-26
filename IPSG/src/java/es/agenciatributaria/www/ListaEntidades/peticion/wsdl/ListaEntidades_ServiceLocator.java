/**
 * ListaEntidades_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.ListaEntidades.peticion.wsdl;

public class ListaEntidades_ServiceLocator extends org.apache.axis.client.Service implements es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_Service {

    public ListaEntidades_ServiceLocator() {
    }


    public ListaEntidades_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ListaEntidades_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ListaEntidades
    private java.lang.String ListaEntidades_address = "https://www2.agenciatributaria.gob.es/L/NCD1NCDLP0DL";

    public java.lang.String getListaEntidadesAddress() {
        return ListaEntidades_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ListaEntidadesWSDDServiceName = "ListaEntidades";

    public java.lang.String getListaEntidadesWSDDServiceName() {
        return ListaEntidadesWSDDServiceName;
    }

    public void setListaEntidadesWSDDServiceName(java.lang.String name) {
        ListaEntidadesWSDDServiceName = name;
    }

    public es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType getListaEntidades() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ListaEntidades_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getListaEntidades(endpoint);
    }

    public es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType getListaEntidades(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_BindingStub _stub = new es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_BindingStub(portAddress, this);
            _stub.setPortName(getListaEntidadesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setListaEntidadesEndpointAddress(java.lang.String address) {
        ListaEntidades_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_BindingStub _stub = new es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_BindingStub(new java.net.URL(ListaEntidades_address), this);
                _stub.setPortName(getListaEntidadesWSDDServiceName());
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
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ListaEntidades".equals(inputPortName)) {
            return getListaEntidades();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.agenciatributaria.es/ListaEntidades/peticion/wsdl", "ListaEntidades");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.agenciatributaria.es/ListaEntidades/peticion/wsdl", "ListaEntidades"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ListaEntidades".equals(portName)) {
            setListaEntidadesEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

package es.agenciatributaria.www.ListaEntidades.peticion.wsdl;

import java.rmi.RemoteException;

import es.agenciatributaria.www.peticion.PeticionListaEntidades;
import es.agenciatributaria.www.respuesta.RespuestaListaEntidades;

public class ListaEntidadesProxy implements es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType {
  private String _endpoint = null;
  private es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType listaEntidades_PortType = null;
  
  public ListaEntidadesProxy() {
    _initListaEntidadesProxy();
  }
  
  public ListaEntidadesProxy(String endpoint) {
    _endpoint = endpoint;
    _initListaEntidadesProxy();
  }
  
  private void _initListaEntidadesProxy() {
    try {
      listaEntidades_PortType = (new es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_ServiceLocator()).getListaEntidades();
      if (listaEntidades_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)listaEntidades_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)listaEntidades_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (listaEntidades_PortType != null)
      ((javax.xml.rpc.Stub)listaEntidades_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType getListaEntidades_PortType() {
    if (listaEntidades_PortType == null)
      _initListaEntidadesProxy();
    return listaEntidades_PortType;
  }

@Override
public RespuestaListaEntidades listaEntidades(PeticionListaEntidades peticion) throws RemoteException {
	// TODO Auto-generated method stub
	return listaEntidades_PortType.listaEntidades(peticion);
}
  
  
}
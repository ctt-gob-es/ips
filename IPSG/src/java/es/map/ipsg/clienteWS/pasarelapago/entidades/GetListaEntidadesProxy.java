package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Class GetListaEntidadesProxy.
 */
public class GetListaEntidadesProxy implements es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el get lista entidades. */
  private es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getListaEntidades = null;
  
  /**
   * Crea una nueva obtiene el lista entidades proxy.
   */
  public GetListaEntidadesProxy() {
    _initGetListaEntidadesProxy();
  }
  
  /**
   * Crea una nueva obtiene el lista entidades proxy.
   *
   * @param endpoint el endpoint
   */
  public GetListaEntidadesProxy(String endpoint) {
    _endpoint = endpoint;
    _initGetListaEntidadesProxy();
  }
  
  /**
   * En el obtiene el lista entidades proxy.
   */
  private void _initGetListaEntidadesProxy() {
    try {
      getListaEntidades = (new es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidadesServiceLocator()).getEntidades();
      if (getListaEntidades != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)getListaEntidades)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)getListaEntidades)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  /**
   * Obtiene el endpoint.
   *
   * @return el endpoint
   */
  public String getEndpoint() {
    return _endpoint;
  }
  
  /**
   * Establece el endpoint.
   *
   * @param endpoint el nuevo endpoint
   */
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (getListaEntidades != null)
      ((javax.xml.rpc.Stub)getListaEntidades)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el obtiene el lista entidades.
   *
   * @return el obtiene el lista entidades
   */
  public es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getGetListaEntidades() {
    if (getListaEntidades == null)
      _initGetListaEntidadesProxy();
    return getListaEntidades;
  }
  
  /* (non-Javadoc)
   * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticion()
   */
  public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticion() throws java.rmi.RemoteException{
    if (getListaEntidades == null)
      _initGetListaEntidadesProxy();
    return getListaEntidades.peticion();
  }
  
  /* (non-Javadoc)
   * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticionACuenta()
   */
  public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionACuenta() throws java.rmi.RemoteException{
    if (getListaEntidades == null)
      _initGetListaEntidadesProxy();
    return getListaEntidades.peticionACuenta();
  }
  
  /* (non-Javadoc)
   * @see es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades#peticionConTarjeta()
   */
  public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionConTarjeta() throws java.rmi.RemoteException{
    if (getListaEntidades == null)
      _initGetListaEntidadesProxy();
    return getListaEntidades.peticionConTarjeta();
  }
  
  
}
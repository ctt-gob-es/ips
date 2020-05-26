package es.map.ipsg.clienteWS.buscarRegistroRec3;

/**
 * El Class BuscarRegistrosElectronicosProxy.
 */
public class BuscarRegistrosElectronicosProxy implements es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el buscar registros electronicos. */
  private es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos buscarRegistrosElectronicos = null;
  
  /**
   * Crea una nueva buscar registros electronicos proxy.
   */
  public BuscarRegistrosElectronicosProxy() {
    _initBuscarRegistrosElectronicosProxy();
  }
  
  /**
   * Crea una nueva buscar registros electronicos proxy.
   *
   * @param endpoint el endpoint
   */
  public BuscarRegistrosElectronicosProxy(String endpoint) {
    _endpoint = endpoint;
    _initBuscarRegistrosElectronicosProxy();
  }
  
  /**
   * En el buscar registros electronicos proxy.
   */
  private void _initBuscarRegistrosElectronicosProxy() {
    try {
      buscarRegistrosElectronicos = (new es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceLocator()).getBuscarRegistrosElectronicosService();
      if (buscarRegistrosElectronicos != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)buscarRegistrosElectronicos)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)buscarRegistrosElectronicos)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (buscarRegistrosElectronicos != null)
      ((javax.xml.rpc.Stub)buscarRegistrosElectronicos)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el buscar registros electronicos.
   *
   * @return el buscar registros electronicos
   */
  public es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos getBuscarRegistrosElectronicos() {
    if (buscarRegistrosElectronicos == null)
      _initBuscarRegistrosElectronicosProxy();
    return buscarRegistrosElectronicos;
  }
  
  /* (non-Javadoc)
   * @see es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos#buscarRegistro(es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType, es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType)
   */
  public es.map.ipsg.clienteWS.registroRec3.type.JustificanteType[] buscarRegistro(es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType autenticacion, es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType criteriosBusqueda) throws java.rmi.RemoteException{
    if (buscarRegistrosElectronicos == null)
      _initBuscarRegistrosElectronicosProxy();
    return buscarRegistrosElectronicos.buscarRegistro(autenticacion, criteriosBusqueda);
  }
  
  
}
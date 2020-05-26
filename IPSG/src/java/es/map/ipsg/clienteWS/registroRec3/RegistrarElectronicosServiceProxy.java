package es.map.ipsg.clienteWS.registroRec3;

/**
 * El Class RegistrarElectronicosServiceProxy.
 */
public class RegistrarElectronicosServiceProxy implements es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosService {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el registrar electronicos service. */
  private es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosService registrarElectronicosService = null;
  
  /**
   * Crea una nueva registrar electronicos service proxy.
   */
  public RegistrarElectronicosServiceProxy() {
    _initRegistrarElectronicosServiceProxy();
  }
  
  /**
   * Crea una nueva registrar electronicos service proxy.
   *
   * @param endpoint el endpoint
   */
  public RegistrarElectronicosServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrarElectronicosServiceProxy();
  }
  
  /**
   * En el registrar electronicos service proxy.
   */
  private void _initRegistrarElectronicosServiceProxy() {
    try {
      registrarElectronicosService = (new es.map.ipsg.clienteWS.registroRec3.RegistrarServiceLocator()).getRegistrarService();
      if (registrarElectronicosService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registrarElectronicosService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registrarElectronicosService)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (registrarElectronicosService != null)
      ((javax.xml.rpc.Stub)registrarElectronicosService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el registrar electronicos service.
   *
   * @return el registrar electronicos service
   */
  public es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosService getRegistrarElectronicosService() {
    if (registrarElectronicosService == null)
      _initRegistrarElectronicosServiceProxy();
    return registrarElectronicosService;
  }
  
  /* (non-Javadoc)
   * @see es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosService#registrar(es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType, es.map.ipsg.clienteWS.registroRec3.RegistroType)
   */
  public es.map.ipsg.clienteWS.registroRec3.type.JustificanteType registrar(es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType autenticacion, es.map.ipsg.clienteWS.registroRec3.RegistroType registro) throws java.rmi.RemoteException{
    if (registrarElectronicosService == null)
      _initRegistrarElectronicosServiceProxy();
    return registrarElectronicosService.registrar(autenticacion, registro);
  }
  
  
}
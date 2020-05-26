package es.minhap.seap.representa;

/**
 * El Class RepresentaProxy.
 */
public class RepresentaProxy implements es.minhap.seap.representa.Representa {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el representa. */
  private es.minhap.seap.representa.Representa representa = null;
  
  /**
   * Crea una nueva representa proxy.
   */
  public RepresentaProxy() {
    _initRepresentaProxy();
  }
  
  /**
   * Crea una nueva representa proxy.
   *
   * @param endpoint el endpoint
   */
  public RepresentaProxy(String endpoint) {
    _endpoint = endpoint;
    _initRepresentaProxy();
  }
  
  /**
   * En el representa proxy.
   */
  private void _initRepresentaProxy() {
    try {
      representa = (new es.minhap.seap.representa.RepresentaServiceLocator()).getRepresentaPort();
      if (representa != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)representa)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)representa)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (representa != null)
      ((javax.xml.rpc.Stub)representa)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el representa.
   *
   * @return el representa
   */
  public es.minhap.seap.representa.Representa getRepresenta() {
    if (representa == null)
      _initRepresentaProxy();
    return representa;
  }
  
  /* (non-Javadoc)
   * @see es.minhap.seap.representa.Representa#comprobarColectivos(es.minhap.seap.representa.RepresentaRequest)
   */
  public es.minhap.seap.representa.RepresentaResponse comprobarColectivos(es.minhap.seap.representa.RepresentaRequest representaRequest) throws java.rmi.RemoteException{
    if (representa == null)
      _initRepresentaProxy();
    return representa.comprobarColectivos(representaRequest);
  }
  
  
}
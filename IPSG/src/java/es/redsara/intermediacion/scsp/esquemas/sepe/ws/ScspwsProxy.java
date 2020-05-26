package es.redsara.intermediacion.scsp.esquemas.sepe.ws;

/**
 * El Class ScspwsProxy.
 */
public class ScspwsProxy implements es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el scspws. */
  private es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws scspws = null;
  
  /**
   * Crea una nueva scspws proxy.
   */
  public ScspwsProxy() {
    _initScspwsProxy();
  }
  
  /**
   * Crea una nueva scspws proxy.
   *
   * @param endpoint el endpoint
   */
  public ScspwsProxy(String endpoint) {
    _endpoint = endpoint;
    _initScspwsProxy();
  }
  
  /**
   * En el scspws proxy.
   */
  private void _initScspwsProxy() {
    try {
      scspws = (new es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsServiceLocator()).getscspwsSoap11();
      if (scspws != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)scspws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)scspws)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (scspws != null)
      ((javax.xml.rpc.Stub)scspws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el scspws.
   *
   * @return el scspws
   */
  public es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws getScspws() {
    if (scspws == null)
      _initScspwsProxy();
    return scspws;
  }
  
  /* (non-Javadoc)
   * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona)
   */
  public es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona peticionSincrona) throws java.rmi.RemoteException{
    if (scspws == null)
      _initScspwsProxy();
    return scspws.peticionSincrona(peticionSincrona);
  }
  	  
	  /* (non-Javadoc)
  	 * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona)
  	 */
  	public es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona peticionAsincrona) throws java.rmi.RemoteException{
		    if (scspws == null)
		        _initScspwsProxy();
	    return scspws.peticionAsincrona(peticionAsincrona);
	  }
	  
	  /* (non-Javadoc)
  	 * @see es.redsara.intermediacion.scsp.esquemas.sepe.ws.Scspws#solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta)
  	 */
  	public es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta solicitudRespuesta) throws java.rmi.RemoteException{
		    if (scspws == null)
		        _initScspwsProxy();
	    return scspws.solicitudRespuesta(solicitudRespuesta);
	  }
  
}
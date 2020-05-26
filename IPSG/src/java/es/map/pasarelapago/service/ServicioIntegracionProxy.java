package es.map.pasarelapago.service;

/**
 * El Class ServicioIntegracionProxy.
 */
public class ServicioIntegracionProxy implements es.map.pasarelapago.service.ServicioIntegracion {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el servicio integracion. */
  private es.map.pasarelapago.service.ServicioIntegracion servicioIntegracion = null;
  
  /**
   * Crea una nueva servicio integracion proxy.
   */
  public ServicioIntegracionProxy() {
    _initServicioIntegracionProxy();
  }
  
  /**
   * Crea una nueva servicio integracion proxy.
   *
   * @param endpoint el endpoint
   */
  public ServicioIntegracionProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioIntegracionProxy();
  }
  
  /**
   * En el servicio integracion proxy.
   */
  private void _initServicioIntegracionProxy() {
    try {
      servicioIntegracion = (new es.map.pasarelapago.service.ServicioIntegracionServiceLocator()).getIntegracionMixta();
      if (servicioIntegracion != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioIntegracion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioIntegracion)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (servicioIntegracion != null)
      ((javax.xml.rpc.Stub)servicioIntegracion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el servicio integracion.
   *
   * @return el servicio integracion
   */
  public es.map.pasarelapago.service.ServicioIntegracion getServicioIntegracion() {
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    return servicioIntegracion;
  }
  
  /* (non-Javadoc)
   * @see es.map.pasarelapago.service.ServicioIntegracion#consultarPago(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  public es.map.pasarelapago.service.DatosPagoIM consultarPago(java.lang.String numeroJustificante, java.lang.String documentoObligado, java.lang.String idTramitacion, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNoPagadoException, es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException, es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException{
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    return servicioIntegracion.consultarPago(numeroJustificante, documentoObligado, idTramitacion, organismo);
  }
  
  /* (non-Javadoc)
   * @see es.map.pasarelapago.service.ServicioIntegracion#crearPagoPendiente(es.map.pasarelapago.service.PagoPendiente, java.lang.String)
   */
  public java.lang.String crearPagoPendiente(es.map.pasarelapago.service.PagoPendiente pagoPendiente, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException{
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    return servicioIntegracion.crearPagoPendiente(pagoPendiente, organismo);
  }
  
  
}
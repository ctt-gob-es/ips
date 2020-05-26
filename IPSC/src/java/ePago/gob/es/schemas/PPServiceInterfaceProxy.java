package ePago.gob.es.schemas;

/**
 * El Class PPServiceInterfaceProxy.
 */
public class PPServiceInterfaceProxy implements ePago.gob.es.schemas.PPServiceInterface {
  
  /** el endpoint. */
  private String _endpoint = null;
  
  /** el p P service interface. */
  private ePago.gob.es.schemas.PPServiceInterface pPServiceInterface = null;
  
  /**
   * Crea una nueva PP service interface proxy.
   */
  public PPServiceInterfaceProxy() {
    _initPPServiceInterfaceProxy();
  }
  
  /**
   * Crea una nueva PP service interface proxy.
   *
   * @param endpoint el endpoint
   */
  public PPServiceInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initPPServiceInterfaceProxy();
  }
  
  /**
   * En el PP service interface proxy.
   */
  private void _initPPServiceInterfaceProxy() {
    try {
      pPServiceInterface = (new ePago.gob.es.schemas.PPServiceInterfaceServiceLocator()).getPPServiceInterfaceSoap11();
      if (pPServiceInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pPServiceInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pPServiceInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (pPServiceInterface != null)
      ((javax.xml.rpc.Stub)pPServiceInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  /**
   * Obtiene el PP service interface.
   *
   * @return el PP service interface
   */
  public ePago.gob.es.schemas.PPServiceInterface getPPServiceInterface() {
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface;
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn, ePago.gob.es.schemas.MapItem[], int)
   */
  public ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, ePago.gob.es.schemas.MapItem[] almacen, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.hacerPagoYRegistro(datosPagoIn, almacen, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn, int)
   */
  public ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.verificarNRC(datosNRCIn, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#consultarPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn, int)
   */
  public ePago.gob.es.schemas.DefaultDatosPagoClaveOut consultarPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.consultarPagoClave(datosPagoClaveIn, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
   */
  public ePago.gob.es.schemas.DefaultDatosPagoOut hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.hacerPago(datosPagoIn, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
   */
  public ePago.gob.es.schemas.DefaultDatosPagoOut consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.consultarPago(datosPagoIn, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#hacerPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn, int)
   */
  public ePago.gob.es.schemas.DefaultDatosPagoClaveOut hacerPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.hacerPagoClave(datosPagoClaveIn, idOrganismo);
  }
  
  /* (non-Javadoc)
   * @see ePago.gob.es.schemas.PPServiceInterface#getJustificante(java.lang.String, java.lang.String, int)
   */
  public java.lang.String getJustificante(java.lang.String modelo, java.lang.String codigoTasa, int idOrganismo) throws java.rmi.RemoteException{
    if (pPServiceInterface == null)
      _initPPServiceInterfaceProxy();
    return pPServiceInterface.getJustificante(modelo, codigoTasa, idOrganismo);
  }
  
  
}
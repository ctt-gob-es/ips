package es.redsara.intermediacion.scsp.esquemas.importe.ws;

import es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.Respuesta;

public class ScspwsProxy implements es.redsara.intermediacion.scsp.esquemas.importe.ws.Scspws {
		private String _endpoint = null;
		private es.redsara.intermediacion.scsp.esquemas.importe.ws.Scspws scspws = null;
  
	public ScspwsProxy() {
	    _initScspwsProxy();
	  }
	  
	public ScspwsProxy(String endpoint) {
	    _endpoint = endpoint;
	    _initScspwsProxy();
	  }
	  
  
	private void _initScspwsProxy() {
		    try {
		      scspws = (new es.redsara.intermediacion.scsp.esquemas.importe.ws.ScspwsServiceLocator()).getscspwsSoap11();
		      if (scspws != null) {
		        if (_endpoint != null)
		          ((javax.xml.rpc.Stub)scspws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
		        else
		          _endpoint = (String)((javax.xml.rpc.Stub)scspws)._getProperty("javax.xml.rpc.service.endpoint.address");
		      }
		      
		    }
		    catch (javax.xml.rpc.ServiceException serviceException) {}
		  }
		  
	  public String getEndpoint() {
		  return _endpoint;
		 }

  
	  public void setEndpoint(String endpoint) {
		    _endpoint = endpoint;
		    if (scspws != null)
		      ((javax.xml.rpc.Stub)scspws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
		    
		  }
  
	  public es.redsara.intermediacion.scsp.esquemas.importe.ws.Scspws getScspws() {
		    if (scspws == null)
		      _initScspwsProxy();
		    return scspws;
		  }
  
	  public Respuesta peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona peticionSincrona) throws java.rmi.RemoteException{
		    if (scspws == null)
		      _initScspwsProxy();
		    return scspws.peticionSincrona(peticionSincrona);
		  }
		  	  
			  public es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona peticionAsincrona) throws java.rmi.RemoteException{
				    if (scspws == null)
				        _initScspwsProxy();
			    return scspws.peticionAsincrona(peticionAsincrona);
			  }
			  
			  public es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.Respuesta solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta solicitudRespuesta) throws java.rmi.RemoteException{
				    if (scspws == null)
				        _initScspwsProxy();
			    return scspws.solicitudRespuesta(solicitudRespuesta);
			  }
		  
		}
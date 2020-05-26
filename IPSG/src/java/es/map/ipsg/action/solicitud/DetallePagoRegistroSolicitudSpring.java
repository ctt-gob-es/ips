package es.map.ipsg.action.solicitud;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DetalleRegistroPagoBean;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class DetallePagoRegistroSolicitudSpring.
 */
public class DetallePagoRegistroSolicitudSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetallePagoRegistroSolicitudSpring.class);
	
	/** el solicitud manager. */
	private SolicitudesManager solicitudManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * DetallePagoRegistroSolicitudAction.
	 */
	public DetallePagoRegistroSolicitudSpring() {
		try{
			setSolicitudManager((SolicitudesManager) getBean("solicitudesManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error DetallePagoRegistroSolicitudSpring:",e);
		}
	}

	/**
	 * doExecute  de DetallePagoRegistroSolicitudAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String mensaje;
		String resultado;
		try{
			logger.debug("DetallePagoRegistroSolicitudSpring -start");			
			
			String idSolicitud = this.getRequest().getParameter("idSolicitud");
			
			if(!StringUtils.isEmpty(idSolicitud)){
			
				DetalleRegistroPagoBean detalle = solicitudManager.obtenerDetalleRegistroPago(Long.valueOf(idSolicitud));
				
				if(detalle!=null){
					this.getRequest().setAttribute("pagos", detalle.getPagos());
					this.getRequest().setAttribute("registros", detalle.getRegistros());
					resultado = "success";
				}else{
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("field.solicitud.errorDatosSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					logger.error("Numero de solicitud incorrecto");
				}
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("field.solicitud.errorNumeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error(" Error Numero de solicitud obtenido es null");
			}
			
			logger.debug("DetallePagoRegistroSolicitudSpring -end");
		}catch (Exception ex){
			logger.error(" DetallePagoRegistroSolicitudSpring - doExecute",ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");	
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);			
		}
		
		return resultado;
	}
	

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return the solicitudManager
	 */
	public SolicitudesManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager the solicitudManager to set
	 */
	public void setSolicitudManager(SolicitudesManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


}

package es.map.ipsc.spring.solicitudes;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.form.DetalleSolicitudForm;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class DetallePagoSolicitudesSpring.
 */
public class DetallePagoSolicitudesSpring extends AbstractSecureSpring {

	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetallePagoSolicitudesSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
    
    /** La constante STRING_ERRORDESCRIPCION. */
    private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_PAGOSOLICITUD. */
	private static final String STRING_PAGOSOLICITUD = "pagoSolicitud";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/**
	 * Crea una nueva detalle pago solicitudes spring.
	 */
	public DetallePagoSolicitudesSpring() {
		try {
			setPagoSolicitudManager((PagoSolicitudManager) getBean ("pagoSolicitudesManager"));
			setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error detalle pago solicitudes",e);
		}

		
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		DetalleSolicitudForm formulario;
		formulario = (DetalleSolicitudForm) form;
		String codSolicitud = (String) this.getRequest().getParameter("registro");
		logger.info(codSolicitud);
		if(codSolicitud == null || "".equals(codSolicitud)){
			codSolicitud = formulario.getIdSolicitud();
		}
		//Comprobar si el usuario esta en sesion
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuario");
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
			return STRING_ERRORUSUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
				return STRING_ERRORUSUARIO;
			}
		}
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(codSolicitud));		
		
		//Buscar la solicitud
		DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudQuery);
		//Comprobar si el usuario de sesion es el mismo del usuario de la solicitud
		if(solicitudes != null){
			if(solicitudes.getNif().equals(usuActual.getNif())){
				int idEstadoSolicitud = solicitudes.getIdEstadoSolicitud();
				// Se comprueba si la solicitud se encuentra en estado EN PROCESO DE PAGO
				if(idEstadoSolicitud == Constantes.SOLICITUD_ID_PROCESO_PAGO)
				{
					setRequestAttribute("errorPago" , RESOURCE_BUNDLE.getString("field.solicitud.mensajeProcesoPago"));
					DetallePagoSolicitudBean pagoSolicitudAux = new DetallePagoSolicitudBean();				
					pagoSolicitudAux.setIdSolicitud(codSolicitud);
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitudError"));
					setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitudAux);
					setRequestAttribute("enProcesoPago", Constantes.SI);
					return STRING_SUCCESS;
				}
				
				if(Constantes.SOLICITUD_ID_NOPAGADO == idEstadoSolicitud){
					//Si el estado de la solicitud es no pagado, la pagina redirecciona al pago de la solicitud
					this.setRequestAttribute("id", String.valueOf(codSolicitud));
					return "pagarSolicitud";
				}
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
				pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
				//Busca el pago de la solicitud
				DetallePagoSolicitudBean pagoSolicitud= pagoSolicitudManager.buscarPagoSolicitudIdSolicitu(pagoSolicitudQuery);
				if(pagoSolicitud == null){
					DetallePagoSolicitudBean pagoSolicitudAux = new DetallePagoSolicitudBean();				
					pagoSolicitudAux.setIdSolicitud(codSolicitud);
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitudError"));
					setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitudAux);
					return STRING_SUCCESS;
				}
				pagoSolicitud.setIdSolicitud(codSolicitud);
				pagoSolicitud.setNumJustificante(solicitudes.getNumJustificante());
				pagoSolicitud.setNif(solicitudes.getNif());
				
				//Comprobar si tiene un justificante de pago
				String tipoPago = pagoSolicitud.getTipo();
				
				if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO.toUpperCase())){
					this.setRequestAttribute("certPago", true);
				}
					
				
				
				String nombreCompleto = solicitudes.getNombre() + " " +solicitudes.getPrimerApellido() + " " + solicitudes.getSegundoApellido();
				pagoSolicitud.setNombreCompleto(nombreCompleto);
				setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitud);
				setRequestAttribute("registro", codSolicitud);
			}else{
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitud.usuarioError"));
				return STRING_ERRORUSUARIO;
			}			
		}else{
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.errorCodSolicitud"));
			return STRING_ERRORUSUARIO;
		}
		
		return STRING_SUCCESS;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}



}

package es.map.ipsc.spring.solicitudes;

import java.util.Properties;
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
 * El Class DetalleSolicitudesSpring.
 */
public class DetalleSolicitudesSpring extends AbstractSecureSpring {

	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetalleSolicitudesSpring.class);
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva detalle solicitudes spring.
	 */
	public DetalleSolicitudesSpring() {
		try {
			setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error detalle solicitudes",e);
		}

		
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		DetalleSolicitudForm formulario;
		formulario = (DetalleSolicitudForm) form;
		String siglasCentroGestor = null;
		
		//Obtengo las siglas del Centro Gestor 
		String siglasCentroGestorJusticia = "";		
	    siglasCentroGestor = (String)this.getRequest().getParameter("siglas");
	     
		
		if(siglasCentroGestor != null){
			
			this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
			this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 
			
		}
		
		
		
		
		
		String codSolicitud = (String) this.getRequest().getParameter(STRING_REGISTRO);
		logger.info("CodSolicitud: "+codSolicitud);
		String auxCodSolicitud = (String) this.getRequest().getAttribute(STRING_REGISTRO);
		logger.info("AuxSolicitud: "+auxCodSolicitud);
		if(auxCodSolicitud != null){
			codSolicitud = auxCodSolicitud;
		}
		logger.info("CodSolicitud: "+codSolicitud);
		//Comprueba que el usuario este en sesion
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
			return STRING_ERRORUSUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
				return STRING_ERRORUSUARIO;
			}
		}
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.parseLong(codSolicitud));

		
		//Busca los datos de la solicitud
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
		
		DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudComunQuery);		
		if(solicitudes != null ){
			if(solicitudes.getNif().equals(usuActual.getNif())){
				DetallePagoSolicitudBean detallePagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicitu(pagoSolicitudQuery);
				if(detallePagoSolicitudBean!=null){
					solicitudes.setImporte(Float.parseFloat(detallePagoSolicitudBean.getImporte()));
				}
				setRequestAttribute("solicitud", solicitudes);
				setRequestAttribute(STRING_REGISTRO, codSolicitud);
			}else{
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitud.usuarioError"));
				return STRING_ERRORUSUARIO;
			}
		}else{
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.errorCodSolicitud"));
			return STRING_ERRORUSUARIO;
		}
		return "success";
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
	 * @param pSolicitudManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager pSolicitudManager) {
		this.solicitudesManager = pSolicitudManager;
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

}

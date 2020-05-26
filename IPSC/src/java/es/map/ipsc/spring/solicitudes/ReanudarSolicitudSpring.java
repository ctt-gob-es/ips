package es.map.ipsc.spring.solicitudes;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class ReanudarSolicitudSpring.
 */
public class ReanudarSolicitudSpring extends AbstractSecureSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** el properties. */
	private static Properties properties;
	
	/** el solicitud manager. */
	private SolicitudManager solicitudManager;
	
	/**
	 * Crea una nueva reanudar solicitud spring.
	 */
	public ReanudarSolicitudSpring() {
		try{			
			setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error reanudar solicitud",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String codSolicitud = (String) this.getRequest().getParameter(STRING_REGISTRO);
		if(codSolicitud == null){
			codSolicitud = (String) this.getRequest().getAttribute(STRING_REGISTRO);
		}

		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		
		//Obtengo las siglas del Centro Gestor 
		String siglasCentroGestorJusticia = "";		
		String siglasCentroGestor = (String)this.getRequest().getParameter("siglas");
		
		this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
		this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 
		
		
		
		//Comprobar si el usuario esta en la sesion
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuario");
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
			return "errorUsuario";
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return "errorUsuario";
			}
		}
		Long auxCod = 0L;
		try{
		auxCod = Long.parseLong(codSolicitud);
		}catch(Exception e){
			logger.error("El codigo solicitud no es correcto",e);
		}
		solicitudQuery.setIdSolicitud(auxCod);
		//Buscar la solicitud
		SolicitudBean solicitud = solicitudManager.buscarSolicitudById(solicitudQuery);
		if(solicitud != null){
			if(!solicitud.getNif().equals(usuActual.getNif())){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.solicitud.pagoSolicitud.usuarioError"));
				return STRING_ERROR;
			}
			//Comprobar el estado y redireccionar dependiendo de donde venga
			if(solicitud.getEstadoSolicitud() != null){
				int idEstadoSolicitud = Integer.valueOf(solicitud.getIdEstadoSolicitud()).intValue();
				if(Constantes.SOLICITUD_ID_NOPAGADO == idEstadoSolicitud){
					this.setRequestAttribute("id",String.valueOf(solicitud.getId()));
					return "noPagado";
				}else if(Constantes.SOLICITUD_ID_NOREGISTRADO == idEstadoSolicitud){
					this.setRequestAttribute("id",String.valueOf(solicitud.getId()));
					return "noRegistrado";
				}else if(Constantes.SOLICITUD_ID_REGISTRADO == idEstadoSolicitud){
					this.setRequestAttribute(STRING_REGISTRO,String.valueOf(solicitud.getId()));
					return "registrado";
				}else if(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO == idEstadoSolicitud){
					logger.warn("Solicitud en estado Pendiente de Registro");
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,"Su solicitud se encuentra en estado Pendiente de Registro por parte del Centro Gestor");
					return STRING_ERROR;
				}
				else if(Constantes.SOLICITUD_ID_PROCESO_PAGO == idEstadoSolicitud){
					logger.warn("Solicitud en estado Proceso de Pago");
					this.setRequestAttribute("id",String.valueOf(solicitud.getId()));
					return "procesoPago";
				}
				else if(Constantes.SOLICITUD_ID_PROCESO_REGISTRO == idEstadoSolicitud){
					logger.warn("Solicitud en estado Proceso de Registro");
					this.setRequestAttribute("id",String.valueOf(solicitud.getId()));
					return "procesoRegistro";
				}
				
			}
		}else{
			logger.error("No se ha podido recuperar la solicitud");
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,"No es posible reanudar la solicitud en este momento");
			return STRING_ERROR;
		}
		this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,"No es posible reanudar la solicitud en este momento");
		return STRING_ERROR;
	}

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

}

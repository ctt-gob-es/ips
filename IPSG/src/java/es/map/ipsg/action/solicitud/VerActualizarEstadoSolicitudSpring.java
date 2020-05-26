package es.map.ipsg.action.solicitud;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.ActualizarEstadoSolicitudForm;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class VerActualizarEstadoSolicitudSpring.
 */
public class VerActualizarEstadoSolicitudSpring extends AbstractSpring {
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerActualizarEstadoSolicitudSpring.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/**
	 * Crea una nueva ver actualizar estado solicitud spring.
	 */
	public VerActualizarEstadoSolicitudSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error(" Error VerActualizarEstadoSolicitudSpring: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		
		String resultado;
	try{	
		ActualizarEstadoSolicitudForm formulario = (ActualizarEstadoSolicitudForm ) form;
		String idSolicitud = this.getRequest().getParameter("solicitud");
		
		if(!StringUtils.isEmpty(idSolicitud)){
			SolicitudBean solicitudBean;
			
			solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));

			formulario.setIdNuevoEstado(solicitudBean.getIdEstadoSolicitud());
			formulario.setEstadoActual(solicitudBean.getDescEstadoSolicitud());
			
			this.setRequestAttribute("solicitud", solicitudBean);
			this.setRequestAttribute("scroll", this.getRequest().getParameter("scroll"));
			
			resultado = "success";
		}else{
			resultado = "error";
		}	
		
	}catch(Exception eGenerico){
		logger.error(" Error VerActualizarEstadoSolicitudSpring - error  actualizar estado solicitud: ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return resultado;
	}

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return the solicitudManager
	 */
	public SolicitudesRegistradasManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager the solicitudManager to set
	 */
	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}

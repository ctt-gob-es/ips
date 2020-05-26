package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.IntermediacionIdentidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.DatosPersonalesManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class VerificarFechaNacimientoSpring.
 */
public class VerificarFechaNacimientoSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarFechaNacimientoSpring.class);
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el datos personales manager. */
	private DatosPersonalesManager datosPersonalesManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * Crea una nueva verificar fecha nacimiento spring.
	 */
	public VerificarFechaNacimientoSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			setDatosPersonalesManager((DatosPersonalesManager) getBean("datosPersonalesManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error VerificarFechaNacimientoSpring:" ,e);
		}
	}

	/**
	 * doExecute  de VerificarFechaNacimientoAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarFechaNacimientoSpring -start");
			
			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();
			
			if (sUsernameLogin.equals(STRING_ERROR)) {
				return sUsernameLogin;
			}
			

			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			
			String idSolicitud = (String)this.getRequest().getParameter("idSolicitud");
			String auxiliar = "";
			
			if (this.getRequest().getParameter("auxiliar") != null) {
				auxiliar = (String)this.getRequest().getParameter("auxiliar");
			}
			
			if(idSolicitud!=null){
				SolicitudBean solicitudBean  = new SolicitudBean ();
				
				try{
					if(auxiliar.equalsIgnoreCase("true") && !StringUtils.isEmpty(auxiliar)) {
						solicitudBean = solicitudComunAuxiliarManager.obtenerSolicitud(Long.valueOf(idSolicitud));
					}else {
						solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
					}
				}catch(Exception e){
					logger.error("Error  VerificarFechaNacimientoSpring - error al obtener solicitud- numero de solicitud erroneo:" +idSolicitud ,e);
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					return resultado;
				}
				
				// Se valida si tiene fecha de nacimiento y nif
				if(solicitudBean != null){
					if(solicitudBean.getFechaNacimiento() == null){
						logger.error("Error  VerificarFechaNacimientoSpring - error al obtener la fecha de nacimiento");
						resultado = STRING_ERROR;
						mensaje = RESOURCE_BUNDLE.getString("svdi.error.fechaNacimiento");
						this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
						return resultado;
					}
					if(solicitudBean.getNif() == null || solicitudBean.getNif().isEmpty()){
						logger.error("Error  VerificarFechaNacimientoSpring - error al obtener la fecha de nacimiento");
						resultado = STRING_ERROR;
						mensaje = RESOURCE_BUNDLE.getString("svdi.error.nif");
						this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
						return resultado;
					}
				}

				if(auxiliar.equalsIgnoreCase("true") && !StringUtils.isEmpty(auxiliar)) {
					IntermediacionIdentidadBean identidadBean = datosPersonalesManager.validarFechaNacimientoAuxiliar(solicitudBean,usuarioBean);
					this.getRequest().setAttribute("identidad", identidadBean);
				}else {
					IntermediacionIdentidadBean identidadBean = datosPersonalesManager.validarFechaNacimiento(solicitudBean,usuarioBean);
					this.getRequest().setAttribute("identidad", identidadBean);
				}

				this.getRequest().setAttribute("solicitud", solicitudBean);
				resultado = "success";
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("Numero de solicitud obtenido es null");
			}
			
			logger.debug("VerificarFechaNacimientoSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error("Error  VerificarFechaNacimientoSpring - doExecute:"  ,ex);
			SpringErrors errors = new SpringErrors();
			errors.add("", new SpringMessage(ex.getMessage(),false));			
			resultado = "success";
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error  VerificarFechaNacimientoSpring - doExecute:"  ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error  VerificarFechaNacimientoSpring - recuperarUsuarioSesion:"+  sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
	}
	
	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudesRegistradasManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}
	
	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return el solicitud comun auxiliar manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

	/**
	 * Obtiene el datos personales manager.
	 *
	 * @return el datos personales manager
	 */
	public DatosPersonalesManager getDatosPersonalesManager() {
		return datosPersonalesManager;
	}

	/**
	 * Establece el datos personales manager.
	 *
	 * @param datosPersonalesManager el nuevo datos personales manager
	 */
	public void setDatosPersonalesManager(
			DatosPersonalesManager datosPersonalesManager) {
		this.datosPersonalesManager = datosPersonalesManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

}

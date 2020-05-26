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
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDiscapacidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.VerificarDiscapacidadForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosDiscapacidadManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class ConsultarDiscapacidadSpring.
 */
public class ConsultarDiscapacidadSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarDiscapacidadSpring.class);
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el datos discapacidad manager. */
	private DatosDiscapacidadManager datosDiscapacidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/**
	 * Crea una nueva consultar discapacidad spring.
	 */
	public ConsultarDiscapacidadSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setDatosDiscapacidadManager((DatosDiscapacidadManager) getBean("datosDiscapacidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ConsultarDiscapacidadSpring: ",e);
		}
	}

	/**
	 * doExecute  de VerificarDiscapacidadAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarDiscapacidadSpring -start");
			VerificarDiscapacidadForm formulario = new VerificarDiscapacidadForm();
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
				SolicitudBean solicitudBean ;
				SolicitudCcaaBean solicitudCcaaBean = null;
				SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = null;
				ConvocatoriasBean convocatoriaBean;
				SolicitudComunQuery solicitudQuery= new SolicitudComunQuery();
				SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
				
				try{
					if(auxiliar.equalsIgnoreCase("true") && !StringUtils.isEmpty(auxiliar)) {
						solicitudBean = solicitudComunAuxiliarManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						
						SolicitudComunAuxiliarQuery solicitudAuxiliarQueryAux = new SolicitudComunAuxiliarQuery();
						solicitudAuxiliarQueryAux.setIdSolicitudAuxiliar(solicitudBean.getId());
						logger.debug("SolicitudBeanAux.getId: "+solicitudAuxiliarQueryAux.getIdSolicitudAuxiliar());
						
						solicitudCcaaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudAuxiliarQueryAux);
						logger.debug("Hacemos consulta a Solicitud de Comunidades");
						solicitudCcaaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxiliarByIdSolicitud(solicitudCcaaAuxiliarQuery);
					}else {
						solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						
						solicitudQuery.setIdSolicitud(solicitudBean.getId());
						solicitudCcaaQuery.setSolicitudComun(solicitudQuery);
						solicitudCcaaBean = solicitudCcaaManager.obtenerSolicitudCcaaByIdSolicitud(solicitudCcaaQuery);	
					}

					convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
					convocatoriaBean =convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);
					
					
				}catch(Exception e){
					logger.error("Error ConsultarDiscapacidadSpring  numero de solicitud erronea: "+idSolicitud ,e);
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					logger.error("Numero de solicitud erroneo: "+idSolicitud);
					return resultado;
				}
				
				// Se valida si tiene nif
				if(solicitudBean != null){
					if(solicitudBean.getNif() == null || solicitudBean.getNif().isEmpty()){
						logger.error("Error  VerificarFechaNacimientoSpring - error al obtener la fecha de nacimiento");
						resultado = STRING_ERROR;
						mensaje = RESOURCE_BUNDLE.getString("svdi.error.nif");
						this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
						return resultado;
					}
				}
				
				if(solicitudCcaaBean != null) {
					if(solicitudCcaaBean.getIdComunidad()!=null ){
					/*INI-TRA042*/
					IntermediacionDiscapacidadBean discapacidadBean = datosDiscapacidadManager.validarDiscapacidad(convocatoriaBean,solicitudBean,usuarioBean,solicitudCcaaBean, solicitudBean.getCentroGestor());
					/*FIN-TRA042*/
					
					//Si falla el WS se guarda el estado Ptd.error al comprobar el Ws sino como Ptd
					if (discapacidadBean == null && solicitudBean.getId() != null) {
						datosDiscapacidadManager.guardarEstadoFalloWsSolicitud(solicitudBean);
						throw new BusinessException("intermediacion.svdi.errorConexion");
					} else {
						datosDiscapacidadManager.guardarEstadoPendiente(solicitudBean.getId());
					}
					
					formulario.setGradoDiscapacidad(discapacidadBean.getGradoDiscapacidad());
					formulario.setResultadoVerificacion(discapacidadBean.isResultadoVerificacion());
					this.setRequestAttribute("formulario",formulario);
					
					resultado = "success";
					}
				}else if(solicitudCcaaAuxiliarBean != null) {
					if(solicitudCcaaAuxiliarBean.getIdComunidad()!=null ){
					/*INI-TRA042*/
					IntermediacionDiscapacidadBean discapacidadBean = datosDiscapacidadManager.validarDiscapacidadAuxiliar(convocatoriaBean,solicitudBean,usuarioBean,solicitudCcaaAuxiliarBean, solicitudBean.getCentroGestor());
					/*FIN-TRA042*/
					
					//Si falla el WS se guarda el estado Ptd.error al comprobar el Ws sino como Ptd
					if (discapacidadBean == null && solicitudBean.getId() != null) {
						datosDiscapacidadManager.guardarEstadoFalloWsSolicitudAuxiliar(solicitudBean);
						throw new BusinessException("intermediacion.svdi.errorConexion");
					} else {
						datosDiscapacidadManager.guardarEstadoPendienteAuxiliar(solicitudBean.getId());
					}
					
					formulario.setGradoDiscapacidad(discapacidadBean.getGradoDiscapacidad());
					formulario.setResultadoVerificacion(discapacidadBean.isResultadoVerificacion());
					this.setRequestAttribute("formulario",formulario);
					
					resultado = "success";
					}
				}else{				
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("field.comunidadesErrorDD");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("No se puede realizar la consulta puesto que el usuario no ha seleccionado la comunidad o la comunidad no se encuentra disponible en el servicio de consulta de discapacidad");
			}
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("Numero de solicitud obtenido es null");
			}
			
			logger.debug("VerificarDesempleoSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error("Error ConsultarDiscapacidadSpring:"  ,ex);
			SpringErrors errors = new SpringErrors();
			errors.add("", new SpringMessage(ex.getMessage(),false));			
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error ConsultarDiscapacidadSpring:"  ,ex);
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
			logger.error("Error ConsultarDiscapacidadSpring - recuperarUsuarioSesion: "+sUsernameLogin,e);
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

	/**
	 * Obtiene el datos discapacidad manager.
	 *
	 * @return el datos discapacidad manager
	 */
	public DatosDiscapacidadManager getDatosDiscapacidadManager() {
		return datosDiscapacidadManager;
	}

	/**
	 * Establece el datos discapacidad manager.
	 *
	 * @param datosDiscapacidadManager el nuevo datos discapacidad manager
	 */
	public void setDatosDiscapacidadManager(DatosDiscapacidadManager datosDiscapacidadManager) {
		this.datosDiscapacidadManager = datosDiscapacidadManager;
	}

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriaManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return el solicitud ccaa manager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager el nuevo solicitud ccaa manager
	 */
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
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
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return el solicitud ccaa auxiliar manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}

	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager el nuevo solicitud ccaa auxiliar manager
	 */
	public void setSolicitudCcaaAuxiliarManager(SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
	}

}

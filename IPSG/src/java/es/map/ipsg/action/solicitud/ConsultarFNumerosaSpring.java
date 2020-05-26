package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionFNumerosaBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.VerificarFNumerosaForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosFNumerosaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class ConsultarFNumerosaSpring.
 */
public class ConsultarFNumerosaSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante MESSAGE_INFO. */
	private static final String MESSAGE_INFO = "ExceptionResources";
	
	/** La constante RESOURCE_INFO. */
	private static final ResourceBundle RESOURCE_INFO = ResourceBundle.getBundle(MESSAGE_INFO);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarFNumerosaSpring.class);
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el datos F numerosa manager. */
	private DatosFNumerosaManager datosFNumerosaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/**
	 * Crea una nueva consultar F numerosa spring.
	 */
	public ConsultarFNumerosaSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setDatosFNumerosaManager((DatosFNumerosaManager) getBean("datosFNumerosaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setPagoSolicitudManager ((PagoSolicitudManager) getBean ("pagoSolicitudManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ConsultarFNumerosaSpring:",e);
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
			logger.debug("ConsultarFNumerosaSpring -start");
			VerificarFNumerosaForm formulario = new VerificarFNumerosaForm();		
				
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
				SolicitudBean solicitudBean;
				SolicitudCcaaBean solicitudCcaaBean = null;
				SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = null;
				SolicitudBean solicitudBeanAux;				
				SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
				SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
				try{	
					logger.debug("Hacemos consulta a Solicitud Comun");
					if(auxiliar.equalsIgnoreCase("true") && !StringUtils.isEmpty(auxiliar)) {
						solicitudBean = solicitudComunAuxiliarManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						
						SolicitudComunAuxiliarQuery solicitudAuxiliarQueryAux = new SolicitudComunAuxiliarQuery();
						solicitudAuxiliarQueryAux.setIdSolicitudAuxiliar(solicitudBean.getId());
						logger.debug("SolicitudBeanAux.getId: "+solicitudAuxiliarQueryAux.getIdSolicitudAuxiliar());
						
						solicitudCcaaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudAuxiliarQueryAux);
						logger.debug("Hacemos consulta a Solicitud de Comunidades");
						solicitudCcaaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxiliarByIdSolicitud(solicitudCcaaAuxiliarQuery);
						
					}else {
						solicitudBeanAux = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						
						SolicitudComunQuery solicitudQueryAux= new SolicitudComunQuery();
						solicitudQueryAux.setIdSolicitud(solicitudBeanAux.getId());
						logger.debug("SolicitudBeanAux.getId: "+solicitudQueryAux.getIdSolicitud());
						
						pagoSolicitudQuery.setSolicitudComun(solicitudQueryAux);
						logger.debug("Hacemos consulta a Pago Solicitud");
						solicitudBean = pagoSolicitudManager.completaDatosPagoSolicitud(pagoSolicitudQuery, solicitudBeanAux);
						
						solicitudCcaaQuery.setSolicitudComun(solicitudQueryAux);		
						logger.debug("Hacemos consulta a Solicitud de Comunidades");
						solicitudCcaaBean = solicitudCcaaManager.obtenerSolicitudCcaaByIdSolicitud(solicitudCcaaQuery);	
						
					}

					convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
					logger.debug("Hacemos consulta a Convocatoria");
					convocatoriaBean =convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);

				}catch(Exception e){
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					logger.error(" Error ConsultarFNumerosaSpring Numero de solicitud erroneo: "+idSolicitud,e);
					
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
					if(solicitudCcaaBean.getIdComunidad()!=null){
						IntermediacionFNumerosaBean fnumerosaBean = datosFNumerosaManager.validarFNumerosa(convocatoriaBean,solicitudBean,usuarioBean,solicitudCcaaBean);				
						
							if (fnumerosaBean == null) {
								datosFNumerosaManager.guardarEstadoFalloWsSolicitud(solicitudBean);
								throw new BusinessException("Error al conectar con el Servicio.");
							}else {
								if (solicitudBean.getId() != null) {
									datosFNumerosaManager.guardarEstadoPendiente(solicitudBean.getId());
								}
								formulario.setCategoriaFNumerosa(fnumerosaBean.getCategoriaFNumerosa());
								formulario.setVigenciaTitulo(fnumerosaBean.getVigenciaTitulo());
								formulario.setDescripcionError(fnumerosaBean.getDescripcionError());
								formulario.setSolicitanteFNumerosa(solicitudBean.getFamNumerosa());
								this.setRequestAttribute("formulario",formulario);
								resultado = "success";
							}
					}
				}else if(solicitudCcaaAuxiliarBean != null){
					if (solicitudCcaaAuxiliarBean.getIdComunidad()!= null) {
						IntermediacionFNumerosaBean fnumerosaBean = datosFNumerosaManager.validarFNumerosaAuxiliar(convocatoriaBean,solicitudBean,usuarioBean,solicitudCcaaAuxiliarBean);				
						
						if (fnumerosaBean == null) {
								datosFNumerosaManager.guardarEstadoFalloWsSolicitudAuxiliar(solicitudBean);
							throw new BusinessException("Error al conectar con el Servicio.");
						}else {
								if (solicitudBean.getId() != null) {
									datosFNumerosaManager.guardarEstadoPendienteAuxiliar(solicitudBean.getId());
								}
							formulario.setCategoriaFNumerosa(fnumerosaBean.getCategoriaFNumerosa());
							formulario.setVigenciaTitulo(fnumerosaBean.getVigenciaTitulo());
							formulario.setDescripcionError(fnumerosaBean.getDescripcionError());
							formulario.setSolicitanteFNumerosa(solicitudBean.getFamNumerosa());
							this.setRequestAttribute("formulario",formulario);
							resultado = "success";
						}
					}
				}else{				
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("field.comunidadesErrorFN");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				}
				
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("Numero de solicitud obtenido es null");
			}
			
			logger.debug("VerificarFNumerosaSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error("Error ConsultarFNumerosaSpring - error de conexion ",ex);
			resultado=STRING_ERROR;
			mensaje = RESOURCE_INFO.getString("intermediacion.svdi.errorConexion").concat(" ").concat((ex.getMessage()!= null)?ex.getMessage():"");	
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error ConsultarFNumerosaSpring  ",ex);
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
			logger.error("Error ConsultarFNumerosaSpring - recuperarUsuarioSesion:"+ sUsernameLogin,e);
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
	 * Obtiene el datos F numerosa manager.
	 *
	 * @return el datos F numerosa manager
	 */
	public DatosFNumerosaManager getDatosFNumerosaManager() {
		return datosFNumerosaManager;
	}

	/**
	 * Establece el datos F numerosa manager.
	 *
	 * @param datosFNumerosaManager el nuevo datos F numerosa manager
	 */
	public void setDatosFNumerosaManager(DatosFNumerosaManager datosFNumerosaManager) {
		this.datosFNumerosaManager = datosFNumerosaManager;
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
	
	

}
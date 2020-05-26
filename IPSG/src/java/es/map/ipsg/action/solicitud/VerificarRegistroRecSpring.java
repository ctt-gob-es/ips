package es.map.ipsg.action.solicitud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ActualizarEstadoSolicitudBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.RegistroRecBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosProxy;
import es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerificarRegistroRecSpring.
 */
public class VerificarRegistroRecSpring extends AbstractSpring {


	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el buscar reg elec WS. */
	//WebService
	private BuscarRegistrosElectronicos buscarRegElecWS;
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarRegistroRecSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/**
	 * Crea una nueva verificar registro rec spring.
	 */
	public VerificarRegistroRecSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
				setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
				setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
				properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error VerificarRegistroRecSpring ",e);
		}
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********			
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
	try{	
		//Tomamos el usuario que se ha logueado
		String sNif = "";
		try{
			SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
			
			AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			sNif = user.getIdentificador();
		}catch(Exception e){
			logger.error("Error VerificarRegistroRecSpring - recuperar usuarioSesion "+ sNif,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}

		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(sNif); //Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		
		String solicitud = this.getRequest().getParameter("solicitud");
		long idSolicitud = 0;
		
		try{
			idSolicitud = Long.parseLong(solicitud);
		}catch(Exception e){
			logger.error("Error VerificarRegistroRecSpring - error parsear idSolicitud: "+ idSolicitud,e);
			return STRING_ERROR;
		}
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(idSolicitud);
		
		SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
		String numRegistro = solicitudBean.getNumeroRegistro();
		
		setRequestAttribute("solicitud", solicitudBean);
		
		if (numRegistro == null || "".equals(numRegistro)) {
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultaRec.errores.errorJustificante");
			errors.add(STRING_ERRORCODIGO,error);
			saveErrors(this.getRequest(),errors);
			return STRING_ERROR;
		}
		
		try {		
			
			//Llamada al WebService
			String direccionWS = properties.getProperty("conf.url_buscarRegistrosElectronicos");
			buscarRegElecWS = new BuscarRegistrosElectronicosProxy(direccionWS);
			
			//Criterios de busqueda necesarios para el WS
			CriteriosBusquedaType criterios = new CriteriosBusquedaType();
			criterios.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
			criterios.setCdTipoRegistro(properties.getProperty("cdTipoAsiento"));
			criterios.setNmRegistro(numRegistro);
			
			logger.info("Registro Buscado:" + numRegistro);
			
			String idAplicacion = properties.getProperty("idAplicacion");
			String password = properties.getProperty("password");
			AutenticacionType autentication = new AutenticacionType();
			autentication.setIdAplicacion(idAplicacion);
			autentication.setPassword(password);
			
			JustificanteType[] resultado = buscarRegElecWS.buscarRegistro(autentication, criterios);
			RespuestaType respuestaType=resultado[0].getRespuesta();

			List <RegistroRecBean> listRegistroRec = new ArrayList<RegistroRecBean>();
			if (resultado != null && !"".equals(Arrays.toString(resultado)) && resultado.length != 0) {
				if (respuestaType.getCdRespuesta() != null && !"00".equals(respuestaType.getCdRespuesta())) {
					
					logger.error("justificanteType.isExisteError() is true");
					
					if(null!=respuestaType.getDsRespuesta()){	
						logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());
					}
					
					//Se ha producido un error en el WS
					if (respuestaType.getCdRespuesta().equals("01")) {
						SpringMessages errors = new SpringMessages();					
						SpringMessage error = new SpringMessage("field.consultaRec.errores.sinResultados");
						errors.add(STRING_ERRORCODIGO,error);
						saveErrors(this.getRequest().getSession(),errors);
						
						return STRING_SUCCESS;
					}else {
						SpringMessages errors = new SpringMessages();					
						SpringMessage error = new SpringMessage("field.consultaRec.errores.falloWS");
						errors.add(STRING_ERRORCODIGO,error);
						saveErrors(this.getRequest().getSession(),errors);
						
						return STRING_SUCCESS;
					}					
				}else {
					//Han llegado datos				
					int tamanio = resultado.length;
					

					for (int i=0; i<tamanio; i++){
						JustificanteType registro;
						RegistroRecBean registroRecBean = new RegistroRecBean();
						registro = resultado[i];
						
						//Seteamos en el bean los campos obtenidos a traves del WS
						if (registro.getFeRegistro() != null) {
							registroRecBean.setFeRegistro(registro.getFeRegistro());
						}
						
						registroRecBean.setNumRegistro(registro.getNmRegistro());
						registroRecBean.setCdOficinaOrigen(registro.getCdOficinaOrigen());
						
						//Guardar los datos del registro
						RegistroSolicitudBean registroBean =  new RegistroSolicitudBean();
						Date fechaIntento = new Date();
						registroBean.setIdSolicitud(idSolicitud);
						registroBean.setFechaIntento(fechaIntento);
						registroBean.setResultado("OK");
						String fechaRegistro = registro.getFeRegistro();
						Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(fechaRegistro);
						registroBean.setFechaRegistro(date);
						registroBean.setNumeroRegistro(registro.getNmRegistro());
						registroBean.setSolicitante('A');
						registroBean.setOficinaRegistro(registro.getCdOficinaOrigen());
						registroBean.setCodigoError("");
						registroBean.setDescripcionError("");					
						
						try{
							registroSolicitudManager.guardarRegistroSolicitud(registroBean);
							logger.info("Se ha guardado el registro");
							
							SolicitudComunQuery solicitudQueryAux = new SolicitudComunQuery();
							solicitudQueryAux.setIdSolicitud(idSolicitud);
							SolicitudBean solicitudAuxBean = solicitudesManager.buscarSolicitudById(solicitudQuery);							
							Integer estadoSolicitud = solicitudAuxBean.getIdEstadoSolicitud();
							solicitudesManager.actualizarEstadoSolicitud(solicitudAuxBean, 3);
							logger.info("Se ha actualizado el estado de la solicitud");
							
							this.setRequestAttribute("actualizado", RESOURCE_BUNDLE.getString("field.estadoSolicitud.actualizacionCorrecta"));
							LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
							Date fechaActual = new Date();
							logSolicitudBean.setFecha(fechaActual);
							logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_ALTA.charAt(0));
							logSolicitudBean.setActor(usuarioBean.getLogin());
							logSolicitudBean.setDetalleOperacion("Alta de registro de solicitud");
							logSolicitudBean.setIdSolicitud(idSolicitud);
							
							try{
								logSolicitudBean.setId_estado_actual(Integer.parseInt(Constantes.LOG_SOLICITUD_ESTADO_REGISTRADO));
							}catch(Exception e){
								
								logger.error(" Error VerificarRegistroRecSpring  - No se ha podido establecer el estado actual en el logSolicitud",e);
							}							
							
							logSolicitudBean.setId_estado_anterior(estadoSolicitud);
							logSolicitudBean.setIdEstadoAnterior(String.valueOf(estadoSolicitud));
							logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_REGISTRADO);
							logSolicitudBean.setTipoActor(Constantes.LOG_TIPO_ACTOR_USUARIO_CHAR);														
							logSolicitudManager.generarRegistroLogSolicitud(logSolicitudBean);
							logger.info("Se ha guardado el log del registro");
							
							ActualizarEstadoSolicitudBean actualizarEstadoSolicitudBean = new ActualizarEstadoSolicitudBean();
							actualizarEstadoSolicitudBean.setNumJustificante(solicitudAuxBean.getNumeroSolicitud());
							actualizarEstadoSolicitudBean.setNumRegistro(registroBean.getNumeroRegistro());
							String formatoFecha = "dd/MM/yyyy";
							SimpleDateFormat sdfFecha = new SimpleDateFormat(formatoFecha);
							actualizarEstadoSolicitudBean.setFecha(sdfFecha.format(registroBean.getFechaRegistro()));
							actualizarEstadoSolicitudBean.setMensaje("Se ha encontrado el registro de la solicitud, y se ha procedido a actualizar el estado");
							this.setRequestAttribute("actualizarEstadoBean", actualizarEstadoSolicitudBean);
						}catch(Exception e){
							
							logger.error(" error VerificarRegistroRecSpring  - Error guardar registro: ", e);
						}
						
						return STRING_SUCCESS;					
					}
				}
			}else {
				//resultado vacia
				SpringMessages errors = new SpringMessages();					
				SpringMessage error = new SpringMessage("field.consultaRec.errores.sinResultados");
				errors.add(STRING_ERRORCODIGO,error);
				saveErrors(this.getRequest().getSession(),errors);
				
				return STRING_SUCCESS;
			}
			setRequestAttribute("registros", listRegistroRec);	
		}catch (Exception e) {
			logger.error(" Error VerificarRegistroRecSpring  - Error en llamada a WebService buscarRegistrosElectronicos",e);
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultaRec.errores.falloWS");
			errors.add(STRING_ERRORCODIGO,error);
			saveErrors(this.getRequest().getSession(),errors);
			
			return STRING_SUCCESS;
		}
		
	}catch(Exception eGenerico){
		logger.error(" Error VerificarRegistroRecSpring  -doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		
		return "errorGenerico";
	}
		return STRING_SUCCESS;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}	
}

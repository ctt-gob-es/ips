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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.EstadoSolicitud;
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
import es.map.ipsg.form.ConsultarRegistrosRecForm;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerVerificarRegistroSpring.
 */
public class VerVerificarRegistroSpring extends AbstractSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerVerificarRegistroSpring.class);
	
	/** La constante STRING_FIELD_CONSULTAREC_ERRORES_SINRESULTADOS. */
	private static final String STRING_FIELD_CONSULTAREC_ERRORES_SINRESULTADOS = "field.consultaRec.errores.sinResultados";
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** el solicitud manager. */
	//Manager
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;

	/** La constante MESSAGE_RESOURCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
	
	/** el properties. */
	private static Properties properties;

			/**
			 * Crea una nueva ver verificar registro spring.
			 */
			public VerVerificarRegistroSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error(" Error VerVerificarRegistroSpring", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		logger.info("doExecute - inicio");
		String result;
		boolean actualizarSolicitud = false;
		
		try{	
			ConsultarRegistrosRecForm formulario = (ConsultarRegistrosRecForm) form;
			String idRegistro = this.getRequest().getParameter("idRegistro");

			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();
			
			if (sUsernameLogin.equals(STRING_ERROR)) {
				return sUsernameLogin;
			}

			if(!StringUtils.isEmpty(idRegistro)){
				SolicitudBean solicitudBean;

				//Obtenemos el numero de solicitud a traves del id
				solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idRegistro));
				String numSolicitud = solicitudBean.getNumeroSolicitud();
				numSolicitud = numSolicitud + properties.getProperty("extension");
				formulario.setNumJustificante(numSolicitud);

				/* Comprobamos el estado de la solicitud, con los estados 2,5,7 
				 * hay que actualizar la solicitud a estado 3, siempre que 
				 * el WS nos devuelva información del registro
				 */
				EstadoSolicitud estadoSolicitud =  solicitudBean.getEstadoSolicitud();
				Integer idEstado = estadoSolicitud.getId();
				if ((idEstado.compareTo(Integer.valueOf(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO)) == 0) ||
						(idEstado.compareTo(Integer.valueOf(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO)) == 0) ||
						(idEstado.compareTo(Integer.valueOf(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO)) == 0)) {
					actualizarSolicitud = true;
				}

				//error si no hay numero solicitud
				if (numSolicitud == null || "".equals(numSolicitud)) {
					SpringMessages errors = new SpringMessages();					
					SpringMessage error = new SpringMessage(STRING_FIELD_CONSULTAREC_ERRORES_SINRESULTADOS);
					errors.add(STRING_ERRORCODIGO,error);
					saveErrors(this.getRequest().getSession(),errors);
					
					return STRING_SUCCESS;
				}

				/*
				 * Llamada al WS de busqueda de registos electronicos
				 */

				try {		
					//Llamada al WebService
					String direccionWS = properties.getProperty("conf.url_buscarRegistrosElectronicos");
					BuscarRegistrosElectronicos buscarRegElecWS = new BuscarRegistrosElectronicosProxy(direccionWS);

					//Criterios de busqueda necesarios para el WS
					CriteriosBusquedaType criterios = new CriteriosBusquedaType();
					criterios.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
					criterios.setCdTipoRegistro(properties.getProperty("cdTipoAsiento"));
					String idAplicacion = properties.getProperty("idAplicacion");
					String password = properties.getProperty("password");

					AutenticacionType autentication = new AutenticacionType();
					autentication.setIdAplicacion(idAplicacion);
					autentication.setPassword(password);

					logger.info("Numero solicitud que se busca mediante WS: " + numSolicitud);

					JustificanteType[] resultado = buscarRegElecWS.buscarRegistro(autentication, criterios);
					RespuestaType respuestaType=resultado[0].getRespuesta();

					List <RegistroRecBean> listRegistroRec = new ArrayList<RegistroRecBean>();
					if (resultado != null && !"".equals(Arrays.toString(resultado))) {
						if (respuestaType.getCdRespuesta() != null && !"".equals(respuestaType.getCdRespuesta())) {

							logger.error("justificanteType.isExisteError() is true");
							if(null!=respuestaType.getDsRespuesta()){	
								logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());
							}

							//Se ha producido un error en el WS
							if (respuestaType.getCdRespuesta().equals("1")) {
								SpringMessages errors = new SpringMessages();					
								SpringMessage error = new SpringMessage(STRING_FIELD_CONSULTAREC_ERRORES_SINRESULTADOS);
								errors.add(STRING_ERRORCODIGO,error);
								saveErrors(this.getRequest().getSession(),errors);
								return STRING_SUCCESS;
							}else {
								SpringMessages errors = new SpringMessages();					
								SpringMessage error = new SpringMessage("field.verificarRec.errores.sinResultados");
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
								registroRecBean.setNomDocumento(numSolicitud);
								registroRecBean.setCdOficinaOrigen(registro.getCdOficinaOrigen());

								//Añadimos a la lista
								listRegistroRec.add(registroRecBean);						
							}
							//Seteamos los campos en el formulario
							formulario.setFeRegistro(listRegistroRec.get(0).getFeRegistro());
							formulario.setNumRegistro(listRegistroRec.get(0).getNumRegistro());
							formulario.setCdOficinaOrigen(listRegistroRec.get(0).getCdOficinaOrigen());

							//Verificamos si es necesario actualizar el estado de la solicitud
							if (actualizarSolicitud) {
								logger.info("Se actualiza la solicitud a estado registrada");
								//Procedemos a cambiar el estado de la solicitud
								EstadoSolicitud nuevoEstado = new EstadoSolicitud();
								nuevoEstado.setId(Integer.valueOf(Constantes.ESTADO_SOLICITUD_REGISTRADO));
								solicitudBean.setEstadoSolicitud(nuevoEstado);
								solicitudManager.modificarSolicitudRegistrada(solicitudBean);							

								logger.info("Se actualiza el log de solicitud");
								//Actualizamos el registro de log solicitud
								LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
								Date date = new Date();
								logSolicitudBean.setFecha(date);
								logSolicitudBean.setTipoOperacion((Constantes.LOG_SOLICITUD_CAMBIO_ESTADO).charAt(0));
								logSolicitudBean.setActor(sUsernameLogin);
								logSolicitudBean.setDetalleOperacion("Cambio de estado de la solicitud");
								logSolicitudBean.setIdSolicitud(solicitudBean.getId());
								logSolicitudBean.setIdEstadoAnterior(String.valueOf(solicitudBean.getIdEstadoSolicitud()));
								logSolicitudBean.setIdEstadoActual(String.valueOf(Constantes.ESTADO_SOLICITUD_REGISTRADO));
								logSolicitudBean.setTipoActor((Constantes.LOG_TIPO_ACTOR).charAt(0));							
								logSolicitudManager.insertarLogSolicitud(logSolicitudBean);

								logger.info("Se actualiza la tabla registro solicitud");
								//Actualizamos la tabla de registro solicitud
								RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();							
								registroSolicitudBean.setIdSolicitud(solicitudBean.getId());
								registroSolicitudBean.setFechaIntento(date);
								registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_OK);
								
								if (resultado[0].getFeRegistro() != null) {
									Date fechaRegistro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(resultado[0].getFeRegistro());
									registroSolicitudBean.setFechaRegistro(fechaRegistro);//Fecha en que se ha realizado el registro en REC
								}
								
								registroSolicitudBean.setNumeroRegistro(resultado[0].getNmRegistro());//Numero registro devuelto por REC
								registroSolicitudBean.setSolicitante((Constantes.REGISTRO_SOLICITANTE).charAt(0));
								registroSolicitudBean.setOficinaRegistro(resultado[0].getCdOficinaOrigen());//Oficina de Registro con la que se realizo el registro	
								registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
								setRequestAttribute("actualizacion", "S");
							}
						}
					}else {
						//resultado vacia
						SpringMessages errors = new SpringMessages();					
						SpringMessage error = new SpringMessage(STRING_FIELD_CONSULTAREC_ERRORES_SINRESULTADOS);
						errors.add(STRING_ERRORCODIGO,error);
						saveErrors(this.getRequest().getSession(),errors);
						return STRING_SUCCESS;
					}
				}catch (Exception e) {
					logger.error(" Error VerVerificarRegistroSpring - Error en llamada a WebService buscarRegistrosElectronicos",e);

					SpringMessages errors = new SpringMessages();					
					SpringMessage error = new SpringMessage("field.consultaRec.errores.falloWS");
					errors.add(STRING_ERRORCODIGO,error);
					saveErrors(this.getRequest().getSession(),errors);
					
					return STRING_SUCCESS;
				}

				result = STRING_SUCCESS;

			}else{
				result = STRING_ERROR;
			}	
			logger.info("doExecute - fin");

		}catch(Exception eGenerico){
			logger.error(" Error VerVerificarRegistroSpring - doExecute:", eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	

		return result;
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
			logger.error(" Error VerVerificarRegistroSpring - recuperar UsuarioSesion:"+ sUsernameLogin, e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
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

}

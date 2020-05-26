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

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.RegistroRecBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceLocator;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub;
import es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.form.ConsultarRegistrosRecForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarConsultarRegistrosRecSpring.
 */
public class BuscarConsultarRegistrosRecSpring extends AbstractSpring {

	/** La constante BUNDLE_MESSAGES. */
	//Declarar los resource
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConsultarRegistrosRecSpring.class);
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 30000;
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_ORG_APACHE_SPRING_ERROR. */
	private static final String STRING_ORG_APACHE_SPRING_ERROR = "org.apache.spring.ERROR";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** el properties. */
	private Properties properties;

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;

	/** el buscar reg elec WS. */
	//WebService
	private BuscarRegistrosElectronicos buscarRegElecWS;
	
	/**
	 * Crea una nueva buscar consultar registros rec spring.
	 */
	public BuscarConsultarRegistrosRecSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			} catch (Exception e) {
				logger.warn(e);
				logger.error("Error BuscarConsultarRegistrosRecSpring ",e);
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
		getLogger().debug("BuscarConsultarRegistrosRecAction - start");
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(BUNDLE_MESSAGES);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		String subMenu_aviso = RESOURCE_MESSAGES.getString("field.menuLateral.solicitudes.consultarRegistros");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_aviso);
		//******************************************************************
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		properties = (Properties) getBean("propertiesBean");
		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		//Cogemos el form del jsp
		ConsultarRegistrosRecForm formulario = (ConsultarRegistrosRecForm) form;
		
		//Obtener el campo del formulario		
		String numRegistro = formulario.getNumJustificante();
		
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		formulario.setNumJustificante("");
		
		//FIN Limpiado de formulario y muestra de resultados.
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("sVieneMenu", "S"); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		logger.info("NumRegistro: "+numRegistro);
		if (numRegistro == null || "".equals(numRegistro)) {
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultaRec.errores.errorJustificante");
			errors.add(STRING_ERRORCODIGO,error);
			this.getRequest().setAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
			return STRING_SUCCESS;
		}
		
		try {		
			
			//Criterios de busqueda necesarios para el WS
			CriteriosBusquedaType criterios = new CriteriosBusquedaType();
			criterios.setCdAsunto(properties.getProperty("dsAsunto"));
			criterios.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
			criterios.setNmRegistro(numRegistro);
			logger.info("Registro Buscado:" + numRegistro);
			
			String idAplicacion = properties.getProperty("idAplicacion");
			String password = properties.getProperty("password");
			
			AutenticacionType autentication = new AutenticacionType();
			autentication.setIdAplicacion(idAplicacion);
			autentication.setPassword(password);
			
			BuscarRegistrosElectronicosServiceLocator locator = new BuscarRegistrosElectronicosServiceLocator();
			locator.setBuscarRegistrosElectronicosServiceEndpointAddress(properties.getProperty("conf.url_buscarRegistrosElectronicos"));

			BuscarRegistrosElectronicosServiceSoapBindingStub binding = (BuscarRegistrosElectronicosServiceSoapBindingStub) locator.getBuscarRegistrosElectronicosService();
			binding.setTimeout(TIMEOUT);
			
			JustificanteType[] resultado = binding.buscarRegistro(autentication, criterios);
			RespuestaType respuestaType=resultado[0].getRespuesta();
			

			List <RegistroRecBean> listRegistroRec = new ArrayList<RegistroRecBean>();
			if (resultado != null && !"".equals(Arrays.toString(resultado)) && resultado.length != 0) {
				logger.info("Se han obtenido "+resultado.length+ " resultados");

				if (respuestaType.getCdRespuesta() != null && !"".equals(respuestaType.getCdRespuesta()) && !"00".equals(respuestaType.getCdRespuesta())) {
					
					logger.error("justificanteType.isExisteError() is true");
					if(null!=respuestaType.getDsRespuesta()){	
						logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());
					}
					
					//Se ha producido un error en el WS
					if (respuestaType.getCdRespuesta().equals("01")) {
						SpringMessages errors = new SpringMessages();					
						SpringMessage error = new SpringMessage("field.consultaRec.errores.sinResultados");
						errors.add(STRING_ERRORCODIGO,error);
						formulario.setNumJustificante(numRegistro);
						setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
						return STRING_SUCCESS;
					}else {
						SpringMessages errors = new SpringMessages();					
						SpringMessage error = new SpringMessage("field.consultaRec.errores.NoExiste");
						errors.add(STRING_ERRORCODIGO,error);
						setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
						return STRING_SUCCESS;
					}					
				}else {
					//Han llegado datos				
					int tamanio = resultado.length;
					String formato = "dd/MM/yyyy HH:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(formato);
					for (int i=0; i<tamanio; i++){
						JustificanteType registro;
						RegistroRecBean registroRecBean = new RegistroRecBean();
						registro = resultado[i];
						
						//Seteamos en el bean los campos obtenidos a traves del WS
						if (registro.getFeRegistro() != null) {
							Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(registro.getFeRegistro());
							registroRecBean.setFeRegistro(sdf.format(date));
						}
						registroRecBean.setNumRegistro(registro.getNmRegistro());
						registroRecBean.setCdOficinaOrigen(registro.getCdOficinaOrigen());
						
						//Añadimos a la lista
						listRegistroRec.add(registroRecBean);						
					}
				}
			}else {
				logger.info("No se han encontrado resultados en la busqueda");
				//resultado vacia
				SpringMessages errors = new SpringMessages();					
				SpringMessage error = new SpringMessage("field.consultaRec.errores.sinResultados");
				errors.add(STRING_ERRORCODIGO,error);
				setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
				return STRING_SUCCESS;
			}
			setRequestAttribute("registros", listRegistroRec);
			
		}catch (Exception e) {
			logger.error("Error BuscarConsultarRegistrosRecSpring - Error en llamada a WebService buscarRegistrosElectronicos",e);
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultaRec.errores.NoExiste");
			errors.add(STRING_ERRORCODIGO,error);
			setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
			return STRING_SUCCESS;
		}	
		
		getLogger().debug("BuscarConsultarRegistrosRecAction - end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarConsultarRegistrosRecSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGES.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		return STRING_SUCCESS;
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
			logger.error("Error BuscarConsultarRegistrosRecSpring - recuperarUsuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}

	/**
	 * Funciones Auxiliares.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR)){
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE)){
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR)){
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR)){
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}

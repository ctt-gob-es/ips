package es.map.ipsg.action.solicitud;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ConsultarRegistrosPagoAeatForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.pasarelapago.service.DatosPagoIM;
import es.map.pasarelapago.service.IntegracionMixtaGenericException;
import es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException;
import es.map.pasarelapago.service.IntegracionMixtaNoPagadoException;
import es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException;
import es.map.pasarelapago.service.ServicioIntegracion;
import es.map.pasarelapago.service.ServicioIntegracionProxy;


/**
 * El Class BuscarConsultarPagosAeatSpring.
 */
public class BuscarConsultarPagosAeatSpring extends AbstractSpring {

	/** La constante BUNDLE_MESSAGES. */
	//Declarar los resource
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_ERRORCONSULTA. */
	private static final String STRING_ERRORCONSULTA = "errorConsulta";
	
	/** La constante STRING_MENSAJE. */
	private static final String STRING_MENSAJE = "mensaje";
	
	/** La constante STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA. */
	private static final String STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA = "Error BuscarConsultarPagosAeatSpring - pasarelaPago.WS.resultadoConsulta :";
	
	/** La constante STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA. */
	private static final String STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA = "field.pasarelaPago.WS.resultadoConsulta";
	
	/** La constante STRING_CODIGOERROR. */
	private static final String STRING_CODIGOERROR= "codigoError";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	
	/** La constante STRING_EL_ERROR_ES. */
	private static final String STRING_EL_ERROR_ES = "El error es: ";
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConsultarPagosAeatSpring.class);

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;

	/** el servicio integracion. */
	//WebService
	private ServicioIntegracion servicioIntegracion;
	
	/**
	 * Crea una nueva buscar consultar pagos aeat spring.
	 */
	public BuscarConsultarPagosAeatSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				properties = (Properties) getBean("propertiesBean");
			} catch (Exception e) {
				logger.warn(e);
				logger.error("Error BuscarConsultarPagosAeatSpring:",e);
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
		String subMenu_consultarPagoAeat = RESOURCE_MESSAGES.getString("field.menuLateral.solicitudes.consultarPagos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_consultarPagoAeat);
		//******************************************************************
	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
				
		//Cogemos el form del jsp
		ConsultarRegistrosPagoAeatForm formulario = (ConsultarRegistrosPagoAeatForm) form;
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		
		if(sVieneMenu != null && sVieneMenu.equals("S")){
			formulario.setNif("");
			formulario.setNumJustificante("");
		}
		
		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		

		
		String numJustificante = formulario.getNumJustificante();
		String nif = formulario.getNif();
		
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("sVieneMenu", "S"); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		
		if (numJustificante == null || "".equals(numJustificante)) {
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultarPagoAeat.errores.justificante.vacio");
			errors.add(STRING_ERRORCODIGO,error);
			saveErrors(this.getRequest().getSession(),errors);
			return STRING_SUCCESS;
		}
		
		if (nif == null || "".equals(nif)) {
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultarPagoAeat.errores.nif.vacio");
			errors.add(STRING_ERRORCODIGO,error);
			saveErrors(this.getRequest().getSession(),errors);
			return STRING_SUCCESS;
		}		
		
		
		try {		
			//Llamada al WebService
			String direccionWS = properties.getProperty("conf.consultaPago.WS.direccion");
			servicioIntegracion = new ServicioIntegracionProxy(direccionWS);
			
			
			
			DatosPagoIM datosPagoIM = servicioIntegracion.consultarPago(numJustificante, nif, null, "MAP");
			List <DatosPagoIM> listPagoAeat = new ArrayList<DatosPagoIM>();
			if (datosPagoIM != null) {
				listPagoAeat.add(datosPagoIM);
			}else {
				
				//resultado vacia
				setRequestAttribute(STRING_ERRORCONSULTA, "true");
				setRequestAttribute(STRING_MENSAJE, RESOURCE_BUNDLE_2.getString("field.consultarPagoAeat.errores.sinResultados"));
	
				return STRING_SUCCESS;
			}
			setRequestAttribute("registros", listPagoAeat);
			
		}catch (IntegracionMixtaPagoNotFoundException nof){
			logger.error(STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA,nof);
			setRequestAttribute(STRING_ERRORCONSULTA, "true");
			setRequestAttribute(STRING_MENSAJE, RESOURCE_BUNDLE_2.getString(STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA));
			setRequestAttribute(STRING_CODIGOERROR, nof.getErrorCode());
			setRequestAttribute(STRING_DESCRIPCIONERROR, nof.getErrorCodeTexto());

			return STRING_SUCCESS;
		}catch (IntegracionMixtaNoPagadoException nop){
			logger.info(STRING_EL_ERROR_ES +nop.getErrorCode() + " "+nop.getErrorCodeTexto());
			logger.error(STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA,nop);
			setRequestAttribute(STRING_ERRORCONSULTA, "true");
			setRequestAttribute(STRING_MENSAJE, RESOURCE_BUNDLE_2.getString(STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA));
			setRequestAttribute(STRING_CODIGOERROR, nop.getErrorCode());
			setRequestAttribute(STRING_DESCRIPCIONERROR, nop.getErrorCodeTexto());

			return STRING_SUCCESS;
		}catch (IntegracionMixtaIncidenciaPagoException inc){
			logger.info(STRING_EL_ERROR_ES +inc.getErrorCode() + " "+inc.getErrorCodeTexto());
			logger.error(STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA,inc);
			setRequestAttribute(STRING_ERRORCONSULTA, "true");
			setRequestAttribute(STRING_MENSAJE, RESOURCE_BUNDLE_2.getString(STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA));
			setRequestAttribute(STRING_CODIGOERROR, inc.getErrorCode());
			setRequestAttribute(STRING_DESCRIPCIONERROR, inc.getErrorCodeTexto());

			return STRING_SUCCESS;
		}catch (IntegracionMixtaGenericException gen){
			logger.info(STRING_EL_ERROR_ES +gen.getErrorCode() + " "+gen.getErrorCodeTexto());
			logger.error(STRING_ERROR_PASARELAPAGO_WS_RESULTADOCONSULTA,gen);
			setRequestAttribute(STRING_ERRORCONSULTA, "true");
			setRequestAttribute(STRING_MENSAJE, RESOURCE_BUNDLE_2.getString(STRING_FIELD_PASARELAPAGO_WS_RESULTADOCONSULTA));
			setRequestAttribute(STRING_CODIGOERROR, gen.getErrorCode());
			setRequestAttribute(STRING_DESCRIPCIONERROR, gen.getErrorCodeTexto());
			
			return STRING_SUCCESS;
		}catch (RemoteException e) {
			
			logger.error("Error BuscarConsultarPagosAeatSpring - Error en llamada a WebService Consulta Pago AEAT (excepcion normal)",e);
			
			SpringMessages errors = new SpringMessages();					
			SpringMessage error = new SpringMessage("field.consultarPagoAeat.errores.falloWS");
			errors.add(STRING_ERRORCODIGO,error);
			saveErrors(this.getRequest().getSession(),errors);
			return STRING_SUCCESS;
		}
		
		
		getLogger().debug("BuscarConsultarRegistrosRecAction - end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarConsultarPagosAeatSpring - doExecute",eGenerico);
		this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_MESSAGES.getString("field.errorGenerico"));
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
			logger.error("Error BuscarConsultarPagosAeatSpring - recuperarUsuarioSesion:"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	/*
	 * Funciones Auxiliares
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
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

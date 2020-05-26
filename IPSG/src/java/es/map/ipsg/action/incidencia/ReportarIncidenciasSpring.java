package es.map.ipsg.action.incidencia;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.IncidenciasForm;
import es.map.ipsg.manager.IncidenciasManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;


/**
 * El Class ReportarIncidenciasSpring.
 */
public class ReportarIncidenciasSpring extends AbstractSpring {
	
	/** el incidencias manager. */
	IncidenciasManager incidenciasManager;
	
	/** el usuario manager. */
	UsuarioManager usuarioManager;
	
	/** el parametro configuracion manager. */
	ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ReportarIncidenciasSpring.class);
	
		
	/**
	 * Crea una nueva reportar incidencias spring.
	 */
	public ReportarIncidenciasSpring() {
		try{
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ReportarIncidenciasSpring()",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		
		getLogger().debug("ReportarIncidenciasSpring -start");
				
		try{
			IncidenciasForm formulario = (IncidenciasForm) form;		
			String username = recuperarUsuario();
			
			if (username.equals("error")) {
				return username;
			}
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(username);
			UsuarioBean auxUsuario = usuarioManager.buscarUsuario(usuarioQuery);
			
			formulario.setId(auxUsuario.getId());
			formulario.setNombre(auxUsuario.getNombre());
			formulario.setNif(auxUsuario.getNif());
			formulario.setTelefono(auxUsuario.getTelefono());
			formulario.setEmail(auxUsuario.getEmail());					
			formulario.setPrimerApellido(auxUsuario.getPrimerApellido());
			formulario.setSegundoApellido(auxUsuario.getSegundoApellido());
			
			String texto = formulario.getTexto();
			
			if(texto != null && !"".equals(texto)){
				ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
				if(auxUsuario.getIdPerfil()!= Constantes.PERFIL_ADMINISTRADOR_INT){
					paramConfQuery.setNombreCampo(Constantes.CORREO_INCIDENCIA_USUARIOS);
				}else{
					paramConfQuery.setNombreCampo(Constantes.CORREO_INCIDENCIA_ADMIN);
				}
				ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
				
				CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
				correoElectronicoBean.setDe(properties.getProperty("mail.de"));
				correoElectronicoBean.setPara(parametrosConfiguracion.getValorCampo());
				correoElectronicoBean.setAsunto(RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.asunto") + " " + auxUsuario.getNombre() + " " + auxUsuario.getPrimerApellido() + " " + auxUsuario.getSegundoApellido());
				
				String texto1 			= RESOURCE_BUNDLE.getString("field.incidencias.cuerpo1");
				String br 				= RESOURCE_BUNDLE.getString("texto.br");
				String labelNombre 		= RESOURCE_BUNDLE.getString("field.incidencias.nombre");
				String labelMail 		= RESOURCE_BUNDLE.getString("field.incidencias.email");
				String labelTelefono 	= RESOURCE_BUNDLE.getString("field.incidencias.telefono");
				String labelNif 		= RESOURCE_BUNDLE.getString("field.incidencias.nif");
				
				String nombreIncidencia = labelNombre+ " " +auxUsuario.getNombre() + " " + auxUsuario.getPrimerApellido() + " " + auxUsuario.getSegundoApellido()+br;
				String nif = labelNif+ " " +auxUsuario.getNif()+br;
				String email = labelMail+ " " +formulario.getEmail()+br;
				String telefono = labelTelefono+ " " +formulario.getTelefono()+br+br;
				String texto2 = RESOURCE_BUNDLE.getString("field.incidencias.cuerpo2");
				String texto3 = formulario.getTexto();
				String textoFinal = texto1 + br + nombreIncidencia + nif + email + telefono + texto2 + br + texto3;
				
				correoElectronicoBean.setMensaje(textoFinal);
				correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_PENDIENTE);
				
				boolean result = EnvioMails.envioMail(correoElectronicoBean);
				
				if(result == false){
					correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_PENDIENTE);
					setRequestAttribute("mensajeError" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.incorrecto"));
				}else{
					correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);
					setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.correcto"));
				}
				
				
			}
			setRequestAttribute("incidencia", formulario);	
			getLogger().debug("ReportarIncidenciasSpring -end");
			
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ReportarIncidenciasSpring - doExecute" ,e);
			return "nosuccess";
		}
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String username = null;
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			username = usuarioBean.getLogin();
			return username;
		}catch(Exception e){
			logger.error("Error ReportarIncidenciasSpring - recuperarUsuarioSesion" + username,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}


	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return el incidencias manager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager el nuevo incidencias manager
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
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
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
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

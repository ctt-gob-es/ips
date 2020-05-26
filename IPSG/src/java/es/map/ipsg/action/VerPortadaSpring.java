package es.map.ipsg.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogAccesoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.LogAccesoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class VerPortadaSpring.
 */
public class VerPortadaSpring extends AbstractSpring {

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log acceso manager. */
	private LogAccesoManager logAccesoManager;
	
	/** el alerta manager. */
	private AlertaManager alertaManager;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerPortadaSpring.class);
	
	
	/** La constante STRING_MOSTRARALERTAS. */
	private static final String STRING_MOSTRARALERTAS = "mostrarAlertas";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";

	/**
	 * Crea una nueva ver portada spring.
	 */
	public VerPortadaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogAccesoManager((LogAccesoManager) getBean("logAccesoManager"));
			setAlertaManager((AlertaManager) getBean("alertaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error en VerPortadaSpring:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("VerPortadaSpring -start");
		logger.info("Entra en el Action VerPortada");
		
		String username = null;
		try{
			/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING */
			/* ****************************************** */
			username = recuperarUsuario();
			
			/* ****************************************** */
			/*GUARDO EL LOG DEL ACCESO*/
			LogAccesoBean logAccesoBean = new LogAccesoBean();
			logAccesoBean.setLoginUsuario(username);
			logAccesoBean.setFecha(new Date());
			logAccesoBean.setIdCodigoAcceso(Constantes.CODIGO_ACCESO_EXITO);
			
			if(username != null && !username.equals(""))
			{	
				logAccesoManager.guardarAcceso(logAccesoBean);
			}
			SpringMessages mensajes = new SpringMessages(); 
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(username);
			
			UsuarioBean usuarioBean = null;
			
			try {
				logger.info("VerPortada - Antes de buscar usuarios");
				usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			} catch (Exception e) {
				//Si ha ocurrido un error al buscar el usuario devolvemos al login
				logger.error("Error al obtener el usuario de la tabla USUARIOS.");
			}
			logger.info("VerPortada - Despues de buscar usuarios");
			//Si ha dado error o no se ha encontrado un usuario en BD
			if (usuarioBean == null || (usuarioBean != null && usuarioBean.getEstado() == '0')) {
//				getSession().setAttribute("usuario", null);
//				getSession().setAttribute("NIF", null);
//				getSession().setAttribute("LOGIN", null);
//				getSession().setAttribute("SPRING_SECURITY_CONTEXT", null);
//				Exception exception = new Exception("Bad credentials");
//				getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
				return "errorUser";
			}
			
			logger.info("Se ha conectado el usuario con los siguientes datos -> Login: "+usuarioBean.getLogin()+" . Perfil: "+usuarioBean.getDesPerfil());
			
			this.getRequest().getSession().setAttribute("usuario", usuarioBean);
			
			alertaManager.comprobarAlertas(usuarioBean,mensajes);
			saveMessages(this.getRequest().getSession(), mensajes);
			
			
			if(mensajes.size()>0){
				setRequestAttribute(STRING_MOSTRARALERTAS, STRING_MOSTRARALERTAS);	
			}else{
				setRequestAttribute(STRING_MOSTRARALERTAS, "");
			}
	
			String referer = this.getRequest().getHeader("referer");
			
			if(referer != null && !referer.contains("certificado")){//Si viene por certificado no se tiene que validarse la ultima fecha de actualizacion del password
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(Calendar.DATE, -15);
				Date fechaActual= calendar.getTime();
			
				Date fechaUltimaActualizacion = usuarioBean.getUltimaActPassword();
				
				if((fechaUltimaActualizacion==null) || (fechaUltimaActualizacion.before(fechaActual))){
					 return "passwd";
				}
			}
			
			getLogger().debug("VerPortadaSpring -end");
			
			switch(String.valueOf(usuarioBean.getIdPerfil())) {
			case Constantes.PERFIL_ADMINISTRADOR :				
				return STRING_SUCCESS;
			case Constantes.PERFIL_GESTOR :
				return STRING_SUCCESS;
			case Constantes.PERFIL_SOPORTE :
				return "successSoporteCons"; 
			case Constantes.PERFIL_CONSULTOR :
				return "successSoporteCons"; 				
			case Constantes.PERFIL_OPERADOR :
				return "successOperador";				
			case Constantes.PERFIL_EMPRESA :
				return "successEmpresa";
			case Constantes.PERFIL_REGISTRO :
				return "successRegistro";
			default:
				break;
			 
		}		
		
			return STRING_SUCCESS;
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerPortada  guardando el log de acceso:", e);
			return "nosuccess";
		}
	}	
	
	/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING */
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	/* ****************************************** */
	private String recuperarUsuario() {
		try{
			SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
			
			AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			
			// Obtenemos el usuario de base de datos a partir del identificador obtenido de autentica
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setNif(user.getIdentificador());
			UsuarioBean userIPS = usuarioManager.buscarUsuario(usuarioQuery);
			
			
			return userIPS.getLogin();
			
//			org.springframework.security.autentica.authentication.AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//			return user.getIdentificador();
		}catch(Exception e){
			logger.error("Error en recuperarUsuarioSesion:", e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	

	/**
	 * Obtiene el alerta manager.
	 *
	 * @return the alertaManager
	 */
	public AlertaManager getAlertaManager() {
		return alertaManager;
	}

	/**
	 * Establece el alerta manager.
	 *
	 * @param alertaManager the alertaManager to set
	 */
	public void setAlertaManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
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
	 * Establece el log acceso manager.
	 *
	 * @param logAccesoManager el nuevo log acceso manager
	 */
	public void setLogAccesoManager(LogAccesoManager logAccesoManager){
		this.logAccesoManager = logAccesoManager;
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

package es.map.ipsg.action;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogAccesoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.LogAccesoManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class InicioSpring.
 */
public class InicioSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(InicioSpring.class);
	
	/** el log acceso manager. */
	private LogAccesoManager logAccesoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * Crea una nueva inicio spring.
	 */
	public InicioSpring(){
		try {
			setLogAccesoManager((LogAccesoManager) getBean("logAccesoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.error("Error en  InicioSpring:", e);
		}
	}

	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Llega a InicioAction");
		Integer error = (Integer) this.getRequest().getSession().getAttribute("login_error");
		this.getRequest().getSession().setAttribute("login_error", null);
		
		if(error != null){
			String username = (String) this.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME");
//			Exception exception = (Exception) this.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//			String message = exception.getMessage();
			
			int codigoError=0;
			if(error == 1){
				codigoError = 2;
				logger.error("Security-Error tipo 2");
				Exception exception = new Exception("Bad credentials");
				this.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
			}else if(error == 2){
				codigoError = 3;
				logger.error("Security-Error tipo 3");
				Exception exception = new Exception("User is disabled");
				this.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
			}else{
				codigoError = 4;
				logger.error("Security-Error tipo 4");
			}

			LogAccesoBean logAccesoBean = new LogAccesoBean();
			logAccesoBean.setFecha(new Date());
			logAccesoBean.setLoginUsuario(username);
			logAccesoBean.setIdCodigoAcceso(codigoError);
			
			if(username != null && !username.equals(""))
			{	
				logAccesoManager.guardarAcceso(logAccesoBean);
			}	
		} else {
			SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			if (context != null) {
				//Comprobar si existe el usuario - INI
				Integer numUsuarios = comprobarExistenciaUsuario(context);		
				//Comprobar si existe el usuario - INI
				if (numUsuarios != null && numUsuarios == 1) {
					return "successUser"; 
				} else {
					UsuarioBean usuarioBean = buscarUsuarioAplicacion(context);
					
					logger.error("Security-Error tipo 5");
					Exception exception = new Exception("User no exists");
					if (usuarioBean != null && usuarioBean != null && usuarioBean.getEstado() == '0') {
						exception = new Exception("User is disabled");
					} else if (usuarioBean != null && usuarioBean != null 
							&& usuarioBean.getEstado() == '1' && StringUtils.isEmpty(usuarioBean.getLogin())) {
						exception = new Exception("User is receptor");
					}
					this.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
				}
			}		
		}
		logger.info("Sale de InicioAction");
		return "success";
	}


	/**
	 * Buscar usuario aplicacion.
	 *
	 * @param context el context
	 * @return el usuario bean
	 */
	private UsuarioBean buscarUsuarioAplicacion(SecurityContext context) {
		AutenticaAuthenticationToken user = obtenerUsuario(context);
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(user.getIdentificador());
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean;
	}

	/**
	 * Comprueba si existe el usuario en BD.
	 *
	 * @param context el context
	 * @return el integer
	 */
	private Integer comprobarExistenciaUsuario(SecurityContext context) {
		logger.info("entrando en comprobarExistenciaUsuario");
		AutenticaAuthenticationToken user = obtenerUsuario(context);
		// Obtenemos el usuario de base de datos a partir del identificador obtenido de autentica
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(user.getIdentificador());
		usuarioQuery.setJoin_perfil(true);
		usuarioQuery.addPerfilIn(1);
		usuarioQuery.addPerfilIn(2);
		usuarioQuery.addPerfilIn(3);
		usuarioQuery.addPerfilIn(4);
		usuarioQuery.addPerfilIn(6);
		usuarioQuery.addPerfilIn(8);
		usuarioQuery.addPerfilIn(9);
		usuarioQuery.setEstado('1');
		
		Integer numUsuarios = usuarioManager.obtenerNumeroUsuarios(usuarioQuery);
		logger.info("saliendo de comprobarExistenciaUsuario");
		return numUsuarios;
	}


	/**
	 * Obtener usuario.
	 *
	 * @param context el context
	 * @return el autentica authentication token
	 */
	private AutenticaAuthenticationToken obtenerUsuario(SecurityContext context) {
		SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
		
		AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		return user;
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
	 * Establece el log acceso manager.
	 *
	 * @param logAccesoManager el nuevo log acceso manager
	 */
	public void setLogAccesoManager(LogAccesoManager logAccesoManager){
		this.logAccesoManager = logAccesoManager;
	}
	
	/**
	 * Obtiene el log acceso manager.
	 *
	 * @return el log acceso manager
	 */
	public LogAccesoManager getLogAccesoManager(){
		return logAccesoManager;
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

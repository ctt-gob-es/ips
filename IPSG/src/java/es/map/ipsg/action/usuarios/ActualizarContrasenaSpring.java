package es.map.ipsg.action.usuarios;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ActualizarContrasenaForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class ActualizarContrasenaSpring.
 */
public class ActualizarContrasenaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarContrasenaSpring.class);
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/**
	 * Crea una nueva actualizar contrasena spring.
	 */
	public ActualizarContrasenaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ActualizarContrasenaSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("ActualizarContrasenaSpring -start");
		logger.info("Entra en el Action");
		
		ActualizarContrasenaForm formulario = (ActualizarContrasenaForm) form;
	
		try{
			String passwordNueva = formulario.getPasswordNueva();
			UsuarioBean usuarioBean ;
			/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING SI EL FORMULARIO NO TIENE INFORMADO EL ID*/
			/* ****************************************** */
			
			if(formulario.getId()==null){
				try{
					String username = null;
					SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
					
					UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					username = usuarioSession.getLogin();
					UsuarioQuery usuarioQuery = new UsuarioQuery();
					usuarioQuery.setLogin(username);
					
					 usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
					formulario.setId(usuarioBean.getId().toString());
				}catch(Exception e){
					logger.error("Error ActualizarContrasenaSpring - recuperar UsuarioSesion",e);
					new BusinessException("exception.recuperarUsuarioSesion");
					return STRING_NOSUCCESS;
				}
			}else{
				Integer id = Integer.valueOf(formulario.getId());
				
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				usuarioQuery.setId(id);
				
				usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			}
			
			
			CryptUtil cryptUtil = new CryptUtil();
			
			String passwordNuevaCifrada = null;
			
			String token = usuarioBean.getToken();

			if(token != null)
			{	
				String passSalt = passwordNueva.concat(token);
				passwordNuevaCifrada = cryptUtil.cifrar(passSalt);
				
				logger.info("MD5(passwordNueva+token):"+passwordNuevaCifrada);
			}
			else
			{
				logger.error(RESOURCE_BUNDLE.getString("field.error.recuperarToken"));
				this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.error.recuperarToken"));
				return STRING_NOSUCCESS;
			}	
			
			usuarioBean.setPassword(passwordNuevaCifrada);
			usuarioBean.setUltimaActPassword(new Date());
			
			usuarioManager.modificarUsuario(usuarioBean);
			this.getRequest().setAttribute("mensajeConfirmacion", RESOURCE_BUNDLE.getString("field.usuario.confirmacionCambioPassword"));
			getLogger().debug("ActualizarContrasenaSpring -end");
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarContrasenaSpring - doExecute",e);
			return STRING_NOSUCCESS;}
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}

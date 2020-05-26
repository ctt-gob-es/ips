package es.map.ipsg.action.usuarios;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CambiarContrasenaForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class CambiarContrasenaSpring.
 */
public class CambiarContrasenaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CambiarContrasenaSpring.class);
	
	/** La constante STRING_FIELD_ERROR_RECUPERARTOKEN. */
	private static final String STRING_FIELD_ERROR_RECUPERARTOKEN = "field.error.recuperarToken";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/**
	 * Crea una nueva cambiar contrasena spring.
	 */
	public CambiarContrasenaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CambiarContrasenaSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("CambiarContrasenaSpring -start");
		logger.info("Entra en el Action");
		CambiarContrasenaForm formulario = (CambiarContrasenaForm) form;
		
		String passwordActual = formulario.getPasswordActual();
		String passwordNueva = formulario.getPasswordNueva();
		try{
			String username = null;
			
			/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING */
			/* ****************************************** */
			try{
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				username = usuarioBean.getLogin();
			}catch(Exception e){
				logger.error("Error CambiarContrasenaSpring - recuperar usuarioSesion"+username,e);
				new BusinessException("exception.recuperarUsuarioSesion");
				return "error";
			}
			/* ****************************************** */
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(username);
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);

			CryptUtil cryptUtil = new CryptUtil();
			
			String token = usuarioBean.getToken();
			String passwordActualCifrada = null;
			
			if(token != null)
			{	
				String passSalt = passwordActual.concat(token);
				passwordActualCifrada = cryptUtil.cifrar(passSalt);
				
				logger.info("MD5(passwordActual+token):"+passwordActualCifrada);
			}
			else
			{
				logger.error(RESOURCE_BUNDLE.getString(STRING_FIELD_ERROR_RECUPERARTOKEN));
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_BUNDLE.getString(STRING_FIELD_ERROR_RECUPERARTOKEN));
				logger.info("MD5(passwordActual+token):"+passwordActualCifrada);
				return STRING_NOSUCCESS;
			}	
		
			String passwordNuevaCifrada = null;
			
			if(passwordActualCifrada.equals(usuarioBean.getPassword())){
				// La contraseña guardada y la introducida coinciden
				if(token != null)
				{
					String passSaltNueva = passwordNueva.concat(token);
					passwordNuevaCifrada = cryptUtil.cifrar(passSaltNueva);
					
					logger.info("MD5(passwordNueva+token):"+passwordNuevaCifrada);
				}
				else
				{
					logger.error(RESOURCE_BUNDLE.getString(STRING_FIELD_ERROR_RECUPERARTOKEN));
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_BUNDLE.getString(STRING_FIELD_ERROR_RECUPERARTOKEN));
					return STRING_NOSUCCESS;
				}
				
				usuarioBean.setPassword(passwordNuevaCifrada);
				
				usuarioBean.setUltimaActPassword(new Date());
				
				usuarioManager.modificarUsuario(usuarioBean);
				this.getRequest().setAttribute("mensajeConfirmacion", RESOURCE_BUNDLE.getString("field.usuario.confirmacionCambioPassword"));
			}else{
				SpringMessages messages = new SpringMessages();
				messages.add("errorEqualPasswordActual",new SpringMessage("usuario.error.passwordActualDistinta"));
				saveErrors(this.getRequest(),messages);
			}
			getLogger().debug("CambiarContrasenaSpring -end");
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, e.getMessage());
			logger.error("Error CambiarContrasenaSpring - doExecute",e);
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

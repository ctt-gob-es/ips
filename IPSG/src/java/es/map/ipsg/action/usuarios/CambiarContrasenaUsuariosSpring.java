package es.map.ipsg.action.usuarios;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CambiarContrasenaUsuariosForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class CambiarContrasenaUsuariosSpring.
 */
public class CambiarContrasenaUsuariosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CambiarContrasenaUsuariosSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/**
	 * Crea una nueva cambiar contrasena usuarios spring.
	 */
	public CambiarContrasenaUsuariosSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CambiarContrasenaUsuariosSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("CambiarContrasenaUsuariosSpring -start");
		logger.info("Entra en el Action");
		CambiarContrasenaUsuariosForm formulario = (CambiarContrasenaUsuariosForm) form;
		try{
			String passwordNueva = formulario.getPasswordNueva();		
			Integer id = Integer.valueOf(formulario.getId());
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setId(id);
			
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
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
				return "nosuccess";
			}	
			
			usuarioBean.setPassword(passwordNuevaCifrada);
			
			usuarioBean.setUltimaActPassword(new Date());
			
			usuarioManager.modificarUsuario(usuarioBean);
			this.getRequest().setAttribute("mensajeConfirmacion", RESOURCE_BUNDLE.getString("field.usuario.confirmacionCambioPassword"));
			getLogger().debug("CambiarContrasenaUsuariosSpring -end");
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error CambiarContrasenaUsuariosSpring - doExecute",e);
			return "nosuccess";}
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

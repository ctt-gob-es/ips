package es.map.ipsg.action.usuariosAplicacion;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.manager.UsuarioAplicacionManager;


/**
 * El Class VerAltaUsuarioAplicacionSpring.
 */
public class VerAltaUsuarioAplicacionSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaUsuarioAplicacionSpring.class);
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/**
	 * Crea una nueva ver alta usuario aplicacion spring.
	 */
	public VerAltaUsuarioAplicacionSpring() {
		try {
			setUsuarioAplicacionManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaUsuarioAplicacionSpring() :",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_usuario = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.usuario");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuario);
		
		getLogger().debug("VerAltaUsuarioAplicacionSpring -start");
		logger.info("Entra en el Action");
		try {
			getLogger().debug("VerAltaUsuarioAplicacionSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerAltaUsuarioAplicacionSpring - doExecute",e);
			return "nosuccess";
			}
	}
	
	/**
	 * Obtiene el usuario aplicacion manager.
	 *
	 * @return the UsuarioAplicacionManager
	 */
	public UsuarioAplicacionManager getUsuarioAplicacionManager() {
		return usuarioAplicacionManager;
	}

	/**
	 * Establece el usuario aplicacion manager.
	 *
	 * @param usuarioAplicacionManager el nuevo usuario aplicacion manager
	 */
	public void setUsuarioAplicacionManager(
			UsuarioAplicacionManager usuarioAplicacionManager) {
		this.usuarioAplicacionManager = usuarioAplicacionManager;
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

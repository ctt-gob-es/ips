package es.map.ipsg.action.usuariosAplicacion;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.UsuarioAplicacionBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioAplicacionManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarUsuarioAplicacionSpring.
 */
public class EliminarUsuarioAplicacionSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaUsuarioAplicacionSpring.class);
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva eliminar usuario aplicacion spring.
	 */
	public EliminarUsuarioAplicacionSpring() {
		try {
			setUsuarioAplicacionManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarUsuarioAplicacionSpring - crear constructor",e);
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
		
		getLogger().debug("EliminarUsuarioAplicacionSpring -start");
		logger.info("Entra en el Action");
		
		String idUsuario = this.getRequest().getParameter("id");
		logger.info("Usuario a eliminar: "+idUsuario);
		
		try {
			UsuarioAplicacionBean usuarioAplicacionBean = usuarioAplicacionManager.obtenerUsuarioAplicacion(Integer.valueOf(idUsuario));
			usuarioAplicacionBean.setEstado(Constantes.USUARIO_ESTADO_INACTIVO);
			
			usuarioAplicacionManager.modificarUsuario(usuarioAplicacionBean);
			
			String mensaje = RESOURCE_MESSAGE.getString("field.usuario.eliminarUsuarioConfirmacion");
			String titulo = RESOURCE_MESSAGE.getString("field.usuario.tituloEliminarUsuario");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);

			getLogger().debug("EliminarUsuarioAplicacionSpring -end");
			
			return "success";
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarUsuarioSpring - obtener usuaria a eliminar",e);
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
	
	/**
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}
	
}

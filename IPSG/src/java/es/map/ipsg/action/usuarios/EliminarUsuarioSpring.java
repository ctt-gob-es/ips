package es.map.ipsg.action.usuarios;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarUsuarioSpring.
 */
public class EliminarUsuarioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarUsuarioSpring.class);
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva eliminar usuario spring.
	 */
	public EliminarUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarUsuarioSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_usuario = RESOURCE_BUNDLE.getString("field.menu.usuarios");
		this.getRequest().getSession().setAttribute("pagActiva", menu_usuario);
		String subMenu_usuarios = RESOURCE_BUNDLE.getString("field.menuLateral.usuarios.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuarios);
		
		getLogger().debug("EliminarUsuarioSpring -start");
		logger.info("Entra en el Action");
				
		String idUsuario = this.getRequest().getParameter("id");
		logger.info("Usuario a eliminar: "+idUsuario);

		try{	
			UsuarioBean usuarioBean = usuarioManager.obtenerUsuario(Integer.valueOf(idUsuario));
			usuarioBean.setEstado(Constantes.USUARIO_ESTADO_INACTIVO);
			
			usuarioManager.modificarUsuario(usuarioBean);
			
			String mensaje = RESOURCE_BUNDLE.getString("field.usuario.eliminarUsuarioConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.usuario.tituloEliminarUsuario");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarUsuarios");
			
			UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioSession.getLogin(),new Long(usuarioBean.getId()));
			getLogger().debug("EliminarUsuarioSpring -end");
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarUsuarioSpring - obtener usuaria a eliminar",e);
			return "nosuccess";}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idUsuario el id usuario
	 */
	public void generarRegistroLogGenerico(String username, Long idUsuario){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_USUARIO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.usuario.detalleOperacionBaja") + " " + idUsuario.toString());
		logGenericoBean.setIdTablaOrigen(idUsuario);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}

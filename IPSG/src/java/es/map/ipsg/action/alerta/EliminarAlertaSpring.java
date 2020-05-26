package es.map.ipsg.action.alerta;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarAlertaSpring.
 */
public class EliminarAlertaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarAlertaSpring.class);
	
	/** La constante ESTADO_INACTIVO. */
	private static final Character ESTADO_INACTIVO = 'D';

	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/**
	 * Crea una nueva eliminar alerta spring.
	 */
	public EliminarAlertaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setAlertaManager((AlertaManager) getBean("alertaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (final Exception e) {
			logger.warn(e);
			logger.error("Error Eliminar alerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(final SpringForm form) throws Exception {
		logger.info("Comienza Eliminar Alerta Accion");
	try{
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		 getLogger().debug("EliminarAlertaSpring -start");SpringMessages errors = new SpringMessages();
		 String idAlerta = this.getRequest().getParameter("id");
		 AlertaBean alertaBean = alertaManager.obtenerAlerta(Integer.valueOf(idAlerta));
		 alertaBean.setEstado(ESTADO_INACTIVO);
		 boolean bElimina = true;
				
		if (bElimina){
			alertaManager.modificarAlerta(alertaBean);
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			 generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(alertaBean.getId()));
		}
		else{
			saveErrors(this.getRequest().getSession(),errors);
			logger.info("Termina Eliminar Alerta Accion");
			return "errorEliminar";
		}
						
		final String mensaje = RESOURCE_BUNDLE.getString("field.alerta.eliminarAlertaConfirmacion");
		final String titulo = RESOURCE_BUNDLE.getString("field.alerta.tituloEliminarAlerta");			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarAlerta");
		getLogger().debug("EliminarAlertaSpring -end");
		logger.info("Termina Eliminar Alerta Accion");
		
	}catch(Exception eGenerico){
		logger.error("Error eliminar alerta Accion: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idAlerta el id alerta
	 */
	public void generarRegistroLogGenerico(final String username, final Long idAlerta){
		
		final UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		final UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		final LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ALERTA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.alerta.detalleOperacionBaja") + " " +idAlerta);
		logGenericoBean.setIdTablaOrigen(idAlerta);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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
	 * Obtiene el log generico manager.
	 *
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el message resource.
	 *
	 * @return the mESSAGE_RESOURCE
	 */
	public static String getMESSAGE_RESOURCE() {
		return MESSAGE_RESOURCE;
	}

	/**
	 * Obtiene el resource bundle.
	 *
	 * @return the rESOURCE_BUNDLE
	 */
	public static ResourceBundle getRESOURCE_BUNDLE() {
		return RESOURCE_BUNDLE;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}

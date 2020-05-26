package es.map.ipsg.action.entidadFinanciera;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarEntidadFinancieraSpring.
 */
public class EliminarEntidadFinancieraSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarEntidadFinancieraSpring.class);
	
	/** el entidad financiera manager. */
	//Declaracion de manager
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;	
	
	/**
	 * Crea una nueva eliminar entidad financiera spring.
	 */
	public EliminarEntidadFinancieraSpring() {
		try {
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarEntidadFinancieraSpring() :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_EntidadFinanciera = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.entidadFinanciera");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_EntidadFinanciera);
		
		getLogger().debug("EliminarEntidadFinancieraSpring -start");
	try{
		String idEntidadFinanciera = this.getRequest().getParameter("id");
			
		List<EntidadFinancieraBean> lEntidadFinancieraBean = null;
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setId(Integer.valueOf(idEntidadFinanciera));
		
		lEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);		
		EntidadFinancieraBean entidadFinancieraBean = lEntidadFinancieraBean.get(0);
		if (entidadFinancieraBean.getEstado() == Constantes.ENTIDAD_FINANCIERA_ESTADO_INACTIVO) {
			entidadFinancieraBean.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		} else {
			entidadFinancieraBean.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_INACTIVO);
		}
		entidadFinancieraManager.modificarEntidadFinanciera(entidadFinancieraBean);
		
		String mensaje = RESOURCE_BUNDLE.getString("field.entidadFinanciera.eliminarEntidadFinancieraConfirmacion");
		String titulo = RESOURCE_BUNDLE.getString("field.entidadFinanciera.titulo.EliminarEntidadFinanciera");			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(entidadFinancieraBean.getId()), entidadFinancieraBean.getEstado());
		getLogger().debug("EliminarEntidadFinancieraSpring -end");
	
	}catch(Exception eGenerico){
		logger.error("Error EliminarEntidadFinancieraSpring() - doExecute :",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idProvincia el id provincia
	 * @param estado el estado
	 */
	public void generarRegistroLogGenerico(String username, Long idProvincia, Character estado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ENTIDAD_FINANCIERA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.entidadFinanciera.detalleOperacionBaja") + " " + idProvincia.toString() + RESOURCE_BUNDLE.getString("field.entidadFinanciera.detalleOperacionEstado") + estado);
		logGenericoBean.setIdTablaOrigen(idProvincia);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
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

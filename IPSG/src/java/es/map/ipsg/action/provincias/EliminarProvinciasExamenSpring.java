package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarProvinciasExamenSpring.
 */
public class EliminarProvinciasExamenSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarProvinciasExamenSpring.class);
	
	/** el provincia examen manager. */
	//Declaracion de manager
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	
	/**
	 * Crea una nueva eliminar provincias examen spring.
	 */
	public EliminarProvinciasExamenSpring() {
		try {
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarProvinciasExamenSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provinciaExamen = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.provinciaExamen");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provinciaExamen);
		
		getLogger().debug("EliminarProvinciasSpring -start");
		String idProvincia = this.getRequest().getParameter("registro");
		try{	
			List<ProvinciaExamenBean> provinciasExamen = null;
			ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
			provinciaExamenQuery.setId(Integer.valueOf(idProvincia));
			
			provinciasExamen = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
			
			ProvinciaExamenBean provinciaExamenBean = provinciasExamen.get(0);
			
			provinciaExamenBean.setEstado(Constantes.PROVINCIA_ESTADO_INACTIVO);
			
			provinciaExamenManager.modificarProvinciaExamen(provinciaExamenBean);
			
			String mensaje = RESOURCE_BUNDLE.getString("field.provincia.eliminarProvinciaConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.provincia.tituloEliminarProvincia");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarUsuarios");	
			
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(provinciaExamenBean.getId()));
			getLogger().debug("EliminarProvinciasSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarProvinciasExamenSpring - doExecute :",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idProvincia el id provincia
	 */
	public void generarRegistroLogGenerico(String username, Long idProvincia){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_PROVINCIAS);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.provincia.detalleOperacionBaja") + " "  + idProvincia.toString());
		logGenericoBean.setIdTablaOrigen(idProvincia);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}


	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
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

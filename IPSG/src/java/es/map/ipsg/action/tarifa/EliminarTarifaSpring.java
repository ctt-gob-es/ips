package es.map.ipsg.action.tarifa;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarTarifaSpring.
 */
public class EliminarTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarTarifaSpring.class);
	
	/** La constante ESTADO_INACTIVO. */
	private static final Character ESTADO_INACTIVO = 'D';


	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva eliminar tarifa spring.
	 */
	public EliminarTarifaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarTarifaSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_cuerposEscala = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_cuerposEscala);
		String subMenu_tarifa = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tarifa");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tarifa);
		
		getLogger().debug("EliminarCuerposEscalaSpring -start");
	try{
		SpringMessages errors = new SpringMessages();
		
		String idTarifa = this.getRequest().getParameter("id");
			
		TarifaBean tarifaBean = tarifaManager.obtenerTarifa(Integer.valueOf(idTarifa));
		tarifaBean.setEstado(ESTADO_INACTIVO);
		boolean bElimina = true;
				
		if (bElimina){
			tarifaManager.modificarTarifa(tarifaBean);
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),new Long(tarifaBean.getId()));
		}
		else{
			saveErrors(this.getRequest().getSession(),errors);
			return "errorEliminar";
		}
						
		String mensaje = RESOURCE_BUNDLE.getString("field.tarifa.eliminarTarifaConfirmacion");
		String titulo = RESOURCE_BUNDLE.getString("field.tarifa.tituloEliminarTarifa");			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarTarifa");
		
		getLogger().debug("EliminarTarifaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error EliminarTarifaSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idTarifa el id tarifa
	 */
	public void generarRegistroLogGenerico(String username, Long idTarifa){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_TARIFA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.tarifa.detalleOperacionBaja") + " "  + idTarifa);
		logGenericoBean.setIdTablaOrigen(idTarifa);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return the tarifaManager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAccesoManager getTipoAcceso() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager the tarifaManager to set
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAccesoManager = tipoAcceso;
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
	
	
}

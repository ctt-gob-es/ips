package es.map.ipsg.action.formaAcceso;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoAcceso;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.cuerpoescala.ModificarCuerposEscalaSpring;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.FormasAccesoForm;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarFormasAccesoSpring.
 */
public class ModificarFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCuerposEscalaSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva modificar formas acceso spring.
	 */
	public ModificarFormasAccesoSpring() {
		try {
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));	
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.error("Error ModificarFormasAccesoSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_formaAcceso = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.formaAcceso");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_formaAcceso);
		
		getLogger().debug("ModificarFormasAccesoSpring -start");
		String resultado;
	try{
		FormasAccesoForm formulario = (FormasAccesoForm) form;
		String idFormasAcceso= formulario.getId();
		
		
	if(idFormasAcceso!=null){//Modificacion de Formas Acceso
			FormaAccesoBean formaAccesoBean = new FormaAccesoBean();
			
			formaAccesoBean.setId(Integer.valueOf(formulario.getId()));
			
			TipoAcceso tipoAcceso = new TipoAcceso();
			tipoAcceso.setId(Integer.valueOf(formulario.getIdTipoAcceso()));
			
			formaAccesoBean.setTipoAcceso(tipoAcceso);
			formaAccesoBean.setDescripcion(formulario.getDescripcion());

			formaAccesoBean.setCodigo(formulario.getCodigo());
			formaAccesoBean.setEstado(ESTADO_ACTIVO);
			if(formulario.getVisibilidad() != null)
			{	
				formaAccesoBean.setVisibilidad(formulario.getVisibilidad());
			}
			else
			{
				formaAccesoBean.setVisibilidad(false);
			}	
			
			TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
			List<TipoAccesoBean> tipoAccesoList = tipoAccesoManager.buscarTipoAccesoCombo(tipoAccesoQuery);
			
			this.setRequestAttribute("tipoAcceso", tipoAccesoList);
			formaAccesoManager.modificarFormaAcceso(formaAccesoBean);
			
		
			
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(formaAccesoBean.getId()));

			String mensaje = RESOURCE_BUNDLE.getString("field.formasAcceso.modificarFormasAccesoConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.formasAcceso.formaAccesoMantenimientoTitulo");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarFormasAcceso");
			
			resultado = "success";
		}else{
			resultado = "error";
		}
	
		getLogger().debug("ModificarFormasAccesoSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error ModificarFormasAccesoSpring() - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param idFormaAcceso el id forma acceso
 */
public void generarRegistroLogGenerico(String username, Long idFormaAcceso){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_FORMA_ACCESO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.formaAcceso.detalleOperacionModificacion") + " "  + idFormaAcceso.toString());
		logGenericoBean.setIdTablaOrigen(idFormaAcceso);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}



	/**
	 * Obtiene el tipo acceso manager.
	 *
	 * @return the tipoAccesoManager
	 */
	public TipoAccesoManager getTipoAccesoManager() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAccesoManager the tipoAccesoManager to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAccesoManager) {
		this.tipoAccesoManager = tipoAccesoManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return the formaAccesoManager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager the formaAccesoManager to set
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
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

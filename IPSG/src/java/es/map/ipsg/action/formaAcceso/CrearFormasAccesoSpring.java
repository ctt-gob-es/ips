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
 * El Class CrearFormasAccesoSpring.
 */
public class CrearFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearFormasAccesoSpring.class);
	
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
	 * Crea una nueva crear formas acceso spring.
	 */
	public CrearFormasAccesoSpring() {
		try {
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearFormasAccesoSpring() :",e);
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
		
		getLogger().debug("CrearFormasAccesoSpring -start");
		String resultado = "";
	try{
		FormasAccesoForm formulario = (FormasAccesoForm) form;
		
		FormaAccesoBean formaAccesoBean = new FormaAccesoBean();

		// Crear Forma Acceso		
		formaAccesoBean.setCodigo(formulario.getCodigo());
		formaAccesoBean.setDescripcion(formulario.getDescripcion());
		formaAccesoBean.setIdTipoAcceso(formulario.getIdTipoAcceso());
		if(formulario.getVisibilidad() != null)
		{	
			formaAccesoBean.setVisibilidad(formulario.getVisibilidad());
		}
		else
		{
			formaAccesoBean.setVisibilidad(false);
		}	
		
		TipoAcceso tipoAcceso = new TipoAcceso();
		tipoAcceso.setId(formulario.getIdTipoAcceso());
		
		formaAccesoBean.setTipoAcceso(tipoAcceso);
		
		formaAccesoBean.setEstado(ESTADO_ACTIVO);
		
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> formaAcceso = tipoAccesoManager.buscarTipoAccesoCombo(tipoAccesoQuery);
		
		this.setRequestAttribute("tipoAcceso", formaAcceso);	
				
		// Guardamos la Forma de Acceso
		int result = formaAccesoManager
				.guardarFormaAcceso(formaAccesoBean);

		String mensaje;			

		if (result > 0) {
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),formaAccesoBean.getDescripcion(), Long.valueOf(result));
			resultado = "success";
			mensaje = RESOURCE_BUNDLE
					.getString("field.formasAcceso.crearFormasAccesoConfirmacion");
		} else {
			resultado = "error";
			mensaje = RESOURCE_BUNDLE
					.getString("field.formasAcceso.crearFormasAccesoError");
		}

		String titulo = RESOURCE_BUNDLE
				.getString("field.formasAcceso.crearTituloFormasAcceso");

		setRequestAttribute("titulo", titulo);
		setRequestAttribute("mensaje", mensaje);
		setRequestAttribute("accion", "/spring/buscarFormasAcceso");
			
		getLogger().debug("CrearFormasAccesoSpring -end");
	
	}catch(Exception eGenerico){
		
		logger.error("Error CrearFormasAccesoSpring() -doExecute :",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param formaAccesoDesc el forma acceso desc
 * @param resultado el resultado
 */
public void generarRegistroLogGenerico(String username, String formaAccesoDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_FORMA_ACCESO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.formaAcceso.detalleOperacionAlta") + " "  + formaAccesoDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
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
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}

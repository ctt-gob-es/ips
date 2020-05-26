package es.map.ipsg.action.categoria;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.cuerpoescala.ModificarCuerposEscalaSpring;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CategoriaForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarCategoriaSpring.
 */
public class ModificarCategoriaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCuerposEscalaSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el categoria manager. */
	private CategoriaManager categoriaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva modificar categoria spring.
	 */
	public ModificarCategoriaSpring() {
		try {
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarCategoriaSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
	
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_Categoria = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.categoria");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Categoria);
		
		getLogger().debug("ModificarCategoriaSpring -start");
		String resultado;
	try{
		
		CategoriaForm formulario = (CategoriaForm) form;
		String idCategoria= formulario.getId();
		String accion = formulario.getAccion();
		
		if("VOLVER".equalsIgnoreCase(accion)){
			resultado = "list";
		}else if(idCategoria!=null){//Modificacion de Formas Acceso
			CategoriaBean categoriaBean  = new CategoriaBean ();
			
			categoriaBean.setId(Integer.valueOf(formulario.getId()));
			categoriaBean.setCodigo(formulario.getCodigo());
			categoriaBean.setDescripcion(formulario.getDescripcion());
			categoriaBean.setEstado(ESTADO_ACTIVO);
			categoriaBean.setVisibilidad(formulario.getVisibilidad());

			categoriaManager.modificarCategoria(categoriaBean);
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(categoriaBean.getId()));

			String mensaje = RESOURCE_BUNDLE.getString("field.categoria.modificarCategoriaConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.categoria.MantenimientoTitulo");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarCategoria");
			
			resultado = "success";
		}else{
			resultado = "error";
		}
	
		getLogger().debug("ModificarCategoriaSpring -end");
		
	}catch(Exception eGenerico){
		
		logger.error("Error ModificarCategoriaSpring -doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return resultado;
	}
	
/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param idCategoria el id categoria
 */
public void generarRegistroLogGenerico(String username, Long idCategoria){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CATEGORIA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.categoria.detalleOperacionModificacion") + " "  + idCategoria.toString());
		logGenericoBean.setIdTablaOrigen(idCategoria);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}



	/**
	 * Obtiene el categoria manager.
	 *
	 * @return the categoriaManager
	 */
	public CategoriaManager getCategoriaManager() {
		return categoriaManager;
	}

	/**
	 * Establece el categoria manager.
	 *
	 * @param categoriaManager the categoriaManager to set
	 */
	public void setCategoriaManager(CategoriaManager categoriaManager) {
		this.categoriaManager = categoriaManager;
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

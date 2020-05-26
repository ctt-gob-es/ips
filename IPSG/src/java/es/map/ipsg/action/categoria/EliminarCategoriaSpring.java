package es.map.ipsg.action.categoria;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.cuerpoescala.EliminarCuerposEscalaSpring;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarCategoriaSpring.
 */
public class EliminarCategoriaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarCuerposEscalaSpring.class);
	
	/** el categoria manager. */
	//Declaracion de manager
	private CategoriaManager categoriaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el cuerpo escala manager. */
	//Para comprobar con estas entidades si se permite borrar la Forma de Acceso
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/**
	 * Crea una nueva eliminar categoria spring.
	 */
	public EliminarCategoriaSpring() {
		try {
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarCategoriaSpring():",e );
		}
	}

/**
 * Metodo doExecute de EliminarFormasAccesoAction.
 *
 * @param form SpringForm
 * @return resultado String Si no tiene errores nos devuelve success
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_Categoria = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.categoria");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Categoria);
		String resultado;
		getLogger().debug("EliminarCategoriaSpring -start");
	try{
		SpringMessages errors = new SpringMessages();
				
		
		String idCategoria=  this.getRequest().getParameter("id");
		
		if(idCategoria != null){
			//Obtenemos la forma de acceso que deseamos eliminar
			CategoriaBean categoriaBean;
			categoriaBean = categoriaManager.obtenerCategoria(Integer.valueOf(idCategoria));						
			categoriaBean.setEstado(Constantes.CATEGORIA_ESTADO_INACTIVO); //Para Eliminarlo
			
			//Si la funcion devuelve false, la categoria no podra ser eliminada porque tendra
			//elementos asociados.
			
			if (compruebaCuerpoEscala(categoriaBean, errors)){
				//Eliminamos el Centro Gestor Una vez validado
				categoriaManager.modificarCategoria(categoriaBean); //Guardamos el cambio de estado en la BBDD
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(categoriaBean.getId()));
			}
			else{
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				return "errorEliminarCategoria";
			}
			
			
			getLogger().debug("EliminarCategoriaSpring -end");
			resultado = "success";
		}else{
			resultado = "error";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error EliminarCategoriaSpring - doExecute: "+ eGenerico);
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
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.categoria.detalleOperacionBaja") + " " + idCategoria.toString());
		logGenericoBean.setIdTablaOrigen(idCategoria);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Comprueba cuerpo escala.
	 *
	 * @param categoriaBean el categoria bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que la forma de acceso que queremos eliminar no se encuentra en Convocatoria	
	private boolean compruebaCuerpoEscala (CategoriaBean categoriaBean, SpringMessages errors){	

		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		cuerpoEscalaQuery.addCategoriaIn(categoriaBean.getId());
		cuerpoEscalaQuery.setEstado(Constantes.CATEGORIA_ESTADO_ACTIVO);
		
		cuerpoEscalaQuery.setCalculateNumResults(true);
		List<CuerpoEscalaBean> cuerpoEscala = cuerpoEscalaManager.buscarCuerpoEscalaAll(cuerpoEscalaQuery);
		//si la consulta ha devuelto alguna convocatoria, implica que tiene datos asociados, luego no
		//se puede eliminar.
		if (cuerpoEscala.size() > 0 )
		{ 			
			SpringMessage error = new SpringMessage("field.categoria.errores.categoriaDescripcion");
				
			errors.add("errorCategoria", error);
			for (int i=0; i < cuerpoEscala.size();i++){
				error = new SpringMessage("field.categoria.errores.cuerpoEscala",  cuerpoEscala.get(i).getId(),cuerpoEscala.get(i).getCodigo());
				errors.add("errorCategoria1", error);
			}
			
			return false; //No se puede Eliminar
		}
		else
		{
			return true;
		}
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
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return the cuerpoEscalaManager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager the cuerpoEscalaManager to set
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
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

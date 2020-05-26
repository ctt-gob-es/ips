package es.map.ipsg.action.titulooficial;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * Clase que implemente EliminarTituloOficialAction.
 *
 * @author amartinl
 */
public class EliminarTituloOficialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante ESTADO_INACTIVO. */
	private static final Character ESTADO_INACTIVO = 'D';
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarTituloOficialSpring.class);
	
	/** el titulo oficial manager. */
	//Declaracion de manager
	private TituloOficialManager tituloOficialManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;

	/**
	 * Acción EliminarTituloOficialAction.
	 */
	public EliminarTituloOficialSpring() {
		try {
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarTituloOficialSpring - crear constructor",e);
		}
	}

/**
 * Método doExecute de EliminarTituloOficialAction.
 *
 * @param form SpringForm
 * @return resultado String Si no tiene errores nos devuelve success
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
		String subMenu_tituloOficial = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tituloOficial");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
		
		getLogger().debug("EliminarTituloOficialSpring -start");
		String resultado;
		try{
			String idTitulo =  this.getRequest().getParameter("id");
			
			if(idTitulo != null){//Dar de Baja el Título
				TituloOficialBean tituloOficialBean;
				
				tituloOficialBean = tituloOficialManager.obtenerTituloOficial(Integer.valueOf(idTitulo));
				tituloOficialBean.setEstado(ESTADO_INACTIVO); //Para desactivarlo
				
				tituloOficialManager.modificarTitulo(tituloOficialBean); //Modificamos el registro cambiando el Estado a 'D'
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),new Long(tituloOficialBean.getId()));
				
				String mensaje = RESOURCE_BUNDLE.getString("field.titulo.eliminarTituloConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.titulo.tituloMantenimientoTitulo");			
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarTituloOficial");
				
				resultado = "success";
			}else{
				resultado = "error";
			}
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarTituloOficialSpring - doExecute",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idTitulo el id titulo
	 */
	public void generarRegistroLogGenerico(String username, Long idTitulo){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_TITULO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion("Baja del titulo oficial con id " + idTitulo.toString());
		logGenericoBean.setIdTablaOrigen(idTitulo);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	/**
	 * Método que toma el valor de tituloOficialManager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Método que establece el valor de tituloOficialManager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
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
	


}

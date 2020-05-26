package es.map.ipsg.action.formaAcceso;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.TipoAcceso;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.cuerpoescala.EliminarCuerposEscalaSpring;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * Clase que implemente EliminarCentroGestorAction.
 *
 * @author amartinl
 */
public class EliminarFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarCuerposEscalaSpring.class);
	
	/** La constante ESTADO_ELIMINADO. */
	private static final Character ESTADO_ELIMINADO = 'D'; //Formas Acceso Eliminado	
	
	/** La constante ESTADO_CONFIGURACION. */
	private static final int ESTADO_CONFIGURACION=4;
	
	/** La constante ESTADO_PUBLICADO. */
	private static final int ESTADO_PUBLICADO=5;
	
	/** La constante ESTADO_APROBADO. */
	private static final int ESTADO_APROBADO=7;
	
	/** el forma acceso manager. */
	//Declaracion de manager
	private FormaAccesoManager formaAccesoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el convocatoria manager. */
	//Para comprobar con estas entidades si se permite borrar la Forma de Acceso
	private ConvocatoriasManager convocatoriaManager;	
	
	/**
	 * Crea una nueva eliminar formas acceso spring.
	 */
	public EliminarFormasAccesoSpring() {
		try {
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarFormasAccesoSpring() :",e);
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
		String subMenu_formaAcceso = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.formaAcceso");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_formaAcceso);
		
		getLogger().debug("EliminarFormasAccesoSpring -start");
		
		SpringMessages errors = new SpringMessages();
		String resultado;		
	try{	
		String idFormaAcceso=  this.getRequest().getParameter("id");
		
		if(idFormaAcceso != null){
			//Obtenemos la forma de acceso que deseamos eliminar
			FormaAccesoBean formaAccesoBean;
			formaAccesoBean = formaAccesoManager.obtenerFormaAcceso(Integer.valueOf(idFormaAcceso));
			
			TipoAcceso tipoAcceso = new TipoAcceso();
			tipoAcceso.setId(formaAccesoBean.getTipoAcceso().getId());
			formaAccesoBean.setTipoAcceso(tipoAcceso);
			formaAccesoBean.setEstado(ESTADO_ELIMINADO); //Para Eliminarlo
			
			//Si la funcion devuelve false, la forma de acceso no podria ser eliminada porque tendria
			//elementos asociados.
			
			if (compruebaConvocatoria(formaAccesoBean, errors)){
				//Eliminamos el Centro Gestor Una vez validado
				formaAccesoManager.modificarFormaAcceso(formaAccesoBean); //Guardamos el cambio de estado en la BBDD
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(), Long.valueOf(formaAccesoBean.getId()));
			}
			else{
				
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				return "errorEliminarFormasAcceso";
			}
			
			String mensaje = RESOURCE_BUNDLE.getString("field.formasAcceso.eliminarFormaAccesoConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.formasAcceso.formaAccesoMantenimientoTitulo");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarFormasAcceso");
			
			getLogger().debug("EliminarFormasAccesoSpring -end");
			resultado = "success";
		}else{
			resultado = "error";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error EliminarFormasAccesoSpring() :",eGenerico);
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
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.formaAcceso.detalleOperacionBaja") + " "  + idFormaAcceso.toString());
		logGenericoBean.setIdTablaOrigen(idFormaAcceso);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Comprueba convocatoria.
	 *
	 * @param formaAccesoBean el forma acceso bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que la forma de acceso que queremos eliminar no se encuentra en Convocatoria	
	private boolean compruebaConvocatoria (FormaAccesoBean formaAccesoBean, SpringMessages errors){	

		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		//Son los distintos estados donde queremos buscar 
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_CONFIGURACION);
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_PUBLICADO);
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_APROBADO);
		convocatoriaQuery.addFormaAccesoIn(formaAccesoBean.getId());		
		
		convocatoriaQuery.setCalculateNumResults(true);
		List<ConvocatoriasBean> convocatorias = convocatoriaManager.buscarConvocatoriasAll(convocatoriaQuery);
		//si la consulta ha devuelto alguna convocatoria, implica que tiene datos asociados, luego no
		//se puede eliminar.
		if (convocatorias.size() > 0 )
		{ 			
			SpringMessage error = new SpringMessage("field.formaAcceso.errores.formaAccesoCodigo", formaAccesoBean.getDescripcion());
				
			errors.add("errorFormasAcceso", error);
			for (int i=0; i < convocatorias.size();i++){
				error = new SpringMessage("field.formaAcceso.errores.convocatoria",  convocatorias.get(i).getIdConvocatoria(),convocatorias.get(i).getEjercicio(),convocatorias.get(i).getEstadoConvocatoria());
				errors.add("errorFormasAcceso1", error);
			}
			
			return false; //No se puede Eliminar
		}
		else
		{
			return true;
		}
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
	 * Obtiene el convocatoria manager.
	 *
	 * @return the convocatoriaManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}


	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager the convocatoriaManager to set
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
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

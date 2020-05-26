package es.map.ipsg.action.adaptacionesDiscapacidad;

import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarDiscapacidadSpring.
 */
public class EliminarDiscapacidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarDiscapacidadSpring.class);
	
	/** el discapacidad manager. */
	private DiscapacidadManager discapacidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva eliminar discapacidad spring.
	 */
	public EliminarDiscapacidadSpring() {
		try {
			setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarDiscapacidadSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_discapacidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.discapacidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_discapacidad);
		
		getLogger().debug("EliminarDiscapacidadSpring -start");
		SpringMessages errors = new SpringMessages();
		
		try{
		
			String idDiscapacidad = this.getRequest().getParameter("id");
				
			DiscapacidadBean discapacidadBean = discapacidadManager.obtenerDiscapacidad(Integer.valueOf(idDiscapacidad));
			
			if (discapacidadBean.getConvocatorias() != null && discapacidadBean.getConvocatorias().size() > 0) {
				//Si existen convocatorias asociadas a una discapacidad
				SpringMessage error = new SpringMessage("field.discapacidad.errores.convocatoria1",discapacidadBean.getDescripcion());
				errors.add("errorConvocatoria",error);
				
				
				
				Iterator<Convocatoria> convocatorias = discapacidadBean.getConvocatorias().iterator();
				while (convocatorias.hasNext()) {
					Convocatoria convocatoria = convocatorias.next();
					if (convocatoria != null) {
						error= new SpringMessage("field.discapacidad.errores.convocatoria3",convocatoria.getId(), convocatoria.getEjercicio(), convocatoria.getEstadoConvocatoria().getDescripcion());
						errors.add("errorConvocatoria1",error);
					}
				}
				
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				
				return "errorEliminar";
			} else if (!discapacidadManager.eliminarDiscapacidad(discapacidadBean)){
				SpringMessage error = new SpringMessage("field.discapacidad.errores.eliminar",discapacidadBean.getDescripcion());
				errors.add("errorConvocatoria",error);
				this.getRequest().setAttribute("org.apache.spring.ERROR", errors);
				return "errorEliminar";
			}
							
			String mensaje = RESOURCE_BUNDLE.getString("field.discapacidad.eliminarDiscapacidadConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.discapacidad.tituloEliminarDiscapacidad");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarDiscapacidad");
			
			getLogger().debug("EliminarDiscapacidadSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarDiscapacidadSpring() - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idDiscapacidad el id discapacidad
	 */
	public void generarRegistroLogGenerico(String username, Long idDiscapacidad){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CUERPO_ESCALA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.discapacidad.detalleOperacionBaja") + " " + idDiscapacidad.toString());
		logGenericoBean.setIdTablaOrigen(idDiscapacidad);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el discapacidad manager.
	 *
	 * @return the discapacidadManager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadManager the discapacidadManager to set
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadManager) {
		this.discapacidadManager = discapacidadManager;
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

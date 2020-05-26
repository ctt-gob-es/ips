package es.map.ipsg.action.otrosTitulos;

import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarOtrosTitulosSpring.
 */
public class EliminarOtrosTitulosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarOtrosTitulosSpring.class);
	
	/** el otros titulos manager. */
	private OtrosTitulosManager otrosTitulosManager;

/** el usuario manager. */
//	private EspecialidadManager especialidadManager;
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva eliminar otros titulos spring.
	 */
	public EliminarOtrosTitulosSpring() {
		try {
			setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
//			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
//			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarOtrosTitulosSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_otrosTitulos = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.otrosTitulos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_otrosTitulos);
		
		getLogger().debug("EliminarOtrosTitulosSpring -start");
		SpringMessages errors = new SpringMessages();
		
		try{
		
			String idOtrosTitulos = this.getRequest().getParameter("id");
				
			OtrosTitulosBean otrosTitulosBean = otrosTitulosManager.obtenerOtroTitulo(Integer.valueOf(idOtrosTitulos));
			
			if (otrosTitulosBean.getConvocatorias() != null && otrosTitulosBean.getConvocatorias().size() > 0) {
				//Si existen convocatorias asociadas a otroTitulo
				SpringMessage error = new SpringMessage("field.otrosTitulos.errores.convocatoria1",otrosTitulosBean.getDescripcion());
				errors.add("errorConvocatoria",error);
				
				
				
				Iterator<Convocatoria> convocatorias = otrosTitulosBean.getConvocatorias().iterator();
				while (convocatorias.hasNext()) {
					Convocatoria convocatoria = convocatorias.next();
					if (convocatoria != null) {
						error= new SpringMessage("field.otrosTitulos.errores.convocatoria3",convocatoria.getId(), convocatoria.getEjercicio(), convocatoria.getEstadoConvocatoria().getDescripcion());
						errors.add("errorConvocatoria1",error);
					}
				}
				
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				
				return "errorEliminar";
			} else if (!otrosTitulosManager.eliminarOtroTitulo(otrosTitulosBean)){
				SpringMessage error = new SpringMessage("field.otrosTitulos.errores.eliminar",otrosTitulosBean.getDescripcion());
				errors.add("errorConvocatoria",error);
				this.getRequest().setAttribute("org.apache.spring.ERROR", errors);
				return "errorEliminar";
			}
							
			String mensaje = RESOURCE_BUNDLE.getString("field.otrosTitulos.eliminarOtrosTitulosConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.otrosTitulos.tituloEliminarOtrosTitulos");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarOtrosTitulos");
			
			getLogger().debug("EliminarOtrosTitulosSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarOtrosTitulosSpring() - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idOtrosTitulos el id otros titulos
	 */
	public void generarRegistroLogGenerico(String username, Long idOtrosTitulos){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CUERPO_ESCALA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.otrosTitulos.detalleOperacionBaja") + " " + idOtrosTitulos.toString());
		logGenericoBean.setIdTablaOrigen(idOtrosTitulos);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}


	//Comprueba que el Cuerpo y Escala que queremos eliminar no se encuentra en Convocatoria
/*	private boolean compruebaConvocatoria (OtrosTitulosBean otrosTitulosBean, SpringMessages errors){	

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		//Son los distintos estados donde queremos buscar 
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
		
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		
		OtrosTitulosQuery otrosTitulosQuery= new OtrosTitulosQuery();
		
		otrosTitulosQuery.setIdOtroTitulo(otrosTitulosBean.getId());
		
		convocatoriaQuery.setOtrosTitulos(otrosTitulosQuery);
		
		
		
		List<ConvocatoriasBean> convocatoria = null;
		convocatoria = convocatoriasManager.buscarConvocatoriasAllSinNumTotal(convocatoriaQuery);
		
		if (convocatoria.size()!=0){
			SpringMessage error = new SpringMessage("field.otrosTitulos.errores.convocatoria1",otrosTitulosBean.getDescripcion());
			errors.add("errorConvocatoria",error);
			this.setRequestAttribute("errorConvocatoria", "true");
			
			for (int i=0; i<convocatoria.size();i++){
				error= new SpringMessage("field.otrosTitulos.errores.convocatoria3",convocatoria.get(i).getIdConvocatoria(), convocatoria.get(i).getEjercicio(), convocatoria.get(i).getEstadoConvocatoria());
				errors.add("errorConvocatoria1",error);
			}
			
			
			return false;
		}
		else		
		return true;
		
	}*/
	//Comprueba que el Cuerpo y Escala que queremos eliminar no se encuentra en Especialidad
/*	private boolean compruebaEspecialidad (OtrosTitulosBean otrosTitulosBean, SpringMessages errors){	

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		EspecialidadQuery especialidadQuery= new EspecialidadQuery();
		
		especialidadQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);		
		
		
		OtrosTitulosQuery otrosTitulosQuery= new OtrosTitulosQuery();
		
		otrosTitulosQuery.setId(otrosTitulosBean.getId());
		
		especialidadQuery.setOtrosTitulos(otrosTitulosQuery);		
		
		
		List<EspecialidadBean> especialidad = null;
		especialidad = especialidadManager.buscarEspecialidadCombo(especialidadQuery);
		
		if (especialidad.size()!=0){			
		
		SpringMessage error = new SpringMessage("field.otrosTitulos.errores.convocatoria2",otrosTitulosBean.getDescripcion());
		errors.add("errorEspecialidad",error);
		this.setRequestAttribute("errorEspecialidad", "true");
		
		for (int i=0; i<especialidad.size();i++){
			error = new SpringMessage("field.otrosTitulos.errores.especialidad1",especialidad.get(i).getId().toString(),especialidad.get(i).getDescripcion());
			errors.add("errorEspecialidad1",error);
		}
		
		return false;
		}
		else		
		return true;
		
	}*/
	/**
	 * Obtiene el otros titulos manager.
	 *
	 * @return the otrosTitulosManager
	 */
	public OtrosTitulosManager getOtrosTitulosManager() {
		return otrosTitulosManager;
	}

	/**
	 * Establece el otros titulos manager.
	 *
	 * @param otrosTitulosManager the otrosTitulosManager to set
	 */
	public void setOtrosTitulosManager(OtrosTitulosManager otrosTitulosManager) {
		this.otrosTitulosManager = otrosTitulosManager;
	}

/*	*//**
 * Obtiene el usuario manager.
 *
 * @return the otrosTitulosManager
 *//*
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	*//**
	 * @param otrosTitulosManager the otrosTitulosManager to set
	 *//*
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}*/

/*	*//**
	 * @return the especialidadManager
	 *//*
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	*//**
	 * @param especialidadManager the especialidadManager to set
	 *//*
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}*/


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

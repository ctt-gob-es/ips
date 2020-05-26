package es.map.ipsg.action.cuerpoescala;

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
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarCuerposEscalaSpring.
 */
public class EliminarCuerposEscalaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarCuerposEscalaSpring.class);
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva eliminar cuerpos escala spring.
	 */
	public EliminarCuerposEscalaSpring() {
		try {
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarCuerposEscalaSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_cuerpoEscala = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.cuerpoEscala");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_cuerpoEscala);
		
		getLogger().debug("EliminarCuerposEscalaSpring -start");
		SpringMessages errors = new SpringMessages();
		
		try{
		
			String idCuerpoEscala = this.getRequest().getParameter("id");
				
			CuerpoEscalaBean cuerpoEscalaBean = cuerpoEscalaManager.obtenerCuerpoEscala(Integer.valueOf(idCuerpoEscala));
			cuerpoEscalaBean.setEstado(Constantes.CUERPO_ESCALA_ESTADO_INACTIVO);
			boolean bEliminaConvocatoria;
			boolean bEliminaEspecialidad;
			bEliminaConvocatoria=compruebaConvocatoria (cuerpoEscalaBean,errors);
			bEliminaEspecialidad=compruebaEspecialidad (cuerpoEscalaBean,errors);
			
			if (bEliminaConvocatoria &&  bEliminaEspecialidad){
				cuerpoEscalaManager.modificarCuerposEscala(cuerpoEscalaBean);
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(cuerpoEscalaBean.getId()));
			}
			else{
				
				this.getRequest().setAttribute("org.apache.spring.ERROR", errors);
				return "errorEliminar";
			}
							
			String mensaje = RESOURCE_BUNDLE.getString("field.cuerpoEscala.eliminarcuerpoEscalaConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.cuerpoEscala.tituloEliminarcuerpoEscala");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarCuerposEscala");
			
			getLogger().debug("EliminarCuerposEscalaSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarCuerposEscalaSpring() - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idCuerpoEscala el id cuerpo escala
	 */
	public void generarRegistroLogGenerico(String username, Long idCuerpoEscala){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CUERPO_ESCALA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.cuerpoEscala.detalleOperacionBaja") + " " + idCuerpoEscala.toString());
		logGenericoBean.setIdTablaOrigen(idCuerpoEscala);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}


	/**
	 * Comprueba convocatoria.
	 *
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que el Cuerpo y Escala que queremos eliminar no se encuentra en Convocatoria
	private boolean compruebaConvocatoria (CuerpoEscalaBean cuerpoEscalaBean, SpringMessages errors){	

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		//Son los distintos estados donde queremos buscar 
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
		
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		
		CuerpoEscalaQuery cuerpoEscalaQuery= new CuerpoEscalaQuery();
		
		cuerpoEscalaQuery.setId(cuerpoEscalaBean.getId());
		
		convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
		
		
		
		List<ConvocatoriasBean> convocatoria = null;
		convocatoria = convocatoriasManager.buscarConvocatoriasAllSinNumTotal(convocatoriaQuery);
		
		if (convocatoria.size()!=0){
			SpringMessage error = new SpringMessage("field.cuerpoEscala.errores.convocatoria1",cuerpoEscalaBean.getDescripcion());
			errors.add("errorConvocatoria",error);
			this.setRequestAttribute("errorConvocatoria", "true");
			
			for (int i=0; i<convocatoria.size();i++){
				error= new SpringMessage("field.cuerpoEscala.errores.convocatoria3",convocatoria.get(i).getIdConvocatoria(), convocatoria.get(i).getEjercicio(), convocatoria.get(i).getEstadoConvocatoria());
				errors.add("errorConvocatoria1",error);
			}
			
			
			return false;
		}
		else		
		return true;
		
	}
	
	/**
	 * Comprueba especialidad.
	 *
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que el Cuerpo y Escala que queremos eliminar no se encuentra en Especialidad
	private boolean compruebaEspecialidad (CuerpoEscalaBean cuerpoEscalaBean, SpringMessages errors){	

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		EspecialidadQuery especialidadQuery= new EspecialidadQuery();
		
		especialidadQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);		
		
		
		CuerpoEscalaQuery cuerpoEscalaQuery= new CuerpoEscalaQuery();
		
		cuerpoEscalaQuery.setId(cuerpoEscalaBean.getId());
		
		especialidadQuery.setCuerpoEscala(cuerpoEscalaQuery);		
		
		
		List<EspecialidadBean> especialidad = null;
		especialidad = especialidadManager.buscarEspecialidadCombo(especialidadQuery);
		
		if (especialidad.size()!=0){			
		
		SpringMessage error = new SpringMessage("field.cuerpoEscala.errores.convocatoria2",cuerpoEscalaBean.getDescripcion());
		errors.add("errorEspecialidad",error);
		this.setRequestAttribute("errorEspecialidad", "true");
		
		for (int i=0; i<especialidad.size();i++){
			error = new SpringMessage("field.cuerpoEscala.errores.especialidad1",especialidad.get(i).getId().toString(),especialidad.get(i).getDescripcion());
			errors.add("errorEspecialidad1",error);
		}
		
		return false;
		}
		else		
		return true;
		
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
	 * Obtiene el convocatorias manager.
	 *
	 * @return the cuerpoEscalaManager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return the especialidadManager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager the especialidadManager to set
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
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

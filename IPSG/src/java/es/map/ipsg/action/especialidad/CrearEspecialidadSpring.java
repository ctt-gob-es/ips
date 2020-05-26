package es.map.ipsg.action.especialidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CuerpoEscalaQuery;

import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.EspecialidadForm;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class CrearEspecialidadSpring.
 */
public class CrearEspecialidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearEspecialidadSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva crear especialidad spring.
	 */
	public CrearEspecialidadSpring() {
		try {
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearEspecialidadSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_especialidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.especialidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_especialidad);
		
		getLogger().debug("CrearEspecialidadSpring -start");
		
		EspecialidadForm formulario = (EspecialidadForm) form;
		
		EspecialidadBean especialidadBean = new EspecialidadBean();
		
		try{
			
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			List<CuerpoEscalaBean> cuerpoEscala = cuerposEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
			
			this.setRequestAttribute("cuerpo", cuerpoEscala);
			
			//Alta de Especialidad			
			especialidadBean.setCodigo(formulario.getCodigo());
			especialidadBean.setDescripcion(formulario.getDescripcion());
			especialidadBean.setIdCuerpoEscala(Integer.valueOf(formulario.getIdCuerpoEscala()));		
			especialidadBean.setEstado(ESTADO_ACTIVO);
			if(formulario.getVisibilidad() != null)
			{	
				especialidadBean.setVisibilidad(formulario.getVisibilidad());
			}
			else
			{
				especialidadBean.setVisibilidad(false);
			}	
			
			
			int result = especialidadManager.guardarEspecialidad(especialidadBean);
			String mensaje;
			String resultado;
			
			if(result>0){
				resultado="success";
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),especialidadBean.getDescripcion(),Long.valueOf(result));
				mensaje = RESOURCE_BUNDLE.getString("field.especialidad.crearEspecialidadConfirmacion");
			}else{
				resultado="error";
				mensaje = RESOURCE_BUNDLE.getString("field.especialidad.crearEspecialidadError");
			}
			
			String titulo = RESOURCE_BUNDLE.getString("field.especialidad.tituloCrearEspecialidad");
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarEspecialidad");
			getLogger().debug("CrearEspecialidadSpring -end");
			return resultado;
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error CrearEspecialidadSpring() - doExecute :",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param especialidadDesc el especialidad desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String especialidadDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ESPECIALIDAD);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.especialidad.detalleOperacionAlta") + " "  + especialidadDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}


	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}
	
	/**
	 * Obtiene el cuerpos escala manager.
	 *
	 * @return el cuerpos escala manager
	 */
	public CuerpoEscalaManager getCuerposEscalaManager() {
		return cuerposEscalaManager;
	}

	/**
	 * Establece el cuerpos escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpos escala manager
	 */
	public void setCuerposEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerposEscalaManager = cuerpoEscalaManager;
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

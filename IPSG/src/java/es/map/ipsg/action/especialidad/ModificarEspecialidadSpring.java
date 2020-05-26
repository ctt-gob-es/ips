package es.map.ipsg.action.especialidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.form.EspecialidadForm;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;

/**
 * El Class ModificarEspecialidadSpring.
 */
public class ModificarEspecialidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarEspecialidadSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
		
	/**
	 * Crea una nueva modificar especialidad spring.
	 */
	public ModificarEspecialidadSpring() {
		try {
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarEspecialidadSpring():",e);
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
		
		getLogger().debug("ModificarEspecialidadSpring -start");
		String resultado;
		
		EspecialidadForm formulario = (EspecialidadForm) form;
		String idEspecialidad= formulario.getId();
		
		
		try{
		
			if(idEspecialidad!=null){//Modificacion de Usuario
				EspecialidadBean especialidadBean = new EspecialidadBean();
				
				especialidadBean.setId(Integer.valueOf(formulario.getId()));				
				especialidadBean.setDescripcion(formulario.getDescripcion());
				especialidadBean.setCodigo(formulario.getCodigo());
				especialidadBean.setIdCuerpoEscala(formulario.getIdCuerpoEscala());			
				especialidadBean.setEstado(ESTADO_ACTIVO);
				if(formulario.getVisibilidad() != null)
				{	
					especialidadBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					especialidadBean.setVisibilidad(false);
				}

				CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
				List<CuerpoEscalaBean> cuerpoEscala = cuerpoEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
				
				this.setRequestAttribute("cuerpo", cuerpoEscala);
				especialidadManager.modificarEspecialidad(especialidadBean);
				
				String mensaje = RESOURCE_BUNDLE.getString("field.especialidad.modificarUsuarioConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.especialidad.tituloMantenimientoEspecialidad");			
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarEspecialidad");
				
				resultado = "success";
			}else{
				resultado = "error";
			}
		
			getLogger().debug("ModificarEspecialidadSpring -end");
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarEspecialidadSpring() - doExecute:",e);
			return "nosuccess";
		}
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
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
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

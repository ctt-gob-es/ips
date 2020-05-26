package es.map.ipsg.action.grupos;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.form.GrupoForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class ActualizarGrupoSpring.
 */
public class ActualizarGrupoSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarGrupoSpring.class);
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/**
	 * Crea una nueva actualizar grupo spring.
	 */
	public ActualizarGrupoSpring() {
		try {
			setGrupoManager((GrupoManager) getBean("grupoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ActualizarGrupoSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_grupo = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.grupo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_grupo);
		
		getLogger().debug("ActualizarGrupoSpring -start");
		GrupoForm formulario;
		formulario = (GrupoForm) form;
		String busqueda = formulario.getSubmit();
		
		try{
			if("Modificar".equals(busqueda)){
				
				GrupoBean grupoBean = cargarFormulario(formulario);
				grupoManager.actualizarGrupo(grupoBean);
			}
			String titulo = "";
			String mensaje = "";
			if("Modificar".equals(busqueda)){
				mensaje = RESOURCE_BUNDLE.getString("field.grupo.actualizar.mensaje");
				titulo = RESOURCE_BUNDLE.getString("field.grupo.actualizar.titulo");
				setRequestAttribute("accion","/spring/buscarGrupo");
			}else{
				mensaje = RESOURCE_BUNDLE.getString("field.grupo.actualizar.mensajeError");
				titulo = RESOURCE_BUNDLE.getString("field.grupo.actualizar.titulo");
				setRequestAttribute("accion","/spring/modificarGrupo?id= "+formulario.getId());
			}
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			
			getLogger().debug("ActualizarGrupoSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarGrupoSpring - doExecute:",e);
			return "nosuccess";
		}
	}

	/**
	 * Cargar formulario.
	 *
	 * @param formulario el formulario
	 * @return el grupo bean
	 */
	private GrupoBean cargarFormulario(GrupoForm formulario) {
		GrupoBean grupoAux = new GrupoBean();
		grupoAux.setId(formulario.getId());
		grupoAux.setCodigo(formulario.getCodigo());
		grupoAux.setDescripcion(formulario.getDescripcion());
		grupoAux.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);		
		grupoAux.setSiglas(formulario.getSiglas());
		if(formulario.getVisibilidad() != null)
		{	
			grupoAux.setVisibilidad(formulario.getVisibilidad());
		}
		else
		{
			grupoAux.setVisibilidad(false);
		}	
		return grupoAux;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return el grupo manager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager el nuevo grupo manager
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
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

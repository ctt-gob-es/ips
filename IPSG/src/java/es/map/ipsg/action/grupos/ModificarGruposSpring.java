package es.map.ipsg.action.grupos;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.GrupoQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.form.GrupoForm;
import es.map.ipsg.manager.GrupoManager;

/**
 * El Class ModificarGruposSpring.
 */
public class ModificarGruposSpring extends AbstractSpring {
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarGruposSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Crea una nueva modificar grupos spring.
	 */
	public ModificarGruposSpring() {
		try {
			setGrupoManager((GrupoManager) getBean("grupoManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarGruposSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_grupo = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.grupo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_grupo);
		
		getLogger().debug("ModificarGrupoSpring -start");
		GrupoForm formulario;
		formulario = (GrupoForm) form;
		try{
			logger.info("Entra en el action de modificar");
			String id = this.getRequest().getParameter("id").trim();
			logger.info("Id: "+id);
			GrupoQuery grupoQuery = new GrupoQuery();
			int codMinisterio = Integer.parseInt(id);
			GrupoBean grupoBean = new GrupoBean();
			if(!"Modificar".equals(formulario.getSubmit())){
				grupoQuery.setId(codMinisterio);
				grupoBean = grupoManager.buscarGrupoUnico(grupoQuery);
			}else{
				grupoBean.setCodigo(formulario.getCodigo());
				grupoBean.setDescripcion(formulario.getDescripcion());
				grupoBean.setId(formulario.getId());
				if(formulario.getVisibilidad() != null)
				{	
					grupoBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					grupoBean.setVisibilidad(false);
				}	
			}
			setRequestAttribute("grupo", grupoBean);
			getLogger().debug("ModificarGrupoSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarGruposSpring - doExecute:",e);
			return "nosuccess";
		}
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
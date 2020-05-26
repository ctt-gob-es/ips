package es.map.ipsg.action.cuerpoescala;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.form.CuerpoEscalaForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarCuerposEscalaSpring.
 */
public class ModificarCuerposEscalaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCuerposEscalaSpring.class);

	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el categoria manager. */
	private CategoriaManager categoriaManager;
		
	/**
	 * Crea una nueva modificar cuerpos escala spring.
	 */
	public ModificarCuerposEscalaSpring() {
		try {
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarCuerposEscalaSpring() :",e);
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
		
		getLogger().debug("ModificarCuerposEscalaSpring -start");
		String resultado;
		
		CuerpoEscalaForm formulario = (CuerpoEscalaForm) form;
		String idCuerpoEscala = formulario.getId();
		String accion = formulario.getAccion();
		
		try{
		
			GrupoQuery grupoQuery = new GrupoQuery();
			List<GrupoBean> grupos = grupoManager.buscarGrupoCombo(grupoQuery);
			
			CategoriaQuery categoriaQuery = new CategoriaQuery();
			List<CategoriaBean> categorias = categoriaManager.buscarCategoriaCombo(categoriaQuery);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			this.setRequestAttribute("categorias",categorias);
			this.setRequestAttribute("grupos",grupos);
			
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "list";
			}else if(idCuerpoEscala!=null){//Modificacion de Usuario
				CuerpoEscalaBean cuerpoEscalaBean = new CuerpoEscalaBean();
				
				cuerpoEscalaBean.setId(Integer.valueOf(formulario.getId()));
				cuerpoEscalaBean.setDescripcion(formulario.getDescripcion());
				cuerpoEscalaBean.setCodigo(formulario.getCodigo());
				cuerpoEscalaBean.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
				if(formulario.getVisibilidad() != null)
				{	
					cuerpoEscalaBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					cuerpoEscalaBean.setVisibilidad(false);
				}	
				
				
				if(StringUtils.isNotEmpty(formulario.getIdCategoria()))
					cuerpoEscalaBean.setIdCategoria(Integer.valueOf(formulario.getIdCategoria()));
				
				if(StringUtils.isNotEmpty(formulario.getCentroGestor()))
					cuerpoEscalaBean.setIdCentroGestor(Integer.valueOf(formulario.getCentroGestor()));
				
				if(StringUtils.isNotEmpty(formulario.getIdGrupo()))
					cuerpoEscalaBean.setIdGrupo(Integer.valueOf(formulario.getIdGrupo()));
				
				cuerpoEscalaManager.modificarCuerposEscala(cuerpoEscalaBean);
				
				String mensaje = RESOURCE_BUNDLE.getString("field.cuerpoEscala.modificarCuerpoEscalaConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.cuerpoEscala.tituloMantenimientoCuerpoEscala");			
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarCuerposEscala");
				
				resultado = "success";
			}else{
				resultado = "error";
			}
			
			getLogger().debug("ModificarCuerposEscalaSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarCuerposEscalaSpring() - doExecute :",e);
			return "nosuccess";
		}
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
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the centroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager the centroGestorManager to set
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}

package es.map.ipsg.action.otrosTitulos;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.form.OtrosTitulosForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.OtrosTitulosManager;

/**
 * El Class ModificarOtrosTitulosSpring.
 */
public class ModificarOtrosTitulosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarOtrosTitulosSpring.class);

	/** el otros titulos manager. */
	private OtrosTitulosManager otrosTitulosManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el categoria manager. */
	private CategoriaManager categoriaManager;
		
	/**
	 * Crea una nueva modificar otros titulos spring.
	 */
	public ModificarOtrosTitulosSpring() {
		try {
			setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarOtrosTitulosSpring() :",e);
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
		
		getLogger().debug("ModificarOtrosTitulosSpring -start");
		String resultado;
		
		OtrosTitulosForm formulario = (OtrosTitulosForm) form;
		String idOtrosTitulos = formulario.getId();
		String accion = formulario.getAccion();
		
		try{
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "list";
			}else if(idOtrosTitulos!=null){//Modificacion de Usuario
				OtrosTitulosBean otrosTitulosBean = new OtrosTitulosBean();
				
				otrosTitulosBean.setId(Integer.valueOf(formulario.getId()));
				otrosTitulosBean.setDescripcion(formulario.getDescripcion().toUpperCase());
				if(formulario.getVisibilidad() != null)
				{	
					otrosTitulosBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					otrosTitulosBean.setVisibilidad(false);
				}	
						
				
				if(StringUtils.isNotEmpty(formulario.getCentroGestor())) {
					otrosTitulosBean.setIdCentroGestor(Integer.valueOf(formulario.getCentroGestor()));
				}
						
				
				if (!otrosTitulosManager.modificarOtrosTitulos(otrosTitulosBean)) {
					resultado = "error";
				} else {
					String mensaje = RESOURCE_BUNDLE.getString("field.otrosTitulos.modificarOtrosTitulosConfirmacion");
					String titulo = RESOURCE_BUNDLE.getString("field.otrosTitulos.tituloMantenimientoOtrosTitulos");			
					
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarOtrosTitulos");
					
					resultado = "success";
				}
			}else{
				resultado = "error";
			}
			
			getLogger().debug("ModificarOtrosTitulosSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarOtrosTitulosSpring() - doExecute :",e);
			return "nosuccess";
		}
	}


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

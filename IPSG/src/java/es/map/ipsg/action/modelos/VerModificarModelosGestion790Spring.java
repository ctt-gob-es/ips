package es.map.ipsg.action.modelos;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.action.centrogestor.VerModificarCentroGestorSpring;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;

/**
 * El Class VerModificarModelosGestion790Spring.
 */
public class VerModificarModelosGestion790Spring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCentroGestorSpring.class);
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
		

	/**
	 * Crea una nueva ver modificar modelos gestion 790 spring.
	 */
	public VerModificarModelosGestion790Spring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
			setCampoPropioManager((CamposPropiosManager)getBean("camposPropiosManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarModelosGestion790Spring:" ,e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.gestionModelos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("VerModificarModelosGestion790Spring -start");
		String resultado;
		
		ModeloGestion790Form formulario = (ModeloGestion790Form) form;
		try{
			String idModelo = this.getRequest().getParameter("id");
			
			if (StringUtils.isEmpty(idModelo) && !StringUtils.isEmpty(formulario.getIdCampo())) {
				idModelo = formulario.getIdCampo();
			}
			
			if(idModelo!=null){			
				
				ModeloBean modeloBean = new ModeloBean();
				logger.info(formulario.getAccion());
				if(!"Modificar".equals(formulario.getAccion())){
					modeloBean = modeloManager.obtenerModelo790ById(Integer.valueOf(idModelo));
					formulario.setId(Integer.toString(modeloBean.getId()));
					formulario.setNumModelo(modeloBean.getNumModelo());
					formulario.setDescripcion(modeloBean.getDescripcion());
				}else{
					comprobarEntero(formulario.getId());
				}
				
				//buscamos los campos propios del modelo		
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
				ModeloQuery modeloQuery = new ModeloQuery();
				modeloQuery.setIdModelo(Integer.parseInt(formulario.getId()));
				camposPropiosQuery.setModelo(modeloQuery);
				List<CamposPropiosBean> camposPropiosBean = campoPropioManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
				this.setRequestAttribute("modeloBean", modeloBean);
				this.setRequestAttribute("camposPropiosBean", camposPropiosBean);
				
				resultado = "success";
			}else{
				resultado = "error";
			}
			
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarModelosGestion790Spring -  doExecute :" ,e);
			return "nosuccess";
		}	
	}

	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 */
	private void comprobarEntero(String numero) {
		try{
			Integer.parseInt(numero);
		}catch(Exception e){
			int id=0;
			logger.error("Error VerModificarModelosGestion790Spring - error parseado Id:"+ id ,e);
		}
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
	 * Obtiene el ministerio manager.
	 *
	 * @return the ministerioManager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager the ministerioManager to set
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
	}

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return the plantillaManager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager the plantillaManager to set
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Obtiene el modelo manager.
	 *
	 * @return the centroGestorManager
	 */
	public ModeloManager getmodeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}
	
	/**
	 * Obtiene el campo propio manager.
	 *
	 * @return el campo propio manager
	 */
	public CamposPropiosManager getCampoPropioManager() {
		return campoPropioManager;
	}

	/**
	 * Establece el campo propio manager.
	 *
	 * @param campoPropioManager el nuevo campo propio manager
	 */
	public void setCampoPropioManager(CamposPropiosManager campoPropioManager) {
		this.campoPropioManager = campoPropioManager;
	}
}

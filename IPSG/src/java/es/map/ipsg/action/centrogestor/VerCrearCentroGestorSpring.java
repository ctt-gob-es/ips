package es.map.ipsg.action.centrogestor;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;


/**
 * El Class VerCrearCentroGestorSpring.
 */
public class VerCrearCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearCentroGestorSpring.class);
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
		
	/**
	 * Crea una nueva ver crear centro gestor spring.
	 */
	public VerCrearCentroGestorSpring() {
		try {
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));	
			setModeloManager((ModeloManager)getBean("modelosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearCentroGestorSpring: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.centroGestor");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("VerCrearCentroGestorSpring -start");
		
		try{
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			ministerioQuery.setVisible('S');
			List<MinisterioBean> ministerio = ministerioManager.buscarMinisterioCombo(ministerioQuery);		
			
			this.setRequestAttribute("ministerio",ministerio);

			List<CentroGestorBean> centroGestor = centroGestorManager.buscarCentroGestorComboTodos();
			
			this.setRequestAttribute("centroGestorLista", centroGestor);
			
			ModeloQuery modeloQuery = new ModeloQuery();
			List<ModeloBean> modeloBean = modeloManager.buscarModeloCombo(modeloQuery);
			this.setRequestAttribute("modelos",modeloBean);
			
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerCrearCentroGestorSpring - doExecute: ",e);
			return "nosuccess";
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
	 * Modelo manager.
	 *
	 * @return the modeloManager
	 */
	public ModeloManager modeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager the modeloManager to set
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
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

package es.map.ipsg.action.modelos;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.manager.ModeloManager;

/**
 * El Class VerCrearModelos790Spring.
 */
public class VerCrearModelos790Spring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearModelos790Spring.class);

	/** el modelo manager. */
	private ModeloManager modeloManager;
		
	/**
	 * Crea una nueva ver crear modelos 790 spring.
	 */
	public VerCrearModelos790Spring() {
		try {	
			setModeloManager((ModeloManager)getBean("modelosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearModelos790Spring:" ,e);
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
		
		getLogger().debug("VerCrearModelos790Spring -start");
		
		try{
			//No hacemos nada, simplemente devolvemos exito para mostrar el jsp
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerCrearModelos790Spring - doExecute:" ,e);
			return "nosuccess";
		}
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
package es.map.ipsg.action.categoria;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;


/**
 * VerCrearCategoriaAction.
 */
public class VerCrearCategoriaSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearCategoriaSpring.class);
	

	/**
	 * VerCrearCategoriaAction.
	 */
	public VerCrearCategoriaSpring() {	//Metodo vacio

	}

	/**
	 * doExecute de VerCrearCategoriaAction.
	 *
	 * @param form SpringForm
	 * @return success String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {	
	try{
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_Categoria = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.categoria");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Categoria);	
	}catch(Exception eGenerico){
		logger.error("Error VerCrearCategoriaSpring : "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return "success";
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

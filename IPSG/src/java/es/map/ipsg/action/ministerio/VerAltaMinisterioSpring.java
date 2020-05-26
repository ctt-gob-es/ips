package es.map.ipsg.action.ministerio;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.manager.MinisterioManager;

/**
 * El Class VerAltaMinisterioSpring.
 */
public class VerAltaMinisterioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaMinisterioSpring.class);

	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
		
	/**
	 * Crea una nueva ver alta ministerio spring.
	 */
	public VerAltaMinisterioSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaMinisterioSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ministerio = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.ministerio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ministerio);
		
		try{
			getLogger().debug("VerAltaMinisterioSpring -start");
			logger.info("Entra en el Action");
			
			List<MinisterioBean> ministerioLista = ministerioManager.buscarMinisterioComboTodos();
			
			this.setRequestAttribute("ministerioLista", ministerioLista);
			
			getLogger().debug("VerAltaMinisterioSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerAltaMinisterioSpring - doExecute:",e);
			return "nosuccess";
		}
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
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

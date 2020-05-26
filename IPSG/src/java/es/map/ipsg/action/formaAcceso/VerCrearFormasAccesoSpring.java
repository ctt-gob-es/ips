package es.map.ipsg.action.formaAcceso;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.TipoAccesoManager;

/**
 * El Class VerCrearFormasAccesoSpring.
 */
public class VerCrearFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearFormasAccesoSpring.class);

	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;	
		
	/**
	 * Crea una nueva ver crear formas acceso spring.
	 */
	public VerCrearFormasAccesoSpring() {
		try {
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));			
		} catch (Exception e) {
			logger.error("Error VerCrearFormasAccesoSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {		
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_formaAcceso = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.formaAcceso");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_formaAcceso);
		
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> tipoAcceso = tipoAccesoManager.buscarTipoAccesoCombo(tipoAccesoQuery);		
		
		this.setRequestAttribute("tipoAcceso",tipoAcceso);
		
		return "success";
	}

	/**
	 * Obtiene el tipo acceso manager.
	 *
	 * @return the tipoAccesoManager
	 */
	public TipoAccesoManager getTipoAccesoManager() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAccesoManager the tipoAccesoManager to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAccesoManager) {
		this.tipoAccesoManager = tipoAccesoManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return the formaAccesoManager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager the formaAccesoManager to set
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}
}

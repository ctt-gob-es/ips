package es.map.ipsg.action.grupos;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;

/**
 * El Class VerAltaGrupoSpring.
 */
public class VerAltaGrupoSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaGrupoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Crea una nueva ver alta grupo spring.
	 */
	public VerAltaGrupoSpring() {
		try {
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaGrupoSpring:",e);
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
		try{
			getLogger().debug("VerAltaGrupoSpring -start");
			logger.info("Entra en el Action");
			getLogger().debug("VerAltaGrupoSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerAltaGrupoSpring - doExecute:",e);
			return "nosuccess";
		}
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

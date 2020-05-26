package es.map.ipsg.action.motivoReduccionTasa;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.manager.MotivoReduccionTasaManager;

/**
 * El Class VerCrearMotivoReduccionTasaSpring.
 */
public class VerCrearMotivoReduccionTasaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el motivo manager. */
	private MotivoReduccionTasaManager motivoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearMotivoReduccionTasaSpring.class);
	
	
	/**
	 * Crea una nueva ver crear motivo reduccion tasa spring.
	 */
	public VerCrearMotivoReduccionTasaSpring() {
		try {
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		} catch (Exception e) {
			logger.error("Error VerCrearMotivoReduccionTasaSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_motivos = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.motivoReduccion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_motivos);
		
		
		return "success";
	}

	/**
	 * Obtiene el motivo manager.
	 *
	 * @return the motivoManager
	 */
	public MotivoReduccionTasaManager getMotivoManager() {
		return motivoManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoManager the motivoManager to set
	 */
	public void setMotivoReduccionTasaManager(MotivoReduccionTasaManager motivoManager) {
		this.motivoManager = motivoManager;
	}

	


}

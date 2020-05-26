package es.map.ipsg.action.alerta;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.AlertaQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.manager.AlertaManager;

/**
 * El Class ModificarAlertaSpring.
 */
public class ModificarAlertaSpring extends AbstractSpring {
	
	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarAlertaSpring.class);
	
	/**
	 * Crea una nueva modificar alerta spring.
	 */
	public ModificarAlertaSpring() {
		try {
			setAlertaManager((AlertaManager) getBean("alertasManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error modificar Alerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Modificar Eliminar Alerta Accion");
	try{
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		getLogger().debug("ModificarAlertaSpring -start");
		String id = this.getRequest().getParameter("id").trim();
		
		AlertaQuery alertaQuery = new AlertaQuery();
		int codAlerta = Integer.parseInt(id);
		alertaQuery.setId(codAlerta);
		AlertaBean alertaBean;
		alertaBean = alertaManager.buscarAlerta(alertaQuery);
		
		setRequestAttribute("alerta", alertaBean);
		getLogger().debug("ModificarAlertaSpring -end");
		logger.info("Termina Eliminar Alerta Accion");
		
	}catch(Exception eGenerico){
		logger.error("Error modificar alerta - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}

	/**
	 * Obtiene el alerta manager.
	 *
	 * @return el alerta manager
	 */
	public AlertaManager getAlertaManager() {
		return alertaManager;
	}

	/**
	 * Establece el alerta manager.
	 *
	 * @param alertaManager el nuevo alerta manager
	 */
	public void setAlertaManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
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
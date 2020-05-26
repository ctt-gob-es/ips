package es.map.ipsg.action.configuracionErrores;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.manager.LogGenericoManager;

public class VerCrearConfiguracionErroresSpring extends AbstractSpring {

	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	private static final Logger logger = Logger.getLogger(VerCrearConfiguracionErroresSpring.class);

	private LogGenericoManager logGenericoManager;
		
	public VerCrearConfiguracionErroresSpring() {
		try {
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearConfiguracionErroresSpring() - doExecute:",e);
		}
	}

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ConfiguracionErrores = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.configuracionErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ConfiguracionErrores);
		
		return "success";
	}

	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}

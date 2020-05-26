package es.map.ipsg.action.incidencia;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.manager.IncidenciasManager;

/**
 * El Class VerBuscarIncidenciasSpring.
 */
public class VerBuscarIncidenciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerBuscarIncidenciasSpring.class);

	//Declaracion de manager

	/** el incidencias manager. */
	private IncidenciasManager incidenciasManager;


	
		
	/**
	 * Crea una nueva ver buscar incidencias spring.
	 */
	public VerBuscarIncidenciasSpring() {
		try {
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerBuscarIncidenciasSpring",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("VerBuscarIncidenciasSpring -start");
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
			String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
			this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
			String subMenu_incidencias = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.incidencias");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_incidencias);
			//****************************************************************** 
	
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerBuscarIncidenciasSpring - doExecute",e);
			return "nosuccess";
		}
	}


			
	








	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return the incidenciasManager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager the incidenciasManager to set
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
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

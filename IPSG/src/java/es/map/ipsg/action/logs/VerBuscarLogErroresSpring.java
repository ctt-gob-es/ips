package es.map.ipsg.action.logs;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoServicioQuery;
import es.map.ipsg.bean.TipoServicioBean;
import es.map.ipsg.form.LogErroresForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.TipoServicioManager;



/**
 * El Class VerBuscarLogErroresSpring.
 */
public class VerBuscarLogErroresSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	

	//Declaracion de manager
	/**
	 * Para recuperar los Errores se utiliza la tabla y ls manager LogServicio pero con el Resultado de error (ER).
	 */
	private LogServicioManager logErroresManager;
	
	/** el tipo servicio manager. */
	private TipoServicioManager tipoServicioManager;

	
	/** La constante logger. */
	//Variables Paginacion
	private static final Logger logger = Logger.getLogger(LogServicioManager.class);
	
		
	/**
	 * Crea una nueva ver buscar log errores spring.
	 */
	public VerBuscarLogErroresSpring() {
		try {
			setLogErroresManager((LogServicioManager) getBean("logServicioManager"));
			setTipoServicioManager((TipoServicioManager) getBean("tipoServicioManager"));
		} catch (Exception e) {
			logger.error("Error VerBuscarLogErroresSpring:",e);
		}
	}

	




	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.logErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
		//****************************************************************** 
		getLogger().debug("VerBuscarLogErroresSpring -start");
	try{
		String paginaActual = this.getRequest().getParameter("paginaActual");
	
		String cambios = this.getRequest().getParameter("cambios");
		
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		
		LogErroresForm formulario;
		formulario = (LogErroresForm) form;
		formulario.setNumeroSolicitud("");	
		formulario.setNif("");
		form = formulario;
		
		List <TipoServicioBean> tiposServicios=null;
		TipoServicioQuery tipoServicioQuery=new TipoServicioQuery();
		tipoServicioQuery.setCalculateNumResults(true);
		tiposServicios=tipoServicioManager.buscarTipoServicioAll(tipoServicioQuery);
		
		
		setRequestAttribute("tiposServicios", tiposServicios);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("cambios", cambios);

		getLogger().debug("VerBuscarLogErroresSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error VerBuscarLogErroresSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return "success";
	}

	/**
	 * Obtiene el log errores manager.
	 *
	 * @return the logErroresManager
	 */
	public LogServicioManager getLogErroresManager() {
		return logErroresManager;
	}

	/**
	 * Establece el log errores manager.
	 *
	 * @param logErroresManager the logErroresManager to set
	 */
	public void setLogErroresManager(LogServicioManager logErroresManager) {
		this.logErroresManager = logErroresManager;
	}



	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}






	/**
	 * Obtiene el tipo servicio manager.
	 *
	 * @return the tipoServicioManager
	 */
	public TipoServicioManager getTipoServicioManager() {
		return tipoServicioManager;
	}






	/**
	 * Establece el tipo servicio manager.
	 *
	 * @param tipoServicioManager the tipoServicioManager to set
	 */
	public void setTipoServicioManager(TipoServicioManager tipoServicioManager) {
		this.tipoServicioManager = tipoServicioManager;
	}

}

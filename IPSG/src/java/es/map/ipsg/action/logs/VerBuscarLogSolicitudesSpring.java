package es.map.ipsg.action.logs;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.LogSolicitudesForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.TipoServicioManager;


/**
 * El Class VerBuscarLogSolicitudesSpring.
 */
public class VerBuscarLogSolicitudesSpring extends AbstractSpring {


	//Declaracion de manager
	/**
	 * Para recuperar los Errores se utiliza la tabla y ls manager LogServicio pero con el Resultado de error (ER).
	 */
	
	
	private static final Logger logger = Logger.getLogger(LogServicioManager.class);

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";

	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
		
	/**
	 * Crea una nueva ver buscar log solicitudes spring.
	 */
	public VerBuscarLogSolicitudesSpring() {
		try {
		} catch (Exception e) {
			logger.error("Error VerBuscarLogSolicitudesSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_BUNDLE.getString("field.menuLateral.consultas.logSolicitudes");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
		//****************************************************************** 
		getLogger().debug("VerBuscarLogSolicitudesSpring -start");
	try{

		String paginaActual = this.getRequest().getParameter("paginaActual");
	
		LogSolicitudesForm formulario;
		formulario = (LogSolicitudesForm) form;
		formulario.setNif(null);
		formulario.setIdConvocatoria(null);
		formulario.setDsConvocatoria(null);
		formulario.setFechaDesde(null);
		formulario.setFechaHasta(null);
		formulario.setNumeroJustificante(null);
		String cambios = this.getRequest().getParameter("cambios");
		
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("cambios", cambios);

		getLogger().debug("VerBuscarLogSolicitudesSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error VerBuscarLogSolicitudesSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}


	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}


}

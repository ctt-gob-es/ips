package es.map.ipsg.action.aviso;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ipsg.bean.EstadoAvisoBean;
import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.manager.EstadoAvisoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerAltaAvisoSpring.
 */
public class VerAltaAvisoSpring extends AbstractSpring {
	
	/** el aviso manager. */
	private AvisoManager avisoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el estado aviso manager. */
	private EstadoAvisoManager estadoAvisoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaAvisoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Crea una nueva ver alta aviso spring.
	 */
	public VerAltaAvisoSpring() {
		try {
			setAvisoManager((AvisoManager) getBean("avisoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setEstadoAvisoManager((EstadoAvisoManager) getBean("estadoAvisoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaAvisoSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.avisos");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_aviso = RESOURCE_BUNDLE.getString("field.menuLateral.avisos.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_aviso);
		//****************************************************************** 
		getLogger().debug("VerAltaAvisoSpring -start");
	try{
		
		//Rellenamos el combo de estados.
		EstadoAvisoQuery estadoAvisoQuery= new EstadoAvisoQuery();
		
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_ACTIVO);
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_INACTIVO);
		ArrayList <EstadoAvisoBean> estadosList;
		
		estadosList=estadoAvisoManager.buscarEstadoAvisoCombo(estadoAvisoQuery);
		
		
		setRequestAttribute("estados", estadosList);

		getLogger().debug("VerAltaAvisoSpring -end");
	
	}catch(Exception eGenerico){
		logger.error("Error VerAltaAviso - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}

	/**
	 * Obtiene el aviso manager.
	 *
	 * @return the avisoManager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}

	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager the avisoManager to set
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el estado aviso manager.
	 *
	 * @return the estadoAvisoManager
	 */
	public EstadoAvisoManager getEstadoAvisoManager() {
		return estadoAvisoManager;
	}

	/**
	 * Establece el estado aviso manager.
	 *
	 * @param estadoAvisoManager the estadoAvisoManager to set
	 */
	public void setEstadoAvisoManager(EstadoAvisoManager estadoAvisoManager) {
		this.estadoAvisoManager = estadoAvisoManager;
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

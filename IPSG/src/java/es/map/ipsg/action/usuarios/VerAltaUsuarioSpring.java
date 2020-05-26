package es.map.ipsg.action.usuarios;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.PerfilQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.PerfilBean;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.PerfilManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class VerAltaUsuarioSpring.
 */
public class VerAltaUsuarioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaUsuarioSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el perfil manager. */
	private PerfilManager perfilManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
		
	/**
	 * Crea una nueva ver alta usuario spring.
	 */
	public VerAltaUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setPerfilManager((PerfilManager) getBean("perfilManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaUsuarioSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_usuario = RESOURCE_BUNDLE.getString("field.menu.usuarios");
		this.getRequest().getSession().setAttribute("pagActiva", menu_usuario);
		String subMenu_usuario = RESOURCE_BUNDLE.getString("field.menuLateral.usuarios.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuario);
		
		getLogger().debug("VerAltaUsuarioSpring -start");
		logger.info("Entra en el Action");
		try{
			PerfilQuery perfilQuery = new PerfilQuery();
			List<PerfilBean> perfiles = perfilManager.buscarPerfiles(perfilQuery);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			this.setRequestAttribute("perfiles",perfiles);
			this.setRequestAttribute("idCg",0);
			getLogger().debug("VerAltaUsuarioSpring -end");
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerAltaUsuarioSpring - doExecute",e);
			return "nosuccess";}
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	/**
	 * Obtiene el perfil manager.
	 *
	 * @return el perfil manager
	 */
	public PerfilManager getPerfilManager() {
		return perfilManager;
	}

	/**
	 * Establece el perfil manager.
	 *
	 * @param perfilManager el nuevo perfil manager
	 */
	public void setPerfilManager(PerfilManager perfilManager) {
		this.perfilManager = perfilManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
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

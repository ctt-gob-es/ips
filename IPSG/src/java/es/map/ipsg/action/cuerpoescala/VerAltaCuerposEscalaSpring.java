package es.map.ipsg.action.cuerpoescala;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerAltaCuerposEscalaSpring.
 */
public class VerAltaCuerposEscalaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaCuerposEscalaSpring.class);
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el categoria manager. */
	private CategoriaManager categoriaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver alta cuerpos escala spring.
	 */
	public VerAltaCuerposEscalaSpring() {
		try {
			setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaCuerposEscalaSpring() :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_cuerpoEscala = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.cuerpoEscala");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_cuerpoEscala);
		
		getLogger().debug("VerAltaCuerposEscalaSpring -start");
		
		//INI - cpasculi - IPS-148 - Campo CentroGestor
		
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				UsuarioBean usuarioBean = recuperarUsuario(usuarioSession.getLogin());
				
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
				
		try{
		
			GrupoQuery grupoQuery = new GrupoQuery();
			grupoQuery.setVisible('S');
			List<GrupoBean> grupos = grupoManager.buscarGrupoCombo(grupoQuery);
			
			CategoriaQuery categoriaQuery = new CategoriaQuery();
			categoriaQuery.setVisible('S');
			List<CategoriaBean> categorias = categoriaManager.buscarCategoriaCombo(categoriaQuery);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			this.setRequestAttribute("categorias",categorias);
			this.setRequestAttribute("grupos",grupos);
			
			//INI - cpasculi - IPS-148 - Campo CentroGestor
			
				if(usuarioBean!=null){
					/*INI-TRA042*/
					logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
					setRequestAttribute("rol", usuarioBean.getIdPerfil());

					String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);

					if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
					{
						List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
						this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
					}
					/*FIN-TRA042*/
				}
			
			//FIN - cpasculi - IPS-148 - Campo CentroGestor
			
			getLogger().debug("VerAltaCuerposEscalaSpring -end");
			return "success";
		
		}catch(Exception e){
				logger.warn(e);
				this.getRequest().setAttribute("descripcionError", e.getMessage());
				logger.error("Error VerAltaCuerposEscalaSpring() - doExecute :",e);
				return "nosuccess";
			}
	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
	/*INI-TRA042*/
	private String comprobarPerfilUsuario(UsuarioBean usuarioBean) {
		String sPerfil = "";
		
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;

	}
	/*FIN-TRA042*/
	
	//INI - cpasculi - IPS-148 - Campo CentroGestor
	
		/**
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(username);
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			return usuarioBean;
		}

		/**
		 * Check rol usuario.
		 *
		 * @param usuarioBean el usuario bean
		 */
		
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
	

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
	 * Obtiene el cuerpos escala manager.
	 *
	 * @return the cuerposEscalaManager
	 */
	public CuerpoEscalaManager getCuerposEscalaManager() {
		return cuerposEscalaManager;
	}

	/**
	 * Establece el cuerpos escala manager.
	 *
	 * @param cuerposEscalaManager the cuerposEscalaManager to set
	 */
	public void setCuerposEscalaManager(CuerpoEscalaManager cuerposEscalaManager) {
		this.cuerposEscalaManager = cuerposEscalaManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Obtiene el categoria manager.
	 *
	 * @return the categoriaManager
	 */
	public CategoriaManager getCategoriaManager() {
		return categoriaManager;
	}

	/**
	 * Establece el categoria manager.
	 *
	 * @param categoriaManager the categoriaManager to set
	 */
	public void setCategoriaManager(CategoriaManager categoriaManager) {
		this.categoriaManager = categoriaManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
	
	
}

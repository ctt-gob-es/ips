package es.map.ipsg.action.especialidad;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerCrearEspecialidadSpring.
 */
@SuppressWarnings("rawtypes")
public class VerCrearEspecialidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearEspecialidadSpring.class);
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;	
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver crear especialidad spring.
	 */
	public VerCrearEspecialidadSpring() {
		try {
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));	
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearEspecialidadSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_especialidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.especialidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_especialidad);
		
		getLogger().debug("VerCrearEspecialidadSpring -start");
		
		//INI - cpasculi - IPS-148 - Campo CentroGestor
		
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				UsuarioBean usuarioBean = recuperarUsuario(usuarioSession.getLogin());
			
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
		
		try{
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			if(usuarioBean!=null){
				/*INI-TRA042*/
				logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
				setRequestAttribute("rol", usuarioBean.getIdPerfil());
				String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
				List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

				if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
				{
					CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
					listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
					for(CentroGestorBean cg: listaCentrosGestores) {
						centroGestorQuery.addIdCentroGestorIn(cg.getId());
					}
					cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
				}
				/*FIN-TRA042*/
			}
			
			List<CuerpoEscalaBean> cuerpoEscala = cuerpoEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
			
			this.setRequestAttribute("cuerpo",cuerpoEscala);
			getLogger().debug("VerCrearEspecialidadSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerCrearEspecialidadSpring() - doExecute:",e);
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

	//FIN - cpasculi - IPS-148 - Campo CentroGestor
		
	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}
	
	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
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

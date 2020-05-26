package es.map.ipsg.action.otrosTitulos;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.OtrosTitulosForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerModificarOtrosTitulosSpring.
 */
@SuppressWarnings("rawtypes")
public class VerModificarOtrosTitulosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarOtrosTitulosSpring.class);

	/** el otros titulos manager. */
	private OtrosTitulosManager otrosTitulosManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_otrosTitulos = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.otrosTitulos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_otrosTitulos);
		
		setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
		setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		/*INI-TRA042*/
		setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		
		getLogger().debug("VerAltaOtrosTitulosSpring -start");
		String resultado;
		
		setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		recuperarUsuario(usuarioBean.getNif());
				
		String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
		}
		
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		/*FIN-TRA042*/
		
		try{
		
			OtrosTitulosForm formulario = (OtrosTitulosForm) form;
			
			String idOtrosTitulos = this.getRequest().getParameter("id");
			
			if(idOtrosTitulos!=null){
				
				OtrosTitulosBean otrosTitulosBean = otrosTitulosManager.obtenerOtroTitulo(Integer.valueOf(idOtrosTitulos));
				logger.info(formulario.getAccion());
			
				if(!"Modificar".equals(formulario.getAccion())){
					formulario.setDescripcion(otrosTitulosBean.getDescripcion().toString());
					/*INI-TRA042*/
					formulario.setCentroGestor(otrosTitulosBean.getIdCentroGestor().toString());
					/*FIN-TRA042*/
					if(otrosTitulosBean.getVisibilidad() != null && !otrosTitulosBean.getVisibilidad().toString().equals(""))
					{
						formulario.setVisibilidad(otrosTitulosBean.getVisibilidad());
					}
					else
					{
						formulario.setVisibilidad(false);
					}
					
					if (otrosTitulosManager.modificarOtrosTitulos(otrosTitulosBean))
					
					this.setRequestAttribute("otrosTitulos", otrosTitulosBean);	
				}
			}
			
			resultado = "success";
			
			getLogger().debug("VerAltaOtrosTitulosSpring -end");
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarOtrosTitulosSpring() - doExecute :",e);
			return "nosuccess";
		}
	}

	/*INI-TRA042*/
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
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
	 * Obtiene el otros titulos manager.
	 *
	 * @return the otrosTitulosaManager
	 */
	public OtrosTitulosManager getOtrosTitulosManager() {
		return otrosTitulosManager;
	}

	/**
	 * Establece el otros titulos manager.
	 *
	 * @param otrosTitulosaManager the otrosTitulosaManager to set
	 */
	public void setOtrosTitulosManager(OtrosTitulosManager otrosTitulosaManager) {
		this.otrosTitulosManager = otrosTitulosaManager;
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
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean;
	}

	/*INI-TRA042*/
	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


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

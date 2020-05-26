package es.map.ipsg.action.adaptacionesDiscapacidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.DiscapacidadForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarDiscapacidadSpring.
 */
@SuppressWarnings("rawtypes")
public class ModificarDiscapacidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarDiscapacidadSpring.class);

	/** el discapacidad manager. */
	private DiscapacidadManager discapacidadManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el categoria manager. */
	private CategoriaManager categoriaManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
		
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * Crea una nueva modificar discapacidad spring.
	 */
	public ModificarDiscapacidadSpring() {
		try {
			setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarDiscapacidadSpring() :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_discapacidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.discapacidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_discapacidad);
		
		getLogger().debug("ModificarDiscapacidadSpring -start");
		String resultado;
		
		DiscapacidadForm formulario = (DiscapacidadForm) form;
		String idDiscapacidad = formulario.getId();
		String accion = formulario.getAccion();
		
		try{
			/*INI-TRA042*/			
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			usuarioBean = recuperarUsuario(usuarioBean.getNif());
			
			String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);

			if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
			{
				List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
				this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
			} 
			/*FIN-TRA042*/		
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "list";
			}else if(idDiscapacidad!=null){//Modificacion de Usuario
				DiscapacidadBean discapacidadBean = new DiscapacidadBean();
				
				discapacidadBean.setId(Integer.valueOf(formulario.getId()));
				discapacidadBean.setDescripcion(formulario.getDescripcion().toUpperCase());
				if(formulario.getVisibilidad() != null)
				{	
					discapacidadBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					discapacidadBean.setVisibilidad(false);
				}	
						
				
				if(StringUtils.isNotEmpty(formulario.getCentroGestor())) {
					discapacidadBean.setIdCentroGestor(Integer.valueOf(formulario.getCentroGestor()));
				}
						
				
				if (!discapacidadManager.modificarDiscapacidad(discapacidadBean)) {
					resultado = "error";
				} else {
					String mensaje = RESOURCE_BUNDLE.getString("field.discapacidad.modificarDiscapacidadConfirmacion");
					String titulo = RESOURCE_BUNDLE.getString("field.discapacidad.tituloMantenimientoDiscapacidad");			
					
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarDiscapacidad");
					
					resultado = "success";
				}
			}else{
				resultado = "error";
			}
			
			getLogger().debug("ModificarDiscapacidadSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarDiscapacidadSpring() - doExecute :",e);
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
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el discapacidad manager.
	 *
	 * @return the discapacidadManager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadManager the discapacidadManager to set
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadManager) {
		this.discapacidadManager = discapacidadManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the centroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager the centroGestorManager to set
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
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

	/**
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	/*FIN-TRA042*/	
}

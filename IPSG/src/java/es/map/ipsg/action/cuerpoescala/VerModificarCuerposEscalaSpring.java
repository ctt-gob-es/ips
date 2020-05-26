package es.map.ipsg.action.cuerpoescala;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CuerpoEscalaForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class VerModificarCuerposEscalaSpring.
 */
public class VerModificarCuerposEscalaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCuerposEscalaSpring.class);

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
		
	/**
	 * Crea una nueva ver modificar cuerpos escala spring.
	 */
	public VerModificarCuerposEscalaSpring() {
		try {
			setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarCuerposEscalaSpring() :",e);
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
		String resultado;
		
		setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		checkRolUsuario(recuperarUsuario(usuarioBean.getLogin()));
			
		try{
		
			CuerpoEscalaForm formulario = (CuerpoEscalaForm) form;
			GrupoQuery grupoQuery = new GrupoQuery();
			grupoQuery.setVisible('S');
			List<GrupoBean> grupos = grupoManager.buscarGrupoCombo(grupoQuery);
			
			CategoriaQuery categoriaQuery = new CategoriaQuery();
			categoriaQuery.setVisible('S');
			List<CategoriaBean> categorias = categoriaManager.buscarCategoriaCombo(categoriaQuery);
			
			
			String idCuerpoEscala = this.getRequest().getParameter("id");
			
			if(idCuerpoEscala!=null){
				
				CuerpoEscalaBean cuerpoEscalaBean = cuerposEscalaManager.obtenerCuerpoEscala(Integer.valueOf(idCuerpoEscala));
				logger.info(formulario.getAccion());
			
				if(!"Modificar".equals(formulario.getAccion())){
					formulario.setDescripcion(cuerpoEscalaBean.getDescripcion().toString());
					formulario.setCodigo(cuerpoEscalaBean.getCodigo().toString());
					
					doExecuteAux(formulario,cuerpoEscalaBean);
				
					if(cuerpoEscalaBean.getVisibilidad() != null && !cuerpoEscalaBean.getVisibilidad().toString().equals(""))
					{
						formulario.setVisibilidad(cuerpoEscalaBean.getVisibilidad());
					}
					else
					{
						formulario.setVisibilidad(false);
					}
					
					this.setRequestAttribute("cuerpoEscala", cuerpoEscalaBean);	
				}
			}
			
			this.setRequestAttribute("grupos",grupos);
			this.setRequestAttribute("categorias",categorias);
			
			resultado = "success";
			
			getLogger().debug("VerAltaCuerposEscalaSpring -end");
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarCuerposEscalaSpring() - doExecute :",e);
			return "nosuccess";
		}
	}

	
	/**
	 * Do execute aux.
	 *
	 * @param formulario el formulario
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 */
	public void doExecuteAux(CuerpoEscalaForm formulario, CuerpoEscalaBean cuerpoEscalaBean) {
		if(cuerpoEscalaBean.getIdCategoria() != null)
			formulario.setIdCategoria(cuerpoEscalaBean.getIdCategoria().toString());
		if(cuerpoEscalaBean.getIdCentroGestor() != null){
			formulario.setCentroGestor(cuerpoEscalaBean.getIdCentroGestor().toString());
			formulario.setDsCentroGestor(cuerpoEscalaBean.getDesCentroGestor());
		}
		if(cuerpoEscalaBean.getIdGrupo() != null)
			formulario.setIdGrupo(cuerpoEscalaBean.getIdGrupo().toString());
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
	public void checkRolUsuario(UsuarioBean usuarioBean){
		
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());

	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
}

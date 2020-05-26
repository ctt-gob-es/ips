package es.map.ipsg.action.cuerpoescala;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CuerpoEscalaForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class AltaCuerposEscalaSpring.
 */
public class AltaCuerposEscalaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaCuerposEscalaSpring.class);
	
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
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva alta cuerpos escala spring.
	 */
	public AltaCuerposEscalaSpring() {
		try {
			setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaCuerposEscalaSpring():",e);
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
		
		getLogger().debug("AltaCuerposEscalaSpring -start");
		CuerpoEscalaForm formulario = (CuerpoEscalaForm) form;
		
		CuerpoEscalaBean cuerpoEscalaBean = new CuerpoEscalaBean();
		
		SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
		
		AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		UsuarioBean usuarioBean = recuperarUsuario(user.getIdentificador());
		
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
		
		
		//INI - cpasculi - IPS-148 - Campo CentroGestor

			if(usuarioBean!=null){
				/*INI-TRA042*/
				logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
				setRequestAttribute("rol", usuarioBean.getIdPerfil());
				
				String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
				List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

				if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
				{
					listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
					this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
				}
				/*FIN-TRA042*/
			}
	
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
		
		try{
		
			//Alta de CuerposEscala

			cuerpoEscalaBean.setDescripcion(formulario.getDescripcion());
			cuerpoEscalaBean.setCodigo(formulario.getCodigo());
			cuerpoEscalaBean.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
			if(formulario.getVisibilidad() != null)
			{	
				cuerpoEscalaBean.setVisibilidad(formulario.getVisibilidad());
			}
			else
			{
				cuerpoEscalaBean.setVisibilidad(false);
			}	
			
			GrupoQuery grupoQuery = new GrupoQuery();
			List<GrupoBean> grupos = grupoManager.buscarGrupoCombo(grupoQuery);
			
			CategoriaQuery categoriaQuery = new CategoriaQuery();
			List<CategoriaBean> categorias = categoriaManager.buscarCategoriaCombo(categoriaQuery);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			this.setRequestAttribute("categorias",categorias);
			this.setRequestAttribute("grupos",grupos);
			
			
			if(formulario.getCentroGestor()!=null && !formulario.getCentroGestor().equals(""))
				cuerpoEscalaBean.setIdCentroGestor(Integer.valueOf(formulario.getCentroGestor()));
			
			if(formulario.getIdGrupo()!=null && !formulario.getIdGrupo().equals(""))
				cuerpoEscalaBean.setIdGrupo(Integer.valueOf(formulario.getIdGrupo()));
			
			if(formulario.getIdCategoria()!=null && !formulario.getIdCategoria().equals(""))
				cuerpoEscalaBean.setIdCategoria(Integer.valueOf(formulario.getIdCategoria()));
			
			int result=0;
			logger.info(new BeanFormatter().format(cuerpoEscalaBean));
			
			result =cuerposEscalaManager.guardarCuerposEscala(cuerpoEscalaBean);

			String mensaje;
			String resultado;
			
			if(result>0){
				UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioSession.getLogin(),cuerpoEscalaBean.getDescripcion(), Long.valueOf(result));
				resultado="success";
				mensaje = RESOURCE_BUNDLE.getString("field.cuerpoEscala.altaCuerpoEscalaConfirmacion");
				}else{
				resultado="error";
				mensaje = RESOURCE_BUNDLE.getString("field.cuerpoEscala.altaCuerpoEscalaError");
				}
			
			String titulo = RESOURCE_BUNDLE.getString("field.cuerpoEscala.tituloAltaCuerpoEscala");
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarCuerposEscala");
			
			getLogger().debug("AltaCuerposEscalaSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaCuerposEscalaSpring()- doExecute:",e);
			return "nosucess";
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
				usuarioQuery.setNif(username);
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				return usuarioBean;
			}
			
			//FIN - cpasculi - IPS-148 - Campo CentroGestor
	
	/**
			 * Generar registro log generico.
			 *
			 * @param username el username
			 * @param cuerpoEscalaDesc el cuerpo escala desc
			 * @param resultado el resultado
			 */
			public void generarRegistroLogGenerico(String username, String cuerpoEscalaDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CUERPO_ESCALA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.cuerpoEscala.detalleOperacionAlta") + " " + cuerpoEscalaDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
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
	/*FIN-TRA042*/
}

package es.map.ipsg.action.otrosTitulos;

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
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.OtrosTitulosForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class AltaOtrosTitulosSpring.
 */
@SuppressWarnings("rawtypes")
public class AltaOtrosTitulosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaOtrosTitulosSpring.class);
	
	/** el Otros titulos manager. */
	private OtrosTitulosManager OtrosTitulosManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
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
		
		getLogger().debug("AltaOtrosTitulosSpring -start");
		OtrosTitulosForm formulario = (OtrosTitulosForm) form;
		
		OtrosTitulosBean otrosTitulosBean = new OtrosTitulosBean();	
		
		setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
		setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		/*INI-TRA042*/
		setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		usuarioBean = recuperarUsuario(usuarioBean.getNif());
		
		if(usuarioBean!=null){
			logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
			setRequestAttribute("rol", usuarioBean.getIdPerfil());
			String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
			List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

			if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
			{
				listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
				this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
			}
		}
		/*FIN-TRA042*/
		
		try{
		
			//Alta de OtrosTitulos
			otrosTitulosBean.setDescripcion(formulario.getDescripcion().toUpperCase());
			if(formulario.getVisibilidad() != null)
			{	
				otrosTitulosBean.setVisibilidad(formulario.getVisibilidad());
			}
			else
			{
				otrosTitulosBean.setVisibilidad(false);
			}	
			
			if(formulario.getCentroGestor()!=null && !formulario.getCentroGestor().equals("")) {
				otrosTitulosBean.setIdCentroGestor(Integer.valueOf(formulario.getCentroGestor()));
			}	
			
			int result=0;
			logger.info(new BeanFormatter().format(otrosTitulosBean));
			
			result =OtrosTitulosManager.guardarOtrosTitulos(otrosTitulosBean);

			String mensaje;
			String resultado;
			
			if(result>0) {
				SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
				
				AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
				generarRegistroLogGenerico(user.getIdentificador(),otrosTitulosBean.getDescripcion(), Long.valueOf(result));
				resultado="success";
				mensaje = RESOURCE_BUNDLE.getString("field.otrosTitulos.altaOtrosTitulosConfirmacion");
			}else{
				resultado="error";
				mensaje = RESOURCE_BUNDLE.getString("field.otrosTitulos.altaOtrosTitulosError");
			}
			
			String titulo = RESOURCE_BUNDLE.getString("field.otrosTitulos.tituloAltaOtrosTitulos");
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarOtrosTitulos");
			
			getLogger().debug("AltaOtrosTitulosSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaOtrosTitulosSpring()- doExecute:",e);
			return "nosucess";
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
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param otrosTitulosDesc el otros titulos desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String otrosTitulosDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CUERPO_ESCALA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.otrosTitulos.detalleOperacionAlta") + " " + otrosTitulosDesc);
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
	 * Obtiene el otros titulos manager.
	 *
	 * @return the OtrosTitulosManager
	 */
	public OtrosTitulosManager getOtrosTitulosManager() {
		return OtrosTitulosManager;
	}

	/**
	 * Establece el otros titulos manager.
	 *
	 * @param OtrosTitulosManager the OtrosTitulosManager to set
	 */
	public void setOtrosTitulosManager(OtrosTitulosManager OtrosTitulosManager) {
		this.OtrosTitulosManager = OtrosTitulosManager;
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

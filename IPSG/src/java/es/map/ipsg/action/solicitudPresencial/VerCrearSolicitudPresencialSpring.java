package es.map.ipsg.action.solicitudPresencial;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class VerCrearSolicitudPresencialSpring.
 *
 * @author amartinl
 */
public class VerCrearSolicitudPresencialSpring extends AbstractSpring {

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearSolicitudPresencialSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Crea una nueva ver crear solicitud presencial spring.
	 */
	public VerCrearSolicitudPresencialSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			} catch (Exception e) {
				logger.error("Error VerCrearSolicitudPresencialSpring- crear constructor",e);
		}
		
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_solicitudPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		logger.info("--------Entramos en VerCrearSolicitudPresencial");
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = "";
	try{	
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error VerCrearSolicitudPresencialSpring - recuperar usuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}

		
		
		//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		//Cogemos el form del jsp
		AltaSolicitudPresencialForm formulario;
		formulario = (AltaSolicitudPresencialForm) form;
		
		if(formulario.getAccion() != null && formulario.getAccion().equals("VOLVER"))
		{
			return "list";
		}
		else
		{	
			logger.info("Formulario:"+formulario.getDatosSolicitud());
			
			
			//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
			String sPerfilUsuario = "";
			sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
			formulario.setPerfilUsuario(sPerfilUsuario);
			//Indicamos si viene desde el enlace del menu para mostrar los resultados de busqueda o no 
			//y guardar la descripcion para el formulario de busqueda
			String sVieneMenu = this.getRequest().getParameter("menu"); 
			if (sVieneMenu != null && sVieneMenu.equals("S"))
			{
				formulario.setAccion("IrAlta");
			}else //Si viene a null es que viene de las demas paginas que no son del menu principal
			{
				sVieneMenu = "N";
			}
			//FIN Limpiado de formulario y muestra de resultados.
			
			//Pasar la solicitud y la pagina al jsp
			setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
			setRequestAttribute("sVieneMenu", "S"); //Esta en S para que no muestre resultados al ir hacia atras
			logger.info("---------Salimos de VerCrearSolicitudPresencial");
			return "success";
		}
	}catch(Exception eGenerico){
		logger.error("Error VerCrearSolicitudPresencialSpring- doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
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
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return centroGestorManager CentroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager CentroGestorManager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}
}

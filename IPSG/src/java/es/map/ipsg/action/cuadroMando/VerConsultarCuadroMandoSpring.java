package es.map.ipsg.action.cuadroMando;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.EstadoConvocatoriaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ConsultarCuadroMandoForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerConsultarCuadroMandoSpring.
 */
public class VerConsultarCuadroMandoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerConsultarCuadroMandoSpring.class);
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el estados convocatoria manager. */
	private EstadoConvocatoriaManager estadosConvocatoriaManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;

	/**
	 * Crea una nueva ver consultar cuadro mando spring.
	 */
	public VerConsultarCuadroMandoSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setEstadosConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error("Error VerConsultarCuadroMandoSpring(): ",e);
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
		String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_cuadroMando = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.CdM");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_cuadroMando);
		//******************************************************************
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		
		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();

		
		//Cogemos el form del jsp
		ConsultarCuadroMandoForm formulario;
		formulario = (ConsultarCuadroMandoForm) form;
		//Creamos la Query

		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());	
		}
			
		//Carga el Combo de los Centros Gestores
		cargarCombos();
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco
			formulario.setListaCentrosGestores(listaCentrosGestores);
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			
			
		}else //Si viene a null es que viene de las demás páginas que no son del menú principal
		{
			sVieneMenu = "N";
		}
		setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		/*FIN-TRA042*/
	}catch(Exception eGenerico){
		
		logger.error("Error VerConsultarCuadroMandoSpring()-doExecute: ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}

	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error VerConsultarCuadroMandoSpring()- recuperar usuarioSesion: "+ sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
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
		
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		
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
	 * Carga el combo del Estado.
	 */
	public void cargarCombos() {	
		
		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CERRADO);
		List<EstadoConvocatoriaBean> lEstadoConvocatoriaBean;
		lEstadoConvocatoriaBean = estadosConvocatoriaManager.buscarEstadoConvocatoriaCombo(estadoConvocatoriaQuery);
		
		setRequestAttribute("estadosConvocatoria", lEstadoConvocatoriaBean);
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

	/**
	 * Obtiene el estados convocatoria manager.
	 *
	 * @return estadosConvocatoriaManager EstadoConvocatoriaManager
	 */
	public EstadoConvocatoriaManager getEstadosConvocatoriaManager() {
		return estadosConvocatoriaManager;
	}

	/**
	 * Establece el estados convocatoria manager.
	 *
	 * @param estadosConvocatoriaManager EstadoConvocatoriaManager
	 */
	public void setEstadosConvocatoriaManager(
			EstadoConvocatoriaManager estadosConvocatoriaManager) {
		this.estadosConvocatoriaManager = estadosConvocatoriaManager;
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